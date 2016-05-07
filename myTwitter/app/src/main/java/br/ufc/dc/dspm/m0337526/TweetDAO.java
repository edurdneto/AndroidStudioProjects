package br.ufc.dc.dspm.m0337526;

import java.util.ArrayList;

/**
 * Created by eduardo on 2016-05-03.
 */
public class TweetDAO {
    private ArrayList<Tweet> tweet_array = new ArrayList<>();

    public void add(Tweet tweet){

        tweet_array.add(0,tweet);
    }

    public Tweet read(int id){

        return tweet_array.get(id);
    }

    public void remove(int id){

        tweet_array.remove(id);
    }

    public void update(Tweet tweet){

    }

    public ArrayList<Tweet> list(){
        return tweet_array;
    }

    @Override
    public String toString() {
        return "TweetDAO{" +
                "tweet_array=" + tweet_array +
                '}';
    }
}
