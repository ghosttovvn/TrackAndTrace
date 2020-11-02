import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//make class immutable?
public class IO {
    Scanner scanner = new Scanner(System.in);
    Controller controller;
    //formatting for dob and event
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public IO () {
        this.controller = new Controller();
    }

    public static void main () {
        IO io = new IO();
        io.startMenu();
    }

    public void startMenu() {

        while (true) {
            System.out.println("Welcome to Track and Trace!" +
                    "\nPlease enter a number to begin:" +
                    "\n1. Record an Event" +
                    "\n2. Add an Establishment" +
                    "\n3. Filter Records" +
                    "\n4. Print Events" +
                    "\n5. Print Establishments" +
                    "\n6. Exit the program");
            int input = Integer.valueOf(scanner.nextLine());

            switch (input) {
                case 1:
                    recordEvent();
                    break;
                case 2:
                    System.out.println("\nYou have selected 'Add Establishment'.");
                    recordEstablishment();
                    break;
                case 3:
                    System.out.println("You have selected 'Filter Records'." +
                            "Please pick an option:" +
                            "1. Records by Establishment" +
                            "2. Records by Date" +
                            "3. Records by Name" +
                            "4. Go back");
                    int choice = Integer.valueOf(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("You have selected 'Records by Establishment'" +
                                    "What is the name of the establishment?");
                            String establishmentName = scanner.nextLine();
                            controller.getByEstablishments(establishmentName);
                            break;
                        case 2:
                            System.out.println("You have selected 'Records by Date'" +
                                    "\nWhat date do you want to filter by? " +
                                    "Please use the format DD/MM/YYYY");
                            while (true) {
                                String date = scanner.nextLine();
                                try {
                                    LocalDate filterDate = LocalDate.parse(date, format); //validity test
                                    controller.getByDate(filterDate); //get records by their date
                                } catch (Exception e) {
                                    System.out.println("The date was not input in the correct format." +
                                            "\nTry again.");
                                }
                            }
                        case 3:
                            System.out.println("You have selected 'Records by Name'" +
                                    "\nWhat name do you want to filter by?");
                            String name = scanner.nextLine();
                            System.out.println("What is the email you want to filter by?");
                            String email = scanner.nextLine();
                            while (!email.contains("@")) {
                                System.out.println("Please enter a valid email address.");
                                email = scanner.nextLine();
                            }
                            controller.getByName(name, email); //get records by their names
                            break;
                        case 4:
                            System.out.println("Returning to the main menu...");
                            startMenu();
                        default:
                            System.out.println("Please select a valid option from the menu above.");
                            choice = Integer.valueOf(scanner.nextLine());
                            startMenu();
                    }
                case 4:
                    System.out.println("You have selected 'Print Events'" +
                            "\nHere is a list of all of the events:");
                    controller.getEvents();
                    break;
                case 5:
                    System.out.println("You have selected 'Print Establishments'" +
                            "\nHere is a list of all of the establishments:");
                    controller.getEstablishments();
                case 6:
                    System.out.println("Exiting the program");
                    break;
                default:
                    System.out.println("Sorry, please select a valid option from the list above.");
                    startMenu();
            }
        }



    }

    public void recordEvent() {
        System.out.println("Please enter the following data as prompted.");
        System.out.println("What is your full name?");
        String name = scanner.nextLine();
        System.out.println("Thank you. what is your date of birth? (Please enter in format DD/MM/YYYY)");
        String date = scanner.nextLine();
        LocalDate dob = LocalDate.parse(date, format); //change dob from string to date. test for validity
        while (dob.isAfter(LocalDate.now())) {
            System.out.println("Please enter a valid date of birth in format DD/MM/YYYY");
            date = scanner.nextLine();
            dob = LocalDate.parse(date, format);
        }
        System.out.println("What is the best contact number for you?");
        String phoneNo = scanner.nextLine();
        while (phoneNo.length() != 11 || phoneNo.contains("+")) {
            System.out.println("Please enter a valid phone number");
            phoneNo = scanner.nextLine();
        }
        System.out.println("What is your email?");
        String email = scanner.nextLine();
        while (!email.contains("@")) {
            System.out.println("Please enter a valid email address.");
            email = scanner.nextLine();
        }
        System.out.println("On what date and time is this event? (Please enter in format dd/mm/yyyy 00:00)");
        String eventDate = scanner.nextLine();
        System.out.println("How big is the party size?");
        int partySize = Integer.valueOf(scanner.nextLine());
        System.out.println("Where is the name of the establishment where the event is taking place?");
        String placeName = scanner.nextLine();
        System.out.println("What is the address of the establishment: " + placeName + "?");
        String addressLine = scanner.nextLine();
        System.out.println("What is the postcode?");
        String postcode = scanner.nextLine();
        System.out.println("What is the maximum occupancy?");
        int maxOccupancy = Integer.valueOf(scanner.nextLine());

        Address eventAddress = new Address(addressLine, postcode);
        Establishment establishment = new Establishment(placeName, eventAddress, maxOccupancy);
        User user = new User(name, dob, email, phoneNo);
        Event event = new Event(user, eventDate, partySize, establishment);

        System.out.println(event.eventPrint());
        System.out.println("Continue? Y for yes, N for no:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                startMenu();
            } else if (input.equals("N") || input.equals("n")) {
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }

    public void recordEstablishment() {
        System.out.println("Please enter the following information as prompted." +
                "\nWhat is the name of the establishment?");
        String placeName = scanner.nextLine();
        System.out.println("What is the address of the establishment " + placeName + "?");
        String addressLine = scanner.nextLine();
        System.out.println("What is the postcode?");
        String postcode = scanner.nextLine();
        System.out.println("Finally, what is the maximum occupancy?");
        int maxOccupancy = Integer.valueOf(scanner.nextLine());

        Address eventAddress = new Address(addressLine, postcode); //creates an address for the establishment
        Establishment establishment = new Establishment(placeName, eventAddress, maxOccupancy); //creates an establishment
        controller.addEstablishment(establishment); //stores the establishment
    }



}
