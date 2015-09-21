package com.epam.pages;

import com.epam.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class FiltersPage extends AbstractPage {

    private final String FILTERS_URL = "https://mail.google.com/mail/u/4/#settings/filters";
    private final static String MESSAGE_LOADING = "/span[text() = 'Фильтр создан.']";
    @FindBy(xpath = "//span[@class = 'sA'][@role = 'link'][contains(text(),'Создать новый фильтр')]")
    private WebElement linkCreateNewFilter;
    @FindBy(xpath = "//div[@class ='SK ZF-zT']//input[@class = 'ZH nr aQa']")
    private WebElement inputFromWhom;
    @FindBy(xpath = "//div[@class ='SK ZF-zT']//input[@type = 'checkbox']")
    private WebElement checkboxHasAttachment;
    @FindBy(xpath = "//div[@class ='SK ZF-zT']//div[@class = 'acM']")
    private WebElement linkCreateFilterAsRequested;
    @FindBy(xpath = "//div[@class ='ZZ']//label[text() = 'Удалить']")
    private WebElement checkboxDeleteIt;
    @FindBy(xpath = "//div[@class ='ZZ']//label[text() = 'Всегда помечать как важное']")
    private WebElement checkboxMarkAsImportant;
    @FindBy(xpath = "//div[@class ='ZZ']//div[@class = 'T-I J-J5-Ji Zx acL T-I-atl L3']")
    private WebElement buttonCreateFilter;

    public FiltersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(FILTERS_URL);
    }

    public void createNewFilter(String userName) {
        linkCreateNewFilter.click();
        inputFromWhom.sendKeys(userName);
        checkboxHasAttachment.click();
        linkCreateFilterAsRequested.click();
        checkboxDeleteIt.click();
        checkboxMarkAsImportant.click();
        buttonCreateFilter.click();
        Waits.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
    }
}
