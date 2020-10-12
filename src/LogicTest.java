import org.junit.Assert;
import org.junit.Test;

public class LogicTest {

    @Test
    public void getAnswer() {
        Logic logic = new Logic();
        Data data = new Data();
        Long id = (long)1;
        Assert.assertEquals("Чтобы начать, напишите \"Старт\".", logic.getAnswer(id,"Привет"));
        Assert.assertEquals(BaseCommands.start, logic.getAnswer(id,"Старт"));
        Assert.assertEquals(BaseCommands.help, logic.getAnswer(id,"Помощь"));
        String test = "Выберите из списка: " + logic.setAsFormatString(Data.getTypes().keySet());
        Assert.assertEquals(test, logic.getAnswer(id,"Узнать рецепт"));
        test = "Выберите блюдо: " + logic.setAsFormatString(Data.getTypes().get("Супы").keySet());
        Assert.assertEquals(test, logic.getAnswer(id,"Супы"));
    }
}