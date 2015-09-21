package tests;

import com.epam.steps.Steps;
import java.awt.AWTException;
import javafx.scene.layout.Priority;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
/**
 *
 * @author Nadia
 */
public class TestForwardingSettings extends BaseAbstractTest {

    WebDriver driver;
    private final String THEME = "Message with attach";
    private final String THEME_WITHOUT_ATTACH = "Message without attach";
    private final String FILEPATH = "file.txt";
    private final String MESSAGE_TEXT = "Some text";
    private final String SUCCESS_MESSAGE_WITH_ATTACH = "Success. There is letter from user1 with attach and mark as important in trash";
    private final String SUCCESS_MESSAGE_WITHOUT_ATTACH = "Success. There is letter from user1 without attach and not mark as important in inbox";
    private final String FAIL_MESSAGE_WITHOUT_ATTACH = "Error.There is not letter from user1 without attach and not mark as important in inbox";
    public final static Logger log = Logger.getLogger(TestForwardingSettings.class);

    public TestForwardingSettings() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        steps = new Steps();
        steps.init();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        steps.closeDriver();
    }

    public void setPropertiesForwardingAndFilters() {
        steps.logIn(login2, password2);
        steps.goToSettings();
        steps.openToforwardPage();
        steps.setForvardUser(login3);
        steps.closeDriver();
        steps.init();
        steps.logIn(login3, password3);
        steps.confirmLetterForForwarding();
        steps.closeDriver();
        steps.init();
        steps.logIn(login2, password2);
        steps.goToSettings();
        steps.openToforwardPage();
        steps.setForwardACopyOfIncomingMail();
        steps.goToSettings();
        steps.openFilterPage();
        steps.createFilter(login1);
        steps.closeDriver();
        steps.init();
    }

    @Test
    public void testForwardingAndFilters() throws AWTException, InterruptedException {
        setPropertiesForwardingAndFilters();
        boolean status = true;
        steps.logIn(login1, password1);
        steps.sendLetterWithAttach(login2, THEME, FILEPATH);
        steps.sendMessageWithoutAttach(login2, THEME_WITHOUT_ATTACH, MESSAGE_TEXT);
        steps.closeDriver();
        steps.init();
        steps.logIn(login2, password2);
        steps.openTrashPage();
        Assert.assertEquals(steps.isLetterFromUser1WithAttachInTrash(THEME), status, SUCCESS_MESSAGE_WITH_ATTACH);
        steps.openTrashPage();
        Assert.assertTrue(steps.isLetterFromUser1WithAttachInTrash(THEME), SUCCESS_MESSAGE_WITH_ATTACH);
        steps.openMailPage();
        if (steps.isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(THEME_WITHOUT_ATTACH)) {
            log.info(SUCCESS_MESSAGE_WITHOUT_ATTACH);
        } else {
            status = false;
            log.error(FAIL_MESSAGE_WITHOUT_ATTACH);
        }
        Assert.assertTrue(steps.isLetterFromUser1WithAttachInTrash(THEME), SUCCESS_MESSAGE_WITHOUT_ATTACH);
        steps.closeDriver();
        steps.init();
        steps.logIn(login3, password3);
        Assert.assertEquals(steps.isLetterFromUser1WithoutAttachInInbox(THEME_WITHOUT_ATTACH), status, SUCCESS_MESSAGE_WITHOUT_ATTACH);
    }

}
