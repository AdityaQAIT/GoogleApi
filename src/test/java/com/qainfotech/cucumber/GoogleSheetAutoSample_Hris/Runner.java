package com.qainfotech.cucumber.GoogleSheetAutoSample_Hris;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = "StepDefination"
     )

public class Runner extends AbstractTestNGCucumberTests{
	
	

}
