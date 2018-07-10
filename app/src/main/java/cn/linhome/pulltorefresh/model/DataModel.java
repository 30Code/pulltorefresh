package cn.linhome.pulltorefresh.model;


import com.fanwe.lib.selectmanager.SDSelectManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class DataModel implements SDSelectManager.Selectable
{

    private String name;

    //add
    private boolean selected;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static List<DataModel> getListModel(int count)
    {
        final List<DataModel> listModel = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            DataModel model = new DataModel();
            model.setName(String.valueOf(i));
            listModel.add(model);
        }
        return listModel;
    }

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
