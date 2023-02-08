package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MyToDoList> myToDoLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyToDoListDB myToDoListDB = MyToDoListDB.getInstance();

        if(myToDoListDB.checkNullMyToDoLists()){
            myToDoListDB.loadDummyData();
        }

        myToDoLists = myToDoListDB.getAllMyToDoList();

        Intent i = getIntent();
        int imgStatus;

        if (i.hasExtra("deleted")){
            myToDoLists.remove(i.getIntExtra("deleted",0));
        }

        if (i.hasExtra("updated")){
            imgStatus = getImageStatus(i.getStringExtra("status"));
            MyToDoList updatedList = new MyToDoList(i.getStringExtra("title"), i.getStringExtra("content"), imgStatus);
            int position = i.getIntExtra("position", 0);
            myToDoListDB.updateToDoList(position, updatedList);
        }

        if(i.hasExtra("created")){
            String newTitle = i.getStringExtra("title");
            String newContent = i.getStringExtra("content");
            String newStatus = i.getStringExtra("status");

            imgStatus = getImageStatus(newStatus);

            myToDoListDB.addNewToDoList(new MyToDoList(newTitle, newContent, imgStatus));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyToDoListAdapter myToDoListAdapter = new MyToDoListAdapter(myToDoLists);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myToDoListAdapter);
    }

    public int getImageStatus(String newStatus){
        if(newStatus.equalsIgnoreCase("urgent")){
            return android.R.drawable.ic_dialog_alert;
        }
        else if(newStatus.equalsIgnoreCase("reminder")){
            return android.R.drawable.ic_dialog_info;
        }

        return android.R.drawable.ic_dialog_dialer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_activity_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.newItemButton:
                Toast.makeText(this, "Time to Create New To Do List", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, CreateNewToDoList.class);
                startActivity(intent);
                return true;
            case R.id.exitButton:
                Toast.makeText(this, "Thank you for using this application", Toast.LENGTH_SHORT).show();
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}