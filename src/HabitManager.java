import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class HabitManager {
    private final int MAX_HABITS = 10;
    private final String FILE_NAME = "data.txt";
    private ArrayList<Habit> habits;
    private int counter;

    public HabitManager() {
        habits = new ArrayList<>();
        if (!loadData()) {
            counter = 0;
        }
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean isFull() {
        return counter >= MAX_HABITS;
    }

    public Habit getHabit(int habitIndex) {
        return habits.get(habitIndex);
    }

    public int getCounter() {
        return counter;
    }

    public boolean addHabit(String name, LocalDate creationDate) {
        if (!isFull()) {
            Habit habitToAdd = new Habit(name, creationDate);
            habits.add(habitToAdd);
            counter++;
            return true;
        }
        return false;
    }

    public CheckStatus checkHabit(int habitIndex, LocalDate date) {
        if ((0 <= habitIndex && habitIndex < counter)) {
            if (!habits.get(habitIndex).isChecked(date)) {
                if (habits.get(habitIndex).check(date)) {
                    return CheckStatus.SUCCESS;
                }
            } else {
                return CheckStatus.ALREADY_CHECKED;
            }
        }
        return CheckStatus.FAILURE;
    }

    public boolean deleteHabit(int habitIndex) {
        if (!isEmpty()) {
            if (0 <= habitIndex && habitIndex < counter) {
                habits.remove(habitIndex);
                counter--;
                return true;
            }
        }
        return false;
    }

    public boolean saveData() {
        try {
            File file = new File(FILE_NAME);
            FileWriter writer = new FileWriter(file);
            for (Habit habit : habits) {
                writer.write(habit.getDataAsString() + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadData() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return false;
            }

            habits.clear();
            Scanner fileScanner = new Scanner(file);
            counter = 0;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");

                String habitName = data[0];
                String firstLog = data[1];
                LocalDate creationDate = LocalDate.parse(firstLog.split(":")[0]);

                addHabit(habitName, creationDate);

                Habit currentHabit = habits.get(counter - 1);
                for (int i = 1; i < data.length; i++) {
                    String[] log = data[i].split(":");
                    LocalDate date = LocalDate.parse(log[0]);
                    boolean isChecked = Boolean.parseBoolean(log[1]);
                    if (isChecked) {
                        currentHabit.check(date);
                    }
                }
            }
            fileScanner.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
