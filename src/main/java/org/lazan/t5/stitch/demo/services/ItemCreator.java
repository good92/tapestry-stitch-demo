package org.lazan.t5.stitch.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.lazan.t5.stitch.demo.entities.Category;

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
	}
}
