package com.google.engedu.ghost;

import android.text.TextUtils;
import android.util.Log;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {

        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if (TextUtils.isEmpty(prefix)){
            int randomIndex= new Random().nextInt(words.size());
            return words.get(randomIndex);
        }
        else {
            int start=0;
            int end=words.size()-1;
            int mid=(start+end)/2;
            int i=0;
            while (start<=end){
                i++;
                Log.e("h",i+"");
                if (words.get(start).startsWith(prefix))
                    return words.get(start);
                else if (words.get(mid).startsWith(prefix))
                    return words.get(mid);
                else if (words.get(end).startsWith(prefix))
                    return words.get(end);
                if (words.get(mid).compareToIgnoreCase(prefix)>0){
                    end=mid-1;
                    mid=(start+end)/2;
                    Log.e("end mid",end+" "+mid);
                }else {
                    start=mid+1;
                    mid=(start+end)/2;
                    Log.e("mid start",mid+" "+start);
                }
            }
            return null;
        }

    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}
