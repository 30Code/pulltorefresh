package cn.linhome.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;

import cn.linhome.lib.pulltorefresh.PullToRefreshView;
import cn.linhome.lib.pulltorefresh.loadingview.SimpleTextLoadingView;

/**
 * des:
 * Created by 30Code
 * on 2019/2/22
 */
public class CustomTextLoadingView extends SimpleTextLoadingView
{
    public CustomTextLoadingView(Context context)
    {
        super(context);
    }

    public CustomTextLoadingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomTextLoadingView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 是否已经全部加载完毕
     */
    private boolean mIsCompleteLoading;

    @Override
    public void onStateChanged(PullToRefreshView.State newState, PullToRefreshView.State oldState, PullToRefreshView view)
    {
        super.onStateChanged(newState, oldState, view);
        switch (newState)
        {
            case RESET:
            case PULL_TO_REFRESH:
                if (this == getPullToRefreshView().getFooterView())
                {
                    loadMoreData();
                }
                break;
            case RELEASE_TO_REFRESH:
                if (this == getPullToRefreshView().getFooterView())
                {
                    loadMoreData();
                }
                break;
            case REFRESHING:
                if (this == getPullToRefreshView().getFooterView())
                {
                    loadMoreData();
                }
                break;
            case REFRESHING_SUCCESS:
                if (this == getPullToRefreshView().getFooterView())
                {
                    loadMoreData();
                }
                break;
            case REFRESHING_FAILURE:
                if (this == getPullToRefreshView().getFooterView())
                {
                    loadMoreData();
                }
                break;
            case FINISH:
                if (oldState == PullToRefreshView.State.REFRESHING)
                {
                    if (this == getPullToRefreshView().getFooterView())
                    {
                        loadMoreData();
                    }
                }
        }
    }

    private void loadMoreData()
    {
        if (isCompleteLoading())
        {
            getProgressBar().setVisibility(GONE);
            getTextView().setText(getResources().getString(R.string.complete_loading));
        }else
        {
            getProgressBar().setVisibility(VISIBLE);
            getTextView().setText(getResources().getString(R.string.pull_to_load_more));
        }
    }

    public boolean isCompleteLoading()
    {
        return mIsCompleteLoading;
    }

    public void setCompleteLoading(boolean completeLoading)
    {
        mIsCompleteLoading = completeLoading;
    }
}
