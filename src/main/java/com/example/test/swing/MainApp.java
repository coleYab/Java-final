package com.example.test.swing;

import javax.swing.*;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
    now you need to create  ui to act as a dashboard for a student that will contain
    the information of the student like their name, their user name, and a table to display 
    the course that they are enrolled to and the table will be displyed from a course list,
    in the table we need to show curse code, course name, course status and button to the the course detial 
 */
@Component
public class MainApp extends JFrame {
    public MainApp() {
        // Create the main frame
        super("Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLayout(new CardLayout());

        ArrayList<StudentDashboardUIS.Course> courses = new ArrayList<>();
        courses.add(new StudentDashboardUIS.Course("CSC101", "Introduction to Computer Science", "Enrolled"));
        courses.add(new StudentDashboardUIS.Course("MAT200", "Advanced Mathematics", "Not Enrolled"));
        
        // Simulated assignment and task data
        AssignmentDetailUI.Assignment assignment = new AssignmentDetailUI.Assignment("Assignment 1", 100, "2024-06-20", "Not Submitted");
                ArrayList<AssignmentDetailUI.Task> tasks = new ArrayList<>();
                tasks.add(new AssignmentDetailUI.Task("Task 1", "Description for Task 1"));
                tasks.add(new AssignmentDetailUI.Task("Task 2", "Description for Task 2"));
        

        // Create the panel container with CardLayout
        JPanel panelContainer = new JPanel(new CardLayout());

        // Create login and register panels
        JPanel loginPanel = createLoginPanel(panelContainer);
        JPanel registerPanel = createRegisterPanel(panelContainer);
        JPanel coursePanel = CourseDashboardPanel.createAssignmentPanel("CS5 - 1122", "Into to computer science", panelContainer);
        JPanel studentPanel = StudentDashboardUIS.createStudentDashboardPanel(new StudentDashboardUIS.Student("NAme", "usdrwesfsd"), courses, panelContainer);
        JPanel assignmentPanel = AssignmentDetailUI.createAssignmentDetailPanel(assignment, tasks, panelContainer);

        // Add panels to the container
        panelContainer.add(loginPanel, "loginPanel");
        panelContainer.add(registerPanel, "registerPanel");
        panelContainer.add(coursePanel, "coursePanel");
        panelContainer.add(studentPanel, "studentPanel");
        panelContainer.add(assignmentPanel, "assignmentPanel");

        // Add container to the frame
        this.add(panelContainer);
        this.setVisible(true);
    }

    // Method to create login panel
    public static JPanel createLoginPanel(JPanel panelContainer) {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add a title label
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        // Add a username label
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        // Add a username text field
        JTextField userText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(userText, gbc);

        // Add a password label
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        // Add a password field
        JPasswordField passwordText = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordText, gbc);

        // Add a login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 25));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // Add a register button
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 25));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(registerButton, gbc);


        // Add action listner to login button to swith to the course panel
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "studentPanel");
            }
        });

        // Add action listener to register button to switch to the register panel
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "registerPanel");
            }
        });

        return loginPanel;
    }

    // Method to create register panel
    public static JPanel createRegisterPanel(JPanel panelContainer) {
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add a title label
        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registerPanel.add(titleLabel, gbc);

        // Add a full name label
        JLabel fullNameLabel = new JLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        registerPanel.add(fullNameLabel, gbc);

        // Add a full name text field
        JTextField fullNameText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        registerPanel.add(fullNameText, gbc);

        // Add a username label
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(userLabel, gbc);

        // Add a username text field
        JTextField userText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        registerPanel.add(userText, gbc);

        // Add a password label
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(passwordLabel, gbc);

        // Add a password field
        JPasswordField passwordText = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        registerPanel.add(passwordText, gbc);

        // Add a role label
        JLabel roleLabel = new JLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        registerPanel.add(roleLabel, gbc);

        // Add a role combo box
        String[] roles = { "Teacher", "Student" };
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        gbc.gridy = 4;
        registerPanel.add(roleComboBox, gbc);

        // Add a register button
        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 25));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(registerButton, gbc);

        // Add action listener to register button (you can implement the registration
        // logic here)
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Registration logic here
                JOptionPane.showMessageDialog(panelContainer, "Registered successfully!");
                CardLayout cardLayout = (CardLayout) panelContainer.getLayout();
                cardLayout.show(panelContainer, "loginPanel");
            }
        });

        return registerPanel;
    }

    
}