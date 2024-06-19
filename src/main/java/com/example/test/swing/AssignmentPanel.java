package com.example.test.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignmentPanel {

    private static DefaultTableModel taskTableModel;

    public static JPanel createAssignmentPanel() {
        // Create the panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Create the labels and text fields for the assignment
        JLabel titleLabel = new JLabel("Assignment Title:");
        JTextField titleField = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Assignment Description:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel resultsLabel = new JLabel("Assignment Results:");
        JTextField resultsField = new JTextField(20);

        // Create labels and text fields for the task
        JLabel taskNameLabel = new JLabel("Task Name:");
        JTextField taskNameField = new JTextField(20);

        JLabel taskDescriptionLabel = new JLabel("Task Description:");
        JTextField taskDescriptionField = new JTextField(20);

        JLabel testFileLabel = new JLabel("Test File:");
        JTextField testFileField = new JTextField(20);
        JButton browseButton = new JButton("Browse");

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    testFileField.setText(fileChooser.getSelectedFile().getPath());
                }
            }
        });

        // Create table to display tasks
        String[] columnNames = {"Task Name", "Task Description"};
        taskTableModel = new DefaultTableModel(columnNames, 0);
        JTable taskTable = new JTable(taskTableModel);
        JScrollPane taskScrollPane = new JScrollPane(taskTable);

        // Create buttons
        JButton addTaskButton = new JButton("Add Task");
        JButton createAssignmentButton = new JButton("Create Assignment");
        JButton goBackButton = new JButton("Go Back");

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskNameField.getText();
                String taskDescription = taskDescriptionField.getText();
                if (!taskName.isEmpty() && !taskDescription.isEmpty()) {
                    taskTableModel.addRow(new Object[]{taskName, taskDescription});
                    taskNameField.setText("");
                    taskDescriptionField.setText("");
                    testFileField.setText("");
                } else {
                    JOptionPane.showMessageDialog(panel, "Task name and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to the previous screen/page
                JOptionPane.showMessageDialog(panel, "Going back to the previous page.", "Info", JOptionPane.INFORMATION_MESSAGE);
                // This is a placeholder. Replace with actual navigation logic.
            }
        });

        // Add components to the panel with GridBagConstraints

        // Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(titleLabel, gbc);

        // Title Field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titleField, gbc);

        // Description Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        panel.add(descriptionLabel, gbc);

        // Description Area
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(descriptionScrollPane, gbc);

        // Results Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(resultsLabel, gbc);

        // Results Field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(resultsField, gbc);

        // Task Name Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(taskNameLabel, gbc);

        // Task Name Field
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(taskNameField, gbc);

        // Task Description Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(taskDescriptionLabel, gbc);

        // Task Description Field
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(taskDescriptionField, gbc);

        // Test File Label
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(testFileLabel, gbc);

        // Test File Field
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(testFileField, gbc);

        // Browse Button
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(browseButton, gbc);

        // Add Task Button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(addTaskButton, gbc);

        // Task Table
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3; // Span across three columns
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(taskScrollPane, gbc);

        // Create Assignment Button
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(createAssignmentButton, gbc);

        // Go Back Button
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(goBackButton, gbc);

        return panel;
    }

    public static void main(String[] args) {
        // Create a frame to test the panel
        JFrame frame = new JFrame("Add Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.add(createAssignmentPanel());
        frame.setVisible(true);
    }
}

