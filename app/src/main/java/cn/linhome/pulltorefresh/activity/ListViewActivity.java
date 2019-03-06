package cn.linhome.pulltorefresh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import cn.linhome.lib.adapter.FSimpleAdapter;
import cn.linhome.lib.pulltorefresh.FPullToRefreshView;
import cn.linhome.lib.pulltorefresh.PullToRefreshView;
import cn.linhome.library.activity.SDBaseActivity;
import cn.linhome.pulltorefresh.CustomPullToRefreshLoadingView;
import cn.linhome.pulltorefresh.R;
import cn.linhome.pulltorefresh.model.DataModel;

import static cn.linhome.lib.utils.FViewHolder.get;

public class ListViewActivity extends SDBaseActivity
{
    private FPullToRefreshView view_pull;
    private ListView mListView;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_listview);
        view_pull = findViewById(R.id.view_pull);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if (visibleItemCount + firstVisibleItem == totalItemCount)
                {
                    View lastVisibleItemView = mListView.getChildAt(totalItemCount - firstVisibleItem - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == view.getHeight())
                    {
                        if (view_pull.isRefreshing())
                        {
                            return;
                        }

                        view_pull.startRefreshingFromFooter();
                    }
                }
            }
        });

        mAdapter.getDataHolder().setData(DataModel.getListModel(20));

        view_pull.setDebug(true);
        view_pull.setFooterView(new CustomPullToRefreshLoadingView(this)); //自定义FooterView
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
                        mAdapter.getDataHolder().setData(DataModel.getListModel(20));
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
        view_pull.startRefreshingFromHeader();
    }

    private FSimpleAdapter<DataModel> mAdapter = new FSimpleAdapter<DataModel>(this)
    {
        @Override
        public int getLayoutId(int position, View convertView, ViewGroup parent)
        {
            return R.layout.item_textview;
        }

        @Override
        public void onBindData(int position, View convertView, ViewGroup parent, DataModel model)
        {
            TextView tv_content = get(R.id.tv_content, convertView);
            tv_content.setText(model.getName());
        }
    };

}
