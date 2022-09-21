import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void precondition(){
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void loginPositiveTest(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sonka04@gmail.com", "Sonka04$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
