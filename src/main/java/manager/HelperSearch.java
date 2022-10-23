package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        //click(By.cssSelector());
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

        if(isPreviousPageButtonNotActive()){
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
        if(isPreviousPageButtonNotActive()){
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

    public boolean isPreviousPageButtonNotActive() {
        boolean res =isElementPresent(By.cssSelector("[aria-label='Previous month']"));

        return res &&!wd.findElement(By.cssSelector("[aria-label='Previous month']")).isEnabled();
    }

    public void selectAnyPeriod(String city, String dateFrom, String dateTo) {
        click(By.cssSelector("[href='/search']"));
        typeCity(city);
        click(By.cssSelector("#dates"));
        //String nowData = "10/20/2022";
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        logger.info("Day of month--> "+now.getDayOfMonth());
        logger.info("Month value--> "+now.getMonthValue());
        logger.info("Year--> "+now.getYear());

//        String[] from = dateFrom.split("/");
//        int diffYear = Integer.parseInt(from[2]) - now.getYear();

        int diffYear;
        int diffMonth;

        diffYear = from.getYear()-now.getYear();
        if(diffYear == 0){
            diffMonth = from.getMonthValue()-now.getMonthValue();
        }else{
            diffMonth = 12-now.getMonthValue()+from.getMonthValue();
        }
        clickNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        //part 2
        diffYear = to.getYear()-from.getYear();
        if(diffYear==0){
            diffMonth = to.getMonthValue()-from.getMonthValue();
        }
        else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonth(int count) {
        click(By.cssSelector("button[aria-label='Next month']"));
    }

    public boolean isDateCorrect(String dateFrom, String dateTo) {
        WebElement element = wd.findElement(By.cssSelector("input[aria-haspopup='dialog']"));
        return true;
    }

    public void typePeriodInPast(String city, String dateFrom, String dateTo) {
        click(By.cssSelector("[href='/search']"));
        typeCity(city);
        type(By.id("dates"), dateFrom + " - " + dateTo);
    }
}
