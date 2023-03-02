
package console_application;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import javax.swing.*;

public class GUIAddPatients extends JFrame
{
    private JComboBox<String> cboDate;
    private JComboBox<String> cboMonth;
    private JComboBox<String> cboYear;

    private JLabel lblAddPatientBanner;
    private JLabel lblAddPatientBtn;
    private JLabel lblAddPatientIcon;
    private JLabel lblAddress;
    private JLabel lblBannerHolder;
    private JLabel lblCloseBtn;
    private JLabel lblDOB;
    private JLabel lblDescription;
    private JLabel lblFirstName;
    private JLabel lblMedLicenceNo;
    private JLabel lblMobileNumber;
    private JLabel lblSurname;
    private JLabel lblWestminsterBanner;
    private JPanel pnlMain;
    private JTextField txtAddress;

    private JTextField txtFirstName;
    private JTextField txtMedLicenceNo;
    private JTextField txtMobileNumber;
    private JTextField txtSurname;
    int posX = 0, posY = 0;

    public GUIAddPatients()
    {
        pnlMain = new JPanel();
        lblAddPatientIcon = new JLabel();
        lblWestminsterBanner = new JLabel();
        lblAddPatientBanner = new JLabel();
        lblBannerHolder = new JLabel();
        lblMedLicenceNo = new JLabel();
        lblCloseBtn = new JLabel();
        lblAddPatientBtn = new JLabel();
        txtMedLicenceNo = new JTextField();
        lblFirstName = new JLabel();
        lblDOB = new JLabel();
        txtFirstName = new JTextField();
        txtSurname = new JTextField();
        lblSurname = new JLabel();
        cboDate = new JComboBox<>();
        cboYear = new JComboBox<>();
        cboMonth = new JComboBox<>();
        lblMobileNumber = new JLabel();
        txtMobileNumber = new JTextField();
        lblAddress = new JLabel();
        txtAddress = new JTextField();
        lblDescription = new JLabel();

        // database connector to connect to the database.
        DatabaseConnector databaseConnector = new DatabaseConnector(false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("frmAddPatients");
        setUndecorated(true);
        setResizable(false);

        pnlMain.setBackground(new Color(255, 255, 255));
        pnlMain.setPreferredSize(new Dimension(941, 600));
        pnlMain.setLayout(null);

        lblAddPatientIcon.setFont(new Font("Segoe UI Black", 0, 48));
        lblAddPatientIcon.setForeground(new Color(255, 255, 255));
        lblAddPatientIcon.setIcon(new ImageIcon(getClass().getResource("image/add_person.png")));
        pnlMain.add(lblAddPatientIcon);
        lblAddPatientIcon.setBounds(130, 30, 100, 80);

        lblWestminsterBanner.setFont(new Font("Segoe UI Black", 0, 28));
        lblWestminsterBanner.setForeground(new Color(255, 255, 255));
        lblWestminsterBanner.setText("WESTMINSTER SKIN CONSULTATION");
        pnlMain.add(lblWestminsterBanner);
        lblWestminsterBanner.setBounds(510, 40, 520, 60);

        lblAddPatientBanner.setFont(new Font("Segoe UI Black", 0, 36));
        lblAddPatientBanner.setForeground(new Color(255, 255, 255));
        lblAddPatientBanner.setText("Add Patients |  ");
        pnlMain.add(lblAddPatientBanner);
        lblAddPatientBanner.setBounds(240, 40, 280, 60);

        lblBannerHolder.setBackground(new Color(33, 0, 167));
        lblBannerHolder.setFont(new Font("Segoe UI Black", 0, 40));
        lblBannerHolder.setForeground(new Color(255, 255, 255));
        lblBannerHolder.setHorizontalAlignment(SwingConstants.CENTER);
        lblBannerHolder.setOpaque(true);
        lblBannerHolder.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event) {
                int x = event.getXOnScreen();
                int y = event.getYOnScreen();

                setLocation(x-posX-140, y-posY-40);
            }
        });
        lblBannerHolder.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                posX = event.getX();
                posY = event.getY();
            }
        });
        pnlMain.add(lblBannerHolder);
        lblBannerHolder.setBounds(100, 20, 960, 100);

        lblMedLicenceNo.setBackground(new Color(255, 255, 255));
        lblMedLicenceNo.setFont(new Font("Segoe UI", 1, 15));
        lblMedLicenceNo.setForeground(new Color(51, 51, 51));
        lblMedLicenceNo.setHorizontalAlignment(SwingConstants.LEFT);
        lblMedLicenceNo.setText("Patient ID");
        pnlMain.add(lblMedLicenceNo);
        lblMedLicenceNo.setBounds(70, 160, 170, 40);

        lblCloseBtn.setBackground(new Color(33, 0, 167));
        lblCloseBtn.setFont(new Font("Segoe UI", 1, 18));
        lblCloseBtn.setForeground(new Color(255, 255, 255));
        lblCloseBtn.setHorizontalAlignment(SwingConstants.CENTER);
        lblCloseBtn.setText("Close");
        lblCloseBtn.setToolTipText("Close the window");
        lblCloseBtn.setOpaque(true);
        lblCloseBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                dispose();
            }
            public void mouseEntered(MouseEvent event) {
                setLabelGrey(lblCloseBtn);
            }
            public void mouseExited(MouseEvent event) {
                setLabelBlue( lblCloseBtn);
            }
        });

        pnlMain.add(lblCloseBtn);
        lblCloseBtn.setBounds(810, 540, 190, 40);

        lblAddPatientBtn.setBackground(new Color(33, 0, 167));
        lblAddPatientBtn.setFont(new Font("Segoe UI", 1, 18));
        lblAddPatientBtn.setForeground(new Color(255, 255, 255));
        lblAddPatientBtn.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddPatientBtn.setText("Add Patient");
        lblAddPatientBtn.setToolTipText("Add Patient");
        lblAddPatientBtn.setOpaque(true);
        lblAddPatientBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent event)
            {
                String patientId = txtMedLicenceNo.getText();
                String firstname = txtFirstName.getText();
                String surname = txtSurname.getText();
                String dateOfBirth = cboYear.getSelectedItem() + "-" + (cboMonth.getSelectedIndex() + 1) + "-" + cboDate.getSelectedItem();
                String mobileNumber = txtMobileNumber.getText();
                String address = txtAddress.getText();


                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;                                   // create a Date object using user input.
                    try
                    {
                        date = formatter.parse(dateOfBirth);

                        Instant instant = date.toInstant();                                      // convert Date object to LocalDate type.
                        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                        LocalDate localDate = zonedDateTime.toLocalDate();

                        String error = "";
                        Validation val = new Validation();
                        if( val.validateMedLicenceNumberOrPatientId(patientId, 2,3) == 1 )
                        {
                            if(val.validateName(firstname))
                            {
                                if(val.validateName(surname))
                                {
                                    if(val.validateDate(localDate.toString(), true) )
                                    {
                                        if(val.validateMobileNo(mobileNumber))
                                        {
                                            if(val.validateAddress(address))
                                            {
                                                // put into arraylist
                                                Patient patient = new Patient(Integer.parseInt(patientId), firstname.toUpperCase(), surname.toUpperCase(), localDate, mobileNumber, address.toUpperCase());
                                                WestminsterSkinConsultationManager.getPatientArrayList().add(patient);

                                                // put in the database
                                                String sql = "INSERT INTO Patient (Patient_id, First_name, Surname, Birth_day, Mobile_number, Address) VALUES (?,?,?,?,?,?)";
                                                try
                                                {
                                                    PreparedStatement statement = databaseConnector.getConnection().prepareStatement(sql);
                                                    statement.setInt(1, Integer.parseInt(patientId));
                                                    statement.setString(2, firstname.toUpperCase());
                                                    statement.setString(3, surname.toUpperCase());
                                                    statement.setString(4, localDate.toString());
                                                    statement.setString(5, mobileNumber);
                                                    statement.setString(6, address.toUpperCase());

                                                    statement.executeUpdate();
                                                }
                                                catch (SQLException exc)
                                                {
                                                    throw new RuntimeException(exc);
                                                }
                                            }else
                                                error += "|Address|";
                                        }else
                                            error += "|Mobile Number|";
                                    }else
                                        error += "|Birth Day|";
                                }else
                                    error += "|Surname|";
                            }else
                                error += "|First Name|";
                        } else
                            error += "|Medical Licence Number|";

                        if(error.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "THE PATIENT WITH THE PATIENT ID %s - %s %s SUCCESSFULLY ADDED TO THE SYSTEM."
                                    .formatted(patientId, firstname.toUpperCase(), surname.toUpperCase()),"PATIENT ADDED TO THE SYSTEM", JOptionPane.INFORMATION_MESSAGE);
                            if(JOptionPane.YES_NO_OPTION == 0)
                            {
                                GUIPatient.loadDataToTable();  // call load data method.
                                dispose();                     // close this window.
                            }
                        }
                        else
                        {
                            // if an error occurred while adding the row, show an error message.
                            JOptionPane.showMessageDialog(null, "Error occurred in %s field/s while adding the patient".formatted(error), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception exc)
                    {
                        // if an error occurred while adding the row, show an error message.
                        JOptionPane.showMessageDialog(null, "Error occurred while adding patient to the system: %s".formatted(exc), "Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
            public void mouseEntered(MouseEvent event)
            {
                setLabelGrey(lblAddPatientBtn);
            }
            public void mouseExited(MouseEvent event)
            {
                setLabelBlue( lblAddPatientBtn);
            }
        });
        pnlMain.add(lblAddPatientBtn);
        lblAddPatientBtn.setBounds(590, 540, 190, 40);

        txtMedLicenceNo.setBackground(new Color(255, 255, 255));
        txtMedLicenceNo.setFont(new Font("Segoe UI", 1, 15));
        txtMedLicenceNo.setForeground(new Color(51, 51, 51));
        txtMedLicenceNo.setToolTipText("E.g. 123456");
        txtMedLicenceNo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {

            }
        });
        txtMedLicenceNo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {

            }
            public void mouseExited(MouseEvent event) {

            }
        });
        pnlMain.add(txtMedLicenceNo);
        txtMedLicenceNo.setBounds(270, 160, 730, 40);

        lblFirstName.setBackground(new Color(255, 255, 255));
        lblFirstName.setFont(new Font("Segoe UI", 1, 15));
        lblFirstName.setForeground(new Color(51, 51, 51));
        lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
        lblFirstName.setText("First Name");
        pnlMain.add(lblFirstName);
        lblFirstName.setBounds(70, 220, 190, 40);

        lblDOB.setBackground(new Color(255, 255, 255));
        lblDOB.setFont(new Font("Segoe UI", 1, 15));
        lblDOB.setForeground(new Color(51, 51, 51));
        lblDOB.setHorizontalAlignment(SwingConstants.LEFT);
        lblDOB.setText("Date of Birth");
        pnlMain.add(lblDOB);
        lblDOB.setBounds(70, 340, 190, 40);

        txtFirstName.setBackground(new Color(255, 255, 255));
        txtFirstName.setFont(new Font("Segoe UI", 1, 15));
        txtFirstName.setForeground(new Color(51, 51, 51));
        txtFirstName.setToolTipText("E.g. John");
        pnlMain.add(txtFirstName);
        txtFirstName.setBounds(270, 220, 730, 40);

        txtSurname.setBackground(new Color(255, 255, 255));
        txtSurname.setFont(new Font("Segoe UI", 1, 15));
        txtSurname.setForeground(new Color(51, 51, 51));
        txtSurname.setToolTipText("E.g. Steve");
        pnlMain.add(txtSurname);
        txtSurname.setBounds(270, 280, 730, 40);

        lblSurname.setBackground(new Color(255, 255, 255));
        lblSurname.setFont(new Font("Segoe UI", 1, 15));
        lblSurname.setForeground(new Color(51, 51, 51));
        lblSurname.setHorizontalAlignment(SwingConstants.LEFT);
        lblSurname.setText("Surname");
        pnlMain.add(lblSurname);
        lblSurname.setBounds(70, 280, 190, 40);

        cboDate.setFont(new Font("Segoe UI", 1, 15));
        cboDate.setForeground(new Color(51, 51, 51));
        cboDate.setModel(new DefaultComboBoxModel<>(new String[] { "Date" }));
        cboDate.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {

            }
        });
        cboDate.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                cboDate.removeItem("Date");
            }
        });
        pnlMain.add(cboDate);
        cboDate.setBounds(550, 340, 120, 40);

        cboYear.setFont(new Font("Segoe UI", 1, 15));
        cboYear.setForeground(new Color(51, 51, 51));
        cboYear.setModel(new DefaultComboBoxModel<>(new String[] { "Year" }));
        cboYear.setToolTipText("");
        cboYear.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {

                if( event.getStateChange() == ItemEvent.SELECTED)
                {
                    int year = Integer.parseInt( (String)cboYear.getSelectedItem() );
                    String selectedMonth = (String) cboMonth.getSelectedItem();

                    if(!selectedMonth.equals("Month"))
                    {
                        cboDate.removeAllItems();
                        int noOfDays = getNoOfDays(selectedMonth, year);

                        for(int i=1; i<=noOfDays; i++)
                        {
                            cboDate.addItem(String.valueOf(i));
                        }
                    }
                }
                cboMonth.setEnabled(true);
            }
        });
        cboYear.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                cboYear.removeItem("Year");
            }
        });
        cboYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

            }
        });
        cboYear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent event) {

            }
        });
        pnlMain.add(cboYear);
        cboYear.setBounds(270, 340, 120, 40);

        cboMonth.setFont(new Font("Segoe UI", 1, 15));
        cboMonth.setForeground(new Color(51, 51, 51));
        cboMonth.setModel(new DefaultComboBoxModel<>(new String[] { "Month" }));
        cboMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                /**
                 * get state change because we want to sync data dynamically. if year is 2008(leap year) and
                 * month is February then it had 29 days but 2010(not a leap year) have only 28 days.
                 * @param event event handler.
                 */
                if(event.getStateChange() == ItemEvent.SELECTED)
                {
                    String selectedMonth = (String) cboMonth.getSelectedItem();
                    int year = Integer.parseInt( (String)cboYear.getSelectedItem() );

                    cboDate.removeAllItems();
                    int noOfDays = getNoOfDays(selectedMonth, year);
                    for(int i=1; i<=noOfDays; i++)
                    {
                        cboDate.addItem(String.valueOf(i));
                    }
                }
                cboDate.setEnabled(true);
            }
        });
        cboMonth.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                cboMonth.removeItem("Month");
            }
        });
        pnlMain.add(cboMonth);
        cboMonth.setBounds(410, 340, 120, 40);

        lblMobileNumber.setBackground(new Color(255, 255, 255));
        lblMobileNumber.setFont(new Font("Segoe UI", 1, 15));
        lblMobileNumber.setForeground(new Color(51, 51, 51));
        lblMobileNumber.setHorizontalAlignment(SwingConstants.LEFT);
        lblMobileNumber.setText("Mobile No");
        pnlMain.add(lblMobileNumber);
        lblMobileNumber.setBounds(70, 400, 190, 40);

        txtMobileNumber.setBackground(new Color(255, 255, 255));
        txtMobileNumber.setFont(new Font("Segoe UI", 1, 15));
        txtMobileNumber.setForeground(new Color(51, 51, 51));
        txtMobileNumber.setToolTipText("E.g. +94773568837");
        pnlMain.add(txtMobileNumber);
        txtMobileNumber.setBounds(270, 400, 730, 40);

        lblAddress.setBackground(new Color(255, 255, 255));
        lblAddress.setFont(new Font("Segoe UI", 1, 15));
        lblAddress.setForeground(new Color(51, 51, 51));
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblAddress.setText("Address");
        pnlMain.add(lblAddress);
        lblAddress.setBounds(70, 460, 190, 40);


        txtAddress.setBackground(new Color(255, 255, 255));
        txtAddress.setFont(new Font("Segoe UI", 1, 15));
        txtAddress.setForeground(new Color(51, 51, 51));
        txtAddress.setToolTipText("E.g. 76 PARK ROAD, LONDON");
        pnlMain.add(txtAddress);
        txtAddress.setBounds(270, 460, 730, 40);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 1059, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
        cboMonth.setEnabled(false);
        cboDate.setEnabled(false);
        setYearComboBoxValues();
        setMonthComboBoxValues();
    }



    public void setLabelGrey(JLabel label)
    {
        label.setBackground(new Color(51,51,51));  // grey
    }

    public void setLabelBlue(JLabel label)
    {
        label.setBackground(new Color(33,0,167)); // blue
    }


    /**
     * This method will set values for year combo box.
     */
    public  void setYearComboBoxValues()
    {
        int year = Year.now().getValue();
        for(int i = 1900; i <= year; i++)
        {
            cboYear.addItem(String.valueOf(i));
        }
    }

    /**
     * This method will set values for month combo box.
     */
    public void setMonthComboBoxValues()
    {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthNames = dfs.getMonths();
        for(int i=0; i < 12; i++)
        {
            cboMonth.addItem(monthNames[i]);
        }
    }

    /**
     * This method will check the selected year is a leap year or not.
     * @param year the provided year for checking.
     * @return true if a leap year otherwise false.
     */
    public boolean isLeapYear(int year)
    {
        if (year % 400 == 0)
        {
            return true; // divisible by 400
        }
        else if (year % 100 == 0)
        {
            return false; // divisible by 100 but not by 400
        }
        else if (year % 4 == 0)
        {
            return true; // divisible by 4 but not by 100
        }
        else
        {
            return false; // not divisible by 4
        }
    }


    /**
     * get no of days of a particular month in a particular year.
     * @param selectedMonth  the month that need to check.
     * @param year the year that need to check.
     * @return no of days.
     */
    public int getNoOfDays(String selectedMonth, int year)
    {

        // Populate the second combo box with different items based on the selected item in the first combo box
        if ( selectedMonth.equals("February") && isLeapYear(year) )
        {
            return 29;
        }
        else if ( selectedMonth.equals("February") && (!isLeapYear(year)) )
        {
            return 28;
        }
        else if ( selectedMonth.equals("January") || selectedMonth.equals("March") || selectedMonth.equals("May") || selectedMonth.equals("July") || selectedMonth.equals("August") || selectedMonth.equals("October") || selectedMonth.equals("December") )
        {
            return 31;
        }
        else
        {
            return 30;
        }
    }

    // setLocationRelativeTo(null); will show the interface middle of the screen --------------> Remember this.
}
