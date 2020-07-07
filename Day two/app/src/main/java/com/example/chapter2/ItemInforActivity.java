package com.example.chapter2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemInforActivity extends AppCompatActivity{

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlayout);
        Log.i(TAG, "ItemInforActivity onCreate");
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onStart() {
        Intent intent_item = getIntent();
        String str = intent_item.getStringExtra("data");
        TextView item = findViewById(R.id.tv_item);
        item.setText("你点击了第" + str + "条对话框");
        super.onStart();
        Log.i(TAG, "ItemInforActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "ItemInforActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "ItemInforActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ItemInforActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "ItemInforActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ItemInforActivity onDestroy");
    }
}
