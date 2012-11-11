package org.lazan.t5.stitch.demo.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.lazan.t5.stitch.services.StitchModule;

@SubModule(StitchModule.class)
public class AppModule {
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
	}
}
