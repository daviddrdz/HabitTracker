public class Habit {
    private final short DAYS = 31;
    private String name;
    private boolean[] track;
    private short counter;

    public Habit(String title) {
        this.name = title;
        track = new boolean[DAYS];
        counter = 0;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void check() {
        track[counter++] = true;
    }

    public void printHabit() {
        System.out.println(name);
        for (int i = 0; i < (track.length); i++) {
            if (track[i]) {
                System.out.print("[X]");
            } else {
                System.out.print("[ ]");
            }
        }
        System.out.println();
    }
}
