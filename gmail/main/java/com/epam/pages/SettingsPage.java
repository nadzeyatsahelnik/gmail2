package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class SettingsPage extends AbstractPage {

    private final String SETTINGS_URL = "https://mail.google.com/mail/?hl=en#settings";
    
    @FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/u/0/#settings/fwdandpop']")
    private WebElement linkForwardingAndPopImap;
    
    @FindBy(xpath = "//a[@class='f0 ou'][@href='https://mail.google.com/mail/u/0/#settings/filters']")
    private WebElement linkFilters;
    
    @FindBy(xpath = "//a[@class='f0'][@href='https://mail.google.com/mail/u/0/#settings/oldthemes']")
    private WebElement linkThemes;

    public SettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(SETTINGS_URL);
    }

    public void goToForwardPage() {
        linkForwardingAndPopImap.click();
    }

    public void goToFilterPage() {
        linkFilters.click();
    }

    public void goToThemesPage() {
        linkThemes.click();
    }
}
