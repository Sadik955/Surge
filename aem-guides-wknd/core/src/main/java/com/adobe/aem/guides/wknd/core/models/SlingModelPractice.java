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
		ArrayList<SlingModelBean> obj=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		Resource childResource = resource.getChild("eaddress");
		
		                                        
		Iterator<Resource> item = childResource.listChildren();
		while(item.hasNext()) {
			
			Resource itemResource = item.next();
			
			//Nested Multi field Data Employee Tab
			String efullname= itemResource.getValueMap().get("efullname",String.class);
			String eaddr= itemResource.getValueMap().get("eaddr",String.class);
			
			slingModelBean.setEfullname(efullname);
			slingModelBean.setEaddr(eaddr);

		
			
			
			//To get Sub child Data
			
			Resource subChildResource = itemResource.getChild("einfo");
			Iterator<Resource> item1 = subChildResource.listChildren();
			while (item1.hasNext()) {
				Resource itemResource1 = item1.next();
			LOG.info("SubItem:"+itemResource1.getName());
				String eGender= itemResource1.getValueMap().get("egender",String.class);
			LOG.info("Gender:"+eGender);
				String eDoj = itemResource1.getValueMap().get("edoj",String.class);
			LOG.info("DOJ:"+eDoj);
				slingModelBean.setEgender(eGender);
				slingModelBean.setEdoj(eDoj);
				
				
				
		 //sub1 child Student Login Details
			Resource sub1ChildResource = itemResource1.getChild("studentlogin");
		LOG.info("Sub1Item:"+sub1ChildResource.getName());
			Iterator<Resource> item2 = sub1ChildResource.listChildren();
			while (item2.hasNext()) {
				Resource itemResource2 = item2.next();
				String userName = itemResource2.getValueMap().get("username",String.class);
				LOG.info("STUDENTUSERNAME:"+userName);
				String password = itemResource2.getValueMap().get("password",String.class);
				slingModelBean.setUsername(userName);
				slingModelBean.setPassword(password);
			LOG.info("PASSWORD:"+password);
				
				obj.add(slingModelBean);
				
			}
				
			}
		}
		return obj;
			
		}
	
	
	/*//einfoinfo
	public ArrayList<SlingModelBean> getMultiFiedEinfo() {
		ArrayList<SlingModelBean> obb=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		Resource child1 = resource.getChild("einfo");
		Iterator<Resource> listChildren = child1.listChildren();
		while(listChildren.hasNext()) {
			Resource next1 = listChildren.next();
			String egender = next1.getValueMap().get("egender",String.class);
			String edoj = next1.getValueMap().get("edoj",String.class);
			
			slingModelBean.setEgender(egender);
			slingModelBean.setEdoj(edoj);
			
			obb.add(slingModelBean);
		}
		return obb;
		}*/
		
	


}

	
	

