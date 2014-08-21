import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Date class to be able to extract Date data from Twitter feed.
 * 
 * @author Hansen Cheng, Micah Angeles, Jason Delos Reyes
 *
 */
public class Date {

	  private int tweetDay;    // Extract each 
	  private int tweetMonth;  // value in 
	  private int tweetYear;   // the twitter 
	  private int tweetHour;   // date and time
	  private int tweetMin;    // column for extraction.
	  
	  
	  public Date(){
		}
	  
	  
	  /**
	   * Return value of day value of tweet data.
	   * 
	   * @return tweetDay - day ("date"?) value of tweet.
	   */
	  public int getTweetDay() {
		  
		 // String currDate = getTweetDate();
		 // tweetDay = currDate.day;
		  
		  return tweetDay;
	  }
	  
	  /**
	   * Return value of month of tweet data.
	   * 
	   * @return tweetMonth - month value
	   */
	  public int getTweetMonth() {
		  
		  //tweetMonth = getTweetDate().month;
		  
		  return tweetMonth;
	  }
	  
	  /**
	   * Return value of year of tweet data.
	   * 
	   * @return tweetYear - year value
	   */
	  public int getTweetYear(){
		  return tweetYear;
	  }
	  
	  /**
	   * Return value of hour of tweet data.
	   * 
	   * @return tweetHour - hour value
	   */
	  public int getTweetHour(){
		  return tweetHour;
	  }
	  
	  /**
	   * Return value of minute of tweet data.
	   * 
	   * @return tweetMin - minute value
	   */
	  public int getTweetMin(){
		  return tweetMin;
	  }
	

		  public static void main(String[] args) throws ParseException {
			  
		        java.util.Date temp1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
		                .parse("20/08/2117 04:45:25");
		        java.util.Date temp2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .parse("10/11/2017 23:11:48");
		        java.util.Date temp3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .parse("13/01/1989 12:41:28");
		        
		        System.out.println("Times are in chronological order: ");
		        System.out.println("Time 1: (Past) " +temp3.getTime());
		        System.out.println("Time 2: (Now) " +temp1.getTime());
		        System.out.println("Time 3: (Future) " +temp2.getTime());
		        
		        System.out.println();
		        System.out.println("Use info above to place into sorting algorithm.");
		        
		        
		        
		    }
			
	  
	  
	  
}
