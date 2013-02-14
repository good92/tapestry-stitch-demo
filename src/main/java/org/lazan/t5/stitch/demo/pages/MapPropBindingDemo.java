package org.lazan.t5.stitch.demo.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.Translator;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.internal.translator.StringTranslator;

public class MapPropBindingDemo {
	private static final String[] FIELD_NAMES = { "field1", "field2", "field3" };
	private static final StringTranslator STRING_TRANSLATOR = new StringTranslator();
	
	@Property
	private Map<String, String> fieldValues;
	
	@Persist(PersistenceConstants.FLASH)
	@Property
	private Map<String, String> submittedFieldValues;
	
	@Property
	private String fieldName;

	void onActivate() {
		init();
	}
	
	void onPrepare() {
		init();
	}
	
	void onSuccess() {
		submittedFieldValues = fieldValues;
	}
	
	void init() {
		fieldValues = new LinkedHashMap<String, String>();
	}
	
	public String[] getFieldNames() {
		return FIELD_NAMES;
	}
	
	public Translator<String> getTranslator() {
		return STRING_TRANSLATOR;
	}
}
