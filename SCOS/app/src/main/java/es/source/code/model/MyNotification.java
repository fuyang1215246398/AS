package es.source.code.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Switch;

import es.source.code.activity.MainScreen;
import es.source.code.activity.R;

public class MyNotification {
    private static final String TAG = "ProgressNotification";
    public final static String INTENT_BUTTONID_TAG = "ButtonId";
    /**
     * 通知栏按钮点击事件对应的ACTION（标识广播）
     */
    public final static String ACTION_BUTTON = "com.notification.intent.action.ButtonClick";
    /**
     * 通知栏按钮广播
     */
    public ButtonBroadCastReceiver receiver;
    public final static int BUTTON_PALY_ID = 1;
     private final int NOTIFICATION_ID = 0xa01;
     private Context context;
     private NotificationManager notificationManager;
     private RemoteViews contentView;
     private Notification notification;
     public MyNotification(Context context){
         this.context=context;
     }
     public void sendNotification(int foodNum){
         notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
         NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(context);
         // 此处设置的图标仅用于显示新提醒时候出现在设备的通知栏
         mBuilder.setSmallIcon(R.mipmap.ic_launcher);
         notification=mBuilder.build();
         // 当用户下来通知栏时候看到的就是RemoteViews中自定义的Notification布局
         contentView = new RemoteViews(context.getPackageName(),R.layout.notification);
         contentView.setTextViewText(R.id.foodNum,foodNum+"");
         contentView.setImageViewResource(R.id.btn_delete,R.drawable.ic_close);
         //注册广播
         receiver =new ButtonBroadCastReceiver();
         IntentFilter intentFilter =new IntentFilter();
         intentFilter.addAction(ACTION_BUTTON);
         context.registerReceiver(receiver,intentFilter);
         //设置点击事件
         Intent buttonIntent = new Intent(ACTION_BUTTON);
         buttonIntent.putExtra(INTENT_BUTTONID_TAG,BUTTON_PALY_ID);
         PendingIntent intent_play=PendingIntent.getBroadcast(context,BUTTON_PALY_ID,buttonIntent,PendingIntent.FLAG_UPDATE_CURRENT);
         contentView.setOnClickPendingIntent(R.id.btn_delete,intent_play);

         notification.contentView=contentView;
         //点击后自动消失
         notification.flags=Notification.FLAG_AUTO_CANCEL;
         Intent intent = new Intent(context, MainScreen.class);
         intent.putExtra("string_data",111);
         PendingIntent pi=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
         notification.contentIntent=pi;
         //发送通知
         notificationManager.notify(NOTIFICATION_ID,notification);
     }
     public class ButtonBroadCastReceiver extends BroadcastReceiver{

         @Override
         public void onReceive(Context context, Intent intent) {
             String action = intent.getAction();
             if(action.equals(ACTION_BUTTON)){
                 int buttonId =intent.getIntExtra(INTENT_BUTTONID_TAG,0);
                 switch (buttonId) {
                     case BUTTON_PALY_ID:
                         NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                         notificationManager.cancel(NOTIFICATION_ID);
                         break;
                     default:
                         break; }

             }
         }
     }
}
