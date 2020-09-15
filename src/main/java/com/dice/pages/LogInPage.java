package com.dice.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dice.base.BasePageObject;

public class LogInPage extends BasePageObject<LogInPage>{
   private static final String URL = "https://www.dice.com/dashboard/login";
   
   private By emailField = By.xpath("//input[@id='email']");
   private By passwordField = By.xpath("//input[@id='password']");
   private By signInButton = By.xpath("//button[@type='submit']");
   private By errorMessage = By.xpath("//span[@data-automation-id='login-failure-help-message']");
	public LogInPage(WebDriver driver, Logger log) {
		super(driver,log);
		
	}
    public void openLogInPage() {
    	getPage(URL);
    }
    public void fillupEmailandPassword(String email, String password) {
    	log.info("Filling up mail and passwords");
    	type(email,emailField);
    	type(password,passwordField);
    }
    public  ProfilePage pushSignInButton() {
    	log.info("Clicking and signin button");
    	click(signInButton);
		return new ProfilePage(driver,log);
    	
    }
	public String getLogInMessage() {
		
		waitforVisibilityOf(errorMessage,10);
		// TODO Auto-generated method stub
		return getText(errorMessage);
	}
  
}
