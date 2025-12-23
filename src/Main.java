import java.util.Scanner;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.io.IOException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static HabitManager manager = new HabitManager();
    static LocalDate date = LocalDate.now();

    public static void main(String[] args) {
        MenuOption option;
        boolean continueProgram;

        do {
            option = printMenuGetOption();
            continueProgram = option != MenuOption.EXIT;

            if (option == null) {
                System.out.println("\nError: Not a valid option.\n");
                pause();
                clearScreen();
                continue;
            }

            switch (option) {
                case ADD_HABIT:
                    addHabit();
                    break;
                case CHECK_HABIT:
                    checkHabits();
                    break;
                case DELETE_HABIT:
                    deleteHabit();
                    break;
                case CHANGE_DATE:
                    changeDate();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
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
        } else {
            System.out.println("You reached the habit limit\n");
        }
        pause();
    }

    public static void checkHabits() {
        int counter, index;
        CheckStatus status;

        clearScreen();
        System.out.println("CHECK HABITS\n");

        if (!manager.isEmpty()) {
            counter = manager.getCounter();
            for (int i = 0; i < counter; i++) {
                System.out.print((i + 1) + ". ");
                manager.getHabit(i).printHabit();
                System.out.println();
            }
            System.out.println((counter + 1) + ". Exit\n");
            index = readInt("Enter the habit to check: ");

            if (index != (counter + 1)) {
                index--;
                status = manager.checkHabit(index, date);
                switch (status) {
                    case ALREADY_CHECKED:
                        System.out.println("\nYou already checked this habit today!\n");
                        break;
                    case SUCCESS:
                        System.out.println("\n" + manager.getHabit(index).getName() + " checked succesfully!\n");
                        break;
                    case FAILURE:
                        System.out.println("\nCould not check habit!\n");
                        break;
                    default:
                }
                pause();
            }

        } else {
            System.out.println("There are no habits registered.\n");
            pause();
        }
    }

    public static void deleteHabit() {
        int counter, index;
        char answ;

        clearScreen();
        System.out.println("DELETE AN HABIT \n");

        if (!manager.isEmpty()) {
            counter = manager.getCounter();
            for (int i = 0; i < counter; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(manager.getHabit(i).getName());
            }
            System.out.println((counter + 1) + ". Exit\n");
            index = readInt("Enter the habit to delete: ");

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
        int year, month, day;

        clearScreen();
        System.out.println("CHANGE DATE\n");
        System.out.println("Current Date: " + date + "\n");

        year = readInt("Year: ");
        month = readInt("Month: ");
        day = readInt("Day: ");

        try {
            date = LocalDate.of(year, month, day);
            System.out.println("\nDate changed succesfully!\n");
        } catch (DateTimeException e) {
            System.out.println("\nError: You must enter a valid date.\n");
            pause();
        }
    }

    public static void exit() {
        System.out.println("\nExiting the program . . .");
        manager.saveData();
        System.out.println("Bye ;P\n");
    }

    public static MenuOption printMenuGetOption() {
        int option = -1;
        System.out.println("HABIT TRACKER     Date: " + date + "\n");
        System.out.println("1. Add habit");
        System.out.println("2. Check habits");
        System.out.println("3. Delete habit");
        System.out.println("4. Change date");
        System.out.println("5. Exit");
        option = readInt("Option: ");
        return MenuOption.parseInt(option);
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

    public static int readInt(String message) {
        int value = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(message);
            try {
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        return value;
    }
}
