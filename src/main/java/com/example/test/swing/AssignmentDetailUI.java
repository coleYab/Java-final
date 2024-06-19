package com.example.test.swing;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignmentDetailUI {
    public static void main(String[] args) {
        // Simulated assignment and task data
        Assignment assignment = new Assignment("Assignment 1", 100, "2024-06-20", "Not Submitted");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description for Task 1"));
        tasks.add(new Task("Task 2", "Description for Task 2"));

        // Create the frame
        JFrame frame = new JFrame("Assignment Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add the assignment detail panel
        frame.add(createAssignmentDetailPanel(assignment, tasks, new JPanel()));

        // Set frame visibility
        frame.setVisible(true);
    }

    // Function to create an assignment detail panel
    public static JPanel createAssignmentDetailPanel(Assignment assignment, ArrayList<Task> tasks, JPanel panelContainer) {
        JPanel assignmentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Assignment information labels
        JLabel titleLabel = new JLabel("Title: " + assignment.getTitle());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        assignmentPanel.add(titleLabel, gbc);

        JLabel totalMarksLabel = new JLabel("Total Marks: " + assignment.getTotalMarks());
        gbc.gridy = 1;
        assignmentPanel.add(totalMarksLabel, gbc);

        JLabel submissionDateLabel = new JLabel("Submission Date: " + assignment.getSubmissionDate());
        gbc.gridy = 2;
        assignmentPanel.add(submissionDateLabel, gbc);

        JLabel statusLabel = new JLabel("Status: " + assignment.getStatus());
        gbc.gridy = 3;
        assignmentPanel.add(statusLabel, gbc);

        // Table for tasks
        String[] columnNames = {"Task", "Description", "Accept File"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable taskTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        assignmentPanel.add(scrollPane, gbc);

        // Populate the table with task data
        for (Task task : tasks) {
            Object[] rowData = {task.getTitle(), task.getDescription(), "Accept File"};
            model.addRow(rowData);
        }

        // Button column for accepting files
        ButtonColumn acceptFileButtonColumn = new ButtonColumn(taskTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle file acceptance here
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Process the selected file
                    JOptionPane.showMessageDialog(null, "File accepted: " + fileChooser.getSelectedFile().getName());
                }
            }
        }, 2);

//         // Button to choose a file for submission
//         JButton chooseFileButton = new JButton("Choose File to Submit");
//         gbc.gridx = 0;
//         gbc.gridy = 5;
//         gbc.gridwidth = 2;
//         gbc.fill = GridBagConstraints.NONE;
//         gbc.anchor = GridBagConstraints.CENTER;
//         assignmentPanel.add(chooseFileButton, gbc);

//         // Add action listener to the choose file button

// chooseFileButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Handle file selection for submission
//                 JFileChooser fileChooser = new JFileChooser();
//                 int result = fileChooser.showOpenDialog(null);
//                 if (result == JFileChooser.APPROVE_OPTION) {
//                     // Process the selected file
//                     JOptionPane.showMessageDialog(null, "File chosen: " + fileChooser.getSelectedFile().getName());
//                 }
//             }
//         });

        // Button to submit the current task results
        JButton submitCurrentResultsButton = new JButton("Go Back");
        gbc.gridy = 6;
        assignmentPanel.add(submitCurrentResultsButton, gbc);

        // Add action listener to the submit current results button
        submitCurrentResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "coursePanel");
            }
        });

        // Button to submit the whole assignment
        JButton submitAssignmentButton = new JButton("Submit Assignment");
        gbc.gridy = 7;
        assignmentPanel.add(submitAssignmentButton, gbc);

        // Add action listener to the submit assignment button
        submitAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle submission of the entire assignment
                JOptionPane.showMessageDialog(null, "Assignment submitted.");
            }
        });

        return assignmentPanel;
    }

    // Example class to represent an assignment
    static class Assignment {
        private String title;
        private int totalMarks;
        private String submissionDate;
        private String status;

        public Assignment(String title, int totalMarks, String submissionDate, String status) {
            this.title = title;
            this.totalMarks = totalMarks;
            this.submissionDate = submissionDate;
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public int getTotalMarks() {
            return totalMarks;
        }

        public String getSubmissionDate() {
            return submissionDate;
        }

        public String getStatus() {
            return status;
        }
    }

    // Example class to represent a task
    static class Task {
        private String title;
        private String description;

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
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