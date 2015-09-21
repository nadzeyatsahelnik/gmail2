package tests;

import com.epam.steps.Steps;
import java.util.ResourceBundle;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public abstract class BaseAbstractTest {

    public static Steps steps;
    public ResourceBundle resource = ResourceBundle.getBundle("com.epam.resurces.userData");
    public String login1 = resource.getString("login1");
    public String password1 = resource.getString("password1");
    public String login2 = resource.getString("login2");
    public String password2 = resource.getString("password2");
    public String login3 = resource.getString("login3");
    public String password3 = resource.getString("password3");

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        steps.init();
    }

    @AfterClass
    public void stopBrowser() {
        steps.closeDriver();
    }
}
