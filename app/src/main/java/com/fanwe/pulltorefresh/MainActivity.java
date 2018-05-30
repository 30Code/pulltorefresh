package com.fanwe.pulltorefresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fanwe.library.activity.SDBaseActivity;
import com.fanwe.pulltorefresh.activity.ButtonActivity;
import com.fanwe.pulltorefresh.activity.ListViewActivity;
import com.fanwe.pulltorefresh.activity.RecyclerViewActivity;
import com.fanwe.pulltorefresh.activity.ScrollViewActivity;

public class MainActivity extends SDBaseActivity
{

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ButtonActivity.class));
            }
        });

        findViewById(R.id.btn_recyclerview).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });

        findViewById(R.id.btn_listview).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
            }
        });

        findViewById(R.id.btn_scrollview).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
            }
        });
    }
}
