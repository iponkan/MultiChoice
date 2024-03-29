package com.example.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.iponkan.R;

public class ConfirmDialog extends Dialog {

    private Context context;
    private String content;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {

        void doProceed();
    }

    public ConfirmDialog(Context context, String content) {
        super(context, R.style.CustomDialog);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.timer_pause_dialog, null);
        setContentView(view);

        TextView tv_timer_dialog_content = (TextView) view.findViewById(R.id.tv_timer_dialog_content);
        TextView btn_timer_dialog_proceed = (TextView) view.findViewById(R.id.btn_timer_dialog_proceed);

        tv_timer_dialog_content.setText(content);

        btn_timer_dialog_proceed.setOnClickListener(new clickListener());
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_timer_dialog_proceed:
                    clickListenerInterface.doProceed();
                    break;
                default:
                    break;
            }
        }
    }

    ;
}