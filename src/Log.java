import java.time.LocalDate;

class Log {
    private LocalDate date;
    private boolean checked;

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
