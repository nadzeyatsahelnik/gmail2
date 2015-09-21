package com.epam.utils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class Waits {

    public static void waitForElementVisible(WebDriver webDriver, WebElement webElement, int time) {
        WebDriverWait driverWait = new WebDriverWait(webDriver, time);
        driverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementInvisibility(WebDriver webDriver, final String elementLocator, int time) {
        WebDriverWait driverWait = new WebDriverWait(webDriver, time);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementLocator)));
    }

    public static boolean isElementPresent(WebDriver webDriver, final String elementLocator) {
        final int numberOfElements = webDriver.findElements(By.xpath(elementLocator)).size();
        return numberOfElements > 0;
    }
}
