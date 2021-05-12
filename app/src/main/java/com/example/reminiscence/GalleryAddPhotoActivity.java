package com.example.reminiscence;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



public class GalleryAddPhotoActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    GalleryViewModelClass myViewModel;


    ImageView imageView;
    ImageButton btnPick;
    EditText inputName;
    ImageButton saveImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_add_photo);

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

            String name = inputName.getText().toString();
            Uri imageData = data.getData();
            imageView.setImageURI(imageData);



            myViewModel = new ViewModelProvider(this).get(GalleryViewModelClass.class);

            //AQUI GRABAR A LA DATABASE LOS DOS VALORES to string
            if (name.length() != 0) {
                myViewModel.insert(new PhotoName(imageData.toString(), name));
                Toast.makeText(getBaseContext(), name + " was added to your gallery", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getBaseContext(),"Please fill the name", Toast.LENGTH_LONG).show();
//                return;
            }



        }

    }

    public void onGoToGalleryClick(View v){
        Intent intent = new Intent(this, GalleryShowActivity.class);
        startActivity(intent);
    }

}