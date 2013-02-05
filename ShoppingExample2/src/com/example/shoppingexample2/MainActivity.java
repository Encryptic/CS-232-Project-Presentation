package com.example.shoppingexample2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Found this in examples. Seems that you need to use a number to define
	// which activity
	// you are returning from. I guess 0 works for now?
	private final int ACTIVITY_NEWITEM = 0;

	ShoppingList list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = new ShoppingList();

		// For now, this list is really simple and just lists each of the items.
		//
		// In the future move to using a custom view in the table to make it
		// look better.
		ListView listView = (ListView)findViewById(R.id.listView1);
		listView.setAdapter(list.getAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
		super.onActivityResult(requestCode, resultCode, resultIntent);
		switch (requestCode) {
		case (ACTIVITY_NEWITEM): {
				if (resultCode == Activity.RESULT_OK) {
					ShoppingItem newItem = new ShoppingItem();
	
					newItem.setItemName(resultIntent
							.getStringExtra(NewItemActivity.NAME_FIELD));
					newItem.setItemCost(resultIntent.getDoubleExtra(
							NewItemActivity.PRICE_FIELD, 0.0));
					newItem.setItemPriority((ShoppingPriority) resultIntent
							.getSerializableExtra(NewItemActivity.PRIORITY_FIELD));
	
					list.addItem(newItem);
				}
				break;
			}
		}
	}

	public void addButtonClicked(View v) {
		Intent addItemIntent = new Intent(this, NewItemActivity.class);
		startActivityForResult(addItemIntent, ACTIVITY_NEWITEM);
	}
}
