package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Configura automáticamente el driver
        driver = new ChromeDriver(); // No redeclarar
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @Test
    public void testGoogleSearch() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("Hola mundo desde Selenium con Java");
        searchBox.submit();

        // Espera implícita (no muy recomendada, pero está bien para pruebas simples)
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        // Verifica que el título contenga lo buscado
        assertTrue(driver.getTitle().toLowerCase().contains("hola mundo"), "El título no contiene el texto esperado.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
