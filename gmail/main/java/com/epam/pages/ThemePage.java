package com.epam.pages;

import com.epam.utils.CreateFile;
import com.epam.utils.Downloader;
import com.epam.utils.Waits;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class ThemePage extends AbstractPage {

    private final String THEME_URL = "https://mail.google.com/mail/u/0/#inbox/themes";
    private final String theme = "//img[@class='ao0']";
    private final static String errorLable = "//div[@class = 'Xf-eg-Dg-Lb'][contains(text(), 'не поддерживается')]";

    @FindBy(xpath = "//div[text()='Мои фото']")
    private WebElement buttonToUploadPhoto;

    @FindBy(xpath = "//iframe[@class = 'KA-JQ']")
    private WebElement frameToUploadImage;

    @FindBy(xpath = "//div[text() = 'Загрузка фото']")
    private WebElement linkToUploadImage;

    @FindBy(xpath = "//div[text() = 'Выберите файл на компьютере']")
    private WebElement buttonToUploadImagefromComputer;

    @FindBy(xpath = "//a[@class ='e NvzLyc']")
    private WebElement establishUserTheme;

    @FindBy(xpath = "//img[@class ='a7G'][@src = '//ssl.gstatic.com/ui/v1/icons/mail/themes/beach2/previewHD.png']")
    private WebElement beachTheme;

    @FindBy(xpath = "//span[@class = 'Kj-JD-K7-Jq a8Z'][@data-tooltip = 'Сохранить и закрыть']")
    private WebElement closeThemeWindow;

    public ThemePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.get(THEME_URL);
    }

    public void changeBackgroundImage(String filePath) throws IOException, FileNotFoundException, AWTException {
        buttonToUploadPhoto.click();
        driver.switchTo().frame(frameToUploadImage);
        linkToUploadImage.click();
        buttonToUploadImagefromComputer.click();
        attachNotAnImage(filePath);
    }

    public boolean isErrorMessageAppear() {
        return Waits.isElementPresent(driver, errorLable);
    }

    public void changeUserTheme() {
        establishUserTheme.click();
        beachTheme.click();
        closeThemeWindow.click();
    }

    public boolean isBeachThemeChanged() {
        return Waits.isElementPresent(driver, theme);
    }

    private void attachNotAnImage(String filePath) throws FileNotFoundException, IOException, AWTException {
        StringSelection stringSelection = CreateFile.createFile(filePath);
        Downloader.javaRobotForAttachFile(stringSelection);
    }
}
