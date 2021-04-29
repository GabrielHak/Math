package com.example.math;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Levels extends AppCompatActivity {

    int s_2 = 15, s_3 = 45, s_4 = 90, s_5 = 150;
    int s_6 = 225, s_7 = 315, s_8 = 420, s_9 = 540, s_10 = 675;

    private ImageView levels_imageView_1, levels_imageView_1_2, levels_imageView_1_3;
    private ImageView levels_imageView_2, levels_imageView_2_2, levels_imageView_2_3;
    private ImageView levels_imageView_sign;
    private TextView levels_textView_score;
    private EditText niveles_textView_result;
    private Board levels_board;
    private Switch levels_switch_pencil;
    private Switch levels_switch_board;

    int num_rnd1, num_rnd2, result;
    int level = 0, score = 0;
    String name = "";

    SoundPool sp;
    int id_song_ok;
    int id_song_nook;
    int background = 0;
    String[] numeros = {"n_cero", "n_uno", "n_dos", "n_tres", "n_cuatro", "n_cinco", "n_seis",
            "n_siete", "n_ocho", "n_nueve"};

    String[] operation = {"n_mas", "n_menos", "n_por", "n_dividido"};

    @SuppressLint("SetTextI18n")
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
        setContentView(R.layout.activity_levels);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        id_song_ok = sp.load(this, R.raw.wonderful,1);
        id_song_nook = sp.load(this, R.raw.bad,1);


        levels_imageView_1 = findViewById(R.id.niveles_imageView_1);
        levels_imageView_1_2 = findViewById(R.id.niveles_imageView_1_2);
        levels_imageView_1_3 = findViewById(R.id.niveles_imageView_1_3);

        levels_imageView_2 = findViewById(R.id.niveles_imageView_2);
        levels_imageView_2_2 = findViewById(R.id.niveles_imageView_2_2);
        levels_imageView_2_3 = findViewById(R.id.niveles_imageView_2_3);

        levels_imageView_sign = findViewById(R.id.niveles_imageView_signo);

        niveles_textView_result = findViewById(R.id.niveles_textView_resultado);
        levels_textView_score = findViewById(R.id.niveles_textView_puntaje);
        levels_board = findViewById(R.id.niveles_pizarra_pizarron);
        levels_switch_pencil = findViewById(R.id.niveles_switch_lapiz);
        levels_switch_board = findViewById(R.id.niveles_switch_pizarron);

        levels_switch_pencil.setChecked(true);
        levels_switch_board.setChecked(true);

        level = getIntent().getIntExtra("nivel", level);
        name = getIntent().getStringExtra("nombre");
        score = getIntent().getIntExtra("puntaje", score);

        levels_textView_score.setText( ""+ this.getString(R.string.estrellas) + score + "x");

        operation();
    }

    //---------- Operation ---------
    public int numAl(int Min, int Max){
        return (int)(Math.random()*(Max-Min+1)+Min);
    }
    //----------------------------------------------------------------------------------------------

    //--------- Random ----------
    public void operation(){
        int op = 0;
        int id;
        int tmp1, tmp2, tmp3, tmp4;
        if(level == 1){
            op = 1;
            num_rnd1 = numAl(1, 9);
            num_rnd2 = numAl(1,9);
        }
        else if(level == 2){
            op = numAl(1,2);
            if(op == 1){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(1,9);
            }else if(op == 2){
                num_rnd1 = numAl(1, 9);
                num_rnd2 = numAl(1,(num_rnd1 -1));
            }
        }
        else if(level == 3){
            op = numAl(1,2);
            if(op == 1){
                tmp1 = numAl(1,8);
                tmp2 = numAl(0,9);
                tmp3 = numAl(1, (9-tmp1));
                tmp4 = numAl(0, (9-tmp2));
                num_rnd1 = (tmp1*10) + tmp2;
                num_rnd2 = (tmp3*10) + tmp4;
            }else if(op == 2){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(1, 9);
            }
        }
        else if(level == 4){
            op = numAl(1,2);
            if(op == 1){
                num_rnd1 = numAl(1, 99);
                num_rnd2 = numAl(1,99);
            }else if(op == 2){
                tmp1 = numAl(2,9);
                tmp2 = numAl(2,9);
                tmp3 = numAl(1, (tmp1-1));
                tmp4 = numAl(1, (tmp2-1));
                num_rnd1 = (tmp1*10) + tmp2;
                num_rnd2 = (tmp3*10) + tmp4;
            }
        }
        else if(level == 5){
            op = numAl(1,2);
            if(op == 1){
                num_rnd1 = numAl(1, 999);
                num_rnd2 = numAl(1,999);
            }else if(op == 2){
                num_rnd1 = numAl(1, 99);
                num_rnd2 = numAl(1,(num_rnd1 -1));
            }
        }
        else if(level == 6){
            op = numAl(2,3);
            if(op == 2){
                num_rnd1 = numAl(1, 999);
                num_rnd2 = numAl(1,(num_rnd1 -1));
            }else if(op == 3){
                num_rnd1 = numAl(1, 9);
                num_rnd2 = numAl(1,9);
            }
        }
        else if(level == 7){
            op = numAl(3,4);
            if(op == 3){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(1, 9);
            }else if(op == 4){
                num_rnd1 = numAl(1, 9);
                num_rnd2 = numAl(1, num_rnd1);
            }
        }
        else if(level == 8){
            op = numAl(3,4);
            if(op == 3){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(10, 99);
            }else if(op == 4){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(1,9);
            }
        }
        else if(level == 9){
            op = numAl(3,4);
            if(op == 3){
                num_rnd1 = numAl(100, 999);
                num_rnd2 = numAl(100, 999);
            }else if(op == 4){
                num_rnd1 = numAl(10, 99);
                num_rnd2 = numAl(10, num_rnd1);
            }
        }
        else if(level == 10){
            op = 4;

            num_rnd1 = numAl(100, 999);
            num_rnd2 = numAl(1,99);
        }

        //------ Put number images ----------
        setNums(num_rnd1, num_rnd2);
        //------------------------------------------------------------------------------------------
        //---------- Put sig images ----------
        id = getResources().getIdentifier(operation[op-1], "drawable",getPackageName());
        levels_imageView_sign.setImageResource(id);
        //------------------------------------------------------------------------------------------
        //---------- Get result ----------
        if(op == 1){
            result = num_rnd1 + num_rnd2;
        }else if(op == 2){
            result = num_rnd1 - num_rnd2;
        }else if(op == 3){
            result = num_rnd1 * num_rnd2;
        }else if(op == 4){
            result = num_rnd1 / num_rnd2;
        }
        //------------------------------------------------------------------------------------------
    }
    //------ Set number images ----------
    private void setNums(int num_al1, int num_al2) {
        int id;
        int firstDigit, secondDigit, thirdDigit;

        if(num_al1 < 10){
            levels_imageView_1.setVisibility(View.VISIBLE);
            levels_imageView_1_2.setVisibility(View.GONE);
            levels_imageView_1_3.setVisibility(View.GONE);

            id = getResources().getIdentifier(numeros[num_al1], "drawable", getPackageName());
            levels_imageView_1.setImageResource(id);
        }else if(num_al1 < 100){
            levels_imageView_1.setVisibility(View.VISIBLE);
            levels_imageView_1_2.setVisibility(View.VISIBLE);
            levels_imageView_1_3.setVisibility(View.GONE);

            firstDigit = num_al1 % 10;
            secondDigit = num_al1 / 10;

            id = getResources().getIdentifier(numeros[firstDigit], "drawable", getPackageName());
            levels_imageView_1.setImageResource(id);
            id = getResources().getIdentifier(numeros[secondDigit], "drawable", getPackageName());
            levels_imageView_1_2.setImageResource(id);
        }else if(num_al1 < 1000){
            levels_imageView_1.setVisibility(View.VISIBLE);
            levels_imageView_1_2.setVisibility(View.VISIBLE);
            levels_imageView_1_3.setVisibility(View.VISIBLE);

            firstDigit = num_al1 % 10;
            secondDigit = (num_al1 % 100) / 10;
            thirdDigit = num_al1 / 100;

            id = getResources().getIdentifier(numeros[firstDigit], "drawable", getPackageName());
            levels_imageView_1.setImageResource(id);
            id = getResources().getIdentifier(numeros[secondDigit], "drawable", getPackageName());
            levels_imageView_1_2.setImageResource(id);
            id = getResources().getIdentifier(numeros[thirdDigit], "drawable", getPackageName());
            levels_imageView_1_3.setImageResource(id);
        }


        if(num_al2 < 10){
            levels_imageView_2.setVisibility(View.VISIBLE);
            levels_imageView_2_2.setVisibility(View.GONE);
            levels_imageView_2_3.setVisibility(View.GONE);

            id = getResources().getIdentifier(numeros[num_al2], "drawable", getPackageName());
            levels_imageView_2.setImageResource(id);
        }else if(num_al2 < 100){
            levels_imageView_2.setVisibility(View.VISIBLE);
            levels_imageView_2_2.setVisibility(View.VISIBLE);
            levels_imageView_2_3.setVisibility(View.GONE);

            firstDigit = num_al2 % 10;
            secondDigit = num_al2 / 10;

            id = getResources().getIdentifier(numeros[firstDigit], "drawable", getPackageName());
            levels_imageView_2.setImageResource(id);
            id = getResources().getIdentifier(numeros[secondDigit], "drawable", getPackageName());
            levels_imageView_2_2.setImageResource(id);
        }else if(num_al2 < 1000){
            levels_imageView_2.setVisibility(View.VISIBLE);
            levels_imageView_2_2.setVisibility(View.VISIBLE);
            levels_imageView_2_3.setVisibility(View.VISIBLE);

            firstDigit = num_al2 % 10;
            secondDigit = (num_al2 % 100) / 10;
            thirdDigit = num_al2 / 100;

            id = getResources().getIdentifier(numeros[firstDigit], "drawable", getPackageName());
            levels_imageView_2.setImageResource(id);
            id = getResources().getIdentifier(numeros[secondDigit], "drawable", getPackageName());
            levels_imageView_2_2.setImageResource(id);
            id = getResources().getIdentifier(numeros[thirdDigit], "drawable", getPackageName());
            levels_imageView_2_3.setImageResource(id);
        }
    }
    //----------------------------------------------------------------------------------------------

    //---------- Consult and modify ddbb according to the score ----------
    public void IsBestScore()
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        @SuppressLint("Recycle") Cursor query = db.rawQuery("select * from puntaje where score = (select max(score) from puntaje)", null);
        if(query.moveToFirst()){
            String s_score = query.getString(1);
            int i_score = Integer.parseInt(s_score);

            if(score > i_score){
                ContentValues modification = new ContentValues();
                modification.put("nombre", name);
                modification.put("score", score);
                db.update("puntaje", modification, "score=" + i_score, null);
            }
        }else{
            ContentValues insert = new ContentValues();
            insert.put("nombre", name);
            insert.put("score", score);
            db.insert("puntaje", null, insert);
        }
        db.close();
    }
    //----------------------------------------------------------------------------------------------

    private void nextLevel(){
        int n = 0;
        if(score >= s_10) n = 10;
        else if(score >= s_9) n = 9;
        else if(score >= s_8) n = 8;
        else if(score >= s_7) n = 7;
        else if(score >= s_6) n = 6;
        else if(score >= s_5) n = 5;
        else if(score >= s_4) n = 4;
        else if(score >= s_3) n = 3;
        else if(score >= s_2) n = 2;

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("Nivel " + n + " desbloqueado!!!!")
                .setCancelable(true);
                /*
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        l = true;
                        nivel++;
                        operacion();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        l = false;
                        operacion();
                    }
                });*/
        AlertDialog tittle = alerta.create();
        tittle.setTitle("" + score +  this.getString(R.string.estrellitas) +"!!!!");
        tittle.show();
    }

    //--------- Verificar ---------
    @SuppressLint("SetTextI18n")
    public void Verificar(View view){

        String save = niveles_textView_result.getText().toString();

        if(save.equals("")) {
            niveles_textView_result.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(niveles_textView_result, InputMethodManager.SHOW_IMPLICIT);
        }else{
            int valor = Integer.parseInt(save);
            int puntje_anterior = score;
            if(result == valor){
                sp.play(id_song_ok,1,1,0,0,0);
                switch (level){
                    case 1:
                        score++;
                        break;
                    case 2:
                        score +=2;
                        break;
                    case 3:
                        score +=3;
                        break;
                    case 4:
                        score +=4;
                        break;
                    case 5:
                        score +=5;
                        break;
                    case 6:
                        score +=6;
                        break;
                    case 7:
                        score +=7;
                        break;
                    case 8:
                        score +=8;
                        break;
                    case 9:
                        score +=9;
                        break;
                    case 10:
                        score +=10;
                        break;
                }
                if(puntje_anterior < s_2 && score >= s_2) nextLevel();
                if(puntje_anterior < s_3 && score >= s_3) nextLevel();
                if(puntje_anterior < s_4 && score >= s_4) nextLevel();
                if(puntje_anterior < s_5 && score >= s_5) nextLevel();
                if(puntje_anterior < s_6 && score >= s_6) nextLevel();
                if(puntje_anterior < s_7 && score >= s_7) nextLevel();
                if(puntje_anterior < s_8 && score >= s_8) nextLevel();
                if(puntje_anterior < s_9 && score >= s_9) nextLevel();
                if(puntje_anterior < s_10 && score >= s_10) nextLevel();
                MakeToast(true);
                levels_textView_score.setText("" + this.getString(R.string.estrellas) + score + "x");
                operation();
            }else{
                sp.play(id_song_nook,1,1,0,0,0);
                MakeToast(false);
            }
            IsBestScore();
            niveles_textView_result.setText(null);
        }

    }
    //----------------------------------------------------------------------------------------------

    //---------- Board ----------
    @SuppressLint("SetTextI18n")
    public void board(View view){
        if(levels_switch_board.isChecked()){
            levels_switch_pencil.setText(this.getString(R.string.lapiz));
            levels_switch_pencil.setChecked(true);
            levels_board.setVisibility(View.VISIBLE);
            levels_switch_pencil.setVisibility(View.VISIBLE);
            levels_board.borrar(false);
            levels_board.setSize(3);
            Toast.makeText(this, "" + this.getString(R.string.lapiz), Toast.LENGTH_SHORT).show();
        }else{
            levels_board.setVisibility(View.GONE);
            levels_switch_pencil.setChecked(false);
            levels_switch_pencil.setVisibility(View.GONE);
        }
    }
    //----------------------------------------------------------------------------------------------

    public void setColor(View view){
        levels_board.setColor(Color.WHITE);
    }

    @SuppressLint("SetTextI18n")
    public void borrar(View view){
        if(levels_switch_pencil.isChecked()){
            Toast.makeText(this, "" + this.getString(R.string.lapiz), Toast.LENGTH_SHORT).show();
            levels_switch_pencil.setText(this.getString(R.string.lapiz));
            levels_board.setSize(3);
            levels_board.borrar(false);
        }else{
            Toast.makeText(this, "" + this.getString(R.string.goma), Toast.LENGTH_SHORT).show();
            levels_switch_pencil.setText(this.getString(R.string.goma));
            levels_board.setSize(30);
            levels_board.borrar(true);
        }


    }

    //------ Make Toast --------
    public void MakeToast(boolean b){
        Toast toast = Toast.makeText(Levels.this, "", Toast.LENGTH_LONG);
        //toast.getView().setPadding(20, 20, 20, 20);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
        if(b){
            toast.setText((this.getString(R.string.bien)));
            toast.getView().setBackgroundResource(R.color.ok);
        }
        else{
            toast.setText(this.getString(R.string.mal));
            toast.getView().setBackgroundResource(R.color.nook);
        }
        toast.show();
    }
    //----------------------------------------------------------------------------------------------

    //---------- Overflow ----------
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home, menu);
        getMenuInflater().inflate(R.menu.config, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_config){
            Intent config = new Intent(this, Configuration.class);
            config.putExtra("fondo", background);
            startActivity(config);
            finish();
        }else if(id == R.id.menu_home){
            Intent inicio = new Intent(this, MainActivity.class);
            inicio.putExtra("fondo", background);
            startActivity(inicio);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Selection.class);
        i.putExtra("nombre", name);
        i.putExtra("puntaje", score);
        i.putExtra("fondo", background);
        startActivity(i);
        finish();
    }
}