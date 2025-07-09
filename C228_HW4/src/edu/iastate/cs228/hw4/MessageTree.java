package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageTree {

	public static void main(String[] args) throws FileNotFoundException{
		//Get file name
		System.out.println("Please enter filename to decode:");
		Scanner scnr = new Scanner(System.in);
		String fileName = scnr.nextLine();
		scnr.close();
		
		
		File file = new File(fileName);    
		Scanner scanner = new Scanner(file);
		String temp = scanner.nextLine();
		String temp2 = scanner.nextLine();
		String scheme;
		String compressed;
		
		
		if(scanner.hasNextLine()) {
			scheme = temp + "\n" + temp2;
			
			compressed = scanner.nextLine();
		}
		else {
			scheme = temp;
			compressed = temp2;
		}
		
		scanner.close();
		
		String NOcarrot = scheme.replace("^", "");
		Set<Character> chars = new HashSet<>();
		for (char c : NOcarrot.toCharArray()) {
				chars.add(c);
		}
		
		
		String charMap = chars.stream().map(String::valueOf).collect(Collectors.joining());
		
		
		MsgTree root = new MsgTree(scheme);
		MsgTree.printCodes(root, charMap);
		
		System.out.println("MESSAGE:");
		root.print(root, compressed);
		
	}

}
