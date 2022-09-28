package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()=' Logout ']"));
    }

    public void logOut() {
        wd.findElement(By.xpath("//*[text()=' Logout ']")).click();
    }

    public void openLoginFormHeader() {
        wd.findElement(By.xpath("//*[text()=' Log in ']")).click();
    }

    public void openLoginFormFooter() {
        // a[href ^='/login']
        wd.findElement(By.xpath("//a[text()='Log in']")).click();
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));
        return wd.findElement(By.cssSelector("h2.message")).getText();
    }
    public String getTitleMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));

        // pause(2000);
        return wd.findElement(By.cssSelector("div.dialog-container>h1")).getText();
    }
    public void clickOkButton() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }

    }

    public String getErrorMessage() {

        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public String getErrorWrongPassword() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.cdk-overlay-pane"))));
        return wd.findElement(By.cssSelector("h1.title")).getText();
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.cssSelector("#name"),user.getName());
        type(By.cssSelector("#lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        click(By.cssSelector("label[for='terms-of-use']"));    }
}
