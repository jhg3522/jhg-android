package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Product> mItem;

    public MyAdapter(List<Product> item) {
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
    public View getView(int position, View convertView, ViewGroup parent) {//한칸에 들어가는 layout 정의
        ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder= new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_product, parent, false);

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
