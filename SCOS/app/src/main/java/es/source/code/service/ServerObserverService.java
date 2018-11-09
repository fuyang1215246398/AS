package es.source.code.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodView;
import es.source.code.activity.SCOSHelper;
import es.source.code.model.EventBusCarrier;

import static java.lang.Thread.sleep;

public class ServerObserverService extends Service {
   public int counter=20;;
   public boolean threadDisable=false;
    public static final String[] tabTitle = new String[]{"什锦冷菜","杂蔬菜冷盘","凉面冷盘","拌凉菜",
            "可乐鸡翅","烧羊肉","水煮肉片","宫保鸡丁","麻婆豆腐","海鲜面","海鲜粥","海鲜沙拉",
            "海鲜大咖","烤大虾","果粒橙","可乐","雪碧","脉动","农夫山泉"
    };
   public ArrayList<Map<String,Object>> FoodNum=new ArrayList<>();
   public  void  intFoodNum(){
       Map<String,Object> map = new HashMap<>();
        for(int i=0;i<19;i++){
            int x=1+(int)(Math.random()*50);
            map.put("FoodName",tabTitle[i]);
            map.put("num",x);
            FoodNum.add(map);
            map = new HashMap<>();
        }

   }
    Messenger mMessenger, cMessenger; //Service的信使对象和客户端的信使对象
    public ServerObserverService() {
    }

    public boolean isRun(Context context){
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = "es.source.code.activity";
        //100表示取的最大的任务数，info.topActivity表示当前正在运行的Activity，info.baseActivity表系统后台有此进程在运行
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME) || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;

                break;
            }
        }

        return isAppRunning;
    }

  /*final Handler cMessageHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            cMessenger =msg.replyTo;

            final Context context=getApplicationContext();
            final boolean isrun=isRun(context);
            switch (msg.what){
                case 1:
                threadDisable=false;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(isrun){
                            while(!threadDisable){
                                intFoodNum();
                        Message message =Message.obtain();
                        message.what=10;
                            Bundle b=new Bundle();
                            b.putSerializable("FoodNum",(Serializable)FoodNum);
                         message.setData(b);
                        try {
                            cMessenger.send(message);
                        }catch (RemoteException e){
                            e.printStackTrace();

                    }
                            try {
                                sleep(10000);
                            } catch (InterruptedException e) {
// TODO 自动生成的 catch 块
                                e.printStackTrace();
                            }
                            }}}
                }).start();


                    break;
                case 0:
                    threadDisable=true;
                    break;
                default:
                    break;
            }
        }
    };*/
 @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier eventBusCarrier) {
     final Context context=getApplicationContext();
     final boolean isrun=isRun(context);
     int what = eventBusCarrier.getWhat();
     switch (what){
         case 1:
             threadDisable=false;
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     if(isrun){
                         while(!threadDisable){
                             intFoodNum();
                             Log.d("Server", "run: ");
                             EventBusCarrier eventBusCarrier=new EventBusCarrier();
                             eventBusCarrier.setWhat(10);
                             eventBusCarrier.setFoodNum(FoodNum);
                             EventBus.getDefault().post(eventBusCarrier);

                             try {
                                 sleep(5000);
                             } catch (InterruptedException e) {
// TODO 自动生成的 catch 块
                                 e.printStackTrace();
                             }
                         }}}
             }).start();


             break;
         case 0:
             threadDisable=true;
             break;
         default:
             break;
     }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

       return  mMessenger.getBinder();
        // throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onDestroy(){
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    public void onCreate(){
        super.onCreate();
        EventBus.getDefault().register(this);
       // mMessenger = new Messenger(cMessageHandler);
        //EventBus.getDefault().register(ServerObserverService.this);
    }
}
