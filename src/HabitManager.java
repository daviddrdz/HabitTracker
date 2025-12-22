import java.time.LocalDate;
import java.util.ArrayList;

class HabitManager {
    private final short MAX_HABITS = 3;
    private ArrayList<Habit> habits;
    private short counter;

    public HabitManager() {
        habits = new ArrayList<>();
        counter = 0;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean isFull() {
        return counter >= MAX_HABITS;
    }

    public Habit getHabit(short habitIndex) {
        return habits.get(habitIndex);
    }

    public short getCounter() {
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

    public short checkHabit(short habitIndex, LocalDate date) {
        if ((0 <= habitIndex && habitIndex < counter)) {
            if (!habits.get(habitIndex).isChecked(date)) {
                if (habits.get(habitIndex).check(date)) {
                    return 1;
                }
            } else {
                return 0;
            }
        }
        return -1;
    }

    public boolean deleteHabit(short habitIndex) {
        if (!isEmpty()) {
            if (0 <= habitIndex && habitIndex < counter) {
                habits.remove(habitIndex);
                counter--;
                return true;
            }
        }
        return false;
    }
}
