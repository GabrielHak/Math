package com.example.math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Selection extends AppCompatActivity {

    int s_2 = 15, s_3 = 45, s_4 = 90, s_5 = 150;
    int s_6 = 225, s_7 = 315, s_8 = 420, s_9 = 540, s_10 = 675;
    int score = 0;
    int max_score = 0;
    int best_score = 0;
    String name = "";
    Switch selection_switch_practice;
    int n_score = 0;
    int background = 0;
    int mission = 0;
    @SuppressLint("ResourceAsColor")
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
        setContentView(R.layout.activity_selection);

        Button selection_button_2 = findViewById(R.id.selection_button_2);
        Button selection_button_3 = findViewById(R.id.selection_button_3);
        Button selection_button_4 = findViewById(R.id.selection_button_4);
        Button selection_button_5 = findViewById(R.id.selection_button_5);
        Button selection_button_6 = findViewById(R.id.selection_button_6);
        Button selection_button_7 = findViewById(R.id.selection_button_7);
        Button selection_button_8 = findViewById(R.id.selection_button_8);
        Button selection_button_9 = findViewById(R.id.selection_button_9);
        Button selection_button_10 = findViewById(R.id.selection_button_10);

        selection_switch_practice = findViewById(R.id.seleccion_switch_ejercicios);
        selection_switch_practice.setChecked(true);

        best_score = getIntent().getIntExtra("best_puntaje", score);
        n_score = getIntent().getIntExtra("puntaje", score);

        if(best_score > score) score = best_score;
        else score = n_score;

        if(score < s_10) selection_button_10.setBackgroundColor(R.color.bg_dif);
        if(score < s_9) selection_button_9.setBackgroundColor(R.color.bg_dif);
        if(score < s_8) selection_button_8.setBackgroundColor(R.color.bg_dif);
        if(score < s_7) selection_button_7.setBackgroundColor(R.color.bg_dif);
        if(score < s_6) selection_button_6.setBackgroundColor(R.color.bg_dif);
        if(score < s_5) selection_button_5.setBackgroundColor(R.color.bg_dif);
        if(score < s_4) selection_button_4.setBackgroundColor(R.color.bg_dif);
        if(score < s_3) selection_button_3.setBackgroundColor(R.color.bg_dif);
        if(score < s_2) selection_button_2.setBackgroundColor(R.color.bg_dif);

        name = getIntent().getStringExtra("nombre");
    }

    //---------- Buttons to play ---------
    @SuppressLint("SetTextI18n")
    public void goTo(View view){
        int id = view.getId();

        query();

        if(selection_switch_practice.isChecked()){
            selection_switch_practice.setText(this.getString(R.string.ejercicios));
            practices(id);
        }else{
            selection_switch_practice.setText(this.getString(R.string.teoria));
            Toast.makeText(this, "" + this.getString(R.string.n_teoria), Toast.LENGTH_SHORT).show();
        }

    }
    //----------------------------------------------------------------------------------------------

    //--------- Practices ----------
    public void practices(int id){
        int level = 0;
        if(id == R.id.seleccion_button_1){
            level = 1;
        }else if(id == R.id.selection_button_2){
            if(score >= s_2){
                level = 2;
            }else{
                Toast.makeText(this,  this.getString(R.string.consigue) + " "+s_2+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_3) {
            if(score >= s_3){
                level = 3;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_3+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_4){
            if(score >= s_4){
                level = 4;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_4+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_5){
            if(score >= s_5){
                level = 5;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_5+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_6){
            if(score >= s_6){
                level = 6;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_6+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_7){
            if(score >= s_7){
                level = 7;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_7+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_8){
            if(score >= s_8){
                level = 8;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_8+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_9){
            if(score >= s_9){
                level = 9;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_9+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.selection_button_10){
            if(score >= s_10){
                level = 10;
            }else{
                Toast.makeText(this, this.getString(R.string.consigue) + " "+s_10+" " + this.getString(R.string.estrellitas), Toast.LENGTH_SHORT).show();
            }
        }

        if(level > 0){
            Intent i = new Intent(this, Levels.class);
            i.putExtra("nivel", level);
            i.putExtra("nombre", name);
            i.putExtra("puntaje", score);
            i.putExtra("fondo", background);
            startActivity(i);
            finish();
        }
    }
    //----------------------------------------------------------------------------------------------

    //--------- Best score query ----------
    public void query(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        @SuppressLint("Recycle") Cursor consulta = db.rawQuery("select * from puntaje where score = (select max(score) from puntaje)", null);
        if(consulta.moveToFirst()){
            max_score = consulta.getInt(1);
        }
    }
    //----------------------------------------------------------------------------------------------

    //---------- Overflow ----------
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home, menu);
        getMenuInflater().inflate(R.menu.config, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_config){
            Intent config = new Intent(this, Configuration.class);
            config.putExtra("fondo", background);
            startActivity(config);
            finish();
        }else if(id == R.id.menu_home){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("fondo", background);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}