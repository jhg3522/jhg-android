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


        MyAdapter adapter = new MyAdapter(item);

        GridView listView = findViewById(R.id.cart_ListView);
        listView.setAdapter(adapter);
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
