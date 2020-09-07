import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Data {
    private static HashMap<String, HashMap<String, String>> types = new HashMap<>();
    static String start;
    static String help;

    public Data() {
        start = "Привет! Здесь вы можете узнать рецепт интересуещего вас блюда.\n" +
                "Чтобы получить рецепт, напишите \"Узнать рецепт\".Если что-то пойдет не так, напишите \"Помощь\".";
        help = "Чтобы получить рецепт, напишите \"Узнать рецепт\".";
        loadTypes();
    }

    public static HashMap<String, HashMap<String, String>> getTypes() {
        return types;
    }

    public static void loadTypes() {
        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader("data.txt");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            reader.close();
            int indexOfType = 0;
            for (int i = 1; i < list.size(); ) {
                HashMap<String, String> recipes = new HashMap<>();
                while (!list.get(i).equals("0")) {
                    String dish = list.get(i);
                    i++;
                    StringBuilder recipe = new StringBuilder();
                    while (!list.get(i).equals("&")) {
                        if (list.get(i).equals("ИНСТРУКЦИЯ ПО ПРИГОТОВЛЕНИЮ:")) {
                            recipe.append("\n");
                        }
                        recipe.append(list.get(i)).append("\n");
                        i++;
                    }
                    recipe.append("\n").append("Приятного аппетита!");
                    recipes.put(dish, recipe.toString());
                    i++;
                }
                types.put(list.get(indexOfType), recipes);
                indexOfType += (i - indexOfType) + 1;
                i += 2;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
