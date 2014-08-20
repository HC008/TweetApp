/**
 * Representing the row of data from the CSV file.
 * 
 * @author Hansen Cheng, Micah Angeles, Jason Delos Reyes
 *
 */
public class TweetRecord implements Comparable<TweetRecord>{
  
  private String tweetDate;
  private String handle; 
  private String name; 
  private String text; 
  private String url; 
  private String platform; 
  private String type; 
  private String retweetCount; 
  private String favoriteCount; 
 
  /**
   * Constructing the lines of information.
   * 
   * @param tweetDate
   * @param handle
   * @param name
   * @param text
   * @param url
   * @param platform
   * @param type
   * @param retweetCount
   * @param favoriteCount
   */
  public TweetRecord(String tweetDate, String handle, String name, String text, String url, 
                        String platform, String type, String retweetCount, String favoriteCount ) {
    
    this.tweetDate = tweetDate; 
    this.handle = handle; 
    this.name = name; 
    this.text = text; 
    this.url = url; 
    this.platform = platform; 
    this.type = type; 
    this.retweetCount = retweetCount; 
    this.favoriteCount = favoriteCount; 
    
  }
  

  /**
   * Retrieves the date that the tweet was submitted.
   * 
   * @return tweetDate - date of the tweet.
   */
  public String getTweetDate() {
    return tweetDate;
    
  }
  
  /**
   * New date of the tweet that was submitted.
   * 
   * @param tweetDate - date of the tweet.
   */
  public void setTweetDate(String tweetDate) {
    this.tweetDate = tweetDate;
  }
  
  /**
   * Retrieves that handle data 
   * 
   * @return handle - hash tags.
   */
  public String getHandle() {
    return handle;
  }

  /**
   * New handle of the tweet that was submitted 
   * 
   * @param handle - hash tags.
   */
  public void setHandle(String handle) {
    this.handle = handle;
  }

  /**
   * Retrieves the user tweets 
   * 
   * @return name - username.
   */
  public String getName() {
    return name;
  }

  /**
   * Name user of the tweet that was submitted. 
   * 
   * @param name - username.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the tweet comments. 
   * 
   * @return text - comment.
   */
  public String getText() {
    return text;
  }

  /**
   * New comments of the tweets that was submitted. 
   * 
   * @param text - comment.
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Retrieves the address of the tweet. 
   * 
   * @return url - link to the tweet.
   */
  public String getUrl() {
    return url;
  }

  /**
   * New address to the tweet 
   * 
   * @param url - link to the tweet.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Retrieves the information of the type of the device that the users used.
   * 
   * @return platform - type of device. 
   */
  public String getPlatform() {
    return platform;
  }

  /**
   *The information about the type of product that the users used.
   * 
   * @param platform - type of device.
   */
  public void setPlatform(String platform) {
    this.platform = platform;
  }

  /**
   * Retrieves status of the comment such as if it is New, Retweet etc.
   * 
   * @return type - status of the comment. 
   */
  public String getType() {
    return type;
  }
  
  /**
   * New status of the comment 
   * 
   * @param type - status of the comment.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Retrieves the amount of time that the tweet got reused. 
   * 
   * @return retweetCount - amount of time that the tweet got reused. 
   */
  public String getRetweetCount() {
    return retweetCount;
  }

  /**
   * New number of amount of time the tweet got reused.
   * 
   * @param retweetCount - amount of time that the tweet got reused.
   */
  public void setRetweetCount(String retweetCount) {
    this.retweetCount = retweetCount;
  }
  
  /**
   * Retrieves amount people that like the comment.
   * 
   * @return favoriteCount - amount of likes.
   */
  public String getFavoriteCount() {
    return favoriteCount;
  }
  
  /**
   * New amount of people who liked the tweet.
   * 
   * @param favoriteCount - amount of likes.
   */
  public void setFavoriteCount(String favoriteCount) {
    this.favoriteCount = favoriteCount;
  }
  
  
  @Override
  /**
   * Checks equality of the object.
   */
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    TweetRecord other = (TweetRecord) obj;
    if (other.getTweetDate().equals(this.getTweetDate()) &&
        other.getHandle().equals(this.getHandle()) && 
        other.getName().equals(this.getName()) && other.getText().equals(this.getText()) && 
        other.getUrl().equals(this.getUrl()) && other.getPlatform().equals(this.getPlatform()) &&
        other.getType().equals(this.getType()) && 
        other.getRetweetCount().equals(this.getRetweetCount()) &&
        other.getFavoriteCount().equals(this.getFavoriteCount())) {
      
      return true; 
    }
    else {
      return false;
    }
  }
  
  @Override
  /**
   * Comparing by dates to sort it.
   */
  public int compareTo(TweetRecord rec) {
    if (rec == this) {
      return 0;
    }
    else {
      return this.getTweetDate().compareTo(rec.getTweetDate());
    }
  }
  
  @Override
  /**
   * Calculates the hash code.
   * return result = calculated hash code.
   */
  public int hashCode() {
    int hash = 31;
    int result = 1;
    
    result = hash * result + Integer.parseInt(retweetCount);
    result = hash * result + Integer.parseInt(favoriteCount);
    result = hash * result + (null == tweetDate ? 0 : tweetDate.hashCode());
    result = hash * result + (null == handle ? 0 : handle.hashCode());
    result = hash * result + (null == name ? 0 : name.hashCode());
    result = hash * result + (null == text ? 0 : text.hashCode());
    result = hash * result + (null == url ? 0 : url.hashCode());
    result = hash * result + (null == platform ? 0 : platform.hashCode());
    result = hash * result + (null == type ? 0 : type.hashCode());
    
    return result;
  }
  
  @Override
  /**
   * Prints out the whole line of data.
   */
  public String toString() {
    return getTweetDate() + "  " + getHandle() + "  " + getName() + "  " + getText() + "  " +
           getUrl() + "  " + getPlatform() + "  " + getType() + "  " + 
           getRetweetCount() + "  " + getFavoriteCount();
  }

  
 
}
