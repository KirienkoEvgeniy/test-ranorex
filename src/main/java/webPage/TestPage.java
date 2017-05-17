package webPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.io.IOException;


import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.FileAssert.fail;

public class TestPage {
    private WebDriver driver;
    /**
     * Таблица
     */
    @FindBy(id = "VIPs")
    private WebElement table;

    /**
     * Имя пользователя
     */
    @FindBy(id = "FirstName")
    private WebElement firstName;

    /**
     * Фамилия пользователя
     */
    @FindBy(id = "LastName")
    private WebElement lastName;

    /**
     * Кнопка добавления
     */
    @FindBy(id = "Add")
    private WebElement btnAdd;

    /**
     * Кнопка удаления
     */
    @FindBy(id="Delete")
    private WebElement btnDelete;

    /**
     * Кнопка загрузки пользователя
     */
    @FindBy(id="Load")
    private WebElement btnLoad;

    /**
     * Кнопка загрузки пользователя
     */
    @FindBy(id="Clear")
    private WebElement btnClear;

    /**
     * Кнопка сохранение
     */
    @FindBy(id="Save")
    private WebElement btnSave;

    /**
     * Поиск строк в таблице
     * @return
     */
    public List<WebElement> getTableRows(){
        return table.findElements(By.tagName("tr"));
    }

    /**
     * Добавление пользователя
     * @param firstN
     * @param lastN
     */
    public void addUser(String firstN, String lastN) {
        firstName.clear();
        firstName.sendKeys(firstN);
        lastName.clear();
        lastName.sendKeys(lastN);
        btnAdd.click();
    }

    /**
     * Удаление пользователя
     */
    public  void deleteUser(){
        btnDelete.click();
    }

    /**
     * Сщхранение пользователя
     */
    public  void saveUser(){
        btnSave.click();
    }

    /**
     * Очистка списка пользователей
     */
    public void clearUser()
    {
        btnClear.click();
    }
    /**
     * Загрузка списка пользователей
     */
    public void loadUser()
    {
        btnLoad.click();
    }

    public TestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
