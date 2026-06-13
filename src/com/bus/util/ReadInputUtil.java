package com.bus.util;

import java.util.Scanner;

public class ReadInputUtil {
    
    public static int readInt(String prompt, Scanner sc){
        while(true){
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid menu option!");
                continue;
            }
        }
    }
}
