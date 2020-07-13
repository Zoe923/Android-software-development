package com.bytedance.todolist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.todolist.R;

import java.util.Calendar;

public class TodoListAddActivity extends AppCompatActivity {
    private Button confirm;
    private EditText context;
    private RadioGroup priority;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list_add_activity_layout);
        setTitle("Take a note");

        confirm = findViewById(R.id.btn_confirm);
        context = findViewById(R.id.et_context);
        priority = findViewById(R.id.radio_group);

        RadioButton low_btn = findViewById(R.id.btn_low);
        low_btn.setChecked(true);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                str = context.getText().toString();
                if(str.isEmpty())
                {
                    context.setError("新建项的内容不能为空！");
                    return;
                }

                int priority = getPriority();

                Intent intent = new Intent();
                intent.putExtra("content", str);
                intent.putExtra("done", false);
                intent.putExtra("priority", priority);
                setResult(1, intent);
                finish();
            }
        });
    }
    private int getPriority(){
        switch (priority.getCheckedRadioButtonId()) {
            case R.id.btn_high:
                return 3;
            case R.id.btn_medium:
                return 2;
            default:
                return 1;
        }
    }

}
