import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();

    }

    @Test
    public void registrationSuccess(){
        System.currentTimeMillis();
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("New").withLastName("User").withEmail("newuser" +i+ "@gmail.com").withPassword("Newuser01$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");

    }
    @Test

    public void registrationWrongPassword(){
        System.currentTimeMillis();

        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User().withName("Polina").withLastName("Ignatenko").withEmail("polina34@mail.com").withPassword("Polina");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Password must contain minimum 8 symbols ");

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();

    }
}
