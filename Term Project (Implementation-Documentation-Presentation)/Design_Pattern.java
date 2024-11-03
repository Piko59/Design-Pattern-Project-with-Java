import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Pattern: Ensure a class has only one instance.
class HotelManager {
    private static HotelManager instance;

    private HotelManager() {
    }

    public static HotelManager getInstance() {
        if (instance == null) {
            instance = new HotelManager();
        }
        return instance;
    }
}

/* Factory Pattern: Define an interface for creating an object,
but let subclasses alter the type of objects that will be created. */
interface RoomFactory {
    Room createRoom();
}

class EconomyRoomFactory implements RoomFactory {
    @Override
    public Room createRoom() {
        return new EconomyRoom();
    }
}

class StandardRoomFactory implements RoomFactory {
    @Override
    public Room createRoom() {
        return new StandardRoom();
    }
}

class SuiteRoomFactory implements RoomFactory {
    @Override
    public Room createRoom() {
        return new SuiteRoom();
    }
}

class KingRoomFactory implements RoomFactory {
    @Override
    public Room createRoom() {
        return new KingRoom();
    }
}

/* Observer Pattern: Define a one-to-many dependency between objects so that when one object changes state,
 all its dependents are notified and updated automatically.*/

interface BookingObserver {
    void update(String message);

    int getRoomNumber();
}

class Customer implements BookingObserver {
    private String name;
    private String surname;
    private int guestNo;
    private int roomNumber;

    public Customer(String name, String surname, int guestNo, int roomNumber) {
        this.name = name;
        this.surname = surname;
        this.guestNo = guestNo;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getGuestNo() {
        return guestNo;
    }

    @Override
    public void update(String message) {
        System.out.println("Customer " + name + " " + surname + " with no " + guestNo + ": " + message);
    }

    @Override
    public int getRoomNumber() {
        return roomNumber;
    }
}

class BookingSystem {
    private List<BookingObserver> observers = new ArrayList<>();

    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (BookingObserver observer : observers) {
            observer.update(message);
        }
    }

    public List<BookingObserver> getObservers() {
        return observers;
    }
}

/* Strategy Pattern: Define a family of algorithms, encapsulate each one, and make them interchangeable.
 Strategy lets the algorithm vary independently from clients that use it. */

interface PaymentStrategy {
    void pay(double amount);
}

class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid with crypto: $" + amount);
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid with credit card: $" + amount);
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid with cash: $" + amount);
    }
}

/* Decorator Pattern: Attach additional responsibilities to an object dynamically.
Decorators provide a flexible alternative to subclassing for extending functionality */

abstract class RoomDecorator implements Room {
    protected Room decoratedRoom;

    public RoomDecorator(Room decoratedRoom) {
        this.decoratedRoom = decoratedRoom;
    }

    @Override
    public void book() {
        decoratedRoom.book();
    }
}

class ExtraBedDecorator extends RoomDecorator {
    public ExtraBedDecorator(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public void book() {
        decoratedRoom.book();
        addExtraBed();
    }

    private void addExtraBed() {
        System.out.println("Extra Bed added to the room.");
    }
}

class DeskDecorator extends RoomDecorator {
    public DeskDecorator(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public void book() {
        decoratedRoom.book();
        addDesk();
    }

    private void addDesk() {
        System.out.println("Desk added to the room.");
    }
}

class TVDecorator extends RoomDecorator {
    public TVDecorator(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public void book() {
        decoratedRoom.book();
        addTV();
    }

    private void addTV() {
        System.out.println("TV added to the room.");
    }
}

class WiFiDecorator extends RoomDecorator {
    public WiFiDecorator(Room decoratedRoom) {
        super(decoratedRoom);
    }

    @Override
    public void book() {
        decoratedRoom.book();
        addWiFi();
    }

