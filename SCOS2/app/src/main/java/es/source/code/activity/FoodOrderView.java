package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import  es.source.code.adapter2.*;
import es.source.code.model.User;

import java.util.ArrayList;
import java.util.List;
public class FoodOrderView extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private List<Fragment> list;
    private OrderPagerAdapter adapter;
    private ListView listView;
    String  which=new String();
   public static  User user=new User();

    public User getUser() {
        return user;
    }

    public static final String[] tabTitle = new String[]{"未下单菜", "已下单菜"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        which=getIntent().getStringExtra("int");
        user=(User)getIntent().getSerializableExtra("User_data");

        initView(which);



    }

    private void initView(String s) {
        int j=0;
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.TabLayout);
        list = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            list.add(OrderFragment.newInstance(i + 1));
        }
        adapter = new OrderPagerAdapter(list, getSupportFragmentManager());

        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
    if(s!=null){
    if (s.equals("0"))j=0;
    else j=1;
       }
        viewPager.setCurrentItem(j);
        //将TabLayout和ViewPager关联起来。
        tableLayout.setupWithViewPager(viewPager);
        //设置可以滑动
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

}
