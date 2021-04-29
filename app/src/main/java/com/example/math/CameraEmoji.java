package com.example.math;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraEmoji extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 101;

    private ImageView prev_emoji_camera;
    int key = 0;
    int background = 0;

    String currentPhotoPath;
    String tmp_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().containsKey("fondo")){
                background = getIntent().getIntExtra("fondo", background);
                BackgroundClass.putBackground(background, this);
            }
        }else{
            BackgroundClass.putBackground(-1, this);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojis_camera);

        prev_emoji_camera = findViewById(R.id.emojis_camara_previa);
        ImageButton emojis_camera_open_camera = findViewById(R.id.emojis_camara_open_camara);

        key = getIntent().getIntExtra("key", 0);

        tmp_path = getIntent().getStringExtra("fondo");

        emojis_camera_open_camera.setOnClickListener(v -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ActivityCompat.checkSelfPermission(CameraEmoji.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    dispatchTakePictureIntent();
                }else{
                    ActivityCompat.requestPermissions(CameraEmoji.this, new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);
                }
            }else{
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_TAKE_PHOTO){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else{
                Toast.makeText(this, "" + this.getString(R.string.permisos), Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //---------- Method to display preview image ----------
    static final int REQUEST_IMAGE_CAPTURE = 101;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE){
            if (resultCode == RESULT_OK) {
                /*
                Bitmap imageBitmap = (Bitmap)data.getExtras().get("data");
                if(imageBitmap != null)emojis_camara_previa.setImageBitmap(imageBitmap);*/
                prev_emoji_camera.setImageURI(Uri.parse(currentPhotoPath));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //----------------------------------------------------------------------------------------------

    //--------- Method to take a photo and create the file ---------

    @SuppressLint("QueryPermissionsNeeded")
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {// Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.math",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    //----------------------------------------------------------------------------------------------

    //--------- Method to create a unique name to each image ---------

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "BackUp_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",  /* suffix */
                storageDir     /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Emojis.class);
        if(currentPhotoPath == null) i.putExtra("imagen", tmp_path);
        else {
            i.putExtra("imagen", currentPhotoPath);
        }
        i.putExtra("fondo", background);
        i.putExtra("key", key);
        startActivity(i);
        finish();
    }
}