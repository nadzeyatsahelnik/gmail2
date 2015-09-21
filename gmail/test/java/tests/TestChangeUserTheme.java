package tests;

import com.epam.steps.Steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class TestChangeUserTheme extends BaseAbstractTest {

    private final String MESSAGE = "Something wrong with changing theme";
    WebDriver driver;

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
    public void testChangeUserTheme() {
        steps.logIn(login1, password1);
        steps.changeUserTheme();
        Assert.assertTrue(steps.isBeachThemechange(), MESSAGE);
    }

}
