package com.epam.pages;

import com.epam.utils.CreateFile;
import com.epam.utils.Downloader;
import com.epam.utils.Waits;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadia
 */
public class MailPage extends AbstractPage {

    private final String BASE_URL = "https://mail.google.com/mail/#inbox";
    private final String linkToOpenConfirmLetterLetter = ".//table[@class='F cf zt']//tr//td[6]";
    private final String CONFIRMATION = "Подтверждено";
    private final String CONTAIN_STRING = "Gmail";
    private final String CONTAIN_MARK_IMPORTANT = "Important";
    private final static String TOPIC_OF_THE_LETTER = "//table//div[@class='y6']/span[contains(text(),'Message without attach')]";
    private final static String TOPIC_OF_THE_LETTER_WITH_EMOTICONS = "//table//div[@class='y6']/span[contains(text(),'Message with emoticons')]";
    private final static String IMAGE_ATTACHMENT = "//img[@alt='Приложение']";
    private final static String LOADING_BAR_WHEN_ATTACHED_FILE = "//div[@class='dR']";
    private final static String ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_25MB = "//span[@class='Kj-JD-K7-K0'][contains(text(),'25')]";

    @FindBy(xpath = ".//*[@class='T-I J-J5-Ji T-I-KE L3']")
    private WebElement send;

    @FindBy(xpath = ".//*[@name='to']")
    private WebElement writeRecipient;

    @FindBy(xpath = ".//*[@class='aoT']")
    private WebElement themeMessage;

    @FindBy(xpath = ".//*[@class='Am Al editable LW-avf']")
    private WebElement writeMessage;

    @FindBy(xpath = ".//*[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    private WebElement sendMessage;

    @FindBy(xpath = ".//table[@class='F cf zt']//tr[1]//td[2]//div[@class='T-Jo-auh']")
    private WebElement lastInboxMessageCheckBox;

    @FindBy(xpath = ".//div[@class='T-I J-J5-Ji nN T-I-ax7 T-I-Js-Gs T-I-Js-IF ar7']")
    private WebElement spamButton;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3'][ancestor::div[@class='Cr aqJ'][preceding-sibling::div[@gh='mtb']]]")
    private WebElement buttonSettings;

    @FindBy(xpath = "//div[@class='J-N aMS']")
    private WebElement buttonSettingsInContextMenu;

    @FindBy(xpath = "//div[@class='a3s']/a[@target='_blank']")
    private WebElement linkToConfirmLetterForForwarding;

    @FindBy(xpath = "//div[@class = 'a1 aaA aMZ']")
    private WebElement buttonToAttachFile;

    @FindBy(xpath = "//img[@class='ajz']")
    private WebElement imgInfoAboutLetter;

    @FindBy(xpath = "//div[@class='ajB gt']//span")
    private WebElement infoAboutLetter;

    @FindBy(xpath = "//div[@id = 'pbwc']//div[@class = 'J-N-Jz']")
    private WebElement buttonThemeInContextMenu;

    @FindBy(xpath = "//div[@class='QT aaA aMZ']")
    private WebElement emoticonIcon;

    @FindBy(xpath = "//button[@class='a8v a8t a8t'][@title= 'Эмоции']")
    private WebElement emoticonPanel;

    @FindBy(xpath = "//button[@class='a8m'][@string= '1f608']")
    private WebElement buttonSmile;

    @FindBy(xpath = "//button[@class='a8m'][@string= '1f607']")
    private WebElement buttonSmile2;

    private final String smile = "//button[@class='a8m']";
    private final String smilesInInbox = "//div[@class = 'adn ads']//img[@class = 'CToWUd']";

    public MailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(BASE_URL);
    }

    public void clickButtonSendMessage() {
        send.click();
    }

    public void fillFields(String addresse, String theme, String messageText) {
        writeRecipient.sendKeys(addresse);
        themeMessage.sendKeys(theme);
        writeMessage.sendKeys(messageText);
    }

