import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class TweetGUI extends JApplet implements ActionListener {
  
  
  private static final long serialVersionUID = 1L;
  //Panel acts like desk for you to put things on
  private JPanel mainPanel = new JPanel();
  private JLabel fileOne = new JLabel("File One"), fileTwo = new JLabel("File Two");
  private JButton submit = new JButton("Submit"), browse = new JButton("Browse"), 
                  help = new JButton("Help");
  
  private JTextField[] fields = {new JTextField(30), new JTextField(30)};
  private JTextArea area = new JTextArea(40, 100);
  private JScrollPane scroll;
  private List<TweetRecord> dataOne = new ArrayList<TweetRecord>();
  private List<TweetRecord> dataTwo = new ArrayList<TweetRecord>();
  private List<TweetRecord> allData = new ArrayList<TweetRecord>();
  private Processor process = new Processor();
  private File[] files;
  
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
    mainPanel.add(help);
    
    //Prevents anyone from editing the area of the printed results.
    area.setEditable(false);
    mainPanel.add(area);
    
    //Adds a scoll bar to the text area
    scroll = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    mainPanel.add(scroll);
    
    //Giving listeners to the buttons to detect an action
    browse.addActionListener(this);
    submit.addActionListener(this);
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
        
        files = fileSelect.getSelectedFiles();
        
        //Sets both text fields to be empty first to ensure that the previous selection is gone
        //if the user decides to change selection. Then load the name of the file to the textfield.
        fields[0].setText("");
        fields[1].setText("");
        
        for (int i = 0; i < files.length; i++) {
          fields[i].setText(files[i].getName());
        }
      }
      
    }
    else if (e.getSource().equals(submit)) {
      
      if (fields[0].getText().equals("") || fields[1].equals("")) {
        JOptionPane.showMessageDialog(null, "You need two files to compare. Please try again. Thank you.");
      }
      else {
        
        //Clears away previous data
        area.setText("");
        
        dataOne = process.readCsv(dataOne, files[0].getPath());
        dataTwo = process.readCsv(dataTwo, files[1].getPath());
        
        allData = process.checkFileSize(dataOne, dataTwo);
        
        int lineNumber = 1;
        
        for (int i = 0; i < allData.size(); i++) {
          area.append(lineNumber + ". " + allData.get(i).toString() + "\n\n");
          lineNumber++;
        }
        
      }
    }
    else if (e.getSource().equals(help)) {
      
      //Tutorial
      area.setText("");
      
      area.append("Tutorial:\n\n");
      area.append("1. Click on Browse and then select the two files you want to compare.\n");
      area.append("2. Now click on Submit to start data processing.");
    }
    
    
  }
  
}
