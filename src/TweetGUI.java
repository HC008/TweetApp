import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
  private JButton submit = new JButton("Submit"), browse = new JButton("Browse");
  private JTextField textOne = new JTextField(30), textTwo = new JTextField(30);
  private JTextArea area = new JTextArea(40, 50);
  private JScrollPane scroll;
  
  public void init() {
    //Adding content to the app by adding things to panel etc
    mainPanel.setLayout(new FlowLayout());
    mainPanel.setBackground(Color.decode("#00CCFF"));
    mainPanel.add(fileOne);
    mainPanel.add(textOne);
    mainPanel.add(fileTwo);
    mainPanel.add(textTwo);
    mainPanel.add(browse);
    mainPanel.add(submit);
    mainPanel.add(area);
    
    //Adds a scoll bar to the text area
    scroll = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
    
    mainPanel.add(scroll);
    
    //Giving listeners to the buttons to detect an action
    browse.addActionListener(this);
    submit.addActionListener(this);
    
    this.add(mainPanel);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource().equals(browse)) {
      
      //Opens up a window to allow users to select a file
      JFileChooser fileSelect = new JFileChooser();
      int selection = fileSelect.showOpenDialog(this);
      
    }
    else if (e.getSource().equals(submit)) {
      area.append("Done \n");
    }
    
    
  }
  
}
