package com.adobe.aem.guides.wknd.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=OSGIConfigurationMethods.class)
@Designate(ocd=StudentConfiguration1.class)
public class OSGIConfigurationImpl1 implements OSGIConfigurationMethods {

	private String studentName;
	private int rollNumber;
	private boolean regular;
	private String[] subjects;
	private String countries;
	
	
	@Activate()
	protected void start(StudentConfiguration1 config) {
		studentName= config.getStudentName();
		rollNumber= config.getRollNumber();
		regular =config.getRegular();
		subjects=config.getSubjects();
		countries=config.getCountries();
	}
	@Override
	public String getStudentName() {
		// TODO Auto-generated method stub
		return  studentName;
	}

	@Override
	public int getRollNumber() {
		// TODO Auto-generated method stub
		return rollNumber;
	}

	@Override
	public boolean getRegular() {
		// TODO Auto-generated method stub
		return regular;
	}

	@Override
	public String[] getSubjects() {
		// TODO Auto-generated method stub
		return subjects;
	}

	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		return countries;
	}

}
