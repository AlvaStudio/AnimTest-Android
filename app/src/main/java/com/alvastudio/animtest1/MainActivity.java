package com.alvastudio.animtest1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        ImageView animImage = (ImageView)findViewById(R.id.splash_avia_anim);
        animImage.setBackgroundResource(R.drawable.fly_animate);

        AnimationDrawable animation = (AnimationDrawable)animImage.getBackground();
        animation.start();
        checkIfAnimationDone(animation); // проверяем закончилась ли анимация. дальше делаем что нужно - переход на другой экран или запуск другой анимации
    }

    private void checkIfAnimationDone(AnimationDrawable anim){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(a);
                } else{
                    Thread secondThread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);

                                //finish();
                            } catch (InterruptedException e) {

                            }
                        }
                    };
                    secondThread.start();
                }
            }
        }, timeBetweenChecks);
    };
}