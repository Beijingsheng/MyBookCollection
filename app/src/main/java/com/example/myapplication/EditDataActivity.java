package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete;
    private EditText editable_bookname;
    private EditText editable_bookid;
    private Spinner editable_bookRank;
    private EditText editable_bookPage;
    private EditText editable_bookMemo;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;
    private String selectedBookId;
    private String selectedBookRank;
    private String selectedBookMemo;
    private String selectedBookPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        editable_bookname = (EditText) findViewById(R.id.editable_bookname);
        editable_bookid = (EditText) findViewById(R.id.editable_bookid);
        editable_bookMemo = (EditText) findViewById(R.id.editable_bookMemo);
        editable_bookRank = (Spinner) findViewById(R.id.editable_bookRank);
        editable_bookPage = (EditText) findViewById(R.id.editable_bookPage);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //now get the book_id we passed as an extra
        selectedBookId = receivedIntent.getStringExtra("bookid");

        selectedBookMemo = receivedIntent.getStringExtra("memo");

        selectedBookRank = receivedIntent.getStringExtra("rank");

        selectedBookPage = receivedIntent.getStringExtra("pages");

        //set the text to show the current selected name

        editable_bookname.setText(selectedName);
        editable_bookid.setText(selectedBookId);
        editable_bookPage.setText(selectedBookPage);
        editable_bookMemo.setText(selectedBookMemo);
        if (selectedBookRank.equals("*")) editable_bookRank.setSelection(0);
        if (selectedBookRank.equals("* *")) editable_bookRank.setSelection(1);
        if (selectedBookRank.equals("* * *")) editable_bookRank.setSelection(2);





        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bname = editable_bookname.getText().toString();
                String bid = editable_bookid.getText().toString();
                String brank = editable_bookRank.getSelectedItem().toString();
                String bMemo = editable_bookMemo.getText().toString();
                String bPages = editable_bookPage.getText().toString();
                if(!bname.equals("") && !bid.equals("") && !brank.equals("") && !bMemo.equals("")
                        && !editable_bookPage.getText().toString().equals("")){
                    mDatabaseHelper.updateName(bname,selectedID,selectedName);
                    mDatabaseHelper.updateId(bid, selectedID, selectedBookId);
                    mDatabaseHelper.updateMemo(bMemo, selectedID, selectedBookMemo);
                    mDatabaseHelper.updatePages(bPages, selectedID, selectedBookPage);
                    mDatabaseHelper.updateRank(brank, selectedID, selectedBookRank);
                    Intent listDataActivity = new Intent (EditDataActivity.this, ListDataActivity.class);
                    startActivity(listDataActivity);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                mDatabaseHelper.deleteId(selectedID, selectedBookId);
                mDatabaseHelper.deleteMemo(selectedID, selectedBookMemo);
                mDatabaseHelper.deletePages(selectedID, selectedBookPage);
                mDatabaseHelper.deleteRank(selectedID, selectedBookRank);
                editable_bookname.setText("");
                toastMessage("removed from database");
                Intent listDataActivity = new Intent (EditDataActivity.this, ListDataActivity.class);
                startActivity(listDataActivity);
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