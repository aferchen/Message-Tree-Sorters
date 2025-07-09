package edu.iastate.cs228.hw4;

import java.util.Stack;

/**
 * class to make a message tree from already encoded strings
 * 
 * @author Alec Ferchen
 */
public class MsgTree {
		public char payloadChar;
		public MsgTree left;
		public MsgTree right;
		private static String binaryCode;
		
		/*

		//Constructor building the tree from a string
		public MsgTree(String encodingString){}
		DONE
		
		
		//Constructor for a single node with null children
		public MsgTree(char payloadChar){}
		DONE
		
		
		//method to print characters and their binary codes
		public static void printCodes(MsgTree root, String code){}
		WORKING
		*/
		

		//Constructor building the tree from a string
		public MsgTree(String encodingString) {
			if (encodingString == null || encodingString.length() < 2) {
				return;
			}
			Stack<MsgTree> myStack = new Stack<>(); //Use a stack for building trees
			int stackIndex = 0;
			
			MsgTree Mytree = this;
			payloadChar = encodingString.charAt(stackIndex);
			stackIndex += 1;
			myStack.push(Mytree);
			MsgTree current = Mytree;
			String lastOption = "inside";
			
			
			
			while (stackIndex < encodingString.length()) {
				MsgTree node = new MsgTree(encodingString.charAt(stackIndex++));
				if (lastOption.equals("inside")) {
					current.left = node;
					
					if (node.payloadChar == '^') { //Go inside
						current = myStack.push(node);
						lastOption = "inside";
					} else {
						if (!myStack.empty()) { //Go outside
							current = myStack.pop();
						}
						
						lastOption = "outside";
					}
				} else { // last was outside
					current.right = node;
					if (node.payloadChar == '^') { //Go inside
						current = myStack.push(node);
						lastOption = "inside";
					} else {
						if (!myStack.empty()) { //Go outside
							current = myStack.pop();
						}
						
						lastOption = "outside";
					}
				}
			}
		}
		//Constructor for a single node with null children
		public MsgTree(char payloadChar) {
			this.payloadChar = payloadChar;
			this.right = null;
			this.left = null;
		}

		
		

		//Method to print characters and their binary codes
		public static void printCodes(MsgTree root, String code) {
			System.out.println("character code\n-------------------------");
			for (char ch : code.toCharArray()) {
				setLetterPath(root, ch, binaryCode = "");
				System.out.println("    " + (ch == '\n' ? "\\n" : ch + " ") + "    " + binaryCode);
			}
		}

		//prints given the tree and the compressed 
		public void print(MsgTree tree, String msg) {
			MsgTree myTree = tree;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < msg.length(); i++) {
				char ch = msg.charAt(i);
				myTree = (ch == '0' ? myTree.left : myTree.right);
				if (myTree.payloadChar != '^') {
					setLetterPath(tree, myTree.payloadChar, binaryCode = "");
					sb.append(myTree.payloadChar);
					myTree = tree;
				}
			}
			System.out.println(sb.toString());
			statistc(msg.length(), sb.length());
		}
		
		
		
		
		
		
		


		//finds the correct path for the letters
		private static boolean setLetterPath(MsgTree root, char ch, String path) {
			if (root != null) {
				if (root.payloadChar == ch) {
					binaryCode = path;
					return true;
				}
				return setLetterPath(root.left, ch, path + "0") || setLetterPath(root.right, ch, path + "1");
			}
			return false;
		}

		
		//Needs the length of both the encoded charicters and decoded
		private void statistc(int encLen, int decLen) {
			System.out.println("STATISTICS:");
			//TODO Add if time
			//1 – compressedBits/uncompressedBits)*100
			double spaceSavings = (1 - (double) decLen / encLen)*100;
			
			System.out.println("Avg bits/char:       " +  encLen / decLen);
			
			System.out.println("Total characters:    " + decLen);
			System.out.println("Space savings:       " + spaceSavings + "%");
		}
}
