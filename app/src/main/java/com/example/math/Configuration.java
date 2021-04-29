package com.example.math;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Configuration extends AppCompatActivity {
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
        setContentView(R.layout.activity_configuration);
    }

    public void appearance(View view){
        Intent i = new Intent(this, AppearanceConfiguration.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }

    public void sound(View view){
        Toast.makeText(this, "Sound", Toast.LENGTH_SHORT).show();
    }

    public void developer(View view){
        Intent i = new Intent(this, DeveloperConfiguration.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
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
    //----------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}