package com.example.sbotlevskyi.transition.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.sbotlevskyi.transition.R;

public class AnimationsExampleActivity extends AppCompatActivity {
    private static final String TAG = "AnimationsExample";
    private Animation animation;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_example);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        animation = AnimationUtils.loadAnimation(this, R.anim.translation_x_button);
        button1.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translation_y_button);
        button2.startAnimation(animation);



        button3.setOnClickListener(view -> {
            final int[] i = {1};
            final int[] value = {1};
            button3.animate()
                    .setDuration(5000)
                    .setUpdateListener(valueAnimator -> {
                                i[0] += value[0];
                                button3.setTranslationY(i[0]);
                                if (Math.abs(i[0])>20){
                                    value[0] *=-1;
                                }
                                Log.i(TAG, "onAnimationUpdate: ");
                            }
                    ).start();
        });

    }
}
