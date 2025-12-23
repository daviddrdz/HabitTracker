public enum MenuOption {
    ADD_HABIT,
    CHECK_HABIT,
    DELETE_HABIT,
    CHANGE_DATE,
    EXIT;

    public static MenuOption parseInt(int x) {
        switch (x) {
            case 1:
                return ADD_HABIT;
            case 2:
                return CHECK_HABIT;
            case 3:
                return DELETE_HABIT;
            case 4:
                return CHANGE_DATE;
            case 5:
                return EXIT;
            default:
                return null;
        }
    }
}
