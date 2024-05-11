/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.bson.types.ObjectId;
import com.mycompany.mavenproject1.database.Post;

/**
 *
 * @author apple
 */
public class FrontendGUI extends javax.swing.JFrame {

   
    static database db  = database.getInstance();
    private JPanel postsPanel;
    private JScrollPane scrollPane;
    private List<PostPanel> posts;
    String Username ;
    String role ;
    GlobalLeaderBoard leaderBoard ;

    public FrontendGUI(String username , String rol )
     {
        role = rol;

        Custom();
        initComponents();
        //role = rol ;

        LeaderboardinitComponents();
        leaderBoard = new GlobalLeaderBoard();
        leaderBoard.AddUser(username, 0);
        // db.updatePoints(username);
        Username = username ;

        jLabel1.setText(username);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        
        this.setVisible(true);
    }

    void GetLeaderBoardfromdb()
    {
        List<com.mycompany.mavenproject1.Leaderboard.Point> pointsList = db.GetAllPoints();
        tableModel.setRowCount(0);

         for(int i = 0 ; i < pointsList.size() ; i++)
         {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, pointsList.get(i).Username, pointsList.get(i).points});
         }
    }

    void GetPostsfromdb()
    {
         List<Post> postList = db.getAllPosts();

         for(int i = 0 ; i < postList.size() ; i++)
         {
                PostPanel newPost = new PostPanel(postList.get(i).username, postList.get(i).postdata , postList.get(i).comments ,postList.get(i)._id);
                posts.add(newPost);
         }
        //    updatePosts();

    }
    private JTable pointsTable;
    DefaultTableModel tableModel;

    // ... (existing code)

    private void LeaderboardinitComponents() {
        // ... (existing code)
        
        tableModel = new DefaultTableModel(new Object[]{"No.", "Username", "Points"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Set all cells to be uneditable
            }
        };


        // Create the JTable using the model
        pointsTable = new JTable(tableModel);
    
        // Add numbering to the row
        pointsTable.getColumnModel().getColumn(0).setHeaderValue("No."); // Set the header for the numbering column
    
        // Create a scroll pane for the table
        JScrollPane tableScrollPane = new JScrollPane(pointsTable);
        tableScrollPane.setPreferredSize(new Dimension(200, 200));
    
        // Add the scroll pane to jScrollPane2
        jScrollPane2.setViewportView(tableScrollPane);
        GetLeaderBoardfromdb();
    

    }


    void updatePointsTable() {
        // Example of updating the table with data
        DefaultTableModel tableModel = (DefaultTableModel) pointsTable.getModel();
        tableModel.addRow(new Object[]{Username, 100}); // Replace 100 with the actual points value
        // Add more rows as needed

        // Refresh the UI
        tableModel.fireTableDataChanged();
    }
    


    private void updatePosts() {
        // Clear the existing postsPanel before updating
        postsPanel.removeAll();


        for (PostPanel post : posts) {
            postsPanel.add(post.getPostPanel());
        }

        // Refresh the UI
        validate();
        repaint();
    }

    void Custom()
    {
        this.posts = new ArrayList<>();
          this.postsPanel = new JPanel();
        this.postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        this.scrollPane = new JScrollPane(postsPanel);
        GetPostsfromdb();
            // Create Swing components


        // Create layout
        setLayout(new BorderLayout());
        // add(addPostButton, BorderLayout.NORTH);
        // add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        // setTitle("Facebook Posts");
        // setSize(400, 800);
       
        setVisible(true);
    

    

        this.getContentPane().add(scrollPane);
    }
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        jButton2 = new javax.swing.JButton();
        // scrollPane = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setHorizontalAlignment(JLabel.CENTER);
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setHorizontalAlignment(JLabel.CENTER);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setHorizontalAlignment(JLabel.CENTER);
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        // jLabel1.setText("jLabel1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/mavenproject1/imgs/images.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(204, 153, 255));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton2.setText("Add Post");
        jButton2.setAutoscrolls(true);
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setOpaque(true);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = openPostFrame();
                System.out.println("PostPanel: " + postText);

                
            }
        });



        jButton1.setBackground(new java.awt.Color(153, 255, 204));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton1.setText("Competition");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setOpaque(true);
         jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Competition> competitions;
                Competition startedCompetition = null;
                
                if(MainRunner.comparestring(role, "Participant"))
                {
                    competitions = db.getCompetitionsforParticipant(Username);
                    for(int i = 0 ; i < competitions.size() ; i++)
                    {
                        System.out.println(competitions.get(i).hasStarted());
                        if(competitions.get(i).hasStarted())
                        {
                              System.out.println("Selected value in the last column: " + competitions.get(i).Competitionid);
                            startedCompetition = competitions.get(i) ;
                            break;
                        }
                    }


                }
                else
                {
                    System.out.println("fgffgf");
                    competitions = db.RetrieveOwnCompetitions(Username);
                    System.out.println("fgffgf");
                    for(int i = 0 ; i < competitions.size() ; i++)
                    {
                        if(competitions.get(i).hasStarted())
                        {
                            startedCompetition = competitions.get(i) ;
                            break;
                        }
                    }
                }

                
                // Assuming you have a method getPracticeQuestions that returns t
                // displayPracticeQuestionFrame();
                // Display the PracticeQuestionFrame when the button is clicked
                if(startedCompetition==null)
                {
                     openCompetitionWindow(this);
                }
                else
                {
                    
                 CompetitionGUI competitionGUI = new CompetitionGUI(startedCompetition, Username,role);
                }
           
            }
        });
        
      

        jButton3.setBackground(new java.awt.Color(153, 255, 204));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton3.setText("Practice Questions");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setOpaque(true);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming you have a method getPracticeQuestions that returns t
                displayPracticeQuestionFrame();
                // Display the PracticeQuestionFrame when the button is clicked
           
            }
        });
        

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton4.setText("Add Question");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setOpaque(true);
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new frame for input
                openInputFrame();
            }
        });
        if(MainRunner.comparestring(role,"Participant"))
            jButton5.setEnabled(false);

        jButton5.setBackground(new java.awt.Color(153, 204, 255));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton5.setText("Add Competition");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.setOpaque(true);
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddCompetitionFrame();
            }
        });

        // if(MainRunner.comparestring(role,"Participant"))
        //     jButton6.setEnabled(false);

        jButton6.setBackground(new java.awt.Color(153, 204, 255));
        jButton6.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton6.setText("View Competition");
        jButton6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setOpaque(true);
        jButton6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                        openCompetitionWindow(this);

            }

            });

        getContentPane().add(jButton6, BorderLayout.CENTER);

        
      

        jLabel3.setBackground(new java.awt.Color(153, 255, 204));
        jLabel3.setText(role);
        
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(20, 120, 234));
        jLabel4.setFont(new java.awt.Font("Chalkboard SE", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" Timeline");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(20, 120, 234));
        jLabel5.setFont(new java.awt.Font("Chalkboard SE", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Leaderboard");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(398, 398, 398))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



  
    // Variables declaration - do not modify//GEN-BEGIN:variables

    public void openCompetitionWindow(ActionListener actionListener) {
        List<Competition> competitions;
        if(MainRunner.comparestring(role,"Participant"))
        {
            competitions = db.RetrieveOwnCompetitions("All");
        }
        else
        {
        // Retrieve competitions based on a username (replace "exampleUsername" with the actual username)
            competitions = db.RetrieveOwnCompetitions(Username);
        }

        // Create and set up the competition window with a table
        JFrame competitionWindow = new JFrame("Competitions Window");
        competitionWindow.setSize(700, 400);
        competitionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display competitions with all details in a table
        displayCompetitionsTable(competitions, competitionWindow);

        // Center the competition window relative to the parent frame
        // competitionWindow.setLocationRelativeTo(actionListener);

        // Show the competition window
        competitionWindow.setVisible(true);
    }
    JTable competitionTable;

    // Your existing method to retrieve competitions
   
    // Method to display competitions with all details in a table
    private void displayCompetitionsTable(List<Competition> competitions, JFrame competitionWindow) 
    {
        if(MainRunner.comparestring(role,"Participant"))
        {
            String[] columnNames = { "Name", "Participants", "Date", "Starting Time", "Duration", "Evaluation Type", "Question Count", "Status"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Set all cells to be uneditable
        }
        };
        if (MainRunner.comparestring(role, "Participant")) {

        competitionTable = new JTable(model);

        // Add a button column
        // model.addColumn("Participate");

        // Custom renderer and editor for the button column
        competitionTable.getColumnModel().getColumn(model.getColumnCount() - 1).setCellRenderer(new ParticipateButtonRenderer());
        competitionTable.getColumnModel().getColumn(model.getColumnCount() - 1).setCellEditor(new ParticipateButtonEditor(new JCheckBox()));

        for (Competition competition : competitions) {
            int questionCount = competition.quiz.Questioncount;
            int partcount = 0; 
            String hasEnded = new String("Participate");
            if(competition.hasEnded())
            {
                hasEnded = "View Result";
            }
            else if(competition.hasStarted())
            {
                hasEnded = "Started";
            }
            if(competition.Participants != null)
            {
                partcount = competition.Participants.size() ;
            }
            model.addRow(new Object[]{
            competition.NameofCompetition,
            partcount,
            competition.getDate(),
            competition.getStartingtime(),
            competition.getDuration(),
            competition.isEvaluationType(),
            questionCount,
            hasEnded // Placeholder text for the button column
            });
            competitionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = competitionTable.getSelectedRow();
                    int lastColumnIndex = competitionTable.getColumnCount() - 1;

                    // Check if the last element in the row is selected
                    if (competitionTable.getSelectedColumn() == lastColumnIndex) {
                        // Perform your action for the last column
                        // For example, you can access the value in the last column using:
                        Object lastColumnValue = competitionTable.getValueAt(selectedRow, lastColumnIndex);

                        // Do something with the value
                        System.out.println("Selected value in the last column: " + lastColumnValue + competition.Competitionid);
                        if(MainRunner.comparestring(lastColumnValue.toString(),"Participate"))
                            db.addParticipantToCompetitionInDB(competition.getCompetitionId(), Username); // Replace with your actual method
                        else
                        {
                            System.out.println("bagh jaaa");
                            CompetitionLeaderBoard leaderboard ;//new CompetitionLeaderBoard();
                            leaderboard = database.getInstance().getAllPoints(competition.Competitionid);
                            LeaderboardWindow leaderboardWindow = new LeaderboardWindow(leaderboard);
                            leaderboardWindow.setVisible(true);
                        }
                    }
                }
            }
        });


            // Check if the current user is a participant

            // Add the current username to the competition's participant list
            competition.addParticipant(Username); // Assuming you have an addParticipant method in your Competition class
            // System.out.println("qwertyuio");

            }
            
        }

        competitionWindow.getContentPane().add(new JScrollPane(competitionTable));
        return;
        }
                String[] columnNames = { "Name", "Participants", "Date", "Starting Time", "Duration", "Evaluation Type" , "Question Count"};

        // DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Set all cells to be uneditable
            }
        };

        competitionTable  = new JTable(model);

        for (Competition competition : competitions) {
            int pcount = 0 ;
            if(competition.Participants != null)
                pcount = competition.Participants.size() ;
            
            int questionCount = competition.quiz.Questioncount;
            model.addRow(new Object[]{
                    // competition.getCompetitionId(),
                    // competition.getCreatedByUsername(),
                    competition.NameofCompetition,
                    pcount,
                    competition.getDate(),
                    competition.getStartingtime(),
                    competition.getDuration(),
                    competition.isEvaluationType(),
                    questionCount     
            });
        }

         competitionWindow.getContentPane().add(new JScrollPane(competitionTable));


    }
    private class ParticipateButtonRenderer extends JButton implements TableCellRenderer {
    public ParticipateButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
}

