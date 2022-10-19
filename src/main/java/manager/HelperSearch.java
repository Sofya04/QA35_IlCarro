package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    private void typeCity(String city) {
        type(By.cssSelector("#city"), city);
        click(By.cssSelector("div.pac-item"));
        //pause(500);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.cssSelector("#dates"));

        //10/25/2022
        String[]from = dateFrom.split("/");

        //String locatorFrom = "//div[text()=' 25 ']";

        //String locatorFrom = "//div[text()=' "+from[1]+" ']";
        String locatorFrom = String.format("//div[text()=' %s ']",from[1]);//the same action with the help of special formula %s
        click(By.xpath(locatorFrom));


        //10/30/2022
        String[]to = dateTo.split("/");

        //String locatorTo = "//div[text()=' 30 ']";
        String locatorTo = "//div[text()=' "+to[1]+" ']";
        click(By.xpath(locatorTo));



    }

    public void searchNextMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        pause(500);
        click(By.cssSelector("[formcontrolname='dates']"));

        if(isNovemberPresent()==false){
            click(By.cssSelector(".mat-calendar-next-button"));
        }
        String[]from = dateFrom.split("/");

        String locatorFrom = String.format("//div[text()=' %s ']",from[1]);//the same action with the help of special formula %s
        click(By.xpath(locatorFrom));

        String[]to = dateTo.split("/");

        //String locatorTo = "//div[text()=' 30 ']";
        String locatorTo = "//div[text()=' "+to[1]+" ']";
        click(By.xpath(locatorTo));

    }

    public void searchNextMonthFromHeader(String city, String dateFrom, String dateTo) {
        click(By.cssSelector("[href='/search']"));
        typeCity(city);
        pause(500);
        click(By.cssSelector("#dates"));
        if(isNovemberPresent()==false){
            click(By.cssSelector(".mat-calendar-next-button"));
        }
        String[]from = dateFrom.split("/");

        String locatorFrom = String.format("//div[text()=' %s ']",from[1]);//the same action with the help of special formula %s
        click(By.xpath(locatorFrom));

        String[]to = dateTo.split("/");

        //String locatorTo = "//div[text()=' 30 ']";
        String locatorTo = "//div[text()=' "+to[1]+" ']";
        click(By.xpath(locatorTo));

    }
    private boolean isNovemberPresent() {
        return wd.findElements(By.xpath("//*[text()=' OCT 2022 ']")).size()>0;
    }

    public boolean isListOfCarsAppeared() {
        return wd.findElements(By.cssSelector(".car-img-container")).size()>0;
    }
}
