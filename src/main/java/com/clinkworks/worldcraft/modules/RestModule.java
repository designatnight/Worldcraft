package com.clinkworks.worldcraft.modules;

import java.util.HashMap;
import java.util.Map;

import com.clinkworks.worldcraft.services.QuestbookService;
import com.clinkworks.worldcraft.web.CorsFilter;
import com.clinkworks.worldcraft.web.metrics.HttpStatusCodeMetricResourceFilterFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class RestModule extends ServletModule{
	
	@Override
    protected void configureServlets() {
        bind(QuestbookService.class);

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

        Map<String, String> guiceContainerConfig = new HashMap<String, String>();
        
        guiceContainerConfig.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
            HttpStatusCodeMetricResourceFilterFactory.class.getCanonicalName());
        
        
        serve("/*").with(GuiceContainer.class, guiceContainerConfig);
        
        filter("/*").through(CorsFilter.class);
    }
}

