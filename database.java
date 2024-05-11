/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mongodb.client.model.Filters;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.mavenproject1.FrontendGUI.Comment;
import com.mycompany.mavenproject1.FrontendGUI.PostPanel;
import com.mycompany.mavenproject1.Leaderboard.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author apple
 */

public class database {
  public MongoDatabase database;
  
  static database obj = new database();

  public CompetitionLeaderBoard getAllPoints(ObjectId object) {
    MongoCollection<Document> collection = database.getCollection("CompetitionData");

    // Create a query to find the competition with the given ObjectId
    Document query = new Document("_id", object);

    // Execute the find query
    FindIterable<Document> result = collection.find(query);

    // Get the first matching document
    Document competitionDocument = result.first();
    CompetitionLeaderBoard comp = new CompetitionLeaderBoard();

    // Extract the "points" array
    List<Document> pointsDocuments = competitionDocument.get("points", List.class);

    // Sort the "points" array based on the "points" field in descending order
    if (pointsDocuments != null) {
        pointsDocuments.sort((a, b) -> Integer.compare(b.getInteger("points"), a.getInteger("points")));

        // Convert sorted documents to Point objects
        for (Document pointsDocument : pointsDocuments) {
            String username = pointsDocument.getString("Username");
            int points = pointsDocument.getInteger("points");
            // Point point = new Point(username, points);
            comp.AddUser(username, points);
        }
    }

  return comp;
    // return pointsList != null ? pointsList : new ArrayList<>();
}

  
  
  public void insertuserinthecompleaderboard(ObjectId competitionId,String username) 
  {
    MongoCollection<Document> collection = database.getCollection("CompetitionData");
    Document query = new Document("_id", competitionId);

    // Document query = new Document("_id", id);
    // Document query = new Document("_id", competitionId).append("points.Username", new Document("$ne", username));

    // Create an update document to add the username to the "points" array
    Document updateDocument = new Document("$addToSet", new Document("points", new Document("Username", username).append("points", 0)));

    // Execute the update query
    UpdateResult updateResult = collection.updateOne(query, updateDocument);

    if (updateResult.getModifiedCount() > 0) {
        System.out.println("User " + username + " added to points in competition leaderboard with ObjectId: " + competitionId);
    } else {
        System.out.println("User " + username + " already exists in the competition leaderboard with ObjectId: " + competitionId);
    }
    

    // Create an update document to add the username to the "points" array
    // Document updateDocument = new Document("$addToSet", new Document("points", new Document("Username", username).append("points", 0)));

    // // Execute the update query
    // collection.updateOne(query, updateDocument);

    // System.out.println("User " + username + " added to points in competition leaderboard with ObjectId: " + competitionId);

    
    // MongoCollection<Document> collection = database.getCollection("competitions");
    
    
    
  }
//   private void deleteSpecifiedUserWithZeroPoints(ObjectId competitionId, String username) {
//     MongoCollection<Document> collection = database.getCollection("CompetitionData");

//     // Create a query to find the competition with the given ObjectId and the specified username with zero points
//     Document query = new Document("_id", competitionId)
//             .append("points", new Document("$elemMatch", new Document("Username", username).append("points", 0)));

//     // Create an update document to pull the specified user with zero points from the "points" array
//     Document pullDocument = new Document("$pull", new Document("points", new Document("Username", username).append("points", 0)));

//     // Execute the pull query
//     UpdateResult pullResult = collection.updateOne(query, pullDocument);

//     // Log the pull result for debugging
//     System.out.println("Pull Result: " + pullResult);

//     if (pullResult.getModifiedCount() > 0) {
//         System.out.println("User " + username + " with zero points removed from competition leaderboard with ObjectId: " + competitionId);
//     } else {
//         System.out.println("No user " + username + " with zero points found in competition leaderboard with ObjectId: " + competitionId);
//     }
// }
  public void updateUserPoints(ObjectId competitionId, String username) {
        MongoCollection<Document> collection = database.getCollection("CompetitionData");

        // Create a query to find the competition with the given ObjectId
        Document query = new Document("_id", competitionId).append("points.Username", username);

        // Create an update document to increment the points of the specified user by 1
        Document updateDocument = new Document("$inc", new Document("points.$.points", 1));

        // Execute the update query
        collection.updateOne(query, updateDocument);

        System.out.println("Points for user " + username + " incremented in competition leaderboard with ObjectId: " + competitionId);
        // deleteSpecifiedUserWithZeroPoints(competitionId,username);
    }

