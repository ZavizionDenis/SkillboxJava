public enum Help {
    HELP_EMAIL_VALID ("Email адрес может содержать следующие символы:" +
            "\n========================================\n" +
            "1. Заглавные и строчные буквы латинского алфавита: a-z, A-Z;\n" +
            "2. Цифры: 0-9;\n" +
            "3. Символы: -, _, .(точка). Но если они стоят не в начале, не в конце и их не два подряд." +
            "\n========================================\n"),

    HELP_COMMANDS ("Подсказка:" +
            "\n========================================\n" +
            "LIST - вывести список email адресов\n" +
            "ADD qwert@qwert.qw - добавить указанный адрес в список\n" +
            "EXIT - вывести список email адресов" +
            "\n========================================\n\n");

    private String description;

    Help(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
