package com.example.reminiscence;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPhotoViewActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    ImageView imageView;
    Button btnPick;
    EditText inputName;
    Button saveImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_view);

        //initializing views

        imageView = findViewById(R.id.PickImageView);
        btnPick = findViewById(R.id.PickImageButton);
        inputName = findViewById(R.id.InputName);
        saveImageButton = findViewById(R.id.saveImageButton);


        btnPick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            Uri imageData = data.getData();
            imageView.setImageURI(imageData);

        }

    }

}