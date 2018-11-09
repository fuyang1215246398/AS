package es.source.code.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;
import android.graphics.drawable.*;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import es.source.code.model.User;
import es.source.code.model.*;
import es.source.code.Utils.*;

import java.util.regex.Pattern;

import es.source.code.model.UserInfo;

public class LoginOrRegister extends AppCompatActivity {
private  Button btn;
  private   ProgressDialog progressDialog;
    public static final String VALID_ACCOUNT = "^[A-Za-z1-9_-]+$";
private Button btn2;
 private   EditText et1;
 private    EditText et2;
    private  Button  btn3;
    private int progress = 0;
    private static boolean URLFlag=false;
   private  SharedPreferences pref;
   private  SharedPreferences.Editor editor;
 private  Timer timer;
    private TimerTask timerTask;
    private  static  boolean oldUser=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        EventBus.getDefault().register(this);
        et1=(EditText)findViewById(R.id.userId);
        btn=(Button)findViewById(R.id.loginBtn);
        btn2=(Button)findViewById(R.id.Register);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        String account =pref.getString("UserName","");
        if(account.equals("")) {
            btn.setVisibility(View.GONE);
        }else {
            et1.setText(account);
            btn2.setVisibility(View.GONE);
        }

        btn=(Button)findViewById(R.id.loginBtn);
        btn.setEnabled(false);

        et1=(EditText)findViewById(R.id.userId);
        et2=(EditText)findViewById(R.id.pass);
        btn2=(Button)findViewById(R.id.Return);
         btn3=(Button)findViewById(R.id.Register);//注册按钮
        btn3.setEnabled(false);
       et1.addTextChangedListener(new TextWatcher() {
            Pattern pattern =Pattern.compile(VALID_ACCOUNT);
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Drawable dr = getResources().getDrawable(R.drawable.ad3);
                if (!pattern.matcher(s).matches()) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);
                    et1.setError("输入不合规范",dr);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Drawable dr = getResources().getDrawable(R.drawable.ad3);
                if (!pattern.matcher(s).matches()) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);
                    et1.setError("输入不合规范",dr);
                } else if (et1.getText().toString().trim().length() == 0||
                        et2.getText().toString().trim().length() == 0     ) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);
                } else {
                    et1.setError(null);
                    btn.setEnabled(true);
                    btn3.setEnabled(true);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            Pattern pattern =Pattern.compile(VALID_ACCOUNT);
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Drawable dr = getResources().getDrawable(R.drawable.ad3);
                if (!pattern.matcher(s).matches()) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);
                    et1.setError("输入不合规范",dr);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Drawable dr = getResources().getDrawable(R.drawable.ad3);
                if (!pattern.matcher(s).matches()) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);
                    et1.setError("输入不合规范",dr);
                } else if (et2.getText().toString().trim().length() == 0||et1.getText().toString().trim().length() == 0 ) {
                    btn.setEnabled(false);
                    btn3.setEnabled(false);//使注册按钮不能点击
                } else {
                    et1.setError(null);
                    btn.setEnabled(true);
                    btn3.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {//点击登录
            LoginOrRegister.oldUser=true;
               /* Intent intent=new Intent(LoginOrRegister.this,MainScreen.class);
                intent.putExtra("string_data","LoginSuccess");
                intent.putExtra("User_data",USer);
                startActivity(intent);*/
               LoginOrRegister(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account =pref.getString("UserName","");
                editor=pref.edit();
                if(account.equals("")) {

                }else {
                    editor.putInt("loginState",0);
                    editor.apply();
                }
                Intent intent=new Intent(LoginOrRegister.this,MainScreen.class);
                intent.putExtra("string_data","Return");
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user=(EditText)findViewById(R.id.userId);//用户名
                EditText pass=(EditText)findViewById(R.id.pass);//密码
                String  UserName=user.getText().toString();//获取用户名
                String  PassWord=pass.getText().toString();//获取密码
                LoginOrRegister.oldUser=false;
               LoginOrRegister(false);
            }
        });

    }


    private void LoginOrRegister(final boolean oldUser) {
        Log.d("第六次作业","点击登录");
        progressDialog=new ProgressDialog(LoginOrRegister.this);
        if(oldUser)
        progressDialog.setTitle("登陆中");
        else progressDialog.setTitle("注册中");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                progressDialog.dismiss();
                //结束本界面并跳转到收派员列表的界面
                t.cancel();
            }

        }, 2000);
        HttpLoginOperate httpURLOperate=new HttpLoginOperate();
        httpURLOperate.start();//启动网络线程
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusCarrier eventBusCarrier) {
        int what=eventBusCarrier.getWhat();
        if(what==1){
            et1 = (EditText) findViewById(R.id.userId);
            et2 = (EditText) findViewById(R.id.pass);
            final String name = et1.getText().toString();
            final String password = et2.getText().toString();
            User USer=new User();
            USer.SetUserName(name);
            USer.SetPassword(password);
            USer.SetOldUser(oldUser);
            String account =et1.getText().toString();
            editor =pref.edit();
            editor.putString("UserName",account);
            editor.putInt("loginState",1);
            editor.apply();
            Intent intent=new Intent(LoginOrRegister.this,MainScreen.class);
            if(oldUser){
                intent.putExtra("string_data","LoginSuccess");
                intent.putExtra("User_data",USer);
            }else {
                intent.putExtra("string_data","RegisterSuccess");
                intent.putExtra("User_data",USer);
            }
            startActivity(intent);

        }
    }
    class HttpLoginOperate extends Thread{
        public void run(){
            try {
                et1 = (EditText) findViewById(R.id.userId);
                et2 = (EditText) findViewById(R.id.pass);
                final String name = et1.getText().toString();
                final String password = et2.getText().toString();
                UserInfo userInfo =new UserInfo(name,password);
                String loginParamString=new Gson().toJson(userInfo);
                //请求的参数转换为byte数组
                byte[] postData=loginParamString.getBytes("utf8");
                String postUrl = Const.URL.BASE + Const.URL.LOGIN;
                URL url = new URL(postUrl);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                //设置连接超时时间
                connection.setConnectTimeout(5000);
                //设置从主机读取数据超时时间
                connection.setReadTimeout(5000);
                //POST请求设置允许输出
                connection.setDoOutput(true);
                //REQUEST请求设置允许输出
                connection.setDoInput(true);
                //POST请求不能使用缓存
                connection.setUseCaches(false);
                //设置为POST请求
                connection.setRequestMethod("POST");
                //设置本次连接是否自动处理重定向
                connection.setInstanceFollowRedirects(true);
                //配置请求Content-Type
                connection.setRequestProperty("Content-Type","application/json");
                DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                out.write(postData);
                out.flush();
                out.close();
                // 判断请求是否成功
                if (connection.getResponseCode() == 200) {
                    // 获取返回的数据
                    String resultString= Methods.streamToString(connection.getInputStream());
                    Json json =new Gson().fromJson(resultString,Json.class);
                    int i = json.getRESULTCODE();
                    Log.d("返回的是",i+"");
                    if(json.getRESULTCODE()==1){
                        EventBusCarrier eventBusCarrier=new EventBusCarrier();
                        eventBusCarrier.setWhat(1);
                        EventBus.getDefault().post(eventBusCarrier);
                    }else{
                    }
                }
                // 关闭连接
                connection.disconnect();
            }catch (IOException e){
                Log.d("返回的是","错误");
              //  Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();
            }

        }}
        protected  void onDestroy(){
        super.onDestroy();
            EventBus.getDefault().unregister(this);
        }
}
