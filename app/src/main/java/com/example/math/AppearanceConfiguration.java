package com.example.math;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AppearanceConfiguration extends AppCompatActivity {
    int background = 0;
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
        setContentView(R.layout.activity_appearance_configuration);
    }

    //---------- Overflow ----------
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_home){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("fondo", background);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //---------- Emojis Button ----------
    public void emojis(View view){
        Intent emojis = new Intent(this, Emojis.class);
        emojis.putExtra("fondo", background);
        startActivity(emojis);
        finish();
    }
    //----------------------------------------------------------------------------------------------

    //--------- Background Button ----------
    public void backgrounds(View view){
        Intent backgrounds = new Intent(this, Backgrounds.class);
        backgrounds.putExtra("fondo", background);
        startActivity(backgrounds);
        finish();
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Configuration.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}