package logic;

import data.Data;
import data.BaseAnswers;
import data.BaseCommands;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Logic {
    private HashMap<Long, String> idTypes = new HashMap<>();

    public Logic() {
        Data data = new Data();
    }

    public String setAsFormatString(Set<String> set) {
        return set.stream().sorted().collect(Collectors.joining(", ")) + ".";
    }

    public String getAnswer(Long id, String question) {
        switch (BaseCommands.getEnumByTitle(question)) {
            case START:
                return BaseAnswers.START;
            case HELP_EN:
            case HELP_RUS:
                return BaseAnswers.HELP;
            case RECIPE:
                Set<String> keys = Data.getTypes().keySet();
                return "Выберите из списка: " + setAsFormatString(keys);
            default:
                if (Data.getTypes().containsKey(question)) {
                    if (!idTypes.containsKey(id)) {
                        idTypes.put(id, question);
                    }else if (!idTypes.get(id).equals(question)){
                        idTypes.put(id, question);
                    }
                    Set<String> dishes = Data.getTypes().get(idTypes.get(id)).keySet();
                    return "Выберите блюдо: " + setAsFormatString(dishes);
                } else if (idTypes.containsKey(id)) {
                    HashMap<String, String> dishes = Data.getTypes().get(idTypes.get(id));
                    if (dishes.containsKey(question)) {
                        idTypes.remove(id);
                        return question + '\n' + '\n' + dishes.get(question);
                    }
                    return "Выберите блюдо: " + setAsFormatString(dishes.keySet());
                } else return BaseAnswers.HELP;
        }
    }
}