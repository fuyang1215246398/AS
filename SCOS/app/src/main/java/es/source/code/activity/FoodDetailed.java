package es.source.code.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.adapter3.OrderFragment;
import es.source.code.adapter3.OrderPagerAdapter;

public class FoodDetailed extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private List<Fragment> list;
    private OrderPagerAdapter adapter;
    private ListView listView;
    public static  String[] tabTitle = new String[]{"什锦冷菜","杂蔬菜冷盘","凉面冷盘","拌凉菜",
"可乐鸡翅","烧羊肉","水煮肉片","宫保鸡丁","麻婆豆腐","海鲜面","海鲜粥","海鲜沙拉",
            "海鲜大咖","烤大虾","果粒橙","可乐","雪碧","脉动","农夫山泉"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detailed);
        int i=0;
        if(getIntent().getStringExtra("Name")!=null){
        String Reci=getIntent().getStringExtra("Name");

        for(i=0;i<19;i++)
        {
            if(Reci.equals(tabTitle[i])){
                break;
            }
        }
        if(i==19)i=0;
        }
       if(getIntent().getStringExtra("Update")!=null){
            String foodname=getIntent().getStringExtra("FoodName");
          //  Toast.makeText(FoodDetailed.this, "+++", Toast.LENGTH_SHORT).show();
            insert(foodname);
            i=19;
        }
        initView(i);
    }
    public static void insert(String foodname){
        int leng=tabTitle.length;
        boolean flag=false;

            if(foodname.equals(tabTitle[leng-1])){
                flag=true;
            }
        if(!flag){
        String[] copy=new String[leng];
        for(int i=0;i<leng;i++){
            copy[i]=tabTitle[i];
        }
        tabTitle=new String[leng+1];
        for(int i=0;i<leng;i++){
            tabTitle[i]=copy[i];
        }
        tabTitle[leng]=foodname;}
    }
    private void initView(int j) {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.TabLayout);
        list = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            list.add(OrderFragment.newInstance(i + 1));
        }

        adapter = new OrderPagerAdapter(list, getSupportFragmentManager());
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(j);
        //将TabLayout和ViewPager关联起来。
        tableLayout.setupWithViewPager(viewPager);
        //设置可以滑动
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


}
