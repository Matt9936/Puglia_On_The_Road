package com.example.pugliaontheroad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AttivitaAdapter extends ArrayAdapter<Attivita> {

    Context mCtx;
    int layoutRes;
    List<Attivita> attivitaList;
    SQLiteDatabase mDataBase;

    public AttivitaAdapter(Context mCtx, int layoutRes, List<Attivita> attivitaList, SQLiteDatabase mDataBase) {
        super(mCtx, layoutRes, attivitaList);

        this.mCtx=mCtx;
        this.layoutRes=layoutRes;
        this.attivitaList=attivitaList;
        this.mDataBase=mDataBase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(layoutRes, null);

        TextView textViewLuogo = view.findViewById(R.id.textViewLuogo);
        TextView textViewData = view.findViewById(R.id.textViewData);
        TextView textViewOrario = view.findViewById(R.id.textViewOrario);
        TextView textViewInfo = view.findViewById(R.id.textViewInfo);

        final Attivita attivita = attivitaList.get(position);

        textViewLuogo.setText(attivita.getLuogo());
        textViewData.setText(attivita.getData());
        textViewOrario.setText(attivita.getOrario());
        textViewInfo.setText(attivita.getInfo());

        view.findViewById(R.id.buttonElimina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAttivita(attivita);
            }
        });

        view.findViewById(R.id.buttonModifica).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAttivita(attivita);
            }
        });
        return view;
    }

    private void updateAttivita(Attivita attivita){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_attivita, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextLuogo = view.findViewById(R.id.editTextLuogo);
        final EditText editTextData = view.findViewById(R.id.editTextData);
        final EditText editTextOrario = view.findViewById(R.id.editTextOrario);
        final EditText editTextInfo = view.findViewById(R.id.editTextInfo);

        editTextLuogo.setText(attivita.getLuogo());
        editTextData.setText(attivita.getData());
        editTextOrario.setText(attivita.getOrario());
        editTextInfo.setText(attivita.getInfo());



        view.findViewById(R.id.buttonSalvaModifiche).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String luogo = editTextLuogo.getText().toString().trim();
                String data = editTextData.getText().toString().trim();
                String orario = editTextOrario.getText().toString().trim();
                String info = editTextInfo.getText().toString().trim();

                if (luogo.isEmpty()) {
                    editTextLuogo.setError("Inserisci un luogo");
                    editTextLuogo.requestFocus();
                    return;
                }

                if (data.isEmpty()) {
                    editTextData.setError("Inserisci una data");
                    editTextData.requestFocus();
                    return;
                }

                String sql ="UPDATE attivita SET luogo = ?, data = ?, orario = ?, info = ? WHERE id = ?";
                mDataBase.execSQL(sql, new String[]{luogo, data, orario, info, String.valueOf(attivita.getId())});
                Toast.makeText(mCtx, "Attività modificata", Toast.LENGTH_SHORT).show();
                loadAttivitaFromDatabaseAgain();

                alertDialog.dismiss();
            }
        });
        }

        private void deleteAttivita(Attivita attivita) {
           AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
           builder.setTitle("Eliminare questa attività?");
           builder.setPositiveButton("ELIMINA", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   String sql = "DELETE FROM attivita WHERE id = ?";
                   mDataBase.execSQL(sql, new Integer[]{attivita.getId()});
                   loadAttivitaFromDatabaseAgain();
               }
           });
            builder.setNegativeButton("ANNULLA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }

    private void loadAttivitaFromDatabaseAgain() {
        String sql = "SELECT * FROM attivita";
        Cursor cursor = mDataBase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            attivitaList.clear();
            do {
                attivitaList.add(new Attivita(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());

            notifyDataSetChanged();
        }

    }
    }



