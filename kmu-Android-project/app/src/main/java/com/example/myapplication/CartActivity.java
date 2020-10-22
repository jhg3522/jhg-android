package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Button home_btn = findViewById(R.id.cart_home_btn);
        Button buy_btn = findViewById(R.id.cart_buy_btn);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("/Item/");
        final DatabaseReference databaseReference2 = firebaseDatabase.getReference();
        final ArrayList<Product> item = new ArrayList<>();
        final ArrayList<Product> trans_item = new ArrayList<>();
        ImageButton trash_btn = findViewById(R.id.cart_trash_btn);
        final ListView listView = findViewById(R.id.cart_ListView);

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Product product : item) {
                    if (product.isCheck()) {
                        trans_item.add(product);
                    }
                }
                for (Product product : trans_item) {
                    String key = databaseReference2.child("Buy").push().getKey();
                    Map<String, Object> post = product.Product_Map();
                    Map<String, Object> child = new HashMap<>();
                    child.put("/Buy/" + key, post);
                    databaseReference2.updateChildren(child);
                }
                if(trans_item.isEmpty()){Toast.makeText(CartActivity.this,"선택된 상품이 없습니다.",Toast.LENGTH_SHORT).show();}
                else {
                    Intent intent = new Intent(CartActivity.this, BuyActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!item.get(position).isCheck()) Toast.makeText(CartActivity.this, position+1 +"번째 아이템 선택", Toast.LENGTH_SHORT).show();
                else Toast.makeText(CartActivity.this, position+1 +"번째 아이템 선택 해제", Toast.LENGTH_SHORT).show();
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

        trash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                item.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    item.add(product);
                }
                MyAdapter2 adapter = new MyAdapter2(item);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(mValueEventListener);

    }
}
