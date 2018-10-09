package es.source.code.adapter2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import es.source.code.activity.FoodOrderView;
import es.source.code.model.User;

/**
 * Created by Administrator on 2016/11/7.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    User user=new User();
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
        return FoodOrderView.tabTitle[position];

    }

    public void setUser(User user) {
        this.user=user;
    }
}
