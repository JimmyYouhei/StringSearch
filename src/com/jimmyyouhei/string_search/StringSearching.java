package com.jimmyyouhei.string_search;

import java.util.HashMap;

public interface StringSearching {

    // the naive search algorithm
    static void naiveSearch (String toFind , String toSearchFrom){
        // int var to store the number of match found
        int match=0;

        // loop through each sub string to compare with the string to find
        for (int i = 0 ; i < toSearchFrom.length() - toFind.length() ; i++){
            // int var to store character match
            int matchCount = 0 ;

            // if the first left most char is matched
            if (toSearchFrom.charAt(i) == toFind.charAt(0)){
                matchCount++;

                // then compare all other character in the sub string
                for (int j = 1 ; j < toFind.length() ; j++ ){
                    if (toSearchFrom.charAt(i+j) == toFind.charAt(j)){
                        matchCount++;
                    } else {
                        break;
                    }

                }

                // if all chrater are matched , print the start index and increase match by 1
                if (matchCount == toFind.length()){
                    System.out.println("find match at the index of" + " " + i);
                    match++;
                }
            }

        }

        // if no match is found print out as no match
        if (match == 0){
            System.out.println("no match");
        }
    }

    // The Boyer Moore Horspool method
    static void boyerMooreHorspool (String toFind , String toSearchFrom){

        // init a bad match table from the string to find
        BadMatchTable badMatchTable = new BadMatchTable(toFind);

        //init a match var to keep trach the match
        int match =0;

        // loop though the substring and compare with the string to find
        for (int i = 0; i < toSearchFrom.length() - toFind.length() ; i++ ){
            // int var to store the number of match char
            int matchCount = 0;
            // loop through the sub string but compare from the right to left
            for(int j = toFind.length()-1 ; j >= 0 ; j--){
                if (toFind.charAt(j) == toSearchFrom.charAt(i+j)){
                    matchCount++;
                    // if one char is mismatch then increase the index based on the bad match table
                    // the -1 at the last is due to the i also auto increase 1 by loop so need - 1 to stop that
                } else {
                    i+= badMatchTable.getValue(toSearchFrom.charAt(i+j))-1;
                    break;
                }
            }

            // if a match is found print out the index and increase match
            if (matchCount == toFind.length()){
                System.out.println("found the string at the index " + i);
                match++;
            }

        }

        // if there is no match print out the result
        if (match ==0){
            System.out.println("no find");
        }

    }

    //class BadMatchTable for the Boyer Moore Horspool method
    class BadMatchTable{

        // the table will contain as the key value pair inside a HashMap
        HashMap<Character , Integer> badMatchTable = new HashMap<>();

        // quick constructor based on the string to find to make hash map based on each char
        BadMatchTable(String string) {

            for (int i = string.length()-1 ; i >=0; i--){

                if (!badMatchTable.containsKey(string.charAt(i))){
                    badMatchTable.put(string.charAt(i), string.length()-i-1);
                }
            }

            //any other char that is not contain in the string will be stored as null key word and having a same length
            badMatchTable.put(null , string.length());
        }

        // return the value based of the character from the Bad Match Table
        // if the char is not in the bad match table return the value of null key
        int getValue(char n){
            if (badMatchTable.containsKey(n)){
                return badMatchTable.get(n);
            } else {
                return badMatchTable.get(null);
            }
        }

    }

}
