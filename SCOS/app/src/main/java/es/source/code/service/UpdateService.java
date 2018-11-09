package es.source.code.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import es.source.code.Utils.Methods;
import es.source.code.Utils.Const;
import es.source.code.activity.MainScreen;
import es.source.code.model.Food;
import es.source.code.model.*;
/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class UpdateService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "es.source.code.service.action.FOO";
    private static final String ACTION_BAZ = "es.source.code.service.action.BAZ";
    private static final String TAG = "UpdateService";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "es.source.code.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "es.source.code.service.extra.PARAM2";
    public String FoodName="水晶葡萄";
    public String FoodType="酒水";
    public int  FoodPrice=3;

    private static final int TYPE_JSON = 1;
    private static final int TYPE_XML = 2;
    private static int Type=0;

    List<Food> foodList ;
    private Context mContext;
    public UpdateService() {
        super("UpdateService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mContext = this;
        // 这里修改类型
     Type = TYPE_JSON;
     // Type = TYPE_XML;
        updateServer();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public  void updateServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(Type == TYPE_JSON){
                    InputStream resultStream = Methods.connectServerToUpdateFood(null,Const.URL.FOOD,"application/json");
                    if(resultStream==null)return;
                    String resultString = Methods.streamToString(resultStream);
                    Log.d(TAG,resultString);
                    Log.d(TAG, "request = " + resultString);
                    Json json = new Gson().fromJson(resultString, Json.class);
                    String foodString = json.getDataString();
                    Type type = new TypeToken<ArrayList<Food>>() {
                    }.getType();
                    Date  startDate =new Date(System.currentTimeMillis());
                     foodList =new  Gson().fromJson(foodString,type);
                    Date endDate = new  Date(System.currentTimeMillis());
                    long time =endDate.getTime()-startDate.getTime();
                    Log.d(TAG,"Json 生成时间是: "+String.valueOf(time)+"ms,大小是: "+ String.valueOf(foodList.size()));
                }else if (Type == TYPE_XML){
                   InputStream inputStream=Methods.connectServerToUpdateFood(null,Const.URL.FOOD,"text/xml");
                    if(inputStream==null)return;
                    Xml xml = null;
                    try {
                        xml = Methods.getResultFromXml(Methods.streamToXml(inputStream));
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                    if (xml!=null) foodList = xml.getDataList();
                }
                Message message =Message.obtain();
                message.what=1;
                mHandler.sendMessage(message);
            }}).start();
    }

     final Handler mHandler = new Handler() {
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              if(msg.what==1) {
                  Intent intent = new Intent(UpdateService.this, MainScreen.class);
                  intent.putExtra("string_data",111);
                  PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent
                          .FLAG_CANCEL_CURRENT);
                  // 发送状态栏通知
                  MyNotification myNotification=new MyNotification(getApplicationContext());
                  myNotification.sendNotification(foodList.size());
                  // 播放提示音
                 // playNotification();
              }
          }
      };



    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void playNotification() {
        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mediaPlayer = MediaPlayer.create(mContext, ringtone);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}
