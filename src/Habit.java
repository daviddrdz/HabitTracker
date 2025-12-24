import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;

class Habit {
    private String name;
    private LocalDate creationDate;
    private ArrayList<Log> track;

    public Habit(String title, LocalDate date) {
        this.name = title;
        this.creationDate = date;
        track = new ArrayList<>();
        int days = getRemainingDays(creationDate);
        for (int i = 0; i < days; i++) {
            Log log = new Log();
            log.setDate(creationDate.plusDays(i));
            track.add(log);
        }
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastday() {
        return track.getLast().getDate();
    }

    public boolean check(LocalDate date) {
        int index = findIndexOfDate(date);
        if (index != -1) {
            track.get(index).check();
            return true;
        }
        return false;
    }

    public boolean isChecked(LocalDate date) {
        int index = findIndexOfDate(date);
        if (index != -1) {
            return track.get(index).isChecked();
        }
        return false;
    }

    public void incrementLog() {
        LocalDate lastLogDay = track.getLast().getDate();
        for (int i = 1; i <= 7; i++) {
            Log log = new Log();
            log.setDate(lastLogDay.plusDays(i));
            track.add(log);
        }
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

    public void printHabit() {
        System.out.println(this.name);
        int weeks = 1;
        System.out.print("Week " + weeks + ": ");

        for (int i = 0; i < track.size(); i++) {
            Log currentLog = track.get(i);
            LocalDate date = currentLog.getDate();

            if (i > 0 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.println();
                weeks++;
                System.out.print("Week " + weeks + ": ");
            }

            if (currentLog.isChecked()) {
                System.out.print("[X]");
            } else {
                System.out.print("[ ]");
            }
        }
        System.out.println();
    }

    private int findIndexOfDate(LocalDate dateToFind) {
        for (int i = 0; i < track.size(); i++) {
            LocalDate date = track.get(i).getDate();
            if (date.equals(dateToFind)) {
                return i;
            }
        }
        return -1;
    }

    private int getRemainingDays(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        switch (day) {
            case MONDAY:
                return 7;
            case TUESDAY:
                return 6;
            case WEDNESDAY:
                return 5;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 3;
            case SATURDAY:
                return 2;
            case SUNDAY:
                return 1;
            default:
                return 0;
        }
    }
}
