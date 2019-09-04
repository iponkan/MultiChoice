package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.activity.MainActivity;
import com.example.iponkan.R;

public class FragmentResultReport extends Fragment {
    public static final String TAG = "ResultReport";

    public FragmentResultReport() {

    }

    public static Fragment newInstance() {
        return new FragmentResultReport();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_report,
                container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        GridView gv = view.findViewById(R.id.report_gv);
        TextView tv_report_total_question = view.findViewById(R.id.tv_report_total_question);
        RelativeLayout rl_result_panel = view.findViewById(R.id.rl_result_panel);
        //设置scrollview 自动置顶
        rl_result_panel.setFocusable(true);
        rl_result_panel.setFocusableInTouchMode(true);
        rl_result_panel.requestFocus();

        tv_report_total_question.setText("道/" + count + "道");
        MyAdapter adapter = new MyAdapter(getContext());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO跳转到相应的viewpager 页面
                Intent intent = new Intent("com.leyikao.jumptopage");
                intent.putExtra("index", position);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    LocalBroadcastManager mLocalBroadcastManager;
    int count = MainActivity.questionlist.size();
    int[] mIds = new int[count];

    private void initData() {
        for (int i = 0; i < count; i++) {
            mIds[i] = i + 1;
        }
    }

    private class MyAdapter extends BaseAdapter {
        private Context mContext;

        public MyAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mIds.length;
        }

        @Override
        public Object getItem(int position) {
            return mIds[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new GridView.LayoutParams(70, 70));
            tv.setPadding(8, 8, 8, 8);

            tv.setText(mIds[position] + "");
            tv.setBackgroundResource(R.drawable.option_btn_single_normal);
            return tv;
        }
    }
}
