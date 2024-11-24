package kudo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class KoodoScraperService {

    private WebDriver initializeWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Optional: Run in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    public String scrapePrepaidPlans() {
        WebDriver driver = null;
        StringBuilder result = new StringBuilder();

        try {

            // Setup WebDriver
            //WebDriverManager.chromedriver().setup();
            driver = initializeWebDriver();

            // Navigate to the Koodo Mobile prepaid plans page
            driver.get("https://www.koodomobile.com/en/prepaid-plans");

            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".app-container")));

            // Extract plan types from tabs
            List<WebElement> tabs = driver.findElements(By.cssSelector(".KDS_Tabs-modules__tabButton___1RuCu"));
            for (WebElement tab : tabs) {
                String planType = tab.getText();
                tab.click();

                // Extract plan details for the selected tab
                List<WebElement> planCards = driver.findElements(By.cssSelector(".KDS_Item-modules__item___1fILq"));
                for (WebElement planCard : planCards) {
                    String planDetails = planCard.getText();
                    result.append(planType).append(": ").append(planDetails).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        return result.toString();
    }
}
