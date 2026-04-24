package utility;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader 
{
	
	private static Map<String, Object> jsonData;
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getData(String suiteName)
	{
		
		
		//read value from Json file
		
		if(jsonData==null)
		{
		ObjectMapper mapper = new ObjectMapper();
		try {
			String path = System.getProperty("user.dir")
			        + "\\src\\test\\resource\\testdata.json";
			
			jsonData = mapper.readValue(new File(path), Map.class);
			
			
			
		} 
		catch (StreamReadException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		catch (DatabindException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	
		}
		
		return new HashMap<>(
		        (Map<String, Object>) jsonData.get(suiteName));
	}	
}
