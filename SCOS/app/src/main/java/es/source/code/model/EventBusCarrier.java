package es.source.code.model;

import android.os.Messenger;

import java.util.ArrayList;
import java.util.Map;

public class EventBusCarrier {
    private  int  what;
    private ArrayList<Map<String,Object>> FoodNum=new ArrayList<>();
    public  int getWhat(){return  what;}

    public ArrayList<Map<String, Object>> getFoodNum() {
        return FoodNum;
    }
    public void setFoodNum(ArrayList<Map<String,Object>> Food){
        this.FoodNum=Food;
    }
    public  void setWhat(int what){
        this.what=what;
    }

}
