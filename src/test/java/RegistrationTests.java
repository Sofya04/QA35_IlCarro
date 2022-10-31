import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod (alwaysRun = true)
    public void precondition(){
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();

    }

    @Test(dataProvider = "regDataValid", dataProviderClass = DataProviderUser.class)
    public void registrationSuccessDP(User user){
        System.currentTimeMillis();
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");

    }

    @Test(enabled = false,description = "Bug Jira 00012")
    public void  registrationSuccess() {
        System.out.println(System.currentTimeMillis());
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().withName("Lisa").withLastName("Snow").withEmail("lis" + i + "@mail.com").withPassword("Ww12345$");
        logger.info("Registration  scenario success was used data" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("In assert checked message 'Registered' in dialog  ");
    }

    @Test (groups = {"smoke"})
    public void registrationWrongPassword(){

        User user = new User().withName("Polina").withLastName("Ignatenko").withEmail("polina34@mail.com").withPassword("Polina");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter and one number");

    }
    @AfterMethod (alwaysRun = true)
    public void postCondition(){
        app.getHelperUser().clickOkButton();

    }
}