// Custom editor for the "Participate" button column
private class ParticipateButtonEditor extends DefaultCellEditor {
    private JButton button;

    public ParticipateButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        // button.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Handle the "Participate" button click here
        //         // You can get the current row using table.getSelectedRow()
        //         int selectedRow = competitionTable.getSelectedRow();
        //         if (selectedRow != -1) {
        //             // Perform the necessary actions when the button is clicked in a specific row
        //             // For example, add the participant or show a dialog, etc.
        //             // competition.addParticipant(currentUsername);
        //             // db.addParticipantToCompetitionInDB(competition.getCompetitionId(), currentUsername);
        //         }
        //     }
        // });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(value.toString());
        return button;
    }
}


    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    private void openAddCompetitionFrame() {
        SwingUtilities.invokeLater(() -> new AddCompetitionGUI(Username));
    }

        private void openInputFrame() {
        // Create a new JFrame for input
        JFrame inputFrame = new JFrame("Add Question");
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
                PracticeQuestion newQuestion = new PracticeQuestion();
                // newQuestion.QuestionId = new ObjectId();  // Set a new ObjectId
                newQuestion.QuestionStatement = question;
                newQuestion.Output = output;
                newQuestion.SampleExample = sampleExample;
                newQuestion.Topic = topic;
                newQuestion.QuestionType = isCodingType;
                newQuestion.CreatedByUsername = Username ;

                db.addNewPracticeQuestion(newQuestion);

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

    private  void displayPracticeQuestionFrame() {
        JFrame practiceQuestionFrame = new JFrame("Practice Questions");

    // Create a scroll pane to contain the panel
    JScrollPane scrollPane = new JScrollPane();
    practiceQuestionFrame.add(scrollPane);

    // Create a panel to hold the questions
    JPanel questionsPanel = new JPanel();
    questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

    List<PracticeQuestion> practiceQuestions = db.getPracticeQuestions(Username);

    // Add each question panel to the questions panel
    for (PracticeQuestion question : practiceQuestions) {
        // System.out.println("dfsfs");
        JPanel questionPanel = createQuestionPanel(question);
        questionsPanel.add(questionPanel);
    }

    // Set the questions panel as the view for the scroll pane
    scrollPane.setViewportView(questionsPanel);

    // Set the default close operation to exit the application when the frame is closed
    practiceQuestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Set the size of the frame
    practiceQuestionFrame.setSize(700, 800);

    // Make the frame visible
    practiceQuestionFrame.setVisible(true);
    }
    private JPanel createQuestionPanel(PracticeQuestion question) {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        JLabel createdByLabel = new JLabel("Created by: [" + question.getCreatedByUsername()+ "]");
        JLabel questionStatementLabel = new JLabel("Question: " + question.getQuestionStatement());
        JLabel sampleOutputLabel = new JLabel("Sample Output: " + question.getSampleExample());

        JTextField answerTextField = new JTextField();
        JButton answerButton = new JButton("Answer Question");
       
        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the answer here, you can access it using answerTextField.getText()
                String answer = answerTextField.getText();
                System.out.println(question.Output);
                if(question.AnswerPracticeQuestion(answer))
                {
                    
                    if (question.attemptedByUsername == null || !question.attemptedByUsername.contains(Username)) {
                        // Add the username to the list of attempted usernames
                        if (question.attemptedByUsername == null) {
                            question.attemptedByUsername = new ArrayList<>();
                        }
                         question.attemptedByUsername.add(Username);
                         db.updatePoints(Username);
                         GetLeaderBoardfromdb();
                         db.AddUserToQuestionAttemptedByList(question.QuestionId,Username);
                        answerButton.setEnabled(false);
                        uploadButtonActionPerformed(true);
                        // Update the attemptedByUsername list in the MongoDB database
                    }
                    
        
                    
                }
                else
                {
                    uploadButtonActionPerformed(false);
                }
            }
        });

        questionPanel.add(createdByLabel);
        questionPanel.add(questionStatementLabel);
        questionPanel.add(sampleOutputLabel);
        questionPanel.add(answerTextField);
        if(question.QuestionType== false)
        {
            JButton pasteButton = new JButton("Paste");
            pasteButton.addActionListener(e -> pasteText(answerTextField));
            questionPanel.add(pasteButton);
        }
        questionPanel.add(answerButton);
        JPanel seperatorPanel = new JPanel();
             seperatorPanel.setBackground(Color.BLACK);
            questionPanel.add(seperatorPanel);

        return questionPanel;
    }
    void uploadButtonActionPerformed(boolean dialog)
    {
        if(dialog == true)
        {
            JOptionPane.showMessageDialog(null, " Correct Answer ", "Correct Answer", JOptionPane.DEFAULT_OPTION);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Wrong Answer", "Error", JOptionPane.ERROR_MESSAGE);         
        }
    }
    private void pasteText(JTextField answerTextField) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(this);

        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String pastedText = (String) contents.getTransferData(DataFlavor.stringFlavor);
                answerTextField.paste(); // This pastes the text into the JTextField
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String openPostFrame() {
        // Create a new JFrame for the post
        JFrame postFrame = new JFrame(" Add Post");
        postFrame.setLayout(new BorderLayout());

        // Create a JLabel for "Enter your PostPanel Statement"
        JLabel label = new JLabel("Enter your Post Statement");
        postFrame.add(label, BorderLayout.NORTH);

        JTextField postTextField = new JTextField();
        postFrame.add(postTextField, BorderLayout.CENTER);

        JButton postButton = new JButton("Post");
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the post action here
                String postText = postTextField.getText();
                // if(MainRunner.comparestring(postText,""))
                //     return ;
                 if (postText.isEmpty()) {
                        // If the comment is empty, do nothing
                        return;
                    }
               
                postFrame.dispose(); // Close the post frame
                // Return the entered text to the caller
                // You can use this text in the calling method or class
                // In a real application, you might want to perform further actions with the postText
                handlePost(postText);
            }
        });
        postFrame.add(postButton, BorderLayout.SOUTH);
        postFrame.setSize(300, 150);
        postFrame.setLocationRelativeTo(null); // Center on the screen
        postFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        postFrame.setVisible(true);

        // Return an empty string by default
        return "";
    }
    private  void handlePost(String postText) {
        // Do something with the entered text
        System.out.println("Handling post: " + postText);
        // if(MainRunner.comparestring(postText,""))
        //     return ;
        Comment ee ;
        List<Comment> comment = new ArrayList<>();
        // comment.add(ee);
        PostPanel newPost = new PostPanel(Username, postText , comment , null);
        ObjectId postIdd = db.InsertPostdatainDB(newPost);
        newPost.postId = postIdd ;
        posts.add(newPost);
        updatePosts();

    }

    public class PostPanel {
        public String PostByUsername;
        public String PostStatement;
        public List<Comment> comments;
        public JPanel postPanel;
        ObjectId postId ;

        public PostPanel(String author, String content ,List<Comment> comment , ObjectId postId_   ) {
            this.PostByUsername = author;
            this.PostStatement = content;
            this.comments = comment ;
            this.postId = postId_ ;
            this.postPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));

            
            JTextField commentField = new JTextField(1);
            JButton commentButton = new JButton("Add Comment");
            commentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comment = commentField.getText();
                    if (comment.isEmpty()) {
                        // If the comment is empty, do nothing
                        return;
                    }
                    Comment ee = new Comment(Username,comment) ;
                    ee.comment = comment ;
                     comments.add(ee);
                  db.AddCommentInDB(postId, ee);
                    commentField.setText("");
                    updatePostText();
                }
            });

            // postPanel = new JPanel(;
    
            // Create labels with left-aligned text
            JLabel authorLabel = new JLabel("[ "+ PostByUsername + " ] ");
            authorLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(authorLabel);
        
            JLabel contentLabel = new JLabel("Statement : " + PostStatement);
            contentLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(contentLabel);
        
            JLabel commentsLabel = new JLabel("Comments:\n");
            commentsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Add padding to the bottom
            commentsLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(commentsLabel);
        
            for (int i = 0; i < comments.size(); i++) {
                JLabel commentLabel = new JLabel(comments.get(i).GetComment());
                commentLabel.setHorizontalAlignment(JLabel.LEFT);
                postPanel.add(commentLabel);
            }
        
            // Create commentPanel with FlowLayout
            JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            postPanel.add(commentField);
            postPanel.add(commentButton);
        
            // Add the commentPanel to postPanel
            postPanel.add(commentPanel);
        
            // Set background color for postPanel
            // postPanel.setBackground(Color.GREEN);
        
            // Add the postPanel to the postsPanel
            // postsPanel.add(postPanel);
        
            JPanel seperatorPanel = new JPanel();
             seperatorPanel.setBackground(Color.BLACK);
            postPanel.add(seperatorPanel);
           

            // postPanel.setBackground(Color.);
            // Add the postPanel to the postsPanel
            postsPanel.add(postPanel);
        }

        private void updatePostText() {
            postPanel.removeAll();
             JTextField commentField = new JTextField(1);
            JButton commentButton = new JButton("Add Comment");
            commentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comment = commentField.getText();
                    if (comment.isEmpty()) {
                        // If the comment is empty, do nothing
                        return;
                    }
                    Comment ee = new Comment(Username,comment) ;
                    ee.comment = comment ;
                     comments.add(ee);
                  db.AddCommentInDB(postId, ee);
                    commentField.setText("");
                    updatePostText();
                }
            });

             // Create labels with left-aligned text
            JLabel authorLabel = new JLabel("[ "+ PostByUsername + " ] ");
            authorLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(authorLabel);
        
            JLabel contentLabel = new JLabel("Statement : " + PostStatement);
            contentLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(contentLabel);
        
            JLabel commentsLabel = new JLabel("Comments:\n");
            commentsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Add padding to the bottom
            commentsLabel.setHorizontalAlignment(JLabel.LEFT);
            postPanel.add(commentsLabel);
        
            for (int i = 0; i < comments.size(); i++) {
                JLabel commentLabel = new JLabel(comments.get(i).GetComment());
                commentLabel.setHorizontalAlignment(JLabel.LEFT);
                postPanel.add(commentLabel);
            }
        
            // Create commentPanel with FlowLayout
            JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            postPanel.add(commentField);
            postPanel.add(commentButton);
        
            // Add the commentPanel to postPanel
            postPanel.add(commentPanel);
        
            // Set background color for postPanel
            // postPanel.setBackground(Color.GREEN);
        
            // Add the postPanel to the postsPanel
            // postsPanel.add(postPanel);
        
            JPanel seperatorPanel = new JPanel();
             seperatorPanel.setBackground(Color.BLACK);
            postPanel.add(seperatorPanel);
           
            // Refresh the UI
            validate();
            repaint();
        }

        public JPanel getPostPanel() {
            return postPanel;
        }
       
       
    

    
}
public static class Comment{
            public String ByUsername ; 
            public String comment ;
            public Comment()
            {
                ByUsername = null;
                comment = null ;
            }
            public Comment(String ByUsername_ , String comment_)
            {
                ByUsername = ByUsername_;
                comment = comment_ ;
            }

            public String GetComment()
            {
                    return "       [ " + ByUsername + " ] : " +  comment + "\n";
            }

    }
}
class LeaderboardWindow extends JFrame {
    private JTable leaderboardTable;

    public LeaderboardWindow(CompetitionLeaderBoard leaderboard) {
        super("Competition Leaderboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initializeTable(leaderboard);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeTable(CompetitionLeaderBoard leaderboard) {
        // Get data from the CompetitionLeaderBoard
        List<Leaderboard.Point> pointsList = leaderboard.points;

        // Create a table model with column names and no data initially
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Username", "Points"}, 0);

        // Add data to the table model
        for (Leaderboard.Point point : pointsList) {
            model.addRow(new Object[]{point.Username, point.points});
        }

        // Create the leaderboard table with the table model
        leaderboardTable = new JTable(model);

        // Set column alignment to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        leaderboardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        leaderboardTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Set row height for better visibility
        leaderboardTable.setRowHeight(30);

        // Make the table non-editable
        leaderboardTable.setDefaultEditor(Object.class, null);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);
    }

    
}