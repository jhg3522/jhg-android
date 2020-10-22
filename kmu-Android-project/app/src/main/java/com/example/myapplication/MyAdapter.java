package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Product> mItem;

    public MyAdapter(ArrayList<Product> item) {
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
        ViewHolder viewHolder;

        if(convertView==null) {
            viewHolder= new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_product1, parent, false);

            ImageView productImg = convertView.findViewById(R.id.list_img);
            TextView productName = convertView.findViewById(R.id.list_name);
            TextView productCost = convertView.findViewById(R.id.list_cost);
            TextView productSize = convertView.findViewById(R.id.list_size);

            viewHolder.ProductImg = productImg;
            viewHolder.ProductName =productName;
            viewHolder.ProductCost = productCost;
            viewHolder.ProductSize = productSize;

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Product product = mItem.get(position);
        Glide.with(viewHolder.ProductImg).load(mItem.get(position).getImg()).into(viewHolder.ProductImg);
        viewHolder.ProductName.setText(product.getName());
        viewHolder.ProductCost.setText(product.getCost());
        viewHolder.ProductSize.setText(product.getSize());

        return convertView;
    }

    static class  ViewHolder{
        ImageView ProductImg ;
        TextView ProductName ;
        TextView ProductCost ;
        TextView ProductSize ;
    }

}
