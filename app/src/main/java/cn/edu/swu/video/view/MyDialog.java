package cn.edu.swu.video.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import cn.edu.swu.video.R;

public class MyDialog extends Dialog implements View.OnClickListener {
    Context context;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    setMyDialogListener listener;

    public MyDialog(@NonNull Context context,setMyDialogListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public interface setMyDialogListener{
        public void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_play_square_longpress);
        btn1 = findViewById(R.id.d_p_s_btn1);
        btn2 = findViewById(R.id.d_p_s_btn2);
        btn3 = findViewById(R.id.d_p_s_btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }



}
