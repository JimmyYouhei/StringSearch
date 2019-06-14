package com.jimmyyouhei.string_search;

public class Main {

    public static void main(String[] args) {
    	/*
		String test = "I aam very Haappy";
		String test2 = "aa";

		StringSearching.naiveSearch(test2,test);

		System.out.println("end");


		*/

    	String paragram = "WE HOLD THESE TRUTHS TO BE SELF-ECIDENT";
    	String find = "TRUTH";

    	StringSearching.boyerMooreHorspool(find , paragram);
    	StringSearching.naiveSearch(find,paragram);

		System.out.println("end");

    }
}
