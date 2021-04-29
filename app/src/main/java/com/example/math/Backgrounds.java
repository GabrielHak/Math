package com.example.math;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class Backgrounds extends AppCompatActivity {
    int background = 0;
    ImageButton backgrounds_imageView_5, backgrounds_imageView_6, backgrounds_imageView_4, backgrounds_imageView_3;
    ImageButton backgrounds_imageView_2, backgrounds_imageView_1;
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
        setContentView(R.layout.activity_backgrounds);

        backgrounds_imageView_1 = findViewById(R.id.backgrounds_imageView_1);
        backgrounds_imageView_2 = findViewById(R.id.backgrounds_imageView_2);
        backgrounds_imageView_3 = findViewById(R.id.backgrounds_imageView_3);
        backgrounds_imageView_4 = findViewById(R.id.backgrounds_imageView_4);
        backgrounds_imageView_5 = findViewById(R.id.backgrounds_imageView_5);
        backgrounds_imageView_6 = findViewById(R.id.backgrounds_imageView_6);

        backgrounds_imageView_1.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 1);
            startActivity(i);
        });

        backgrounds_imageView_2.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 2);
            startActivity(i);
        });

        backgrounds_imageView_3.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 3);
            startActivity(i);
        });

        backgrounds_imageView_4.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 4);
            startActivity(i);
        });

        backgrounds_imageView_5.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 5);
            startActivity(i);
        });

        backgrounds_imageView_6.setOnClickListener(v -> {
            Intent i = new Intent(Backgrounds.this, MainActivity.class);
            i.putExtra("fondo", 6);
            startActivity(i);
        });

    }
    //---------- Overflow ----------
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_home){
            Intent inicio = new Intent(this, MainActivity.class);
            inicio.putExtra("fondo", background);
            startActivity(inicio);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, AppearanceConfiguration.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}