import java.util.Scanner;

public class Bot {
    private Logic logic;

    public Bot() {
        logic = new Logic();
    }

    private String readIn(Scanner scanner) {
        return scanner.nextLine();
    }

    private void printOut(String strOut) {
        System.out.println(strOut);
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String strIn = bot.readIn(scanner);
            String strOut = bot.logic.getAnswer(strIn);
            bot.printOut(strOut);
        }
    }
}