    private void addWiFi() {
        System.out.println("WiFi added to the room.");
    }
}

// Classes representing different types of rooms
interface Room {
    void book();
}

class EconomyRoom implements Room {
    @Override
    public void book() {
        System.out.println("Economy room booked.");
    }
}

class StandardRoom implements Room {
    @Override
    public void book() {
        System.out.println("Standard room booked.");
    }
}

class SuiteRoom implements Room {
    @Override
    public void book() {
        System.out.println("Suite room booked.");
    }
}

class KingRoom implements Room {
    @Override
    public void book() {
        System.out.println("King room booked.");
    }
}

// Main method to run the Hotel Management System
public class Design_Pattern {
    public static void main(String[] args) {
        // Create a singleton instance of HotelManager using the Singleton pattern.
        HotelManager hotelManager = HotelManager.getInstance();

        // Create a booking system to manage room bookings and notifications.
        BookingSystem bookingSystem = new BookingSystem();

        // Create a Scanner object for user input.
        Scanner scanner = new Scanner(System.in);

        // Variables to store user choices and room information.
        int roomChoice;
        int featureChoice;
        RoomFactory roomFactory;
        Room room = null;
        int roomNumber;

        // Main menu loop
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Book a Room");
            System.out.println("2. View Booked Room Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nRoom Type Menu:");
                    System.out.println("1. Economy Room");
                    System.out.println("2. Standard Room");
                    System.out.println("3. Suite Room");
                    System.out.println("4. King Room");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your room choice: ");
                    roomChoice = scanner.nextInt();

                    // Prompt the user to enter a room number.
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();

                    // Based on the room choice, create a room using the appropriate factory.
                    switch (roomChoice) {
                        case 1:
                            roomFactory = new EconomyRoomFactory();
                            room = roomFactory.createRoom();
                            // Allow the user to add features to the room.
                            handleFeatures(scanner, room, bookingSystem, roomNumber);
                            break;
                        case 2:
                            roomFactory = new StandardRoomFactory();
                            room = roomFactory.createRoom();
                            handleFeatures(scanner, room, bookingSystem, roomNumber);
                            break;
                        case 3:
                            roomFactory = new SuiteRoomFactory();
                            room = roomFactory.createRoom();
                            handleFeatures(scanner, room, bookingSystem, roomNumber);
                            break;
                        case 4:
                            roomFactory = new KingRoomFactory();
                            room = roomFactory.createRoom();
                            handleFeatures(scanner, room, bookingSystem, roomNumber);
                            break;
                        case 5:
                            // Go back to the main menu.
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                    break;

                case 2:
                    // View room details based on the room number.
                    System.out.print("Enter room number to view details: ");
                    roomNumber = scanner.nextInt();
                    displayRoomDetails(bookingSystem, roomNumber);
                    break;

                case 3:
                    // Exit the Hotel Management System.
                    System.out.println("Exiting the Hotel Management System. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }

        } while (choice != 3);

        // Close the scanner to avoid resource leaks.
        scanner.close();
    }

    // Method to handle the addition of features to the room during booking.
    private static void handleFeatures(Scanner scanner, Room room, BookingSystem bookingSystem, int roomNumber) {
        // Prompt the user to enter customer information.
        System.out.print("Enter customer name: ");
        String name = scanner.next();

        System.out.print("Enter customer surname: ");
        String surname = scanner.next();

        System.out.print("Enter customer number: ");
        int guestNo = scanner.nextInt();

        // Create a customer object and add it as an observer to the booking system.
        Customer customer = new Customer(name, surname, guestNo, roomNumber);
        bookingSystem.addObserver(customer);

        int featureChoice;
        do {
            System.out.println("\nRoom Feature Menu:");
            System.out.println("1. Extra Bed");
            System.out.println("2. Desk");
            System.out.println("3. TV");
            System.out.println("4. WiFi");
            System.out.println("5. Finish Booking");
            System.out.print("Enter your feature choice: ");
            featureChoice = scanner.nextInt();

            RoomDecorator roomDecorator;

            switch (featureChoice) {
                case 1:
                    // Decorate the room with an ExtraBedDecorator.
                    roomDecorator = new ExtraBedDecorator(room);
                    room = roomDecorator;
                    System.out.println("Extra Bed added to the room.");
                    break;
                case 2:
                    // Decorate the room with a DeskDecorator.
                    roomDecorator = new DeskDecorator(room);
                    room = roomDecorator;
                    System.out.println("Desk added to the room.");
                    break;
                case 3:
                    // Decorate the room with a TVDecorator.
                    roomDecorator = new TVDecorator(room);
                    room = roomDecorator;
                    System.out.println("TV added to the room.");
                    break;
                case 4:
                    // Decorate the room with a WiFiDecorator.
                    roomDecorator = new WiFiDecorator(room);
                    room = roomDecorator;
                    System.out.println("WiFi added to the room.");
                    break;
                case 5:
                    // Allow the user to choose a payment method.
                    handlePayment(scanner, customer);
                    // Complete the booking and notify observers.
                    room.book();
                    bookingSystem.notifyObservers("Room booked with selected features for " + name + " " + surname);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (featureChoice != 5);
    }

    // Method to handle the selection of a payment method.
    private static void handlePayment(Scanner scanner, Customer customer) {
        System.out.println("\nPayment Method Menu:");
        System.out.println("1. Credit Card Payment");
        System.out.println("2. Crypto Payment");
        System.out.println("3. Cash Payment");
        System.out.print("Enter your payment method choice: ");
        int paymentChoice = scanner.nextInt();

        PaymentStrategy paymentStrategy;

        switch (paymentChoice) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new CryptoPayment();
                break;
            case 3:
                paymentStrategy = new CashPayment();
                break;
            default:
                System.out.println("Invalid choice. Using default payment method: Credit Card Payment");
                paymentStrategy = new CreditCardPayment();
        }

        // Perform the payment.
        performPayment(scanner, customer, paymentStrategy);
    }

    // Method to perform the payment using the selected payment strategy.
    private static void performPayment(Scanner scanner, Customer customer, PaymentStrategy paymentStrategy) {
        System.out.print("Enter the payment amount: $");
        double amount = scanner.nextDouble();
        paymentStrategy.pay(amount);
        System.out.println("Payment successful for " + customer.getName() + " " + customer.getSurname());
    }

    // Method to display room details based on the room number.
    private static void displayRoomDetails(BookingSystem bookingSystem, int roomNumber) {
        for (BookingObserver observer : bookingSystem.getObservers()) {
            if (observer instanceof Customer) {
                Customer customer = (Customer) observer;
                if (customer.getRoomNumber() == roomNumber) {
                    System.out.println("\nRoom Details for Room Number " + roomNumber + ":");
                    System.out.println("Guest Name: " + customer.getName());
                    System.out.println("Guest Surname: " + customer.getSurname());
                    System.out.println("Guest Number: " + customer.getGuestNo());

                    // Display additional features if the room has been decorated.
                    if (observer instanceof RoomDecorator) {
                        RoomDecorator decorator = (RoomDecorator) observer;
                        while (decorator != null) {
                            if (decorator instanceof ExtraBedDecorator) {
                                System.out.println("Extra Bed");
                            } else if (decorator instanceof DeskDecorator) {
                                System.out.println("Desk");
                            } else if (decorator instanceof TVDecorator) {
                                System.out.println("TV");
                            } else if (decorator instanceof WiFiDecorator) {
                                System.out.println("WiFi");
                            }
                            decorator = (RoomDecorator) decorator.decoratedRoom;
                        }
                    }
                    return;
                }
            }
        }
        System.out.println("Room with number " + roomNumber + " not found.");
    }
}