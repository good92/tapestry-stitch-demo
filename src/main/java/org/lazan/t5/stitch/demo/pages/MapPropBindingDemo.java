package org.lazan.t5.stitch.demo.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

public class MapPropBindingDemo {
	private static final String[] FIELD_NAMES = { "field1", "field2", "field3" };

	@Property
	private Map<String, String> fieldValues;
	
	@Persist(PersistenceConstants.FLASH)
	@Property
	private Map<String, String> submittedFieldValues;
	
	@Property
	private String fieldName;

	@SetupRender
	void setupRender() {
		fieldValues = new LinkedHashMap<String, String>();
		int i = 1;
		for (String name : FIELD_NAMES) {
			fieldValues.put(name, "Value " + i++);
		}
	}
	
	void onPrepareForSubmit() {
		fieldValues = new LinkedHashMap<String, String>();
	}
	
	void onSuccess() {
		submittedFieldValues = fieldValues;
	}
	
	public String[] getFieldNames() {
		return FIELD_NAMES;
	}
	
	@Persist(PersistenceConstants.FLASH)
	@Property
	private String sourceTab;
}
