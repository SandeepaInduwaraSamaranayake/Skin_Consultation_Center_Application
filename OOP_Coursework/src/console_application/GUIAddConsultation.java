package console_application;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import javax.swing.*;

public class GUIAddConsultation extends JFrame
{
    private JComboBox<String> cboDate;
    private JComboBox<String> cboMedLicenceNo;
    private JComboBox<String> cboMonth;
    private JComboBox<String> cboPatientId;
    private JComboBox<String> cboTimeSlot;
    private JComboBox<String> cboYear;
    private JScrollPane jspane;
    private JLabel lblAddConsultation;
    private JLabel lblAddDoctorBanner;
    private JLabel lblBannerHolder;
    private JLabel lblCloseBtn;
    private JLabel lblConsultDate;
    private JLabel lblConsultationIcon;
    private JLabel lblCost;
    private JLabel lblDocLicence;
    private JLabel lblNotes;
    private JLabel lblPatientId;
    private JLabel lblRequirement;
    private JLabel lblTimeslot;
    private JLabel lblWestminsterBanner;
    private JPanel pnlMain;
    private JTextArea txtAreaNotes;
    private JTextField txtCost;
    private JTextField txtRequirement;
    private JLabel lblAvailability;
    private JLabel btnCheckAvailability;
    int posX = 0, posY = 0;

    private static DatabaseConnector databaseConnector;                                      // create DatabaseConnector object.