    public void clickSendMessage() {
        sendMessage.click();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public SpamPage markAsSpam() throws InterruptedException {
        lastInboxMessageCheckBox.click();
        spamButton.click();
        return new SpamPage(driver);
    }

    public SettingsPage goToSettings() {
        buttonSettings.click();
        buttonSettingsInContextMenu.click();
        return new SettingsPage(driver);
    }

    public ThemePage goToSettingsTheme() {
        buttonSettings.click();
        buttonThemeInContextMenu.click();
        return new ThemePage(driver);
    }

    public ThemePage goToThemePanel() {
        buttonSettings.click();
        return new ThemePage(driver);

    }

    public void openConfirmLetter() {
        Collection<WebElement> webElements = driver.findElements(By.xpath(linkToOpenConfirmLetterLetter));
        for (WebElement collectionLetters : webElements) {
            if (collectionLetters.getText().contains(CONTAIN_STRING)) {
                collectionLetters.click();
            }
        }
    }

    public void confirmLetterForForwarding() {
        linkToConfirmLetterForForwarding.click();
     //   String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
//        Iterator<String> it = handles.iterator();
//        while (it.hasNext()) {
//            if (currentWindow.equals(it.next())) { 
        for (String handle : handles) {
            WebDriver window = driver.switchTo().window(handle);
            //  WebDriver window = driver.switchTo().window(it.next());
            if (window.getTitle().contains(CONFIRMATION));
            window.close();
        }
//            }
//        }
    }

    public void sendLetterWithAttach(String addresse, String theme, String filePath) throws AWTException {
        writeRecipient.sendKeys(addresse);
        themeMessage.sendKeys(theme);
        buttonToAttachFile.click();
        StringSelection stringSelection = CreateFile.createFile(filePath);
        Downloader.javaRobotForAttachFile(stringSelection);
        if (Waits.isElementPresent(driver, LOADING_BAR_WHEN_ATTACHED_FILE)) {
            Waits.waitForElementInvisibility(driver, LOADING_BAR_WHEN_ATTACHED_FILE, 120);
        }
    }

    public boolean isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(String subject) {
        while (!Waits.isElementPresent(driver, TOPIC_OF_THE_LETTER)) {
            driver.navigate().refresh();
        }
        WebElement letter = driver.findElement(By.xpath(String.format(TOPIC_OF_THE_LETTER, subject)));
        letter.click();
        if (!Waits.isElementPresent(driver, IMAGE_ATTACHMENT)) {
            imgInfoAboutLetter.click();
        }
        return infoAboutLetter.getText().contains(CONTAIN_MARK_IMPORTANT);
    }

    public boolean isLetterFromUser1WithoutAttachInInbox(String subject) {
        while (!Waits.isElementPresent(driver, TOPIC_OF_THE_LETTER)) {
            driver.navigate().refresh();
        }
        WebElement letter = driver.findElement(By.xpath(TOPIC_OF_THE_LETTER));
        letter.click();
        return !Waits.isElementPresent(driver, IMAGE_ATTACHMENT);
    }

    public void attachBigFile(String addresse, String theme, String filePath, long size) throws IOException, AWTException {
        writeRecipient.sendKeys(addresse);
        themeMessage.sendKeys(theme);
        buttonToAttachFile.click();
        StringSelection stringSelection = CreateFile.createFileWithSize(filePath, size);
        Downloader.javaRobotForAttachFile(stringSelection);
        if (Waits.isElementPresent(driver, LOADING_BAR_WHEN_ATTACHED_FILE)) {
            Waits.waitForElementInvisibility(driver, LOADING_BAR_WHEN_ATTACHED_FILE, 120);
        }
    }

    public boolean isWarningMessageAppeared() {
        return Waits.isElementPresent(driver, ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_25MB);
    }

    public void sendMessageWithEmoticons() {
        emoticonIcon.click();
        emoticonPanel.click();
        List<WebElement> links = driver.findElements(By.xpath(smile));
        Iterator<WebElement> iter = links.iterator();
        while (iter.hasNext()) {
            WebElement we = iter.next();
            if (we.equals(buttonSmile) || we.equals(buttonSmile2)) {
                we.click();
            }
        }
    }

    public boolean isEmotioconsInMessage(String subject) {
        WebElement letter = driver.findElement(By.xpath(String.format(TOPIC_OF_THE_LETTER_WITH_EMOTICONS, subject)));
        letter.click();
        if (Waits.isElementPresent(driver, IMAGE_ATTACHMENT)) {
            Waits.waitForElementInvisibility(driver, IMAGE_ATTACHMENT, 120);
        }
        return Waits.isElementPresent(driver, smilesInInbox);
    }
}
