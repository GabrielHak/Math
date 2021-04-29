package com.example.math;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class DeveloperConfiguration extends AppCompatActivity {
    int background = 0;
    private Switch config_developer_switch_restart_db;

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
        setContentView(R.layout.activity_developer_configuration);

        config_developer_switch_restart_db = findViewById(R.id.config_developer_switch_restart_db);
    }

    public void swts(View view){
        if(config_developer_switch_restart_db.isChecked()) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String clearDBQuery = "DELETE FROM puntaje";
            db.execSQL(clearDBQuery);
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Configuration.class);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}