package com.mycompany.mavenproject1;

import com.mongodb.internal.connection.Time;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class AddCompetitionGUI extends JFrame {

    // private JTextField createdByUsernameField;
    private JTextField nameOfCompetitionField;
    // private JTextField participantsField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField durationField;
    private JTextField evaluationTypeField;
    int Qcount ;
    // private JTextField questionCountField;
    List<CompetitionQuestion> QuesList ;
    JButton addQuestion;
    JButton addButton;
    static database db  = database.getInstance();
    private String uname ;
    public AddCompetitionGUI(String username ) 
    {
        uname = username;
        Qcount = 0 ;
        setTitle("Add Competition");
        QuesList = new ArrayList<>();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        // createdByUsernameField = new JTextField();
        nameOfCompetitionField = new JTextField();
        // participantsField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        durationField = new JTextField();
        evaluationTypeField = new JTextField();
        // questionCountField = new JTextField();


        add(new JLabel("Name of Competition:"));
        add(nameOfCompetitionField);

        add(new JLabel("Date (YYYY-MM-DD):"));
        add(dateField);
        add(new JLabel("Time (HH:mm:ss):"));
        add(timeField);
        add(new JLabel("Duration (HH:mm:ss):"));
        add(durationField);
        add(new JLabel("Evaluation Type (true/false):"));
        add(evaluationTypeField);
       
        addButton = new JButton("Add Competition");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Qcount != 0 && isDateAndTimeValid(dateField.getText(),timeField.getText(),durationField.getText()))
                    addCompetition();
                else
                {
                     JOptionPane.showMessageDialog(AddCompetitionGUI.this, "Enter Data in Format Given Above", "Error", JOptionPane.ERROR_MESSAGE);
                     return; // Do not proceed if any field is empty
                }
                
            }
        });
        add(addButton);
        addButton.setEnabled(false);


        addQuestion = new JButton("Add Question");
        addQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                openInputFrame();
                
            }
        });
        add(addQuestion);

        

        

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public boolean isDateAndTimeValid(String dateString, String starttime, String duration) {
    Pattern TIME_PATTERN = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
    Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    if (!DATE_PATTERN.matcher(dateString).matches() || 
        !TIME_PATTERN.matcher(starttime).matches() || 
        !TIME_PATTERN.matcher(duration).matches()) {
        return false;
    }

    try {
        LocalDate inputDate = LocalDate.parse(dateString);
        LocalDate today = LocalDate.now();

        // Check if the input date is after today
        return inputDate.isAfter(today);
    } catch (DateTimeParseException e) {
        // Handle the exception (e.g., log it, return false, etc.)
        // e.printStackTrace();
        return false;
    }
}
    private void addCompetition() {
  
        String nameOfCompetition = nameOfCompetitionField.getText();
        List<String> participants = null;
        String dateString = dateField.getText();
        String timeString = timeField.getText();
        String date = dateString;
        String startingTime = timeString;
        String duration = durationField.getText();
        boolean evaluationType = Boolean.parseBoolean(evaluationTypeField.getText());
      
        Competition competition = new Competition(uname, nameOfCompetition, participants, date, startingTime, duration, evaluationType, Qcount);
        for(int i = 0 ; i < Qcount ; i++)
            competition.quiz.AddQuestion(QuesList.get(i));


        db.uploadCompetition(competition);

        // System.out.println("Added");
        dispose();
    
       
    }
    private void openInputFrame() {

        JFrame inputFrame = new JFrame("Add Question");
        inputFrame.setSize(400, 300);

       
        JTextField questionTextField = new JTextField(20);
        JTextField outputTextField = new JTextField(20);
        JTextField sampleExampleTextField = new JTextField(20);


        String[] topics = {"PF", "OOP", "DSA", "Algorithm"};
        JComboBox<String> topicComboBox = new JComboBox<>(topics);

        JCheckBox codingTypeCheckBox = new JCheckBox("Answer Required Code : ");

        JButton submitButton = new JButton("Submit");

     
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String question = questionTextField.getText();
                String output = outputTextField.getText();
                String sampleExample = sampleExampleTextField.getText();
                String topic = (String) topicComboBox.getSelectedItem(); // Get selected topic
                boolean isCodingType = codingTypeCheckBox.isSelected();



                if (question.isEmpty() || output.isEmpty() || sampleExample.isEmpty()) {
                    JOptionPane.showMessageDialog(inputFrame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Do not proceed if any field is empty
                }


                CompetitionQuestion newQuestion = new CompetitionQuestion();
              
                newQuestion.QuestionStatement = question;
                newQuestion.Output = output;
                newQuestion.SampleExample = sampleExample;
                newQuestion.Topic = topic;
                newQuestion.QuestionType = isCodingType;
                if(Qcount < 3)
                {
                    QuesList.add(newQuestion);
                    Qcount++ ;
                    
                    if(Qcount == 1)
                    {
                        addButton.setEnabled(true);
                        System.out.println("Q added");
                        
                    }
                    else if(Qcount == 3)
                        addQuestion.setEnabled(false);


                }
                // newQuestion.CreatedByUsername = Username ;

                // db.addNewPracticeQuestion(newQuestion);

                // Add your logic to handle the new practice question (e.g., add to MongoDB)
                // addNewPracticeQuestion(collection, newQuestion);
                // ...

                // Close the input frame after submission

                inputFrame.dispose();
            }
        });

        // Set layout for the input frame
        inputFrame.setLayout(new GridLayout(7, 2));

        // Add components to the input frame
        inputFrame.add(new JLabel("Question:"));
        inputFrame.add(questionTextField);
        inputFrame.add(new JLabel("Output:"));
        inputFrame.add(outputTextField);
        inputFrame.add(new JLabel("Sample Example:"));
        inputFrame.add(sampleExampleTextField);
        inputFrame.add(new JLabel("Topic:"));
        inputFrame.add(topicComboBox);
        inputFrame.add(new JLabel("Question Type:"));
        inputFrame.add(codingTypeCheckBox);
        inputFrame.add(submitButton);

        // Set the input frame to be visible
        inputFrame.setVisible(true);
    }
    
    // Open input frames side by side
    private void openInputFrame(String createdByUsername, String nameOfCompetition, List<String> participants, Date date, Time startingTime, Time duration, boolean evaluationType, int questionCount) {
        // Assuming you want to arrange frames horizontally
        int frameWidth = 400;
        int frameHeight = 300;
    
        List<CompetitionQuestion> questions = new ArrayList<>();
    
        for (int i = 0; i < questionCount; i++) {
            // Create a new JFrame for input
            JFrame inputFrame = new JFrame("Add Question");
            inputFrame.setSize(frameWidth, frameHeight);
            inputFrame.setSize(400, 300);

        // Add components for input (text fields, labels, etc.)
        JTextField questionTextField = new JTextField(20);
        JTextField outputTextField = new JTextField(20);
        JTextField sampleExampleTextField = new JTextField(20);

        // ComboBox for Topic selection
        String[] topics = {"PF", "OOP", "DSA", "Algorithm"};
        JComboBox<String> topicComboBox = new JComboBox<>(topics);

        JCheckBox codingTypeCheckBox = new JCheckBox("Coding Type");

        JButton submitButton = new JButton("Submit");

    
            // ... (Rest of your code for creating components and setting up the frame)
    
            // Add an ActionListener to the submit button
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get input values
                    String question = questionTextField.getText();
                    String output = outputTextField.getText();
                    String sampleExample = sampleExampleTextField.getText();
                    String topic = (String) topicComboBox.getSelectedItem(); // Get selected topic
                    boolean isCodingType = codingTypeCheckBox.isSelected();
                    if(isCodingType == true)
                        isCodingType = false ;
                    else
                        isCodingType = true ;

                    // Check for empty fields
                    if (question.isEmpty() || output.isEmpty() || sampleExample.isEmpty()) {
                        JOptionPane.showMessageDialog(inputFrame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Do not proceed if any field is empty
                    }
    
                    // Create a new Question object
                    CompetitionQuestion newQuestion = new CompetitionQuestion();
                    newQuestion.QuestionStatement = question;
                    newQuestion.Output = output;
                    newQuestion.SampleExample = sampleExample;
                    newQuestion.Topic = topic;
                    newQuestion.QuestionType = isCodingType;
                    // newQuestion.CreatedByUsername = createdByUsername;
    
                    // Add the question to the list
                    questions.add(newQuestion);
      
                    // Close the input frame after submission
                    inputFrame.dispose();
                }
            });
    
            // Set the input frame to be visible
            inputFrame.setVisible(true);
        }
    
        // After all questions are added, create the competition
      
    
        // You can now use the 'competition' object as needed (e.g., add to a database).
        // For simplicity, we'll just print the information for now.
        // System.out.println("Competition Added:\n" + competition);
    
        // You might want to close the main frame after adding the competition

    }



    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new AddCompetitionGUI());
    // }
}
