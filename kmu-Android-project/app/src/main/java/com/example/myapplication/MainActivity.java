package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Product> item = new ArrayList<>();
        final ArrayList<Product> trans_item = new ArrayList<>();
        item.add(new Product("https://firebasestorage.googleapis.com/v0/b/kmu-mobile.appspot.com/o/clothes_01.jpeg","패딩","1000","M"));
        item.add(new Product("","옷","10000","L"));
        item.add(new Product("","긴팔","7000","M"));
        item.add(new Product("","바지","8000","L"));
        MyAdapter adapter = new MyAdapter(item);
        final GridView gridView = findViewById(R.id.main_GridView);
        gridView.setAdapter(adapter);
        Button cart_btn = findViewById(R.id.main_cart_btn);
        Button buy_btn = findViewById(R.id.main_buy_btn);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Product product :item){
                    if(product.isCheck()){
                        trans_item.add(product);
                    }
                }
                for(Product product : trans_item){
                    String key = databaseReference.child("Buy").push().getKey();
                    Map<String,Object> post = product.Product_Map();
                    Map<String,Object> child = new HashMap<>();
                    child.put("/Item/"+key,post);
                    databaseReference.updateChildren(child);
                }
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
                finish();

            }
        });

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Product product :item){
                    if(product.isCheck()){
                        trans_item.add(product);
                    }
                }
                for(Product product : trans_item){
                    String key = databaseReference.child("Item").push().getKey();
                    Map<String,Object> post = product.Product_Map();
                    Map<String,Object> child = new HashMap<>();
                    child.put("/Item/"+key,post);
                    databaseReference.updateChildren(child);
                }
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
                finish();

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position+1 +"번째 아이템 선택", Toast.LENGTH_SHORT).show();
                boolean newState =!item.get(position).isCheck();
                item.get(position).setCheck(newState);
                System.out.println(newState);
                if(item.get(position).isCheck()){
                    view.setBackgroundColor(Color.parseColor("#dddddd"));
                }
                else{
                    view.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }
}

