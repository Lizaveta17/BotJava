package Data;

public enum BaseCommands {
    START("/start"),
    HELP_EN("/help"),
    HELP_RUS("Помощь"),
    RECIPE("Узнать рецепт"),
    UNKNOWN("");

    private String title;

    BaseCommands(String title){
        this.title = title;
    }

    public static BaseCommands getEnumByTitle(String title){
        for(BaseCommands command: values()){
            if(command.title.equals(title)) return command;
        }
        return UNKNOWN;
    }

}
