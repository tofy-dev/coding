public class P7_Tabofunda_Skyler_Driver {
    public static void main(String[] args) {
        P7_Tabofunda_Skyler_Person sparky = new P7_Tabofunda_Skyler_Person(14, 0.12, false, "Happy Sparky", 314, "Green", false);
        
        System.out.println("!Hola! Me llamo " + sparky.getName());
        System.out.println("Mi color favorito es " + sparky.getFavoriteColor());
        System.out.println("Yo tengo " + sparky.ageInDogYears() + " anos en anos de perro!");
        System.out.println("... fast forward 15 years ...");
        sparky.increaseAge(15);
        System.out.println("Ahora, tengo " + sparky.ageInDogYears() + " anos en anos de perro!");
        sparky.printSleepState();
        sparky.sleep();
        sparky.printSleepState();
    }
}