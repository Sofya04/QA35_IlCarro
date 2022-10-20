import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccessFromMainPage(){
        app.getSearch().searchCurrentMonth("Tel Aviv","10/25/2022", "10/30/2022");//data format dd/mm/yyyy
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());

    }
    @Test
    public void searchNextMonthSuccess(){
        app.getSearch().searchNextMonth("Saratov, Russia","11/25/2022","11/30/2022");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }

    @Test 
    public void searchNextMonthSuccessFromHeader(){
        app.getSearch().searchNextMonthFromHeader("Rehovot, Isr","11/25/2022","11/30/2022");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }
}
