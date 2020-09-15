
package com.dice;

import org.testng.annotations.Test;

import com.dice.base.BaseTest;

public class FirstTest extends BaseTest{

	@Test
	public void firstTestMethod() {
		// open dice.com
		driver.get("http://www.dice.com");
		System.out.println("Dice opeds.Test passes!");

	}

}
