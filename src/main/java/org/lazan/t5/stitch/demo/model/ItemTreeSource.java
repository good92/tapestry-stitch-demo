package org.lazan.t5.stitch.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ValueEncoder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.lazan.t5.stitch.demo.entities.Category;
import org.lazan.t5.stitch.demo.entities.Item;
import org.lazan.t5.stitch.model.LazyTreeModelSource;

public class ItemTreeSource implements LazyTreeModelSource<ItemTreeNode>, ValueEncoder<ItemTreeNode> {
	private final Session session;
	
	public ItemTreeSource(Session session) {
		super();
		this.session = session;
	}

	public String getLabel(ItemTreeNode value) {
		if (value.isCategory()) {
			return String.format("%s (%s)", value.getCategory().getName(), value.getCategory().getCategoryId());
		} else {
			return String.format("%s (%s)", value.getItem().getName(), value.getItem().getItemId());
		}
	}
	
	public boolean hasChildren(ItemTreeNode value) {
		return value.getChildCategoryCount() + value.getChildItemCount() > 0;
	}
	
	public List<ItemTreeNode> getChildren(ItemTreeNode value) {
		List<ItemTreeNode> children = new ArrayList<ItemTreeNode>();
		if (value.isCategory()) {
			children.addAll(findItemTreeNodes(value.getCategory(), null));
			for (Item item : value.getCategory().getItems()) {
				children.add(new ItemTreeNode(item));
			}
		}
		return children;
	}
	
	public boolean isLeaf(ItemTreeNode value) {
		return !value.isCategory();
	}

	public List<ItemTreeNode> getRoots() {
		return findItemTreeNodes(null, null);
	}
	
	public String toClient(ItemTreeNode value) {
		if (value.isCategory()) {
			return String.valueOf(value.getCategory().getCategoryId());
		}
		throw new IllegalStateException("Unexpected toClient call on a leaf node");
	}
	
	public ItemTreeNode toValue(String clientValue) {
		Long categoryId = Long.parseLong(clientValue);
		List<ItemTreeNode> nodes = findItemTreeNodes(null,  categoryId);
		if (nodes.size() == 1) {
			return nodes.get(0);
		}
		throw new IllegalStateException(String.format("%s nodes found, expecting 1", nodes.size()));
	}
	
	/**
	 * Avoid N+1 selects by doing a single query
	 */
	private List<ItemTreeNode> findItemTreeNodes(Category parentCategory, Long childCategoryId) {
		List<ItemTreeNode> children = new ArrayList<ItemTreeNode>();
		String hqlPrefix = 
			"select childCategory, count(grandChildCategory), count(item) " +
			"from Category childCategory " +
			"left outer join childCategory.childCategories as grandChildCategory " +
			"left outer join childCategory.items as item ";
		String hqlSuffix = "group by childCategory.categoryId, childCategory.name, childCategory.parentCategory";
		Query query;
		if (childCategoryId != null) {
			String hql = hqlPrefix + "where childCategory.categoryId = :childCategoryId " + hqlSuffix;
			query = session.createQuery(hql).setParameter("childCategoryId", childCategoryId);
		} else if (parentCategory != null) {
			String hql = hqlPrefix + "where childCategory.parentCategory = :parentCategory " + hqlSuffix;
			query = session.createQuery(hql).setParameter("parentCategory", parentCategory);
		} else {
			String hql = hqlPrefix + "where childCategory.parentCategory is null " + hqlSuffix;
			query = session.createQuery(hql);
		}
		List<Object[]> results = query.list();
		for (Object[] row : results) {
			Category childCategory = (Category) row[0];
			int grandChildCategoryCount = ((Number) row[1]).intValue();
			int itemCount = ((Number) row[2]).intValue();
			
			ItemTreeNode treeNode = new ItemTreeNode(childCategory, grandChildCategoryCount, itemCount);
			children.add(treeNode);
		}
		return children;
	}
}
