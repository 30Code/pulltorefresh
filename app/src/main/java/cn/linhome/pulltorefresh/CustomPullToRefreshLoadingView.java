package cn.linhome.pulltorefresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import cn.linhome.lib.pulltorefresh.PullToRefreshView;
import cn.linhome.lib.pulltorefresh.loadingview.SimpleImageLoadingView;
import cn.linhome.lib.utils.FViewUtil;
import cn.linhome.lib.utils.context.FResUtil;

/**
 * Created by Administrator on 2017/6/30.
 */

public class CustomPullToRefreshLoadingView extends SimpleImageLoadingView
{
    public CustomPullToRefreshLoadingView(@NonNull Context context)
    {
        super(context);
    }

    public CustomPullToRefreshLoadingView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomPullToRefreshLoadingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init()
    {
        super.init();
        FViewUtil.setHeight(getImageView(), FResUtil.dp2px(20));
    }

    @Override
    public void onStateChanged(PullToRefreshView.State newState, PullToRefreshView.State oldState, PullToRefreshView view)
    {
        switch (newState)
        {
            case RESET:
            case PULL_TO_REFRESH:
            case FINISH:
                getImageView().setImageResource(R.drawable.ic_pull_refresh_progress16);
                break;
            case RELEASE_TO_REFRESH:
                getImageView().setImageResource(R.drawable.ic_pull_refresh_progress0);
                break;
            case REFRESHING:
                getImageView().setImageResource(R.drawable.ic_pull_refresh_refreshing);
                FViewUtil.startAnimationDrawable(getImageView().getDrawable());
                break;
        }
    }
}
