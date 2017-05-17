package webTestingExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webPage.TestPage;

import java.util.concurrent.TimeUnit;


public class FirstTest {
    private WebDriver driver;
    private TestPage testPage;

    @BeforeClass
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\new\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("http://ranorex.com/web-testing-examples/vip/");
        testPage = new TestPage(driver);
    }


    @Test(groups = {"add"})
    public void testAddNewUser() throws Exception {
        int before = testPage.getTableRows().size();
        testPage.addUser("Denim", "Dak");
        int after = testPage.getTableRows().size();
        Assert.assertTrue(after - before == 1);
    }

    @Test(groups = {"add"})
    public void testAddNewUserNumericalValues() throws Exception {
        int before = testPage.getTableRows().size();
        testPage.addUser("12345", "12345");
        int after = testPage.getTableRows().size();
        Assert.assertFalse(after - before == 1);
    }

    @Test(groups = {"add"})
    public void testAddSameUsers() throws Exception {
        testPage.addUser("Max", "Moody");
        int before = testPage.getTableRows().size();
        testPage.addUser("Max", "Moody");
        int after = testPage.getTableRows().size();
        Assert.assertFalse(after - before == 1);

    }

    @Test(groups = {"delete"})
    public void testDeleteUser() throws Exception {
        testPage.addUser("Max", "Moody");
        int before = testPage.getTableRows().size();
        testPage.deleteUser();
        int after = testPage.getTableRows().size();
        Assert.assertTrue(after < before);
    }

    @Test(groups = {"load"})
    public void testLoadUser() throws Exception {
        int before = testPage.getTableRows().size();
        testPage.loadUser();
        int after = testPage.getTableRows().size();
        Assert.assertTrue(after > before);
    }

    @Test(groups = {"clear"})
    public void testClearUser() throws Exception {
        testPage.addUser("Denim", "Dak");
        testPage.addUser("Max", "Moody");
        testPage.clearUser();
        int after = testPage.getTableRows().size();
        Assert.assertTrue(after == 1);
    }

    @Test(groups = {"add"})
    public void testAddEmptyFields() throws Exception {

        // Сохранение дискриптора текущего окна
        String winHandleBefore = driver.getWindowHandle();
        testPage.addUser("", "");

        // Переключение на новое окно
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        String actualText = "Please specify 'First Name' value";
        WebElement text = driver.findElement(By.id("alertTextOK"));
        Assert.assertEquals(actualText, text.getText());

        // Закрытие нового окна
        driver.close();

        // Возврат к первому окну
        driver.switchTo().window(winHandleBefore);
    }

    @Test(groups = {"save"})
    public void testSaveUsers() throws Exception {

        // Сохранение дискриптора текущего окна
        String winHandleBefore = driver.getWindowHandle();
        testPage.clearUser();
        testPage.addUser("Max", "Moody");
        testPage.saveUser();

        // Переключение на новое окно
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        String actualText = "1 VIP(s) stored sucessfully";
        WebElement text = driver.findElement(By.id("alertTextOK"));
        System.out.println(text.getText());
        Assert.assertEquals(actualText, text.getText());

        // Закрытие нового окна
        driver.close();

        // Возврат к первому окну
        driver.switchTo().window(winHandleBefore);
    }

    @AfterClass
    public void afterMethod() {
        driver.quit();
    }

}
