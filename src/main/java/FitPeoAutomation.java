

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FitPeoAutomation {
    public static void main(String[] args) throws InterruptedException {
         WebDriverManager.firefoxdriver().setup();
         WebDriver driver = new FirefoxDriver();
         driver.get("https://www.fitpeo.com");
         driver.manage().window().maximize();
       
         WebDriverWait wait = new WebDriverWait(driver,1000);
        
           
            WebElement revenueCalculatorLink = driver.findElement(By.xpath("//div[text()=\"Revenue Calculator\"]"));
            revenueCalculatorLink.click();
            
            WebElement slider = driver.findElement(By.xpath("//input[@aria-valuenow=\"200\"]/ancestor::span[1]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", slider);
            int sliderValue = 820;
            Thread.sleep(1000);
            int sliderWidth = slider.getSize().width;  // Get the width of the slider
            int xOffset = (int) ((sliderValue / 800.0) * sliderWidth);
            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(xOffset, 0).release().perform();
            Thread.sleep(1000);
            // Step 5: Update the Text Field to 560
            WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
            System.out.println(textField.getText());
            textField.clear();
            textField.sendKeys("560");
            WebElement updatedSlider = driver.findElement(By.xpath("//input[@type='range']"));
            String sliderPosition = updatedSlider.getAttribute("value");
            if (!sliderPosition.equals("560")) {
                System.out.println("Slider position did not update correctly.");
            }
            WebElement cpt99091Checkbox = driver.findElement(By.xpath("//p[text()='CPT-99091']/following-sibling::label/span"));
            WebElement cpt99453Checkbox = driver.findElement(By.xpath("//p[text()='CPT-99453']/following-sibling::label/span"));
            WebElement cpt99454Checkbox = driver.findElement(By.xpath("//p[text()='CPT-99454']/following-sibling::label/span"));
            WebElement cpt99474Checkbox = driver.findElement(By.xpath("//p[text()='CPT-99474']/following-sibling::label/span"));
            cpt99091Checkbox.click();
            cpt99453Checkbox.click();
            cpt99454Checkbox.click();
            cpt99474Checkbox.click();
           WebElement reimbursementValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/p[4]/p[1]")));
            String totalReimbursement = reimbursementValue.getText();
            if (totalReimbursement.equals("$110700")) {
                System.out.println("Total Recurring Reimbursement is correct: " + totalReimbursement);
            } else {
                System.out.println("Total Recurring Reimbursement is incorrect: " + totalReimbursement);
            }
        
            driver.quit();
        }
    
}

