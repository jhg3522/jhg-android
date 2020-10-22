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


        final GridView gridView = findViewById(R.id.main_GridView);
        Button cart_btn = findViewById(R.id.main_cart_btn);
        Button buy_btn = findViewById(R.id.main_buy_btn);
        final ArrayList<Product> item = new ArrayList<>();
        final ArrayList<Product> trans_item = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(item);
        gridView.setAdapter(adapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        item.add(new Product("1","https://firebasestorage.googleapis.com/v0/b/kmu-mobile.appspot.com/o/clothes_03.jpeg?alt=media&token=39633375-8529-48f2-8459-22e0efcc874c","상의","15000","XL"));
        item.add(new Product("2","https://firebasestorage.googleapis.com/v0/b/kmu-mobile.appspot.com/o/clothes_02.jpeg?alt=media&token=9aecba39-593c-4a4c-8a17-4b99fa683d9c","하의","20000","31"));
        item.add(new Product("3","https://firebasestorage.googleapis.com/v0/b/kmu-mobile.appspot.com/o/cap_01.jpeg?alt=media&token=ad9904fe-efb1-4906-a1ac-28affe8f1c6a","모자","10000","M"));
        item.add(new Product("4","https://firebasestorage.googleapis.com/v0/b/kmu-mobile.appspot.com/o/shoes.jpeg?alt=media&token=49994885-ee4f-403a-a164-0b8b42617755","신발","30000","300"));


        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Product product : item) {
                    if (product.isCheck()) {
                        trans_item.add(product);
                    }
                }
                for (Product product : trans_item) {
                    String key = databaseReference.child("Buy").push().getKey();
                    Map<String, Object> post = product.Product_Map();
                    Map<String, Object> child = new HashMap<>();
                    child.put("/Buy/" + key, post);
                    databaseReference.updateChildren(child);
                }
                if(trans_item.isEmpty()){Toast.makeText(MainActivity.this,"선택된 상품이 없습니다.",Toast.LENGTH_SHORT).show();}
                else {
                    Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                    startActivity(intent);
                    finish();
                }
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
                if(!item.get(position).isCheck())Toast.makeText(MainActivity.this, position+1 +"번째 아이템 선택", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, position+1 +"번째 아이템 선택 해제", Toast.LENGTH_SHORT).show();
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

