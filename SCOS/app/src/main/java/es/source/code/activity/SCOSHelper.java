package es.source.code.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import es.source.code.model.User;
import es.source.code.model.EmailSender;
import es.source.code.model.EventBusCarrier;
public class SCOSHelper extends AppCompatActivity {
    private GridView mAppGridView = null;
    //应用图标
    private int[] mAppIcons ={
            R.drawable.ic_rules,R.drawable.ic_help,
            R.drawable.ic_call,R.drawable.ic_message,R.drawable.ic_action_mail
    };
    User user=new User();
    //应用名
    private  String[] mAppNames={
            "用户使用协议","关于系统","电话人工帮助","短信帮助","邮件帮助"
    };



    private final static String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    public static final int REQUEST_CALL_PERMISSION = 10111;
    public  boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    

    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone){
        if(checkReadPermission("android.permission.CALL_PHONE",REQUEST_CALL_PERMISSION)){
            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse(telPhone));
            startActivity(intent);
        }
    }
/* 发送短信*/
private void sendMessage(String tel, String content) {
    SmsManager smsManager = SmsManager.getDefault();
    if (checkReadPermission("android.permission.SEND_SMS",REQUEST_CALL_PERMISSION))
        smsManager.sendTextMessage(tel, null, content, null, null);
}
    private void sendMessage3(String tel, String content) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(tel)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + tel));
            intent.putExtra("sms_body", content);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
   /* final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) Toast.makeText(SCOSHelper.this,"求助邮件发送成功",Toast.LENGTH_SHORT).show();
        }
    };*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier eventBusCarrier) {
        int what=eventBusCarrier.getWhat();
        if(what==2)Toast.makeText(SCOSHelper.this,"求助邮件发送成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoshelper);
        mAppGridView=(GridView)findViewById(R.id.scoshelper);
        EventBus.getDefault().register(this);

        //初始化数据，创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> listItems=new ArrayList<Map<String, Object>>();
            for(int i=0;i<mAppIcons.length;i++){
                Map<String,Object> listItem =new HashMap<String, Object>();
                listItem.put("icon",mAppIcons[i]);
                listItem.put("name",mAppNames[i]);
                listItems.add(listItem);
            }
        SimpleAdapter simpleAdapter =new SimpleAdapter(this,
                listItems,
                R.layout.main_screen,
                new String[]{"icon","name"},
                new int[]{R.id.icon_img,R.id.name_tv}
        );
        mAppGridView.setAdapter(simpleAdapter);

        mAppGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==2){
                  call("tel:"+"5554");
               }else if(position==3){
                   sendMessage("tel:"+"5554","test scos helper");
                   Toast.makeText(SCOSHelper.this, "求助短信发送成功", Toast.LENGTH_SHORT).show();
               }else if(position==4){
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           try {
                               EmailSender sender = new EmailSender();
                               //设置服务器地址和端口，网上搜的到
                               sender.setProperties("smtp.qq.com", "587");
                               //分别设置发件人，邮件标题和文本内容
                               sender.setMessage("1215246398@qq.com", "EmailSender", "Java Mail ！");
                               //设置收件人
                               sender.setReceiver(new String[]{"1215246398@qq.com"});

                               //发送邮件
                               sender.sendEmail("smtp.qq.com", "1215246398@qq.com", "qdyneebvrzjsfijb");//qdyneebvrzjsfijb
                              EventBusCarrier eventBusCarrier=new EventBusCarrier();
                              eventBusCarrier.setWhat(2);
                               EventBus.getDefault().post(eventBusCarrier);
                             /*  Message message = mHandler.obtainMessage();
                               message.what = 1;
                               mHandler.sendMessage(message);*/
                           } catch (AddressException e) {
                               e.printStackTrace();
                           } catch (MessagingException e) {
                               e.printStackTrace();
                           }
                       }
                   }).start();

               }

            }
        });
    }
}
