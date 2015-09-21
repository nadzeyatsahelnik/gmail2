package tests;

import com.epam.steps.Steps;
import com.epam.utils.GenerateSymbols;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Nadia
 */
public class TestSendMessageToSpam extends BaseAbstractTest {

    WebDriver driver;
    private final String THEME = "garbage";
    private final String TEXT_MESSAGE = "SPAM FOR YOU!";
    private final String SECOND_THEME = GenerateSymbols.createRandomString(5);
    private final String SECOND_TEXT_MESSAGE = GenerateSymbols.createRandomString(7);
    private final String nameOfSender = "Надя Цегел";

    @BeforeMethod
    public void setUpMethod() throws Exception {
        steps = new Steps();
        steps.init();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        steps.closeDriver();
    }

    @Test
    public void logIn() throws InterruptedException {
        steps.logIn(login1, password1);
        steps.sendMessage(login2, THEME, TEXT_MESSAGE);
        steps.closeDriver();
        steps.init();
        steps.logIn(login2, password2);
        steps.markAsSpam();
        steps.closeDriver();
        steps.init();
        steps.logIn(login1, password1);
        steps.sendMessage(login2, SECOND_THEME, SECOND_TEXT_MESSAGE);
        steps.closeDriver();
        steps.init();
        steps.logIn(login2, password2);
        Assert.assertTrue(steps.isLetterGoToSpam(nameOfSender));
    }

}
