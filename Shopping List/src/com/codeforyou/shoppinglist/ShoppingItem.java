package com.codeforyou.shoppinglist;

public class ShoppingItem {
	
	private ShoppingPriority itemPriority;
	private String itemName;
	private double itemCost;
	private int itemQuantity;
	
	public ShoppingItem()
	{
		
	}
	
	public ShoppingItem(String name, ShoppingPriority priority, double cost){
		setIndividualItemCost(cost);
		setItemPriority(priority);
		setItemName(name);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getIndividualItemCost() {
		return itemCost;
	}
	
	public double getTotalItemCost() {
		return itemCost * itemQuantity;
	}
	
	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setIndividualItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	
	public ShoppingPriority getItemPriority() {
		return itemPriority;
	}

	public void setItemPriority(ShoppingPriority itemPriority) {
		this.itemPriority = itemPriority;
	}
	
	public void setItemQuantity(int quantity) {
		itemQuantity = quantity;
	}
	
	@Override
	public boolean equals(Object item) {
		if (item == this) {
			return (true);
		}
		
		// TODO: Test if this method works of determining if the object is even the correct type. 
		if (!(item instanceof ShoppingItem)){
			return (false);
		}
		
		ShoppingItem shoppingItem = (ShoppingItem)item;
		return shoppingItem.getItemName().equals(getItemName()) 
				&& shoppingItem.getIndividualItemCost() == getIndividualItemCost() 
				&& shoppingItem.getItemPriority().equals(getItemPriority()) 
				&& shoppingItem.getItemQuantity() == getItemQuantity(); 
	}
	
	@Override
	public String toString(){
		// Override toString to change the value displayed in the list view.
		//
		// We won't really need this once we get to a better system of displaying items.
		return String.format("%s: %s $%.2f %s", itemQuantity, itemName, itemCost, itemPriority);
	}
}
