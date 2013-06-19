package org.lazan.t5.stitch.demo.model;

import org.lazan.t5.stitch.demo.entities.Category;
import org.lazan.t5.stitch.demo.entities.Item;


public class ItemTreeNode {
	private Item item;
	private Category category;
	private int childItemCount = 0;
	private int childCategoryCount = 0;
	
	public ItemTreeNode(Item item) {
		super();
		this.item = item;
	}

	public ItemTreeNode(Category category, int childItemCount, int childCategoryCount) {
		super();
		this.category = category;
		this.childItemCount = childItemCount;
		this.childCategoryCount = childCategoryCount;
	}
	
	public boolean isLeaf() {
		return category == null;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getChildItemCount() {
		return childItemCount;
	}

	public int getChildCategoryCount() {
		return childCategoryCount;
	}
}
