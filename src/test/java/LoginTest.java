import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();

    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("sonka04@gmail.com", "Sonka04$");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //Assert.assertTrue(app.getHelperUser().isLogged());
    }
    @Test
    public void loginSuccessModels(){
        User user = new User().withEmail("sonka04@gmail.com").withPassword("Sonka04$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void negativeWrongEmail(){
        User user = new User().withEmail("sonka04gmail.com").withPassword("Sonka04$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

       Assert.assertEquals(app.getHelperUser().getErrorMessage(), "It'snot look like email");
    }

    @Test
    public void negativeWrongPassword(){
        User user = new User().withEmail("sonka04@gmail.com").withPassword("Sonka04");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorWrongPassword(), "Wrong email or password");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();

    }

}
