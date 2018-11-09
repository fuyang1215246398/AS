package es.source.code.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import es.source.code.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainScreen extends AppCompatActivity {
RelativeLayout ly;
ImageButton but;
ImageButton btn3;
ImageButton btn4;
TextView  tx3;
TextView  tx4;
ImageButton btn5;
    private GridView mAppGridView = null;
    //应用图标
    private int[] mAppIcons ={
            R.drawable.ic_order,R.drawable.ic_list,
            R.drawable.ic_user,R.drawable.ic_help
    };
    User user=new User();
    //应用名
    private  String[] mAppNames={
            "点菜","查看订单","登录/注册","系统帮助"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);
        //获取界面组件

        String Reci=getIntent().getStringExtra("string_data");


        mAppGridView=(GridView)findViewById(R.id.gridview);
        //初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> listItems=new ArrayList<Map<String, Object>>();
        if(Reci!=null){
        if(Reci.equals("FromEntry")||Reci.equals("Return"))
        {
            for(int i=0;i<mAppIcons.length;i++){
                Map<String,Object> listItem =new HashMap<String, Object>();
                listItem.put("icon",mAppIcons[i]);
                listItem.put("name",mAppNames[i]);
                listItems.add(listItem);
            }
           user=null;
        }
        else if(Reci.equals("LoginSuccess")||Reci.equals("RegisterSuccess"))
        {
            for(int i=0;i<4;i++){
                Map<String,Object> listItem =new HashMap<String, Object>();
                listItem.put("icon",mAppIcons[i]);
                listItem.put("name",mAppNames[i]);
                listItems.add(listItem);
            }
            String s;
            s=getIntent().getStringExtra("string_data");
            user=(User)getIntent().getSerializableExtra("User_data");
          //  s+=" "+u.GetUserName()+" "+u.GetPassword()+""+u.GetOldUser();
            if(Reci.equals("RegisterSuccess"))
            Toast.makeText(MainScreen.this, "欢迎您成为SCOS新用户", Toast.LENGTH_SHORT).show();
        }}
        else{
            for(int i=0;i<4;i++){
                Map<String,Object> listItem =new HashMap<String, Object>();
                listItem.put("icon",mAppIcons[i]);
                listItem.put("name",mAppNames[i]);
                listItems.add(listItem);
            }
user=null;
        }

        SimpleAdapter simpleAdapter =new SimpleAdapter(this,
                listItems,
                R.layout.main_screen,
                new String[]{"icon","name"},
                new int[]{R.id.icon_img,R.id.name_tv}
                );
        mAppGridView.setAdapter(simpleAdapter);
        //添加列表被单击的监听器
        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==2){//登录
                    Intent intent=new Intent(MainScreen.this,LoginOrRegister.class);
                    startActivity(intent);
                }
                if(position==0)//点菜
                {
                 Intent intent=new Intent(MainScreen.this,FoodView.class);
                 if(user!=null)
                 intent.putExtra("User_data",user);
                 startActivity(intent);
                }
                if(position==1){//订单
                    Intent intent=new Intent(MainScreen.this,FoodOrderView.class);
                    if(user!=null)
                    intent.putExtra("User_data",user);
                    startActivity(intent);
                }
                if(position==3)
                {
                    Intent intent=new Intent(MainScreen.this,SCOSHelper.class);
                    startActivity(intent);
                }

            }
        });


    }
}
