package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class SpamPage extends AbstractPage {

    private final String URL = "https://mail.google.com/mail/#spam";
    private final String NAME = "name";
    
    @FindBy(xpath = ".//table[@class='cf ix']//span[@class='gD']")
    private WebElement textOfMessage;
   
    @FindBy(xpath = ".//table[@class='F cf zt']//tr[1]")
    private WebElement lastSpamMessage;

    public SpamPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(URL);
    }

    public boolean lastMessageContainsStringInside(String nameOfSender) {
        openPage();
        getDriver().navigate().refresh();
        lastSpamMessage.click();
        return textOfMessage.getAttribute(NAME).contains(nameOfSender);
    }
}
