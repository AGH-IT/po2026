package agh.po.lab1;

import java.util.Scanner;

class Tree {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = 0;
		
		for (String str: args){
			n = Integer.parseInt(str);
		}
		if (n == 0) {
			System.out.println("Enter number of levels: ");
			n = scanner.nextInt();
		}
		
		//System.out.println(n);

		for(int i = 0; i <= n; i++) {
			for(int asterix = 0; asterix < i; asterix++) {
				System.out.print("*");
			}
		System.out.println();
		}
	System.out.println(" ");
	}
}
