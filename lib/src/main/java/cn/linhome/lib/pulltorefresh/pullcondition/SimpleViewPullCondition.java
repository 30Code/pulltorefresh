package cn.linhome.lib.pulltorefresh.pullcondition;

import android.view.View;


import java.lang.ref.WeakReference;

import cn.linhome.lib.pulltorefresh.PullToRefreshView;

public class SimpleViewPullCondition implements PullToRefreshView.PullCondition
{
    private WeakReference<View> mView;

    public SimpleViewPullCondition(View view)
    {
        mView = new WeakReference<>(view);
    }

    public View getView()
    {
        if (mView != null)
        {
            return mView.get();
        } else
        {
            return null;
        }
    }

    @Override
    public boolean canPullFromHeader(PullToRefreshView view)
    {
        if (getView() == null)
        {
            return true;
        } else
        {
            return !getView().canScrollVertically(-1);
        }
    }

    @Override
    public boolean canPullFromFooter(PullToRefreshView view)
    {
        if (getView() == null)
        {
            return true;
        } else
        {
            return !getView().canScrollVertically(1);
        }
    }
}
