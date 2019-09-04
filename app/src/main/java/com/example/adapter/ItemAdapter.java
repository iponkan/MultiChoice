package com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.activity.MainActivity;
import com.example.fragment.QuestionItemFragment;
import com.example.fragment.ScantronItemFragment;

public class ItemAdapter extends FragmentStatePagerAdapter {

    public ItemAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        if (arg0 == MainActivity.questionlist.size()) {
            return new ScantronItemFragment();
        }
        return QuestionItemFragment.newInstance(arg0);
    }

    @Override
    public int getCount() {

        return MainActivity.questionlist.size() + 1;
    }
}
