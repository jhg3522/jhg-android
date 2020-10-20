package com.example.myapplication;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ArrayList<Product> item = new ArrayList<>();
        item.add(new Product("","패딩","1000","M"));
        item.add(new Product("","옷","10000","L"));
        item.add(new Product("","긴팔","7000","M"));
        item.add(new Product("","옷","50000","L"));
        item.add(new Product("","반팔","2000","M"));
        item.add(new Product("","옷","10000","L"));
        item.add(new Product("","바지","8000","L"));
        MyAdapter2 adapter = new MyAdapter2(item);
        GridView listView = findViewById(R.id.cart_ListView);
        listView.setAdapter(adapter);
    }
}
