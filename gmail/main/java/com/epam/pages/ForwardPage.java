package com.epam.pages;

import com.epam.utils.Waits;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class ForwardPage extends AbstractPage {

    private final String FORWARD_URL = "https://mail.google.com/mail/#settings/fwdandpop";
    @FindBy(xpath = "//input[@type = 'button']")
    private WebElement buttonToChoseForwardAddress;
    @FindBy(xpath = "//div[@class ='PN']/input[@type = 'text']")
    private WebElement fieldForwardAddress;
    @FindBy(xpath = "//button[@name = 'next']")
    private WebElement buttonNextOnForwarding;
    @FindBy(xpath = "//button[@class = 'J-at1-auR'][@name = 'ok']")
    private WebElement buttonConfirmOk;
    @FindBy(xpath = "//input[@type ='submit']")
    private WebElement buttonNext;
    @FindBy(xpath = "//input[@name='sx_em'][@value='1']")
    private WebElement radioButtonForwarardACopyOfIncomingMail;
    @FindBy(xpath = "//button[ancestor::div[@class='nH Tv1JD']][@guidedhelpid ='save_changes_button']")
    private WebElement buttonSaveChangesInForwarding;
    private final static String MESSAGE_LOADING = "//span[@class='v1']";

    public ForwardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(FORWARD_URL);
    }

    public void setForwardUser3(String userAddress) {
        buttonToChoseForwardAddress.click();
        fieldForwardAddress.sendKeys(userAddress);
        buttonNextOnForwarding.click();
        String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            if (currentWindow.equals(it.next())) {
                driver.switchTo().window(it.next());
                buttonNext.click();
            }
        }
        driver.switchTo().window(currentWindow);
        buttonConfirmOk.click();
    }

    public void ChooseRadiobuttonToForwardCopy() {
        radioButtonForwarardACopyOfIncomingMail.click();
        Waits.waitForElementVisible(driver, buttonSaveChangesInForwarding, 5);
        buttonSaveChangesInForwarding.click();
        Waits.waitForElementInvisibility(driver, MESSAGE_LOADING, 10);
    }
}
