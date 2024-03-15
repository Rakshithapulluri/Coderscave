import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean avail;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.avail = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return avail;
    }

    public void setAvailable(boolean avail) {
        this.avail = avail;
    }
    //Checking the availabilty of books
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nAvailable: " + avail;
    }
}

class User {
    private String u_name;
    private int user_id;

    public User(String u_name, int user_id) {
        this.u_name = u_name;
        this.user_id = user_id;
    }

    public String getName() {
        return u_name;
    }

    public int getId() {
        return user_id;
    }

    @Override
    public String toString() {
        return "Name: " + u_name + "\nID: " + user_id;
    }
}

public class LibraryManagementSystem {
    private static List<Book> books = new ArrayList<>();
    private static Map<Integer, User> users = new HashMap<>();
    private static int userIdCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Switch cases for our different options while our option is between our given options
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Add User");
            System.out.println("4. Display Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter your option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    addUser(sc);
                    break;
                case 4:
                    displayUsers();
                    break;
                case 5:
                    borrowBook(sc);
                    break;
                case 6:
                    returnBook(sc);
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.Visit again.Bye Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }
    //Adding the book to the library list
    private static void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();

        Book book = new Book(title, author);
        books.add(book);

        System.out.println("Book added successfully!");
    }
    //Displaying the books
    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books avail in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println(book);
                System.out.println("------------");
            }
        }
    }
    //Adding users with names and their ID's Incrementing according to their joining queue
    private static void addUser(Scanner sc) {
        System.out.print("Enter user u_name: ");
        String u_name = sc.nextLine();

        User user = new User(u_name, userIdCounter++);
        users.put(user.getId(), user);

        System.out.println("User added successfully!,Thank you!!");
    }
    //Displaying the users
    private static void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered in the library.");
        } else {
            System.out.println("Users in the library:");
            for (User user : users.values()) {
                System.out.println(user);
                System.out.println("------------");
            }
        }
    }
    //Borrowing book according to user id
    private static void borrowBook(Scanner sc) {
        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        //Checking condition according to our user id to checking that the user is exists or not
        if (users.containsKey(userId)) {
            displayBooks();
            System.out.print("Enter book title to borrow: ");
            String bookTitle = sc.nextLine();
            //if user present he can select the books from available if avail it is assigned else rejected
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book borrowed successfully!,Visit again!!");
                    return;
                }
            }

            System.out.println("Book not avail or does not exist.");
        } else {
            System.out.println("User with ID " + userId + " does not exist.");
        }
    }
    //Returning the borrowed book according to our specified user id
    private static void returnBook(Scanner sc) {
        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        if (users.containsKey(userId)) {
            System.out.print("Enter book title to return: ");
            String bookTitle = sc.nextLine();

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned successfully!Visit again!!");
                    return;
                }
            }

            System.out.println("Book not borrowed or does not exist.");
        } else {
            System.out.println("User with ID " + userId + " does not exist.");
        }
    }
}