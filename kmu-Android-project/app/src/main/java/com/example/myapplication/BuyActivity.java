package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("/Buy/");
        Button home_btn = findViewById(R.id.buy_home_btn);
        Button buy_btn = findViewById(R.id.buy_buy_btn);
        final ArrayList<Product> item = new ArrayList<>();
        final MyAdapter2 adapter = new MyAdapter2(item);
        final ListView listView = findViewById(R.id.buy_ListView);
        final TextView textView = findViewById(R.id.buy_all_cost);
        final EditText editText1 = findViewById(R.id.buy_edit_address);
        final EditText editText2 = findViewById(R.id.buy_edit_name);
        final EditText editText3 = findViewById(R.id.buy_edit_phone);


        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().equals("") || editText2.getText().toString().equals("")
                        || editText3.getText().toString().equals("")){
                    Toast.makeText(BuyActivity.this,"배송 정보를 전부 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    databaseReference.removeValue();
                    Toast.makeText(BuyActivity.this, "상품 주문이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
                Intent intent = new Intent(BuyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                item.clear();
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
                listView.setAdapter(adapter);
                textView.setText(String.valueOf(totalPrice) + " 원 ");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(mValueEventListener);
    }
}
