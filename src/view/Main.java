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
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        User user = userService.findUserByUsername(username);
        if (user == null)
            register(username);
        int choice;

        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add a group of drivers  \n" +
                    "2.Add a group of passengers  \n" +
                    "3.Driver signup or login  \n" +
                    "4.Passenger signup or login  \n" +
                    "5.Show ongoing travels  \n" +
                    "6.Show a list of drivers \n" +
                    "7.Show a list of passengers\n" +
                    "8.exit\n" +
                    "your choice is: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

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
                    break;

                case 6:

                    printStar();
                    break;

                case 7:

                    printStar();
                    break;

                case 8:
                    printStar();
                    break choices;

                default:
                    printInvalidInput();
                    printStar();
            }

        } while (true);
    }

    private static void register(String username) throws SQLException {
        System.out.print("you are not register yet.");
        String answer = null;
        while (!answer.equalsIgnoreCase("n")) {
            System.out.print(" do you want to?(y/n): ");
            answer = scanner.nextLine();
            if ("y".equals(answer)) {
                System.out.print("You already add your username so now enter your password: ");
                String password = scanner.nextLine();
                User user = new User(username, password);
                userService.addNewUser(user);
            } else {
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