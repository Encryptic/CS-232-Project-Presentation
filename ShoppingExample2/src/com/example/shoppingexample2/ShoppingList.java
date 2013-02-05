package com.example.shoppingexample2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ShoppingList {
	
	// Using this to get all the shopping items in order, by priority when displayed to the user.
	public class ShoppingItemComparator implements Comparator<ShoppingItem> {
	    @Override
	    public int compare(ShoppingItem item1, ShoppingItem item2) {
	        return item1.getItemPriority().compareTo(item2.getItemPriority());
	    }
	}
	
	private ArrayAdapter<ShoppingItem> adapter = null;
	private ArrayList<ShoppingItem> shoppingItems;

	public ShoppingList() {
		shoppingItems = new ArrayList<ShoppingItem>();
	}

	public void addItem(ShoppingItem item) {

		try {
			// Match any existing items to the ons that are already in our list.
			for (ShoppingItem existingItem : shoppingItems) {
				
				if (existingItem.getItemName().equalsIgnoreCase(item.getItemName())) {
					// Update the existing item if it already exists
					existingItem.setItemCost(item.getItemCost());
					existingItem.setItemPriority(item.getItemPriority());
					return;
				}
			}

			shoppingItems.add(item);
		} finally {
			// Seems sloppy, but we have to sort per requirement #3. 
			Collections.sort(shoppingItems, new ShoppingItemComparator());
			
			// Refresh the list to update the new
			adapter.notifyDataSetChanged();
		}
	}
	
	public double sumTotal() {
		double total = 0;
		for (ShoppingItem item : shoppingItems) {
			total += item.getItemCost();
		}
		return total;
	}
	
	public ArrayList<ShoppingItem> getByPriority(ShoppingPriority priority) {
		
		// Create a list of shopping items that only has the priority requested.
		ArrayList<ShoppingItem> subList = new ArrayList<ShoppingItem>();
		for(ShoppingItem item : shoppingItems)
		{
			if (item.getItemPriority() == priority)
			{
				subList.add(item);
			}
		}
		
		return (subList);
	}
	
	public ArrayList<ShoppingItem> getAllItems() {
		return (shoppingItems);
	}

	public ArrayAdapter<ShoppingItem> getAdapter(Context context) {
		// Treat the Adapter as though we should only have one. The first time we're called we'll create it
		// after that, return the existing one.
		
		// TODO: Move away from exposing the Adapter here. Use a custom adapter that creates a custom UI
		// which can show an icon for priority.
		if (adapter == null) {
			adapter = new ArrayAdapter<ShoppingItem>(context,
					android.R.layout.simple_list_item_1, shoppingItems);
		}
		return (adapter);
	}
}
