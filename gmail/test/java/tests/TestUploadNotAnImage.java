package tests;

import com.epam.steps.Steps;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class TestUploadNotAnImage extends BaseAbstractTest {

    private final String FILE_PATH = "file.txt";
    private final String ERROR_MESSAGE = "Error message don't appear";
    public final static Logger log = Logger.getLogger(TestUploadNotAnImage.class);
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
    public void testSettingsTheme() throws IOException, FileNotFoundException, AWTException {
        steps.logIn(login1, password1);
        steps.changeBackgroundImage(FILE_PATH);
        Assert.assertTrue(steps.isErrorMessageAppear(), ERROR_MESSAGE);
    }
}
