package com.strawberry.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.TextView;

import com.strawberry.delivery.data.DBhelper;
import com.strawberry.delivery.ui.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;


public class Order extends AppCompatActivity {
    private SQLiteDatabase mDb;
    private Map<String, Integer> restID=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        RecyclerView rv=(RecyclerView)findViewById(R.id.rvFood);
        createMap();
        DBhelper dBhelper=new DBhelper(this);
        mDb=dBhelper.getWritableDatabase();
        Cursor cursor=getAllfood();
        foodAdapter adapter=new foodAdapter(this,cursor);
        adapter.setOnItemClickListener(new foodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView count=(TextView)view.findViewById(R.id.tvFoodCount);
                count.setText(String.valueOf(Integer.parseInt(count.getText().toString())+1));

            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        };


    private Cursor getAllfood(){
        Intent intent=getIntent();
        String name=intent.getStringExtra("restaurantID");
        Log.d("test",name);
        int ID=restID.get(name);
        return mDb.rawQuery("SELECT _ID,foodName,foodPrice,foodUrl FROM RestFood WHERE restaurant="+ID,null);
    }
    private void insertData() {
        addNewFood("大麥克餐",130,"https://imgur.com/Itg7XRG.jpg",1);
        addNewFood("雙層牛肉吉事堡餐",145,"https://imgur.com/qaCXcZ4.jpg",1);
        addNewFood("板烤雞腿堡餐",120,"https://imgur.com/0VAmzrE.jpg",1);
        addNewFood("愛司檸果",60,"https://imgur.com/cd00jNJ.jpg",2);
        addNewFood("愛司紅茶",65,"https://imgur.com/OeYRuM1.jpg",2);
        addNewFood("熟成歐蕾",55,"https://imgur.com/e8iWaFm.jpg",2);
        addNewFood("棉被午茉綠",65,"https://imgur.com/vy5jYkG.jpg",3);
        addNewFood("日安紅珍珠歐蕾",65,"https://imgur.com/bWKSvhu.jpg",3);
    }
    private long addNewFood(String name,int price,String url,int rest){
        ContentValues cv = new ContentValues();
        cv.put("foodName",name);
        cv.put("foodPrice",price);
        cv.put("foodUrl",url);
        cv.put("restaurant",rest);
        return mDb.insert("RestFood",null,cv);
    }
    private void createMap(){

        restID.put("McDonald",1);
        restID.put("KEBUKE",2);
        restID.put("再睡5分鐘",3);
    }
}
