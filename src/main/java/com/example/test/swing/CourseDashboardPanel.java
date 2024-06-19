package com.example.test.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseDashboardPanel {

    public static JPanel createAssignmentPanel(String courseCode, String courseName, JPanel panelContainer) {
        // Create the panel for assignment details
        JPanel assignmentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label for course information
        JLabel courseLabel = new JLabel("Course: " + courseCode + " - " + courseName);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        assignmentPanel.add(courseLabel, gbc);

        // Table for assignments
        String[] columnNames = {"Assignment Title", "Total Mark Value", "Submission Date", "Status", "Details"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable assignmentTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(assignmentTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        assignmentPanel.add(scrollPane, gbc);

        // Simulated assignment data (can be fetched from a database or source)
        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment("Assignment 1", 100, "2024-07-01", "Submitted"));
        assignments.add(new Assignment("Assignment 2", 80, "2024-07-15", "Not Submitted"));

        // Populate the table with assignment data
        for (Assignment assignment : assignments) {
            Object[] rowData = {assignment.getTitle(), assignment.getTotalMark(), assignment.getSubmissionDate(),
                    assignment.getStatus(), "Details"};
            model.addRow(rowData);
        }

        // Button column for assignment details
        ButtonColumn detailsButtonColumn = new ButtonColumn(assignmentTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle assignment details action here
                int modelRow = Integer.valueOf(e.getActionCommand());
                String assignmentTitle = (String) assignmentTable.getModel().getValueAt(modelRow, 0);
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "assignmentPanel");
            }
        }, 4);

        // Button to add new assignment
        JButton addAssignmentButton = new JButton("Add Assignment");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        assignmentPanel.add(addAssignmentButton, gbc);

        // // Button to go to each assignment
        // for (int i = 0; i < assignments.size(); i++) {
        //     JButton assignmentButton = new JButton("Assignment " + (i + 1));
        //     final int index = i; // Need final or effectively final for lambda expression
        //     assignmentButton.addActionListener(e -> {
        //         String assignmentTitle = assignments.get(index).getTitle();
        //         JOptionPane.showMessageDialog(assignmentPanel, "Go to Assignment: " + assignmentTitle);
        //     });
        //     gbc.gridx = 1;
        //     gbc.gridy = 2 + i;
        //     gbc.gridwidth = 1;
        //     gbc.fill = GridBagConstraints.NONE;
        //     gbc.anchor = GridBagConstraints.WEST;
        //     assignmentPanel.add(assignmentButton, gbc);
        // }

        return assignmentPanel;
    }

// Example class to represent an assignment
    static class Assignment {
        private String title;
        private int totalMark;
        private String submissionDate;
        private String status;

        public Assignment(String title, int totalMark, String submissionDate, String status) {
            this.title = title;
            this.totalMark = totalMark;
            this.submissionDate = submissionDate;
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public int getTotalMark() {
            return totalMark;
        }

        public String getSubmissionDate() {
            return submissionDate;
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
            renderButton.setText(value == null ? "" : value.toString());
            return renderButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editButton.setText(value == null ? "" : value.toString());
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

    public static void main(String[] args) {
        // Example usage: Creating and displaying assignment panel for a specific course
        JFrame frame = new JFrame("Course Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel assignmentPanel = createAssignmentPanel("CSC101", "Introduction to Computer Science", new JPanel());
        frame.add(assignmentPanel);

        frame.setVisible(true);
    }
}