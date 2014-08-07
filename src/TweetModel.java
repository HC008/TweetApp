import javax.swing.table.AbstractTableModel;

/**
 * Modified table model to include our own data.
 * 
 * @author Hansen Cheng and Micah Angeles
 *
 */
public class TweetModel extends AbstractTableModel {
 
  private static final long serialVersionUID = 1L;

  private String[] colNames = {"Date (GMT)", "Handle", "Name", 
                              "Text", "URL", "Platform", "Type",
                               "Retweet count", "Favorite count"};
  
  private String[][] rowData = new String[0][9];
  
  
  @Override
  public int getColumnCount() {
    // TODO Auto-generated method stub
    return colNames.length;
  }

  @Override
  public int getRowCount() {
    // TODO Auto-generated method stub
    return rowData.length;
  }
  
  public String getColumnName(int col) {
    return colNames[col];
  }

  @Override
  public Object getValueAt(int x, int y) {
    // TODO Auto-generated method stub
    return rowData[x][y];
  }

  /**
   * Retrieves the lines of data.
   * 
   * @return rowData - filtered data from CSV files.
   */
  public String[][] getRowData() {
    return rowData;
  }
  
  /**
   * Put in new data for the table.
   * 
   * @param rowData - filtered data from CSV files.
   */
  public void setRowData(String[][] rowData) {
    this.rowData = rowData;
  }
  

}
