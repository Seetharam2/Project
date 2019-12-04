package com.spring;

import java.util.ArrayList;
import java.util.HashSet;


public class Test {

//	public static void main(String[] args) {
//		ArrayList<String> stringList = new ArrayList<String>();
//		stringList.add("cae");
//		stringList.add("cate");
//		stringList.add("cane");
//		stringList.add("clle");
//		HashSet<Character> charList = new HashSet<Character>();
//		String firstWord = stringList.get(0);
//		System.out.println(firstWord);
//		for(int i=0;i<firstWord.length();i++)
//		{
//			int count = 0;
//			String s=Character.toString(firstWord.charAt(i));
//			for(int j=1;j<stringList.size();j++)
//			{
//				if(stringList.get(j).contains(s))
//				{
//					count++;
//				}
//			}
//			if(count == stringList.size()-1)
//			{
//				charList.add(firstWord.charAt(i));
//			}
//		}
//		System.out.println(charList);
//		
//	}
	
	public static void main(String[] args) {
		String str = "rlrlrrllrll";
		int rcount =0;
		int lcount =0;
		int bal=0;
		int stringlenght = str.length();
		for(int i=0;i<stringlenght;i++)
		{
			if(str.charAt(i)=='r')
			{
				rcount++;
			}
			else
				lcount++;
			if(rcount==lcount)
			{
				bal++;
				rcount =0;
				lcount =0;
			}
		}
		System.out.println(bal);
	
	}

}
