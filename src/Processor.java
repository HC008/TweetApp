import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

/**
 * To perform various action for the main program.
 * 
 * @author Hansen Cheng & Micah Angeles
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
        tweets.add(new TweetRecord(data.get(lineNumber)[0], data.get(lineNumber)[1], 
                                   data.get(lineNumber)[2], data.get(lineNumber)[3],
                                   data.get(lineNumber)[4], data.get(lineNumber)[5],
                                   data.get(lineNumber)[6], data.get(lineNumber)[7],
                                   data.get(lineNumber)[8]));
        
        lineNumber++;
      }
      
      lineNumber = 0;
     
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
   * To compare which file has the most lines of data before.
   * 
   * @param csvOne - first file.
   * @param csvTwo - second file.
   */
  public List<TweetRecord> checkFileSize(List<TweetRecord> csvOne, List<TweetRecord> csvTwo) {
    int small = 0, large = 0;
    List<TweetRecord> nonDuplicate = new ArrayList<TweetRecord>();
    
    //Test which file size is smaller than the other and then call a method to check for duplicate
    if (csvOne.size() < csvTwo.size() || csvOne.size() == csvTwo.size()) {
      //First file is smaller than second file
      small = csvOne.size();
      large = csvTwo.size();
      
      nonDuplicate = checkDuplicate(csvOne, csvTwo, nonDuplicate, small, large);
    }
    else {
      //Second file is smaller than first file
      small = csvTwo.size();
      large = csvOne.size();
      
      nonDuplicate = checkDuplicate(csvTwo, csvOne, nonDuplicate, small, large);
    } 
    
    return nonDuplicate;
  }
  
  /**
   * Checks for duplicate comments in the data then filtered into a list.
   * 
   * @param less - the file with less content.
   * @param more - the file with more content.
   * @param filtered - for storing the filtered out data.
   * @param little - small file size.
   * @param big - larger file size.
   * 
   * @return filtered - the data that contains both time difference duplicates and non duplicates.
   */
  public List<TweetRecord> checkDuplicate(List<TweetRecord> less, List<TweetRecord> more, 
                                            List<TweetRecord> filtered, int little, int big) {

    //Checks which one is duplicate and flag it to not be included in the final data.
    for (int i = 0; i < little; i++) {
      for (int j = 0; j < big; j++) {
        
        if (more.get(j).equals(less.get(i)) && more.get(j).getFlag() == 0) {
         more.get(j).setFlag(1);
        }
        
      } //end of inner for loop
    } //end of outer for loop
    
    
    //Loading all of the data into  the list
    filtered = less;
    
    for (int m = 0; m < big; m++) {
      if (more.get(m).getFlag() == 0) {
        filtered.add(more.get(m));
      }
    }
    
    
    return filtered;
    
  }
  
}
