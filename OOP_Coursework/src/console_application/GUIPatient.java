package console_application;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Statement;
import java.util.Collections;

/**
 * patient JFrame
 */
public class GUIPatient extends JFrame
{
    private JLabel lblAddPatient;
    private JLabel lblBannerHolder;
    private JLabel lblClose;
    private JLabel lblDeletePatient;
    private JLabel lblOrderAlphabetically;
    private JLabel lblPatientBanner;
    private JLabel lblPatientIcon;
    private JLabel lblSearchText;
    private JLabel lblWestminsterBanner;
    private JPanel pnlMain;
    private static JTable tblPatient;
    private JScrollPane tblePatients;
    private JTextField txtSearch;
    int posX = 0, posY = 0;

        public GUIPatient()
        {

            pnlMain = new JPanel();
            lblPatientIcon = new JLabel();
            lblWestminsterBanner = new JLabel();
            lblPatientBanner = new JLabel();
            lblBannerHolder = new JLabel();
            tblePatients = new JScrollPane();
            tblPatient = new JTable();
            lblDeletePatient = new JLabel();
            lblAddPatient = new JLabel();
            lblClose = new JLabel();
            txtSearch = new JTextField();
            lblSearchText = new JLabel();
            lblOrderAlphabetically = new JLabel();

            // database connector to connect to the database.
            DatabaseConnector databaseConnector = new DatabaseConnector(false);

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setName("frmPatients");
            setUndecorated(true);
            setResizable(false);

            pnlMain.setBackground(new Color(255, 255, 255));
            pnlMain.setPreferredSize(new Dimension(941, 600));
            pnlMain.setLayout(null);

            lblPatientIcon.setFont(new Font("Segoe UI Black", 0, 48));
            lblPatientIcon.setForeground(new Color(255, 255, 255));
            lblPatientIcon.setIcon(new ImageIcon(getClass().getResource("image/patient1.png")));
            pnlMain.add(lblPatientIcon);
            lblPatientIcon.setBounds(280, 30, 80, 80);

            lblWestminsterBanner.setFont(new Font("Segoe UI Black", 0, 36));
            lblWestminsterBanner.setForeground(new Color(255, 255, 255));
            lblWestminsterBanner.setText("WESTMINSTER SKIN CONSULTATION");
            pnlMain.add(lblWestminsterBanner);
            lblWestminsterBanner.setBounds(600, 40, 680, 60);

            lblPatientBanner.setFont(new Font("Segoe UI Black", 0, 45));
            lblPatientBanner.setForeground(new Color(255, 255, 255));
            lblPatientBanner.setText("Patients |");
            pnlMain.add(lblPatientBanner);
            lblPatientBanner.setBounds(370, 40, 220, 50);

            lblBannerHolder.setBackground(new Color(33, 0, 167));
            lblBannerHolder.setFont(new Font("Segoe UI Black", 0, 40));
            lblBannerHolder.setForeground(new Color(255, 255, 255));
            lblBannerHolder.setHorizontalAlignment(SwingConstants.CENTER);
            lblBannerHolder.setOpaque(true);
            lblBannerHolder.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged( MouseEvent event) {
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
            lblBannerHolder.setBounds(250, 20, 1040, 100);

            tblePatients.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            tblePatients.setOpaque(false);

            tblPatient.setAutoCreateRowSorter(true);
            tblPatient.setBackground(new java.awt.Color(255, 255, 255));
            tblPatient.setFont(new java.awt.Font("Segoe UI Black", 0, 12));
            tblPatient.setForeground(new java.awt.Color(51, 51, 51));

            tblPatient.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblPatient.setGridColor(new java.awt.Color(255, 255, 255));
            tblPatient.setRowHeight(27);
            tblPatient.setSelectionBackground(new java.awt.Color(102, 102, 255));
            tblPatient.setShowHorizontalLines(true);
            tblPatient.setShowVerticalLines(true);
            tblePatients.setViewportView(tblPatient);

            loadDataToTable(); // load data to JTable.

            pnlMain.add(tblePatients);
            tblePatients.setBounds(11, 170, 1270, 440);

            lblDeletePatient.setBackground(new java.awt.Color(33, 0, 167));
            lblDeletePatient.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblDeletePatient.setForeground(new java.awt.Color(255, 255, 255));
            lblDeletePatient.setHorizontalAlignment(SwingConstants.CENTER);
            lblDeletePatient.setText("Delete Patient");
            lblDeletePatient.setToolTipText("Delete a patient");
            lblDeletePatient.setOpaque(true);
            lblDeletePatient.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent event)
                {
                    // get selected row
                    int selectedRow = tblPatient.getSelectedRow();

                    // if selected row is -1, row is not selected at this moment.
                    if(selectedRow != -1)
                    {
                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the patient?", "Confirm Delete patient", JOptionPane.YES_NO_OPTION);

                        if (confirmDelete == JOptionPane.YES_OPTION)
                        {
                            // get patient id belongs to selected row.
                            int selectedRowPatientId = (int) tblPatient.getValueAt(selectedRow, 0);

                            // delete patient selected by user to delete
                            for (Patient patient : WestminsterSkinConsultationManager.getPatientArrayList())
                            {
                                if (patient.getPatientId() == selectedRowPatientId)
                                {
                                    Patient memopatient = patient;
                                    try {
                                        // if user try to first delete a patient from the system and add a patient with the same medical id, it will not going to be saved to database
                                        // because that id is already existing in the database. So when user delete a patient record from system we have to delete it from both
                                        // arraylist and database.

                                        // deleting doctor from arraylist
                                        WestminsterSkinConsultationManager.getPatientArrayList().remove(patient);

                                        // deleting doctor from database
                                        Statement statement = databaseConnector.getConnection().createStatement();
                                        statement.executeUpdate("DELETE FROM Patient WHERE Patient_id = " + selectedRowPatientId);

                                        // refresh/update JTable to show the remaining records.
                                        loadDataToTable();

                                        // print remaining number of doctors and successfully deleted message.
                                        int remainingTotalPatients = WestminsterSkinConsultationManager.getPatientArrayList().size();
                                        JOptionPane.showMessageDialog(null, "THE PATIENT %s %s WITH PATIENT ID %d HAS BEEN DELETED SUCCESSFULLY FROM THE SYSTEM.\nTOTAL NUMBER OF REMAINING PATIENTS :%d"
                                                .formatted(memopatient.getName(), memopatient.getSurname(), memopatient.getPatientId(), remainingTotalPatients),"Deleted", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    catch (Exception exc)
                                    {
                                        // if an error occurred while deleting the row, show an error message.
                                        JOptionPane.showMessageDialog(null, "Error occurred while deleting the patient", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblDeletePatient);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue(lblDeletePatient);
                }
            });
            pnlMain.add(lblDeletePatient);
            lblDeletePatient.setBounds(860, 630, 190, 40);

            lblAddPatient.setBackground(new java.awt.Color(33, 0, 167));
            lblAddPatient.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblAddPatient.setForeground(new java.awt.Color(255, 255, 255));
            lblAddPatient.setHorizontalAlignment(SwingConstants.CENTER);
            lblAddPatient.setText("Add Patient");
            lblAddPatient.setToolTipText("Add a patient");
            lblAddPatient.setOpaque(true);
            lblAddPatient.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    showAddPatient();
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblAddPatient);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue(lblAddPatient);
                }
            });
            pnlMain.add(lblAddPatient);
            lblAddPatient.setBounds(650, 630, 190, 40);

            lblClose.setBackground(new java.awt.Color(33, 0, 167));
            lblClose.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblClose.setForeground(new java.awt.Color(255, 255, 255));
            lblClose.setHorizontalAlignment(SwingConstants.CENTER);
            lblClose.setText("Close");
            lblClose.setToolTipText("Close the window");
            lblClose.setOpaque(true);
            lblClose.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    dispose();
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblClose);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue(lblClose);
                }
            });
            pnlMain.add(lblClose);
            lblClose.setBounds(1070, 630, 190, 40);

            txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 12));
            txtSearch.getDocument().addDocumentListener(new DocumentListener()  // adding a document listener to the text field txtSearch to dynamically filter data in JTable.
            {
                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    filterTableData();        // call to filter if a character or sequence of characters are inserted. (simply text in the textField is changed)
                }
                @Override
                public void removeUpdate(DocumentEvent e)
                {
                    filterTableData();       // call to filter if a character or sequence of characters are removed. (simply text in the textField is changed)
                }

                // Any property change of txtSearch will trigger this method.
                public void changedUpdate(DocumentEvent e)
                {
                    filterTableData();       // call to filter if a property is updated. (simply text in the properties are changed)
                }
            });
            pnlMain.add(txtSearch);
            txtSearch.setBounds(250, 130, 1030, 30);

            lblSearchText.setFont(new java.awt.Font("Segoe UI", 0, 14));
            lblSearchText.setForeground(new java.awt.Color(60, 63, 65));
            lblSearchText.setText("Search by  Surname -");
            pnlMain.add(lblSearchText);
            lblSearchText.setBounds(110, 132, 140, 25);

            lblOrderAlphabetically.setBackground(new java.awt.Color(33, 0, 167));
            lblOrderAlphabetically.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblOrderAlphabetically.setForeground(new java.awt.Color(255, 255, 255));
            lblOrderAlphabetically.setHorizontalAlignment(SwingConstants.CENTER);
            lblOrderAlphabetically.setText("Order Alphabetically");
            lblOrderAlphabetically.setToolTipText("Order Alphabetically");
            lblOrderAlphabetically.setOpaque(true);
            lblOrderAlphabetically.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event)
                {
                    // Create a TableRowSorter for the table
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblPatient.getModel());

                    // Set the sorter as the table's row sorter
                    tblPatient.setRowSorter(sorter);

                    // Sort the table by the third column in ascending order
                    sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(2, SortOrder.ASCENDING)));
                    sorter.sort();
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblOrderAlphabetically);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue( lblOrderAlphabetically);
                }
            });
            pnlMain.add(lblOrderAlphabetically);
            lblOrderAlphabetically.setBounds(440, 630, 190, 40);

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 1290, GroupLayout.PREFERRED_SIZE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
            );

            pack();
            setLocationRelativeTo(null);
        }


        // setLocationRelativeTo(null); will show the interface middle of the screen

        public void setLabelGrey(JLabel label)
        {
            label.setBackground(new Color(51,51,51));  // grey
        }

        public void setLabelBlue(JLabel label)
        {
            label.setBackground(new Color(33,0,167)); // blue
        }

    /**
     * this method will load the data to JTable.
     */
    public static void loadDataToTable()
    {
        // Create a new table model
        DefaultTableModel model = new DefaultTableModel();

        // Set the column names for the table model
        model.setColumnIdentifiers(new String[] {"Patient ID", "First Name", "Surname", "Date of Birth", "Mobile Number", "Address"});

        // Add the rows to the table model
        for (Patient patient : WestminsterSkinConsultationManager.getPatientArrayList()) {
            model.addRow(new Object[] {patient.getPatientId(), patient.getName(), patient.getSurname(), patient.getDateOfBirth(), patient.getMobileNumber(), patient.getAddress()});
        }

        // make table not editable
        tblPatient.setDefaultEditor(Object.class, null);

        // Set the table model as the model for the JTable
        tblPatient.setModel(model);
    }


    /**
     * this method will filter data in the JTable according to the user's input.
     */
    public void filterTableData()
    {
        // To filter data from a JTable based on user input in a text field, using the RowFilter class
        // provided in the javax.swing.RowFilter package.
        // This will cause the table to be filtered in real-time as the user types in the text field.
        // The columnIndex variable in the code above specifies the index of the column that the filter should be applied to.
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblPatient.getModel());
        tblPatient.setRowSorter(sorter);

        // Create a filter to be applied when the user types in the text field
        RowFilter<TableModel, Object> filter = new RowFilter<TableModel, Object>()
        {
            @Override
            public boolean include(RowFilter.Entry<? extends TableModel, ? extends Object> entry)
            {
                // Get the value of the column in the current row being filtered. index is specified to filter using surname(index 2 column).
                String value = entry.getStringValue(2);

                // Return true if the value matches the user input, false otherwise
                return value.contains(txtSearch.getText().toUpperCase());
            }
        };

        // Set the filter on the RowSorter
        sorter.setRowFilter(filter);
    }


        public void showAddPatient()
        {
            GUIAddPatients addPatient = new GUIAddPatients();
            addPatient.setVisible(true);
        }
}


