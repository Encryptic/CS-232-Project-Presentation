/******************
 * Chris Theberge
 * 
 * Assignment 3 -- Android adaptation
 * 
 * See 'Readme.txt' for details on future implementation ideas
 * 
 */

package com.codeforyou.shoppinglist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
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
	private final int ACTIVITY_SETTINGS = 1;

	DisplayableShoppingList list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = new DisplayableShoppingList();

		// For now, this list is really simple and just lists each of the items.
		//
		// In the future move to using a custom view in the table to make it
		// look better.
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(list.getAdapter(this));
	}

	@Override
	protected void onResume() {
		super.onResume();
		TextView budgetText = (TextView) findViewById(R.id.textBudget);
		budgetText.setText(String.format("Budget: $%,.2f", getBudget()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent addItemIntent = new Intent(this, SettingsActivity.class);
			startActivityForResult(addItemIntent, ACTIVITY_SETTINGS);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

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
					newItem.setIndividualItemCost(resultIntent.getDoubleExtra(
							NewItemActivity.PRICE_FIELD, 0.0));
					newItem.setItemQuantity(resultIntent
							.getIntExtra(NewItemActivity.QUANTIY_FIELD, 1));
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

	public void buttonAutoShopClicked(View v) {
		if (list.sumTotal() < 100.0) {
			showSimpleAlertDialog("You must have at least $100 in planned expenses to AutoShop");
			return;
		}

		AutoShopper shopper = new AutoShopper();

		ArrayList<ShoppingItem> autoShoppedItems = shopper.autoShop(list,
				getBudget());

		if (autoShoppedItems.size() == 0) {
			showSimpleAlertDialog("It's a mixed blessing really, there isn't anything cheap enough to buy.");
		} else {
			StringBuilder output = new StringBuilder();
			output.append("Your shopping list is:");

			Double totalCost = 0.0;
			
			// Add each item to the list on a new line.
			for (ShoppingItem item : autoShoppedItems) {
				output.append(String.format("\n%s", item));
				totalCost += item.getTotalItemCost();
			}

			output.append("\n\nYou can't buy:");
			for (ShoppingItem item : list.getAllItems()) {
				// Find all of the items that you can't buy right now.
				if (!autoShoppedItems.contains(item)) {
					output.append(String.format("\n%s", item));
				}
			}
			
			Double remainingBudget = getBudget() - totalCost;
			
			output.append(String.format("\n\nBank Account after shopping: %s", remainingBudget));

			showSimpleAlertDialog(output.toString());
		}
	}

	private void showSimpleAlertDialog(String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();
	}

	private double getBudget() {
		return getSharedPreferences(SettingsActivity.APPLICATION_SETTINGS_FILE, MODE_PRIVATE).getFloat(SettingsActivity.KEY_SETTING_BUDGET, 0.0f);
	}
}
