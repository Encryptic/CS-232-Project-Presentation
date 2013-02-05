package com.codeforyou.shoppinglist;

public interface IShoppingList {
	
	double sumTotal();
	void addItem(ShoppingItem item);
	ShoppingItem[] getAllItems();
	ShoppingItem[] getByPriority(ShoppingPriority priority);
}
