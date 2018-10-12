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
import org.openqa.selenium.Keys;

public class SearchStep {

    private WebDriver driver;
    private FluentWait<WebDriver> fWait;
    
    public SearchStep() {
    	System.setProperty("webdriver.gecko.driver","C:\\Program Files\\GeckoDriver\\geckodriver.exe");
        File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("moz:firefoxOptions", options.setBinary(ffBinary));
        WebDriver driver2 = new FirefoxDriver(options);
        driver = driver2;
       
        //fluent wait used to wait 10 sec until the element is visible and poll every 0.5 second to verify that
        fWait = new FluentWait<WebDriver>(driver).pollingEvery(500, 
            TimeUnit.MILLISECONDS).withTimeout(10,  TimeUnit.SECONDS);
         
        }
       
    //Story Mapping
    
    @Given("I navigate to $url with the $body")
    public void navigateToUrl(String url,String body){
        driver.get(url);
        fWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(body)));
    }
    
    @When("I perform a search for $query in the $search")
    public void performASearchForQuery(String query ,String search){
        driver.findElement(By.id(search)).sendKeys(query);
        driver.findElement(By.id(search)).sendKeys(Keys.ENTER);
    }
    /*
    @Then("I click $body Button")
    public void clickSearchButton(String body) {
    	//both google and yahoo has common element named aria label
    	//driver.findElement(By.xpath("//input[@aria-label='"+search+"']  |  //button[@aria-label='"+search+"']")).click();
    	driver.findElement(By.id(body)).sendKeys(Keys.RETURN);
    }
    */
    @Then("A link $text exists in the results")
    public void linkContainingTextExistsInTheResults(String resultText){
    	driver.getPageSource().contains(resultText);
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
   
}
