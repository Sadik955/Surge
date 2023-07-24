package com.adobe.aem.guides.wknd.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=PageDeatilsConfigMethods.class)
@Designate(ocd=PageDetailsConfig1.class)

public class PageDetailsConfigImp1 implements PageDeatilsConfigMethods {

	private String pagePath;
	private String primaryType;
	private String date;
	
	@Activate()
	protected void start(PageDetailsConfig1 config) {
		String pagePath = config.getPagePath();
		String primaryType = config.getPrimaryType();
		String date = config.getDate();
	}

	@Override
	public String getPagePath() {
		// TODO Auto-generated method stub
		return pagePath;
	}

	@Override
	public String getPrimaryType() {
		// TODO Auto-generated method stub
		return primaryType;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	
}

