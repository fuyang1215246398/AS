package es.source.code.adapter3;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.adapter2.OrderListAdapter;
import es.source.code.adapter2.OrderListAdapter2;

/**
 * Created by Administrator on 2016/11/7.
 */

public class OrderFragment extends Fragment {
    private ListView listView;
    private OrderListAdapter adapter;
    private OrderListAdapter2 adapter1;
    private List<Map<String,Object>> list = new ArrayList<>();


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
        View view = inflater.inflate(R.layout.fooddetail,container,false);
        View view1=inflater.inflate(R.layout.items,container,false);

        initView(view,view1);

        return view;
    }

    private void initView(View view,View view1) {
        TextView tx1;
        TextView tx2;
        ImageView image=(ImageView) view.findViewById(R.id.image);
        final Button btn=(Button)view.findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn.getText().toString().equals("点菜"))
                {
                    btn.setText("退点");
                }
                else {
                    btn.setText("点菜");
                }
            }
        });
        Map<String,Object> map = new HashMap<>();
        switch (type) {
            case 1:
                 tx1=(TextView)view.findViewById(R.id.name);
                 tx2=(TextView)view.findViewById(R.id.price);
                 image.setImageResource(R.drawable.image1);
                tx1.setText("菜名为：  什锦冷菜");
                tx2.setText("价格为：  15元");
                break;
            case 2:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  杂蔬菜冷盘");
                tx2.setText("价格为：  13元");
                image.setImageResource(R.drawable.image2);
                break;
            case 3:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  凉面冷盘");
                tx2.setText("价格为：  14元");
                image.setImageResource(R.drawable.image3);
                break;
            case 4:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  拌凉菜");
                image.setImageResource(R.drawable.image4);
                tx2.setText("价格为：  10元");
                break;
            case 5:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  可乐鸡翅");
                tx2.setText("价格为：  21元");
                image.setImageResource(R.drawable.image5);
                break;
            case 6:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  烧羊肉");
                tx2.setText("价格为：  26元");
                image.setImageResource(R.drawable.image6);
                break;
            case 7:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  水煮肉片");
                tx2.setText("价格为：  19元");
                image.setImageResource(R.drawable.image7);
                break;
            case 8:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  宫保鸡丁");
                tx2.setText("价格为：  15元");
                image.setImageResource(R.drawable.image8);
                break;
            case 9:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  麻婆豆腐");
                tx2.setText("价格为：  13元");
                image.setImageResource(R.drawable.image9);
                break;
            case 10:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  海鲜面");
                tx2.setText("价格为：  11元");
                image.setImageResource(R.drawable.image10);
                break;
            case 11:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  海鲜粥");
                tx2.setText("价格为：  16元");
                image.setImageResource(R.drawable.image11);
                break;
            case 12:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  海鲜沙拉");
                tx2.setText("价格为：  12元");
                image.setImageResource(R.drawable.image12);
                break;
            case 13:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  海鲜大咖");
                tx2.setText("价格为：  78元");
                image.setImageResource(R.drawable.image13);
                break;
            case 14:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  烤大虾");
                tx2.setText("价格为：  8元");
                image.setImageResource(R.drawable.image14);
                break;
            case 15:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  果粒橙");
                tx2.setText("价格为：  4元");
                image.setImageResource(R.drawable.image15);
                break;
            case 16:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  可乐");
                tx2.setText("价格为：  3元");
                image.setImageResource(R.drawable.image16);
                break;
            case 17:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  雪碧");
                tx2.setText("价格为：  3元");
                image.setImageResource(R.drawable.image17);
                break;
            case 18:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  脉动");
                image.setImageResource(R.drawable.image18);
                tx2.setText("价格为：  4元");
                break;
            case 19:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  农夫山泉");
                image.setImageResource(R.drawable.image19);
                tx2.setText("价格为：  2元");
                break;
            case 20:
                tx1=(TextView)view.findViewById(R.id.name);
                tx2=(TextView)view.findViewById(R.id.price);
                tx1.setText("菜名为：  水晶葡萄");
                image.setImageResource(R.drawable.image20);
                tx2.setText("价格为：  3元");
                break;
        }


    }
}

