package com.example.chapter2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter2.recycler.LinearItemDecoration;
import com.example.chapter2.recycler.MyAdapter;
import com.example.chapter2.recycler.TestData;
import com.example.chapter2.recycler.TestDataSet;

public class RecyclerViewActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(3000);
//        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    private int getViewCount(ViewGroup viewgroup)
    {
        if(viewgroup == null)
            return 0;
        int num = viewgroup.getChildCount();
        int count = 1;
        int i;
        for(i = 0; i < num; i++)
        {
            View child = viewgroup.getChildAt(i);
            if(child instanceof ViewGroup)
            {
                count += getViewCount((ViewGroup) child);
            }
            else
                {
                count++;
            }
        }
        return count;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout linearlayout = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_recyclerview,null);
        int viewnum = getViewCount(linearlayout);
        Log.i(TAG, "RecyclerViewActivity onResume");
        Log.i(TAG, "RecyclerViewActivity Count，数量为"+viewnum);
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        Intent intent_item = new Intent(this, ItemInforActivity.class);
        String str = String.valueOf(position);
        intent_item.putExtra("data",str);
        startActivity(intent_item);
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(RecyclerViewActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
    }
}