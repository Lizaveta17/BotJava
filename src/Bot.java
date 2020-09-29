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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String strIn = readIn(scanner);
            String strOut = logic.getAnswer(strIn);
            printOut(strOut);
        }
    }
}