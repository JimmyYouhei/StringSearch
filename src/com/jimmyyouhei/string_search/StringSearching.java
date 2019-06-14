package com.jimmyyouhei.string_search;

import java.util.HashMap;
import java.util.HashSet;

public interface StringSearching {

    static void naiveSearch (String toFind , String toSearchFrom){
        int match=0;

        for (int i = 0 ; i < toSearchFrom.length() - toFind.length() ; i++){
            int matchCount = 0 ;

            if (toSearchFrom.charAt(i) == toFind.charAt(0)){
                matchCount++;
                for (int j = 1 ; j < toFind.length() ; j++ ){
                    if (toSearchFrom.charAt(i+j) == toFind.charAt(j)){
                        matchCount++;
                    } else {
                        break;
                    }

                }

                if (matchCount == toFind.length()){
                    System.out.println("find match at the index of" + " " + i);
                    match++;
                }
            }

        }
        if (match == 0){
            System.out.println("no match");
        }
    }

    static void boyerMooreHorspool (String toFind , String toSearchFrom){

        BadMatchTable badMatchTable = new BadMatchTable(toFind);

        int match =0;
        for (int i = 0; i < toSearchFrom.length() - toFind.length() ; i++ ){
            int matchCount = 0;
            for(int j = toFind.length()-1 ; j >= 0 ; j--){
                if (toFind.charAt(j) == toSearchFrom.charAt(i+j)){
                    matchCount++;
                } else {
                    i+= badMatchTable.getValue(toSearchFrom.charAt(i+j))-1;
                    break;
                }
            }

            if (matchCount == toFind.length()){
                System.out.println("found the string at the index " + i);
                match++;
            }

        }

        if (match ==0){
            System.out.println("no find");
        }

    }

    class BadMatchTable{

        HashMap<Character , Integer> badMatchTable = new HashMap<>();

        BadMatchTable(String string) {

            for (int i = string.length()-1 ; i >=0; i--){

                if (!badMatchTable.containsKey(string.charAt(i))){
                    badMatchTable.put(string.charAt(i), string.length()-i-1);
                }
            }

            badMatchTable.put(null , string.length());
        }

        int getValue(char n){
            if (badMatchTable.containsKey(n)){
                return badMatchTable.get(n);
            } else {
                return badMatchTable.get(null);
            }
        }

    }

}
