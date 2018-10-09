package es.source.code.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import es.source.code.adapter.*;
import java.util.ArrayList;
import java.util.List;
import  es.source.code.model.User;
import es.source.code.model.*;
public class FoodView extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private List<Fragment> list;
    private MyPagerAdapter adapter;
    User user=new User();
    public static final String[] tabTitle = new String[]{"冷菜", "热菜", "海鲜","酒水"};
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.mn,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Ordered:
            {
                Toast.makeText(this,"已点菜单",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FoodView.this,FoodOrderView.class);
               intent.putExtra("int","0");
                intent.putExtra("User_data",user);
                startActivity(intent);
            }break;

            case R.id.WList:
            {
                Toast.makeText(this,"查看订单",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FoodView.this,FoodOrderView.class);
                intent.putExtra("int","1");
                intent.putExtra("User_data",user);
                startActivity(intent);
            }break;
            case R.id.Help:
            {
                Toast.makeText(this,"呼叫服务",Toast.LENGTH_SHORT).show();
            }break;
            default :break;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        ActionBar actionBar=getSupportActionBar();
        user=(User)getIntent().getSerializableExtra("User_data");
        initView();
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        tableLayout = (TabLayout) findViewById(R.id.TabLayout_id);
        list = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            list.add(MyFragment.newInstance(i + 1));
        }
        adapter = new MyPagerAdapter(list, getSupportFragmentManager());
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tableLayout.setupWithViewPager(viewPager);
        //设置可以滑动
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
