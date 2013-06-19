package org.lazan.t5.stitch.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.lazan.t5.stitch.demo.entities.Category;
import org.lazan.t5.stitch.demo.entities.Item;

public class ItemCreator {
	private final Session session;
	
	public ItemCreator(Session session) {
		super();
		this.session = session;
	}

	public void createItemsAndCategories() {
		String[] categories = {
			"Vehicles/Cars/Sports Cars",
			"Vehicles/Cars/Sedans",
			"Vehicles/Cars/4x4s",
			"Vehicles/Boats/Speed Boats",
			"Vehicles/Boats/House Boats",
			"Vehicles/Boats/Cruise Ships",
			"Food/Meat",
			"Food/Fruit",
			"Food/Vegetables",
		};
		
		Map<String, Category> categoryMap = new HashMap<String, Category>();
		for (String categoryEntry : categories) {
			String[] hierarchy = categoryEntry.split("/");
			Category parentCategory = null;
			for (String categoryName : hierarchy) {
				Category category = categoryMap.get(categoryName);
				if (category == null) {
					category = new Category();
					category.setName(categoryName);
					category.setParentCategory(parentCategory);
					session.save(category);
					
					String parentName = parentCategory == null ? "null" : parentCategory.getName(); 
					System.out.println(String.format("Inserted %s %s %s", categoryName, category.getCategoryId(), parentName));
					
					categoryMap.put(categoryName, category);
				}
				parentCategory = category;
			}
		}
		
		addItem(categoryMap.get("Sports Cars"), "Mazda RX7", "foo");
		addItem(categoryMap.get("Sports Cars"), "Porsche 911", "foo");
		
		addItem(categoryMap.get("Cruise Ships"), "Royal Princess", "baz");
		addItem(categoryMap.get("Cruise Ships"), "Grand Princess", "aaa");
		addItem(categoryMap.get("Cruise Ships"), "Fairstar", "bbb");
	}

	private void addItem(Category category, String name, String description) {
		Item item = new Item();
		item.setCategory(category);
		item.setName(name);
		item.setDescription(description);
		
		session.save(item);
	}
}
