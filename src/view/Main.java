package view;

import exceptions.UserInputValidation;
import model.Address;
import model.Cart;
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
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

    private static void login(String username) throws SQLException, ClassNotFoundException, InterruptedException {
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

    private static void showMenu(User user) throws SQLException, ClassNotFoundException, InterruptedException {
        int choice;
        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add product to cart  \n" +
                    "2.Remove product from cart  \n" +
                    "3.Show a list of products with details \n" +
                    "4.Show the total prices cart  \n" +
                    "5.Show your carts  \n" +
                    "6.Confirm your cart  \n" +
                    "7.exit\n" +
                    "your choice is: ");

            choice = getIntegerInputAndHandleExceptionForAndReturnIt();
            switch (choice) {
                case 1:
                    addNewProductToCart(user);
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
                    getTotalPriceOfCartsForThisUser(user);
                    printStar();
                    break;

                case 5:
                    showYourCarts(user);
                    printStar();
                    break;

                case 6:
                    confirmOrders(user);
                    printStar();
                    break;

                case 7:
                    printStar();
                    break choices;

                default:
                    printInvalidInput();
                    printStar();
            }

        } while (true);
    }

    private static int getIntegerInputAndHandleExceptionForAndReturnIt() throws InterruptedException {
        int input;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                scanner.nextLine();
                System.out.println("Enter a valid Integer value");
                Thread.sleep(1000);
            }
        }
        return input;
    }

    private static void confirmOrders(User user) throws SQLException, InterruptedException {
        System.out.println("here is your list:");
        showCarts(returnNotCompletedCart(user));
        getTotalPriceOfCartsForThisUser(user);

        while (true) {
            System.out.print("are you sure that you wanna confirm and pay?(y/n):");
            scanner.nextLine();
            try {
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    userService.accessToCartService().confirmCarts(user);
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println("ok... come back later :)");
                    break;
                } else
                    throw new UserInputValidation("invalid answer!");
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                Thread.sleep(1000);
            }
        }
        System.out.println("Done :)");
    }

    private static void showYourCarts(User user) throws SQLException {
        List<Cart> notCompletedCarts = returnNotCompletedCart(user);
        List<Cart> completedCarts = returnCompletedCart(user);
        System.out.println("your past carts:");
        for (Cart completedCart : completedCarts) {
            System.out.println(completedCart.toString());
        }
        System.out.println("your now carts:");
        for (Cart notCompletedCart : notCompletedCarts) {
            System.out.println(notCompletedCart.toString());
        }
    }

    private static List<Cart> returnCompletedCart(User user) throws SQLException {
        return userService.accessToCartService().getCompletedCart(user);
    }

    private static void getTotalPriceOfCartsForThisUser(User user) throws SQLException {
        List<Cart> carts = returnNotCompletedCart(user);
        System.out.println("the total cost is: " + calTotalCost(carts));
    }

    private static double calTotalCost(List<Cart> carts) {
        double totalCost = 0;
        for (Cart cart : carts) {
            for (int i = 0; i < cart.getProducts().size(); i++) {
                totalCost = totalCost + (cart.getProducts().get(i).getCost() * cart.getProducts().get(i).getCount());
            }
        }
        return totalCost;
    }

    private static void removeItemFromCart(User user) throws SQLException, InterruptedException, ClassNotFoundException {
        List<Cart> carts = returnNotCompletedCart(user);
        showCarts(carts);
        int numberToRemove;
        while (true) {
            System.out.print("enter the number of cart to remove it: ");
            try {
                numberToRemove = getIntegerInputAndHandleExceptionForAndReturnIt();
                handleExceptionForIdToRemove(carts, numberToRemove);
                break;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                Thread.sleep(1000);
            }
        }
        removeCart(carts.get(numberToRemove - 1));
    }

    private static void handleExceptionForIdToRemove(List<Cart> carts, int idToRemove) {
        if (idToRemove > carts.size())
            throw new UserInputValidation("invalid input");
    }

    private static void removeCart(Cart cart) throws SQLException, ClassNotFoundException {
        userService.accessToCartService().removeCart(cart);
    }

    private static void showCarts(List<Cart> carts) {
        for (Cart cart : carts) {
            System.out.println(cart.toString());
        }
    }

    private static List<Cart> returnNotCompletedCart(User user) throws SQLException {
        return userService.accessToCartService().getNotCompletedCart(user);
    }

    private static void addNewProductToCart(User user) throws SQLException, ClassNotFoundException, InterruptedException {
        int count = userService.findCountOfItemsInUserCart(user);
        if (count < 5) {
            System.out.printf("your cart has %o items so you can add %o items%n", count, (5 - count));

            List<Product> products = productService.returnAllProducts();
            int productsSize = showAllProducts(products);

            System.out.print("Enter the number of product: ");
            int number = getIntegerInputAndHandleExceptionForAndReturnIt();
            if (number > productsSize + 1) {
                printInvalidInput();
                return;
            }

            Product product = returnProductInListWithNumber(products, number - 1);
            if (product == null)
                return;
            if (product.getCount() == 0) {
                System.out.println("you can't choose this item because we ran out of it!");
                return;
            }

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

    private static Product returnProductInListWithNumber(List<Product> lists, int number) {
        return lists.get(number);
    }

    private static int showAllProducts(List<Product> lists) {
        int index = 0;
        for (Product list : lists) {
            System.out.println((++index) + ")" + list);
        }
        return index;
    }

    private static boolean isCountOfOrderValid(Product product, int countOfOrder) {
        return product.getCount() >= countOfOrder;
    }

    private static int getCountOfOrders() throws InterruptedException {
        System.out.print("Enter the count of it: ");
        return getIntegerInputAndHandleExceptionForAndReturnIt();
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
                Address address = new Address(postalCode);
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