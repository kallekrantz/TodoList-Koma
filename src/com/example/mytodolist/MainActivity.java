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
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private ArrayList<Integer> array;
	private ArrayAdapter<Integer> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int size = 30;
        array = new ArrayList<Integer>();
        for(int i = 0; i < size; i++){
        	array.add(i);
        }
        adapter = new ArrayAdapter<Integer>(this,
        		android.R.layout.simple_list_item_1 , array);
        setListAdapter(adapter);
        setContentView(R.layout.activity_main);
    }
    public void onListItemClick(ListView listView, View view, final int position, final long id){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm " + position + " " + array.get((int)id))
               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int innerId) {
                       // FIRE ZE MISSILES!
                   }
               })
               .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int innerId) {
                       // User cancelled the dialog
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
