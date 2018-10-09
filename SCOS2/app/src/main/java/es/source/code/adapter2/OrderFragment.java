package es.source.code.adapter2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.FoodOrderView;
import es.source.code.activity.R;
import es.source.code.model.User;

/**
 * Created by Administrator on 2016/11/7.
 */

public class OrderFragment extends Fragment {
    private ListView listView;
    private OrderListAdapter adapter;
    private OrderListAdapter2 adapter1;
    private List<Map<String,Object>> list = new ArrayList<>();
    private List<Map<String,Object>> NotOrderedlist = new ArrayList<>();
    private List<Map<String,Object>> Orderedlist = new ArrayList<>();
    String s;
    User user=new User();
    void intNotOrderedList(){
        Map<String,Object> map = new HashMap<>();
        map.put("content", "什锦冷菜");
        map.put("Price", 15);
        map.put("beizhu","备注：  ");
       NotOrderedlist.add(map);
        map = new HashMap<>();

        map.put("content", "杂蔬菜冷盘");
        map.put("Price", 13);
        map.put("beizhu","备注：  ");
        NotOrderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "凉面冷盘");
        map.put("Price", 14);
        map.put("beizhu","备注：  ");
        NotOrderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "拌凉菜");
        map.put("Price", 10);
        map.put("beizhu","备注：  ");
        NotOrderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "麻婆豆腐");
        map.put("Price", 13);
        map.put("beizhu","备注：  ");
        NotOrderedlist.add(map);

    }
    void intiOrderedList(){
        Map<String,Object> map = new HashMap<>();
        map.put("content", "可乐鸡翅");
        map.put("Price", 21);
        map.put("beizhu","备注：  ");
        Orderedlist.add(map);
        map = new HashMap<>();

        map.put("content", "杂蔬菜冷盘");
        map.put("Price", 13);
        map.put("beizhu","备注：  ");
        Orderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "凉面冷盘");
        map.put("Price", 14);
        map.put("beizhu","备注：  ");
       Orderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "拌凉菜");
        map.put("Price", 10);
        map.put("beizhu","备注：  ");
        Orderedlist.add(map);
        map = new HashMap<>();
        map.put("content", "海鲜大咖");
        map.put("Price", 78);
        map.put("beizhu","备注：  ");
        Orderedlist.add(map);

    }

    private int type;
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    public static OrderFragment newInstance(int type) {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.orderlist,container,false);
        View view1=inflater.inflate(R.layout.items,container,false);
        intiOrderedList();
        intNotOrderedList();
        Button btn1=(Button) view1.findViewById(R.id.Buy);


       // Price.setText("95元");
        initView(view,view1);

        return view;
    }

    private void initView(View view,View view1) {
        listView = (ListView) view.findViewById(R.id.listView_id);


        Map<String,Object> map = new HashMap<>();
        switch (type) {
            case 1:
                int count=0;
                int sum=0;

                for (Map<String,Object>m:NotOrderedlist) {
                    for (String s : m.keySet()) {
                        if(s.equals("content"))count++;
                        if(s.equals("Price"))sum=sum+(int)m.get(s);

                    }
                }
                TextView Price=(TextView) view.findViewById(R.id.TotalPrice);
                TextView Num=(TextView) view.findViewById(R.id.Num);
                Button btn1=(Button) view.findViewById(R.id.btn_add);
                Price.setText("菜品总价： "+sum+"元");
                Num.setText("菜品总数  "+count+"个");
                btn1.setText("提交订单");
                final  Context context1;
                adapter1 = new OrderListAdapter2(NotOrderedlist, getActivity());

                listView.setAdapter(adapter1);
context1=adapter1.GetContext();
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       TextView FoodName=(TextView) view.findViewById(R.id.FoodName);
                       s=FoodName.getText().toString();
                        Intent intent=new Intent(context1,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context1.startActivity(intent);
                    }
                });
                break;
            case 2:
                 count=0;
                 sum=0;

                for (Map<String,Object>m:Orderedlist) {
                    for (String s : m.keySet()) {
                        if(s.equals("content"))count++;
                        if(s.equals("Price"))sum=sum+(int)m.get(s);

                    }
                }
                 Price=(TextView) view.findViewById(R.id.TotalPrice);
                Num=(TextView) view.findViewById(R.id.Num);
                 btn1=(Button) view.findViewById(R.id.btn_add);
                Price.setText("菜品总价： "+sum+"元");
                Num.setText("菜品总数  "+count+"个");
                btn1.setText("结账");
                final Context context;

                adapter = new OrderListAdapter(Orderedlist, getActivity());
                listView.setAdapter(adapter);
                context=adapter.GetContext();
                 user=FoodOrderView.user;
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(user!=null&&user.GetOldUser()==true)
                            {
                                Toast.makeText(context, "您好，老 顾客，本次你可享受 7 折优惠", Toast.LENGTH_SHORT).show();
                            }
                            else {}
                        }
                    });
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView FoodName=(TextView) view.findViewById(R.id.FoodName);
                        s=FoodName.getText().toString();
                        Intent intent=new Intent(context,FoodDetailed.class);
                        intent.putExtra("Name",s);
                        context.startActivity(intent);
                    }
                });
                break;
        }


    }
}

