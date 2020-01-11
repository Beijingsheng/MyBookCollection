package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    private Button continue_add;

    private ArrayList<Book> allBooks;

    private ArrayList<String> listData;

    private Intent receivedIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        continue_add = (Button) findViewById(R.id.btm_con_add);
        receivedIntent = getIntent();

        continue_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDataActivity.this, MainActivity.class));
            }
        });

        populateListView();
    }


    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        listData = new ArrayList<>();
        allBooks = new ArrayList<>();

        int isSearch = receivedIntent.getIntExtra("search",0);

        if (isSearch == 0) {
            while(data.moveToNext()){
                //get the value from the database in column 1
                //then add it to the ArrayList
                listData.add(data.getString(1));
                Book toAdd = new Book(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5));
                allBooks.add(toAdd);
            }
        } else {
            while(data.moveToNext()) {
                if (data.getString(1).equals(receivedIntent.getStringExtra("name")) ||
                    data.getString(2).equals(receivedIntent.getStringExtra("id")) ||
                    data.getString(3).equals(receivedIntent.getStringExtra("rank")) ||
                    data.getString(5).equals(receivedIntent.getStringExtra("pages"))) {
                    listData.add(data.getString(1));
                    Book toAdd = new Book(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5));
                    allBooks.add(toAdd);
                }
            }
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);


                    int index = listData.indexOf(name);
                    Book toGet = allBooks.get(index);
                    editScreenIntent.putExtra("bookid", toGet.getBookid());
                    editScreenIntent.putExtra("rank", toGet.getBookRank());
                    editScreenIntent.putExtra("memo",toGet.getBookMemo());
                    editScreenIntent.putExtra("pages", toGet.getBookPages());

                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
