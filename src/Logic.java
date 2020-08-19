import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Logic {
    private String type;

    public Logic() {
        Data data = new Data();
    }

    private String setAsFormatString(Set<String> set) {
        return set.stream().sorted().collect(Collectors.joining(", ")) + ".";
    }

    public String getAnswer(String question) {
        switch (question) {
            case "Старт":
                return Data.start;
            case "Помощь":
                return Data.help;
            case "Узнать рецепт":
                Set<String> keys = Data.getTypes().keySet();
                return "Выберите из списка: " + setAsFormatString(keys);
            default:
                if (Data.getTypes().containsKey(question)) {
                    type = question;
                    Set<String> dishes = Data.getTypes().get(question).keySet();
                    return "Выберите блюдо: " + setAsFormatString(dishes);
                } else if (type != null) {
                    HashMap<String, String> dishes = Data.getTypes().get(type);
                    if (dishes.containsKey(question)) {
                        return dishes.get(question);
                    }
                    return "Выберите блюдо: " + setAsFormatString(dishes.keySet());
                } else return "Чтобы начать, напишите \"Старт\".";
        }
    }
}
