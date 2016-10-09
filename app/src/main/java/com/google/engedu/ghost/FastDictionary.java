package com.google.engedu.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FastDictionary implements GhostDictionary {

    private TrieNode root;

    public FastDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        root = new TrieNode();
        String line = null;
        //Log.e("gh","khk");
        while((line = in.readLine()) != null) {
            String word = line.trim();
           // Log.e("a","cd");
            if (word.length() >= MIN_WORD_LENGTH)
                root.add(line.trim());
        }
       // Log.e("root",root.children.keySet().toString()+"");
    }
    @Override
    public boolean isWord(String word) {
        Log.e("im","here");
        return root.isWord(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        return root.getAnyWordStartingWith(prefix);
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return root.getGoodWordStartingWith(prefix);
    }
}
