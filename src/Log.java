import java.time.LocalDate;

class Log {
    LocalDate date;
    boolean checked;

    Log() {
        checked = false;
    }

    public void setDate(LocalDate d) {
        date = d;
    }

    public LocalDate getDate() {
        return date;
    }

    public void check() {
        checked = true;
    }

    public boolean isChecked() {
        return checked;
    }
}
