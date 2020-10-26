package logic;

import data.Data;
import data.AnswerWithKeyboard;
import data.BaseAnswers;
import data.BaseCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Logic {
    protected static HashMap<Long, String> idTypes = new HashMap<>();
    protected static HashMap<Long, String> idIngredients = new HashMap<>();

    public Logic() {
        Data data = new Data();
    }

    public static String getAnswer(Long id, String question) {
        switch (BaseCommands.getEnumByTitle(question)) {
            case START:
                return BaseAnswers.START;
            case HELP_EN:
            case HELP_RUS:
                return BaseAnswers.HELP;
            default:
                if (idTypes.containsKey(id) && Data.getTypes().get(idTypes.get(id)).containsKey(question)) {
                    String type = idTypes.get(id);
                    idTypes.remove(id);
                    return question + "\n\n" + Data.getTypes().get(type).get(question);
                } else if (idIngredients.containsKey(id) && !Data.getIngredients().containsKey(question)) {
                    String recipe = getNeedRecipe(question);
                    idIngredients.remove(id);
                    return question + "\n\n" + recipe;
                } else return BaseAnswers.HELP;
        }
    }

    private static String getNeedRecipe(String neededDish) {
        for (String type : Data.getTypes().keySet()) {
            for (String dish : Data.getTypes().get(type).keySet()) {
                if (neededDish.equals(dish)) {
                    return Data.getTypes().get(type).get(neededDish);
                }
            }
        }
        return "";
    }

    public static Set<String> getSetForKeyboard(Long chatId, AnswerWithKeyboard answer, String strIn) {
        switch (answer) {
            case LIST:
                return Data.getTypes().keySet();
            case DISH:
                idTypes.put(chatId, strIn);
                return Data.getTypes().get(idTypes.get(chatId)).keySet();
            case INGREDIENT:
                return Data.getIngredients().keySet();
            case TOUCH:
                idIngredients.put(chatId, strIn);
                return Data.getIngredients().get(idIngredients.get(chatId));
            case WAY:
                Set<String> set = new HashSet<>();
                set.add(BaseCommands.NAME.getTitle());
                set.add(BaseCommands.INGREDIENT.getTitle());
                return set;
            default:
                return new HashSet<>();
        }
    }

    public static AnswerWithKeyboard getKeyboardAnswer(String strIn) {
        switch (BaseCommands.getEnumByTitle(strIn)) {
            case NAME:
                return AnswerWithKeyboard.LIST;
            case INGREDIENT:
                return AnswerWithKeyboard.INGREDIENT;
            case RECIPE:
                return AnswerWithKeyboard.WAY;
            default:
                if (Data.getTypes().containsKey(strIn)) {
                    return AnswerWithKeyboard.DISH;
                } else if (Data.getIngredients().containsKey(strIn)) {
                    return AnswerWithKeyboard.TOUCH;
                } else return AnswerWithKeyboard.UNKNOWN;
        }
    }
}