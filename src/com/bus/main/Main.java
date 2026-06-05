package com.bus.main;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println("Enter Option 1 to View buses and 2 to Exit");
            int userInputOption = sc.nextInt();
            if(userInputOption == 1){
                System.out.println("working");
            }
            else{
                on = false;
            }
        }

        sc.close();
    }
}
