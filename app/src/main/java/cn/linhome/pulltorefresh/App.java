package cn.linhome.pulltorefresh;

import android.app.Application;

import com.fanwe.library.SDLibrary;

/**
 * des:
 * Created by yangwb
 * on 2018/5/22.
 */

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        SDLibrary.getInstance().init(this);
    }
}
