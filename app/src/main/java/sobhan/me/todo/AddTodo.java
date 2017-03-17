package sobhan.me.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
    }

    public void savetodo(View v)
    {
        Toast.makeText(this, "Hey", Toast.LENGTH_LONG).show();
    }
}
