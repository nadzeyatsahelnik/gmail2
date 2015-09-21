package com.epam.pages;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author Nadia
 */
public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public abstract void openPage();
}
