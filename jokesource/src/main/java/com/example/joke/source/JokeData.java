package com.example.joke.source;

public class JokeData {

    private String[] mJokes;

    public JokeData() {
        mJokes = new String[]{"Joke 1", "Joke 2", "Joke 3", "Joke 4", "Joke 5", "Joke 6", "Joke 7", "Joke 8"};
    }

    public String getJoke() {
        int index = (int) (Math.random()*mJokes.length);
        return mJokes[index];
    }

}
