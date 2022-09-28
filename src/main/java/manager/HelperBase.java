package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void type(By locator, String text){
        if(text != null) {
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
    public void submit() {//because its the same button for registration and login
        wd.findElement(By.xpath("//*[@type='submit']")).click();
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }
    public boolean isYallaButtonNotActive() {
        boolean res =isElementPresent(By.cssSelector("button[disabled]"));

        return res &&!wd.findElement(By.cssSelector("[type='submit']")).isEnabled();
    }

    public void click(By locator) {
    wd.findElement(locator).click();
    }
}
