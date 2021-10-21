package view;

import exceptions.UserInputValidation;
import model.ProductSold;
import model.enums.BrandOfDevice;
import model.enums.TypeOfReadableItem;
import model.enums.TypeOfShoe;
import model.enums.UserRole;
import model.person.Address;
import model.person.User;
import model.products.ElectronicDevice;
import model.products.Product;
import model.products.ReadableItem;
import model.products.Shoe;
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
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

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
                if (user.getUserRole().equals(UserRole.USER)) {
                    showUserMenu(user);
                } else {
                    showAdminMenu();
                }
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

    private static void showAdminMenu() throws InterruptedException, SQLException, ClassNotFoundException {
        System.out.println("*** ADMIN MENU ***");
        int choice;
        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add new product  \n" +
                    "2.Remove product  \n" +
                    "3.Show all products with details \n" +
                    "4.Show all users  \n" +
                    "5.exit\n" +
                    "your choice is: ");

            choice = getIntegerInputAndHandleExceptionForAndReturnIt();
            switch (choice) {
                case 1:
                    addNewProduct();
                    printStar();
                    break;

                case 2:
                    removeProduct();
                    printStar();
                    break;

                case 3:
                    showAllProducts(productService.returnAllProducts());
                    printStar();
                    break;

                case 4:
                    showAllUsers();
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

    private static void removeProduct() throws InterruptedException, SQLException, ClassNotFoundException {
        System.out.print("which type of product do you wanna remove? 1.electronic device 2.readable item 3.shoe \nenter the number: ");
        int productType = getIntegerInputAndHandleExceptionForAndReturnIt();
        scanner.nextLine();
        switch (productType) {
            case 1:
                removeElectronicProduct();
                break;
            case 2:
                removeReadableProduct();
                break;
            case 3:
                removeShoeProduct();
                break;
            default:
                printInvalidInput();
                break;
        }
    }

    private static Product getRemovingProduct(String tableName) throws InterruptedException, SQLException, ClassNotFoundException {
        System.out.print("enter the id: ");
        int id = getIntegerInputAndHandleExceptionForAndReturnIt();
        return productService.findProductById(tableName, id);
    }

    private static void removeElectronicProduct() throws InterruptedException, SQLException, ClassNotFoundException {
        ElectronicDevice electronicDevice = (ElectronicDevice) getRemovingProduct("electronic_devices");
        if (electronicDevice == null)
            System.out.println("we have n't this product");
        else {
            productService.removeElectronicDevice(electronicDevice);
        }
    }

    private static void removeReadableProduct() throws InterruptedException, SQLException, ClassNotFoundException {
        ReadableItem readableItem = (ReadableItem) getRemovingProduct("readable_items");
        if (readableItem == null)
            System.out.println("we have n't this product");
        else {
            productService.removeReadableItem(readableItem);
        }
    }

    private static void removeShoeProduct() throws InterruptedException, SQLException, ClassNotFoundException {
        Shoe shoe = (Shoe) getRemovingProduct("shoes");
        if (shoe == null)
            System.out.println("we have n't this product");
        else {
            productService.removeShoe(shoe);
        }
    }

    private static void showAllUsers() throws SQLException {
        List<User> users = userService.returnAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void addNewProduct() throws InterruptedException, SQLException {
        System.out.print("which product do you wanna add? 1.electronic device 2.readable item 3.shoe \nenter the number: ");
        int productType = getIntegerInputAndHandleExceptionForAndReturnIt();
        scanner.nextLine();
        switch (productType) {
            case 1:
                addNewElectronicProduct();
                break;
            case 2:
                addNewReadableProduct();
                break;
            case 3:
                addNewShoeProduct();
                break;
            default:
                printInvalidInput();
                break;
        }
    }

    private static void addNewElectronicProduct() throws InterruptedException, SQLException {
        BrandOfDevice brandOfDevice;
        do {
            try {
                System.out.print("brand: ");
                brandOfDevice = BrandOfDevice.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("you must enter LG or SAMSUNG or SONY");
            }
        } while (true);

        System.out.print("count: ");
        int count = getIntegerInputAndHandleExceptionForAndReturnIt();

        System.out.print("cost: ");
        double cost = scanner.nextDouble();

        productService.addNewElectronicProduct(new ElectronicDevice(count, cost, brandOfDevice));
    }

    private static void addNewReadableProduct() throws InterruptedException, SQLException {
        TypeOfReadableItem typeOfReadableItem;
        do {
            try {
                System.out.print("type Of Readable Item: ");
                typeOfReadableItem = TypeOfReadableItem.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("you must enter BOOK or JOURNAL");
            }
        } while (true);

        System.out.print("count: ");
        int count = getIntegerInputAndHandleExceptionForAndReturnIt();

        System.out.print("cost: ");
        double cost = scanner.nextDouble();

        System.out.print("count of pages: ");
        int countOfPages = getIntegerInputAndHandleExceptionForAndReturnIt();

        productService.addNewReadableProduct(new ReadableItem(count, cost, countOfPages, typeOfReadableItem));
    }

    private static void addNewShoeProduct() throws InterruptedException, SQLException {
        TypeOfShoe typeOfShoe;
        do {
            try {
                System.out.print("type Of Shoe: ");
                typeOfShoe = TypeOfShoe.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("you must enter SPORT or FORMAL");
            }
        } while (true);

        System.out.print("count: ");
        int count = getIntegerInputAndHandleExceptionForAndReturnIt();

        System.out.print("cost: ");
        double cost = scanner.nextDouble();

        System.out.print("size of shoe: ");
        int sizeOfShoe = getIntegerInputAndHandleExceptionForAndReturnIt();

        scanner.nextLine();
        System.out.print("color: ");
        String color = scanner.nextLine();

        productService.addNewShoeProduct(new Shoe(count, cost, sizeOfShoe, color, typeOfShoe));
    }

    private static void showUserMenu(User user) throws SQLException, ClassNotFoundException, InterruptedException {
        System.out.println("*** USER MENU ***");
        int choice;
        choices:
        do {
            System.out.print("choose from below:\n" +
                    "1.Add product to cart  \n" +
                    "2.Remove product from cart  \n" +
                    "3.Show a list of products with details \n" +
                    "4.Show the total prices cart  \n" +
                    "5.Show your products sold  \n" +
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
        List<ProductSold> notCompletedProductSold = returnNotCompletedCart(user);
        List<ProductSold> completedProductSold = returnCompletedCart(user);
        System.out.println("your past products sold:");
        for (ProductSold productSold : completedProductSold) {
            System.out.println(productSold.toString());
        }
        System.out.println("your now products sold:");
        for (ProductSold productSold : notCompletedProductSold) {
            System.out.println(productSold.toString());
        }
    }

    private static List<ProductSold> returnCompletedCart(User user) throws SQLException {
        return userService.accessToCartService().getCompletedCart(user);
    }

    private static void getTotalPriceOfCartsForThisUser(User user) throws SQLException {
        List<ProductSold> productsSold = returnNotCompletedCart(user);
        System.out.println("the total cost is: " + calTotalCost(productsSold));
    }

    private static double calTotalCost(List<ProductSold> productsSold) {
        double totalCost = 0;
        for (ProductSold productSold : productsSold) {
            for (int i = 0; i < productSold.getProducts().size(); i++) {
                totalCost = totalCost + (productSold.getProducts().get(i).getCost() * productSold.getProducts().get(i).getCount());
            }
        }
        return totalCost;
    }

    private static void removeItemFromCart(User user) throws SQLException, InterruptedException, ClassNotFoundException {
        List<ProductSold> productsSold = returnNotCompletedCart(user);
        showCarts(productsSold);
        int numberToRemove;
        while (true) {
            System.out.print("enter the number of cart to remove it: ");
            try {
                numberToRemove = getIntegerInputAndHandleExceptionForAndReturnIt();
                handleExceptionForIdToRemove(productsSold, numberToRemove);
                break;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                Thread.sleep(1000);
            }
        }
        removeCart(productsSold.get(numberToRemove - 1));
    }

    private static void handleExceptionForIdToRemove(List<ProductSold> productsSold, int idToRemove) {
        if (idToRemove > productsSold.size())
            throw new UserInputValidation("invalid input");
    }

    private static void removeCart(ProductSold productSold) throws SQLException, ClassNotFoundException {
        userService.accessToCartService().removeCart(productSold);
    }

    private static void showCarts(List<ProductSold> productsSold) {
        for (ProductSold productSold : productsSold) {
            System.out.println(productSold.toString());
        }
    }

    private static List<ProductSold> returnNotCompletedCart(User user) throws SQLException {
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
                User user = new User(username, password, address, UserRole.USER);
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