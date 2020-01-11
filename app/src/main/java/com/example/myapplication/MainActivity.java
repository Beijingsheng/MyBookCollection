package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData, btnSearch;
    private EditText editName;
    private EditText editId;
    private EditText editPages;
    private EditText editMemo;
    private Spinner editRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.bookname);
        editId = (EditText) findViewById(R.id.bookid);
        editPages = (EditText) findViewById(R.id.bookPages);
        editMemo = (EditText) findViewById(R.id.bookMemo);
        editRank = (Spinner) findViewById(R.id.bookRank);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSearch = (Button) findViewById(R.id.search);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                String newId = editId.getText().toString();
                String newPage = editPages.getText().toString();
                String newRank = editRank.getSelectedItem().toString();
                String newMemo = editMemo.getText().toString();

                if (editName.length() != 0 && editId.length() != 0 && editPages.length() != 0 && editMemo.length() != 0) {
                    AddData(newName, newId, newRank, newMemo, newPage);
                    editName.setText("");
                    editId.setText("");
                    editMemo.setText("");
                    editPages.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(unused -> createGameClicked());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String id = editId.getText().toString();
                String page = editPages.getText().toString();
                String rank = editRank.getSelectedItem().toString();
                String memo = editMemo.getText().toString();

                Intent searchIntent = new Intent(MainActivity.this, ListDataActivity.class);
                searchIntent.putExtra("id", id);
                searchIntent.putExtra("name", name);
                searchIntent.putExtra("rank", rank);
                searchIntent.putExtra("memo", memo);
                searchIntent.putExtra("pages", page);
                searchIntent.putExtra("search",1);
                startActivity(searchIntent);
            }
        });
    }

    private void createGameClicked() {
        //Log.i("clicks","You Clicked register page");
        Intent listIntent = new Intent(this, ListDataActivity.class);
        listIntent.putExtra("search", 0);
        startActivity(listIntent);
    }

    public void AddData(String name, String id, String rank, String memo, String pages) {
        boolean insertData = mDatabaseHelper.addData(name, id, rank, memo, pages);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
