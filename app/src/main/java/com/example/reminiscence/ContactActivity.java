package com.example.reminiscence;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ContactActivity extends AppCompatActivity {
    private ContactsPhonesDbAdapter dbAdapter;
    private ListView m_listview;

    // para indicar en un Intent si se quiere crear una nueva nota o editar una existente
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        dbAdapter = new ContactsPhonesDbAdapter(this);
        dbAdapter.open();

        // Creamos un listview que va a contener el título de todas las notas y
        // en el que cuando pulsemos sobre un título lancemos una actividad de editar
        // la nota con el id correspondiente
        m_listview = (ListView) findViewById(R.id.phone_list);
        m_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), ContactEditActivity.class);
                        i.putExtra(ContactsPhonesDbAdapter.KEY_ROWID, id);
                        startActivityForResult(i, ACTIVITY_EDIT);
                    }
                }
        );
        // rellenamos el listview con los títulos de todas las notas en la BD
        fillData();
    }

    private void fillData() {
        Cursor phonesCursor = dbAdapter.fetchAllPhones();

        // Creamos un array con los campos que queremos mostrar en el listview (sólo el título de la nota)
        String[] from = new String[]{ContactsPhonesDbAdapter.KEY_NAME};

        // array con los campos que queremos ligar a los campos del array de la línea anterior (en este caso sólo text1)
        int[] to = new int[]{R.id.text1};

        // Creamos un SimpleCursorAdapter y lo asignamos al listview para mostrarlo
        SimpleCursorAdapter phones =
                new SimpleCursorAdapter(this, R.layout.notepad_row, phonesCursor, from, to, 0);
        m_listview.setAdapter(phones);
    }

    public void createPhone(View view) {
        Intent i = new Intent(this, ContactEditActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}