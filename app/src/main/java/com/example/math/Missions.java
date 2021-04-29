package com.example.math;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Missions extends AppCompatActivity {

    int background = 0;
    int score = 0;
    String name = "";

    ImageView missions_fo1, missions_fo2, missions_pow, missions_sqrt, missions_fraction, missions_decimal;

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
        setContentView(R.layout.activity_missions);

        missions_fo1 = findViewById(R.id.missions_fo1);
        missions_fo2 = findViewById(R.id.missions_fo2);
        missions_pow = findViewById(R.id.missions_pow);
        missions_sqrt = findViewById(R.id.misiones_sqrt);
        missions_fraction = findViewById(R.id.misiones_fraction);
        missions_decimal = findViewById(R.id.missions_decimal);

        name = getIntent().getStringExtra("nombre");
        score = getIntent().getIntExtra("best_puntaje", score);

        missions_fo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Missions.this, Selection.class);
                i.putExtra("nombre", name);
                i.putExtra("best_puntaje", score);
                i.putExtra("fondo", background);
                startActivity(i);
                finish();
            }
        });
    }

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
            Intent home = new Intent(this, MainActivity.class);
            home.putExtra("fondo", background);
            startActivity(home);
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