  // public List<Point> GetAllPoints()
  public void updatePoints(String Username) 
  {
    MongoCollection<Document> collection = database.getCollection("GlobalLeaderboardData");
  
    // Check if the document with the given username exists
    Document existingDocument = collection.find(Filters.eq("Username", Username)).first();
  
    if (existingDocument != null) {
        // If the document exists, increment the points by one
        collection.updateOne(Filters.eq("Username", Username), Updates.inc("Points", 1));
        System.out.println("Points incremented in MongoDB for user: " + Username);
    } else {
        // If the document does not exist, add a new entry with 1 points
        Document newDocument = new Document("Username", Username).append("Points", 1);
        collection.insertOne(newDocument);
        System.out.println("New entry added to MongoDB for user: " + Username);
    }
  
    
  }
  public List<Point> GetAllPoints()
  {
    List<Point> pointsList = new ArrayList<>();
  
        MongoCollection<Document> collection = database.getCollection("GlobalLeaderboardData");
  
        Document sortCriteria = new Document("Points", -1); // -1 for descending order
  
        // Retrieve the sorted data
        // List<Point> pointsList = new ArrayList<>();
        collection.find().sort(sortCriteria).forEach(document -> {
            String username = document.getString("Username");
            int points = document.getInteger("Points");
            Point point = new Point(username, points);
            pointsList.add(point);
        });
  
     return pointsList;
  }
    

    public List<Competition> getCompetitionsforParticipant(String usernameToSearch) {
      MongoCollection<Document> competitionsCollection = database.getCollection("CompetitionData");
      List<Competition> competitions = new ArrayList<>();

      Document filter = new Document("Participants", usernameToSearch);

      // Retrieve documents based on the filter
      
      // Retrieve documents based on the filter
      List<Document> competitionDocuments =  competitionsCollection.find(filter).into(new ArrayList<>());

      // Process the retrieved documents and convert them to Competition objects
      for (Document competitionDocument : competitionDocuments) {
          Competition competition = convertDocumentToCompetition(competitionDocument);
          competitions.add(competition);
      }

      return competitions;
  }


    public void addParticipantToCompetitionInDB(Object competitionId, String username) {
      MongoCollection<Document> competitionsCollection = database.getCollection("CompetitionData");

        // Convert competitionId to ObjectId (assuming it's a String)
        

        // Create a filter to find the competition by its unique identifier
        Bson filter = Filters.eq("_id", competitionId);

        // Create an update to add the participant to the Participants array
        Bson update = Updates.addToSet("Participants", username);

        // Update the competition document in the database
        competitionsCollection.updateOne(filter, update);
    }

    // MongoCollection<Document> collection = database.getCollection("CompetitionData");
      // Convert the Competition object to a Document

    public List<Competition> RetrieveOwnCompetitions(String Username) {
      MongoCollection<Document> collection = database.getCollection("CompetitionData");

      List<Competition> competitions = new ArrayList<>();
 
      // Retrieve all documents from the collection
      FindIterable<Document> competitionDocuments = collection.find();

      // Iterate through the documents
      for (Document competitionDocument : competitionDocuments) {
          // Convert each document to a Competition object
          Competition competition = convertDocumentToCompetition(competitionDocument);
          competition.Competitionid = competitionDocument.getObjectId("_id");
        //  System.out.println("1111111");

          if(MainRunner.comparestring(competition.CreatedByUsername,Username)|| MainRunner.comparestring("All",Username))
              competitions.add(competition);
      }

      return competitions;
  }

