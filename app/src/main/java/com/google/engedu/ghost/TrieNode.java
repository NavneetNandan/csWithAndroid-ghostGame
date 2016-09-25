package com.google.engedu.ghost;

import android.util.Log;

import java.util.HashMap;


public class TrieNode {
     HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {

        children = new HashMap<>();
        isWord = false;
    }
/*
    public void add(String s) {
        Log.e("in","add");
        if (children.containsKey(s.charAt(0))) {
            children.get(s.charAt(0)).add(s.substring(1,s.length()));
        }
        else {
            Log.e("in", "else");
            TrieNode temp=new TrieNode();
            temp.isWord=true;
            if (s.length()==1){
                children.put(s.charAt(0),temp);
                isWord=true;
            }
            else {
                Log.e("su",s.charAt(0)+"");
                temp.add(s.substring(1, s.length()));
                children.put(s.charAt(0), temp);
            }
        }
    }*/
    public void add(String s){
        HashMap<Character, TrieNode> tempMap=children;
        for (int i=0;i<s.length();i++){
            if(tempMap.containsKey(s.charAt(i)))
                tempMap=tempMap.get(s.charAt(i)).children;
            else {
                TrieNode temp=new TrieNode();
                if(i==s.length()-1)
                    temp.isWord=true;
                tempMap.put(s.charAt(i),temp);
                tempMap=tempMap.get(s.charAt(i)).children;
            }
        }
    }
    public boolean isWord(String s) {
        if (s.length()==1&&children.containsKey(s.charAt(0))){
            return children.get(s.charAt(0)).isWord;
        }else 
        if (children.containsKey(s.charAt(0))){
            Log.e("s",s.charAt(0)+"");
            return children.get(s.charAt(0)).isWord(s.substring(1,s.length()));
        }
        else
            return false;
    }

    public String getAnyWordStartingWith(String s) {
        String result=new String();
        HashMap<Character,TrieNode> tempMap=children;
        for (int i = 0; i <s.length() ; i++) {
            if (tempMap.containsKey(s.charAt(i))){
                tempMap=tempMap.get(s.charAt(i)).children;
            }else {
                return null;
            }
        }

        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
