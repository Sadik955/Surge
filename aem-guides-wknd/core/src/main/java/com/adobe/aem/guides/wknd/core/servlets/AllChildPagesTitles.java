package com.adobe.aem.guides.wknd.core.servlets;



import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes = "cq:Page",
							selectors= {"add","sub","surge"},
							extensions= {"txt","json","xml"})
public class AllChildPagesTitles extends SlingAllMethodsServlet {

    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response)
            throws IOException {

        ResourceResolver resourceResolver = ((Resource) request).getResourceResolver();
        Resource parentResource = resourceResolver.getResource("/content/wknd/us/en/card");

        if (parentResource != null) {
            Iterable<Resource> childPages = parentResource.getChildren();

            for (Resource childPage : childPages) {
                String resourceType = childPage.getResourceType();

                // Replace "your/resource/type" with the desired resource type
                if (resourceType.equals(resourceType)) {
                    String pageTitle = childPage.getValueMap().get("jcr:title", String.class);
                    // You can use pageTitle in your further processing or output it to the response
                    // For example:
                    response.getWriter().println("Child Page Title: " + pageTitle);
                }
            }
        }
    }
}
