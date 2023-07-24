package com.adobe.aem.guides.wknd.core.models;

import java.util.List;
import java.util.Map;

public interface SlingModelPracticeImp {
	
	List<Map<String,String>> getMultiChildNodesMap();
	List<Map<String,String>> getNestedMultiFieldMap();

}
