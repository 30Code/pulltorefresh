package cn.linhome.pulltorefresh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import cn.linhome.lib.pulltorefresh.FPullToRefreshView;
import cn.linhome.lib.pulltorefresh.PullToRefreshView;
import cn.linhome.library.activity.SDBaseActivity;
import cn.linhome.pulltorefresh.CustomTextLoadingView;
import cn.linhome.pulltorefresh.R;

public class ScrollViewActivity extends SDBaseActivity
{
    private static final String TAG = "ScrollViewActivity";

    private CustomTextLoadingView mCustomTextLoadingView;
    private FPullToRefreshView view_pull;
    private Button btn;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_scrollview);
        view_pull = (FPullToRefreshView) findViewById(R.id.view_pull);
        btn = (Button) findViewById(R.id.btn);

        mCustomTextLoadingView = new CustomTextLoadingView(this);
        mCustomTextLoadingView.setCompleteLoading(false);

        view_pull.setDebug(true);
        view_pull.setFooterView(mCustomTextLoadingView);
        view_pull.setOnStateChangeCallback(new PullToRefreshView.OnStateChangeCallback()
        {
            @Override
            public void onStateChanged(PullToRefreshView.State newState, PullToRefreshView.State oldState, PullToRefreshView view)
            {
                //状态变化回调
                btn.setText(String.valueOf(view.getDirection()) + "->" + String.valueOf(newState));
            }
        });
        view_pull.setOnViewPositionChangeCallback(new PullToRefreshView.OnViewPositionChangeCallback()
        {
            @Override
            public void onViewPositionChanged(PullToRefreshView view)
            {
                //view被拖动回调
                Log.i(TAG, "onViewPositionChanged getScrollDistance:" + view.getScrollDistance());
            }
        });
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
                        view.stopRefreshing();
                    }
                }, 1000);
            }
        });
        view_pull.startRefreshingFromHeader();
    }

}
