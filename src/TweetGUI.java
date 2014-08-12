import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import au.com.bytecode.opencsv.CSVWriter;


/**
 * Interface to the application that allows one to input two CSV files
 * of Twitter tweets and filtered out duplicates between the two files.
 * 
 * Tool to extract data from the CSV files was obtained from the following
 * website - http://opencsv.sourceforge.net/
 * 
 * @author Hansen Cheng, Micah Angeles, Jason Delos Reyes
 *
 */
public class TweetGUI extends JApplet implements ActionListener {
  
  
  private static final long serialVersionUID = 1L;
  
  //Components of the Applet
  private JPanel mainPanel = new JPanel();
  private JLabel fileOne = new JLabel("File One"), fileTwo = new JLabel("File Two");
  private JButton browse = new JButton("Browse"), submit = new JButton("Submit"),
                  save = new JButton("Save"), reset = new JButton("Reset"), 
                  help = new JButton("Help");
   
  private JTextField[] fields = {new JTextField(30), new JTextField(30)};
  private Font normalFont = new Font("TimesNewRoman", Font.PLAIN, 16);
  private JTextArea area = new JTextArea(10, 50);
  private JScrollPane scroll;
  private List<TweetRecord> dataOne = new ArrayList<TweetRecord>();
  private List<TweetRecord> dataTwo = new ArrayList<TweetRecord>();
  private List<TweetRecord> allData = new ArrayList<TweetRecord>();
  
  //Table
  private JTable dataTable;
  private TweetModel model = new TweetModel();
  private String[][] rowData = new String[0][9];
  
  //Misc.
  private Processor process = new Processor();
  private File[] files = new File[2];
  private File singleFile;
  private int fileSignal = 0; //To signal when to put the file's name in the respective text field
  
  public void init() {
    //Adding content to the app by adding things to panel etc
    mainPanel.setLayout(new FlowLayout());
    mainPanel.setBackground(Color.decode("#00CCFF"));
    mainPanel.add(fileOne);
    mainPanel.add(fields[0]);
    mainPanel.add(fileTwo);
    mainPanel.add(fields[1]);
    mainPanel.add(browse);
    mainPanel.add(submit);
    mainPanel.add(save);
    mainPanel.add(reset);
    mainPanel.add(help);
    
    //Creating the table 
    model.addTableModelListener(dataTable);
    dataTable = new JTable(model);
    dataTable.setPreferredScrollableViewportSize(new Dimension(1200, 500));
    //Reordering of the columns is not allow
    dataTable.getTableHeader().setReorderingAllowed(false);
    mainPanel.add(dataTable);
    scroll = new JScrollPane(dataTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    mainPanel.add(scroll);
    
    //Area to print out status or any other information.
    area.setEditable(false);
    mainPanel.add(area);
    scroll = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    mainPanel.add(scroll);
    
    //Giving listeners to the buttons to detect an action
    browse.addActionListener(this);
    submit.addActionListener(this);
    save.addActionListener(this);
    reset.addActionListener(this);
    help.addActionListener(this);
    
    this.add(mainPanel);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource().equals(browse)) {
      
      //Opens up a window to allow users to select a file
      JFileChooser fileSelect = new JFileChooser();
      fileSelect.setMultiSelectionEnabled(true);
      int selection = fileSelect.showDialog(TweetGUI.this, "Select");
      
      if (selection == JFileChooser.APPROVE_OPTION) {
        
        //Multiple file selection
        if (fileSelect.getSelectedFiles().length == 2) {
        
          files = fileSelect.getSelectedFiles();
          
          //Sets both text fields to be empty first to ensure that the previous selection is gone
          //if the user decides to change selection. Then load the name of the file to the textfield.
          fields[0].setText("");
          fields[1].setText("");
          
          for (int i = 0; i < files.length; i++) {
            fields[i].setText(files[i].getName());
          }
          
          fileSignal = 1;
        }
        else {
          
          //Select a file one file at a time
          singleFile = fileSelect.getSelectedFile();
          
          //Puts name of file into the first text field when both fields are blank
          if (fields[0].getText().equals("") && fields[1].getText().equals("")) {
            
            fields[0].setText(singleFile.getName());
            files[0] = singleFile;
            
          }
          //Put the new single file's name into the first text field when both fields are filled up
          else if (!fields[0].getText().isEmpty() 
                    && !fields[1].getText().isEmpty() && fileSignal == 1) {
            
            fields[0].setText(singleFile.getName());
            files[0] = singleFile;
            fileSignal = 0;
            
          }
          //Puts the name of the file into the second text field
          else {
            fields[1].setText(singleFile.getName());
            files[1] = singleFile;
            fileSignal = 1;
          }
        }
      }
      
    }
    else if (e.getSource().equals(submit)) {
      
      if (fields[0].getText().equals("") || fields[1].equals("")) {
        JOptionPane.showMessageDialog(null, "You need two files to compare. Please try again. Thank you.");
      }
      else {
        
        //Reading in the data to two separate list and then filtered things into one list
        dataOne = process.readCsv(files[0].getPath());
        dataTwo = process.readCsv(files[1].getPath());
        
        allData = process.checkDuplicate(dataOne, dataTwo);
        
        //Loading the data to the table
        rowData = new String[allData.size()][9];
        
        for (int i = 0; i < allData.size(); i++) {
          
          rowData[i][0] = allData.get(i).getTweetDate();
          rowData[i][1] = allData.get(i).getHandle();
          rowData[i][2] = allData.get(i).getName();
          rowData[i][3] = allData.get(i).getText();
          rowData[i][4] = allData.get(i).getUrl();
          rowData[i][5] = allData.get(i).getPlatform();
          rowData[i][6] = allData.get(i).getType();
          rowData[i][7] = allData.get(i).getRetweetCount();
          rowData[i][8] = allData.get(i).getFavoriteCount();
           
        }
        
        //Refreshes the table to clear away previous data and add in new data
        dataTable.removeAll();
        model.fireTableDataChanged();
        //Notifying the table to load the changes
        model.setRowData(rowData);
        dataTable = new JTable(model); 
        model.fireTableDataChanged();
                
      }
    }
    
    else if (e.getSource().equals(save)) {
      
      JFileChooser fileSave = new JFileChooser();
      int saveSelect = fileSave.showDialog(TweetGUI.this, "Save");
      
      //try {
        //CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
        // feed in your array (or convert your data to an array)
        //String[] entries = "first#second#third".split("#");
        //writer.writeNext(entries);
        //writer.close();
      //}
      //catch (IOException e1) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
      //}
     
    }
    else if (e.getSource().equals(reset)) {
      
      //Clears everything
      for (int i = 0; i < fields.length; i++) {
        fields[i].setText("");
      }
      
      if (files != null) {
        for (int j = 0; j < files.length; j++) {
          files[j] = null;
        }
      }
      
      area.setText("");
    }
    else if (e.getSource().equals(help)) {
      
      //Clears away text.
      area.setText("");
      
      //Tutorial
      area.setFont(normalFont);
      area.append("How to use:\n\n");
      area.append("\t1. Select Multiple Files checkbox if want to select multiple files.\n" +
      		        "\t    If don't want to select multiple files then jump to step 2.\n\n");
      
      area.append("\t2.Click on Browse and then select the two files you want to compare.\n\n");
      area.append("\t3. Now click on Submit to start data processing.\n\n");
 
      area.append("Other features:\n\n");
      area.append("\tClick Reset to clear everything.");
    }  
  }
  
}
