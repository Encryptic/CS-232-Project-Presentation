package com.codeforyou.shoppinglist;

import java.util.Comparator;

//Using this to get all the shopping items in order, by priority when displayed to the user.
//
//TODO: Stop utilizing this method, and find a way to use headings on the list 
public class ShoppingItemComparator implements Comparator<ShoppingItem> {
	@Override
	public int compare(ShoppingItem item1, ShoppingItem item2) {
		//
		// Changed Here priority has the highest precedence. If the two items
		// are the same priority, return the item that is more expensive first.
		//
		int priorityComparison = item1.getItemPriority().compareTo(item2.getItemPriority());
		if (priorityComparison == 0) 
		{
			return (Double.compare(item2.getIndividualItemCost(), item1.getIndividualItemCost()));
		}
		else
		{
			return (priorityComparison);
		}
	}
}
