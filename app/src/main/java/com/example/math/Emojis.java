package com.example.math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Emojis extends AppCompatActivity {

    private ImageView btn_camera_1;
    private ImageView btn_camera_2;
    private ImageView btn_camera_3;
    int key = 0;
    int emoji = 0;
    int background = 0;
    String data_1 = "";
    String data_2 = "";
    String data_3 = "";
    String s = "";
    String data_intent = "";

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
        setContentView(R.layout.activity_emojis);

        btn_camera_1 = findViewById(R.id.emojis_button_camara_1);
        btn_camera_2 = findViewById(R.id.emojis_button_camara_2);
        btn_camera_3 = findViewById(R.id.emojis_button_camara_3);

        key = getIntent().getIntExtra("key", 0);

        registerForContextMenu(btn_camera_1);
        registerForContextMenu(btn_camera_2);
        registerForContextMenu(btn_camera_3);

        putImages();
    }

    public void shortClick(View view){
        Toast.makeText(this, "" + this.getString(R.string.pulsado), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        s = String.valueOf(v.getContentDescription());
        switch (s) {
            case "btn_1":
                emoji = 10;
                break;
            case "btn_2":
                emoji = 11;
                break;
            case "btn_3":
                emoji = 12;
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(/*@NonNull*/ MenuItem item) {

        Intent i;
        switch (item.getItemId()){
            case R.id.it_select :
                i = new Intent(this, MainActivity.class);
                i.putExtra("dato", emoji);
                switch (s) {
                    case "btn_1":
                        //emoji = 10;
                        i.putExtra("fondo_e", data_1);
                        break;
                    case "btn_2":
                        //emoji = 11;
                        i.putExtra("fondo_e", data_2);
                        break;
                    case "btn_3":
                        //emoji = 12;
                        i.putExtra("fondo_e", data_3);
                        break;
                }
                startActivity(i);
                finish();
                return true;
            case R.id.it_take :
                i = new Intent(this, CameraEmoji.class);
                i.putExtra("key", emoji);
                switch (s) {
                    case "btn_1":
                        i.putExtra("fondo_e", data_1);
                        break;
                    case "btn_2":
                        i.putExtra("fondo_e", data_2);
                        break;
                    case "btn_3":
                        i.putExtra("fondo_e", data_3);
                        break;
                }
                startActivity(i);
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void putImages() {
        data_intent = getIntent().getStringExtra("imagen");

        SharedPreferences preferences_1 = getSharedPreferences("datos_1", Context.MODE_PRIVATE);
        data_1 = preferences_1.getString("image1", "");
        SharedPreferences.Editor E_image_1 = preferences_1.edit();

        SharedPreferences preferences_2 = getSharedPreferences("datos_2", Context.MODE_PRIVATE);
        data_2 = preferences_2.getString("image2", "");
        SharedPreferences.Editor E_image_2 = preferences_2.edit();

        SharedPreferences preferences_3 = getSharedPreferences("datos_3", Context.MODE_PRIVATE);
        data_3 = preferences_3.getString("image3", "");
        SharedPreferences.Editor E_image_3 = preferences_3.edit();

        if(key == 10){
            if(data_intent != null){
                E_image_1.putString("image1", data_intent);
                E_image_1.apply();
                data_1 = preferences_1.getString("image1", "");
                btn_camera_1.setImageURI(Uri.parse(data_1));
            }
        }else if(key == 11){
            if(!data_intent.equals("")){
                E_image_2.putString("image2", data_intent);
                E_image_2.apply();
                data_2 = preferences_2.getString("image2", "");
                btn_camera_2.setImageURI(Uri.parse(data_2));
            }
        }else if(key == 12){
            if(!data_intent.equals("")){
                E_image_3.putString("image3", data_intent);
                E_image_3.apply();
                data_3 = preferences_3.getString("image3", "");
                btn_camera_3.setImageURI(Uri.parse(data_3));
            }
        }
        if(key != 10 && !data_1.equals("")) {
            btn_camera_1.setImageURI(Uri.parse(data_1));
        }
        if(key != 11 && !data_2.equals("")) {
            btn_camera_2.setImageURI(Uri.parse(data_2));
        }
        if(key != 12 && !data_3.equals("")) {
            btn_camera_3.setImageURI(Uri.parse(data_3));
        }
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
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void button(View view){
        Intent i = new Intent(this, MainActivity.class);
        int id = view.getId();

        if(id == R.id.emojis_button_lentes){
            emoji = 1;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_guino){
            emoji = 2;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_agrandado){
            emoji = 3;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_ojitos){
            emoji = 4;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_corazon){
            emoji = 5;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_sonrisa){
            emoji = 6;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_sorpresa){
            emoji = 7;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_tierno){
            emoji = 8;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }else if(id == R.id.emojis_button_risa){
            emoji = 9;
            i.putExtra("dato", emoji);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, AppearanceConfiguration.class);
        startActivity(i);
        finish();
    }
}