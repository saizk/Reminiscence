package com.example.reminiscence;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class NotepadActivity extends AppCompatActivity {
    private NotepadDbAdapter dbAdapter;
    private ListView m_listview;

    // para indicar en un Intent si se quiere crear una nueva nota o editar una existente
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        dbAdapter = new NotepadDbAdapter(this);
        dbAdapter.open();

        // Creamos un listview que va a contener el título de todas las notas y
        // en el que cuando pulsemos sobre un título lancemos una actividad de editar
        // la nota con el id correspondiente
        m_listview = (ListView) findViewById(R.id.id_list_view);
        m_listview.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    Intent i = new Intent(view.getContext(), NotepadEditActivity.class);
                    i.putExtra(NotepadDbAdapter.KEY_ROWID, id);
                    startActivityForResult(i, ACTIVITY_EDIT);
                }
            }
        );

        // rellenamos el listview con los títulos de todas las notas en la BD
        fillData();
    }

    private void fillData() {
        Cursor notesCursor = dbAdapter.fetchAllNotes();

        // Creamos un array con los campos que queremos mostrar en el listview (sólo el título de la nota)
        String[] from = new String[]{NotepadDbAdapter.KEY_TITLE};

        // array con los campos que queremos ligar a los campos del array de la línea anterior (en este caso sólo text1)
        int[] to = new int[]{R.id.text1};

        // Creamos un SimpleCursorAdapter y lo asignamos al listview para mostrarlo
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.notepad_row, notesCursor, from, to, 0);
        m_listview.setAdapter(notes);
    }

    public void createNote(View view) {
        Intent i = new Intent(this, NotepadEditActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}