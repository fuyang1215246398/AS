package es.source.code.adapter2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.model.User;

/**
 * Created by Administrator on 2016/9/23.
 */
public class OrderListAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Map<String, Object>> list;
    private Context context;
    User user=new User();
    public OrderListAdapter(List<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
        this.user=user;
    }
    public void setUser(User u){this.user=u;};
    public  Context GetContext(){return  context;}
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Map<String, Object> getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LinearLayout.inflate(context, R.layout.ordereditem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Map<String, Object> item = getItem(position);

        TextView food = (TextView) convertView.findViewById(R.id.FoodName);
        TextView price = (TextView) convertView.findViewById(R.id.Price);
        TextView Beizhu=(TextView) convertView.findViewById(R.id.Beizhu);
       /* Button Buy = (Button) convertView.findViewById(R.id.Buy);
        Buy.setTag(R.id.btn, position);
        Buy.setOnClickListener(this);*/
        food.setText(item.get("content") + "");
        food.setTag(R.id.tv, position);
        food.setOnClickListener(this);
        price.setText(item.get("Price") + "元");
        Beizhu.setText(item.get("beizhu")+"");
        price.setOnClickListener(this);
        Beizhu.setOnClickListener(this);
        return convertView;
    }

  @Override
    public void onClick(View view) {
      switch (view.getId()){
            case R.id.Buy:
                int b = (int) view.getTag(R.id.btn);
                Button btn=(Button)view.findViewById(R.id.Buy);
                if(btn.getText().equals("点菜")){
                    btn.setText("退点");
                    Toast.makeText(context,"点菜成功" , Toast.LENGTH_SHORT).show();}
                else{
                    btn.setText("点菜");
                    Toast.makeText(context,"退点成功" ,Toast.LENGTH_SHORT).show();
                }
                break;
            default:
               // int t = (int)view.getTag(R.id.tv);

                break;
        }

Intent intent=new Intent(context,FoodDetailed.class);
      context.startActivity(intent);


    }

    class ViewHolder{
        private TextView textView2;

        public ViewHolder(View view){
            textView2 = (TextView) view.findViewById(R.id.text2_id);
        }

    }
}