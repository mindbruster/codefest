package com.mycompany.mavenproject1;


import java.util.*;
import org.bson.types.ObjectId;


public class Question {
    public ObjectId QuestionId ;
    public String QuestionStatement;
    public String Output;
    public String SampleExample;
    public boolean QuestionType ;   // false for coding type true for simple question.
    public String Topic ;

    public Question() {
        this.QuestionStatement = "";
        this.Output = "";
        this.SampleExample = "";
        this.QuestionType = false ;
        Topic = null ;
        QuestionId = null ;
    }

    protected Question(String questionStatement, String output, String sampleExample, boolean questionType, String Topic_) {
        this.QuestionStatement = questionStatement;
        this.Output = output;
        this.SampleExample = sampleExample;
        this.QuestionType = questionType;
        Topic = Topic_ ;
        QuestionId = null ;
    }
    
    

    public void AddQuestion()
    {

    }
    
}

class CompetitionQuestion extends Question
{
    public CompetitionQuestion() 
    {
        super(); // This calls the default constructor of the Question class
    }
    // List<String> attemptedByUsername ;
    public boolean AnswerPracticeQuestion(String Answer)
    {
        // System.out.println(QuestionType);
        if(QuestionType == false)
        {
            CheckCode obj = new CheckCode();
            String returnval = obj.ReturnOutput(Answer);
            System.out.println(Output + returnval);
            if(MainRunner.comparestring(returnval, Output))
                return true;
            return false ;
        }
        System.out.println(Output + Answer);
        if(MainRunner.comparestring(Answer.toLowerCase(), Output.toLowerCase()))
                return true;
            return false ;

        
    }

}
class PracticeQuestion extends Question
{
    public String CreatedByUsername ;
    List<String> attemptedByUsername ;



    public PracticeQuestion() {
        super(); // This calls the default constructor of the Question class
        this.CreatedByUsername = "";
        attemptedByUsername = null ;
    }

    public PracticeQuestion(String questionStatement, String output, String sampleExample, boolean questionType , String Topic_, String createdByUsername,List<String> attemptedByUsername_ ) {
        super(questionStatement, output, sampleExample, questionType ,Topic_); // This calls the parameterized constructor of the Question class
        this.CreatedByUsername = createdByUsername;
        attemptedByUsername = attemptedByUsername_ ;
    }

    public boolean AnswerPracticeQuestion(String Answer)
    {
        // System.out.println(QuestionType);
        if(QuestionType == false)
        {
            CheckCode obj = new CheckCode();
            String returnval = obj.ReturnOutput(Answer);
            System.out.println(Output + returnval);
            if(MainRunner.comparestring(returnval, Output))
                return true;
            return false ;
        }
        System.out.println(Output + Answer);
        if(MainRunner.comparestring(Answer.toLowerCase(), Output.toLowerCase()))
                return true;
            return false ;

        
    }

     public String getQuestionStatement() {
            return QuestionStatement;
        }

        public String getOutput() {
            return Output;
        }

        public String getSampleExample() {
            return SampleExample;
        }

        public boolean isQuestionType() {
            return QuestionType;
        }

        public String getTopic() {
            return Topic;
        }

        public String getCreatedByUsername() {
            return CreatedByUsername;
        }

        public List<String> getAttemptedByUsername() {
            return attemptedByUsername;
        }
    
}


