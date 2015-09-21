package com.epam.steps;

import com.epam.pages.FiltersPage;
import com.epam.pages.ForwardPage;
import com.epam.pages.LogInPage;
import com.epam.pages.MailPage;
import com.epam.pages.SettingsPage;
import com.epam.pages.SpamPage;
import com.epam.pages.ThemePage;
import com.epam.pages.TrashPage;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Nadia
 */
public class Steps {

    private WebDriver driver;

    public void init() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void closeDriver() {
        driver.quit();
    }

    public MailPage logIn(String username, String password) {
        LogInPage firstUserPage = new LogInPage(driver);
        firstUserPage.openPage();
        return firstUserPage.logIn(username, password);

    }

    public void openMailPage() {
        MailPage mailPage = new MailPage(driver);
        mailPage.openPage();
    }

    public void sendMessage(String addresse, String theme, String textMessage) throws InterruptedException {

        MailPage mailPage = new MailPage(driver);
        mailPage.clickButtonSendMessage();
        mailPage.fillFields(addresse, theme, textMessage);
        mailPage.clickSendMessage();
        mailPage.refreshPage();
    }

    public void markAsSpam() throws InterruptedException {
        MailPage mailPage = new MailPage(driver);
        mailPage.markAsSpam();
    }

    public void logOut() {
        LogInPage firstUserPage = new LogInPage(driver);
        firstUserPage.logOut();
    }

    public boolean isLetterGoToSpam(String senderName) {
        SpamPage spamPage = new SpamPage(driver);
        return spamPage.lastMessageContainsStringInside(senderName);
    }

    public void goToSettings() {
        MailPage mailPage = new MailPage(driver);
        mailPage.goToSettings();
    }

    public void openToforwardPage() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.goToForwardPage();

    }

    public void setForvardUser(String address) {
        ForwardPage forwardPage = new ForwardPage(driver);
        forwardPage.setForwardUser3(address);
    }

    public void confirmLetterForForwarding() {
        MailPage mailPage = new MailPage(driver);
        mailPage.openConfirmLetter();
        mailPage.confirmLetterForForwarding();
    }

    public void setForwardACopyOfIncomingMail() {
        ForwardPage forwardPage = new ForwardPage(driver);
        forwardPage.ChooseRadiobuttonToForwardCopy();
    }

    public void openFilterPage() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.goToFilterPage();
    }

    public void createFilter(String userName) {
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.createNewFilter(userName);
    }

    public void sendLetterWithAttach(String addresse, String theme, String filePath) throws AWTException, InterruptedException {
        MailPage mailPage = new MailPage(driver);
        mailPage.clickButtonSendMessage();
        mailPage.sendLetterWithAttach(addresse, theme, filePath);
        mailPage.clickSendMessage();

    }

    public void sendMessageWithoutAttach(String addresse, String theme, String textMessage) throws InterruptedException {
        MailPage mailPage = new MailPage(driver);
        mailPage.clickButtonSendMessage();
        mailPage.fillFields(addresse, theme, textMessage);
        mailPage.clickSendMessage();
        mailPage.refreshPage();
    }

    public void openTrashPage() {
        TrashPage trashPage = new TrashPage(driver);
        trashPage.openPage();
    }

    
    public boolean isLetterFromUser1WithAttachInTrash(String theme) {
        TrashPage trashPage = new TrashPage(driver);
        return trashPage.isLetterFromUser1InTrash(theme);
    }

    public boolean isLetterFromUser1WithoutAttachInInbox(String theme) {
        MailPage mailPage = new MailPage(driver);
        return mailPage.isLetterFromUser1WithoutAttachInInbox(theme);

    }

    public boolean isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(String theme) {
        MailPage mailPage = new MailPage(driver);
        return mailPage.isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(theme);

    }

    public void attachFileBiggerThen25Mb(String addresse, String theme, String filePath, long size) throws IOException, AWTException {
        MailPage mailPage = new MailPage(driver);
        mailPage.clickButtonSendMessage();
        mailPage.attachBigFile(addresse, theme, filePath, size);
        mailPage.clickSendMessage();
    }

    public boolean isWarningMessageAppeared() {
        MailPage mailPage = new MailPage(driver);
        return mailPage.isWarningMessageAppeared();
    }

    public void changeBackgroundImage(String filePath) throws IOException, FileNotFoundException, AWTException {
        MailPage mailPage = new MailPage(driver);
        mailPage.goToSettingsTheme();
        ThemePage themePage = new ThemePage(driver);
        themePage.changeBackgroundImage(filePath);
    }

    public boolean isErrorMessageAppear() {
        ThemePage themePage = new ThemePage(driver);
        return themePage.isErrorMessageAppear();
    }

    public void sendMessageWithEmoticons(String addresse, String theme, String textMessage) {
        MailPage mailPage = new MailPage(driver);
        mailPage.clickButtonSendMessage();
        mailPage.fillFields(addresse, theme, textMessage);
        mailPage.sendMessageWithEmoticons();
        mailPage.clickSendMessage();
    }

    public boolean isEmotioconsInMessage(String subject) {
        MailPage mailPage = new MailPage(driver);
        return mailPage.isEmotioconsInMessage(subject);
    }

    public void changeUserTheme() {
        MailPage mailPage = new MailPage(driver);
        mailPage.goToSettings();
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.goToThemesPage();
        ThemePage themePage = new ThemePage(driver);
        themePage.changeUserTheme();
    }

    public boolean isBeachThemechange() {
        ThemePage themePage = new ThemePage(driver);
        MailPage mailPage = new MailPage(driver);
        mailPage.openPage();
        return themePage.isBeachThemeChanged();
    }
}
