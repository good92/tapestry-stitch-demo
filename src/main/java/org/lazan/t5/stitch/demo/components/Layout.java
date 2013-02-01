package org.lazan.t5.stitch.demo.components;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.alerts.AlertStorage;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet="context:bootstrap/css/bootstrap.css")
public class Layout {
	@SessionState(create=false)
	private AlertStorage alertsStorage;
	
	@Property
	private Map<String, String> pageLabelsByPage;
	
	@Property
	private Map.Entry<String, String> pageEntry;
	
	@Inject
	private ComponentResources resources;
	
	@SetupRender
	void setupRender() {
		pageLabelsByPage = new LinkedHashMap<String, String>();
		pageLabelsByPage.put("ProgressLinkDemo", "Progress Link");
		pageLabelsByPage.put("GalleryDemo", "Gallery");
		pageLabelsByPage.put("GridDecoratorDemo", "Grid Decorator");
		pageLabelsByPage.put("PDFDemo", "PDF Link");
	}
	
	public boolean isAlerts() {
		return (alertsStorage != null && !alertsStorage.getAlerts().isEmpty());
	}
	
	public String getTitle() {
		return "TODO: Title";
	}
	
	public String getPageClass() {
		return resources.getPageName().equalsIgnoreCase(pageEntry.getKey()) ? "active" : null;
	}
}
