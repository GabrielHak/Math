package com.example.math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText main_textView_name;
    private TextView  main_textView_bestScore;
    private ImageView main_imageView_icon;
    String r_data_1 = "";
    String r_data_2 = "";
    String r_data_3 = "";
    String temp_name = "";
    String temp_score = "";
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

        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        main_textView_name = findViewById(R.id.main_textView_nombre);
        main_textView_bestScore = findViewById(R.id.main_textView_bestScore);
        main_imageView_icon = findViewById(R.id.main_imageView_icono);

        //---------- Get selected emoji ----------
        selectEmoji();
        //--------------------------

        //---------- DDBB'best score -------------
        dataBase();
        //------------------------------------------------------------------------------------------
    }

    //---------- Update and consult database ----------
    public void dataBase(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        @SuppressLint("Recycle") Cursor consulta = db.rawQuery("select * from puntaje where score = (select max(score) from puntaje)", null);

        if(consulta.moveToFirst()){ // Si hay algo en consulta (la db noe esta vacia)
            temp_name = consulta.getString(0);
            temp_score = consulta.getString(1);
            main_textView_bestScore.setText(String.format("%s: %s " + this.getString(R.string.estrellitas), temp_name, temp_score));
        }
        db.close();
    }
    //----------------------------------------------------------------------------------------------

    //---------- Select emoji ----------
    public void selectEmoji(){
        int img_id;
        int data;
        String intent_data;

        // Save selected emoji
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        data = preferences.getInt("sp_emoji", 0);

        data = getIntent().getIntExtra("dato", data);//Obtengo el emoji desde emojis.class
        SharedPreferences.Editor E_emoji = preferences.edit();
        E_emoji.putInt("sp_emoji", data);
        E_emoji.apply();

        if(data != 0){ //Confirm that the data has already arrived
            if(data == 1){
                img_id = getResources().getIdentifier("ic_caralentes", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 2){
                img_id = getResources().getIdentifier("ic_guinoloco", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 3){
                img_id = getResources().getIdentifier("ic_lose", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 4){
                img_id = getResources().getIdentifier("ic_ojitos", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 5){
                img_id = getResources().getIdentifier("ic_ojoscorazon", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 6){
                img_id = getResources().getIdentifier("ic_sonrisa", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 7){
                img_id = getResources().getIdentifier("ic_sorpresa", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 8){
                img_id = getResources().getIdentifier("ic_tierno", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 9){
                img_id = getResources().getIdentifier("ic_risacerrada", "drawable", getPackageName());
                main_imageView_icon.setImageResource(img_id);
            }else if(data == 10){
                intent_data = getIntent().getStringExtra("fondo_e");
                if(intent_data == null) intent_data = "";

                SharedPreferences preferences_1 = getSharedPreferences("datos_1", Context.MODE_PRIVATE);
                r_data_1 = preferences_1.getString("image", "");
                SharedPreferences.Editor E_image_1 = preferences_1.edit();

                if(Objects.requireNonNull(intent_data).length() != 0){
                    E_image_1.putString("image", intent_data);
                    E_image_1.apply();
                    main_imageView_icon.setImageURI(Uri.parse(intent_data));
                }else main_imageView_icon.setImageURI(Uri.parse(r_data_1));

            }else if(data == 11){
                intent_data = getIntent().getStringExtra("fondo_e");
                if(intent_data == null) intent_data = "";

                SharedPreferences preferences_2 = getSharedPreferences("datos_2", Context.MODE_PRIVATE);
                r_data_2 = preferences_2.getString("image", "");
                SharedPreferences.Editor E_image_2 = preferences_2.edit();

                if(Objects.requireNonNull(intent_data).length() != 0){
                    E_image_2.putString("image", intent_data);
                    E_image_2.apply();
                    main_imageView_icon.setImageURI(Uri.parse(intent_data));
                }else main_imageView_icon.setImageURI(Uri.parse(r_data_2));
            }else if(data == 12){
                intent_data = getIntent().getStringExtra("fondo_e");
                if(intent_data == null) intent_data = "";

                SharedPreferences preferences_3 = getSharedPreferences("datos_3", Context.MODE_PRIVATE);
                r_data_3 = preferences_3.getString("image", "");
                SharedPreferences.Editor E_image_3 = preferences_3.edit();

                if(Objects.requireNonNull(intent_data).length() != 0){
                    E_image_3.putString("image", intent_data);
                    E_image_3.apply();
                    main_imageView_icon.setImageURI(Uri.parse(intent_data));
                }else main_imageView_icon.setImageURI(Uri.parse(r_data_3));
            }
        }
        else {
            main_imageView_icon.setImageResource(R.drawable.ic_caralentes);
        }
    }
    //----------------------------------------------------------------------------------------------

    //---------- Overflow ----------
    public boolean onCreateOptionsMenu(Menu menu){
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
        }

        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------------------------------------

    //--------- Play Button ----------
    public void play(View view){
        String name = main_textView_name.getText().toString();

        if(!name.equals("")){
            Intent i = new Intent(this, Missions.class);
            i.putExtra("nombre", name);
            if(!temp_score.equals("")) {
                int p = Integer.parseInt(temp_score);
                i.putExtra("best_puntaje", p);
            }
            i.putExtra("fondo", background);
            startActivity(i);
            finish();
        }else{
            //Put cursor on TextEdit
            main_textView_name.requestFocus();
            //Open keyboard
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(main_textView_name, InputMethodManager.SHOW_IMPLICIT);
        }
    }
    //--------------------------------
    @Override
    public void onBackPressed(){
        //Do nothing on pressed back
    }
}