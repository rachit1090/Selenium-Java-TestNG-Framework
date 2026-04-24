package utility;

import org.testng.annotations.DataProvider;

import commontests.BaseTest;

import java.util.Map;

public class DataProviders
{
    @DataProvider(name = "testData")
    public static Object[][] testData()
    {
        // reads from JSON file
        Map<String, Object> data = 
            JsonReader.getData("testData");
        
        data.put(DataKeys.EMAIL, Constants.getEmail());

        return new Object[][] {{ data }};
    }
    
    
}
