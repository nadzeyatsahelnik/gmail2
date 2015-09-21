package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class LogInPage extends AbstractPage {

    private final String BASE_URL = "https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier";
    @FindBy(id = "Email")
    private WebElement email;
    @FindBy(id = "next")
    private WebElement buttonNext;
    @FindBy(id = "Passwd")
    private WebElement password;
    @FindBy(id = "signIn")
    private WebElement buttonSign;
    @FindBy(xpath = ".//*[@class='gb_pa gb_l gb_r gb_h']")
    private WebElement clicktoSettings;
    @FindBy(xpath = ".//*[@class = 'gb_cc gb_jc gb_a']")
    private WebElement clickToSignOut;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(BASE_URL);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public MailPage logIn(String username, String passw) {
        email.sendKeys(username);
        buttonNext.click();
        password.sendKeys(passw);
        buttonSign.click();
        return new MailPage(driver);
    }

    public void logOut() {
        clicktoSettings.click();
        clickToSignOut.click();
    }
}
