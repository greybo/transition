package com.example.sbotlevskyi.transition.activity;

import android.animation.ValueAnimator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_example);

        button1 = findViewById(R.id.button1);
        animation = AnimationUtils.loadAnimation(this, R.anim.transition_button1);
        button1.startAnimation(animation);

//        button1.animate()
//                .setDuration(1000)
//                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        Log.i(TAG, "onAnimationUpdate: ");
//                    }
//                }).start();
    }
}
