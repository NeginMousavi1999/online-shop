package view;

import exceptions.UserInputValidation;
import model.Address;
import model.User;
import model.products.Product;
import service.ProductService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class Main {
    static UserService userService;
    static ProductService productService;

    static {
        try {
            userService = new UserService();
            productService = new ProductService();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public Main() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

    private static void login(String username) throws SQLException, ClassNotFoundException {
        int tryToEnterCorrectPass = 0;
        while (true) {
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            User user = userService.findMentionedUser(username, password);
            if (user != null) {
                showMenu(user);
                break;

            } else if (tryToEnterCorrectPass == 2) {
                System.out.println("you've tried 3 times and it's still incorrect!");
                break;

            } else {
                System.out.println("the password is incorrect");
                tryToEnterCorrectPass++;
            }
        }
    }

    private static void showMenu(User user) throws SQLException, ClassNotFoundException {
        int choice;
        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add product to cart  \n" +
                    "2.Remove product from cart  \n" +
                    "3.Show a list of products with details \n" +
                    "4.Show the total prices cart  \n" +
                    "5.Confirm your cart  \n" +
                    "6.exit\n" +
                    "your choice is: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewProduct(user);
                    printStar();
                    break;

                case 2:
                    removeItemFromCart(user);
                    printStar();
                    break;

                case 3:
                    showAllProducts(productService.returnAllProducts());
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
                    break choices;

                default:
                    printInvalidInput();
                    printStar();
            }

        } while (true);
    }

    private static void removeItemFromCart(User user) {
        showUserCart(user);
    }

    private static void showUserCart(User user) {

    }

    private static void addNewProduct(User user) throws SQLException, ClassNotFoundException {
        int count = userService.findCountOfItemsInUserCart(user);
        if (count < 5) {
            System.out.printf("your cart has %o items so you can add %o items%n", count, (5 - count));

            List<List<Product>> products = productService.returnAllProducts();
            int productsSize = showAllProducts(products);

            System.out.print("Enter the number of product: ");
            int number = scanner.nextInt();
            if (number > productsSize + 1) {
                printInvalidInput();
                return;
            }

            Product product = returnProductInListWithNumber(products, number - 1);
            if (product == null)
                return;
            System.out.println("you choose : " + product.toString());

            int countOfOrder = getCountOfOrders();
            while (!isCountOfOrderValid(product, countOfOrder)) {
                System.out.println("it is more than the allowed count");
                countOfOrder = getCountOfOrders();
            }
            System.out.println("***" + product.getTypeOfProducts().toString().toLowerCase());
            userService.accessToCartService().addNewProductForThisUser(user, product, countOfOrder);

        } else
            System.out.println("Sorry... you can't add more than 5 items in your cart");
    }

    private static Product returnProductInListWithNumber(List<List<Product>> lists, int number) {
        try {
            if (number < lists.get(0).size())
                return lists.get(0).get(number);
            else if (number < lists.get(1).size() + lists.get(0).size())
                return lists.get(1).get(number - lists.get(0).size());
            else
                return lists.get(2).get(number - (lists.get(1).size() + lists.get(0).size()));
        } catch (Exception e) {
            printInvalidInput();
        }
        return null;
    }

    private static int showAllProducts(List<List<Product>> lists) {
        int index = 0;
        for (List<Product> productList : lists) {
            for (Product product : productList) {
                System.out.println((++index) + ")" + product.toString());
            }
        }
        return index;
    }

    private static boolean isCountOfOrderValid(Product product, int countOfOrder) {
        return product.getCount() >= countOfOrder;
    }

    private static int getCountOfOrders() {
        System.out.print("Enter the count of it: ");
        return scanner.nextInt();
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
                System.out.print("Enter your address postal code: ");
                String postalCode = scanner.nextLine();
                Address address = new Address(postalCode);//TODO felan table db barassh dar nazar nemigiram
                User user = new User(username, password, address);
                userService.addNewUser(user);
                break;
            } else if (!"n".equals(answer)) {
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