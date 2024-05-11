package com.mycompany.mavenproject1;

import java.util.*;


public class Leaderboard {

    public List<Point> points;

    public Leaderboard() {
        points = new ArrayList<>();
    }
    public void AddUser(String user , int score)
    {
        Optional<Point> existingUser = points.stream()
        .filter(point -> point.Username.equals(user))
        .findFirst();

// If the user exists, update the score if the new score is higher
existingUser.ifPresentOrElse(
        point -> {
            if (score > point.points) {
                point.points = score;
            }
        },
        // If the user doesn't exist, add a new entry
        () -> points.add(new Point(user, score))
);
    }

    public static class Point{
        public String Username ;
        public int points ;
  
        public Point()
        {
            Username = null ;
            points = 0 ;
        }   
        public Point(String user , int a)
        {
            Username = user ;
            points = a ;
        }   
        public void IncrementPoints()
        {
            points++;
        }
  
    }
}

class GlobalLeaderBoard extends Leaderboard
{

}

class CompetitionLeaderBoard extends Leaderboard
{
    
}
