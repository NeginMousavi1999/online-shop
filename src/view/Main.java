package view;

import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class Main {
    static UserService userService;

    static {
        try {
            userService = new UserService();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public Main() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws SQLException {
        welcome();
        printStar();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = userService.findUserByUsername(username);
        if (user == null)
            register(username);
        else {
            login(username);
        }
    }

    private static void login(String username) throws SQLException {
        int tryToEnterCorrectPass = 0;
        while (true) {
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            User user = new User(username, password);
            if (userService.isPassCorrect(user)) {//TODO id?
                tryToEnterCorrectPass = 0;
                showMenu(user);
                break;
            } else if (tryToEnterCorrectPass == 2) {
                System.out.println("you've tried 3 times and it's still incorrect!");
                break;
            }
            else {
                System.out.println("the password is incorrect");
                tryToEnterCorrectPass++;
            }
        }
    }

    private static void showMenu(User user) throws SQLException {
        int choice;
        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add product to cart  \n" +
                    "2.Remove product from cart  \n" +
                    "3.Show a list of products with details \n" +
                    "4.Show the total prices cart  \n" +
                    "5.exit\n" +
                    "your choice is: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewProduct(user);
                    printStar();
                    break;

                case 2:

                    printStar();
                    break;

                case 3:

                    printStar();
                    break;

                case 4:

                    printStar();
                    break;

                case 5:
                    printStar();
                    break choices;

                default:
                    printInvalidInput();
                    printStar();
            }

        } while (true);
    }

    private static void addNewProduct(User user) throws SQLException {
        int count = userService.findCountOfItemsInUserCart(user);
        if (count < 5)
            userService.addNewProductForThisUser(user);
        else
            System.out.println("sorry... you can't add more than 5 items in your cart");
    }

    private static void register(String username) throws SQLException {
        System.out.print("you are not register yet.");
        String answer = "";
        while (!answer.equalsIgnoreCase("n")) {
            System.out.print("do you want to?(y/n): ");
            answer = scanner.nextLine();
            if ("y".equals(answer)) {
                System.out.print("You already add your username so now enter your password: ");
                String password = scanner.nextLine();
                User user = new User(username, password);
                userService.addNewUser(user);
                break;
            } else if (!"n".equals(answer)){
                printInvalidInput();
            }
        }
    }

    public static void printInvalidInput() {
        System.out.println("invalid input");
    }

    public static void printStar() {
        System.out.println("**********************************************************");
    }

    public static void welcome() {
        System.out.println("   ____        _ _                _____ _                 \n" +
                "  / __ \\      | (_)              / ____| |                \n" +
                " | |  | |_ __ | |_ _ __   ___   | (___ | |__   ___  _ __  \n" +
                " | |  | | '_ \\| | | '_ \\ / _ \\   \\___ \\| '_ \\ / _ \\| '_ \\ \n" +
                " | |__| | | | | | | | | |  __/   ____) | | | | (_) | |_) |\n" +
                "  \\____/|_| |_|_|_|_| |_|\\___|  |_____/|_| |_|\\___/| .__/ \n" +
                "                                                   | |    \n" +
                "                                                   |_|    ");
    }
}