  private Competition convertDocumentToCompetition(Document competitionDocument) {
      Competition competition = new Competition();

      // Extract fields from the document and set them in the Competition object
      competition.CreatedByUsername = competitionDocument.getString("CreatedByUsername");
      competition.NameofCompetition = competitionDocument.getString("NameofCompetition");
      competition.Participants = (List<String>) competitionDocument.get("Participants");
      competition.date = competitionDocument.getString("date");
      competition.Startingtime = competitionDocument.getString("Startingtime");
      competition.Duration = competitionDocument.getString("Duration");
      competition.EvaluationType = competitionDocument.getBoolean("EvaluationType");
      competition.Competitionid = competitionDocument.getObjectId("_id");

      // Extract and convert the quiz field
      
      Document quizDocument = (Document) competitionDocument.get("quiz");
      if (quizDocument != null) {
          competition.quiz.Questioncount = quizDocument.getInteger("Questioncount");
          competition.quiz.Questions = (CompetitionQuestion[]) convertQuestionDocumentsToCompetitionQuestions(
                  (List<Document>) quizDocument.get("Questions"),competition.quiz.Questioncount);
      }

      return competition;
  }

  private CompetitionQuestion[] convertQuestionDocumentsToCompetitionQuestions(List<Document> questionDocuments , int Qcount) {
      CompetitionQuestion[] questions = new CompetitionQuestion[3];


      for (int i = 0 ; i < Qcount ; i++) {
          // Convert each document to a CompetitionQuestion object
          CompetitionQuestion question = new CompetitionQuestion();
         
          
          Document questionDocument = questionDocuments.get(i);
          question.QuestionStatement = questionDocument.getString("QuestionStatement");
          question.Output = questionDocument.getString("Output");
          question.Topic = questionDocument.getString("Topic");
           question.SampleExample = questionDocument.getString("SampleExample");
           question.QuestionType = questionDocument.getBoolean("QuestionType");
          // Add other fields as needed
         

          questions[i] = question;
      }

      return questions;
  }


    public void uploadCompetition(Competition competition) {
      MongoCollection<Document> collection = database.getCollection("CompetitionData");

      List<String> usernames = Arrays.asList();

      // Convert the Competition object to a Document
      
      Document competitionDocument = new Document()
              .append("CreatedByUsername", competition.CreatedByUsername)
              .append("NameofCompetition", competition.NameofCompetition)
              .append("Participants", usernames)
              .append("date", competition.date)
              .append("Startingtime", competition.Startingtime)
              .append("Duration", competition.Duration)
              .append("EvaluationType", competition.EvaluationType)
              .append("points", List.of())
              .append("quiz", new Document()
              .append("Questioncount", competition.quiz.Questioncount)
              .append("Questions", convertQuestionsToDocument(competition.quiz.Questions)));


      // Insert the document into the collection
      collection.insertOne(competitionDocument);
  }

  private List<Document> convertQuestionsToDocument(CompetitionQuestion[] questions) {
      List<Document> questionDocuments = new ArrayList<>();
      for (CompetitionQuestion question : questions) {
          // Convert each CompetitionQuestion to a Document
          Document questionDocument = new Document()
                  .append("QuestionStatement", question.QuestionStatement) // Replace with actual fields in CompetitionQuestion
                  .append("Output", question.Output)
                  .append("SampleExample", question.SampleExample)
                  .append("Topic", question.Topic)
                  .append("QuestionType", question.QuestionType);
                  // .append("fieldN", question.fieldN);


          questionDocuments.add(questionDocument);
      }
      return questionDocuments;
  }

    
    public  void addNewPracticeQuestion(PracticeQuestion newQuestion) {
     MongoCollection<Document> collection = database.getCollection("PracticeQuestionData");
      // Create a new document for the question
      Document questionDocument = new Document()
              .append("CreatedByUsername", newQuestion.CreatedByUsername)
              .append("QuestionStatement", newQuestion.QuestionStatement)
              .append("Output", newQuestion.Output)
              .append("SampleExample", newQuestion.SampleExample)
              .append("QuestionType", newQuestion.QuestionType)
              .append("Topic", newQuestion.Topic)
              .append("AttemptedByUsername", List.of());

      // Insert the document into the collection
      collection.insertOne(questionDocument);
      ObjectId insertedId = questionDocument.getObjectId("_id");

      System.out.println("New practice question added with id " + newQuestion.QuestionId);
  }


