package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyToDoListDetail extends AppCompatActivity {
    int position;
    Button editButton;
    EditText currTitle, currContent, currStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_do_list_detail);

        Intent i = getIntent();

        if(i.hasExtra("title") && i.hasExtra("content") && i.hasExtra("status")){
            String strTitle = i.getStringExtra("title");
            String strContent = i.getStringExtra("content");
            String strStatus = i.getStringExtra("status");

            position = i.getIntExtra("position",0);

            currTitle = findViewById(R.id.textTitle);
            currTitle.setText(strTitle);

            currContent = findViewById(R.id.textContent);
            currContent.setText(strContent);

            currStatus = findViewById(R.id.textStatus);
            currStatus.setText(strStatus);
        }

        editButton = findViewById(R.id.buttonEdit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editButton.getText().toString().equals("Edit")){
                   editButton.setText("Save");

                   currTitle.setInputType(InputType.TYPE_CLASS_TEXT);
                   currTitle.setEnabled(true);

                   currContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                   currContent.setEnabled(true);
                   currContent.setLines(5);

                   currStatus.setInputType(InputType.TYPE_CLASS_TEXT);
                   currStatus.setEnabled(true);
                }
                else{
                    Intent intent = new Intent(MyToDoListDetail.this, MainActivity.class);
                    intent.putExtra("title", currTitle.getText().toString());
                    intent.putExtra("content", currContent.getText().toString());
                    intent.putExtra("status", currStatus.getText().toString());
                    intent.putExtra("updated", 0);
                    intent.putExtra("position", position);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_to_do_list_detail, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteItem) {
            Toast.makeText(this, "This to do list has been deleted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("deleted", position);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}