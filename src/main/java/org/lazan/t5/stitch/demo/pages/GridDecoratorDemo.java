package org.lazan.t5.stitch.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.dom.Element;
import org.lazan.t5.stitch.model.CellDecorator;
import org.lazan.t5.stitch.model.RowDecorator;

public class GridDecoratorDemo {
	public static class Item {
		public int value;
		public int valueTimes2;
		public int valueTimes10;
	}

	public List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 100; ++i) {
			int value = i + 1;
			
			Item item = new Item();
			item.value = value;
			item.valueTimes2 = value * 2;
			item.valueTimes10 = value * 10;
			
			items.add(item);
		}
		return items;
	}
	
	public RowDecorator getRowDecorator() {
		return new RowDecorator() {
			public void decorate(Element element, Object rowValue, int rowIndex) {
				Item item = (Item) rowValue;
				String script = String.format("alert('value=%s, rowIndex=%s')", item.value, rowIndex);
				element.attribute("onclick", script);
			}
		};
	}
	
	public CellDecorator getCellDecorator() {
		return new CellDecorator() {
			public void decorate(Element cellElement, Object rowObject, int rowIndex, String propertyName, int colIndex) {
				String color;
				if (rowIndex % 2 == 0) {
					color = colIndex % 2 == 0 ? "green" : "red";
				} else {
					color = colIndex % 2 == 0 ? "pink" : "blue";
				}
				cellElement.attribute("style", "background-color:" + color);
			}
		};
	}
}
