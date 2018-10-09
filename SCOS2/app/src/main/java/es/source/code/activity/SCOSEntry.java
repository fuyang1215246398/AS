package es.source.code.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Intent;
public class SCOSEntry extends AppCompatActivity {
    private GestureDetector mGestureDetector;
    public final  String str="FromEntry";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        mGestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener() {
            @Override

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
                if(Math.abs(e1.getRawY() - e2.getRawY())>100){
                    return true;
                }
                if(Math.abs(velocityX)<150){
                    return true;
                }
                if((e1.getRawX() - e2.getRawX()) >120){//  滑动表示下一页
                    //显示下一页
                    Toast.makeText(SCOSEntry.this, "向左手势", Toast.LENGTH_SHORT).show();
                    Intent intend=new Intent(SCOSEntry.this,MainScreen.class);
                    intend.putExtra("string_data","FromEntry");
                    startActivity(intend);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
            }
            @Override
    public boolean onTouchEvent(MotionEvent event) {
        //2.让手势识别器生效
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
