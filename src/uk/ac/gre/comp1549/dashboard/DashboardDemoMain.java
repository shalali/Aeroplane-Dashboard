package uk.ac.gre.comp1549.dashboard;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import uk.ac.gre.comp1549.dashboard.controls.BarPanel;
import uk.ac.gre.comp1549.dashboard.controls.DialPanel;
import uk.ac.gre.comp1549.dashboard.controls.IndicatorFactory;
import uk.ac.gre.comp1549.dashboard.events.*;
import uk.ac.gre.comp1549.dashboard.scriptreader.DashboardEventGeneratorFromXML;
import uk.ac.gre.comp1549.dashboard.controls.IIndicator;
import uk.ac.gre.comp1549.dashboard.controls.Singleton;
import uk.ac.gre.comp1549.dashboard.controls.ArrivalBoard;

/**
 * DashboardDemoMain.java Contains the main method for the Dashboard demo
 * application. It: a) provides the controller screen which allows user input
 * which is passed to the display indicators, b) allows the user to run the XML
 * script which changes indicator values, c) creates the dashboard JFrame and
 * adds display indicators to it.
 *
 * @author COMP1549
 * @version 2.0
 */
public class DashboardDemoMain extends JFrame {

    /**
     * Name of the XML script file - change here if you want to use a different
     * filename
     */
    public static final String XML_SCRIPT = "dashboard_script.xml";

    // fields that appear on the control panel
    private JButton btnScript;

    // fields that appear on the dashboard itself
    private DialPanel speedDial, fuelDial, compassDial, altitudeDial;
    private BarPanel fuelBar;

    /**
     * Constructor. Does maybe more work than is good for a constructor.
     */
    public DashboardDemoMain() {
        // Set up the frame for the controller
        setTitle("Dashboard demonstration controller");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        btnScript = new JButton("Run XML Script");

        // When the button is read the XML script will be run
        btnScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        runXMLScript();
                    }
                }.start();
            }
        });
        panel.add(btnScript);
        add(panel);
        pack();//size determined by contained components

        setLocationRelativeTo(null); // display in centre of screen
        // this.setVisible(true);

        // Set up the dashboard screen        
        JFrame dashboard = new JFrame("Demo dashboard");
        dashboard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dashboard.setLayout(new FlowLayout());

//        IndicatorFactory indicatorFactory = new IndicatorFactory();
//        IIndicator fullDial = indicatorFactory.createIndicator("full");
        compassDial = new DialPanel("full");//full dial
        compassDial.setLabel("COMPASS (deg.)");
        dashboard.add(compassDial);

        speedDial = new DialPanel("threequater");//three quaters dial
        speedDial.setLabel("AIR SPEED (m/s)");
        dashboard.add(speedDial);

        
        altitudeDial = new DialPanel("half");//half dial
        altitudeDial.setLabel("ALTITUDE (km)");
        dashboard.add(altitudeDial);

        fuelDial = new DialPanel(("quater"));//quater dial
        fuelDial.setLabel("FUEL (1000L)");
        dashboard.add(fuelDial);
        
       
        DocumentListener petrolListener = new PetrolValueListener();
        fuelDial.txtUserInput.getDocument().addDocumentListener(petrolListener);

        // add the petrol Bar
        fuelBar = new BarPanel();
        fuelBar.setLabel("FUEL");
        fuelBar.setValue(100);
        dashboard.add(fuelBar);
       ArrivalBoard ab = new ArrivalBoard();
       dashboard.add(ab);

//dashboard.pack();
        dashboard.setSize(1000, 300); //w,h

        dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //dashboard.setUndecorated(true);
        dashboard.add(panel);
        // centre the dashboard frame above the control frame
//        Point topLeft = this.getLocationOnScreen(); // top left of control frame (this)
        int hControl = this.getHeight(); // height of control frame (this)
        int wControl = this.getWidth(); // width of control frame (this)
        int hDash = dashboard.getHeight(); // height of dashboard frame 
        int wDash = dashboard.getWidth(); // width of dashboard frame 
        // calculate where top left of the dashboard goes to centre it over the control frame
        //Point p2 = new Point((int) topLeft.getX() - (wDash - wControl) / 2, (int) topLeft.getY() - (hDash + hControl));
        //dashboard.setLocation(p2);

        dashboard.setVisible(true);
    }

    public void setFuel() {
        try {
            int value = Integer.parseInt(fuelDial.txtUserInput.getText().trim());
            fuelBar.setValue(value);
        } catch (NumberFormatException e) {
        }
        // don't set the speed if the input can't be parsed
    }

    /**
     * Respond to user input in the Petrol textfield
     */
    private class PetrolValueListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setFuel();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setFuel();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }

    /**
     * Run the XML script file which generates events for the dashboard
     * indicators
     */
    private void runXMLScript() {
        try {
            DashboardEventGeneratorFromXML dbegXML = new DashboardEventGeneratorFromXML();

            // Register for speed events from the XML script file
            DashBoardEventListener dbelSpeed = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    speedDial.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("speed", dbelSpeed);

            // Register for petrol events from the XML script file
            DashBoardEventListener dbelPetrol = new DashBoardEventListener() {
                @Override
                public void processDashBoardEvent(Object originator, DashBoardEvent dbe) {
                    fuelDial.setValue(Integer.parseInt(dbe.getValue()));
                    fuelBar.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("petrol", dbelPetrol);

            // Process the script file - it willgenerate events as it runs
            dbegXML.processScriptFile(XML_SCRIPT);

        } catch (Exception ex) {
            Logger.getLogger(DashboardDemoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        final DashboardDemoMain me = new DashboardDemoMain();
    }
}