    public void AddUserToQuestionAttemptedByList(ObjectId Questionid,String Username)
    {
      // String questionId = QuestionStatement; // replace with the actual method to get the question ID

     System.out.println(Questionid);
      // Access the collection
      MongoCollection<Document> collection = database.getCollection("PracticeQuestionData");
  
        Bson query = Filters.eq("_id", Questionid);

        // Update the document by pushing the entire comment object into the "comments" array
        Bson update = Updates.push("AttemptedByUsername", Username);

        // Update the document in the collection
        UpdateResult updateResult = collection.updateOne(query, update);

     
      
    }

    public List<PracticeQuestion> getPracticeQuestions(String username)
    {

        MongoCollection<Document> collection = database.getCollection("PracticeQuestionData");
        FindIterable<Document> documents = collection.find();
        List<PracticeQuestion> programmingQuestions = new ArrayList<>();
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();

        
                PracticeQuestion question = new PracticeQuestion(
                        document.getString("QuestionStatement"),
                        document.getString("Output"),
                        document.getString("SampleExample"),
                        document.getBoolean("QuestionType"),
                        document.getString("Topic"),
                        document.getString("CreatedByUsername"),
                        document.getList("AttemptedByUsername", String.class)
                );
               question.QuestionId = document.getObjectId("_id");

                // question.QuestionId = document;
                List<String> attemptedUsernames = document.getList("AttemptedByUsername", String.class);

               
                  if (attemptedUsernames != null && attemptedUsernames.contains(username)) {
                      // The username exists in the list
                      System.out.println("Username exists in the AttemptedByUsername list.");
                  } else {
                      // The username does not exist in the list
                      System.out.println("Username does not exist in the AttemptedByUsername list.");
                      programmingQuestions.add(question);
                  }
                  

                // Add the PracticeQuestion to the list
                
            }
        // }
      return programmingQuestions ;
    
    }

    

    void AddCommentInDB(ObjectId id , Comment comment)
    {
            MongoCollection<Document> collection = database.getCollection("Postdata");

          // Convert the String id to ObjectId

        // Create a query to find the document with the specified id
        Bson query = Filters.eq("_id", id);

        // Update the document by pushing the entire comment object into the "comments" array
        Bson update = Updates.push("comments", comment);

        // Update the document in the collection
        UpdateResult updateResult = collection.updateOne(query, update);

            System.out.println("Comment added to the document with ID: " + id);
        
    }
    

    public ObjectId InsertPostdatainDB( PostPanel newPost)
    {
        MongoCollection<Document> collection = database.getCollection("Postdata");

        // Create a new Post document
        Document postDocument = new Document();
        postDocument.append("comments", (List<Comment>) newPost.comments);
        postDocument.append("postdata", newPost.PostStatement);
        postDocument.append("username", newPost.PostByUsername);

        // Insert the document into the collection
        collection.insertOne(postDocument);

        // Retrieve the _id of the inserted document
        ObjectId insertedId = postDocument.getObjectId("_id");
        return insertedId ;



    }
    public List<Post> getAllPosts() {
        List<Post> postList = new ArrayList<>();
        
      MongoCollection<Document> collection =database.getCollection("Postdata");

      MongoCursor<Document> cursor = collection.find().iterator();
     
      while (cursor.hasNext()) {

        Document currentDocument = cursor.next();

        String username = currentDocument.getString("username");
        String postdata = currentDocument.getString("postdata");
        List<Document> commentsDocuments = currentDocument.getList("comments", Document.class);
        ObjectId _id = currentDocument.getObjectId("_id");

        List<Comment> comments = new ArrayList<>();
        for (Document commentDocument : commentsDocuments) {
            Comment comment = new Comment();
            comment.comment = commentDocument.getString("comment");
              comment.ByUsername = commentDocument.getString("ByUsername");
          
            // Set other properties of Comment class as needed
            comments.add(comment);
        }

        Post currentPost = new Post();
        currentPost.username = username;
        currentPost.postdata = postdata;
        currentPost.comments = comments;
        currentPost._id = _id;
        postList.add(currentPost);
      //  System.out.printf("%s has %d ingredients and takes %d minutes to make\n");
     }

      // Convert the List<Post> to a Post[]
      return postList;
  }


    public void insertuserdataindb(Userdata data)
    {
        MongoCollection<Userdata> collection = database.getCollection("Userdata", Userdata.class);
        collection.insertOne(data);
    }
    
    public static database getInstance()
    {
        return obj;
    }
            
    
    public Userdata isHavingUserData(String username)
    {
        MongoCollection<Userdata> collection = database.getCollection("Userdata", Userdata.class);
//        FindIterable<Userdata> findIterable = ;

// Check if any documents were found
//        MongoCursor<Userdata> cursor = findIterable.iterator();
// System.out.println("The value in jTextField1 is: " + username);

    Bson finduser = Filters.eq("username", username);
    Userdata user = collection.find(finduser).first();
    return user;

    }
 
    
    private database()
    {
        start();
    }
    
    public void start() 
    {
        // Start code here, similar to Start() in Unity
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.WARNING);
    // TODO:
    //  Replace the placeholder connection string below with your
    // Altas cluster specifics. Be sure it includes
    // a valid username and password! Note that in a production environment,
    // you do not want to store your password in plain-text here.
    ConnectionString mongoUri = new ConnectionString("mongodb+srv://admin:admin@mongodb.wos6mf4.mongodb.net/");

    // Provide the name of the database and collection you want to use.
    // If they don't already exist, the driver and Atlas will create them
    // automatically when you first write data.
    String dbName = "CODEFESTDB";
    String collectionName = "Userdata";

    // a CodecRegistry tells the Driver how to move data between Java POJOs (Plain Old Java Objects) and MongoDB documents
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    // The MongoClient defines the connection to our MongoDB datastore instance (Atlas) using MongoClientSettings
    // You can create a MongoClientSettings with a Builder to configure codecRegistries, connection strings, and more
    MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(mongoUri).build();

    MongoClient mongoClient = null;
    try {
       mongoClient = MongoClients.create(settings);
    } catch (MongoException me) {
      System.err.println("Unable to connect to the MongoDB instance due to an error: " + me);
      System.exit(1);
    }

    // MongoDatabase defines a connection to a specific MongoDB database
     database = mongoClient.getDatabase(dbName);
    // MongoCollection defines a connection to a specific collection of documents in a specific database
    MongoCollection<Userdata> collection = database.getCollection(collectionName, Userdata.class);
    
   
    }


  
