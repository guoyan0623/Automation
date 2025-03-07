package com.dice.base;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class CsvDataProvider {
	
           @DataProvider(name = "CsvDataProvider")
           public static Iterator<Object[]> provideData(Method method) {
        	   List<Object[]> list = new ArrayList<Object[]>();
        	   String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data"
        			   + File.separator + method.getDeclaringClass().getSimpleName() + "_" + method.getName() + ".csv";
        	   File file = new File(pathname);
        	   try {
        		   CSVReader reader = new CSVReader(new FileReader(file));
        		   String[] keys = reader.readNext();
        		   if(keys != null) {
        			   String[] dataParts;
        			   while ((dataParts = reader.readNext()) != null) {
        				   Map<String,String> testData = new HashMap<String,String>();
        				   for (int i = 0; i< keys.length;i++) {
        					   testData.put(keys[i], dataParts[i]);
        				   }
        				   list.add(new Object[] { testData});
        			   }
        		   }
        		   
        		   reader.close();
        	   } catch (FileNotFoundException e){
        		   throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());    		   
        	   }  catch (IOException e) {
        		   throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
        	   } catch (CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	   
        	   return list.iterator();
           }
      }
