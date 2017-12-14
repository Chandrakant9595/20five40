package com.nadsoft.twentyfiveforty.TwentyFiveFortyCheckoutProcess;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class checkoutProcess {
  
  @Test
  public void testScripts() throws InterruptedException, AWTException {
	  
	  /*adminLogin();
	  addSupplier();
	  activeSupplier();
	  signAgrement();
	  adminLogout();*/
	  
	  supplierLogin();
	  //payPalRegistration();
	  //stripeAccount();
	  addLocation();
	  addItem();
	  supplierLogout();
	  
	  /*adminLogin();
	  addMember();
	  adminLogout();*/
	  
	  /*memberLogin();
	  checkoutStratfordWithPayPAl();
	  //checkoutStratfordWithPayPAlOne();
	  //otherButtonFunctionalityOnShopPage();
	  memberLogout();*/
	  
	  /*loginInFontSide();
	  memberOtherOPtion();
	  logoutFontSide();*/
	  
	  //guifterFunctionality();
  }
  
  public static WebDriver driver;
  public static Robot robot; 
  public static JavascriptExecutor jse;
 
  @BeforeClass
  public void testSetup(){
	  
	// To run scripts on chrome browser
	System.setProperty("webdriver.chrome.driver","/home/nadsoft/AutomationSoftware/ChromrDriver/chromedriver"); 
	driver = new ChromeDriver();
	  
	/*// To run scripts on firefox browser
	System.setProperty("webdriver.gecko.driver","/home/nadsoft/AutomationSoftware/firefox/geckodriver");
	driver = new FirefoxDriver();*/
	
	driver.manage().window().maximize();
  }
  
  @AfterClass
	public void tearDown() {
		//driver.quit();
	}
  
  public void adminLogin() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Admin login script");
	  
	  driver.get("http://dev.20five40.com/login");
	  Thread.sleep(2000);
	  
	  //check the validation of buttons
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(2000);
	  
	  //Capture the invalid msg and enter mail ID
	  String errorMSGEmailIDActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[1]")).getText();
	  String errorMSGEmailIDExpected = "Please enter a valid email address";
	  Assert.assertEquals(errorMSGEmailIDActual, errorMSGEmailIDExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).sendKeys("tushar@nadsoftdev.com");
	  Thread.sleep(1000);
	  
	  //password
	  String errorMSGPasswordActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[2]")).getText();
	  String errorMSGPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGPasswordActual, errorMSGPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //login
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(4000);
	  
	  //verify the login
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/admin/dashboard";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Admin login successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void adminLogout() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Admin logout script");
	  
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(5000);
	  
	  //logout button
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[3]/ul/li/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[3]/ul/li/ul/li[4]/a")).click();
	  Thread.sleep(1000);
	  
	  //verify the logout
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Admin logout successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void addSupplier() throws InterruptedException, AWTException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Add supplier script");
	  Thread.sleep(5000);
	  WebDriverWait wait = new WebDriverWait(driver, 2000);
	  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/section/aside/div/ul/li[4]/a")));
	  boolean status = element.isDisplayed();                                                 
	  
	  if(status){
		  //Manage account
		  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/a")).click();
		  Thread.sleep(2000);          
	  }else{
		  System.out.println("Link not found");
	  }
	  
	  //Add new user
	  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/ul/li[2]")).click();
	  Thread.sleep(7000);          
	  
	  //Save
	  driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[3]/div/button")).click();
	  Thread.sleep(2000);
	  
	  //First Name
	  String errorMSGEFnamectual = driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[1]/div[1]/div/label")).getText();
	  String errorMSGFnameExpected = "Please enter a first name";
	  Assert.assertEquals(errorMSGEFnamectual,errorMSGFnameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("f_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("f_name")).sendKeys("Chandrakant");
	  Thread.sleep(1000);
	  
	  //Last Name
	  String errorMSGELnamectual = driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[1]/div[2]/div/label")).getText();
	  String errorMSGLnameExpected = "Please enter a last name";
	  Assert.assertEquals(errorMSGELnamectual,errorMSGLnameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("l_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("l_name")).sendKeys("Shinde");
	  Thread.sleep(1000);
	  
	  //Email
	  String errorMSGEmailActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[3]/div/label")).getText();
	  String errorMSGEmailExpected = "Please enter a valid email address";
	  Assert.assertEquals(errorMSGEmailActual,errorMSGEmailExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("email")).sendKeys("chandrakant@nadsoftdev.com");
	  Thread.sleep(1000);
	  
	  //Password
	  String errorMSGPasswordActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[4]/div/label")).getText();
	  String errorMSGPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGPasswordActual,errorMSGPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //Confirm Password
	  String errorMSGConfirmPasswordActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[5]/div/label")).getText();
	  String errorMSGConfirmPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGConfirmPasswordActual,errorMSGConfirmPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("confirm_password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("confirm_password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //Mobile
	  driver.findElement(By.name("mobile")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("mobile")).sendKeys("9595455315");
	  Thread.sleep(1000);
	  
	  //User Type
	  driver.findElement(By.id("type")).click();
	  Thread.sleep(200);
	  Robot r = new Robot();
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Company Name 
	  driver.findElement(By.name("company_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("company_name")).sendKeys("Automation Company");
	  Thread.sleep(1000);
	  
	  //Select Company
	  driver.findElement(By.xpath(".//*[@id='select2-company-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Nadsoft Company-1");
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Expiry Days
	  driver.findElement(By.name("expiry_days")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("expiry_days")).sendKeys("aaa");
	  Thread.sleep(1000);
	 
	  //Address1
	  driver.findElement(By.name("address1")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("address1")).sendKeys("RM Road");
	  Thread.sleep(1000);
	  
	  //Expiry Days
	  String errorMSGExpDaysActual = driver.findElement(By.xpath(".//*[@id='add-user-pane']/div[1]/div/label")).getText();
	  String errorMSGExpDaysExpected = "Please enter only digits.";
	  Assert.assertEquals(errorMSGExpDaysActual,errorMSGExpDaysExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("expiry_days")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("expiry_days")).sendKeys("44");
	  Thread.sleep(1000);
	  
	  //Address2
	  driver.findElement(By.name("address2")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("address2")).sendKeys("TRP Stop");
	  Thread.sleep(1000);
	  
	  //Country
	  driver.findElement(By.xpath(".//*[@id='select2-c-name-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("India");
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //State
	  driver.findElement(By.xpath(".//*[@id='select2-state-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Maharashtra");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(4000);
	  
	  //City
	  driver.findElement(By.xpath(".//*[@id='select2-city-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Ratnagiri");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Zip
	  driver.findElement(By.name("zip")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("zip")).sendKeys("415612");
	  Thread.sleep(1000);
	  
	  //Timezone
	  driver.findElement(By.xpath(".//*[@id='select2-timezone-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Kolkata");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_profile']")).click();
	  Thread.sleep(5000);
	  
	  //verify
	  String actualres = driver.findElement(By.xpath("html/body/section/section/section/div/div/section/div/div/div/table/tbody/tr[1]/td[3]")).getText();
	  String expectesres = "Nadsoft Company-1";
	  Assert.assertEquals(actualres, expectesres);
	  
	  System.out.println("Supplier add successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void activeSupplier() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Active supplier script");
	  
	  //Dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(5000);
	  WebDriverWait wait = new WebDriverWait(driver, 2000);
	  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/section/aside/div/ul/li[4]/a")));
	  boolean status = element.isDisplayed();
	  
	  if(status){
		  //Manage account
		  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/a")).click();
		  Thread.sleep(2000);
	  }else{
		  System.out.println("Link not found");
	  }
	  
	  //User list
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[4]/ul/li[1]")).click();
	  Thread.sleep(2000);
	  
	  //active
	  driver.findElement(By.xpath("html/body/section/section/section/div/div/section/div/div/div/table/tbody/tr[1]/td[7]/span/button")).click();
	  Thread.sleep(2000);
	  
	  System.out.println("Supplier active successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void signAgrement() throws InterruptedException, AWTException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Sign Agreement script");
	  
	  //Manage agreement
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[6]/a")).click();
	  Thread.sleep(2000);
	  
	  //Setting
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[6]/ul/li[2]")).click();
	  Thread.sleep(4000);
	  
	  //Select Supplier:
	  driver.findElement(By.xpath(".//*[@id='select2-supplier_list-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("chandrakant");
	  Thread.sleep(1000);
	  Robot r = new Robot();
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  
	  //Company Commission
	  driver.findElement(By.id("company_commission")).clear();
	  Thread.sleep(500);
	  driver.findElement(By.id("company_commission")).sendKeys("40");
	  Thread.sleep(2000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='btn_agreement']")).click();
	  Thread.sleep(5000);
	  
	 
  }
  
  public void supplierLogin() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Supplier login script");
	  
	  driver.navigate().to("http://dev.20five40.com/login");
	  Thread.sleep(3000);
	  
	  //check the validation of buttons
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(2000);
	  
	  //Capture the invalid msg and enter mail ID
	  String errorMSGEmailIDActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[1]")).getText();
	  String errorMSGEmailIDExpected = "Please enter a valid email address";
	  Assert.assertEquals(errorMSGEmailIDActual, errorMSGEmailIDExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).sendKeys("nadsoft.test02@gmail.com");
	  Thread.sleep(1000);
	  
	  //password
	  String errorMSGPasswordActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[2]")).getText();
	  String errorMSGPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGPasswordActual, errorMSGPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //login
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(2000);
	  
	  //verify the login
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/supplier/dashboard";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Supplier login successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void supplierLogout() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Supplier logout script");
	  
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(5000);
	  
	  //logout button
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[2]/ul/li/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[2]/ul/li/ul/li[3]/a")).click();
	  Thread.sleep(1000);
	  
	  //verify the logout
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Supplier logout successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void payPalRegistration() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("PayPal Registration script");
	  
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(3000);
	  
	  //PayPal link
	  driver.findElement(By.xpath("html/body/section/section/section/div[2]/div/section/div/div/p[3]/a")).click();
	  Thread.sleep(3000);
	  
	  //page down
	  jse = (JavascriptExecutor) driver;
	  jse.executeScript("window.scrollBy(0,1000)", "");
	  Thread.sleep(2000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_profile']")).click();
	  Thread.sleep(2000);
	  
	  //page up
	  jse = (JavascriptExecutor) driver;
	  jse.executeScript("window.scrollBy(0,-1000)", "");
	  Thread.sleep(2000);
	  
	  //PayPal Email
	  driver.findElement(By.name("paypal_email")).clear();
	  Thread.sleep(1000);
	  driver.findElement(By.name("paypal_email")).sendKeys("deepak@nadsoftdev.com");
	  Thread.sleep(1000);
	  
	  //page down
	  jse = (JavascriptExecutor) driver;
	  jse.executeScript("window.scrollBy(0,1000)", "");
	  Thread.sleep(2000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_profile']")).click();
	  Thread.sleep(2000);
	  
	  System.out.println("PayPal Registration successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void stripeAccount() throws InterruptedException, AWTException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Stripe account Registration script");
	  Thread.sleep(2000);
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(3000);
	  
	  //Account details
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[5]/a")).click();
	  Thread.sleep(2000);
	  
	  //Create account
	  driver.findElement(By.xpath(".//*[@id='main-content']/section/div/div/section/div/div/div/a")).click();
	  Thread.sleep(4000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='btnsbSuppSetting']")).click();
	  Thread.sleep(2000);
	  
	  //DOB
	  WebElement dateOfBirth = driver.findElement(By.id("dob"));
	  dateOfBirth.sendKeys("1990-10-10");
	  dateOfBirth.sendKeys(Keys.TAB);
	  Thread.sleep(1000);
	  
	  //SSN
	  String errorMSGSSNActual = driver.findElement(By.xpath(".//*[@id='supplier_account']/fieldset[1]/div[3]/div/label[2]")).getText();
	  String errorMSGSSNExpected = "This field is required.";
	  Assert.assertEquals(errorMSGSSNActual, errorMSGSSNExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("ssn")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("ssn")).sendKeys("000000000");
	  Thread.sleep(1000);
	  
	  //Your business type
	  driver.findElement(By.id("business_type")).click();
	  Thread.sleep(200);
	  Robot r = new Robot();
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Routing Number
	  String errorMSGRoutingNoActual = driver.findElement(By.xpath(".//*[@id='supplier_account']/fieldset[2]/div[1]/div/label[2]")).getText();
	  String errorMSGRoutingNoExpected = "This field is required.";
	  Assert.assertEquals(errorMSGRoutingNoActual, errorMSGRoutingNoExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("routing_number")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("routing_number")).sendKeys("110000000");
	  Thread.sleep(1000);
	  
	  //Account Number
	  String errorMSGAccountNoActual = driver.findElement(By.xpath(".//*[@id='supplier_account']/fieldset[2]/div[2]/div/label[2]")).getText();
	  String errorMSGAccountNoExpected = "This field is required.";
	  Assert.assertEquals(errorMSGAccountNoActual, errorMSGAccountNoExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("account_number")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("account_number")).sendKeys("000123456789");
	  Thread.sleep(1000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='btnsbSuppSetting']")).click();
	  Thread.sleep(2000);
	  
	  System.out.println("Stripe account create successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void addLocation() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Stripe account Registration script");
	  Thread.sleep(2000);
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(3000);
	  
	  //Manage location
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[2]/a")).click();
	  Thread.sleep(3000);
	  
	  //Add new
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[2]/ul/li[2]")).click();
	  Thread.sleep(3000);
	  
	  //Submit 
	  driver.findElement(By.xpath(".//*[@id='submit']")).click();
	  Thread.sleep(3000);
	  
	  //Store Name
	  String errorMSGAStoreNameActual = driver.findElement(By.xpath(".//*[@id='locationForm']/div[1]/div/label[2]")).getText();
	  String errorMSGStoreNameExpected = "This field is required.";
	  Assert.assertEquals(errorMSGAStoreNameActual, errorMSGStoreNameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("store")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("store")).sendKeys("CCD");
	  Thread.sleep(1000);
	  
	  //Location
	  String errorMSGALocationActual = driver.findElement(By.xpath(".//*[@id='locationForm']/div[2]/div/label[2]")).getText();
	  String errorMSGLocationExpected = "This field is required.";
	  Assert.assertEquals(errorMSGALocationActual, errorMSGLocationExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("location")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("location")).sendKeys("Chintamani Park, Chintamani Park Road, Tukaram Nagar, Kharadi, Pune, Maharashtra 411014, India");
	  Thread.sleep(1000);
	  
	  //Submit 
	  driver.findElement(By.xpath(".//*[@id='submit']")).click();
	  Thread.sleep(5000);
	  
	  //verification
	  String actualres = driver.findElement(By.xpath("html/body/section/section/section/div/div/section/div[2]/div/div/table/tbody/tr/td[3]")).getText();
	  String expectesres = "Chintamani Park, Chintamani Park Road, Tukaram Nagar, Kharadi, Pune, Maharashtra 411014, India";
	  Assert.assertEquals(actualres, expectesres);
	  
	  System.out.println("Location add successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void addItem() throws InterruptedException, AWTException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Add item script");
	  Thread.sleep(2000);
	  //dashboard
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[1]/a")).click();
	  Thread.sleep(3000);
	  
	  //manage item
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //add item 
	  driver.findElement(By.xpath(".//*[@id='nav-accordion']/li[3]/ul/li[2]")).click();
	  Thread.sleep(4000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_item']")).click();
	  Thread.sleep(4000);
	  
	  //Item Name 
	  String errorMSGItemNameActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[1]/div[1]/div/div/label[2]")).getText();
	  String errorMSGItemNameExpected = "Please enter Item name";
	  Assert.assertEquals(errorMSGItemNameActual, errorMSGItemNameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("itm_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("itm_name")).sendKeys("MRF Bat");
	  Thread.sleep(1000);
	  
	  //Category
	  String errorMSGCategoryActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[1]/div[2]/div/div/label[2]")).getText();
	  String errorMSGCategoryExpected = "Please Select Category";
	  Assert.assertEquals(errorMSGCategoryActual, errorMSGCategoryExpected);
	  Thread.sleep(200);
	  driver.findElement(By.xpath(".//*[@id='select2-ct_name-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Breakfast");
	  Thread.sleep(2000);
	  Robot r = new Robot();
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Original Price
	  String errorMSGOPriceActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[2]/div[1]/div/div/label[2]")).getText();
	  String errorMSGOPriceExpected = "Please enter Original Price";
	  Assert.assertEquals(errorMSGOPriceActual, errorMSGOPriceExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("rprice_itm")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("rprice_itm")).sendKeys("5");
	  Thread.sleep(1000);
	  
	  //Selling Price
	  String errorMSGSPriceActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[2]/div[2]/div/div/label[2]")).getText();
	  String errorMSGSPriceExpected = "Please enter Selling Price";
	  Assert.assertEquals(errorMSGSPriceActual, errorMSGSPriceExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("sprice_itm")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("sprice_itm")).sendKeys("3");
	  Thread.sleep(1000);
	  
	  //Location
	  String errorMSGLocationActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[3]/div[1]/div/div/label[2]")).getText();
	  String errorMSGLocationExpected = "Please enter a location";
	  Assert.assertEquals(errorMSGLocationActual, errorMSGLocationExpected);
	  Thread.sleep(200);
	  driver.findElement(By.xpath(".//*[@id='select2-location_id-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("CCD");
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Quantity
	  String errorMSGQuanActual = driver.findElement(By.xpath(".//*[@id='itemForm']/div[3]/div[2]/div/div/label[2]")).getText();
	  String errorMSGQuanExpected = "Please enter a quantity";
	  Assert.assertEquals(errorMSGQuanActual, errorMSGQuanExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("quantity_itm")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("quantity_itm")).sendKeys("10");
	  Thread.sleep(1000);
	  
	  //Short Description
	  driver.findElement(By.name("shrt_desc")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("shrt_desc")).sendKeys("MRF Bat");
	  Thread.sleep(1000);
	  
	  //Keywords / Tags
	  driver.findElement(By.id("tagsinput_tag")).clear();
	  Thread.sleep(200);
      driver.findElement(By.id("tagsinput_tag")).sendKeys("MRF");
	  Thread.sleep(1000);
	  
	  //Description
	  driver.findElement(By.name("desc_itm")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("desc_itm")).sendKeys("MRF Bat for playing cricket. It is sachin tendulake brand. India Sachin Tendulkar, India Rohit Sharma, India Gautam Gambhir MRF Bat for playing cricket. It is sachin tendulake brand. India Sachin Tendulkar, India Rohit Sharma, India Gautam Gambhir");
	  Thread.sleep(1000);
	  
	  //Item image
	  driver.findElement(By.xpath(".//*[@id='itemForm']/div[5]/div[2]/div/div/div/div[2]")).click();
    	driver.switchTo()          
	            .activeElement()
	            .sendKeys(
	                    "/home/nadsoft/Desktop/pic/mrf.jpg");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Thread.sleep(5000);
	    r.keyPress(KeyEvent.VK_ESCAPE);
	    r.keyRelease(KeyEvent.VK_ESCAPE);
	    Thread.sleep(2000); 
	  
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_item']")).click();
	  Thread.sleep(4000);
	  
	  //verification
	  String actualres = driver.findElement(By.xpath("html/body/section/section/section/div/div/section/div[2]/div/div/table/tbody/tr/td[2]")).getText();
	  String expectesres = "Breakfast";
	  Assert.assertEquals(actualres, expectesres);
	  
	  System.out.println("Item add successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void addMember() throws InterruptedException, AWTException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Add Member script");
	  Thread.sleep(5000);
	  WebDriverWait wait = new WebDriverWait(driver, 2000);
	  WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/section/aside/div/ul/li[4]/a")));
	  boolean status = element.isDisplayed();
	  
	  if(status){
		  //Manage account
		  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/a")).click();
		  Thread.sleep(2000);
	  }else{
		  System.out.println("Link not found");
	  }
	  
	  //Add new user
	  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/ul/li[2]")).click();
	  Thread.sleep(7000);
	  
	  //Add new user
	  driver.findElement(By.xpath("html/body/section/aside/div/ul/li[4]/ul/li[2]")).click();
	  Thread.sleep(7000);
	  
	  //User Type
	  driver.findElement(By.id("type")).click();
	  Thread.sleep(200);
	  Robot r = new Robot();
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Save
	  driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[3]/div/button")).click();
	  Thread.sleep(2000);
	  
	  //First Name
	  String errorMSGEFnamectual = driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[1]/div[1]/div/label")).getText();
	  String errorMSGFnameExpected = "Please enter a first name";
	  Assert.assertEquals(errorMSGEFnamectual,errorMSGFnameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("f_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("f_name")).sendKeys("Swapnil");
	  Thread.sleep(1000);
	  
	  //Last Name
	  String errorMSGELnamectual = driver.findElement(By.xpath("html/body/section/section/section/div/aside/section/div[2]/form/div[1]/div[2]/div/label")).getText();
	  String errorMSGLnameExpected = "Please enter a last name";
	  Assert.assertEquals(errorMSGELnamectual,errorMSGLnameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("l_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("l_name")).sendKeys("Patil");
	  Thread.sleep(1000);
	  
	  //Email
	  String errorMSGEmailActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[3]/div/label")).getText();
	  String errorMSGEmailExpected = "Please enter a valid email address";
	  Assert.assertEquals(errorMSGEmailActual,errorMSGEmailExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("email")).sendKeys("swapnil@nadsoftdev.com");
	  Thread.sleep(1000);
	  
	  //Password
	  String errorMSGPasswordActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[4]/div/label")).getText();
	  String errorMSGPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGPasswordActual,errorMSGPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //Confirm Password
	  String errorMSGConfirmPasswordActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[5]/div/label")).getText();
	  String errorMSGConfirmPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGConfirmPasswordActual,errorMSGConfirmPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("confirm_password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("confirm_password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //Mobile
	  driver.findElement(By.name("mobile")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("mobile")).sendKeys("9595455315");
	  Thread.sleep(1000);
	  
	  //Display Name
	  String errorMSGAliasNameActual = driver.findElement(By.xpath(".//*[@id='addUserForm']/div[1]/div[11]/div/label")).getText();
	  String errorMSGAliasNameExpected = "This field is required.";
	  Assert.assertEquals(errorMSGAliasNameActual,errorMSGAliasNameExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("display_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("display_name")).sendKeys("Swap");
	  Thread.sleep(1000);
	  
	  //Address1
	  driver.findElement(By.name("address1")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("address1")).sendKeys("RK Road");
	  Thread.sleep(1000);
	  
	  //Address2
	  driver.findElement(By.name("address2")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("address2")).sendKeys("Ram Wadi");
	  Thread.sleep(1000);
	  
	  //Country
	  driver.findElement(By.xpath(".//*[@id='select2-c-name-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("India");
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //State
	  driver.findElement(By.xpath(".//*[@id='select2-state-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Maharashtra");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(4000);
	  
	  //City
	  driver.findElement(By.xpath(".//*[@id='select2-city-container']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Ratnagiri");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Zip
	  driver.findElement(By.name("zip")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("zip")).sendKeys("415612");
	  Thread.sleep(1000);
	  
	  //Timezone
	  driver.findElement(By.xpath(".//*[@id='select2-timezone-container']")).click();
	  Thread.sleep(200);
	  driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Kolkata");
	  Thread.sleep(2000);          
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //save
	  driver.findElement(By.xpath(".//*[@id='save_profile']")).click();
	  Thread.sleep(4000);
	  
	  //verify
	  String actualres = driver.findElement(By.xpath("html/body/section/section/section/div/div/section/div/div/div/table/tbody/tr[1]/td[4]")).getText();
	  String expectesres = "Ratnagiri / Maharashtra";
	  Assert.assertEquals(actualres, expectesres);
	  
	  System.out.println("Member add successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
public void memberLogin() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Member login script");
	  
	  driver.get("http://dev.20five40.com/login");
	  Thread.sleep(2000);
	  
	  //check the validation of buttons
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(2000);
	  
	  //Capture the invalid msg and enter mail ID
	  String errorMSGEmailIDActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[1]")).getText();
	  String errorMSGEmailIDExpected = "Please enter a valid email address";
	  Assert.assertEquals(errorMSGEmailIDActual, errorMSGEmailIDExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).sendKeys("swapnil@nadsoftdev.com");
	  Thread.sleep(1000);
	  
	  //password
	  String errorMSGPasswordActual = driver.findElement(By.xpath(".//*[@id='login_form']/div/label[2]")).getText();
	  String errorMSGPasswordExpected = "Please enter a password";
	  Assert.assertEquals(errorMSGPasswordActual, errorMSGPasswordExpected);
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(1000);
	  
	  //login
	  driver.findElement(By.xpath(".//*[@id='btn-login']")).click();
	  Thread.sleep(4000);
	  
	 /* //verify the login
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/member/dashboard";
	  Assert.assertEquals(actualURL, expectedURL);*/
	  
	  System.out.println("Member login successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void memberLogout() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Member logout script");
	  
	  //logout button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/ul/li[4]/a")).click();
	  Thread.sleep(1000);
	  
	  //verify the logout
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Member logout successfull.");
	  System.out.println("--------------------------------------------------");
  }
  
  public void checkoutStratfordWithPayPAl() throws InterruptedException, AWTException{
	  
	  //This test script is simple, just member comes and purchas the item and checkout.
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Simple checkout process using paupal script");
	  
	  //shop page link
	  driver.findElement(By.xpath("html/body/section/header/a/img")).click();
	  Thread.sleep(3000);
	  
	  //purchas item link
	  driver.findElement(By.xpath(".//*[@id='nav']/li[4]/a")).click();
	  Thread.sleep(3000);
	  
	  //Focus the company (company-1)
	  WebElement button = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actions = new Actions(driver);
  	  actions.moveToElement(button).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);          
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //select product (MRF bat)
	  WebElement button1 = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1 = new Actions(driver);
  	  actions1.moveToElement(button1).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[1]/section[1]/div/form/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  //paypal selct for checkout
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[3]/a/img")).click();
  	  Thread.sleep(8000);
  	  
  	  String actualDoller = driver.findElement(By.xpath(".//*[@id='miniCart']/div[2]/ul/li/span")).getText();
  	  String expectedDoller = "$3.00 USD";
  	  Assert.assertEquals(actualDoller, expectedDoller);
  	  Thread.sleep(2000);
  	  
  	  //login paypal
  	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]")).click();
  	  Thread.sleep(8000);
  	  
  	  //Paypal login details
  	  //email
  	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]/div[2]/div[3]/div/p[1]/span/input")).sendKeys("tushar@nadsoftdev.com");
  	  Thread.sleep(2000);          
  	  //password
  	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]/div[2]/div[3]/div/p[2]/span/input")).sendKeys("nadsoft123");
	  Thread.sleep(2000);
	  //login
	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]/div[2]/div[3]/div/p[3]/input")).click();
	  Thread.sleep(15000);
	  //pay button
	  driver.findElement(By.id("submit.x")).click();
	  Thread.sleep(20000);  
	  
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/complete";
	  Assert.assertEquals(actualURL, expectedURL);
	  
	  System.out.println("Checkout process complete");
	  System.out.println("--------------------------------------------------");
  }
  
  public void checkoutStratfordWithPayPAlOne() throws InterruptedException, AWTException{
	  
	  /*In this script
	    1- shop one item
	    2- Increase and decrease the quantity of item
	    3- Select the recurring
	    4- Add to cart 
	    5- Increase and decrease the quantity of item
	    6- Select the recurring
	    7- Verify the grand total total, verify the Sub Total,verify total, Verify Unit cost, 
	    8- delete the item
	    9- verify cart
	    10- Continue shopping 
	    11- Add single item in cart 
	    12- Click on Item link
	    13- Increase and decrease the quantity of item
	    14- Select the recurring
	    15- Add to cart
	    16- Continue shopping for add second item (nadsoft.test05@gmail.com user (supplier) used for add 2nd item in cart)
	    17- Verify the grand total total, verify the Sub Total,verify total, Verify Unit cost of that two items
	    18- Paypal checkout process
	  */
	  System.out.println("--------------------------------------------------");
	  System.out.println("Simple checkout process using paupal script");
	  
	  //shop page link
	  driver.findElement(By.xpath("html/body/section/header/a/img")).click();
	  Thread.sleep(3000);
	  
	  //purchas item link
	  driver.findElement(By.xpath(".//*[@id='nav']/li[4]/a")).click();
	  Thread.sleep(3000);
	  
	  //Focus the company (Company-1)
	  WebElement button = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actions = new Actions(driver);
  	  actions.moveToElement(button).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //select product (MRF bat)
	  WebElement button1 = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1 = new Actions(driver);
  	  actions1.moveToElement(button1).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img")).click();
  	  Thread.sleep(2000);
  	  
  	  //Choose the quantity you'd like to purchase (plus) (o/p 4)
  	  for(int i=1; i<=3; i++){
  		  driver.findElement(By.id("qnty_plus")).click();
  		  Thread.sleep(1000);
  	  }
  	  Thread.sleep(1000);
  	  for(int i=1; i<=1; i++){  //(o/p 3)
		  driver.findElement(By.id("qnty_minus")).click();
		  Thread.sleep(1000);
	  }
  	  
  	  //Choose the frequency of this purchase. (Select monthly)
  	  driver.findElement(By.id("recurring_cycle")).click();
  	  Thread.sleep(1000);
  	  Robot r = new Robot();
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[1]/section[1]/div/form/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[5]/select")).click(); //(select yearly option)
	  Thread.sleep(1000);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	//Choose the quantity you'd like to purchase (plus) (o/p 4)
  	  for(int i=1; i<=1; i++){
  		  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[6]/div/div/span/button[1]")).click();
  		  Thread.sleep(1000);
  	  }
  	  Thread.sleep(1000);
  	  for(int i=1; i<=2; i++){  //(o/p 2)
		  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[6]/div/div/span/button[2]")).click();
		  Thread.sleep(1000);
	  }
  	
  	  //verify the grand total total
  	  String actualGToal = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal = "$6";
  	  Assert.assertEquals(expectedGToatal, actualGToal);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal = "$6";
	  Assert.assertEquals(actualSToal, expectedSToatal);
	  Thread.sleep(200);
	  
	  //verify total
	  String actualToal = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[7]")).getText();
	  String expectedToatal = "$6";
	  Assert.assertEquals(actualToal, expectedToatal);
	  Thread.sleep(200);
	  
	  //Verify Unit cost
	  String actualUnitCost = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[4]")).getText();
	  String expectedUnitCost = "$3";
	  Assert.assertEquals(actualUnitCost, expectedUnitCost);
	  Thread.sleep(200);
	  
	  //delete the item
	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[1]/a/i")).click();
	  Thread.sleep(2000);
	  
	  //verify cart
	  String actualresult = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div/div[2]/table/tbody/tr/td")).getText();
	  String expectedresult = "No Item(s) Found";
	  Assert.assertEquals(actualresult, expectedresult);
	  Thread.sleep(500);
	  
	  //Continue shopping
	  driver.findElement(By.xpath(".//*[@id='orderInfo']/div/div[1]/h3/a")).click();
	  Thread.sleep(5000);
	  
	  //Focus the company (company-1)
	  WebElement button11 = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actions11 = new Actions(driver);
  	  actions11.moveToElement(button11).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //select product (MRF bat)
	  WebElement button111 = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions111 = new Actions(driver);
  	  actions111.moveToElement(button111).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath(".//*[@id='frm_cart']/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  //verify the grand total total
  	  String actualGToal1 = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal1 = "$3";
  	  Assert.assertEquals(expectedGToatal1, actualGToal1);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal1 = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal1 = "$3";
	  Assert.assertEquals(actualSToal1, expectedSToatal1);
	  Thread.sleep(200);
	  
	  //verify total
	  String actualToal1 = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[7]")).getText();
	  String expectedToatal1 = "$3";
	  Assert.assertEquals(actualToal1, expectedToatal1);
	  Thread.sleep(200);
	  
	  //Verify Unit cost
	  String actualUnitCost1 = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[4]")).getText();
	  String expectedUnitCost1 = "$3";
	  Assert.assertEquals(actualUnitCost1, expectedUnitCost1);
	  Thread.sleep(200);
	  
	  //click on item link
	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[2]/a")).click();
	  Thread.sleep(2000);
	  
	  //Choose the quantity you'd like to purchase (plus) (o/p 4)
  	  for(int i=1; i<=1; i++){
  		  driver.findElement(By.id("qnty_plus")).click();
  		  Thread.sleep(1000);
  	  }
  	  Thread.sleep(1000);
  	  for(int i=1; i<=1; i++){  //(o/p 2)
		  driver.findElement(By.id("qnty_minus")).click();
		  Thread.sleep(1000);
	  }
  	  
  	  //Choose the frequency of this purchase. (Select monthly)
  	  driver.findElement(By.id("recurring_cycle")).click();
  	  Thread.sleep(1000);
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
	  
	  //Add to cart
  	  driver.findElement(By.xpath(".//*[@id='frm_cart']/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  //verify the grand total total
  	  String actualGToal1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal1a = "$6";
  	  Assert.assertEquals(expectedGToatal1a, actualGToal1a);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal1a = "$6";
	  Assert.assertEquals(actualSToal1a, expectedSToatal1a);
	  Thread.sleep(200);
	  
	  //verify total
	  String actualToal1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[7]")).getText();
	  String expectedToatal1a = "$6";
	  Assert.assertEquals(actualToal1a, expectedToatal1a);
	  Thread.sleep(200);
	  
	  //Verify Unit cost
	  String actualUnitCost1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[4]")).getText();
	  String expectedUnitCost1a = "$3";
	  Assert.assertEquals(actualUnitCost1a, expectedUnitCost1a);
	  Thread.sleep(200);
	  
	  //Continue shopping
	  driver.findElement(By.xpath(".//*[@id='orderInfo']/div/div[1]/h3/a")).click();
	  Thread.sleep(5000);
	  
	  //Focus the company (Nadsoft-3)
	  WebElement button11a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[4]/div/div"));
  	  Actions actions11a = new Actions(driver);                    
  	  actions11a.moveToElement(button11a).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[4]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //Shop now
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select product (Mushroom)
	  WebElement button1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1a = new Actions(driver);
  	  actions1a.moveToElement(button1a).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[2]")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add to cart second item
  	  driver.findElement(By.xpath(".//*[@id='frm_cart']/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  //Select yearly in dropdown 
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr[2]/td[5]/select")).click();
  	  Thread.sleep(1000);
  	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
  	  
  	  //verify the grand total
  	  String actualGToal1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal1b = "$16";
  	  Assert.assertEquals(expectedGToatal1b, actualGToal1b);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal1b = "$16";
	  Assert.assertEquals(actualSToal1b, expectedSToatal1b);
	  Thread.sleep(200);
	  
	  //Verify Unit cost
	  String actualUnitCost1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText();
	  String expectedUnitCost1b = "$3";                      
	  Assert.assertEquals(actualUnitCost1b, expectedUnitCost1b);
	  Thread.sleep(200);
	  
	  String actualUnitCost1bb = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr[2]/td[4]")).getText();
	  String expectedUnitCost1bb = "$10";                      
	  Assert.assertEquals(actualUnitCost1bb, expectedUnitCost1bb);
	  Thread.sleep(200);
	  
	  //paypal selct for checkout
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[3]/a[1]/img")).click();
  	  Thread.sleep(8000);          
  	  
  	  String actualDoller = driver.findElement(By.xpath(".//*[@id='miniCart']/div[2]/ul/li/span")).getText();
  	  String expectedDoller = "$16.00 USD";               
  	  Assert.assertEquals(actualDoller, expectedDoller);
  	  Thread.sleep(2000);
  	  
  	  //login paypal
  	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]")).click();
  	  Thread.sleep(8000);
  	  
  	  //Paypal login details
  	  //email
  	  driver.findElement(By.id("login_email")).clear();
  	  Thread.sleep(500);            
  	  driver.findElement(By.id("login_email")).sendKeys("amol.patil@nadsoftdev.com");
  	  Thread.sleep(2000);
  	  //password
  	  driver.findElement(By.id("login_password")).sendKeys("nadsoft123");
	  Thread.sleep(2000);
	  //login
	  driver.findElement(By.id("submitLogin")).click();
	  Thread.sleep(15000);         
	  //pay button
	  driver.findElement(By.id("submit.x")).click();
	  Thread.sleep(30000);  
	  
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/complete";
	  Assert.assertEquals(actualURL, expectedURL);
	  Thread.sleep(2000);
	  
	  //note
	  driver.findElement(By.id("note_box")).clear();
	  Thread.sleep(500);
	  driver.findElement(By.id("note_box")).sendKeys("Test note added.");
	  Thread.sleep(2000);
	  
	  //Add note button
	  driver.findElement(By.id("add_recipint_note")).click();
	  Thread.sleep(2000);
	  
	  //focus on success msg 
	  WebElement button1aa = driver.findElement(By.xpath("html/body/div[10]/div"));
  	  Actions actions1aa = new Actions(driver);
  	  actions1aa.moveToElement(button1aa).perform();
  	  Thread.sleep(2000);
  	  String actres = driver.findElement(By.xpath("html/body/div[10]/div/div[2]")).getText();
  	  String expres = "Success!";
  	  Assert.assertEquals(expres, actres);
	  
	  System.out.println("Checkout process complete");
	  System.out.println("--------------------------------------------------");
	  
	  ArrayList<String> windoes = new ArrayList<String>(driver.getWindowHandles());
	  driver.switchTo().window(windoes.get(1));
	 
  }
  
  public void otherButtonFunctionalityOnShopPage() throws InterruptedException, AWTException{
	  
	  /*
	   1- Sorting functionality (Select Supplier and Select Location)
	   2- All supplier (Select Supplier drop down)
	   3- Select supplier and select location 
	   4- Search functionality
	   5- Category search functionality
	   6- Sort by price
	   7- Back to location functionality
	   8- Back to companies
	   9- Company link
	   10- Home link
	   */
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Other button functionality on checkout process script");
	  
	  //shop page link
	  driver.findElement(By.xpath("html/body/section/header/a/img")).click();
	  Thread.sleep(3000);
	  
	  //purchas item link
	  driver.findElement(By.xpath(".//*[@id='nav']/li[4]/a")).click();
	  Thread.sleep(3000);
	  
	  //Focus the company (Company-1)
	  WebElement button = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actions = new Actions(driver);
  	  actions.moveToElement(button).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	 //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //Sorting functionality (Select Supplier and Select Location)
	  //Select Supplier
	  driver.findElement(By.id("supplierSel")).click();  // (All suppliers)
	  Thread.sleep(500);
	  Robot r = new Robot();
	  r.keyPress(KeyEvent.VK_UP);
	  r.keyRelease(KeyEvent.VK_UP);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);
	  
	  //verify
	  String actualItemOne = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[1]/a/section/div[2]/h4/span")).getText();
	  String expectedItemOne = "Lunch Combo";
	  Assert.assertEquals(expectedItemOne, actualItemOne);
	  Thread.sleep(1000);
	  
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("supplierSel")).click();  // (Sales technologies)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(5000);
	  
	  //Select Location
	  driver.findElement(By.id("locationSel")).click();  // (nadsoft loc-2)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);; // view result of loc-2
	  
	  //verify
	  String actualItemTwo = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemTwo = "Nadsoft Item - 2";
	  Assert.assertEquals(expectedItemTwo, actualItemTwo);
	  Thread.sleep(1000);
	  
	  
	  driver.findElement(By.id("locationSel")).click();  // (nadsoft loc-1)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000); // view result of loc-1
	  
	  //verify
	  String actualItemThree = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemThree = "Nadsoft Item - 1";
	  Assert.assertEquals(expectedItemThree, actualItemThree);
	  Thread.sleep(1000);
	  
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  
	  //Select Supplier
	  driver.findElement(By.id("supplierSel")).click();  // (All suppliers)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_UP);
	  r.keyRelease(KeyEvent.VK_UP);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(5000);
	  
	  //search box (Lunch search)
	  driver.findElement(By.name("item_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("item_name")).sendKeys("Lunch");
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);
	  
	  //verify
	  String actualItemFour = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemFour = "Lunch Combo";
	  Assert.assertEquals(expectedItemFour, actualItemFour);
	  Thread.sleep(1000);
	  
	  //search box (MRF search)
	  driver.findElement(By.name("item_name")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("item_name")).sendKeys("MRF");
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);
	  
	  //verify
	  String actualItemFive = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemFive = "MRF Bat";
	  Assert.assertEquals(expectedItemFive, actualItemFive);
	  Thread.sleep(1000);
	  
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  
	  //Category search
	  driver.findElement(By.id("supplierSel")).click();  // (All suppliers)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_UP);
	  r.keyRelease(KeyEvent.VK_UP);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(5000);
	  
	  //category food
	  driver.findElement(By.linkText("Food")).click();
	  Thread.sleep(3000);
	  //Breakfast
	  driver.findElement(By.linkText("- Breakfast")).click();
	  Thread.sleep(10000);
	  //verify
	  String actualItemSix = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[1]/a/section/div[2]/h4/span")).getText();
	  String expectedItemSix = "Nadsoft Item - 3";                   
	  Assert.assertEquals(expectedItemSix, actualItemSix);
	  Thread.sleep(1000);
	  //Lunch
	  driver.findElement(By.linkText("- Lunch")).click();
	  Thread.sleep(10000);
	  //verify
	  String actualItemSeven = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemSeven = "Nadsoft Item - 4";                   
	  Assert.assertEquals(expectedItemSeven, actualItemSeven);
	  Thread.sleep(1000);
	  //Lunch or Dinner
	  driver.findElement(By.linkText("- Lunch or Dinner")).click();
	  Thread.sleep(10000);
	  //verify
	  String actualItemEight = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemEight = "Lunch Combo";                   
	  Assert.assertEquals(expectedItemEight, actualItemEight);
	  Thread.sleep(1000);
	  //food
	  driver.findElement(By.linkText("Food")).click();
	  Thread.sleep(3000);
	  //Cloaths
	  driver.findElement(By.linkText("Cloaths")).click();
	  Thread.sleep(3000);
	  //Small 
	  driver.findElement(By.linkText("- Small(S)")).click();
	  Thread.sleep(10000);
	  //verify
	  String actualItemNine = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemNine = "Nadsoft Item - 2";                   
	  Assert.assertEquals(expectedItemNine, actualItemNine);
	  Thread.sleep(1000);
	  //Large
	  driver.findElement(By.linkText("- Large (L)")).click();
	  Thread.sleep(10000);
	  //verify
	  String actualItemTen = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[2]/h4/span")).getText();
	  String expectedItemTen = "Nadsoft Item - 1";                   
	  Assert.assertEquals(expectedItemTen, actualItemTen);
	  Thread.sleep(1000);
	  
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  
	  //Sort
	  driver.findElement(By.id("supplierSel")).click();  // (All suppliers)
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_UP);
	  r.keyRelease(KeyEvent.VK_UP);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(5000);
	  
	  //Sort by Price
	  //low to high
	  driver.findElement(By.name("srtItm")).click();
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);
	  
	  //verify
	  String actualItema = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[1]/a/section/div[2]/h4/span")).getText();
	  String expectedItema = "Nadsoft Item - 2";                   
	  Assert.assertEquals(expectedItema, actualItema);
	  Thread.sleep(1000);
	  String actualItemb = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[3]/a/section/div[2]/h4/span")).getText();
	  String expectedItemb = "Lunch Combo";                   
	  Assert.assertEquals(expectedItemb, actualItemb);
	  Thread.sleep(1000);
	  
	  //high to low
	  driver.findElement(By.name("srtItm")).click();
	  Thread.sleep(2000);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(500);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(10000);
	  
	  //verify
	  String actualItemc = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[1]/a/section/div[2]/h4/span")).getText();
	  String expectedItemc = "Large Shirt";                   
	  Assert.assertEquals(expectedItemc, actualItemc);
	  Thread.sleep(1000);
	  String actualItemd = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div[3]/a/section/div[2]/h4/span")).getText();
	  String expectedItemd = "Nadsoft Item - 4";                   
	  Assert.assertEquals(expectedItemd, actualItemd);
	  Thread.sleep(1000);
	  
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  
	  //Back to location functionality
	  driver.findElement(By.xpath(".//*[@id='item_filter']/div[2]/section/div/div/div/a[2]")).click();
	  Thread.sleep(5000);
	  //select location - Automation company
	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
	  Thread.sleep(5000);          
	  //Show now
	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(5000);                
	  
	  //Back to companies
	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/section/div/div/div/a[1]")).click();
	  Thread.sleep(5000);
	  //Focus the company (NADSOFT company-2)
	  WebElement buttona = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[5]/div/div"));
  	  Actions actionsa = new Actions(driver);               
  	  actionsa.moveToElement(buttona).perform();
  	  Thread.sleep(2000);
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[5]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);                    
  	  //back to company 
  	  driver.findElement(By.xpath("html/body/div[4]/div/div/div/a")).click();
	  Thread.sleep(5000);
	  //Focus the company (NADSOFT company-3)
	  WebElement buttonb = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[4]/div/div"));
  	  Actions actionsb = new Actions(driver);                 
  	  actionsb.moveToElement(buttonb).perform();
  	  Thread.sleep(2000);
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[4]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);          
  	  //back to company 
  	  driver.findElement(By.xpath("html/body/div[4]/div/div/div/a")).click();
	  Thread.sleep(5000);
	  //Focus the company (NADSOFT company-1)
	  WebElement buttonc = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actionsc = new Actions(driver);
  	  actionsc.moveToElement(buttonc).perform();
  	  Thread.sleep(2000);
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000); 
	 
  	  //Company link
  	  driver.findElement(By.xpath("html/body/div[4]/div/div/div/div[2]/ul/li[2]/a")).click();
  	  Thread.sleep(5000);          
  	  //Home link
  	  driver.findElement(By.xpath("html/body/div[4]/div/div/div/div[2]/ul/li[1]/a")).click();
	  Thread.sleep(5000);
	  
	  System.out.println("Other option working fine");
	  System.out.println("--------------------------------------------------");
  }
  
  public void loginInFontSide() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Login member in frontside  script");
	  
	  driver.navigate().to("http://dev.20five40.com/");
	  Thread.sleep(2000);
	  
	  //login link
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[4]/a")).click();
	  Thread.sleep(2000);
	  
	  //email
	  driver.findElement(By.name("user_email")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("user_email")).sendKeys("swapnil@nadsoftdev.com");
	  Thread.sleep(2000);
	  
	  //password
	  driver.findElement(By.name("password")).clear();
	  Thread.sleep(200);
	  driver.findElement(By.name("password")).sendKeys("nadsoft");
	  Thread.sleep(2000);
	  
	  //login button
	  driver.findElement(By.id("btn-login")).click();
	  Thread.sleep(2000);
	  
	  //verification
	  String actulUserName = driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a/span")).getText();
	  String expectedUserName = "Swap";
	  Assert.assertEquals(actulUserName, expectedUserName);
	  
	  System.out.println("Member login success");
	  System.out.println("--------------------------------------------------");
  }
  
  public void memberOtherOPtion() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Member other options  script");
	  
	  //dash board under drop down
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //dashboard
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/ul/li[1]/a")).click();
	  Thread.sleep(2000);
	  
	  //verify
	  String actulURL = driver.getCurrentUrl();
	  String expectURL = "http://dev.20five40.com/member/dashboard";
	  Assert.assertEquals(actulURL, expectURL);
	  
	  //Home
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[3]/ul/li[1]/a")).click();
	  Thread.sleep(2000);
	  
	  //verify
	  String actulURLa = driver.getCurrentUrl();
	  String expectURLa = "http://dev.20five40.com/";
	  Assert.assertEquals(actulURLa, expectURLa);
	  
	  //Profile under drop down
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //Profile
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/ul/li[2]/a")).click();
	  Thread.sleep(2000);
	  
	  //verify
	  String actulURLb = driver.getCurrentUrl();
	  String expectURLb = "http://dev.20five40.com/member/profile";
	  Assert.assertEquals(actulURLb, expectURLb);
	  
	  //Home
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[3]/ul/li[1]/a")).click();
	  Thread.sleep(2000);          
	  
	  //verify
	  Assert.assertEquals(actulURLa, expectURLa);
	  
	  //Messages under drop down
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //Messages
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //verify
	  String actulURLd = driver.getCurrentUrl();
	  String expectURLd = "http://dev.20five40.com/member/messages";
	  Assert.assertEquals(actulURLd, expectURLd);
	  
	  //Home
	  driver.findElement(By.xpath(".//*[@id='container']/header/div[3]/ul/li[1]/a")).click();
	  Thread.sleep(2000);          
	  
	  //verify
	  Assert.assertEquals(actulURLa, expectURLa);
	  
	  System.out.println("Member other options works fine");
	  System.out.println("--------------------------------------------------");
  }
  
  public void logoutFontSide() throws InterruptedException{
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Logout member in frontside  script");
	  
	  //drop down
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  
	  //logout
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/ul/li[3]/ul/li[4]/a")).click();
	  Thread.sleep(2000);
	  
	  System.out.println("Member logout successfully");
	  System.out.println("--------------------------------------------------");
  }
  
  public void guifterFunctionality() throws InterruptedException, AWTException{
	  
	  /*1- Verify the tooltip
	    2- Add related item
	    3- Delete item
	    4- Verify the grand total total, verify the Sub Total,verify total, Verify Unit cost
	    5- Cart Icon functionality
	    6- Give Button
	    7- Add Item.
	    8- Icon link
	    9- Verify the totals
	  */
	  
	  System.out.println("--------------------------------------------------");
	  System.out.println("Guifter functionality  script");
	  
	  driver.navigate().to("http://dev.20five40.com/");
	  Thread.sleep(2000);
	  
	  //Purchase item link
	  driver.findElement(By.xpath(".//*[@id='nav']/li[4]/a")).click();
	  Thread.sleep(2000);
	  
	  //Focus the company (NADSOFT company-1)
	  WebElement buttonc = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actionsc = new Actions(driver);
  	  actionsc.moveToElement(buttonc).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //select product (MRF bat)
	  WebElement button1 = driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1 = new Actions(driver);
  	  actions1.moveToElement(button1).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[2]")).click();
  	  Thread.sleep(2000);
  	  
  	  //Choose the quantity you'd like to purchase (plus) (o/p 4)
  	  for(int i=1; i<=1; i++){
  		  driver.findElement(By.id("qnty_plus")).click();
  		  Thread.sleep(1000);
  	  }
  	  Thread.sleep(1000);
  	  for(int i=1; i<=1; i++){  //(o/p 2)
		  driver.findElement(By.id("qnty_minus")).click();
		  Thread.sleep(1000);
	  }
  	  
  	  //Choose the frequency of this purchase. (Select monthly)
  	  driver.findElement(By.id("recurring_cycle")).click();
  	  Thread.sleep(1000);
  	  Robot r = new Robot();
      r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_DOWN);
	  r.keyRelease(KeyEvent.VK_DOWN);
	  Thread.sleep(200);
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath("html/body/div[5]/div/div/div/div[1]/section[1]/div/form/div[2]/p[2]/button")).click();
  	  Thread.sleep(5000);   
  	  
  	  //Click on ToolTip
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[3]/span/i")).click();
	  Thread.sleep(2000);          
	  
	  //Capture the ToolTip and verified it               
	  String actualToolTip = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[3]/span/div/div[2]")).getText();
	  String expectedToolTip = "MRF Bat for playing cricket. It is sachin tendulake brand. India Sachin Tendulkar, India Rohit Sharma, India Gautam Gambhir MRF Bat for playing cricket. It is sachin tendulake brand. India Sachin Tendulkar, India Rohit Sharma, India Gautam Gambhir";
	  Assert.assertEquals(actualToolTip, expectedToolTip);
	  Thread.sleep(1000);
	  
	  //view cart Icon
	  driver.findElement(By.xpath("html/body/div[3]/div")).click();
	  Thread.sleep(2000);          
	  
	  //Continue shopping
	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[1]/h3/a")).click();
	  Thread.sleep(5000);
	  
	  //Focus the company
	  WebElement button11 = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[6]/div/div"));
  	  Actions actions11 = new Actions(driver);            
  	  actions11.moveToElement(button11).perform();
  	  Thread.sleep(2000);
  	  
  	  //Select button
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[6]/div/div/div/div/a")).click();
  	  Thread.sleep(5000);
  	  
  	  //select location (Automation company)
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div/div[1]/section/div/section/div/ul[1]/li/div[2]/a")).click();
  	  Thread.sleep(2000);          
  	  
  	  //Shop now 
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
	  Thread.sleep(2000);
	  
	  //select product (MRF bat)
	  WebElement button1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1a = new Actions(driver);
  	  actions1a.moveToElement(button1a).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[2]")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add related item
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div/div[2]/a/section/div[1]/div")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath(".//*[@id='frm_cart']/div[2]/p[2]/button")).click();
  	  Thread.sleep(5000);
  	  
  	  //delete item 
  	  driver.findElement(By.xpath(".//*[@id='orderInfo']/div/div[2]/table/tbody/tr[2]/td[1]/a/i")).click();
  	  Thread.sleep(3000);
  	  
  	  //verify the grand total
  	  String actualGToal1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal1b = "$3";                   
  	  Assert.assertEquals(expectedGToatal1b, actualGToal1b);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal1b = "$3";
	  Assert.assertEquals(actualSToal1b, expectedSToatal1b);
	  Thread.sleep(200);
	  
	  //Verify Unit cost
	  String actualUnitCost1b = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText();
	  String expectedUnitCost1b = "$3";                      
	  Assert.assertEquals(actualUnitCost1b, expectedUnitCost1b);
	  Thread.sleep(200);
	 
	  //Total
	  String actualUnitCost1c = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/table/tbody/tr/td[7]")).getText();
	  String expectedUnitCost1c = "$3";                      
	  Assert.assertEquals(actualUnitCost1c, expectedUnitCost1c);
	  Thread.sleep(200);
	  
	  // Continue shopping
	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[1]/h3/a")).click();
	  Thread.sleep(3000);
	  
	  //Cart Icon
	  driver.findElement(By.xpath("html/body/div[3]/div")).click();
	  Thread.sleep(3000);
	  
	  //Give button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[2]/div[3]/a")).click();
	  Thread.sleep(3000);
	  
	  //Focus on company-3
	  WebElement buttonca = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[4]/div/div"));
  	  Actions actionsca = new Actions(driver);
  	  actionsca.moveToElement(buttonca).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div[4]/div/div/div/div/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //shop now
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div[4]/div[4]/div/div[2]/div/div/p/a")).click();
  	  Thread.sleep(2000);
  	  
  	  //select product (Mushroom)
	  WebElement button1aa = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[1]/img"));
  	  Actions actions1aa = new Actions(driver);
  	  actions1aa.moveToElement(button1aa).perform();
  	  Thread.sleep(2000);
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[2]/form/div[2]/div[2]/div/a/section/div[1]/div[2]")).click();
  	  Thread.sleep(2000);
  	  
  	  //Add to cart
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div[1]/section[1]/div/form/div[2]/p[2]/button")).click();
  	  Thread.sleep(2000);
  	  
  	  //Icon link
  	  driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[1]/div/a/img")).click();
  	  Thread.sleep(2000);
  	  
  	  //shop cart  Icon
  	  driver.findElement(By.xpath("html/body/div[3]/div")).click();
  	  Thread.sleep(2000);
  	  
  	  //verify the grand total total
  	  String actualGToal1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[2]/td[2]")).getText();
  	  String expectedGToatal1a = "$13";
  	  Assert.assertEquals(expectedGToatal1a, actualGToal1a);
  	  Thread.sleep(200);
  	  
  	  //verify the Sub Total
  	  String actualSToal1a = driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[2]")).getText();
	  String expectedSToatal1a = "$13";                    
	  Assert.assertEquals(actualSToal1a, expectedSToatal1a);
	  Thread.sleep(200);
	  
	  //paypal selct for checkout
  	  driver.findElement(By.xpath("html/body/div[6]/div/div/div/div/div[3]/a[1]/img")).click();
  	  Thread.sleep(8000);          
  	  
  	  String actualDoller = driver.findElement(By.xpath(".//*[@id='miniCart']/div[2]/ul/li/span")).getText();
  	  String expectedDoller = "$13.00 USD";               
  	  Assert.assertEquals(actualDoller, expectedDoller);
  	  Thread.sleep(2000);
  	  
  	  //login paypal
  	  driver.findElement(By.xpath("html/body/div[4]/div[2]/div[3]/div/form[3]/div/div[2]/div[2]/div/fieldset/div/div[1]")).click();
  	  Thread.sleep(8000);
  	  
  	  //Paypal login details
  	  //email
  	  driver.findElement(By.id("login_email")).clear();
  	  Thread.sleep(500);            
  	  driver.findElement(By.id("login_email")).sendKeys("amol.patil@nadsoftdev.com");
  	  Thread.sleep(2000);
  	  //password
  	  driver.findElement(By.id("login_password")).sendKeys("nadsoft123");
	  Thread.sleep(2000);
	  //login
	  driver.findElement(By.id("submitLogin")).click();
	  Thread.sleep(15000);         
	  //pay button
	  driver.findElement(By.id("submit.x")).click();
	  Thread.sleep(30000);  
	  
	  String actualURL = driver.getCurrentUrl();
	  String expectedURL = "http://dev.20five40.com/complete";
	  Assert.assertEquals(actualURL, expectedURL);
	  Thread.sleep(2000);
	  
	  //note
	  driver.findElement(By.id("note_box")).clear();
	  Thread.sleep(500);
	  driver.findElement(By.id("note_box")).sendKeys("Test note added.");
	  Thread.sleep(2000);
	  
	  //Add note button
	  driver.findElement(By.id("add_recipint_note")).click();
	  Thread.sleep(2000);
	  
	  //focus on success msg 
	  WebElement button1aaa = driver.findElement(By.xpath("html/body/div[10]/div"));
  	  Actions actions1aaa = new Actions(driver);
  	  actions1aaa.moveToElement(button1aaa).perform();
  	  Thread.sleep(2000);
  	  String actres = driver.findElement(By.xpath("html/body/div[10]/div/div[2]")).getText();
  	  String expres = "Success!";
  	  Assert.assertEquals(expres, actres);
	  
	  System.out.println("Checkout process complete of guifter");
	  System.out.println("--------------------------------------------------");
	  
  }
}

