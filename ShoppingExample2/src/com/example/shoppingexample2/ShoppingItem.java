package com.example.shoppingexample2;

public class ShoppingItem {
	
	private ShoppingPriority itemPriority;
	private String itemName;
	private double itemCost;
	
	public ShoppingItem()
	{
		
	}
	
	public ShoppingItem(String name, ShoppingPriority priority, double cost){
		setItemCost(cost);
		setItemPriority(priority);
		setItemName(name);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	
	public ShoppingPriority getItemPriority() {
		return itemPriority;
	}

	public void setItemPriority(ShoppingPriority itemPriority) {
		this.itemPriority = itemPriority;
	}
	
	@Override
	public boolean equals(Object item) {
		if (item == this) {
			return (true);
		}
		
		// TODO: Find a way to check to make sure that an object can be casted. In C# I'd use the 'is' operator.
		// need to find the Java equivalent.
		
		ShoppingItem shoppingItem = (ShoppingItem)item;
		return shoppingItem.getItemName().equals(getItemName()) && shoppingItem.getItemCost() == getItemCost() && shoppingItem.getItemPriority().equals(getItemPriority()); 
	}
	
	@Override
	public String toString(){
		// Override toString to change the value displayed in the list view.
		//
		// We won't really need this once we get to a better system of displaying items.
		return String.format("%s $%.2f %s", itemName, itemCost, itemPriority);
		
	}
}
