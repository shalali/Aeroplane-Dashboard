package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import uk.ac.gre.comp1549.dashboard.DashboardDemoMain;

/**
 * DialPanel. Container for DialDrawPanel to hold dial and label. If a label is
 * not needed DialDrawPanel an be used on its own
 *
 * @author COMP1549
 * @version 2.0
 */
public class DialPanel extends JPanel implements IIndicatorPanel {

   

    private JLabel lblTitle; // the label which always appears above the dial
    public JTextField txtUserInput;

    IIndicator indicator;
    String indicatorType;
    IndicatorFactory indicatorFactory = new IndicatorFactory();

    /**
     * Default constructor
     *
     * @param degrees
     */
    //STATIC POLYMORPHISM 
    public DialPanel(String userDesiredType) {
        setLayout(new BorderLayout());
        indicatorType = userDesiredType;
        labelDial();

        //Indicator fullDial = indicatorFactory.createIndicator("full");
        indicator = getIndicatorFromFactory(indicatorType);
        if (indicator != null) {//allows for null singleton handling with invalid parameter
            add((Component) indicator, BorderLayout.CENTER);
            displayInputFields();

            DocumentListener inputListener = new UserInputListener();
            txtUserInput.getDocument().addDocumentListener(inputListener);
        }
    }

//METHOD THAT BRIDGES DIAL PANEL TO FACOTORY CLASS
    public IIndicator getIndicatorFromFactory(String indicator) {
        IIndicator in = indicatorFactory.createIndicator(indicator);

        return in;
    }

    //Listens for changes in user input fields for changes
    private class UserInputListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent event) {
            setDialValue();
        }

        @Override
        public void removeUpdate(DocumentEvent event) {
            setDialValue();
        }

        @Override
        public void changedUpdate(DocumentEvent event) {
        }
    }

    //set Dial Value according to user input
    public void setDialValue() {
        try {
            int value = Integer.parseInt(txtUserInput.getText().trim());
            indicator.setValue(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, " Invalid input type: " + e.getMessage());

        }
        // don't set the speed if the input can't be parsed
    }

    private void displayInputFields() {
        // this.add(new JLabel("Input value"), BorderLayout.SOUTH);
        txtUserInput = new JTextField("0", 3);
        //txtuserInput.SET
        txtUserInput.setMaximumSize(txtUserInput.getPreferredSize());
        add(txtUserInput, BorderLayout.SOUTH);
    }

    private void labelDial() {
        setLayout(new BorderLayout());

        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // position the label above the dial
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
    }

    /**
     * Set the value for the dial
     *
     * @param value - value for the dial
     */
    public void setValue(int value) {//USED FOR XML?
        indicator.setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {

        lblTitle.setText(label);
    }
}
