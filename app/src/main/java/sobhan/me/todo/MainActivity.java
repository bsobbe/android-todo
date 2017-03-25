package sobhan.me.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    int position;
    public final static int NEW_TODO_REQUEST_CODE = 1;
    public final static int EDIT_TODO_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditTodo.class);
                intent.putExtra("messageData", arrayList.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, EDIT_TODO_REQUEST_CODE);
            }
        });

        try {
            Scanner sc = new Scanner(openFileInput("Todo.txt"));
            while (sc.hasNext()) {
                String data = sc.nextLine();
                arrayList.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void newTodo(View v)
    {
        Intent intent =  new Intent(MainActivity.this, AddTodo.class);
        startActivityForResult(intent, NEW_TODO_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode ==  RESULT_OK) {
            String content = data.getStringExtra("content");
            arrayList.add(content);
            arrayAdapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            String content = data.getStringExtra("content");
            position = data.getIntExtra("position", -1);
            arrayList.remove(position);
            arrayList.add(position, content);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void onStop()
    {
        super.onStop();
        try {
            PrintWriter pw = new PrintWriter(openFileOutput("Todo.txt", Context.MODE_PRIVATE));
            for (String data : arrayList) {
                pw.println(data);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        try {
            PrintWriter pw = new PrintWriter(openFileOutput("Todo.txt", Context.MODE_PRIVATE));
            for (String data : arrayList) {
                pw.println(data);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

}
