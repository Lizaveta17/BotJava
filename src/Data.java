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
                    recipes.put(list.get(i), list.get(i + 1));
                    i += 2;
                }
                types.put(list.get(indexOfType), recipes);
                indexOfType += i + 1;
                i += 2;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
