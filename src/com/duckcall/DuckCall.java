package com.duckcall;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DuckCall {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        
		 System.setProperty("webdriver.chrome.driver", "//Users//Daksh-PC//Documents//workspace//DuckCall//Drivers//chromedriver");
	        WebDriver driver = new ChromeDriver();   
	        driver.get("https://accounts.google.com/SignUp");
	        // Read test data from excel file
	          // Method getDataFromExcel is defined in class Util
	    	
	    	String[][] testData = Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME);
		    String username, password;
		
		//Testing for all parameters stored in the Excel File
		  for (int i = 0; i < testData.length; i++) {
		    username = testData[i][0]; // get username
		    password = testData[i][1]; // get password
		    
		    //Setup Firefox driver
		   
		    
		    // Enter username
		    driver.findElement(By.id("Email")).clear();
		    driver.findElement(By.id("Email")).sendKeys(username);

		    // Enter Password
		    driver.findElement(By.id("Passwd")).clear();
		    driver.findElement(By.id("Passwd")).sendKeys(password);

		    // Click Login
		    driver.findElement(By.id("signIn")).click();
	       
	        /* Determine Pass Fail Status of the Script
	         * If login credentials are correct, Script will show the status Pass
	         * If login credentials are invalid, Script will show the stutus Failed 	    
	         */
		    
		    try
		    {
		       	
			String actualURL = driver.getCurrentUrl(); // get the current page URL
				
				if (actualURL.contains(Util.FILE_PATH)) { // Compare Error URL with Expected Error URL
				    System.out.println("Test case SS[" + i + "]: Failed"); 
				} else {
				    System.out.println("Test case SS[" + i + "]: Pass");
				}
			}    
		    catch (NoAlertPresentException Ex){ 
		    	
				    System.out.println(Ex);
				}
				
	        } 
	        
	}

}
