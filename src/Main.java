import java.util.Scanner;
import java.io.IOException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static HabitManager admin = new HabitManager();

    public static void main(String[] args) {
        short option;
        boolean continueProgram;

        do {
            continueProgram = true;
            option = printMenuGetOption();
            switch (option) {
                case 1:
                    addHabit();
                    break;
                case 2:
                    checkHabits();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("\nExiting the program . . .");
                    scanner.close();
                    continueProgram = false;
                    System.out.println("Bye ;P\n");
                    break;
                default:
                    System.out.println("\nERROR: Not a valid option\n");
            }
            if (continueProgram) {
                clearScreen();
            }
        } while (continueProgram);

        scanner.close();
    }

    public static void addHabit() {
        String name;
        clearScreen();
        System.out.println("ADD HABIT\n");
        if (!admin.isFull()) {
            System.out.print("Enter the habit name: ");
            name = scanner.nextLine();
            if (admin.addHabit(name)) {
                System.out.println("\nHabit " + name + " created succesfully!\n");
            } else {
                System.out.println("Error while creating the habit.\n");
            }
            pause();
        }
    }

    public static void checkHabits() {
        short counter;
        short option;
        Habit habit;

        clearScreen();
        System.out.println("CHECK HABITS\n");

        if (!admin.isEmpty()) {
            counter = admin.getCounter();
            for (short i = 0; i < counter; i++) {
                System.out.print((i + 1) + ". ");
                habit = admin.getHabit(i);
                habit.printHabit();
                System.out.println();
            }
            System.out.println((counter + 1) + ". Exit\n");
            System.out.print("Enter the habit to check: ");
            option = scanner.nextShort();
            scanner.nextLine();

            if (option != (counter + 1)) {
                option--;
                admin.checkHabit(option);
                System.out.println("\n" + admin.getHabit(option).getName() + " checked succesfully!\n");
                pause();
            }

        } else {
            System.out.println("ERROR: There are no habits registered.\n");
            pause();
        }
    }

    public static short printMenuGetOption() {
        short option;
        System.out.println("HABIT TRACKER\n");
        System.out.println("1. Add habit");
        System.out.println("2. Check habits");
        System.out.println("3. Delete habit");
        System.out.println("4. Exit");
        System.out.print("Option: ");
        option = scanner.nextShort();
        scanner.nextLine();
        return option;
    }

    public static void pause() {
        System.out.print("Press enter to continue . . .");
        scanner.nextLine();
    }

    public static void clearScreen() {
        try {
            String sistemaOperativo = System.getProperty("os.name");
            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al intentar limpiar pantalla: " + e.getMessage());
        }
    }
}
