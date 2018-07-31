package com.example.sbotlevskyi.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        final View androidRobotView = findViewById(R.id.img_container);

// get the common element for the transition in this activity
        final View imgContainerView = findViewById(R.id.image_small);

// define a click listener
        imgContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> pair1 = Pair.create(androidRobotView, androidRobotView.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(MainActivity.this, pair1);
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
                // start the new activity

            }
        });
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
