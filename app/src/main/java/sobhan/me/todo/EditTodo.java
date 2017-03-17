package sobhan.me.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTodo extends AppCompatActivity {
    String messageData;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        Intent intentRequest = getIntent();
        messageData = intentRequest.getStringExtra("messageData");
        position = intentRequest.getIntExtra("position", -1);
        EditText editText = (EditText) findViewById(R.id.todo_content);
        editText.setText(messageData);
    }

    public void saveTodo(View v)
    {
        String content = ((EditText) findViewById(R.id.todo_content)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra("content", content);
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
