package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public boolean isLogged(){
        List<WebElement> list = wd.findElements(By.xpath("//*[text()=' Logout ']"));
        return list.size() > 0;
    }

    public void logOut(){
        wd.findElement(By.xpath("//*[text()=' Logout ']")).click();
    }

    public void openLoginForm(){
       wd.findElement(By.xpath("//*[text()=' Log in ']")).click();
    }

    public void fillLoginForm(String email, String password) {
        WebElement inputEmail = wd.findElement(By.cssSelector("#email"));
        inputEmail.click();
        inputEmail.clear();
        inputEmail.sendKeys(email);

        WebElement inputPassword = wd.findElement(By.cssSelector("#password"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void submitLogin() {
        wd.findElement(By.xpath("//*[@type='submit']")).click();
    }
}
