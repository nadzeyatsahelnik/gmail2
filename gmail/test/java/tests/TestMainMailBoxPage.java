package tests;

import com.epam.steps.Steps;
import java.awt.AWTException;
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
public class TestMainMailBoxPage extends BaseAbstractTest {

    WebDriver driver;
    public final static Logger log = Logger.getLogger(TestForwardingSettings.class);
    private final String THEME = "Message with attach";
    private final String FAIL_MESSAGE = "Something wrong";
    private final String SUCCESS_MESSAGE = "Success.Warning message that size of file is bigger than 25 mb. ";
    private final String FILE_PATH = "file.txt";
    private final int size = 1024 * 1024 * 26;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        steps = new Steps();
        steps.init();
        steps.logIn(login1, password1);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        steps.closeDriver();
    }

    @Test
    public void testAttachBigFile() throws IOException, AWTException {
        steps.attachFileBiggerThen25Mb(login1, THEME, FILE_PATH, size);
        Assert.assertTrue(steps.isWarningMessageAppeared(), FAIL_MESSAGE);
        log.info(SUCCESS_MESSAGE);

    }
}
