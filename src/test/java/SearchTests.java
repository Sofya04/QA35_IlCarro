import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    @Test  (priority = 1)
    public void searchCurrentMonthSuccessFromMainPage(){
        app.getSearch().searchCurrentMonth("Tel Aviv","11/8/2022", "11/15/2022");//data format dd/mm/yyyy
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());

    }
    @Test (groups = {"smoke"}, priority = 2)
    public void searchNextMonthSuccess(){
        app.getSearch().searchNextMonth("Saratov, Russia","11/25/2022","11/30/2022");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }

    @Test (priority = 3)
    public void searchNextMonthSuccessFromHeader(){
        app.getSearch().searchNextMonthFromHeader("Rehovot, Isr","11/25/2022","11/30/2022");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
        Assert.assertTrue(app.getSearch().isDateCorrect("11/25/2022","11/30/2022"));
    }

    @Test (priority =  4)
    public void searchAnyPeriod(){
        app.getSearch().selectAnyPeriod("Haifa, Israel","12/20/2022","6/10/2023");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }

    @Test (priority = 5)
    public void searchInPast(){
        app.getSearch().typePeriodInPast("Netanya, Israel", "10/10/2022", "10/15/2022");
        app.getSearch().submitWithChecking();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed());
    }
    @BeforeMethod (alwaysRun = true)
    public void returnHomePage(){
        app.getSearch().clickLogo();
    }
}
