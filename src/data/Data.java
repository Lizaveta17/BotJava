package data;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Data {
    private static Map<String, Map<String, String>> types = new HashMap<>();
    private static Map<String, Set<String>> ingredients = new HashMap<>();

    public Data() {
        loadTypes();
    }

    public static Map<String, Map<String, String>> getTypes() {
        return types;
    }

    public static Map<String, Set<String>> getIngredients() {
        return ingredients;
    }

    public static void loadTypes() {
        List<String> list = new ArrayList<>();
        try {
            File file = new File("../resources/recipes.txt");
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            reader.close();
            int indexOfType = 0;
            for (int i = 1; i < list.size(); ) {
                Map<String, String> recipes = new HashMap<>();
                while (!list.get(i).equals("0")) {
                    String dish = list.get(i);
                    i++;
                    StringBuilder recipe = new StringBuilder();
                    while (!list.get(i).equals("&")) {
                        if (list.get(i).equals("ИНГРЕДИЕНТЫ:")) {
                            String ingredient = list.get(i + 1).split("\\s\\s")[0];
                            if (!ingredients.containsKey(ingredient)) {
                                HashSet<String> dishes = new HashSet<>();
                                dishes.add(dish);
                                ingredients.put(ingredient, dishes);
                            } else {
                                ingredients.get(ingredient).add(dish);
                            }
                        } else if (list.get(i).equals("ИНСТРУКЦИЯ ПО ПРИГОТОВЛЕНИЮ:")) {
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
