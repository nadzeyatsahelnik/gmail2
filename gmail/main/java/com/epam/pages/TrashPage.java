package com.epam.pages;

import com.epam.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class TrashPage extends AbstractPage {

    private final String TRASH_PATH = "https://mail.google.com/mail/u/0/#trash";
    private final String TOPIC_OF_THE_LETTER = "//table//div[@class='y6']/span[contains(text(),'Message with attach')]";
    private final String IMAGE_ATTACHMENT = "//img[@alt='Приложение']";
    private final String CONTAIN_MARK_IMPORTANT = "Отмечено как важное";
  
    @FindBy(xpath = "//img[@class='ajz']")
    private WebElement imgInfoAboutLetter;

    @FindBy(xpath = "//div[@class='ajB gt']//span[contains(text(),'Отмечено как важное')]")
    private WebElement infoAboutLetter;

    public TrashPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(TRASH_PATH);
    }

    public boolean isLetterFromUser1InTrash(String subject) {
        while (!Waits.isElementPresent(driver, TOPIC_OF_THE_LETTER)) {
            driver.navigate().refresh();
        }
        WebElement letter = driver.findElement(By.xpath(TOPIC_OF_THE_LETTER));
        letter.click();
        if (Waits.isElementPresent(driver, IMAGE_ATTACHMENT)) {
            imgInfoAboutLetter.click();
        }
        return infoAboutLetter.getText().contains(CONTAIN_MARK_IMPORTANT);
    }
}
