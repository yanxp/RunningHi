package com.example.paohi;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by yxp on 2015/8/29.
 */
public class rundataAdapter extends BaseAdapter{
    private FragmentActivity context;                        //上下文
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;           //视图容器
    public final class ListItemView{                //自定义控件集合
        public TextView digit;
        public TextView time;
        public TextView overtime;
        public ImageView star;

    }
    public rundataAdapter(FragmentActivity context, List<Map<String, Object>> listItems){
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;


    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.rundatalist, null);
            //获取控件对象
            listItemView.digit = (TextView)convertView.findViewById(R.id.one);
            listItemView.time = (TextView)convertView.findViewById(R.id.time);
            listItemView.overtime = (TextView)convertView.findViewById(R.id.overtime);
            listItemView.star = (ImageView)convertView.findViewById(R.id.star);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }
        listItemView.digit.setText((String) listItems.get(position).get("digit"));
        listItemView.time.setText((String) listItems.get(position).get("time"));
        listItemView.overtime.setText((String) listItems.get(position).get("overtime"));
        listItemView.star.setBackgroundResource((Integer) listItems.get(
                position).get("image"));
        return convertView;

    }
}
