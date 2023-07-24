package com.adobe.aem.guides.wknd.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class,},adapters = SlingModelPracticeImp.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SlingModelPractice implements SlingModelPracticeImp{
	
	private static final Logger LOG=LoggerFactory.getLogger(SlingModelPractice.class);
	
	@ValueMapValue
	public String name;
	
	public String getName() {
		return name;
	}
	
	
	@ValueMapValue
	public String image;
	
	public String getImage() {
		return image;
	}
	
	@ValueMapValue
	public String rollnumber;
	
	public String getrollnumber() {
		return rollnumber;
	}
	
	@ValueMapValue
	public String text;
	
	public String getText() {
		return text;
	}
	
	@ValueMapValue
	public String aadhar;
	
	public String getAadhar() {
		return aadhar;
	}
	/*public String getCretaed() {
		return JcrConstants.JCR_CREATED;
	}*/
	
	@ValueMapValue
	public String []country;
	
	public String []getCountry(){
		 return country;
	}
	
    @ValueMapValue
	public String ename;
    
    public String getEname(){
    	return ename;
    }
    
   @ValueMapValue
    public String eid;
     
   public String getEid() {
	   return eid;
   }
   
   @ValueMapValue
   public String eimage;
   public String getEimage() {
	   return eimage;
   }
   
	
	
	
	@Inject
	Resource resource;
	
	
	public String getResourceName() {
		return resource.getName();
	}
	
	public String getPath() {
		return resource.getPath();	
	}
	 
	
	public String getParent() {
		String parent=resource.getParent().getPath();
		
		
		return parent;
	}
	
/*	@ChildResource
	Resource address;
	
	public List<SlingModelBean> getMultiChildNodesMap(){

	    ArrayList<SlingModelBean> object=new ArrayList<SlingModelBean>();
		
		                                        
        try {
        	Resource child = resource.getChild("address");
        	if(child!=null) {
        		for(Resource item:child.getChildren()) {
        			String fullname= item.getValueMap().get("fullname",String.class);
        			String zipcode= item.getValueMap().get("zipcode",String.class);
        			String paddress= item.getValueMap().get("paddress",String.class);
        			
        			object.add(new SlingModelBean(item));
        		}
        	}
        	
			
		}
        catch(Exception e) {
        	
        }
		return object;
	}*/

	
	
	//Multi field Using Map
	@Override
	public List<Map<String,String>> getMultiChildNodesMap() {
		 List<Map<String,String>> object=new ArrayList<>();
		 try {
			 Resource child = resource.getChild("address");
			 if(child!=null) {
				 for(Resource item:child.getChildren()) {
					 Map<String,String> sadikMap=new HashMap<>();
					 sadikMap.put("fullname", item.getValueMap().get("fullname",String.class));
					 sadikMap.put("zipcode", item.getValueMap().get("zipcode",String.class));
					 sadikMap.put("paddress", item.getValueMap().get("paddress",String.class));
					 object.add(sadikMap);
				 }
			 }
		 }
		 catch(Exception e){ 
			e.getMessage();
			 
		 }
		return object;
		
		
		
		
	}
	
	
	
	//NestedMultifield using map
	
	
	public List<Map<String,String>> getNestedMultiFieldMap() {
		 List<Map<String,String>> object1=new ArrayList<>();
		 try {
			 Resource child = resource.getChild("eaddress");
			 if(child!=null) {
				 for(Resource item7:child.getChildren()) {
					 Map<String,String> kalyanMap=new HashMap<>();
					 kalyanMap.put("efullname", item7.getValueMap().get("efullname",String.class));
					 kalyanMap.put("eaddr", item7.getValueMap().get("eaddr",String.class));
					 
					 
					 //sub child
					 
					 Resource child2 = item7.getChild("einfo");
					 if(child2!=null) {
						 for(Resource item8:child2.getChildren()) {
							
							 kalyanMap.put("gender", item8.getValueMap().get("gender",String.class));
							
							 
					//Sub1 Child	
							 Resource child3 = item8.getChild("staffinfo");
							 if(child3!=null) {
								 for(Resource item9:child3.getChildren()) {
									
									 kalyanMap.put("doj", item9.getValueMap().get("doj",String.class));
									
							 
						 }
							 
							 }
					 }
					 object1.add(kalyanMap);
				 }
			 }
			
		 
				 
			 }
		 }
		 catch(Exception e){ 
			e.getMessage();
			 
		 }
		return object1;
	
	
	
	
	}
	
}

     
	/*//NestedField
	public ArrayList<SlingModelBean> getNestedMultiFieldNode() {
		ArrayList<SlingModelBean> ob=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		Resource childResource = resource.getChild("eaddress");
		
		                                        
		Iterator<Resource> item = childResource.listChildren();
		while(item.hasNext()) {
			
		
			Resource itemResource1 = item.next();
			
			//Nested Multi field Data Employee Tab
			String efullname= itemResource1.getValueMap().get("efullname",String.class);
			String eaddr= itemResource1.getValueMap().get("eaddr",String.class);
			slingModelBean.setEfullname(efullname);
			slingModelBean.setEaddr(eaddr);
			ob.add(slingModelBean);
			
			
                //To get Sub child Data Employee Info
			
			Resource subChildResource = itemResource1.getChild("einfo");
			Iterator<Resource> item1 = subChildResource.listChildren();
			while (item1.hasNext()) {
				Resource itemResource2 = item1.next();
			LOG.info("SubItem:"+itemResource2.getName());
				String eGender= itemResource2.getValueMap().get("gender",String.class);
			LOG.info("Gender:"+eGender);
			slingModelBean.setGender(eGender);
			
			
			Resource subChildResource1 = itemResource2.getChild("staffinfo");
			Iterator<Resource> item2 = subChildResource1.listChildren();
			while (item2.hasNext()) {
				Resource itemResource3 = item2.next();
				String doj = itemResource3.getValueMap().get("doj",String.class);
				slingModelBean.setDoj(doj);
				LOG.info("DOJ:"+doj);
				
				
				
				
			}
			}
			}
		
		return ob;
			
		}*/
	
	
	
	
	


		
	



	
	

