package console_application;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Statement;

public class GUIConsultation extends JFrame
{
        private JLabel lblAddConsultation;
        private JLabel lblBannerHolder;
        private JLabel lblClose;
        private JLabel lblDeleteConsultation;
        private JLabel lblPatientBanner;
        private JLabel lblPatientIcon;
        private JLabel lblSearchText;
        private JLabel lblWestminsterBanner;
        private JPanel pnlMain;
        private static JTable tblConsult;
        private JScrollPane tbleConsult;
        private JTextField txtSearch;

        int posX = 0, posY = 0;
        public GUIConsultation()
        {
            pnlMain = new JPanel();
            lblPatientIcon = new JLabel();
            lblWestminsterBanner = new JLabel();
            lblPatientBanner = new JLabel();
            lblBannerHolder = new JLabel();
            tbleConsult = new JScrollPane();
            tblConsult = new JTable();
            lblDeleteConsultation = new JLabel();
            lblAddConsultation = new JLabel();
            lblClose = new JLabel();
            txtSearch = new JTextField();
            lblSearchText = new JLabel();

            // database connector to connect to the database.
            DatabaseConnector databaseConnector = new DatabaseConnector(false);

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setName("frmConsultation");
            setUndecorated(true);
            setResizable(false);

            pnlMain.setBackground(new java.awt.Color(255, 255, 255));
            pnlMain.setPreferredSize(new java.awt.Dimension(941, 600));
            pnlMain.setLayout(null);

            lblPatientIcon.setFont(new java.awt.Font("Segoe UI Black", 0, 48));
            lblPatientIcon.setForeground(new java.awt.Color(255, 255, 255));
            lblPatientIcon.setIcon(new ImageIcon(getClass().getResource("image/consult.png")));
            pnlMain.add(lblPatientIcon);
            lblPatientIcon.setBounds(160, 30, 80, 80);

            lblWestminsterBanner.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
            lblWestminsterBanner.setForeground(new java.awt.Color(255, 255, 255));
            lblWestminsterBanner.setText("WESTMINSTER SKIN CONSULTATION");
            pnlMain.add(lblWestminsterBanner);
            lblWestminsterBanner.setBounds(600, 40, 680, 60);

            lblPatientBanner.setFont(new java.awt.Font("Segoe UI Black", 0, 45));
            lblPatientBanner.setForeground(new java.awt.Color(255, 255, 255));
            lblPatientBanner.setText("Consultations |");
            pnlMain.add(lblPatientBanner);
            lblPatientBanner.setBounds(250, 40, 360, 50);

            lblBannerHolder.setBackground(new java.awt.Color(33, 0, 167));
            lblBannerHolder.setFont(new java.awt.Font("Segoe UI Black", 0, 40));
            lblBannerHolder.setForeground(new java.awt.Color(255, 255, 255));
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
            lblBannerHolder.setBounds(150, 20, 1140, 100);

            tbleConsult.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            tbleConsult.setOpaque(false);

            tblConsult.setAutoCreateRowSorter(true);
            tblConsult.setBackground(new java.awt.Color(255, 255, 255));
            tblConsult.setFont(new java.awt.Font("Segoe UI Black", 0, 12));
            tblConsult.setForeground(new java.awt.Color(51, 51, 51));

            loadDataToTable();

            tblConsult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tblConsult.setGridColor(new java.awt.Color(255, 255, 255));
            tblConsult.setRowHeight(27);
            tblConsult.setSelectionBackground(new java.awt.Color(102, 102, 255));
            tblConsult.setShowHorizontalLines(true);
            tblConsult.setShowVerticalLines(true);
            tbleConsult.setViewportView(tblConsult);



            pnlMain.add(tbleConsult);
            tbleConsult.setBounds(11, 170, 1270, 440);

            lblDeleteConsultation.setBackground(new java.awt.Color(33, 0, 167));
            lblDeleteConsultation.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblDeleteConsultation.setForeground(new java.awt.Color(255, 255, 255));
            lblDeleteConsultation.setHorizontalAlignment(SwingConstants.CENTER);
            lblDeleteConsultation.setText("Delete Consultation");
            lblDeleteConsultation.setToolTipText("Delete consultation");
            lblDeleteConsultation.setOpaque(true);
            lblDeleteConsultation.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent event)
                {
                    // get selected row
                    int selectedRow = tblConsult.getSelectedRow();

                    // if selected row is -1, row is not selected at this moment.
                    if(selectedRow != -1)
                    {
                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the consultation?", "Confirm Delete consultation", JOptionPane.YES_NO_OPTION);

                        if (confirmDelete == JOptionPane.YES_OPTION)
                        {
                            // get medical licence number belongs to selected row.
                            int selectedRowConsultId = (int) tblConsult.getValueAt(selectedRow, 0);

                            // delete doctor selected by user to delete
                            for (Consultation consult : WestminsterSkinConsultationManager.getConsultArrList())
                            {
                                if (consult.getConsultationId() == selectedRowConsultId)
                                {
                                    Consultation memoConsult = consult;
                                    try {
                                        // if user try to first delete a consultation from the system and add a consultation with the same consultation id, it will not going to be saved to database
                                        // because that id is already existing in the database. So when user delete a consultation record from system we have to delete it from both
                                        // arraylist and database.

                                        // deleting consultation from arraylist
                                        WestminsterSkinConsultationManager.getConsultArrList().remove(consult);

                                        // deleting consultation from database
                                        Statement statement = databaseConnector.getConnection().createStatement();
                                        statement.executeUpdate("DELETE FROM Consultation WHERE Consultation_Id = " + selectedRowConsultId );

                                        // refresh/update JTable to show the remaining records.
                                        loadDataToTable();

                                        JOptionPane.showMessageDialog(null, "THE CONSULTATION ID :%d WITH DR. %s ON %s HAS BEEN DELETED SUCCESSFULLY"
                                                .formatted(consult.getConsultationId(), consult.getConsultationDoctor(consult.getConsultDoctorMedNo()).getSurname(), consult.getConsultationDate()),"Deleted", JOptionPane.INFORMATION_MESSAGE);

                                    }
                                    catch (Exception exc)
                                    {
                                        // if an error occurred while deleting the row, show an error message.
                                        JOptionPane.showMessageDialog(null, "Error occurred while deleting the consultation", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
                            }
                        }
                    }

                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblDeleteConsultation);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue(lblDeleteConsultation);
                }
            });
            pnlMain.add(lblDeleteConsultation);
            lblDeleteConsultation.setBounds(850, 630, 190, 40);

