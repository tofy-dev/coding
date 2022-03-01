/*
 * Skyler Tabofunda
 * Intro Java
 * Lab 2b- HouseWithInput
 * Period 7
 * 1/20/22
 */
import java.util.Scanner;

public class P7_Tabofunda_Skyler_Driver {
    public static void main(String[] args) {
        P7_Tabofunda_Skyler_House defaultHouse = new P7_Tabofunda_Skyler_House();
        System.out.println(defaultHouse.toString());
        System.out.print("doors> " + defaultHouse.getDoors());
        System.out.print(" bedrooms> " + defaultHouse.getBedrooms());
        System.out.println(" asking price> " + defaultHouse.getPrice());
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of doors? ");
        int numDoors = sc.nextInt();
        System.out.println("Number of windows? ");
        int numWindows = sc.nextInt();
        System.out.println("Number of bedrooms? ");
        int numRooms = sc.nextInt();
        System.out.println("Asking price? ");
        int askingPrice = sc.nextInt();
    
        P7_Tabofunda_Skyler_House userHouse = new P7_Tabofunda_Skyler_House(numDoors, numWindows, numRooms, askingPrice);
        System.out.println(userHouse.toString());
        
        if (userHouse.isBelowMarketValue()) {
            System.out.println("The asking price is below market value.");
        } else {
            System.out.println("The asking price is above market value.");
        }
        
        userHouse.addBedrooms(1);
        
        System.out.println("What is your new asking price? ");
        askingPrice = sc.nextInt();
        userHouse.setPrice(askingPrice);
        
        System.out.println("After adding a bedroom: ");
        System.out.println(userHouse.toString());
        
        if (userHouse.isBelowMarketValue()) {
            System.out.println("The asking price is below market value.");
        } else {
            System.out.println("The asking price is above market value.");
        }
    }
}
