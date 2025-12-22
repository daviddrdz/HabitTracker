public class HabitManager {
    private final short INIT_HABITS = 3;
    private final short MAX_HABITS = 10;
    private Habit habits[];
    private short counter;

    public HabitManager() {
        habits = new Habit[INIT_HABITS];
        counter = 0;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean isFull() {
        return counter == MAX_HABITS;
    }

    public Habit getHabit(short index) {
        return habits[index];
    }

    public short getCounter() {
        return counter;
    }

    private void grow() {
        if (counter >= INIT_HABITS) {
            Habit temp[] = new Habit[counter + 1];
            for (int i = 0; i < habits.length; i++) {
                temp[i] = habits[i];
            }
            habits = temp;
        }
    }

    public boolean addHabit(String name) {
        if (!isFull()) {
            grow();
            habits[counter++] = new Habit(name);
            return true;
        }
        return false;
    }

    public boolean checkHabit(short index) {
        if (0 <= index && index < counter) {
            habits[index].check();
            return true;
        }
        return false;
    }
}
