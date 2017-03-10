package com.epam.java.se.task4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieCollection implements Serializable{

    private Map<String, ArrayList<String>> movieActorsMap = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MovieCollection movieCollection = MovieCollection.turnOn();

        movieCollection.addMovie("Interstellar");
        movieCollection.addActorToMovie("Interstellar", "Matthew McConaughey");
        movieCollection.addActorToMovie("Interstellar", "Anne Hathaway");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("test.txt"));
        objectOutputStream.writeObject(movieCollection);

        try {
            movieCollection.turnOff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void turnOff() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("db.txt"));
        objectOutputStream.writeObject(this);
        System.out.println("off");
    }

    private void addActorToMovie(String movieName, String actorName) {
        ArrayList newList = movieActorsMap.get(movieName);
        newList.add(actorName);
        movieActorsMap.put(movieName, newList);
    }

    private void addMovie(String movieName) {
        movieActorsMap.put(movieName, new ArrayList<>());
    }

    private static MovieCollection turnOn() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("db.txt"));
        MovieCollection newMovieCollection = (MovieCollection) objectInputStream.readObject();

        System.out.println("on");
        return newMovieCollection;
    }
}
