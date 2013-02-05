package com.codeforyou.shoppinglist;

import java.util.ArrayList;

public class AutoShopper {
	
	// Constructor doesn't really need anything.
	public AutoShopper()
	{
		
	}
	
	public ArrayList<ShoppingItem> autoShop(ShoppingList list, double budget) {
		double sumTotal = 0;
		
		// There's a more elegant way to do this, but I need to save time for the moment.
		//
		// TODO: Cleanup shopping logic to reuse a single method for determining the sublists.
		// BUG: Right now, we're not going to buy the most high priority items. We need to sort the list first so we can
		// buy all of the cheap items first.
		ArrayList<ShoppingItem> budgetedItems = new ArrayList<ShoppingItem>();
		for(ShoppingItem item : list.getByPriority(ShoppingPriority.High))
		{
			if (sumTotal + item.getTotalItemCost() <= budget)
			{
				sumTotal += item.getTotalItemCost();
				budgetedItems.add(item);
			}
		}
		
		for(ShoppingItem item : list.getByPriority(ShoppingPriority.Medium))
		{
			if (sumTotal + item.getTotalItemCost() <= budget)
			{
				sumTotal += item.getTotalItemCost();
				budgetedItems.add(item);
			}
		}
		
		for(ShoppingItem item : list.getByPriority(ShoppingPriority.Low))
		{
			if (sumTotal + item.getTotalItemCost() <= budget)
			{
				sumTotal += item.getTotalItemCost();
				budgetedItems.add(item);
			}
		}
		
		return (budgetedItems);
	}
}
