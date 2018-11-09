package es.source.code.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import es.source.code.adapter.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import  es.source.code.service.*;
import es.source.code.adapter2.OrderFragment;
import  es.source.code.model.User;
import es.source.code.model.*;
import es.source.code.model.EventBusCarrier;
public class FoodView extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tableLayout;
    private List<Fragment> list;
    private MyPagerAdapter adapter;
    public static   ArrayList<Map<String,Object>> FoodNum=null;
    Menu menu;
    User user=new User();
    int counter = 0;
    private NotificationManager manager;
    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    public  static  int count=1;
    Messenger rMessenger, mMessenger;
    public static  ProgressDialog progressDialog;
    private int Notification_ID;
    public static final String[] tabTitle = new String[]{"冷菜", "热菜", "海鲜","酒水"};
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.mn,menu);
        this.menu=menu;
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
            case R.id.Update:
            {
               Intent Nintent=new Intent(this,UpdateService.class);
              startService(Nintent);
                count++;
                handleRefreshClick();

            }break;
            case R.id.Help:
            {
                MyNotification myNotification=new MyNotification(this);
                myNotification.sendNotification(1000);
            }
            default :break;
        }
        return super.onOptionsItemSelected(item);

    }
    private void handleRefreshClick() {
       // if(mMessenger==null)return;
        MenuItem item = menu.getItem(0);
        String title = item.getTitle().toString();
        int what;
        if (title.equals("启动实时更新")) {
            title = "停止实时更新";
            what = 1;
        } else {
            title ="启动实时更新";
            what = 0;
        }
       EventBusCarrier eventBusCarrier=new EventBusCarrier();
        eventBusCarrier.setWhat(what);
        //eventBusCarrier.setReplyTo(mMessenger);
        EventBus.getDefault().post(eventBusCarrier);
      /*  Message message = Message.obtain();
        message.what =what;
        message.replyTo=mMessenger;

        try{
            rMessenger.send(message);
        }catch (RemoteException e) {
            e.printStackTrace();
        }*/
        item.setTitle(title);
    }
   /*Handler handler=new Handler(){
      public  void  handleMessage(Message msg){
          if(msg.what==10){
              FoodNum= new ArrayList<>();
        FoodNum=(ArrayList<Map<String,Object>>)msg.getData().getSerializable("FoodNum");
         Map<String,Object> map=FoodNum.get(0);
              initView();

          }

      };
    };*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusCarrier eventBusCarrier) {
        int what=eventBusCarrier.getWhat();
        if(what==10){
            FoodNum= new ArrayList<>();
            FoodNum=eventBusCarrier.getFoodNum();
            Map<String,Object> map=FoodNum.get(0);
            initView();
        }
    }


    private void initBinder() {
        Intent intent = new Intent(this, ServerObserverService.class);
        startService(intent);//

       // bindService(intent, connection, BIND_AUTO_CREATE);
    }

  /* ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            rMessenger =new Messenger(service);
            mMessenger =new Messenger(handler);
            Message message=Message.obtain();
            message.replyTo=mMessenger;
            //if(item.getTitle().toString().equals("启动实时更新"))
            message.what=88;


            try {
                rMessenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
rMessenger=null;
        }
    };*/
  public void onBackPressed(){

  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);

        EventBus.getDefault().register(this);
        manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ActionBar actionBar=getSupportActionBar();
        user=(User)getIntent().getSerializableExtra("User_data");
        initView();
        initBinder();

    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
       /* if(connection!=null){
            unbindService(connection);
        }*/
        super.onDestroy();
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
