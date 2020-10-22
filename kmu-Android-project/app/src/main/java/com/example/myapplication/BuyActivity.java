package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("/Buy/");
        Button home_btn = findViewById(R.id.buy_home_btn);
        final ArrayList<Product> item = new ArrayList<>();


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    item.add(product);
                }
                int totalPrice =0;
                for(Product product : item){
                    int product_price = Integer.parseInt(product.getCost());
                    totalPrice += product_price;
                    Log.d("total", String.valueOf(totalPrice));
                }
                MyAdapter2 adapter = new MyAdapter2(item);
                ListView listView = findViewById(R.id.buy_ListView);
                listView.setAdapter(adapter);
                TextView textView = findViewById(R.id.buy_all_cost);
                textView.setText(String.valueOf(totalPrice));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(mValueEventListener);

    }
}
