package com.example.math;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView load_textView_from = findViewById(R.id.load_textView_from);
        TextView load_textView_gabs = findViewById(R.id.load_textView_gabs);
        ImageView load_imageView_logo = findViewById(R.id.load_imageView_logo);

        Animation logo = AnimationUtils.loadAnimation(this, R.anim.bajar);
        Animation letras = AnimationUtils.loadAnimation(this, R.anim.subir);

        load_imageView_logo.startAnimation(logo);
        load_textView_from.startAnimation(letras);
        load_textView_gabs.startAnimation(letras);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(LoadingScreen.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 3000);
    }
}