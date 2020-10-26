package data;

public enum BaseCommands {
    START("/start"),
    HELP_EN("/help"),
    HELP_RUS("Помощь"),
    RECIPE("Узнать рецепт"),
    INGREDIENT("По ингредиенту"),
    NAME("По названию"),
    UNKNOWN("");

    private String title;

    BaseCommands(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static BaseCommands getEnumByTitle(String title) {
        for (BaseCommands command : values()) {
            if (command.title.equals(title)) return command;
        }
        return UNKNOWN;
    }
}
