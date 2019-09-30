package Help;

public enum Help {
    HELP_COMMANDS ("Допустимые команды:" +
            "\n========================\n" +
            "0 - Завершение\n" +
            "1 - Вывести указанное количество максимальных зарплат\n" +
            "2 - Вывести указанное количество минимальных зарплат\n" +
            "3 - Нанять нового сотрудника\n" +
            "4 - Уволить сотрудника\n" +
            "5 - Сценарий\n" +
            "\n========================\n\n"),
    HELP_EMPLOYEE_TYPE ("Допустимые должности:" +
            "\n========================\n" +
            "1 - Топ менеджер\n" +
            "2 - Менеджер по продажам\n" +
            "3 - Оператор\n" +
            "\n========================\n\n");

    private String description;

    Help(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}