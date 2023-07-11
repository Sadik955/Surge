package com.adobe.aem.guides.wknd.core.models;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.bean.SlingModelBean;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SlingModelPractice {
	
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
	
	public String getPath() {
		return resource.getPath();	
	}
	 
	
	public String getParent() {
		Resource parent=resource.getParent();
		String parentnodename=parent.getName();
		return parentnodename;
	}
	
	@ChildResource
	Resource address;
	public ArrayList<SlingModelBean> getMultiChildNodes() {
		
		ArrayList<SlingModelBean> object=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		                                        
		Iterator<Resource> itemResources = address.listChildren();
		while(itemResources.hasNext()) {
			
			Resource itemResource = itemResources.next();
			//To Get the Data
			String fullname= itemResource.getValueMap().get("fullname",String.class);
			String zipcode= itemResource.getValueMap().get("zipcode",String.class);
			String paddress= itemResource.getValueMap().get("paddress",String.class);
            //To Set the Data
			slingModelBean.setFullname(fullname);
			slingModelBean.setZipcode(zipcode);
			slingModelBean.setPaddress(paddress);
		
			object.add(slingModelBean);
			
			
		}
		return object;
	}
	
           //NestedField
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
			
		}
	
	
	
}
		
	



	
	

