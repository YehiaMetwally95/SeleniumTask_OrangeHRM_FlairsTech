package pages;

import io.qameta.allure.Step;
import objectModelsForAPIs.AddEmployeeResponseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import yehiaEngine.assertions.CustomSoftAssert;
import java.util.Arrays;
import java.util.List;

import static yehiaEngine.utilities.RandomDataGenerator.*;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueName;

public class UserCreationPage extends HomePage{
/*    //Enums
    @AllArgsConstructor
    @Getter
    public enum UserRole {
        Admin("Admin"),
        ESS("ESS");
        private final String userRole;
    }

    @AllArgsConstructor
    @Getter
    public enum Status {
        Enabled("Enabled"),
        Disabled("Disabled");
        private final String status;
    }*/

    //Variables
    private String username;

    //Locators
    final private By addUserHeader = By.cssSelector(".orangehrm-main-title");
    final private By saveButton = By.xpath("//button[@type='submit']");
    final private By employeeNameLabel = By.xpath("//label[contains(.,'Employee Name')]");
    final private By employeeNameTextBox = RelativeLocator.with(By.tagName("input")).below(employeeNameLabel);
    final private By usernameLabel = By.xpath("//label[contains(.,'Username')]");
    final private By usernameTextBox = RelativeLocator.with(By.tagName("input")).below(usernameLabel);
    final private By passwordLabel = By.xpath("//label[contains(.,'Password')]");
    final private By passwordTextBox = RelativeLocator.with(By.tagName("input")).below(passwordLabel);
    final private By confirmPasswordLabel = By.xpath("//label[contains(.,'Confirm Password')]");
    final private By confirmPasswordTextBox = RelativeLocator.with(By.tagName("input")).below(confirmPasswordLabel);
    final private By userRoleLabel = By.xpath("//label[contains(.,'User Role')]");
    final private By userRoleDropDown = RelativeLocator.with(By.cssSelector(".oxd-select-text--arrow")).below(userRoleLabel);
    final private By statusLabel = By.xpath("//label[contains(.,'Status')]");
    final private By statusDropDown = RelativeLocator.with(By.cssSelector(".oxd-select-text--arrow")).below(statusLabel);

    private By getUserRoleLocator(String userRole) {
        return By.xpath("//div[@role='listbox']//child::*[contains(.,'"+ userRole +"')]");
    }

    private By getStatusLocator(String status) {
        return By.xpath("//div[@role='listbox']//child::*[contains(.,'"+ status +"')]");
    }

    private By getEmployeeName(String employeeName) {
        return By.xpath("//div[@role='listbox']//child::*[contains(.,'"+ employeeName +"')]");
    }

    //Constructor
    public UserCreationPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Add New User")
    public AdminPage addNewUser(String userRole,String employeeName,String status,
                                String userName,String password,String confirmPassword)
    {
        action
                .press(userRoleDropDown)
                .press(getUserRoleLocator(userRole))

                .type(employeeNameTextBox,employeeName)
                .press(getEmployeeName(employeeName))

                .press(statusDropDown)
                .press(getStatusLocator(status))

                .type(usernameTextBox,userName)
                .type(passwordTextBox,password)
                .type(confirmPasswordTextBox,confirmPassword)
                .press(saveButton);

        return new AdminPage(driver);
    }

    @Step("Add New User")
    public UserCreationPage addNewUserWithRandomData(String userFullName)
    {
        fillRandomUserRole()
                .fillEmployeeName(userFullName)
                .fillRandomStatus()
                .fillRandomUsername()
                .fillRandomPassword()
                .clickOnSaveButton();
        return this;
    }

    @Step("Get Username")
    public String getUsername()
    {
        return this.username;
    }

    //Validations
    @Step("Verify UserCreation Page is Opened")
    public UserCreationPage verifyUserCreationPageIsOpened(String header)
    {
        verifyUserCreationHeader(header);
        return this;
    }

    //Private Methods
    @Step("Verify UserCreation Header is Displayed")
    private UserCreationPage verifyUserCreationHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(addUserHeader),header);
        return this;
    }

    @Step("Fill User Role With Random Value")
    private UserCreationPage fillRandomUserRole()
    {
        List<String> userRoleList = Arrays.asList("Admin","ESS");
        action
                .press(userRoleDropDown)
                .press(getUserRoleLocator(generateItemFromList(userRoleList)));
        return this;
    }

    @Step("Fill Status With Random Value")
    private UserCreationPage fillRandomStatus()
    {
        List<String> statusList = Arrays.asList("Enabled","Disabled");
        action
                .press(statusDropDown)
                .press(getStatusLocator(generateItemFromList(statusList)));
        return this;
    }

    @Step("Fill Employee Name")
    private UserCreationPage fillEmployeeName(String employeeName)
    {
        action
                .type(employeeNameTextBox,employeeName)
                .press(getEmployeeName(employeeName));
        return this;
    }

    @Step("Fill Username With Random Value")
    private UserCreationPage fillRandomUsername()
    {
        username = generateUniqueName();
        action.type(usernameTextBox,username);
        return this;
    }

    @Step("Fill Password With Random Value")
    private UserCreationPage fillRandomPassword()
    {
        String password = generateStrongPassword();
        action
                .type(passwordTextBox,password)
                .type(confirmPasswordTextBox,password);
        return this;
    }

    @Step("Click on Save Button")
    private AdminPage clickOnSaveButton()
    {
        action.press(saveButton);
        return new AdminPage(driver);
    }
}
