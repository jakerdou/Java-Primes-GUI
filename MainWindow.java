import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel; //organizes
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
  private static final long serialVersionUID = -3880026026104218593L;
  private Primes m_Primes = new Primes();
  private JTextField tfPrimeFileName;
  private JTextField tfCrossFileName;
  private JLabel lblPrimeCount;
  private JLabel lblCrossCount;
  private JLabel lblLargestPrime;
  private JLabel lblLargestCross;
  private JLabel lblStatus = new JLabel("Status: Bored.");
  
  MainWindow(String name, Primes p)
  {
    m_Primes = p;
    
    //instantiate main window and set layout and background
    JDialog mainWin = new JDialog(this, name);
    GridBagLayout gridLayout = new GridBagLayout();
    mainWin.getContentPane().setBackground(new Color(67, 0, 48));
    mainWin.getContentPane().setLayout(gridLayout);
    
    //gridbagconstraints for the window
    GridBagConstraints gbcDlg = new GridBagConstraints();
    gbcDlg.fill = GridBagConstraints.HORIZONTAL;
    gbcDlg.anchor = GridBagConstraints.WEST;
    gbcDlg.insets = new Insets(2,2,2,0);
    gbcDlg.weightx = .5;
    gbcDlg.gridx = 0;
    gbcDlg.gridy = 0;
    
    //gridbagconstraints for the panels to add to the window
    GridBagConstraints gbcPnl = new GridBagConstraints();
    gbcPnl.fill = GridBagConstraints.HORIZONTAL;
    gbcPnl.anchor = GridBagConstraints.WEST;
    gbcPnl.insets = new Insets(4,2,4,0);
    gbcPnl.weightx = .5;
    gbcPnl.gridx = 0;
    gbcPnl.gridy = 0;
    
    //1st Panel
    JPanel pnlPF = new JPanel();
    pnlPF.setLayout(new GridBagLayout());
    
    //1.1 prime file text field
    tfPrimeFileName = new JTextField("primes.txt");
    tfPrimeFileName.setColumns(40);
    pnlPF.add(tfPrimeFileName, gbcPnl);
    
    //1.2 prime file label
    JLabel lblPF = new JLabel("Primes File");
    lblPF.setFont(new Font("Tahoma", Font.PLAIN, 30));
    gbcPnl.gridwidth = 1;
    gbcPnl.gridy = 1;
    pnlPF.add(lblPF, gbcPnl);
    
    //1.2.1 spacer label
    JLabel lblSpace = new JLabel("");
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 0;
    pnlPF.add(lblSpace, gbcPnl);
    
    //1.3 number of primes
    lblPrimeCount = new JLabel("0");
    gbcPnl.gridx = 2;
    gbcPnl.gridy = 0;
    pnlPF.add(lblPrimeCount, gbcPnl);
    
    //1.4 load button
    JButton btnLoad = new JButton("Load");
    //make it load when you click the button
    btnLoad.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String filename = new String();
        
        //if text field is empty make it the default file name
        if(tfPrimeFileName.getText().equals("")) {
          filename = Config.DATAPATH + Config.PRIMEFILE;
        } else {
          filename = Config.DATAPATH + tfPrimeFileName.getText();
        }
        
        try
        {
          //load primes
          if(FileAccess.loadPrimes(p, filename)) {
            lblStatus.setText("Status: Loaded " + filename + " successfully.");
            updateStats();
          } else {
            lblStatus.setText("Status: Error loading " + filename + ".");
          }
        }
        catch(NumberFormatException ex)
        {
          lblStatus.setText("Status: Error loading " + filename + ".");
        }
      }
    });
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 1;
    pnlPF.add(btnLoad, gbcPnl);
    
    //1.5 save button
    JButton btnSave = new JButton("Save");
    //make it save when you click the button
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String filename = new String();
        
        //if text field is empty, save to the default file name
        if(tfPrimeFileName.getText().equals("")) {
          filename = Config.DATAPATH + Config.PRIMEFILE;
        } else {
          filename = Config.DATAPATH + tfPrimeFileName.getText();
        }
        
        try
        {
          //save file
          if(FileAccess.savePrimes(p, filename)) {
            lblStatus.setText("Status: Saved " + filename + " successfully.");
          } else {
            lblStatus.setText("Status: Error saving " + filename + ".");
          }
        }
        catch(NumberFormatException ex)
        {
          lblStatus.setText("Status: Error saving " + filename + ".");
        }
      }
    });
    gbcPnl.gridx = 2;
    pnlPF.add(btnSave, gbcPnl);
    
    //add 1st panel
    mainWin.add(pnlPF, gbcDlg);
    
    //2nd Panel
    JPanel pnlHF = new JPanel();
    pnlHF.setLayout(new GridBagLayout());
    
    //2.1 Hex File text field
    tfCrossFileName = new JTextField("crosses.txt");
    tfCrossFileName.setColumns(40);
    gbcPnl.gridx = 0;
    gbcPnl.gridy = 0;
    pnlHF.add(tfCrossFileName, gbcPnl);
    
    //2.2 Hex File label
    JLabel lblHF = new JLabel("Hexagon Cross File");
    lblHF.setFont(new Font("Tahoma", Font.PLAIN, 30));
    gbcPnl.gridwidth = 1;
    gbcPnl.gridy = 1;
    pnlHF.add(lblHF, gbcPnl);
    
    //2.2.1 spacer label
    JLabel lblSpace2 = new JLabel("");
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 0;
    pnlPF.add(lblSpace2, gbcPnl);
    
    //2.3 number of crosses
    lblCrossCount = new JLabel("0");
    gbcPnl.anchor = GridBagConstraints.EAST;
    gbcPnl.gridx = 2;
    gbcPnl.gridy = 0;
    pnlHF.add(lblCrossCount, gbcPnl);
    
    //2.4 load button
    JButton btnLoad2 = new JButton("Load");
    //make it load when you click the button
    btnLoad2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String filename = new String();
        
        //if text field is empty, load default file name
        if(tfCrossFileName.getText().equals("")) {
          filename = Config.DATAPATH + Config.CROSSFILE;
        } else {
          filename = Config.DATAPATH + tfCrossFileName.getText();
        }
        
        try
        {
          //load file
          if(FileAccess.loadCrosses(p, filename)) {
            lblStatus.setText("Status: Loaded " + filename + " successfully.");
            updateStats();
          } else {
            lblStatus.setText("Status: Error loading " + filename + ".");
          }
        }
        catch(NumberFormatException ex)
        {
          lblStatus.setText("Status: Error loading " + filename + ".");
        }
      }
    });
    gbcPnl.anchor = GridBagConstraints.WEST;
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 1;
    pnlHF.add(btnLoad2, gbcPnl);
    
    //2.5 save button
    JButton btnSave2 = new JButton("Save");
    //make it save when you click the button
    btnSave2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String filename = new String();
        
        //if text field is empty, save to default file name
        if(tfCrossFileName.getText().equals("")) {
          filename = Config.DATAPATH + Config.CROSSFILE;
        } else {
          filename = Config.DATAPATH + tfCrossFileName.getText();
        }
        
        try
        {
          //save file
          if(FileAccess.saveCrosses(p, filename)) {
            lblStatus.setText("Status: Saved " + filename + " successfully.");
          } else {
            lblStatus.setText("Status: Error saving " + filename + ".");
          }
        }
        catch(NumberFormatException ex)
        {
          lblStatus.setText("Status: Error saving " + filename + ".");
        }
      }
    });
    gbcPnl.anchor = GridBagConstraints.EAST;
    gbcPnl.gridx = 2;
    gbcPnl.gridy = 1;
    pnlHF.add(btnSave2, gbcPnl);
    
    //add 2nd Panel
    gbcDlg.gridy = 1;
    mainWin.add(pnlHF, gbcDlg);
    
    //3rd Panel
    JPanel pnlGen = new JPanel();
    pnlGen.setLayout(new GridBagLayout());
    
    //3.1 generate primes button
    JButton btnGenP = new JButton("Generate Primes");
    //make prime generation button pop up when you click it
    btnGenP.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //generate primes window
        popupGeneratePrimes();
      }
    });
    gbcPnl.gridx = 0;
    gbcPnl.gridy = 0;
    pnlGen.add(btnGenP, gbcPnl);
    
    //3.2 largest prime label
    lblLargestPrime = new JLabel("The largest prime has 0 digits.");
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 0;
    gbcPnl.anchor = GridBagConstraints.NORTH;
    gbcPnl.insets = new Insets(2,150,1,150);
    pnlGen.add(lblLargestPrime, gbcPnl);
    
    //3.3 largest cross label
    lblLargestCross = new JLabel("The largest cross has 0 and 0 digits.");
    gbcPnl.gridx = 1;
    gbcPnl.gridy = 0;
    gbcPnl.anchor = GridBagConstraints.SOUTH;
    pnlGen.add(lblLargestCross, gbcPnl);
    
    //3.4 generate crosses button
    JButton btnGenC = new JButton("Generate Crosses");
    //generate crosses from existing primes when button is pressed
    btnGenC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //generate hex crosses
        m_Primes.generateTwinPrimes();
        m_Primes.generateHexPrimes();         
        lblStatus.setText("Status: Hexagon Crosses have been generated.");
        updateStats();  
      }
    });
    gbcPnl.anchor = GridBagConstraints.EAST;
    gbcPnl.gridx = 2;
    gbcPnl.gridy = 0;
    gbcPnl.insets = new Insets(4,2,4,0);
    pnlGen.add(btnGenC, gbcPnl);
    
    //add 3rd panel
    gbcDlg.gridy = 2;
    mainWin.add(pnlGen, gbcDlg);
    
    //4th panel
    JPanel pnlStat = new JPanel();
    pnlStat.setLayout(new GridBagLayout());
    
    //4.1 add status label
    gbcPnl.anchor = GridBagConstraints.WEST;
    gbcPnl.gridx = 0;
    gbcPnl.gridy = 0;
    pnlStat.add(lblStatus, gbcPnl);
    
    //add 4th panel
    gbcDlg.gridy = 3;
    mainWin.add(pnlStat, gbcDlg);
    
    //set size and make window visible
    mainWin.setSize(1000, 400);
    mainWin.pack();
    mainWin.setVisible(true);
  }
  
  protected void popupGeneratePrimes()
  {
    JDialog dPrimes = new JDialog(this, "Prime Number Generation");
    GridBagLayout gridLayout = new GridBagLayout();
    dPrimes.getContentPane().setBackground(new Color(67, 0, 48));
    dPrimes.getContentPane().setLayout(gridLayout);
    
    GridBagConstraints gbcDialog = new GridBagConstraints();
    gbcDialog.fill = GridBagConstraints.HORIZONTAL;
    gbcDialog.anchor = GridBagConstraints.WEST;
    gbcDialog.ipady = 10;
    gbcDialog.weightx = .5;
    gbcDialog.insets = new Insets(1,2,0,0);
    gbcDialog.gridx = 0;
    gbcDialog.gridy = 0;
    
    GridBagConstraints gbcPanel = new GridBagConstraints();
    gbcPanel.anchor = GridBagConstraints.WEST;
    gbcPanel.weightx = .5;
    gbcPanel.insets = new Insets(1,2,0,0);
    gbcPanel.gridx = 0;
    gbcPanel.gridy = 0;
    
    JPanel pnlGenerate = new JPanel();
    pnlGenerate.setLayout(new GridBagLayout());
    
    JLabel lblCount = new JLabel("Number of Primes to Generate");
    lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
    pnlGenerate.add(lblCount, gbcPanel);
    
    JTextField tfCount = new JTextField();
    lblCount.setLabelFor(tfCount);
    tfCount.setColumns(30);
    gbcPanel.gridx = 1;
    pnlGenerate.add(tfCount, gbcPanel);
    
    JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
    lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
    gbcPanel.gridx = 0;
    gbcPanel.gridy = 1;
    pnlGenerate.add(lblStart, gbcPanel);
    
    JTextField tfStart = new JTextField();
    lblStart.setLabelFor(tfStart);
    tfStart.setColumns(30);
    gbcPanel.gridx = 1;
    pnlGenerate.add(tfStart, gbcPanel);
    
    dPrimes.add(pnlGenerate, gbcDialog);
    
    JPanel pnlButtons = new JPanel();
    pnlButtons.setLayout(new GridBagLayout());
    
    JButton btnGeneratePrimes = new JButton("Generate Primes");
    btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try
        {
          int count = Integer.parseInt(tfCount.getText());
          if(count >= 0) {
            BigInteger start = new BigInteger(tfStart.getText());
            if(start.intValue() < 2) {
              start = BigInteger.valueOf(2);
            }
            m_Primes.generatePrimes(start, count);
            lblStatus.setText("Status: Excited. Primes have been generated.");
            updateStats();
          }
          else {
            lblStatus.setText("You must generate a non-negative number of primes.");
          }
          
          dPrimes.dispose();
        }
        catch(NumberFormatException ex)
        {
          lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
          dPrimes.dispose();
        }
        
      }
    });
    gbcPanel.gridx = 0;
    gbcPanel.gridy = 0;
    pnlButtons.add(btnGeneratePrimes, gbcPanel);
    
    JButton btnCancel = new JButton("Cancel Generation");
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dPrimes.dispose();
      }
    });
    gbcPanel.anchor = GridBagConstraints.EAST;
    gbcPanel.gridx = 1;
    pnlButtons.add(btnCancel, gbcPanel);        
    
    gbcDialog.gridy = 1;
    dPrimes.add(pnlButtons, gbcDialog);
    
    JPanel pnlStatus = new JPanel();
    pnlStatus.setLayout(new GridBagLayout());
    
    gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
    gbcPanel.weightx = .5;
    gbcPanel.insets = new Insets(1,2,0,0);
    gbcPanel.gridx = 0;
    gbcPanel.gridy = 1;
    
    JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
    lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
    pnlStatus.add(lblNotice, gbcPanel);
    
    gbcDialog.gridy = 2;
    dPrimes.add(pnlStatus, gbcDialog);
    
    dPrimes.setSize(200,200);
    dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
    dPrimes.setVisible(true);       
  }
  
  // This function updates all the GUI statistics. (# of primes, # of crosses, etc)
  private void updateStats()
  {
    //update number of primes
    String numPrimes = Integer.toString(m_Primes.primeCount());
    lblPrimeCount.setText(numPrimes);
    
    //update number of crosses
    String numCrosses = Integer.toString(m_Primes.crossesCount());
    lblCrossCount.setText(numCrosses);
    
    //update largest prime
    String sizePrime = Integer.toString(m_Primes.sizeofLastPrime());
    lblLargestPrime.setText("The largest prime has " + sizePrime + " digits.");
    
    //update largest cross
    String sizeLeft = Integer.toString(m_Primes.sizeofLastCross().left());
    String sizeRight = Integer.toString(m_Primes.sizeofLastCross().right());
    lblLargestCross.setText("The largest cross has " + sizeLeft + " and " + sizeRight + " digits.");
  }
}
