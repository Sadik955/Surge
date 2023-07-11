package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;

@Component(service=Servlet.class)  //registering servlet
@SlingServletResourceTypes(resourceTypes={"wknd/components/page","foundation/components/redirect"},
							selectors={"add","sub","surge"},
							extensions={"txt","json","xml"})
@SlingServletPaths(value="/bin/demo/recent")
public class DSOSGISServiceExample extends SlingSafeMethodsServlet{
	private static final Logger LOG=LoggerFactory.getLogger(DSOSGISServiceExample.class);
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException {
		
		
		String pagePath=req.getParameter("pagePath");
		if(pagePath==null) {
			pagePath="/content/wknd/us/en/card";
		} 
		ResourceResolver rr=req.getResourceResolver();           //to all configuration and connectivity
		PageManager pageManager=rr.adaptTo(PageManager.class);   //adaptTo() : is responsible to convert resource to an object
		Page page= pageManager.getPage(pagePath);                //From Page manager we're getting pages and also perform CRUD operations
		Iterator<Page> childPages=page.listChildren(new PageFilter(),true);        //listChildern(): which will iterate the child pages of particular page
		
		JsonArrayBuilder jab=Json.createArrayBuilder();
		
		while(childPages.hasNext()) 
		{
			
			Page next =childPages.next();	
			JsonObjectBuilder job=Json.createObjectBuilder(); //JsonObjectBuilder is Responsible to store Json Object
			LOG.info("childPages:"+next.getTitle());
			job.add("Title", next.getTitle());  //getting title
			job.add("Path",next.getPath());     //getting path
			jab.add(job);                      //Adding to array object
			
		}
		res.getWriter().write(jab.build().toString());
	}

}
