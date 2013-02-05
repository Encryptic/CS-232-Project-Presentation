package com.codeforyou.shoppingexample1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShoppingList extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        
        // For now, this list is really simple and just lists each of the items.
        ListView listView = (ListView) findViewById(R.id.shoppingListView);

        String[] array = new String[] { "Hello, World1", "Hello, World2", "CS 232" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data){
    	
    }
}
