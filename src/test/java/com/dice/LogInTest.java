package com.dice;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProvider;
import com.dice.pages.LogInPage;
import com.dice.pages.ProfilePage;

public class LogInTest extends BaseTest{
	@Test(priority = 1,groups = { "positive"})
	public void positiveLogInTest() {
		LogInPage loginPage = new LogInPage(driver, log);
		String expectedPageTitle = "Dashboard Home Feed | Dice.com";
		String correctProfileName = "Yan";
		// open dice page https://www.dice.com/dashboard/login
		loginPage.openLogInPage();
		//fill up email and password 
		loginPage.fillupEmailandPassword("guoyan0623@gmail.com", "Shanghai@0623");
		
		//push sign in button wait for profile page to load 
		ProfilePage profilepage = loginPage.pushSignInButton();
		//verification 
		profilepage.waitforProfilePageToLoad();
		// -verify title of the page is correct 
		
		log.info("verifications");
		String actualTitle = profilepage.getTitle();
		Assert.assertTrue(expectedPageTitle.contains(actualTitle),"Page title is not expected .\nExpected:" + expectedPageTitle + "\nActual: " + actualTitle);
		// - verify correct name on profile page
		Assert.assertTrue(profilepage.isCorrectProfileLoaded(correctProfileName), "profile name is not expected");
	}
	
	@Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class,priority = 2,groups = { "negative","broken"})
	public void negativeLogInTest(Map<String,String> testData) {
		String expectedErrorMessage = "Email and/or password incorrect.";
		String testNumber = testData.get("no");
		String email = testData.get("email");
		String password = testData.get("password");
		String description = testData.get("description");
		log.info("Test No #" + testNumber + "for " + description + "where\nemail:" + email + "\nPassword: "
				   + password);
		
		LogInPage loginPage = new LogInPage(driver, log);
		
		// open dice page https://www.dice.com/dashboard/login
		loginPage.openLogInPage();
		//fill up email and password 
		loginPage.fillupEmailandPassword(email, password);
		
		loginPage.pushSignInButton();
		
		String errorMessage = loginPage.getLogInMessage();
		Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
				"Error message is not expected .\nExpected:" + expectedErrorMessage + "\nActual: " + errorMessage);
	}

}
