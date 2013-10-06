package com.example.mytodolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	private ArrayList<Todo> array;
	private ArrayAdapter<Todo> adapter;
	private TodosDataSource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new TodosDataSource(this);
        datasource.open();
        array = datasource.getAllTodos();
        
        adapter = new ArrayAdapter<Todo>(this,
        		android.R.layout.simple_list_item_1 , array);
        setListAdapter(adapter);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		    	if(v.getId() == R.id.button1){
		    		EditText eText = (EditText)findViewById(R.id.editText1);
		    		String text = eText.getText().toString();
		    		if (text.length() > 0) {
//		    			Todo t = new Todo(text);
		    			Todo todo = datasource.createTodo(text);
		    			adapter.add(todo);
		    			eText.setText("");
		    		}
		    	}
			}
		});
    }
    public void onListItemClick(ListView listView, View view, final int position, final long id){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm " + position + " " + array.get((int)id))
               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int innerId) {
                   }
               })
               .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int innerId) {
                       // User cancelled the dialog
                	   datasource.deleteTodo(array.get((int)id));
                	   adapter.remove(array.get((int)id));
                   }
               }).create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
