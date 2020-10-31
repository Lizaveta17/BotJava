package test;

import data.Data;
import data.BaseAnswers;
import data.BaseCommands;
import data.AnswerWithKeyboard;
import logic.Logic;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LogicTest extends Logic{
    Logic logic = new Logic();

    @Test
    public void getAnswer() {
        Long id = (long)1;
        Assert.assertEquals(BaseAnswers.START, getAnswer(id,BaseCommands.START.getTitle()));
        Assert.assertEquals(BaseAnswers.HELP, getAnswer(id,BaseCommands.HELP_RUS.getTitle()));
        Assert.assertEquals(BaseAnswers.HELP, getAnswer(id,BaseCommands.HELP_EN.getTitle()));
        Assert.assertEquals(BaseAnswers.HELP, getAnswer(id,"Привет"));

        idTypes.put(id, "Супы");
        Assert.assertEquals("Борщ\n\n" + Data.getTypes().get("Супы").get("Борщ"),
                getAnswer(id,"Борщ"));
        Assert.assertEquals(BaseAnswers.HELP, getAnswer(id,"Борщ"));

        idIngredients.put(id, "Рыба");
        Assert.assertEquals("Мимоза\n\n" + Data.getTypes().get("Салаты").get("Мимоза"),
                getAnswer(id, "Мимоза"));
    }

    @Test
    public void getSetForKeyboard() {
        Long id = (long)2;
        Assert.assertEquals(Data.getTypes().keySet(), getSetForKeyboard(id, AnswerWithKeyboard.LIST, ""));

        Assert.assertEquals(getSetForKeyboard(id, AnswerWithKeyboard.DISH, "Соусы"),
                Data.getTypes().get(idTypes.get(id)).keySet());

        Assert.assertEquals(Data.getIngredients().keySet(),
                getSetForKeyboard(id, AnswerWithKeyboard.INGREDIENT, ""));


        Assert.assertEquals(getSetForKeyboard(id, AnswerWithKeyboard.TOUCH, "Орехи"),
                Data.getIngredients().get(idIngredients.get(id)));

        Set<String> set = new HashSet<>();
        set.add(BaseCommands.NAME.getTitle());
        set.add(BaseCommands.INGREDIENT.getTitle());
        Assert.assertEquals(set, getSetForKeyboard(id, AnswerWithKeyboard.WAY, ""));

        set = new HashSet<>();
        Assert.assertEquals(set, getSetForKeyboard(id, AnswerWithKeyboard.UNKNOWN, ""));
    }

    @Test
    public void getKeyboardAnswer(){
        Assert.assertEquals(AnswerWithKeyboard.LIST,getKeyboardAnswer("По названию"));
        Assert.assertEquals(AnswerWithKeyboard.INGREDIENT,getKeyboardAnswer("По ингредиенту"));
        Assert.assertEquals(AnswerWithKeyboard.WAY,getKeyboardAnswer("Узнать рецепт"));
        Assert.assertEquals(AnswerWithKeyboard.DISH,getKeyboardAnswer("Напитки"));
        Assert.assertEquals(AnswerWithKeyboard.TOUCH,getKeyboardAnswer("Свинина"));
        Assert.assertEquals(AnswerWithKeyboard.UNKNOWN,getKeyboardAnswer("Привет"));
    }
}