//  public static void main(String[] args) 
//  {
    
//    start();
     //    frame.displayoff();

        // Convert the JSON to a BSON Document and insert it into MongoDB


    /*      *** INSERT DOCUMENTS ***
     *
     * You can insert individual documents using collection.insert().
     * In this example, we're going to create 4 documents and then
     * insert them all in one call with insertMany().
     */

//    try {
//      // recipes is a static variable defined above
//      InsertManyResult result = collection.insertMany(recipes);
//      System.out.println("Inserted " + result.getInsertedIds().size() + " documents.\n");
//    } catch (MongoException me) {
//      System.err.println("Unable to insert any recipes into MongoDB due to an error: " + me);
//      System.exit(1);
//    }

    /*      *** FIND DOCUMENTS ***
     *
     * Now that we have data in Atlas, we can read it. To retrieve all of
     * the data in a collection, we call find() with an empty filter. We can
     * retrieve an iterator to return the results from our call to the find()
     * method. Here we use the try-with-resources pattern to automatically
     * close the cursor once we finish reading the recipes.
     */

//    try (MongoCursor<Recipe> cursor = collection.find().iterator()) {
//      while (cursor.hasNext()) {
//        Recipe currentRecipe = cursor.next();
//        System.out.printf("%s has %d ingredients and takes %d minutes to make\n", currentRecipe.getName(), currentRecipe.getIngredients().size(), currentRecipe.getPrepTimeInMinutes());
//      }
//    } catch (MongoException me) {
//      System.err.println("Unable to find any recipes in MongoDB due to an error: " + me);
//    }

    // We can also find a single document. Let's find the first document
    // that has the string "potato" in the ingredients list. We
    // use the Filters.eq() method to search for any values in any
    // ingredients list that match the string "potato":

