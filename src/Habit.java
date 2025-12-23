import java.time.LocalDate;

class Habit {
    private final int DAYS = 31;
    private String name;
    private Log[] track;

    public Habit(String title, LocalDate creationDate) {
        this.name = title;
        track = new Log[DAYS];
        for (int i = 0; i < DAYS; i++) {
            track[i] = new Log();
            track[i].setDate(creationDate.plusDays(i));
        }
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    private int findIndexOfDate(LocalDate dateToFind) {
        for (int i = 0; i < DAYS; i++) {
            LocalDate date = track[i].getDate();
            if (date.equals(dateToFind)) {
                return i;
            }
        }
        return -1;
    }

    public boolean check(LocalDate date) {
        int index = findIndexOfDate(date);
        if (index != -1) {
            track[index].check();
            return true;
        }
        return false;
    }

    public boolean isChecked(LocalDate date) {
        int index = findIndexOfDate(date);
        if (index != -1) {
            return track[index].isChecked();
        }
        return false;
    }

    public void printHabit() {
        System.out.println(name);
        for (int i = 0; i < (track.length); i++) {
            if (track[i].isChecked()) {
                System.out.print("[X]");
            } else {
                System.out.print("[ ]");
            }
        }
        System.out.println();
    }

    public String getDataAsString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name);
        for (Log log : track) {
            sb.append(";");
            sb.append(log.getDate().toString());
            sb.append(":");
            sb.append(log.isChecked());
        }

        return sb.toString();
    }
}