            lblAddConsultation.setBackground(new java.awt.Color(33, 0, 167));
            lblAddConsultation.setFont(new java.awt.Font("Segoe UI", 1, 18));
            lblAddConsultation.setForeground(new java.awt.Color(255, 255, 255));
            lblAddConsultation.setHorizontalAlignment(SwingConstants.CENTER);
            lblAddConsultation.setText("Add Consultation");
            lblAddConsultation.setToolTipText("Add a consultation");
            lblAddConsultation.setOpaque(true);
            lblAddConsultation.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event)
                {
                    GUIAddConsultation addGUIConsult = new GUIAddConsultation();
                            addGUIConsult.setVisible(true);
                }
                public void mouseEntered(MouseEvent event) {
                    setLabelGrey(lblAddConsultation);
                }
                public void mouseExited(MouseEvent event) {
                    setLabelBlue(lblAddConsultation);
                }
            });
            pnlMain.add(lblAddConsultation);
            lblAddConsultation.setBounds(630, 630, 190, 40);

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
            pnlMain.add(txtSearch);
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


            lblSearchText.setFont(new java.awt.Font("Segoe UI", 0, 14));
            lblSearchText.setForeground(new java.awt.Color(60, 63, 65));
            lblSearchText.setText("Search by  Med Licence No -");
            pnlMain.add(lblSearchText);
            lblSearchText.setBounds(70, 133, 175, 25);

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
            label.setBackground(new java.awt.Color(51,51,51));  // grey
        }

        public void setLabelBlue(JLabel label)
        {
            label.setBackground(new java.awt.Color(33,0,167)); // blue
        }

        public static void loadDataToTable()
        {
            // Create a new table model
            DefaultTableModel model = new DefaultTableModel();

            // Set the column names for the table model
            model.setColumnIdentifiers(new String[] {"Consultation Id", "Doc. Med Licence No", "Patient Id", "Requirement", "Placed Date", "Placed Time", "Consultation Date", "Timeslot" , "Cost", "Notes"});

            // Add the rows to the table model
            for (Consultation consult : WestminsterSkinConsultationManager.getConsultArrList()) {
                model.addRow(new Object[] { consult.getConsultationId(), consult.getConsultDoctorMedNo(), consult.getConsultPatientId(), consult.getPatientRequirement(), consult.getStringConsultationPlacedDate(), consult.getStringConsultationPlacedTime(), consult.getConsultationDate(), consult.getTimeSlot().getStringTimeslotForGivenId(consult.getTimeSlot().getTimeslotId()), consult.getCost(), consult.getNotes()});
            }

            // make table not editable
            tblConsult.setDefaultEditor(Object.class, null);

            // Set the table model as the model for the JTable
            tblConsult.setModel(model);
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
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblConsult.getModel());
        tblConsult.setRowSorter(sorter);

        // Create a filter to be applied when the user types in the text field
        RowFilter<TableModel, Object> filter = new RowFilter<TableModel, Object>()
        {
            @Override
            public boolean include(RowFilter.Entry<? extends TableModel, ? extends Object> entry)
            {
                // Get the value of the column in the current row being filtered. index is specified to filter using med licence no.(index 1 column)
                String value = entry.getStringValue(1);

                // Return true if the value matches the user input, false otherwise
                return value.contains(txtSearch.getText().toUpperCase());
            }
        };

        // Set the filter on the RowSorter
        sorter.setRowFilter(filter);
    }
    }

