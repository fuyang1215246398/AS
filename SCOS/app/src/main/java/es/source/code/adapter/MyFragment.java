package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.FoodView;
import es.source.code.activity.R;

/**
 * Created by Administrator on 2016/11/7.
 */

public class MyFragment extends Fragment {
    private ListView listView;
    private ListAdapter adapter;
    private ArrayList<Map<String,Object>> list = new ArrayList<>();
    public  static   ArrayList<HashMap<String,Object>> coldFoodList = new ArrayList<HashMap<String,Object>>(){
        {
            add(new HashMap<String, Object>(){
                {
                    put("content","什锦冷菜");
                    put("Price",15);
                    put("num",12);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","杂蔬菜冷盘");
                    put("Price",13);
                    put("num",10);
                }

            });
            add(new HashMap<String, Object>(){
                {
                   put("content","凉面冷盘");
                   put("Price",14);
                   put("num",18);
                }

            });
            add(new HashMap<String, Object>(){
                {
                   put("content","拌凉菜");
                    put("Price",10);
                    put("num",11);
                }

            });
        }
    };
    public  static   ArrayList<HashMap<String,Object>> HotFoodList = new ArrayList<HashMap<String,Object>>(){
        {
            add(new HashMap<String, Object>(){
                {
                    put("content","可乐鸡翅");
                    put("Price",21);
                    put("num",26);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","烧羊肉");
                    put("Price",26);
                    put("num",32);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","水煮肉片");
                    put("Price",19);
                    put("num",18);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","宫保鸡丁");
                    put("Price",15);
                    put("num",11);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","麻婆豆腐");
                    put("Price",13);
                    put("num",13);
                }

            });
        }
    };
    public  static   ArrayList<HashMap<String,Object>> SeaFoodList = new ArrayList<HashMap<String,Object>>(){
        {
            add(new HashMap<String, Object>(){
                {
                    put("content","海鲜面");
                    put("Price",11);
                    put("num",43);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","海鲜粥");
                    put("Price",16);
                    put("num",32);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","海鲜沙拉");
                    put("Price",12);
                    put("num",12);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","海鲜大咖");
                    put("Price",78);
                    put("num",33);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","烤大虾");
                    put("Price",8);
                    put("num",47);
                }

            });
        }
    };
    public  static   ArrayList<HashMap<String,Object>> DrinkList = new ArrayList<HashMap<String,Object>>(){
        {
            add(new HashMap<String, Object>(){
                {
                    put("content","果粒橙");
                    put("Price",4);
                    put("num",35);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","可乐");
                    put("Price",3);
                    put("num",31);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","雪碧");
                    put("Price",3);
                    put("num",18);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","脉动");
                    put("Price",4);
                    put("num",11);
                }

            });
            add(new HashMap<String, Object>(){
                {
                    put("content","农夫山泉");
                    put("Price",2);
                    put("num",13);
                }

            });
        }
    };
String s;
int counter=0;



    private int type;
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    public static MyFragment newInstance(int type) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list,container,false);
        initView(view);
        return view;
    }
    public void updataNum(boolean flag,ArrayList<HashMap<String,Object>> FoodList){
        HashMap<String,Object> map = new HashMap<>();
        if(flag){
            for(int i=0;i<FoodList.size();i++){
                for (int j=0;j<list.size();j++)
                {
                    String name1=(String)FoodList.get(i).get("content");
                    String name2=(String)list.get(j).get("FoodName");
                    int num=(int)list.get(j).get("num");
                    if(name1.equals(name2)){
                        map=FoodList.get(i);
                        map.put("num",num);
                    }
                }
            }
        }
    }
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView_id);
        HashMap<String,Object> map = new HashMap<>();
        list=FoodView.FoodNum;
        boolean flag=false;
        if(list!=null)flag=true;
        switch (type){
            case 1:
              updataNum(flag,coldFoodList);
                adapter = new ListAdapter(coldFoodList,getActivity());
                listView.setAdapter(adapter);
                final Context context1;
                context1=adapter.GetContext();
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView FoodName=(TextView) view.findViewById(R.id.text2_id);
                        s=FoodName.getText().toString();
                        Intent intent=new Intent(context1,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context1.startActivity(intent);
                    }
                });
                break;
            case 2:
                updataNum(flag,HotFoodList);
                adapter = new ListAdapter(HotFoodList,getActivity());
                listView.setAdapter(adapter);
                final Context context2;
                context2=adapter.GetContext();
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView FoodName=(TextView) view.findViewById(R.id.text2_id);
                        s=FoodName.getText().toString();
                        Intent intent=new Intent(context2,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context2.startActivity(intent);
                    }
                });
                break;
            case 3:
                updataNum(flag,SeaFoodList);
                adapter = new ListAdapter(SeaFoodList,getActivity());
                listView.setAdapter(adapter);
                final Context context3;
                context3=adapter.GetContext();
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView FoodName=(TextView) view.findViewById(R.id.text2_id);
                        s=FoodName.getText().toString();
                        Intent intent=new Intent(context3,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context3.startActivity(intent);
                    }
                });
                break;
            case 4:
                updataNum(flag,DrinkList);
                adapter = new ListAdapter(DrinkList,getActivity());
                listView.setAdapter(adapter);
                final Context context4;
                context4=adapter.GetContext();
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView FoodName=(TextView) view.findViewById(R.id.text2_id);
                        s=FoodName.getText().toString();
                        Intent intent=new Intent(context4,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context4.startActivity(intent);
                    }
                });
        }
    }
}

