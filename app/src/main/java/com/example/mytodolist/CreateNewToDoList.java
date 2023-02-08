package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateNewToDoList extends AppCompatActivity {
    EditText textTitle, textContent, textStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_to_do_list);

        textTitle = findViewById(R.id.textInputTitle);
        textContent = findViewById(R.id.textInputStatus);
        textStatus = findViewById(R.id.textInputContent);
        Button submitBtn = findViewById(R.id.buttonSubmit);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTitle = textTitle.getText().toString();
                String strContent = textContent.getText().toString();
                String strStatus = textStatus.getText().toString();

                Intent intent = new Intent(CreateNewToDoList.this, MainActivity.class);
                intent.putExtra("title", strTitle);
                intent.putExtra("content", strContent);
                intent.putExtra("status", strStatus);
                intent.putExtra("created", 0);

                startActivity(intent);
            }
        });


    }
}