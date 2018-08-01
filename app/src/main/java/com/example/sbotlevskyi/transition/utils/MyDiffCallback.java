package com.example.sbotlevskyi.transition.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.example.sbotlevskyi.transition.User;

import java.util.ArrayList;

public class MyDiffCallback extends DiffUtil.Callback {

    private static final String TAG = "SecondAdapter";
    private ArrayList<User> oldList;
    private ArrayList<User> newList;

    public MyDiffCallback(ArrayList<User> oldList, ArrayList<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
//        Log.i(TAG, "getOldListSize: " + oldList.size());
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
//        Log.i(TAG, "getNewListSize: " + newList.size());
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        User newUser = newList.get(newItemPosition);
        User oldUser = oldList.get(oldItemPosition);
        Log.i(TAG, "getChangePayload: " + newUser);
        if (!newUser.equals(oldUser)) {

            return newUser;
        }
        return null;
    }

}
