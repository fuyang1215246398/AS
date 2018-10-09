package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;

/**
 * Created by Administrator on 2016/11/7.
 */

public class MyFragment extends Fragment {
    private ListView listView;
    private ListAdapter adapter;
    private List<Map<String,Object>> list = new ArrayList<>();
String s;




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

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView_id);
        Map<String,Object> map = new HashMap<>();
        switch (type){
            case 1:
                list.clear();
                    map.put("content","什锦冷菜");
                    map.put("Price",15);
                    list.add(map);
                    map=new HashMap<>();

                    map.put("content","杂蔬菜冷盘");
                    map.put("Price",13);
                    list.add(map);
                map = new HashMap<>();
                map.put("content","凉面冷盘");
                map.put("Price",14);
                list.add(map);
                map = new HashMap<>();
                map.put("content","拌凉菜");
                map.put("Price",10);
                list.add(map);
                adapter = new ListAdapter(list,getActivity());
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
                list.clear();

                map.put("content","可乐鸡翅");
                map.put("Price",21);
                list.add(map);
                map=new HashMap<>();

                map.put("content","烧羊肉");
                map.put("Price",26);
                list.add(map);
                map=new HashMap<>();
                map.put("content","水煮肉片");
                map.put("Price",19);
                list.add(map);
                map=new HashMap<>();
                map.put("content","宫保鸡丁");
                map.put("Price",15);
                list.add(map);
                map=new HashMap<>();
                map.put("content","麻婆豆腐");
                map.put("Price",13);
                list.add(map);

                adapter = new ListAdapter(list,getActivity());
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
                list.clear();

                map.put("content","海鲜面");
                map.put("Price",11);
                list.add(map);
                map=new HashMap<>();

                map.put("content","海鲜粥");
                map.put("Price",16);
                list.add(map);
                map=new HashMap<>();
                map.put("content","海鲜沙拉");
                map.put("Price",12);
                list.add(map);
                map=new HashMap<>();
                map.put("content","海鲜大咖");
                map.put("Price",78);
                list.add(map);
                map=new HashMap<>();
                map.put("content","烤大虾");
                map.put("Price",8);
                list.add(map);
                adapter = new ListAdapter(list,getActivity());
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
                list.clear();

                map.put("content","果粒橙");
                map.put("Price",4);
                list.add(map);
                map=new HashMap<>();

                map.put("content","可乐");
                map.put("Price",3);
                list.add(map);
                map=new HashMap<>();
                map.put("content","雪碧");
                map.put("Price",3);
                list.add(map);
                map=new HashMap<>();
                map.put("content","脉动");
                map.put("Price",4);
                list.add(map);
                map=new HashMap<>();
                map.put("content","农夫山泉");
                map.put("Price",2);
                list.add(map);

                adapter = new ListAdapter(list,getActivity());
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

