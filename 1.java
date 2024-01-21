import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class OnlineReservationSystem {

    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (true) {
            System.out.println("===== Online Reservation System =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loggedIn = login(scanner);
                    if (loggedIn) {
                        reservationSystem(scanner);
                    }
                    break;
                case 2:
                    System.out.println("Exiting Online Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        userCredentials.put("admin", "password");
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    private static void reservationSystem(Scanner scanner) {
        while (true) {
            System.out.println("===== Reservation System =====");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    System.out.println("Logging out. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        System.out.print("Enter class type: ");
        String classType = scanner.next();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.next();
        System.out.print("Enter departure place: ");
        String fromPlace = scanner.next();
        System.out.print("Enter destination: ");
        String toDestination = scanner.next();

        String reservationKey = generateReservationKey(trainNumber, dateOfJourney);
        Reservation reservation = new Reservation(name, trainNumber, classType, dateOfJourney, fromPlace, toDestination);

        reservations.put(reservationKey, reservation);

        System.out.println("Reservation successful! Your reservation key is: " + reservationKey);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter your reservation key: ");
        String reservationKey = scanner.next();

        if (reservations.containsKey(reservationKey)) {
            Reservation reservation = reservations.get(reservationKey);
            System.out.println("Reservation details: " + reservation);
            System.out.print("Do you want to confirm cancellation? (Y/N): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(reservationKey);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("Reservation cancellation aborted.");
            }
        } else {
            System.out.println("Invalid reservation key. Please try again.");
        }
    }

    private static String generateReservationKey(int trainNumber, String dateOfJourney) {
        return trainNumber + "_" + dateOfJourney;
    }

    private static class Reservation {
        private String name;
        private int trainNumber;
        private String classType;
        private String dateOfJourney;
        private String fromPlace;
        private String toDestination;

        public Reservation(String name, int trainNumber, String classType, String dateOfJourney, String fromPlace, String toDestination) {
            this.name = name;
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.toDestination = toDestination;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "name='" + name + '\'' +
                    ", trainNumber=" + trainNumber +
                    ", classType='" + classType + '\'' +
                    ", dateOfJourney='" + dateOfJourney + '\'' +
                    ", fromPlace='" + fromPlace + '\'' +
                    ", toDestination='" + toDestination + '\'' +
                    '}';
        }
    }
}