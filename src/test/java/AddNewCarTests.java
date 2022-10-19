import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("sonka04@gmail.com").withPassword("Sonka04$"));
        }
    }

    @Test
    public void addCarSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+100;

        Car car = Car.builder().location("Rehovot, Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.5")
                .fuel("Petrol")
                .gear("MT")
                .wD("AWD")
                .doors("5")
                .seats("5")
                .carClass("C")
                .fuelConsumption("6.5")
                .carNumber("73-938" + i)
                .price("75")
                .distanceIncluded("250000")
                .features("Type of features")
                .about("The car is in a very good condition from first hands").build();
        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("/Users/gurevich_laptop/QA35/QA35_IlCarro/src/test/resources/carphoto.jpeg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
    }

    @Test(dataProvider = "carValidData", dataProviderClass = DataProviderCar.class)
    public void addCarSuccessDP(Car car){

        logger.info("The test used car model : " +car.toString());

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("/Users/gurevich_laptop/QA35/QA35_IlCarro/src/test/resources/carphoto.jpeg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        logger.info("In assert checked message 'Car added' in dialog  ");
    }


    @AfterMethod
    public void postCondition(){
        app.helperCar().returnToHomepage();
    }
}
