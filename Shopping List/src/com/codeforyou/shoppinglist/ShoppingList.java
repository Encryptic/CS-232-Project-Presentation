package com.codeforyou.shoppinglist;

import java.util.ArrayList;

public abstract class ShoppingList implements IShoppingList {

	// Inner Class to hide the Linked List Implementation.
	class ShoppingLinkedList
	{
		class Node
		{
			private Node nextNode;
			private ShoppingItem currentItem;
			
			public Node getNextNode()
			{
				return nextNode;
			}
			
			public void setNextNode(Node next)
			{
				nextNode = next;
			}
			
			public ShoppingItem getCurrentItem()
			{
				return currentItem;
			}
			
			public void setCurrentItem(ShoppingItem item)
			{
				currentItem = item;
			}
		}
		
		private int count = 0;
		private Node head = null;
		private Node tail = null;
		
		private Node findNode(int index) {
			if (index < 0) {
				return (null);
			}
			
			Node currentItem = head;
			
			for (int i = 0; i <= index; i++)
			{
				if (i == index)
				{
					return (currentItem);
				}
				else if (currentItem == tail)
				{
					// If the current item is the tail, we're forced to break out
					break;
				}
				else
				{
					currentItem = currentItem.getNextNode();
				}
			}
			
			return (null);
		}
		
		public ShoppingItem getItemAt(int index)
		{
			Node node = findNode(index);
			if (node != null) {
				return node.getCurrentItem();
			}
			
			return (null);
		}
		
		public void setItemAt(int index, ShoppingItem item) throws Exception {
			Node node = findNode(index);
			if (node == null) {
				throw new Exception("Come up with a proper exception to throw here.");
			}
			
			node.setCurrentItem(item);
		}
		
		public void add(ShoppingItem item)
		{
			if (item == null) {
				throw new NullPointerException();
			}
			
			Node newTail = new Node();
			newTail.setCurrentItem(item);
			
			if (tail == null) {
				tail = newTail;
			}
			else {
				tail.setNextNode(newTail);
				tail = newTail;
			}
			
			if (head == null) {
				head = tail;
			}
			
			count++;
		}
		
		public void insert(int index, ShoppingItem item) throws Exception {
			// Check the preconditions.
			if (index < 0 || index > size()) {
				throw new Exception("Bad index.");
			}
			
			if (item == null) {
				throw new NullPointerException();
			}
			
			// Make a new node for the new shopping item.
			Node newItem = new Node();
			newItem.setCurrentItem(item);
			
			Node currentItem = findNode(index);
			Node nextItem = currentItem.getNextNode();
			
			// Update references to point to the new item.
			currentItem.setNextNode(newItem);
			newItem.setNextNode(nextItem);
			
			// Update the total count.
			count++;
		}
		
		public void removeAt(int index) throws Exception {
			// Check the preconditions.
			if (index < 0 || index > size()) {
				throw new Exception("Bad index.");
			}
	
			// 
			Node previousNode = findNode(index - 1);
			Node currentItem = previousNode == null ? head : previousNode.getNextNode();
			Node nextNode = currentItem.getNextNode();
			
			// Update the pointer to the head.
			if (currentItem == head) {
				head = nextNode;
			} else {
				previousNode.setNextNode(nextNode);
			}
			
			// Update the pointer to the tail.
			if (currentItem == tail ) {
				tail = previousNode;
			}
			
			// Update the total count.
			count--;
		}
		
		public int size() {
			return (count);
		}
		
		public ShoppingItem[] toArray(ShoppingItem[] array) {
			ShoppingItem[] ret;
			
			if (array == null || array.length < size())
			{
				array = new ShoppingItem[size()];
			}
			ret = array;
			
			// Create the return array.
			for (int i = 0; i < size(); i++) {
				ret[i] = getItemAt(i);
			}
			
			return (ret);
		}
	}
	
	// Protected to allow classes which inherit from this to be able to sort as required. 
	protected ShoppingLinkedList shoppingItems;

	public ShoppingList() {
		shoppingItems = new ShoppingLinkedList();
	}

	public void addItem(ShoppingItem item) {

		// Match any existing items to the ons that are already in our list.
		
		for (int i = 0; i < shoppingItems.size(); i++) {
			ShoppingItem existingItem = shoppingItems.getItemAt(i);
			
			if (existingItem.getItemName().equalsIgnoreCase(item.getItemName())) {
				// Update the existing item if it already exists
				existingItem.setIndividualItemCost(item.getIndividualItemCost());
				existingItem.setItemPriority(item.getItemPriority());
				return;
			}
		}
		
		shoppingItems.add(item);
		
		try {
			sort();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Returns the Sum Total of all of the items contained in the shopping list.
	public double sumTotal() {
		double total = 0;
		for (int i = 0; i < shoppingItems.size(); i++) {
			ShoppingItem item = shoppingItems.getItemAt(i);
			total += item.getTotalItemCost();
		}
		return total;
	}

	public ShoppingItem[] getByPriority(ShoppingPriority priority) {

		// Create a list of shopping items that only has the priority requested.
		ArrayList<ShoppingItem> subList = new ArrayList<ShoppingItem>();
		for (int i = 0; i < shoppingItems.size(); i++) {
			ShoppingItem item = shoppingItems.getItemAt(i);
			
			if (item.getItemPriority() == priority) {
				subList.add(item);
			}
		}

		return (subList.toArray(new ShoppingItem[subList.size()]));
	}
	
	public ShoppingItem getItemAt(int index) {
		return (shoppingItems.getItemAt(index));
	}

	public ShoppingItem[] getAllItems() {
		return shoppingItems.toArray(new ShoppingItem[shoppingItems.size()]);
	}
	
	public int size() {
		return (shoppingItems.size());
	}

	public void sort() throws Exception {
		// This code goes with the ArrayList
		//ShoppingItemComparator comparator = new ShoppingItemComparator();
		//Collections.sort(shoppingItems, comparator);
		
		
		// From: http://www.java-examples.com/java-bubble-sort-example
		/*
		 * In bubble sort, we basically traverse the array from first to
		 * array_length - 1 position and compare the element with the next one.
		 * Element is swapped with the next element if the next element is
		 * greater.
		 * 
		 * Bubble sort steps are as follows.
		 * 
		 * 1. Compare array[0] & array[1] 2. If array[0] > array [1] swap it. 3.
		 * Compare array[1] & array[2] 4. If array[1] > array[2] swap it. ... 5.
		 * Compare array[n-1] & array[n] 6. if [n-1] > array[n] then swap it.
		 * 
		 * After this step we will have largest element at the last index.
		 * 
		 * Repeat the same steps for array[1] to array[n-1]
		 */

		int n = shoppingItems.size();
		ShoppingItemComparator comparator = new ShoppingItemComparator();

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				ShoppingItem previousItem = shoppingItems.getItemAt(j - 1);
				ShoppingItem currentItem = shoppingItems.getItemAt(j);
				if (comparator.compare(previousItem, currentItem) > 0) {
					// swap the elements!
					shoppingItems.setItemAt(j - 1, currentItem);
					shoppingItems.setItemAt(j, previousItem);
				}
			}
		}
	}
}
