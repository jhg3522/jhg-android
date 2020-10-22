package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter2 extends BaseAdapter {
    private ArrayList<Product> mItem;

    public MyAdapter2(ArrayList<Product> item) {
        this.mItem = item;
    }

    @Override
    public int getCount() { //아이템의 개수
        return mItem.size();
    }

    @Override
    public Object getItem(int position) { //몇번째에 있는지 알려줌
        return mItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAdapter2.ViewHolder viewHolder;

        if(convertView==null) {
            viewHolder= new MyAdapter2.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_product2, parent, false);
            TextView ProductNum = convertView.findViewById(R.id.list_number);
            ImageView productImg = convertView.findViewById(R.id.list_img);
            TextView productName = convertView.findViewById(R.id.list_name);
            TextView productCost = convertView.findViewById(R.id.list_cost);
            TextView productSize = convertView.findViewById(R.id.list_size);
            viewHolder.ProductNum = ProductNum;
            viewHolder.ProductImg = productImg;
            viewHolder.ProductName =productName;
            viewHolder.ProductCost = productCost;
            viewHolder.ProductSize = productSize;

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (MyAdapter2.ViewHolder) convertView.getTag();
        }

        Product product = mItem.get(position);
        viewHolder.ProductNum.setText(product.getNum());
        viewHolder.ProductName.setText(product.getName());
        viewHolder.ProductCost.setText(product.getCost());
        viewHolder.ProductSize.setText(product.getSize());

        return convertView;
    }

    static class  ViewHolder{
        TextView ProductNum;
        ImageView ProductImg ;
        TextView ProductName ;
        TextView ProductCost ;
        TextView ProductSize ;
    }
}

