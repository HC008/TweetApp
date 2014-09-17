import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import au.com.bytecode.opencsv.CSVReader;

/**
 * To perform various action for the main program.
 * 
 * @author Hansen Cheng, Micah Angeles, Jason Delos Reyes
 *
 */
public class Processor {
  
  public Processor() {
    
  }
  
  /**
   * It reads in all of the data from the CSV file.
   * 
   * @param data - String array list.
   * @param filePath - path of file to be read in.
   * @return tweets - the filled up list of records.
   */
  public List<TweetRecord> readCsv(String filePath) {
   
    List<String[]> data = new ArrayList<String[]>();
    List<TweetRecord> tweets = new ArrayList<TweetRecord>();
    String dateFormat = new String ("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
    try {

      CSVReader file = new CSVReader(new FileReader(filePath));
      String [] nextLine;
      int lineNumber = 3;
      
      //Reading in all of the lines of text etc from the CSV file
      while ((nextLine = file.readNext()) != null) {
        data.add(nextLine);
      }
     
      
      //Creating the object while reading the lines of data, but 
      //omitting the column names and the last two lines
      while (lineNumber >= 3 && lineNumber < data.size() - 2) {
        
        if (data.get(lineNumber)[0].matches(dateFormat) == false) {
          data.get(lineNumber)[0] = formatDate(data.get(lineNumber)[0]);
        }
        
        tweets.add(new TweetRecord(data.get(lineNumber)[0], data.get(lineNumber)[1], 
                                   data.get(lineNumber)[2], data.get(lineNumber)[3],
                                   data.get(lineNumber)[4], data.get(lineNumber)[5],
                                   data.get(lineNumber)[6], data.get(lineNumber)[7],
                                   data.get(lineNumber)[8]));
        
        lineNumber++;
      }
      
      lineNumber = 0;
      //Closes the file after being read in.
      file.close();
     
    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
 
    return tweets;
  }
 
  /**
   * Takes data from two files and then remove duplicates with the use of a Set.
   * 
   * @param first - file one.
   * @param second - file two.
   * @return filtered - a list of all the data without duplicates.
   */
  public List<TweetRecord> checkDuplicate(List<TweetRecord> first, List<TweetRecord> second) {
    List<TweetRecord> filtered;
    Set<TweetRecord> noDuplicate = new HashSet<TweetRecord>();

    noDuplicate.addAll(first);
    noDuplicate.addAll(second);
    
    filtered = new ArrayList<TweetRecord>(noDuplicate);
    
    return filtered;
    
  }
  
  /**
   * Reformats the TweetDate to the format of dd/mm/yyyy hh:mm:ss.
   * 
   * @param original - the TweetDate string read in from the CSV file.
   * @return
   */
  public String formatDate(String original) {
    
    //Splits the original string at the space
    String[] tempDate = original.split(" ");
    
    //Split the date part by the slash
    String[] dates = tempDate[0].split("/");
    
    //Split the time part by the colon
    String[] times = tempDate[1].split(":");
    
    //Checks for the parts that is only one number. 
    //If it is then append a 0 to the front and also 
    //change the year to yyyy format.
    for (int i = 0; i < dates.length; i++) {
      if (dates[i].length() == 1) {
        dates[i] = "0" + dates[i];
      }
      
      if (i == dates.length - 1) {
        dates[i] = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
      }
    }
    
    //Checks if the time value is only one number,
    //if it is then append a 0 to the front
    for (int j = 0; j < times.length; j++) {
      if (times[j].length() == 1) {
        times[j] = "0" + times[j];
      }
    }
    
    
    return dates[0] + "/" + dates[1] + "/" + dates[2] + " " + times[0] + ":" + times[1] + ":00";
    
  }
 
  
}
