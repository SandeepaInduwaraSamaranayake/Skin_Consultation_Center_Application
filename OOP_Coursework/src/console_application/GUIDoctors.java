package console_application;

import javax.swing.*;                          // importing swing whole package.
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


public class GUIDoctors extends JFrame         // trying to create a JFrame, so extending from JFrame class in swing package.
    {
        // creating necessary gui element objects.
        private JLabel lblAddDoctors;
        private JLabel lblBannerHolder;
        private JLabel lblClose;
        private JLabel lblDeleteDoctor;
        private JLabel lblDoctorIcon;
        private JLabel lblDoctorText;
        private JLabel lblOrderAlphabetically;
        private JLabel lblSearchText;

        private JLabel lblWestminsterBanner;
        private JPanel pnlDoctor;
        private static JTable tblDoctors;
        private JScrollPane tbleDoctor;
        private JTextField txtSearch;




        int posX = 0, posY = 0;               // assigning position x and position y default values.

        public GUIDoctors()                   // constructor for GUIDoctors class.
        {
            //initialising created objects when the form is loading.
            pnlDoctor = new JPanel();
            lblDoctorIcon = new JLabel();
            lblWestminsterBanner = new JLabel();
            lblDoctorText = new JLabel();
            lblBannerHolder = new JLabel();
            tbleDoctor = new JScrollPane();
            tblDoctors = new JTable();
            lblDeleteDoctor = new JLabel();
            lblAddDoctors = new JLabel();
            lblClose = new JLabel();
            lblOrderAlphabetically = new JLabel();
            lblSearchText = new JLabel();
            txtSearch = new JTextField();

            // database connector to connect to the database.
            DatabaseConnector databaseConnector = new DatabaseConnector(false);

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);               // set default close operation to dispose. this will not terminate the console
                                                                                      // program even though the window is closed using [X].
            setName("frmDoctors");                                                    // set name of the frame.
            setUndecorated(true);                                                     // removing system default titlebar.
            setPreferredSize(new Dimension(1290, 685));                 // set size of the window.
            setResizable(false);                                                      // restrict resizing.
            getContentPane().setLayout(null);                                         // set layout to null.

            pnlDoctor.setBackground(new Color(255, 255, 255));              // setting background color of the pnlDoctor panel. using java.awt.Color class
            pnlDoctor.setPreferredSize(new Dimension(941, 600));        // setting size of pnlDoctor.
            pnlDoctor.setLayout(null);                                                // setting null layout to pnlDoctor.

            lblDoctorIcon.setFont(new Font("Segoe UI Black", 0, 48));              // setting font of the label. using java.awt.Font class
            lblDoctorIcon.setForeground(new Color(255, 255, 255));                        // setting foreground color. using java.awt.Color class
            lblDoctorIcon.setIcon(new ImageIcon(getClass().getResource("image/doctor.png"))); // setting image to the lblDoctorIcon.
            pnlDoctor.add(lblDoctorIcon);                                                           // adding lblDoctorIcon label to pnlDoctor panel.
            lblDoctorIcon.setBounds(270, 30, 80, 80);                           // set the coordinates, width and height of the label.

            lblWestminsterBanner.setFont(new Font("Segoe UI Black", 0, 36));       // setting font of the label. using java.awt.Font class
            lblWestminsterBanner.setForeground(new Color(255, 255, 255));                 // setting foreground color. using java.awt.Color class
            lblWestminsterBanner.setText("WESTMINSTER SKIN CONSULTATION");                          // setting text of the lblWestminsterBanner label.
            pnlDoctor.add(lblWestminsterBanner);                                                    // adding lblWestminsterBanner label to pnlDoctor panel.
            lblWestminsterBanner.setBounds(600, 40, 670, 60);                  // set the coordinates, width and height of the label.

            lblDoctorText.setFont(new Font("Segoe UI Black", 0, 45));             // setting font of the label. using java.awt.Font class
            lblDoctorText.setForeground(new Color(255, 255, 255));                        // setting foreground color. using java.awt.Color class
            lblDoctorText.setText("Doctors |  ");                                                   // setting text of the lblDoctorText label.
            pnlDoctor.add(lblDoctorText);                                                            // adding lblDoctorText label to pnlDoctor panel.
            lblDoctorText.setBounds(390, 40, 250, 50);                           // set the coordinates, width and height of the label.

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
            pnlDoctor.add(lblBannerHolder);
            lblBannerHolder.setBounds(250, 20, 1040, 100);

            tbleDoctor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            tbleDoctor.setOpaque(false);

            tblDoctors.setAutoCreateRowSorter(true);
            tblDoctors.setBackground(new Color(255, 255, 255));
            tblDoctors.setFont(new Font("Segoe UI Black", 2, 12));
            tblDoctors.setForeground(new Color(51, 51, 51));

            tblDoctors.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblDoctors.setGridColor(new Color(255, 255, 255));
            tblDoctors.setRowHeight(27);
            tblDoctors.setSelectionBackground(new Color(102, 102, 255));
            tblDoctors.setShowGrid(false);
            tbleDoctor.setViewportView(tblDoctors);

            loadDataToTable(); // load data to JTable.

            pnlDoctor.add(tbleDoctor);
            tbleDoctor.setBounds(11, 170, 1270, 440);

            lblDeleteDoctor.setBackground(new Color(33, 0, 167));
            lblDeleteDoctor.setFont(new Font("Segoe UI", 1, 18));
            lblDeleteDoctor.setForeground(new Color(255, 255, 255));
            lblDeleteDoctor.setHorizontalAlignment(SwingConstants.CENTER);
            lblDeleteDoctor.setText("Delete Doctor");
            lblDeleteDoctor.setToolTipText("Delete a doctor");
            lblDeleteDoctor.setOpaque(true);


            lblDeleteDoctor.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent event)
                {
                    // get selected row
                    int selectedRow = tblDoctors.getSelectedRow();

                    // if selected row is -1, row is not selected at this moment.
                    if(selectedRow != -1)
                    {
                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the doctor?", "Confirm Delete Doctor", JOptionPane.YES_NO_OPTION);

                        if (confirmDelete == JOptionPane.YES_OPTION)
                        {
                            // get medical licence number belongs to selected row.
                            int selectedRowMedLicenceNo = (int) tblDoctors.getValueAt(selectedRow, 0);

                            // delete doctor selected by user to delete
                            for (Doctor doc : WestminsterSkinConsultationManager.getDocArrList())
                            {
                                if (doc.getMedicalLicenceNumber() == selectedRowMedLicenceNo)
                                {
                                    Doctor doctor = doc;
                                    try {
                                        // if user try to first delete a doctor from the system and add a doctor with the same medical id, it will not going to be saved to database
                                        // because that id is already existing in the database. So when user delete a doctor record from system we have to delete it from both
                                        // arraylist and database.

                                        // deleting doctor from arraylist
                                        WestminsterSkinConsultationManager.getDocArrList().remove(doc);

                                        // deleting doctor from database
                                        Statement statement = databaseConnector.getConnection().createStatement();
                                        statement.executeUpdate("DELETE FROM Doctors WHERE Medical_licence_number = " + selectedRowMedLicenceNo);

                                        // refresh/update JTable to show the remaining records.
                                        loadDataToTable();

                                        // print remaining number of doctors and successfully deleted message.
                                        int remainingTotalDoctors = WestminsterSkinConsultationManager.getDocArrList().size();
                                        JOptionPane.showMessageDialog(null, "THE DOCTOR %s %s SPECIALIST IN %s WITH LICENCE ID : %d HAS BEEN REMOVED FROM SYSTEM.\nTOTAL NUMBER OF REMAINING DOCTORS : %d"
                                                        .formatted(doctor.getName(), doctor.getSurname(), doctor.getSpecialisation(), doctor.getMedicalLicenceNumber(), remainingTotalDoctors),"Deleted", JOptionPane.INFORMATION_MESSAGE);

                                    }
                                    catch (Exception exc)
                                    {
                                        // if an error occurred while deleting the row, show an error message.
                                        JOptionPane.showMessageDialog(null, "Error occurred while deleting the doctor", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }

                public void mouseEntered(MouseEvent event)
                {
                    setLabelGrey(lblDeleteDoctor);
                }
                public void mouseExited(MouseEvent event)
                {
                    setLabelBlue(lblDeleteDoctor);
                }
            });
            pnlDoctor.add(lblDeleteDoctor);
            lblDeleteDoctor.setBounds(850, 630, 190, 40);

            lblAddDoctors.setBackground(new Color(33, 0, 167));
            lblAddDoctors.setFont(new Font("Segoe UI", 1, 18));
            lblAddDoctors.setForeground(new Color(255, 255, 255));
            lblAddDoctors.setHorizontalAlignment(SwingConstants.CENTER);
            lblAddDoctors.setText("Add Doctor");
            lblAddDoctors.setToolTipText("Add a doctor");
            lblAddDoctors.setOpaque(true);
            lblAddDoctors.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    showAddDoctors();
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblAddDoctors);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue( lblAddDoctors);
                }
            });
            pnlDoctor.add(lblAddDoctors);
            lblAddDoctors.setBounds(630, 630, 190, 40);

            lblClose.setBackground(new Color(33, 0, 167));
            lblClose.setFont(new Font("Segoe UI", 1, 18));
            lblClose.setForeground(new Color(255, 255, 255));
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
            pnlDoctor.add(lblClose);
            lblClose.setBounds(1070, 630, 190, 40);

            lblOrderAlphabetically.setBackground(new Color(33, 0, 167));
            lblOrderAlphabetically.setFont(new Font("Segoe UI", 1, 18));
            lblOrderAlphabetically.setForeground(new Color(255, 255, 255));
            lblOrderAlphabetically.setHorizontalAlignment(SwingConstants.CENTER);
            lblOrderAlphabetically.setText("Order Alphabetically");
            lblOrderAlphabetically.setToolTipText("Order Alphabetically");
            lblOrderAlphabetically.setOpaque(true);
            lblOrderAlphabetically.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event)
                {
                    // Create a TableRowSorter for the table
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblDoctors.getModel());

                    // Set the sorter as the table's row sorter
                    tblDoctors.setRowSorter(sorter);

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
            pnlDoctor.add(lblOrderAlphabetically);
            lblOrderAlphabetically.setBounds(410, 630, 190, 40);

            lblSearchText.setFont(new Font("Segoe UI", 0, 13));
            lblSearchText.setForeground(new Color(60, 63, 65));
            lblSearchText.setText("Search by Surname -");
            pnlDoctor.add(lblSearchText);
            lblSearchText.setBounds(110, 133, 140, 25);

            txtSearch.setFont(new Font("Segoe UI", 1, 12));
            pnlDoctor.add(txtSearch);
            txtSearch.setBounds(250, 130, 1030, 30);

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

            getContentPane().add(pnlDoctor);
            pnlDoctor.setBounds(0, 0, 1290, 685);

            pack();
            setLocationRelativeTo(null);  // this will show the interface middle of the screen.
        }


        /**
         * this method will set the background of the passed label to grey
         * @param label label that want to set background to grey
         */
        public void setLabelGrey(JLabel label)
        {
            label.setBackground(new Color(51,51,51));  // grey
        }

        /**
         * this method will set the background of the passed label to blue
         * @param label label that want to set background to blue
         */
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
            model.setColumnIdentifiers(new String[] {"Med. Licence No", "First Name", "Surname", "Date of Birth", "Mobile Number", "Address", "Specialisation", "Category"});

                // Add the rows to the table model
                for (Doctor doc : WestminsterSkinConsultationManager.getDocArrList())
                {
                    model.addRow(new Object[]{doc.getMedicalLicenceNumber(), doc.getName(), doc.getSurname(), doc.getDateOfBirth(), doc.getMobileNumber(), doc.getAddress(), doc.getSpecialisation(), doc.getCategory()});
                }


                // make table not editable
                tblDoctors.setDefaultEditor(Object.class, null);

                // Set the table model as the model for the JTable
                tblDoctors.setModel(model);
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblDoctors.getModel());
            tblDoctors.setRowSorter(sorter);

            // Create a filter to be applied when the user types in the text field
            RowFilter<TableModel, Object> filter = new RowFilter<TableModel, Object>()
            {
                @Override
                public boolean include(RowFilter.Entry<? extends TableModel, ? extends Object> entry)
                {
                    // Get the value of the column in the current row being filtered. index is specified to filter using surname.(index 2 column)
                    String value = entry.getStringValue(2);

                    // Return true if the value matches the user input, false otherwise
                    return value.contains(txtSearch.getText().toUpperCase());
                }
            };

            // Set the filter on the RowSorter
            sorter.setRowFilter(filter);
        }

       public void showAddDoctors()
        {
            // max no of doctors can handle by centre is 10 
            if(WestminsterSkinConsultationManager.getDocArrList().size() < 10)
            {
            GUIAddDoctors addDoctors = new GUIAddDoctors();
            addDoctors.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "THE CONSULTATION CENTRE CAN HANDLE MAXIMUM OF TEN DOCTORS ONLY. TRY AGAIN AFTER DELETING A DOCTOR.", "Can Accept Maximum 10 Doctors", JOptionPane.ERROR_MESSAGE);
            }
        }

    }