package com.adobe.aem.guides.wknd.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Page Details Config",
					   description="Retrives the page details based on the timestamp")
public @interface PageDetailsConfig1{
	
	@AttributeDefinition(
            name = "Page path",
            description = "Enter the page path.",
            type = AttributeType.STRING)
   public String getPagePath();

    @AttributeDefinition(
            name = "JCR Primary type",
            type = AttributeType.STRING
    )
   public String getPrimaryType();

    @AttributeDefinition(
            name = "Date",
            type = AttributeType.STRING)
    public String getDate();
}
	
