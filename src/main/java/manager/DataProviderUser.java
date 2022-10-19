package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser{

    @DataProvider
    public Iterator<Object[]> datalogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"sonka04@gmail.com","Sonka04$"});
        list.add(new Object[]{"sonka04@gmail.com","Sonka04$"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModelUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("sonka04@gmail.com").withPassword("Sonka04$")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("resources/registrationSuccess.csv"));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{new User().withName(split[0]).withLastName(split[1]).withEmail(split[2]).withPassword(split[3])});
            line =reader.readLine();
        }
        return list.iterator();
    }
}
