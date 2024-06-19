package com.example.test.swing;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentDashboardUIS {
    public static void main(String[] args) {
        // Simulated student and course data
        Student student = new Student("John Doe", "johndoe123");
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CSC101", "Introduction to Computer Science", "Enrolled"));
        courses.add(new Course("MAT200", "Advanced Mathematics", "Not Enrolled"));

        // Create the frame
        JFrame frame = new JFrame("Student Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add the student dashboard panel
        frame.add(createStudentDashboardPanel(student, courses, new JPanel()));

        // Set frame visibility
        frame.setVisible(true);
    }

    // Function to create a student dashboard panel
    public static JPanel createStudentDashboardPanel(Student student, ArrayList<Course> courses, JPanel panelContainer) {
        JPanel dashboardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Student information labels
        JLabel nameLabel = new JLabel("Name: " + student.getName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dashboardPanel.add(nameLabel, gbc);

        JLabel usernameLabel = new JLabel("Username: " + student.getUsername());
        gbc.gridx = 1;
        gbc.gridy = 0;
        dashboardPanel.add(usernameLabel, gbc);

        // Table for enrolled courses
        String[] columnNames = {"Course Code", "Course Name", "Course Status", "Details"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable courseTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        dashboardPanel.add(scrollPane, gbc);

        // Populate the table with course data
        for (Course course : courses) {
            Object[] rowData = {course.getCode(), course.getName(), course.getStatus(), "Details"};
            model.addRow(rowData);
        }

        // Button column for course details
        ButtonColumn detailsButtonColumn = new ButtonColumn(courseTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle course details action here
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                String courseCode = (String) table.getModel().getValueAt(modelRow, 0);
                String courseName = (String) table.getModel().getValueAt(modelRow, 1);
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "coursePanel");
            }
        }, 3);

        // Button to register for a new course
        JButton registerButton = new JButton("Register for a New Course");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        dashboardPanel.add(registerButton, gbc);

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle registration for a new course
                JOptionPane.showMessageDialog(null, "Registering for a new course...");
            }
        });

        return dashboardPanel;
    }

    // Example class to represent a student
    static class Student {
        private String name;
        private String username;

public Student(String name, String username) {
            this.name = name;
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }
    }

    // Example class to represent a course
    static class Course {
        private String code;
        private String name;
        private String status;

        public Course(String code, String name, String status) {
            this.code = code;
            this.name = name;
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }
    }

    // Class to create a button column in JTable
    static class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
        private JButton renderButton;
        private JButton editButton;
        private JTable table;
        private Action action;

        public ButtonColumn(JTable table, Action action, int column) {
            this.table = table;
            this.action = action;
            renderButton = new JButton();
            editButton = new JButton();
            editButton.addActionListener(this);
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer(this);
            columnModel.getColumn(column).setCellEditor(this);
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            renderButton.setText(value.toString());
            return renderButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editButton.setText(value.toString());
            return editButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            fireEditingStopped();
            ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
            action.actionPerformed(event);
        }
    }
}