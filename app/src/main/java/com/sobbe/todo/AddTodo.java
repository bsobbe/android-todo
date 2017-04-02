package com.sobbe.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
    }

    public void saveTodo(View v)
    {
        EditText editTextContent = (EditText) findViewById(R.id.todo_content);
        String content = editTextContent.getText().toString();
        if (!content.equals("")) {
            Intent intent  = new Intent();
            intent.putExtra("content", content);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
