package Test;

import Data.Data;
import Data.BaseAnswers;
import Logic.Logic;
import org.junit.Assert;
import org.junit.Test;

public class LogicTest {

    @Test
    public void getAnswer() {
        Logic logic = new Logic();
        Data data = new Data();
        Long id = (long)1;
        Assert.assertEquals(BaseAnswers.START, logic.getAnswer(id,"/start"));
        Assert.assertEquals(BaseAnswers.HELP, logic.getAnswer(id,"Помощь"));
        Assert.assertEquals(BaseAnswers.HELP, logic.getAnswer(id,"/help"));
        String test = "Выберите из списка: " + logic.setAsFormatString(Data.getTypes().keySet());
        Assert.assertEquals(test, logic.getAnswer(id,"Узнать рецепт"));
        test = "Выберите блюдо: " + logic.setAsFormatString(Data.getTypes().get("Супы").keySet());
        Assert.assertEquals(test, logic.getAnswer(id,"Супы"));
    }
}