    public GUIAddConsultation()
    {
        // database connector to connect to the database.
        DatabaseConnector databaseConnector = new DatabaseConnector(false);

        pnlMain = new JPanel();
        lblConsultationIcon = new JLabel();
        lblWestminsterBanner = new JLabel();
        lblAddDoctorBanner = new JLabel();
        lblBannerHolder = new JLabel();
        lblPatientId = new JLabel();
        lblCloseBtn = new JLabel();
        lblAddConsultation = new JLabel();
        cboTimeSlot = new JComboBox<>();
        lblDocLicence = new JLabel();
        lblConsultDate = new JLabel();
        txtRequirement = new JTextField();
        lblRequirement = new JLabel();
        cboDate = new JComboBox<>();
        cboYear = new JComboBox<>();
        cboMonth = new JComboBox<>();
        lblTimeslot = new JLabel();
        lblCost = new JLabel();
        jspane = new JScrollPane();
        txtAreaNotes = new JTextArea();
        txtCost = new JTextField();
        lblNotes = new JLabel();
        cboPatientId = new JComboBox<>();
        cboMedLicenceNo = new JComboBox<>();
        lblAvailability = new JLabel();
        btnCheckAvailability = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("frmAddPatients");
        setUndecorated(true);
        setResizable(false);



        pnlMain.setBackground(new Color(255, 255, 255));
        pnlMain.setPreferredSize(new Dimension(941, 600));
        pnlMain.setLayout(null);

        lblConsultationIcon.setFont(new Font("Segoe UI Black", 0, 48));
        lblConsultationIcon.setForeground(new Color(255, 255, 255));
        lblConsultationIcon.setIcon(new ImageIcon(getClass().getResource("image/consult.png")));
        pnlMain.add(lblConsultationIcon);
        lblConsultationIcon.setBounds(130, 30, 100, 80);

        lblWestminsterBanner.setFont(new Font("Segoe UI Black", 0, 28));
        lblWestminsterBanner.setForeground(new Color(255, 255, 255));
        lblWestminsterBanner.setText("WESTMINSTER SKIN CONSULTATION");
        pnlMain.add(lblWestminsterBanner);
        lblWestminsterBanner.setBounds(600, 43, 520, 60);

        lblAddDoctorBanner.setFont(new Font("Segoe UI Black", 0, 36));
        lblAddDoctorBanner.setForeground(new Color(255, 255, 255));
        lblAddDoctorBanner.setText("Add Consultations |  ");
        pnlMain.add(lblAddDoctorBanner);
        lblAddDoctorBanner.setBounds(240, 40, 390, 60);

        lblBannerHolder.setBackground(new Color(33, 0, 167));
        lblBannerHolder.setFont(new Font("Segoe UI Black", 0, 40));
        lblBannerHolder.setForeground(new Color(255, 255, 255));
        lblBannerHolder.setHorizontalAlignment(SwingConstants.CENTER);
        lblBannerHolder.setOpaque(true);
        lblBannerHolder.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();

                setLocation(x-posX-140, y-posY-40);
            }
        });
        lblBannerHolder.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                posX = evt.getX();
                posY = evt.getY();
            }
        });
        pnlMain.add(lblBannerHolder);
        lblBannerHolder.setBounds(100, 20, 1030, 100);

        lblPatientId.setBackground(new Color(255, 255, 255));
        lblPatientId.setFont(new Font("Segoe UI", 1, 15));
        lblPatientId.setForeground(new Color(51, 51, 51));
        lblPatientId.setHorizontalAlignment(SwingConstants.LEFT);
        lblPatientId.setText("Patient ID");
        pnlMain.add(lblPatientId);
        lblPatientId.setBounds(70, 160, 170, 40);

        lblCloseBtn.setBackground(new Color(33, 0, 167));
        lblCloseBtn.setFont(new Font("Segoe UI", 1, 18));
        lblCloseBtn.setForeground(new Color(255, 255, 255));
        lblCloseBtn.setHorizontalAlignment(SwingConstants.CENTER);
        lblCloseBtn.setText("Close");
        lblCloseBtn.setToolTipText("Close the window");
        lblCloseBtn.setOpaque(true);
        lblCloseBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
            public void mouseEntered(MouseEvent evt) {
                setLabelGrey(lblCloseBtn);
            }
            public void mouseExited(MouseEvent evt) {
                setLabelBlue( lblCloseBtn);
            }
        });
        pnlMain.add(lblCloseBtn);
        lblCloseBtn.setBounds(810, 650, 190, 40);

        lblAddConsultation.setBackground(new Color(33, 0, 167));
        lblAddConsultation.setFont(new Font("Segoe UI", 1, 18));
        lblAddConsultation.setForeground(new Color(255, 255, 255));
        lblAddConsultation.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddConsultation.setText("Add Consultation");
        lblAddConsultation.setToolTipText("Add Consultation");
        lblAddConsultation.setOpaque(true);
        lblAddConsultation.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                try
                {
                    int patientId = Integer.parseInt(cboPatientId.getSelectedItem().toString().substring(0, 6));
                    int docMedNo  = Integer.parseInt(cboMedLicenceNo.getSelectedItem().toString().substring(0,6));
                    String consultDate = cboYear.getSelectedItem() + "-" + (cboMonth.getSelectedIndex() + 1) + "-" + cboDate.getSelectedItem();
                    String requirement = txtRequirement.getText();
                    int timeSlotId = cboTimeSlot.getSelectedIndex()+1;
                    double cost = Double.parseDouble(txtCost.getText());
                    String notes = txtAreaNotes.getText();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;                                   // create a Date object using user input.
                    date = formatter.parse(consultDate);

                    Instant instant = date.toInstant();                                      // convert Date object to LocalDate type.
                    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                    LocalDate localDate = zonedDateTime.toLocalDate();

                    Validation val = new Validation();
                    String error = "";
                    if(val.validateRequirementOrNotes(requirement))
                    {
                        if (val.validateRequirementOrNotes(notes))
                        {
                            // add to arraylist
                            TimeSlot timeSlot = new TimeSlot(timeSlotId);
                            Consultation consultation = new Consultation(docMedNo, patientId, requirement.toUpperCase(), localDate, notes.toUpperCase(),timeSlot);

                            int assignedDocMedNoIfTimeslotAlreadyReserved = consultation.assignAnotherDoctorIfTimeslotReserved(timeSlot, localDate, docMedNo, 2);
                            consultation.setConsultDoctorMedNo(assignedDocMedNoIfTimeslotAlreadyReserved);

                            WestminsterSkinConsultationManager.getConsultArrList().add(consultation);

                            // add to database
                            String sql = "INSERT INTO Consultation (Consultation_Id, Consult_patient_id, Consult_doc_med_no, Patient_requirement, Consultation_placed_date, Consultation_placed_time, Consultation_date, Consultation_timeslot_id, Cost, Notes) VALUES (?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement statement = databaseConnector.getConnection().prepareStatement(sql);

                            statement.setInt(1, consultation.getConsultationId());
                            statement.setInt(2, consultation.getConsultPatientId());
                            statement.setInt(3, consultation.getConsultDoctorMedNo());
                            statement.setString(4, consultation.getPatientRequirement().toUpperCase());
                            statement.setDate(5, consultation.getDateConsultationPlacedDate());
                            statement.setTime(6, consultation.getTimeConsultationPlacedTime());
                            statement.setString(7, consultation.getConsultationDate().toString());
                            statement.setInt(8, consultation.getTimeSlot().getTimeslotId());
                            statement.setDouble(9,cost);
                            statement.setString(10, consultation.getNotes().toUpperCase());
                            statement.executeUpdate();

                        }else error += "|Notes|";
                    }else error += "|Requirement|";

                    if(error.equals(""))
                    {
                        Consultation consultation = WestminsterSkinConsultationManager.getConsultArrList().get(WestminsterSkinConsultationManager.getConsultArrList().size()-1);

                        JOptionPane.showMessageDialog(null, "THE CONSULTATION OF PATIENT ID %d - %s WITH DOCTOR MEDICAL ID %d - %s SUCCESSFULLY ADDED TO THE SYSTEM."
                                .formatted(patientId, consultation.getConsultationDoctor(docMedNo).getSurname() ,docMedNo, consultation.getConsultationPatient(patientId).getSurname()),"CONSULTATION ADDED TO THE SYSTEM", JOptionPane.INFORMATION_MESSAGE);
                        if(JOptionPane.YES_NO_OPTION == 0)
                        {
                            GUIConsultation.loadDataToTable();  // call load data method.
                            dispose();                          // close this window.
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error Occurred While Adding Consultation To The System", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(null, "Error Occurred While Adding Consultation To The System: %s".formatted(exc), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            public void mouseEntered(MouseEvent evt) {
                setLabelGrey(lblAddConsultation);
            }
            public void mouseExited(MouseEvent evt) {
                setLabelBlue(lblAddConsultation);
            }
        });
        pnlMain.add(lblAddConsultation);
        lblAddConsultation.setBounds(590, 650, 190, 40);

        cboTimeSlot.setFont(new Font("Segoe UI", 1, 15));
        cboTimeSlot.setForeground(new Color(51, 51, 51));


        pnlMain.add(cboTimeSlot);
        cboTimeSlot.setBounds(270, 400, 310, 40);

        lblDocLicence.setBackground(new Color(255, 255, 255));
        lblDocLicence.setFont(new Font("Segoe UI", 1, 15));
        lblDocLicence.setForeground(new Color(51, 51, 51));
        lblDocLicence.setHorizontalAlignment(SwingConstants.LEFT);
        lblDocLicence.setText("Doctor Licence No");
        pnlMain.add(lblDocLicence);
        lblDocLicence.setBounds(70, 220, 190, 40);

        lblConsultDate.setBackground(new Color(255, 255, 255));
        lblConsultDate.setFont(new Font("Segoe UI", 1, 15));
        lblConsultDate.setForeground(new Color(51, 51, 51));
        lblConsultDate.setHorizontalAlignment(SwingConstants.LEFT);
        lblConsultDate.setText("Consultation Date");
        pnlMain.add(lblConsultDate);
        lblConsultDate.setBounds(70, 280, 190, 40);

        txtRequirement.setBackground(new Color(255, 255, 255));
        txtRequirement.setFont(new Font("Segoe UI", 1, 15));
        txtRequirement.setForeground(new Color(51, 51, 51));
        txtRequirement.setToolTipText("E.g. Explain Your Requirement ");
        pnlMain.add(txtRequirement);
        txtRequirement.setBounds(270, 340, 730, 40);

        lblRequirement.setBackground(new Color(255, 255, 255));
        lblRequirement.setFont(new Font("Segoe UI", 1, 15));
        lblRequirement.setForeground(new Color(51, 51, 51));
        lblRequirement.setHorizontalAlignment(SwingConstants.LEFT);
        lblRequirement.setText("Requirement");
        pnlMain.add(lblRequirement);
        lblRequirement.setBounds(70, 340, 190, 40);

        cboDate.setFont(new Font("Segoe UI", 1, 15));
        cboDate.setForeground(new Color(51, 51, 51));
        cboDate.setModel(new DefaultComboBoxModel<>(new String[] { "Date" }));

        cboDate.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                cboDate.removeItem("Date");
            }
        });


        pnlMain.add(cboDate);
        cboDate.setBounds(550, 280, 120, 40);

        cboYear.setFont(new Font("Segoe UI", 1, 15));
        cboYear.setForeground(new Color(51, 51, 51));
        cboYear.setModel(new DefaultComboBoxModel<>(new String[] { "Year" }));
        cboYear.addItemListener(new ItemListener() {
            /**
             * get state change because we want to sync data dynamically. if year is 2008(leap year) and
             * month is February then it had 29 days but 2010(not a leap year) have only 28 days.
             * @param evt event handler.
             */
            public void itemStateChanged(ItemEvent evt) {
                if( evt.getStateChange() == ItemEvent.SELECTED)
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
            public void focusGained(FocusEvent evt) {
                cboYear.removeItem("Year");
            }
        });



        pnlMain.add(cboYear);
        cboYear.setBounds(270, 280, 120, 40);

        cboMonth.setFont(new Font("Segoe UI", 1, 15));
        cboMonth.setForeground(new Color(51, 51, 51));
        cboMonth.setModel(new DefaultComboBoxModel<>(new String[] { "Month" }));

        lblAvailability.setBackground(new java.awt.Color(255, 255, 255));
        lblAvailability.setFont(new java.awt.Font("Segoe UI", 1, 15));
        lblAvailability.setForeground(new java.awt.Color(51, 51, 51));
        lblAvailability.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlMain.add(lblAvailability);
        lblAvailability.setBounds(810, 400, 190, 40);

        btnCheckAvailability.setBackground(new java.awt.Color(33, 0, 167));
        btnCheckAvailability.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnCheckAvailability.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckAvailability.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCheckAvailability.setText("Check Availability");
        btnCheckAvailability.setToolTipText("Check the availability of timeslot");
        btnCheckAvailability.setOpaque(true);
        btnCheckAvailability.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent evt) {
                    checkAvailability();
                }
                public void mouseEntered(MouseEvent evt) {
                    btnCheckAvailability.setBackground( new Color(51,51,51));
                }
                public void mouseExited(MouseEvent evt) {
                    btnCheckAvailability.setBackground(new Color(33,0,167));
                }
        });
        pnlMain.add(btnCheckAvailability);
        btnCheckAvailability.setBounds(610, 400, 190, 40);



        /**
         * get state change because we want to sync data dynamically. if year is 2008(leap year) and
         * month is February then it had 29 days but 2010(not a leap year) have only 28 days.
         * @param evt event handler.
         */
        cboMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if(evt.getStateChange() == ItemEvent.SELECTED)
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
            public void focusGained(FocusEvent evt) {
                cboMonth.removeItem("Month");
            }
        });


        pnlMain.add(cboMonth);
        cboMonth.setBounds(410, 280, 120, 40);

        lblTimeslot.setBackground(new Color(255, 255, 255));
        lblTimeslot.setFont(new Font("Segoe UI", 1, 15));
        lblTimeslot.setForeground(new Color(51, 51, 51));
        lblTimeslot.setHorizontalAlignment(SwingConstants.LEFT);
        lblTimeslot.setText("Timeslot");
        pnlMain.add(lblTimeslot);
        lblTimeslot.setBounds(70, 400, 190, 40);

        lblCost.setBackground(new Color(255, 255, 255));
        lblCost.setFont(new Font("Segoe UI", 1, 15));
        lblCost.setForeground(new Color(51, 51, 51));
        lblCost.setHorizontalAlignment(SwingConstants.LEFT);
        lblCost.setText("Cost");
        pnlMain.add(lblCost);
        lblCost.setBounds(70, 460, 190, 40);

        txtAreaNotes.setBackground(new Color(255, 255, 255));
        txtAreaNotes.setColumns(20);
        txtAreaNotes.setFont(new Font("Segoe UI", 1, 15));
        txtAreaNotes.setForeground(new Color(51, 51, 51));
        txtAreaNotes.setRows(5);
        txtAreaNotes.setToolTipText("Important information about patient");
        jspane.setViewportView(txtAreaNotes);

        pnlMain.add(jspane);
        jspane.setBounds(270, 520, 730, 110);

        txtCost.setEditable(false);
        txtCost.setBackground(new Color(255, 255, 255));
        txtCost.setFont(new Font("Segoe UI", 1, 15));
        txtCost.setForeground(new Color(51, 51, 51));
        txtCost.setToolTipText("Cost of the Consultation");
        pnlMain.add(txtCost);
        txtCost.setBounds(270, 460, 730, 40);

        lblNotes.setBackground(new Color(255, 255, 255));
        lblNotes.setFont(new Font("Segoe UI", 1, 15));
        lblNotes.setForeground(new Color(51, 51, 51));
        lblNotes.setHorizontalAlignment(SwingConstants.LEFT);
        lblNotes.setText("Notes");
        pnlMain.add(lblNotes);
        lblNotes.setBounds(70, 520, 190, 40);

        cboPatientId.setEditable(false);
        cboPatientId.setFont(new Font("Segoe UI", 1, 15));
        cboPatientId.setForeground(new Color(51, 51, 51));

        cboPatientId.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent evt)
            {
                Consultation consult = new Consultation();
                double cost = consult.decideCost(Integer.parseInt(cboPatientId.getSelectedItem().toString().substring(0,6)),2);
                txtCost.setText(Double.toString(cost));
            }
        });

        pnlMain.add(cboPatientId);
        cboPatientId.setBounds(270, 160, 730, 40);

        cboMedLicenceNo.setEditable(false);
        cboMedLicenceNo.setFont(new Font("Segoe UI", 1, 15));
        cboMedLicenceNo.setForeground(new Color(51, 51, 51));

        pnlMain.add(cboMedLicenceNo);
        cboMedLicenceNo.setBounds(270, 220, 730, 40);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 1130, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);

        cboMonth.setEnabled(false);
        cboDate.setEnabled(false);
        setYearComboBoxValues();
        setMonthComboBoxValues();

        setPatientIdValues();
        setDocMedLicenceIds();
        setTimeslots();
    }

    public void setPatientIdValues()
    {
        for (Patient patient: WestminsterSkinConsultationManager.getPatientArrayList())
        {
            String patientListItem = patient.getPatientId() + " - " + patient.getName() + " " + patient.getSurname();
            cboPatientId.addItem(patientListItem);
        }
    }

    public void setDocMedLicenceIds()
    {
        for (Doctor doctor:WestminsterSkinConsultationManager.getDocArrList())
        {
            String doc = doctor.getMedicalLicenceNumber() + " - " + doctor.getName() + " " + doctor.getSurname();
            cboMedLicenceNo.addItem(doc);
        }
    }

    public void setTimeslots()
    {
        TimeSlot timeSlot = new TimeSlot();
        for (int i=1; i<=10;i++)
        {
            cboTimeSlot.addItem(timeSlot.getStringTimeslotForGivenId(i));
        }

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
        for(int i = year; i <= year+100; i++)
        {
            cboYear.addItem(String.valueOf(i));
        }
    }

    public void checkAvailability()
    {
        TimeSlot timeSlot = new TimeSlot();
        LocalDate localConsultDate;
        try
        {
            if(!(cboYear.getSelectedItem().equals("Year") || cboMonth.getSelectedItem().equals("Month") || cboDate.getSelectedItem().equals("Date")))
            {
                String consultDate = cboYear.getSelectedItem() + "-" + (cboMonth.getSelectedIndex() + 1) + "-" + cboDate.getSelectedItem();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;                                   // create a Date object using user input.
                date = formatter.parse(consultDate);

                Instant instant = date.toInstant();                                      // convert Date object to LocalDate type.
                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                localConsultDate = zonedDateTime.toLocalDate();

                String [] availabilityList = timeSlot.availableTimeSlots( Integer.parseInt(cboMedLicenceNo.getSelectedItem().toString().substring(0,6)), localConsultDate );

                lblAvailability.setText(availabilityList[cboTimeSlot.getSelectedIndex()]);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please Enter Consultation Date Before Check Availability", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, "Error occurred while checking availability of consultations", "Error", JOptionPane.ERROR_MESSAGE);
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
