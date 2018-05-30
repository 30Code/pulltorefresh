package com.fanwe.pulltorefresh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanwe.lib.pulltorefresh.FPullToRefreshView;
import com.fanwe.lib.pulltorefresh.PullToRefreshView;
import com.fanwe.library.activity.SDBaseActivity;
import com.fanwe.library.adapter.SDSimpleRecyclerAdapter;
import com.fanwe.library.adapter.viewholder.SDRecyclerViewHolder;
import com.fanwe.library.view.SDRecyclerView;
import com.fanwe.pulltorefresh.CustomPullToRefreshLoadingView;
import com.fanwe.pulltorefresh.R;
import com.fanwe.pulltorefresh.model.DataModel;

public class RecyclerViewActivity extends SDBaseActivity
{
    private FPullToRefreshView view_pull;
    private SDRecyclerView mRecyclerView;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_recyclerview);
        view_pull = findViewById(R.id.view_pull);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);

        view_pull.setDebug(true);
        view_pull.setHeaderView(new CustomPullToRefreshLoadingView(this)); //自定义HeaderView
        view_pull.setOnRefreshCallback(new PullToRefreshView.OnRefreshCallback()
        {
            @Override
            public void onRefreshingFromHeader(final PullToRefreshView view)
            {
                //头部刷新回调
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mAdapter.updateData(DataModel.getListModel(3));
                        view.stopRefreshing();
                    }
                }, 1000);
            }

            @Override
            public void onRefreshingFromFooter(final PullToRefreshView view)
            {
                //底部加载回调
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mAdapter.appendData(DataModel.getListModel(3));
                        view.stopRefreshing();
                    }
                }, 1000);
            }
        });
        view_pull.startRefreshingFromHeader();
    }

    private SDSimpleRecyclerAdapter<DataModel> mAdapter = new SDSimpleRecyclerAdapter<DataModel>(this)
    {
        @Override
        public void onBindData(SDRecyclerViewHolder<DataModel> holder, int position, DataModel model)
        {
            TextView tv_content = (TextView) holder.get(R.id.tv_content);
            tv_content.setText(String.valueOf(model.getName()));
        }

        @Override
        public int getLayoutId(ViewGroup parent, int viewType)
        {
            return R.layout.item_textview;
        }
    };
}
