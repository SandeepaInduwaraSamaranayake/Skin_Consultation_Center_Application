package console_application;

import javax.swing.*;                               // importing swing whole package.
import java.awt.*;                                  // importing java awt package.
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GUIHome extends JFrame                 // trying to create a JFrame, so extending from JFrame class in swing package.
{
    private JPanel pnlMain;
    private JLabel lblBackgroundImage;
    private JLabel lblConsultIcon;
    private JLabel lblConsultationsTileName;
    private JLabel lblDate;
    private JLabel lblDoctorsIcon;
    private JLabel lblDoctorsTileName;
    private JLabel lblHome;
    private JLabel lblLogOutIcon;
    private JLabel lblPatientIcon;
    private JLabel lblPatientsTileName;
    private JLabel lblQuitText;
    private JLabel lblSettingsIcon;
    private JLabel lblSettingsText;
    private JLabel lblSummeryIcon;
    private JLabel lblSummeryName;
    private JLabel lblTime;
    private JLabel lblTitle;
    private JPanel pnlConsult;
    private JPanel pnlDateTime;
    private JPanel pnlDoctor;
    private JPanel pnlPatients;
    private JPanel pnlSettings;
    private JPanel pnlSummery;
    private JPanel pnlQuit;

    int posX = 0, posY = 0;               // assigning position x and position y default values.
    public GUIHome()                      // constructor for GUIHome class.
    {
        createGuiElements();              // call createGuiElements() method to initialize the gui components to above declared objects.
        new Thread()
        {
            @Override
            public void run()
            {
                while(true)
                {
                    Calendar cal = new GregorianCalendar();                                     // create GregorianCalendar object as cal.

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM-dd"); // 'MMMM' will show month in words like 'January'.
                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");   // 'a' will show the am,pm of the day. if we use 'HH', it will show hours in 24 time format.

                    String time = timeFormat.format(cal.getTime());                     // create a string containing formatted time.
                    String date = dateFormat.format(cal.getTime());                     // create a string containing formatted date.
                    lblTime.setText(time);                                              // setting time to lblTime.
                    lblDate.setText(date);                                              // setting date to lblDate.
                }
            }
        }.start();                                                                      // start the thread when loading the JFrame.
    }

    /**
     * this method will create gui elements and set their properties according to the need.
     */
    private void createGuiElements()
    {
        //initialising created objects when form is loading.
        pnlMain = new JPanel();
        lblHome = new JLabel();
        pnlDoctor = new JPanel();
        lblDoctorsIcon = new JLabel();
        lblDoctorsTileName = new JLabel();
        pnlSummery = new JPanel();
        lblSummeryName = new JLabel();
        lblSummeryIcon = new JLabel();
        pnlPatients = new JPanel();
        lblPatientsTileName = new JLabel();
        lblPatientIcon = new JLabel();
        pnlConsult = new JPanel();
        lblConsultIcon = new JLabel();
        lblConsultationsTileName = new JLabel();
        pnlQuit = new JPanel();
        lblQuitText = new JLabel();
        lblLogOutIcon = new JLabel();
        pnlSettings = new JPanel();
        lblSettingsText = new JLabel();
        lblSettingsIcon = new JLabel();
        pnlDateTime = new JPanel();
        lblTime = new JLabel();
        lblDate = new JLabel();
        lblTitle = new JLabel();
        lblBackgroundImage = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);      // setting default close operation to DISPOSE_ON_CLOSE. this will completely close the GUI program when user close the window, but console application will run normally.
        setTitle("Westminster Skin Consultation | Home");                // setting title of the window.
        setName("HomeFrame");                                            // setting name of the JFrame.
        setUndecorated(true);                                            // removing system default titlebar.
        setResizable(false);                                             // restricting user from resizing the window.

        pnlMain.setLayout(null);                                         // set main panel layout to null layout.

        lblHome.setIcon(new ImageIcon(getClass().getResource("image/home.png")));   // setting image icon to the lblHome.
        pnlMain.add(lblHome);                                            // add label lblHome to the JPanel.
        lblHome.setBounds(20, 20, 110, 110);         // setting location, width and height of the label

        pnlDoctor.setBackground(new Color(33, 0, 167));        // setting background color of the pnlDoctor panel.
        pnlDoctor.addMouseListener(new MouseAdapter()       // adding mouse listener to pnlDoctor panel to detect mouse actions on pnlDoctor.
        {
            public void mouseClicked(MouseEvent event)      // this will execute when user click pnlDoctor panel.
            {
                showDoctors();                            // when user clicked pnlDoctor panel Doctor window will display
            }
            public void mouseEntered(MouseEvent event)     // this will execute when user cursor entered pnlDoctor panel.
            {
                setPanelGrey(pnlDoctor);                   // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)     // this will execute when user cursor exited pnlDoctor panel.
            {
                setPanelBlue(pnlDoctor);                  // set panel color to blue when mouse exited from its region.
            }
        });

        lblDoctorsIcon.setIcon(new ImageIcon(getClass().getResource("image/doctor.png"))); // setting image to the lblDoctorsIcon.
        lblDoctorsIcon.addMouseListener(new MouseAdapter()    // adding mouse listener to lblDoctorsIcon label to detect mouse actions on lblDoctorsIcon.
        {
            public void mouseClicked(MouseEvent event)          // this will execute when user click lblDoctorsIcon label.
            {
                showDoctors();                                // when user clicked lblDoctorsIcon label Doctor window will display
            }
            public void mouseEntered(MouseEvent event)          // this will execute when user mouse cursor entered lblDoctorsIcon label.
            {
                setPanelGrey(pnlDoctor);                       // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)          // this will execute when user mouse cursor entered lblDoctorsIcon label.
            {
                setPanelBlue(pnlDoctor);                      // set panel color to blue when mouse exited from its region.
            }
        });

        lblDoctorsTileName.setFont(new Font("Segoe UI Black", 0, 20));  // setting font of lblDoctorsTileName.
        lblDoctorsTileName.setForeground(new Color(255, 255, 255));             // setting text color of lblDoctorsTileName.
        lblDoctorsTileName.setHorizontalAlignment(SwingConstants.CENTER);                // setting alignment of lblDoctorsTileName.
        lblDoctorsTileName.setText("Doctors");                                           // setting text to lblDoctorsTileName.
        lblDoctorsTileName.addMouseListener(new MouseAdapter()    // adding mouse listener to lblDoctorsTileName label to detect mouse actions on lblDoctorsTileName.
        {
            public void mouseClicked(MouseEvent event)               // this will execute when user click lblDoctorsTileName label.
            {
                showDoctors();                                     // when user clicked lblDoctorsIcon label Doctor window will display
            }
            public void mouseEntered(MouseEvent event)              // this will execute when user mouse cursor entered lblDoctorsTileName label.
            {
                setPanelGrey(pnlDoctor);                            // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)               // this will execute when user mouse cursor exited lblDoctorsTileName label.
            {
                setPanelBlue(pnlDoctor);                            // set panel color to blue when mouse exited from its region.
            }
        });

        // creating group layout to patients showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlDoctorLayout = new GroupLayout(pnlDoctor);
        pnlDoctor.setLayout(pnlDoctorLayout);

        // this is the horizontal group
        pnlDoctorLayout.setHorizontalGroup(
                pnlDoctorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addGroup(pnlDoctorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDoctorLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlDoctorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlDoctorLayout.createSequentialGroup()
                                                        .addGap(50, 50, 50)
                                                        .addComponent(lblDoctorsIcon))
                                                .addComponent(lblDoctorsTileName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        // this is the vertical group
        pnlDoctorLayout.setVerticalGroup(
                pnlDoctorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addGroup(pnlDoctorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDoctorLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblDoctorsIcon)
                                        .addGap(0, 0, 0)
                                        .addComponent(lblDoctorsTileName, GroupLayout.PREFERRED_SIZE, 30,GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlDoctor);                                                // add designed panel to the main panel
        pnlDoctor.setBounds(50, 160, 220, 150);           // set coordinates, width and height of the pnlDoctor panel

        pnlSummery.setBackground(new Color(33, 0, 167));    // setting background color of the pnlSummery panel.
        pnlSummery.addMouseListener(new MouseAdapter()         // adding mouse listener to pnlSummery panel to detect mouse actions on pnlSummery.
        {
            public void mouseEntered(MouseEvent event)         // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlSummery);                     // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)          // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlSummery);                      // set panel color to blue when mouse exited from its region.
            }
        });

        lblSummeryName.setBackground(new Color(51, 51, 51));                  // using java.awt.Color class
        lblSummeryName.setFont(new Font("Segoe UI Black", 0, 20));    // using java.awt.Font class
        lblSummeryName.setForeground(new Color(255, 255, 255));              // using java.awt.Color class
        lblSummeryName.setHorizontalAlignment(SwingConstants.CENTER);
        lblSummeryName.setText("Summery");                                             // setting text to label lblSummeryName
        lblSummeryName.addMouseListener(new MouseAdapter() {          // adding mouse listener to lblSummeryName label to detect mouse actions on lblSummeryName.
            public void mouseEntered(MouseEvent event)                // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlSummery);                            // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {               // using java.awt.event.MouseEvent
                setPanelBlue(pnlSummery);                             // set panel color to blue when mouse exited from its region.
            }
        });

        lblSummeryIcon.setIcon(new ImageIcon(getClass().getResource("image/summery.png")));  // setting image to the lblSummeryIcon.
        lblSummeryIcon.addMouseListener(new MouseAdapter() {       // adding mouse listener to  lblSummeryIcon label to detect mouse actions on lblSummeryIcon.
            public void mouseClicked(MouseEvent event) {          // using java.awt.event.MouseEvent

            }
            public void mouseEntered(MouseEvent event)            // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlSummery);                        // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)             // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlSummery);                          // set panel color to blue when mouse exited from its region.
            }
        });

        // creating group layout to patients showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlSummeryLayout = new GroupLayout(pnlSummery);
        pnlSummery.setLayout(pnlSummeryLayout);

        // this is the horizontal group
        pnlSummeryLayout.setHorizontalGroup(
                pnlSummeryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addGroup(pnlSummeryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSummeryLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlSummeryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlSummeryLayout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addComponent(lblSummeryIcon))
                                                .addComponent(lblSummeryName, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        // this is the vertical group
        pnlSummeryLayout.setVerticalGroup(
                pnlSummeryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addGroup(pnlSummeryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSummeryLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblSummeryIcon)
                                        .addGap(0, 0, 0)
                                        .addComponent(lblSummeryName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlSummery);                                       // add pnlSummery panel to pnlMain panel
        pnlSummery.setBounds(310, 340, 220, 150);

        pnlPatients.setBackground(new Color(51, 51, 51));     // using java.awt.Color class
        pnlPatients.addMouseListener(new MouseAdapter() {     // adding mouse listener to  pnlPatients panel to detect mouse actions on pnlPatients.
            public void mouseClicked(MouseEvent event) {     // using java.awt.event.MouseEvent
                 showPatient();                             // when user clicked pnlPatients panel patient window will display
            }
            public void mouseEntered(MouseEvent event) {      // using java.awt.event.MouseEvent
                setPanelBlue(pnlPatients);                    // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {       // using java.awt.event.MouseEvent
                setPanelGrey( pnlPatients);                   // set panel color to grey when mouse exited from its region.
            }
        });

        lblPatientsTileName.setBackground(new Color(51, 51, 51));               // using java.awt.Color class
        lblPatientsTileName.setFont(new Font("Segoe UI Black", 0, 20));  // using java.awt.Font class
        lblPatientsTileName.setForeground(new Color(255, 255, 255));            // using java.awt.Color class
        lblPatientsTileName.setHorizontalAlignment(SwingConstants.CENTER);
        lblPatientsTileName.setText("Patients");                                          // setting text to label lblPatientsTileName
        lblPatientsTileName.addMouseListener(new MouseAdapter() {    // adding mouse listener to  lblPatientsTileName label to detect mouse actions on lblPatientsTileName.
            public void mouseClicked(MouseEvent event)              // using java.awt.event.MouseEvent
            {
                showPatient();                              // when user clicked lblPatientsTileName label patient window will display
            }
            public void mouseEntered(MouseEvent event)             // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlPatients);                         // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)             // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlPatients);                       // set panel color to grey when mouse exited from its region.
            }
        });

        lblPatientIcon.setIcon(new ImageIcon(getClass().getResource("image/patient.png")));  // setting image to the lblPatientIcon.
        lblPatientIcon.addMouseListener(new MouseAdapter()   // adding mouse listener to  lblPatientIcon label to detect mouse actions on lblPatientIcon.
        {
            public void mouseClicked(MouseEvent event)                 // using java.awt.event.MouseEvent
            {
                showPatient();                                  // when user clicked lblPatientIcon label patient window will display
            }
            public void mouseEntered(MouseEvent event)                 // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlPatients);                            // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)                 // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlPatients);                          // set panel color to grey when mouse exited from its region.
            }
        });

        // creating group layout to patients showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlPatientsLayout = new GroupLayout(pnlPatients);
        pnlPatients.setLayout(pnlPatientsLayout);

        // this is the horizontal group
        pnlPatientsLayout.setHorizontalGroup(
                pnlPatientsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addGroup(pnlPatientsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPatientsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlPatientsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlPatientsLayout.createSequentialGroup()
                                                        .addGap(50, 50, 50)
                                                        .addComponent(lblPatientIcon))
                                                .addComponent(lblPatientsTileName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        // this is the vertical group
        pnlPatientsLayout.setVerticalGroup(
                pnlPatientsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addGroup(pnlPatientsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPatientsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblPatientIcon)
                                        .addGap(0, 0, 0)
                                        .addComponent(lblPatientsTileName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlPatients);                           // add pnlConsult panel to pnlMain panel
        pnlPatients.setBounds(310, 160, 220, 150);

        pnlConsult.setBackground(new Color(51, 51, 51));          // using java.awt.Color class
        pnlConsult.addMouseListener(new MouseAdapter() {    // adding mouse listener to  pnlConsult panel to detect mouse actions on pnlConsult.
            public void mouseClicked(MouseEvent event) {   // using java.awt.event.MouseEvent
                showConsultation();                      // when user clicked pnlConsult panel consultation window will display
            }
            public void mouseEntered(MouseEvent event)     // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlConsult);                   // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)      // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlConsult);                 // set panel color to grey when mouse exited from its region.
            }
        });

        lblConsultIcon.setIcon(new ImageIcon(getClass().getResource("image/consultations.png")));  // setting image to the lblConsultIcon.
        lblConsultIcon.addMouseListener(new MouseAdapter() {   // adding mouse listener to  lblConsultIcon label to detect mouse actions on lblConsultIcon.
            public void mouseClicked(MouseEvent event) {   // using java.awt.event.MouseEvent
                showConsultation();                    // when user clicked lblConsultIcon label consultation window will display
            }
            public void mouseEntered(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelBlue(pnlConsult);                   // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)     // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlConsult);                // set panel color to grey when mouse exited from its region.
            }
        });

        lblConsultationsTileName.setBackground(new Color(51, 51, 51));                    // using java.awt.Color class
        lblConsultationsTileName.setFont(new Font("Segoe UI Black", 0, 20));      // using java.awt.Font class
        lblConsultationsTileName.setForeground(new Color(255, 255, 255));                 // using java.awt.Color class
        lblConsultationsTileName.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsultationsTileName.setText("Consultations");                                         // setting text to label lblConsultationsTileName
        lblConsultationsTileName.addMouseListener(new MouseAdapter() { // adding mouse listener to  lblConsultationsTileName label to detect mouse actions on lblConsultationsTileName.
            public void mouseClicked(MouseEvent event)    // using java.awt.event.MouseEvent
            {
                showConsultation();                    // when user clicked lblConsultationsTileName label consultation window will display
            }
            public void mouseEntered(MouseEvent event)    // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlConsult);                 // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)    // using java.awt.event.MouseEvent
            {
                setPanelGrey( pnlConsult);                // set panel color to grey when mouse exited from its region.
            }
        });

        // creating group layout to consultations showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlConsultLayout = new GroupLayout(pnlConsult);
        pnlConsult.setLayout(pnlConsultLayout);

        // this is the horizontal layout
        pnlConsultLayout.setHorizontalGroup(
                pnlConsultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addGroup(pnlConsultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlConsultLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlConsultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlConsultLayout.createSequentialGroup()
                                                        .addGap(50, 50, 50)
                                                        .addComponent(lblConsultIcon))
                                                .addComponent(lblConsultationsTileName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        // this is the vertical group
        pnlConsultLayout.setVerticalGroup(
                pnlConsultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addGroup(pnlConsultLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlConsultLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblConsultIcon)
                                        .addGap(0, 0, 0)
                                        .addComponent(lblConsultationsTileName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlConsult);                          // add pnlConsult panel to pnlMain panel
        pnlConsult.setBounds(50, 340, 220, 150);

        pnlQuit.setBackground(new Color(51, 51, 51));                   // using java.awt.Color class
        pnlQuit.addMouseListener(new MouseAdapter() {      // adding mouse listener to  pnlQuit panel to detect mouse actions on pnlQuit.
            public void mouseEntered(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelBlue(pnlQuit);                     // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {    // using java.awt.event.MouseEvent
                setPanelGrey(pnlQuit);                     // set panel color to grey when mouse exited from its region.
            }
        });

        lblQuitText.setFont(new Font("Segoe UI Black", 0, 36));        // using java.awt.Font class
        lblQuitText.setForeground(new Color(255, 255, 255));                  // using java.awt.Color class
        lblQuitText.setText("Log Off");                                                 // setting text to label lblQuitText
        lblQuitText.addMouseListener(new MouseAdapter() {   // adding mouse listener to  lblQuitText label to detect mouse actions on lblQuitText.
            public void mouseClicked(MouseEvent event) {   // using java.awt.event.MouseEvent
                quit();                                    // when user click lblQuitText label, quit() will be called and window will be terminated
            }
            public void mouseEntered(MouseEvent event)    // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlQuit);                    // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event)     // using java.awt.event.MouseEvent
            {
                setPanelGrey(pnlQuit);                     // set panel color to grey when mouse exited from its region.
            }
        });

        lblLogOutIcon.setIcon(new ImageIcon(getClass().getResource("image/log_out.png")));  // setting image to the lblLogOutIcon.
        lblLogOutIcon.addMouseListener(new MouseAdapter() {   // adding mouse listener to  lblQuitText label to detect mouse actions on lblQuitText.
            public void mouseClicked(MouseEvent event) {    // using java.awt.event.MouseEvent
                quit();                                    // when user click lblLogOutIcon label, quit() will be called and window will be terminated
            }
            public void mouseEntered(MouseEvent event)    // using java.awt.event.MouseEvent
            {
                setPanelBlue(pnlQuit);                    // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelGrey(pnlQuit);                    // set panel color to grey when mouse exited from its region.
            }
        });

        // creating group layout to Exit showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlquitLayout = new GroupLayout(pnlQuit);
        pnlQuit.setLayout(pnlquitLayout);

        // this is the horizontal group
        pnlquitLayout.setHorizontalGroup(
                pnlquitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 280, Short.MAX_VALUE)
                        .addGroup(pnlquitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlquitLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblLogOutIcon, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblQuitText, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        // this is the vertical group
        pnlquitLayout.setVerticalGroup(
                pnlquitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
                        .addGroup(pnlquitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlquitLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlquitLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(lblLogOutIcon)
                                                .addComponent(lblQuitText, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlQuit);                                           // add pnlQuit panel to pnlMain panel
        pnlQuit.setBounds(590, 390, 280, 100);

        pnlSettings.setBackground(new Color(33, 0, 167));             // using java.awt.Color class
        pnlSettings.addMouseListener(new MouseAdapter() {  // adding mouse listener to  pnlSettings panel to detect mouse actions on pnlSettings.
            public void mouseEntered(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelGrey(pnlSettings);                 // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelBlue(pnlSettings);                 // set panel color to blue when mouse exited from its region.
            }
        });

        lblSettingsText.setBackground(new Color(51, 51, 51));                             // using java.awt.Color class
        lblSettingsText.setFont(new Font("Segoe UI Black", 0, 36));               // using java.awt.Font class
        lblSettingsText.setForeground(new Color(255, 255, 255));                          // using java.awt.Color class
        lblSettingsText.setText("Settings");                                                      // setting text to label lblSettingsText
        lblSettingsText.addMouseListener(new MouseAdapter() {  // adding mouse listener to  lblSettingsText label to detect mouse actions on lblSettingsText.
            public void mouseEntered(MouseEvent event) {    // using java.awt.event.MouseEvent
                setPanelGrey(pnlSettings);                 // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {     // using java.awt.event.MouseEvent
                setPanelBlue(pnlSettings);                 // set panel color to blue when mouse exited from its region.
            }
        });

        lblSettingsIcon.setIcon(new ImageIcon(getClass().getResource("image/quit.png")));  // setting image to the lblSettingsIcon.
        lblSettingsIcon.addMouseListener(new MouseAdapter() {  // adding mouse listener to  lblSettingsIcon label to detect mouse actions on lblSettingsIcon.
            public void mouseEntered(MouseEvent event) {     // using java.awt.event.MouseEvent
                setPanelGrey(pnlSettings);                   // set panel color to grey when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {    // using java.awt.event.MouseEvent
                setPanelBlue(pnlSettings);                  // set panel color to blue when mouse exited from its region.
            }
        });

        // creating group layout to settings showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlSettingsLayout = new GroupLayout(pnlSettings);
        pnlSettings.setLayout(pnlSettingsLayout);

        // this is the horizontal group
        pnlSettingsLayout.setHorizontalGroup(
                pnlSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 280, Short.MAX_VALUE)
                        .addGroup(pnlSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSettingsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblSettingsIcon)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblSettingsText,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        // this is the vertical group
        pnlSettingsLayout.setVerticalGroup(
                pnlSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
                        .addGroup(pnlSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSettingsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlSettingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlSettingsLayout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addComponent(lblSettingsIcon))
                                                .addComponent(lblSettingsText, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlSettings);                                           // add pnlSettings panel to pnlMain panel
        pnlSettings.setBounds(590, 275, 280, 100);

        pnlDateTime.setBackground(new Color(51, 51, 51));         // using java.awt Color class

        pnlDateTime.addMouseListener(new MouseAdapter() {  // adding mouse listener to  pnlDateTime panel to detect mouse actions on pnlDateTime.
            public void mouseEntered(MouseEvent event) {  // using java.awt.event.MouseEvent
                setPanelBlue(pnlDateTime);                // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelGrey(pnlDateTime);                // set panel color to grey when mouse exited from its region.
            }
        });

        lblTime.setBackground(new Color(51, 51, 51));                   // using java.awt Color class
        lblTime.setFont(new Font("Segoe UI Black", 0, 40));      // using java.awt Font class
        lblTime.setForeground(new Color(255, 255, 255));                // using java.awt Color class
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblTime.setText("10:39:43 AM");                                         // setting text to label lblTime
        lblTime.addMouseListener(new MouseAdapter() {   // adding mouse listener to  lblTime label to detect mouse actions on lblTime.
            public void mouseEntered(MouseEvent event) {    // using java.awt.event.MouseEvent
                setPanelBlue(pnlDateTime);                    // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {     // using java.awt.event.MouseEvent
                setPanelGrey(pnlDateTime);                 // set panel color to grey when mouse exited from its region.
            }
        });

        lblDate.setBackground(new Color(51, 51, 51));                        // using java.awt Color class
        lblDate.setFont(new Font("Segoe UI Black", 0, 20));           // using java.awt Font class
        lblDate.setForeground(new Color(255, 255, 255));                     // using java.awt Color class
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setText("2021:December:12");                                           // setting text to label lblDate
        lblDate.addMouseListener(new MouseAdapter() {   // adding mouse listener to  lblDate label to detect mouse actions on lblDate.
            public void mouseEntered(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelBlue(pnlDateTime);                  // set panel color to blue when mouse entered to its region.
            }
            public void mouseExited(MouseEvent event) {   // using java.awt.event.MouseEvent
                setPanelGrey(pnlDateTime);                // set panel color to grey when mouse exited from its region.
            }
        });

        // creating group layout to date time showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
        GroupLayout pnlDateTimeLayout = new GroupLayout(pnlDateTime);
        pnlDateTime.setLayout(pnlDateTimeLayout);

        // this is the horizontal group
        pnlDateTimeLayout.setHorizontalGroup(
                pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 280, Short.MAX_VALUE)
                        .addGroup(pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDateTimeLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlDateTimeLayout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 260,GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        // this is the vertical group
        pnlDateTimeLayout.setVerticalGroup(
                pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
                        .addGroup(pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDateTimeLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pnlDateTimeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 60,GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlDateTimeLayout.createSequentialGroup()
                                                        .addGap(40, 40, 40)
                                                        .addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 60,GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlDateTime);                                               // add pnlDateTime panel to pnlMain panel
        pnlDateTime.setBounds(590, 160, 280, 100);

        lblTitle.setBackground(new Color(33, 0, 167));                // using java.awt package to get colors.
        lblTitle.setFont(new Font("Segoe UI Black", 0, 40));   //using java.awt package to get fonts.
        lblTitle.setForeground(new Color(255, 255, 255));             // using java.awt package to get colors.
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setText("WESTMINSTER SKIN CONSULTATION");                      // setting text to label lblTitle
        lblTitle.setOpaque(true);
        lblTitle.addMouseMotionListener(new MouseMotionAdapter() {     // adding mouse motion listener to  lblTitle label to detect mouse motion actions on lblTitle.
            public void mouseDragged(MouseEvent event) {   // using java.awt.event.MouseEvent
                int x = event.getXOnScreen();              // getting X coordinate of the screen when mouse drag happened.
                int y = event.getYOnScreen();              // getting Y coordinate of the screen when mouse drag happened.

                setLocation(x-posX-140, y-posY-40);  // setting the window location.
            }
        });

        lblTitle.addMouseListener(new MouseAdapter() {    // adding mouse listener to  lblTitle label to detect mouse actions on lblTitle.
            public void mousePressed(MouseEvent event) {    // using java.awt.event.MouseEvent
                posX = event.getX();                        // get the x coordinate where mouse pressed to an int variable posX
                posY = event.getY();                        // get the y coordinate where mouse pressed to an int variable posY
            }
        });

        pnlMain.add(lblTitle);                                     // add lblTitle label to pnlMain panel
        lblTitle.setBounds(140, 40, 776, 80);  // set coordinates, width and height of lblTitle.

        lblBackgroundImage.setIcon(new ImageIcon(getClass().getResource("image/backdrop.png")));  // setting image to the lblBackgroundImage.
        pnlMain.add(lblBackgroundImage);                                                                 // adding the label lblBackgroundImage which containing background image to the pnlMain- main panel of the window.
        lblBackgroundImage.setBounds(0, 0, 916, 524);

        // creating group layout to date time showing tile
        // setting alignments to leading----> left but can contain leading whitespaces
        // adding gaps between elements
       GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // this is the horizontal group
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain,GroupLayout.PREFERRED_SIZE, 916, GroupLayout.PREFERRED_SIZE)
        );

        // this is the vertical group
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE)
        );

        pack();                                            // revert all elements to the JFrame.
        setLocationRelativeTo(null);                       // set startup location of JFrame to center.
    }

    /**
     * this method will set the background color to grey of a given JPanel.
     * @param panel the panel want to set background grey.
     */
    public void setPanelGrey(JPanel panel)
    {
        panel.setBackground(new Color(51,51,51));              // grey. using java.awt package
    }

    /**
     * this method will set the background color to blue of a given JPanel.
     * @param panel the panel want to set background blue.
     */
    public void setPanelBlue(JPanel panel)
    {
        panel.setBackground(new Color(33,0,167));             // blue. using java.awt package
    }

    /**
     * method to show patient window.
     */
    public void showPatient()
    {
        GUIPatient patient = new GUIPatient();
        patient.setVisible(true);
    }

    public void showConsultation()
    {
        GUIConsultation consult = new GUIConsultation();
        consult.setVisible(true);
    }

    /**
     * method to quit the program.
     */
    public void quit()
    {
        this.dispose();       // dispose will not terminate the program ( console program will ren normally )but it will terminate the JFrame process.
    }

    public void showDoctors()
    {
        GUIDoctors doctor = new GUIDoctors();
        doctor.setVisible(true);
    }
}