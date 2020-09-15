package com.dice.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dice.base.BasePageObject;

public class ProfilePage extends BasePageObject<ProfilePage>{
   private By editProfileButton = By.xpath("//span[@id='current-position']");
   private By submitButton = By.xpath("//button[@id='submitSearch-button']");
   private By profileContactNameText = By.xpath("//span[@id='contact-first-name']");
   public ProfilePage(WebDriver driver,Logger log) {
	   super(driver,log);
	   
   }
   
   public void waitforProfilePageToLoad() {
	   log.info("wait for Profile Page To Load");
	   waitforVisibilityOf(editProfileButton,10);
	   waitforVisibilityOf(submitButton,10);
   }
   public boolean isCorrectProfileLoaded(String correctProfileName){
	   if (getText(profileContactNameText).equals(correctProfileName)) {
		   return true;
	   }
	   return false;
   }
}
