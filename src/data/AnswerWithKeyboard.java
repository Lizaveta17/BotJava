package data;

public enum AnswerWithKeyboard {
    LIST("Выберите из списка: "),
    DISH("Выберите блюдо: "),
    INGREDIENT("Выберите ингридиент: "),
    TOUCH("Выберите блюдо или нажмите: "),
    WAY("Выберите способ: "),
    UNKNOWN("");

    AnswerWithKeyboard(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }
}
