package com.adobe.aem.guides.wknd.core.listeners;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.jackrabbit.spi.Event;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true,service=EventListener.class)
public class JCREventHandler implements EventListener {
	
	
	private static final Logger Log=LoggerFactory.getLogger(JCREventHandler.class);
	
	private Session session;
	
	@Reference
	SlingRepository slingRepository;
	
	@Activate
	public void activate() throws Exception {
		
		try {
			session=slingRepository.loginService("wkndsubservice", null);
			session.getWorkspace().getObservationManager().addEventListener(
					this, 
					Event.NODE_ADDED | Event.PROPERTY_ADDED,
					"/content/wknd/us/en",
					true, 
					null, 
					null,
					true);
			
		}
			catch(RepositoryException e){
				Log.info("\n Error while adding EventListener : {}",e.getMessage());
			
		}
		
	}
	

	public void onEvent(EventIterator eventIterator) {
		try {
			while(eventIterator.hasNext()) {
				Log.info("\n path : {}",eventIterator.nextEvent().getPath());
				
			}
			
		}
		catch(Exception e) {
			
			//Log.error("\n Error while processing event : {}",e.getMessage());
			
		}
		
		
	}

}
