package com.example.sbotlevskyi.transition;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.sbotlevskyi.transition.adapter.SecondAdapter;
import com.example.sbotlevskyi.transition.utils.MyDiffCallback;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondAdapter";
    private SecondAdapter secondAdapter;
    private RecyclerView recyclerView;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupWindowAnimations();
        secondAdapter = new SecondAdapter(initData());
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(secondAdapter);
        rootView = findViewById(android.R.id.content);

    }

    public void addToList(ArrayList<User> list) {
        ArrayList<User> newList = new ArrayList<>(secondAdapter.getUsers());
        newList.addAll(list);
        updateList(newList);

    }

    public void changeList(int index, User user) {
        ArrayList<User> newList = new ArrayList<>(secondAdapter.getUsers());
        newList.set(index, user);
        updateList(newList);
    }

    public void moveTo(int index, int toPosition) {
        User user1 = secondAdapter.getUsers().get(index);
        User user2 = secondAdapter.getUsers().get(toPosition);
        secondAdapter.getUsers().set(index, user2);
        secondAdapter.getUsers().set(toPosition, user1);
    }

    private void updateList(final ArrayList<User> newList) {
        MyDiffCallback callback = new MyDiffCallback(secondAdapter.getUsers(), newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
            @Override
            public void onInserted(int position, int count) {
                Log.i(TAG, "onInserted: " + position + "  " + count);

                for (int i = position; i < position + count; i++) {
                    secondAdapter.addToList(newList.get(i));

//                    startIntroAnimation();

//                    int resId = R.anim.layout_animation_slide_right;
//                    LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getBaseContext(), resId);
//                    recyclerView.setLayoutAnimation(animation);
//
//
//                    secondAdapter.addToList(newList.get(i));
//                    runLayoutInsertAnimation(recyclerView, position,count);
//                    secondAdapter. notifyItemInserted(i);
                }
//                runLayoutInsertAnimation(recyclerView, position,count);
            }

            @Override
            public void onRemoved(int position, int count) {

            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {

            }

            @Override
            public void onChanged(int position, int count, Object payload) {
                Log.i(TAG, "onChanged: " + position + "  " + count + "  " + payload);
                for (int i = position; i < position + count; i++) {
                    secondAdapter.getUsers().set(i, newList.get(i));
                    secondAdapter.notifyItemChanged(i);
                }
            }
        });

//        secondAdapter.setUsers(newList);
//        diffResult.dispatchUpdatesTo(secondAdapter);

    }

    private void startIntroAnimation() {
        recyclerView.setTranslationY(rootView.getHeight());
        recyclerView.setAlpha(0f);
        recyclerView.animate()
                .translationY(0)
                .setDuration(400)
                .alpha(1f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    private void runLayoutInsertAnimation(final RecyclerView recyclerView, int index, int count) {
        Log.i(TAG, "runLayoutInsertAnimation: " + index);
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyItemInserted(index);
        recyclerView.scheduleLayoutAnimation();
    }

    private void setupWindowAnimations() {
//        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
//        getWindow().setExitTransition(slide);
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("add");
        menu.add("change");
        menu.add("move");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "add":
                addToList(new ArrayList<User>() {{
                    add(new User(4, "Helen", 33));
                    add(new User(5, "Ann", 19));
                }});
                break;
            case "change":
                changeList(0, new User(1, "Andrew", 45));
                break;
            case "move":
                moveTo(0, 3);
                break;
        }
        secondAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<User> initData() {
        return new ArrayList<User>() {{
            add(new User(1, "Sergey", 23));
            add(new User(2, "Ivan", 26));
            add(new User(3, "Max", 21));
        }};
    }
}