//    Bson findPotato = Filters.eq("ingredients", "potato");
//    try {
//      Recipe firstPotato = collection.find(findPotato).first();
//      if (firstPotato == null) {
//        System.out.println("Couldn't find any recipes containing 'potato' as an ingredient in MongoDB.");
//        System.exit(1);
//      }
//    } catch (MongoException me) {
//      System.err.println("Unable to find a recipe to update in MongoDB due to an error: " + me);
//      System.exit(1);
//    }

    /*      *** UPDATE A DOCUMENT ***
     *
     * You can update a single document or multiple documents in a single call.
     *
     * Here we update the PrepTimeInMinutes value on the document we
     * just found.
     */
//    Bson updateFilter = Updates.set("prepTimeInMinutes", 72);
//
//    // The following FindOneAndUpdateOptions specify that we want it to return
//    // the *updated* document to us. By default, we get the document as it was *before*
//    // the update.
//    FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
//
//    // The updatedDocument object is a Recipe object that reflects the
//    // changes we just made.
//    try {
//      Recipe updatedDocument = collection.findOneAndUpdate(findPotato,
//              updateFilter, options);
//      if (updatedDocument == null) {
//        System.out.println("Couldn't update the recipe. Did someone (or something) delete it?");
//      } else {
//        System.out.println("\nUpdated the recipe to: " + updatedDocument);
//      }
//    } catch (MongoException me) {
//      System.err.println("Unable to update any recipes due to an error: " + me);
//    }

    /*      *** DELETE DOCUMENTS ***
     *
     *      As with other CRUD methods, you can delete a single document
     *      or all documents that match a specified filter. To delete all
     *      of the documents in a collection, pass an empty filter to
     *      the deleteMany() method. In this example, we'll delete 2 of
     *      the recipes.
     */
//    Bson deleteFilter = Filters.in("name", Arrays.asList("elotes", "fried rice"));
//    try {
//      DeleteResult deleteResult = collection
//              .deleteMany(deleteFilter);
//      System.out.printf("\nDeleted %d documents.\n", deleteResult.getDeletedCount());
//    } catch (MongoException me) {
//      System.err.println("Unable to delete any recipes due to an error: " + me);
//    }
//
//    // always close the connection when done working with the client
//    mongoClient.close();
//  }
    public static class Userdata 
    {
    public String name;
    public String username;
    public String role;   
    public String email;
    public String password;
    
    public Userdata()
    {
        username = null ;
    }
    
    public Userdata(String name,String username,String role , String email , String password) 
    {
      this.name = name;
      this.username = username;
      this.role = role;
      this.email = email;
      this.password = password;

      
    }

    // empty constructor required when we fetch data from the database -- getters and setters are later used to
    // set values for member variables
    

    

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    
  }


    public static class Post 
    { 
       public String username;
       public String postdata;
       public List<Comment> comments;
       public ObjectId _id ;
       
       public Post()
       {
           username = null ;
           postdata = null ;
           comments = null ;
           _id = null ;

       }
       public Post(String author, String content ,List<Comment> comment , ObjectId id )
       {
           username = author ;
           postdata = content ;
           comments = comment ;
           _id = id ;

       }
    }


    

    
}


