package org.lazan.t5.stitch.demo.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.tree.TreeModel;
import org.hibernate.Session;
import org.lazan.t5.stitch.demo.model.ItemTreeNode;
import org.lazan.t5.stitch.demo.model.ItemTreeSource;
import org.lazan.t5.stitch.model.LazyTreeModel;

public class LazyTreeDemo {
	@Inject
	private Session session;
	
	public TreeModel<ItemTreeNode> getTreeModel() {
		ItemTreeSource source = new ItemTreeSource(session);
		return new LazyTreeModel<ItemTreeNode>(source, source);
	}
}
