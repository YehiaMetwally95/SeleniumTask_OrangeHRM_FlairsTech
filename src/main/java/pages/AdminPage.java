package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.managers.JsonManager;

public class AdminPage extends HomePage{
    //Variables
    String jsonFilePathForAddUser = "src/test/resources/TestDataJsonFiles/AddUserTestsWithStaticData_TestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddUser);

    //Locators
    final private By adminHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    final private By totalRecordsNumber = By.xpath("//span[contains(.,'Record')]");
    final private By addUserButton = By.cssSelector(".orangehrm-header-container button");
    final private By usernameLabel = By.xpath("//label[contains(.,'Username')]");
    final private By usernameTextBox = RelativeLocator.with(By.tagName("input")).below(usernameLabel);
    final private By searchButton = By.xpath("//button[@type='submit']");
    final private By deleteButton = By.xpath("//*[contains(@class,'bi-trash')]/ancestor::button");
    final private By yesDeleteButton = By.xpath("//button[contains(.,'Delete')]");
    final private By resetButton = By.xpath("//button[contains(.,'Reset')]");

    private By getSearchedUsernameLocator(String username) {
        return By.xpath("//div[@class='orangehrm-container']/descendant::div[.='"+username+"']");
    }


    //Constructor
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Save Current Total Number of Records")
    public AdminPage saveCurrentTotalNumberOfRecords(String key)
    {
        String fullString = action.readText(totalRecordsNumber);
        int recordNumber = Integer.parseInt(fullString.replaceAll("[^0-9]", ""));
        json.setData(key, String.valueOf(recordNumber));
        return this;
    }

    @Step("Get Current Number of Records")
    public int getCurrentTotalNumberOfRecords()
    {
        String fullString = action.readText(totalRecordsNumber);
        return Integer.parseInt(fullString.replaceAll("[^0-9]", ""));
    }

    @Step("Navigate to User Creation Page")
    public UserCreationPage navigateToUserCreationPage()
    {
        action.press(addUserButton);
        return new UserCreationPage(driver);
    }

    @Step("Search For Username")
    public AdminPage searchForUsername(String username)
    {
        action
                .type(usernameTextBox,username)
                .press(searchButton);
        return this;
    }

    @Step("Delete Username Record")
    public AdminPage deleteUsernameRecord()
    {
        action
                .press(deleteButton)
                .press(yesDeleteButton)
                .press(resetButton);
        return this;
    }


    //Validations
    @Step("Verify Admin Page is Opened")
    public AdminPage verifyAdminPageIsOpened(String header)
    {
        verifyAdminHeader(header);
        return this;
    }

    @Step("Assert Total Number Of Records Increased by 1")
    public AdminPage assertTotalRecordNumberIncreasedBy1()
    {
        int oldValue = Integer.parseInt(json.getData("TotalEmployeeRecords.OldValue"));
        int newValue = Integer.parseInt(json.getData("TotalEmployeeRecords.NewValue"));

        CustomAssert.assertEquals(newValue,oldValue+1);
        return this;
    }

    @Step("Assert Total Number Of Records Decreased by 1")
    public AdminPage assertTotalRecordNumberDecreasedBy1()
    {
        int oldValue = Integer.parseInt(json.getData("TotalEmployeeRecords.OldValue"));
        int newValue = Integer.parseInt(json.getData("TotalEmployeeRecords.NewValue"));

        CustomAssert.assertEquals(newValue,oldValue-1);
        return this;
    }

    @Step("Assert Total Number Of Records Increased by 1")
    public AdminPage assertTotalRecordNumberIncreasedBy1(int oldValue,int newValue)
    {
        CustomAssert.assertEquals(newValue,oldValue+1);
        return this;
    }

    @Step("Assert Total Number Of Records Decreased by 1")
    public AdminPage assertTotalRecordNumberDecreasedBy1(int oldValue,int newValue)
    {
        CustomAssert.assertEquals(newValue,oldValue-1);
        return this;
    }

    @Step("Verify Username Displayed in Search Result")
    public AdminPage verifyUsernameDisplayedInSearchResult(String userName)
    {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(getSearchedUsernameLocator(userName)));
        return this;
    }

    //Private Methods
    @Step("Verify Admin Header is Displayed")
    private AdminPage verifyAdminHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(adminHeader),header);
        return this;
    }
}
