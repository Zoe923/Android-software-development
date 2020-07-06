package com.example.myapplicationone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = findViewById(R.id.btn_login);
        final TextView tv = findViewById(R.id.tv_title);
        final CheckBox computer = findViewById(R.id.computer);
        final CheckBox music = findViewById(R.id.music);
        final CheckBox art = findViewById(R.id.art);
        final CheckBox sleep = findViewById(R.id.sleep);
        final Button btn_sub = findViewById(R.id.btn_sub);
        computer.setOnCheckedChangeListener(checkBox_listener);
        music.setOnCheckedChangeListener(checkBox_listener);
        art.setOnCheckedChangeListener(checkBox_listener);
        sleep.setOnCheckedChangeListener(checkBox_listener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("I want to join bytedance!!!");
                Log.i("MainActivity", "onClick: I want to join bytedance!!!");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "";
                if(computer.isChecked())
                    str += computer.getText().toString()+"";
                if(music.isChecked())
                    str += music.getText().toString()+"";
                if(art.isChecked())
                    str += art.getText().toString()+"";
                if(sleep.isChecked())
                    str += sleep.getText().toString()+"";
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private CompoundButton.OnCheckedChangeListener checkBox_listener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){//判断复选按钮是否被选中
                Log.i("复选按钮","选中了["+compoundButton.getText().toString()+"]");
            }
        }
    };
}
