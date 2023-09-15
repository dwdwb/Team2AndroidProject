package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    Animation anim_splash_imageview;
    Animation anim_splash_in_down;
    Animation anim_splash_logo;
    Animation anim_splash_out_top;
    ImageView logoImage;
    ImageView splashImage;
    ConstraintLayout constraintLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int[] imgArray = {
                R.drawable.fruit_avocado, R.drawable.fruit_banana,
                R.drawable.fruit_cherry, R.drawable.fruit_mango,
                R.drawable.fruit_orange, R.drawable.fruit_korean_melon,
                R.drawable.fruit_ugly, R.drawable.fruit_watermelon
        };


        // 랜덤으로 이미지 선택
        int randomImageIndex = new Random().nextInt(imgArray.length);
        int selectedImageResource = imgArray[randomImageIndex];



        constraintLayout=findViewById(R.id.constraint_layout);
        splashImage = findViewById(R.id.splash_image); // ImageView 추가

        splashImage.setImageResource(selectedImageResource);
        logoImage=findViewById(R.id.splash_logo);

        anim_splash_imageview = AnimationUtils.loadAnimation(this,R.anim.anim_splash_imageview);
        anim_splash_logo = AnimationUtils.loadAnimation(this,R.anim.anim_splash_logo);

        splashImage.startAnimation(anim_splash_imageview);
        logoImage.startAnimation(anim_splash_logo);

        anim_splash_imageview.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_splash_out_top, R.anim.anim_splash_in_down);

                // 현재 액티비티를 종료하려면 다음 코드를 추가
                finish();

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });
    }
}