package es.source.code.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;
import android.graphics.drawable.*;
import java.util.regex.*;
import android.content.Intent;
import es.source.code.model.User;
public class LoginOrRegister extends AppCompatActivity {
Button btn;
    ProgressDialog progressDialog;
    public static final String VALID_ACCOUNT = "^[A-Za-z1-9_-]+$";
Button btn2;
   EditText et1;
    EditText et2;
    Button  btn3;
   //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
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
                progressDialog=new ProgressDialog(LoginOrRegister.this);
                progressDialog.setTitle("登陆中");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
// TODO 自动生成的 catch 块
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();// 万万不可少这句，否则会程序会卡死。
                    }
                };
                thread.start();
                EditText user=(EditText)findViewById(R.id.userId);//用户名
                EditText pass=(EditText)findViewById(R.id.pass);//密码
                String  UserName=user.getText().toString();//获取用户名
                String  PassWord=pass.getText().toString();//获取密码

                User USer=new User();
                USer.SetUserName(UserName);
                USer.SetPassword(PassWord);
                USer.SetOldUser(true);

                Intent intent=new Intent(LoginOrRegister.this,MainScreen.class);
                intent.putExtra("string_data","LoginSuccess");
                intent.putExtra("User_data",USer);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                User USer=new User();
                USer.SetUserName(UserName);
                USer.SetPassword(PassWord);
                USer.SetOldUser(false);

                Intent intent=new Intent(LoginOrRegister.this,MainScreen.class);
                intent.putExtra("string_data","RegisterSuccess");
                intent.putExtra("User_data",USer);
                startActivity(intent);
            }
        });

    }
}
