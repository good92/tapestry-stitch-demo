package org.lazan.t5.stitch.demo.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.lazan.t5.stitch.services.ProgressTaskManager;
import org.lazan.t5.stitch.services.ProgressTaskManagerImpl;
import org.lazan.t5.stitch.services.StitchModule;

@SubModule(StitchModule.class)
public class AppModule {
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
	}
	
	public static void bind(ServiceBinder binder) {
		binder.bind(ProgressTaskManager.class, ProgressTaskManagerImpl.class);
	}
}
