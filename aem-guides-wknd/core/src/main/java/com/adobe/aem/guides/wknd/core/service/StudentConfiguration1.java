package com.adobe.aem.guides.wknd.core.service;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="Student Details",description="taking data from students")
public @interface StudentConfiguration1 {
	
	@AttributeDefinition(name="Student Name",
						type=AttributeType.STRING,
						description="Enter Student Name Here")
	public  String getStudentName() default "amit";
	
	
	@AttributeDefinition(name="Roll Number",
						type=AttributeType.INTEGER,
						description="Enter Your Roll Number")
	public int getRollNumber() default 2;
	
	
	@AttributeDefinition(name="Regular",
						type=AttributeType.BOOLEAN,
						description="Is Student Is Regular")
	public boolean getRegular() default true;
	
	@AttributeDefinition(name="Subjects",
			type=AttributeType.STRING,
			description="See your Subjects")
    public String[] getSubjects() default {"Maths","Physics","English"};
    
    
    @AttributeDefinition(name="Countries",
			type=AttributeType.STRING,
			description="Select Your Contries",
			options= {
					@Option(label="India" ,value="India"),
					@Option(label="USA",value = "USA"),
					@Option(label="Germany",value="Germany"),
					@Option(label="UK",value="UK")
			})
    public String getCountries() default "India" ;
	

}

