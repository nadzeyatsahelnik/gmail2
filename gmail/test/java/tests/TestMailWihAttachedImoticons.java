package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class TestMailWihAttachedImoticons extends BaseAbstractTest {

    WebDriver driver;
    private final String THEME = "Message with emoticons";
    private final String TEXT_MESSAGE = "Some text";

    @Test
    public void sendMessageWithEmoticons() {
        steps.logIn(login1, password1);
        steps.sendMessageWithEmoticons(login1, THEME, TEXT_MESSAGE);
        Assert.assertTrue(steps.isEmotioconsInMessage(THEME), THEME);
    }

}
