package baseTest;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;

import static yehiaEngine.browserActions.WindowManager.*;
import static yehiaEngine.driverManager.BrowserFactory.*;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;

public class BaseTest {
    //Variables
    protected ThreadLocal<RemoteWebDriver> isolatedDriver;
    public String jsonFilePathForSessionDataForAdmin = "src/test/resources/SessionData/SessionDataForAdmin.json";

    @BeforeMethod
    public void setUpAndOpenBrowser() throws MalformedURLException {
        //Open Browser
        isolatedDriver = openBrowser();

        //Navigate to Website URL
        navigateToURL(getDriver(isolatedDriver),getPropertiesValue("baseUrlWeb"));
    }

    @AfterMethod
    public void closeBrowser()
    {
        //Close All Browser Tabs
        closeAllWindows(getDriver(isolatedDriver));
    }
}
