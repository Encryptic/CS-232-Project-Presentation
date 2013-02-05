package com.codeforyou.shoppinglist;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class DisplayableShoppingList extends ShoppingList {

	class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {

		// More info on Base Adapters here: http://thinkandroid.wordpress.com/2010/01/13/custom-baseadapters/
		private ShoppingList list;
		
		public ShoppingListAdapter(Context context, ShoppingList list) {
			super(context, android.R.layout.simple_list_item_1);
			this.list = list;
		}
		
		@Override
		public int getCount() {
			return (list.size());
		}

		@Override
		public ShoppingItem getItem(int index) {
			return list.getItemAt(index);
		}

		@Override
		public long getItemId(int position) {
			return (position);
		}
	}
	
	private BaseAdapter adapter = null;
	
	// Constructs a Displayable Shopping List which has the ability to provide the android
	// adapter required for displaying the list of items.
	public DisplayableShoppingList() {
		super();
	}
	
	public BaseAdapter getAdapter(Context context) {
		// Treat the Adapter as though we should only have one. The first time we're called we'll create it
		// after that, return the existing one.
		
		// TODO: Move away from exposing the Adapter here. Use a custom adapter that creates a custom UI
		// which can show an icon for priority.
		if (adapter == null) {
			//adapter = new ArrayAdapter<ShoppingItem>(context,
			//		android.R.layout.simple_list_item_1, shoppingItems.toArray(array));
			adapter = new ShoppingListAdapter(context, this);
		}
		return (adapter);
	}
	
	@Override
	public void addItem(ShoppingItem item){
		super.addItem(item);
		
		// Refresh the list to update the new
		adapter.notifyDataSetChanged();
	}
}
