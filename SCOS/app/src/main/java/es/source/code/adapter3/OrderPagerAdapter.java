package es.source.code.adapter3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.FoodOrderView;

/**
 * Created by Administrator on 2016/11/7.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public OrderPagerAdapter(List<Fragment> list, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return FoodDetailed.tabTitle[position];

    }
}
