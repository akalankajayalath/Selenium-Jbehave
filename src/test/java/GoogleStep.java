import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class GoogleStep {

    private WebDriver driver;
    private FluentWait<WebDriver> fWait;
    
    public GoogleStep() {
    	System.setProperty("webdriver.gecko.driver","C:\\Program Files\\GeckoDriver\\geckodriver.exe");
        File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:firefoxOptions", options.setBinary(ffBinary));
        WebDriver driver2 = new FirefoxDriver(options);
        driver = driver2;
               
        fWait = new FluentWait<WebDriver>(driver).pollingEvery(500, 
            TimeUnit.MILLISECONDS).withTimeout(10,  TimeUnit.SECONDS);
         
        }
       
    //Google Mapping
    
    @Given("I navigate to $url with the $body")
    public void navigateToUrl(String url,String body){
        driver.get(url);
        fWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(body)));
    }
    
    @When("I perform a search for $query in the $body")
    public void performASearchForGoogleQuery(String query ,String body){
        driver.findElement(By.id(body)).sendKeys(query);
    }
    
    @Then("I click $Search Button")
    public void clickSearchGoogleButton(String search) {
    	driver.findElement(By.xpath("//input[@aria-label='"+search+"']")).click();
    }
    
    @Then("A link $text exists in the results")
    public void linkContainingTextExistsInTheGoogleResults(String resultText){
    	driver.getPageSource().contains(resultText);
    }
    
   
}
