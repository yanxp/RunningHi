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
    private FragmentActivity context;                        //������
    private List<Map<String, Object>> listItems;
    private LayoutInflater listContainer;           //��ͼ����
    public final class ListItemView{                //�Զ���ؼ�����
        public TextView digit;
        public TextView time;
        public TextView overtime;
        public ImageView star;

    }
    public rundataAdapter(FragmentActivity context, List<Map<String, Object>> listItems){
        this.context = context;
        listContainer = LayoutInflater.from(context);   //������ͼ����������������
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
            //��ȡlist_item�����ļ�����ͼ
            convertView = listContainer.inflate(R.layout.rundatalist, null);
            //��ȡ�ؼ�����
            listItemView.digit = (TextView)convertView.findViewById(R.id.one);
            listItemView.time = (TextView)convertView.findViewById(R.id.time);
            listItemView.overtime = (TextView)convertView.findViewById(R.id.overtime);
            listItemView.star = (ImageView)convertView.findViewById(R.id.star);
            //���ÿؼ�����convertView
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
