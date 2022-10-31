import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends TestBase{

    @BeforeMethod (alwaysRun = true)
    public void precondition(){
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
        logger.info("User logged out");

    }

    @Test (dataProvider = "datalogin", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Login scenario success was used data email" +email+ " && password "+password);

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert for successful login passed");
    }


    @Test(dataProvider = "dataModelUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelsDP(User user){

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("User logged in successfully with such data: sonka04@gmail.com, Sonka04$");

    }
    @Test (groups = {"smoke"})
    public void loginSuccessModels(){
        User user = new User().withEmail("sonka04@gmail.com").withPassword("Sonka04$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("User logged in successfully with such data: sonka04@gmail.com, Sonka04$");
        logger.info("Assert for successful login passed");

    }

    @Test
    public void negativeWrongEmail(){
        User user = new User().withEmail("sonka04gmail.com").withPassword("Sonka04$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitWithChecking();

       Assert.assertEquals(app.getHelperUser().getErrorMessage(), "It'snot look like email");
    }

    @Test (enabled = false)//(Bug jira - 0001)
    public void negativeWrongPassword(){
        User user = new User().withEmail("sonka04@gmail.com").withPassword("Sonka4");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorWrongPassword(), "Wrong email or password");
    }

    @AfterMethod (alwaysRun = true)
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }

}
