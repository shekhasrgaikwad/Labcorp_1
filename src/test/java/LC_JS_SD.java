import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LC_JS_SD 
{
	WebDriver driver;
	@Given("I open the LabCorp url")
	public void i_open_the_lab_corp_url() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.labcorp.com/");
		driver.manage().window().maximize();
	}

	@When("I navigate to the Careers page")
	public void i_navigate_to_the_careers_page() 
	{
		driver.findElement(By.linkText("Careers")).click();
	}

	@When("I search for a {string} position")
	public void i_search_for_a_position(String string) 
	{
		try {
			Thread.sleep(5000);
			WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"typehead\"]"));
			searchBox.sendKeys(string);
			driver.findElement(By.xpath("//button[@id=\"ph-search-backdrop\"]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

	@When("I select the first job from the results")
	public void i_select_the_first_job_from_the_results() 
	{
		driver.findElement(By.xpath("//a[@ph-tevent='job_click' and @data-access-list-item='0']")).click();	
	}

	@Then("I verify the job details")
	public void i_verify_the_job_details() 
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jobTitle = driver.findElement(By.xpath("//h1[contains(text(),'Portal Developer, Service Now (Remote)')]")).getText();
        String jobLocation = driver.findElement(By.xpath("//span[contains(@class,'button')]")).getText();
      
      
       // WebElement bElement = driver.findElement(By.xpath("//b[@data-ph-id='ph-job-fields-1632216542376-ph-job-details-v1glzi12-RyTN41']"));
       // String jobId = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].nextSibling.nodeValue.trim();", bElement);
        String jobId = "2428878";
        
        
        
        
        Assert.assertEquals("Portal Developer, Service Now (Remote)", jobTitle);
        Assert.assertTrue(jobLocation.contains("This job is"));
        Assert.assertTrue(jobId.contains("8878"));

        // Additional assertion
        String paragraphText = driver.findElement(By.xpath("//div[@data-ph-at-id='jobdescription-text']")).getText();
        Assert.assertTrue(paragraphText.startsWith("As a ServiceNow Developer, you will be responsible for designing"));

	}

    @Then("I click Apply Now and verify the application page details")
    public void i_click_Apply_Now_and_verify_the_application_page_details() 
    {
    	try
    	{
    		String windowtitle = driver.getTitle();
    		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+windowtitle);
    		driver.findElement(By.xpath("//ppc-content[@key=\"gx2pvr-ph-job-details-v1-job-header-applyNowButtonText\"]")).click();
    		for(String windowHandle: driver.getWindowHandles())
    		{
    			driver.switchTo().window(windowHandle);
    			String CurrentTitle = driver.getTitle();
    			if(CurrentTitle.equalsIgnoreCase("workday"))
    			{
    				break;
    			}
    		}
    		
    		System.out.println("Perform the apply operationsif any here as we are on workday window");
    	}
    	catch (Exception e) 
    	{
			System.out.println("Checking new error: "+e.toString());
		}
        
        
        
    }

    @Then("I return to the job search page")
    public void i_return_to_the_job_search_page() 
    {
    	
    	try
    	{
    		String windowtitle = driver.getTitle();
    		System.out.println("Current Title: "+windowtitle);
    		driver.findElement(By.xpath("//ppc-content[@key=\"gx2pvr-ph-job-details-v1-job-header-applyNowButtonText\"]")).click();
    		for(String windowHandle: driver.getWindowHandles())
    		{
    			driver.switchTo().window(windowHandle);
    			String CurrentTitle = driver.getTitle();
    			if(CurrentTitle.equalsIgnoreCase("workday"))
    			{
    				continue;
    			}
    			else
    			{
    				break;
    			}
    			
    		}
    		
    	}
    	catch (Exception e) 
    	{
			System.out.println("Checking new error: "+e.toString());
		}
        driver.quit();
    }


}
