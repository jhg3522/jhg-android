package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Product> arrayList;
    private MyAdapter myAdapter;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.main_GridView);
        Button cart_btn = (Button)findViewById(R.id.main_cart_btn);
        arrayList = new ArrayList<>();
        myAdapter = new MyAdapter(arrayList);
        gridView.setAdapter(myAdapter);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Item");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터 받아오기
                arrayList.clear(); //초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Product item = snapshot.getValue(Product.class);
                    arrayList.add(item);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity",String.valueOf(error.toException()));
            }
        });

        cart_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}

/*

    @Override
    //onCreate 메서드 호출 이후에 자동 호출됨.
    //Activity가 정지에서 활동상태로 바뀔 때 호출.
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart 호출");
    }

    @Override
    //onStart 메서드가 호출된 이후에 자동 호출.
    //Activity가 일시 정지 되었다가 다시 돌아올 때 호출
    protected void onResume() {
        super.onResume();
        Log.d("test","onResume 호출");
    }

    @Override
    //Activity가 정지 상태가 되었다가 활동 상태로 돌아갈 때
    //onStart 메서드 전에 호출됨.
    protected void onRestart() {
        super.onRestart();
        Log.d("test","onRestart 호출");
    }

    @Override
    //Activity가 일시 정지 상태가 될때 호출됨.
    //화면상에 완전히 사라지거나 현재 화면 위에 작은 팝업 창 같은것이 나타날 때 호출.
    protected void onPause() {
        super.onPause();
        Log.d("test","onPause 호출");
    }

    @Override
    //Activity가 화면에서 사라질 때 호출된다.
    protected void onStop() {
        super.onStop();
        Log.d("test","onStop 호출");
    }

    @Override
    //현재 Activity가 수행이 완전히 종료되어 메모리상에서 제거될 때 호출된다.
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test","onDestroy 호출");
    }*/
