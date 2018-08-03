package com.example.sbotlevskyi.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.sbotlevskyi.transition.activity.AnimationsExampleActivity;
import com.example.sbotlevskyi.transition.activity.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTransition";
    private Button buttonOpenAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout transitionLayout = findViewById(R.id.transition_name1);
        final View smallImage = findViewById(R.id.image_small);
        setupWindowAnimations();
        buttonOpenAnimation = findViewById(R.id.button_animation_activity);
        buttonOpenAnimation.setOnClickListener(v -> {
           Intent intent =new Intent(MainActivity.this, AnimationsExampleActivity.class);
           startActivity(intent);

        });
        smallImage.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);

            View sharedView = transitionLayout;
            String transitionName = getString(R.string.app_name);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
            startActivity(i, transitionActivityOptions.toBundle());
        });

    }


    private void setupWindowAnimations() {
        Log.i(TAG, "setupWindowAnimations: ");
//        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
//        getWindow().setExitTransition(slide);
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setExitTransition(fade);
    }
}
