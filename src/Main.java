import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static HabitManager manager = new HabitManager();
    static LocalDate date = LocalDate.now();

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
                    deleteHabit();
                    break;
                case 4:
                    changeDate();
                    break;
                case 5:
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
        if (!manager.isFull()) {
            System.out.print("Enter the habit name: ");
            name = scanner.nextLine();
            if (manager.addHabit(name, date)) {
                System.out.println("\nHabit " + name + " created succesfully!\n");
            } else {
                System.out.println("Error while creating the habit.\n");
            }
            pause();
        }
    }

    public static void checkHabits() {
        short counter, index, code;

        clearScreen();
        System.out.println("CHECK HABITS\n");

        if (!manager.isEmpty()) {
            counter = manager.getCounter();
            for (short i = 0; i < counter; i++) {
                System.out.print((i + 1) + ". ");
                manager.getHabit(i).printHabit();
                System.out.println();
            }
            System.out.println((counter + 1) + ". Exit\n");
            System.out.print("Enter the habit to check: ");
            index = scanner.nextShort();
            scanner.nextLine();

            if (index != (counter + 1)) {
                index--;
                code = manager.checkHabit(index, date);
                switch (code) {
                    case 0:
                        System.out.println("\nYou already checked this habit today!\n");
                        break;
                    case 1:
                        System.out.println("\n" + manager.getHabit(index).getName() + " checked succesfully!\n");
                        break;
                    default:
                        System.out.println("\nYou already completed this habit!\n");
                }
                pause();
            }

        } else {
            System.out.println("There are no habits registered.\n");
            pause();
        }
    }

    public static void deleteHabit() {
        short counter, index;
        char answ;

        clearScreen();
        System.out.println("DELETE AN HABIT \n");

        if (!manager.isEmpty()) {
            counter = manager.getCounter();
            for (short i = 0; i < counter; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(manager.getHabit(i).getName());
            }
            System.out.println((counter + 1) + ". Exit\n");
            System.out.print("Enter the habit to delete: ");
            index = scanner.nextShort();
            scanner.nextLine();

            if (index != (counter + 1)) {
                index--;
                System.out.print("Are you sure you want to delete " + manager.getHabit(index).getName() + "? (y/n): ");
                answ = scanner.nextLine().charAt(0);
                if (answ == 'y' || answ == 'Y') {
                    manager.deleteHabit(index);
                    System.out.println("\nHabit deleted succesfully!\n");
                } else {
                    System.out.println("\nSuccesfully canceled!\n");
                }
                pause();
            }
        } else {
            System.out.println("There are no habits registered.\n");
            pause();
        }
    }

    public static void changeDate() {
        short year, month, day;

        clearScreen();
        System.out.println("CHANGE DATE\n");
        System.out.println("Current Date: " + date + "\n");
        System.out.print("Year: ");
        year = scanner.nextShort();
        System.out.print("Month: ");
        month = scanner.nextShort();
        System.out.print("Day: ");
        day = scanner.nextShort();
        scanner.nextLine();

        date = LocalDate.of(year, month, day);

        System.out.println("\nDate changed succesfully!\n");
    }

    public static short printMenuGetOption() {
        short option;
        System.out.println("HABIT TRACKER     Date: " + date + "\n");
        System.out.println("1. Add habit");
        System.out.println("2. Check habits");
        System.out.println("3. Delete habit");
        System.out.println("4. Change date");
        System.out.println("5. Exit");
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
