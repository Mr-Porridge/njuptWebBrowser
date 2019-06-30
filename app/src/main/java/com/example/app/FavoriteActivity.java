package com.example.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import com.example.app.entity.BookMark;

import java.util.ArrayList;
import java.util.Date;

public class FavoriteActivity extends Activity implements View.OnClickListener{

    private static final String BOOK_DB_NAME = "Bookmark";
    private static final int VERSION = 1;

    private DBOperator dbOperator;
    private SQLiteDatabase sqLiteDatabase;

    private ListView listView;
    ArrayList<String> listItem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        dbOperator = new DBOperator(this, "browserdatabase.db",
                null, VERSION);
        sqLiteDatabase = dbOperator.getWritableDatabase();

        //视图初始化
        initView();

    }

    private void initView() {
        listView = findViewById(R.id.favListView);

        /**
         * 测试接口BookMark bookMark = new BookMark();
        bookMark.setTitle("sad");
        bookMark.setUrl("sad");
        bookMark.setCategary("adasd");
        bookMark.setDate(new Date());
        bookMark.setId(5);
        bookMark.setFlag(1);
        insert(bookMark);*/
        queryInfo();
    }

    //查询
    public void queryInfo() {
        listItem = new ArrayList<>();
        BookMark bookMark = new BookMark();
        Cursor cursor = sqLiteDatabase.query(BOOK_DB_NAME, null,null,
                null, null, null, "ID desc", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                bookMark.setId(cursor.getInt(0));
                bookMark.setUrl(cursor.getString(2));
                bookMark.setTitle(cursor.getString(4));
                listItem.add(bookMark.getTitle());
            }
            try {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                        R.layout.array_adapter, listItem);
                listView.setAdapter(arrayAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            listItem.add("暂时没有收藏网页呢，嘤嘤嘤");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(arrayAdapter);
        }

        cursor.close();
        sqLiteDatabase.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
