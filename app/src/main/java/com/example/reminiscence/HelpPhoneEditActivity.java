package com.example.reminiscence;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HelpPhoneEditActivity extends AppCompatActivity {

    private EditText mNameText;
    private EditText mPhoneText;
    private Long mRowId;
    private HelpPhonesDbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_phone_edit);

        // obtiene referencia a los tres views que componen el layout
        mNameText = (EditText) findViewById(R.id.name);
        mPhoneText = (EditText) findViewById(R.id.phone);
        ImageButton confirmButton = (ImageButton) findViewById(R.id.confirm_phone);

        //creamos el adaptador de la BD y la abrimos
        dbAdapter = new HelpPhonesDbAdapter(this);
        dbAdapter.open();

        // obtiene id de fila de la tabla si se le ha pasado (hemos pulsado una nota para editarla)
        mRowId = (savedInstanceState == null) ? null :
                (Long) savedInstanceState.getSerializable(HelpPhonesDbAdapter.KEY_ROWID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(HelpPhonesDbAdapter.KEY_ROWID) : null;
        }

        // Si se le ha pasado un id (no era null) rellena el título y el cuerpo con los campos guardados en la BD
        // en caso contrario se dejan en blanco (editamos una nota nueva)
        if (mRowId != null) {
            Cursor phone = dbAdapter.fetchPhone(mRowId);
            mNameText.setText(phone.getString(
                    phone.getColumnIndexOrThrow(HelpPhonesDbAdapter.KEY_NAME)));
            mPhoneText.setText(phone.getString(
                    phone.getColumnIndexOrThrow(HelpPhonesDbAdapter.KEY_PHONE)));
        }
    }

    public void savePhone(View view) {
        String name = mNameText.getText().toString();
        String phone = mPhoneText.getText().toString();

        if (name.length() == 0 || phone.length() == 0) {
            alertDialog("Please fill the name and the phone");
            return;
        }
        if (phone.length() < 9) {
            alertDialog("Please enter a correct phone number");
            return;
        }

        if (mRowId == null) {
            long id = dbAdapter.createPhone(name, phone);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            dbAdapter.updatePhone(mRowId, name, phone);
        }
        setResult(RESULT_OK);
        dbAdapter.close();
        finish();
    }

    public void callPhone(View view) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL );
        String phone = mPhoneText.getText().toString();
        dialIntent.setData(Uri.parse("tel:" + phone));
        startActivity(dialIntent);
    }

    public void deletePhoneHandler(View view){
        alertDialog("Do you want to delete this phone?");
    }

    public void deletePhone(){
        if (mRowId != null) {
            dbAdapter.deletePhone(mRowId);
        }
        setResult(RESULT_OK);
        dbAdapter.close();
        finish();
    }

    private void alertDialog(String message) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setTitle("Reminiscence");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (message.endsWith("?")){
                        deletePhone();
                    };
                }
            });
        if (message.endsWith("?")){
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}