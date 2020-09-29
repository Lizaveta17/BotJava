import org.junit.Assert;
import org.junit.Test;

public class LogicTest {

    @Test
    public void getAnswer() {
        Logic logic = new Logic();
        Data data = new Data();
        BaseCommands baseCommands = new BaseCommands();
        Assert.assertEquals("Чтобы начать, напишите \"Старт\".", logic.getAnswer("Привет"));
        Assert.assertEquals(BaseCommands.start, logic.getAnswer("Старт"));
        Assert.assertEquals(BaseCommands.help, logic.getAnswer("Помощь"));
        String test = "Выберите из списка: " + logic.setAsFormatString(Data.getTypes().keySet());
        Assert.assertEquals(test, logic.getAnswer("Узнать рецепт"));
        test = "Выберите блюдо: " + logic.setAsFormatString(Data.getTypes().get("Супы").keySet());
        Assert.assertEquals(test, logic.getAnswer("Супы"));
    }
}