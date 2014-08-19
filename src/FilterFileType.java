
import java.io.File; 
import javax.swing.filechooser.*;

public class FilterFileType extends FileFilter {
  private String fileType = "csv"; 
  private char dotVal = '.'; 
  
  public boolean accept(File fileVal) {
    if(fileVal.isDirectory()) {
      return true; 
    }
    if(extension(fileVal).equalsIgnoreCase(fileType)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public String getDescription() {
    return "csv format only"; 
  }
  
  public String extension(File fileVal) {
    String fileName = fileVal.getName(); 
    int indexFile = fileName.lastIndexOf(dotVal); 
    if(indexFile > 0 && indexFile < fileName.length() - 1 ) {
      return fileName.substring(indexFile); 
    }
    else{
      return ""; 
    }
  }
  
 
}
