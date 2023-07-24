package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.query.Query;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.service.PageDeatilsConfigMethods;
import com.day.cq.wcm.api.Page;

@Component(service=Servlet.class)
@SlingServletPaths(value= {"/bin/pagesDetails"})
public class PageDetailsConfigServlet extends SlingAllMethodsServlet{
	
	@Reference
	PageDeatilsConfigMethods servletConfig;
	
	@Override
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException {
		ResourceResolver resourceResolver = req.getResourceResolver();
		String pagePath=servletConfig.getPagePath();
		String primaryType=servletConfig.getPrimaryType();
		String date=servletConfig.getDate();
		String query="SELECT * FROM [" + primaryType + "] AS page WHERE ISDESCENDANTNODE(page,'" + pagePath
                + "') AND page.[jcr:created] >= CAST('" + date + "' AS DATE)";
		Iterator<Resource> pageResources = resourceResolver.findResources(query, Query.JCR_SQL2);
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		while (pageResources.hasNext()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			Resource next = pageResources.next();
			Page currentpage = next.adaptTo(Page.class);
			if(currentpage!=null) {
				job.add("Title",currentpage.getTitle());
				job.add("Path",currentpage.getPath());
				jab.add(job);
		
			}
			res.getWriter().write(jab.build().toString());
		}
	}

}
