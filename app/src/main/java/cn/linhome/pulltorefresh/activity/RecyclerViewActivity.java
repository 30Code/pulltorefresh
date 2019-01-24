package cn.linhome.pulltorefresh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.linhome.lib.adapter.FSimpleRecyclerAdapter;
import cn.linhome.lib.adapter.viewholder.FRecyclerViewHolder;
import cn.linhome.lib.pulltorefresh.FPullToRefreshView;
import cn.linhome.lib.pulltorefresh.PullToRefreshView;
import cn.linhome.library.activity.SDBaseActivity;
import cn.linhome.library.view.SDRecyclerView;
import cn.linhome.pulltorefresh.CustomPullToRefreshLoadingView;
import cn.linhome.pulltorefresh.R;
import cn.linhome.pulltorefresh.model.DataModel;

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

        mRecyclerView.addOnScrollCallBack(new SDRecyclerView.OnScrollCallBack()
        {
            @Override
            public void onLoadMore()
            {
                view_pull.startRefreshingFromFooter();
                //底部加载回调
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mAdapter.getDataHolder().appendData(DataModel.getListModel(3));
                        view_pull.stopRefreshing();
                    }
                }, 1000);
            }
        });

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
                        mAdapter.getDataHolder().appendData(DataModel.getListModel(3));
                        view.stopRefreshing();
                    }
                }, 1000);
            }
        });

        mAdapter.getDataHolder().setData(DataModel.getListModel(20));
        view_pull.startRefreshingFromHeader();
    }

    private FSimpleRecyclerAdapter<DataModel> mAdapter = new FSimpleRecyclerAdapter<DataModel>(this)
    {

        @Override
        public void onBindData(FRecyclerViewHolder<DataModel> holder, int position, DataModel model)
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
