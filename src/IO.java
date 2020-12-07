import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*
 *
 * *
 * **
 * ***
 * **** Written by Mia Coupland (with help from Jordan Barnes)
 * ***
 * **
 * *
 */


public class IO {
    private Scanner scanner = new Scanner(System.in);
    public final Controller controller;
    //formatting for dob and event
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public IO() throws FileNotFoundException {
        File establishmentsCSV = new File(Controller.class.getResource("establishments.csv").getFile());
        this.controller = new Controller(establishmentsCSV);
    }

    //debug method is looooong
    public void debug() {
        //creating sampleDate to use for DOB
        LocalDate sampleDate = LocalDate.parse("05-08-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate sampleEventDate = LocalDate.parse("20-11-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        //user objects tests
        User user0 = new User("Sample Person", sampleDate,
                "sampleemailthatisreallyreallyreallyreallyreallyreallylong@mail.com",
                "07827573649");//will work
        User user1 = new User("Another Sample Person", sampleDate,
                "m@googleyahoobtinternethotmail.co.uk.vu.ac.uk",
                "+44826474928");//won't work, international phone number
        User user2 = new User("Third Sample Person", sampleDate,
                "m@n.com", "0729492928493928229823932932");//won't work, phone number too long

        //establishment object tests
        Establishment establishment0 = new Establishment("Sample Place",
                "10 Sample St", "S42 P73", 100);//works
        Establishment establishment1 = new Establishment("Another sample place",
                "really really really really really long first address line",
                "NE203 5019D9283E9i38P", 500000);//works despite length
        Establishment establishment2 = new Establishment("third sample place",
                "adl", "ne1", 1);//works

        //Event object tests
        Event event0 = new Event(user0, sampleEventDate, 5, establishment0);
        Event event1 = new Event(user1, sampleEventDate, 200, establishment1);
        //in future you shouldn't be able to exceed max occupancy on a given day etc
        Event event2 = new Event(user2, sampleEventDate, 10, establishment2);
        /*testing the print methods for the newly created objects
        won't go through checks when input directly, however
        as the checks for phone number length etc happen in the menu
        Flaw is that data input from CSV or like this will not be checked.
        Could implement checks inside each class.
        Can safely assume that the real life use of this program would be
        command line and the CSV information is accurate?*/

        //testing output for each object
        user0.singleLine();
        user0.details();
        user1.singleLine();
        user1.details();
        user2.singleLine();
        user2.details();
        establishment0.toString();
        establishment1.toString();
        establishment2.toString();
        event0.toString();
        event0.singleLine();
        event1.toString();
        event1.singleLine();
        event2.toString();
        event2.singleLine();
    }

    public void startMenu() {
        System.out.println("Welcome to track and trace!");

        while (true) {
            System.out.println("Please enter a number from the menu:" +
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
                    filters();
                    break;
                case 4:
                    System.out.println("\nYou have selected 'Print Events'" +
                            "\nHere is a list of all of the events:");
                    for (Event event : controller.getEvents()) {
                        System.out.println(event.toString());
                    }
                    break;
                case 5:
                    System.out.println("\nYou have selected 'Print Establishments'" +
                            "\nHere is a list of all of the establishments:");
                    for (Establishment establishment : controller.getEstablishments()) {
                        System.out.println(establishment.toString());
                    }
                    break;
                case 6:
                    System.out.println("\nExiting the program");
                    System.exit(0);
                default:
                    System.out.println("\nSorry, please select a valid option from the list above.");
                    startMenu();
            }
        }
    }

    public void filters() {
        System.out.println("You have selected 'Filter Records'." +
                "\nPlease pick an option:" +
                "\n1. Records by Establishment" +
                "\n2. Records by Date" +
                "\n3. Records by Name" +
                "\n4. Go back");
        int choice = Integer.valueOf(scanner.nextLine());
        switch (choice) {
            case 1:
                recordsByEstablishment();
                break;
            case 2:
                recordsByDate();
                break;
            case 3:
                recordsByName();
                break;
            case 4:
                System.out.println("\nReturning to the main menu...");
                break;
            default:
                System.out.println("\nPlease select a valid option from the menu above.");
                choice = Integer.valueOf(scanner.nextLine());
                break;
        }
    }

    public void recordsByEstablishment() {
        System.out.println("You have selected 'Records by Establishment'" +
                "\nWhat is the name of the establishment?");
        String establishmentName = scanner.nextLine();
        System.out.println("\nHere are the records for events at " + establishmentName);
        for (Event event : controller.getByEstablishments(establishmentName)) {
            System.out.println(event.toString());
        }
    }

    public void recordsByDate() {
        System.out.println("You have selected 'Records by Date'" +
                "\nWhat date do you want to filter by? " +
                "Please use the format DD-MM-YYYY");
        String date = scanner.nextLine();
        LocalDate filterDate = LocalDate.parse(date, format); //validity test
        System.out.println("\nHere are events on the date " + filterDate);
        for (Event event : controller.getByDate(filterDate)) {
            System.out.println(event.toString()); //get records by their date
        }
    }

    public void recordsByName() {
        System.out.println("You have selected 'Records by Name'" +
                "\nWhat name do you want to filter by?");
        String name = scanner.nextLine();
        System.out.println("\nWhat is the email you want to filter by?");
        String email = scanner.nextLine();
        while (!email.contains("@")) {
            System.out.println("\nPlease enter a valid email address.");
            email = scanner.nextLine();
        }
        for (Event event : controller.getByName(name, email)) {
            System.out.println(event.toString());//get records by their names
        }
    }

    public void recordEvent() {
        System.out.println("Please enter the following data as prompted.");
        System.out.println("What is your full name?");
        String name = scanner.nextLine();
        System.out.println("Thank you. what is your date of birth? (Please copy the format DD-MM-YYYY)");
        String date = scanner.nextLine();
        LocalDate dob = LocalDate.parse(date, format); //change dob from string to date. test for validity
        while (dob.isAfter(LocalDate.now())) {
            System.out.println("Please enter a valid date of birth in format DD-MM-YYYY");
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
        System.out.println("On what date is this event? (Please enter in format dd-mm-yyyy)");
        String eventDay = scanner.nextLine();
        LocalDate eventDate = LocalDate.parse(eventDay, format);
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
        controller.addEstablishment(establishment);
        controller.addEvent(event);

        System.out.println(event.toString());
        System.out.println("Continue? Y for yes, N for no:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                startMenu();
            } else if (input.equals("N") || input.equals("n")) {
                System.exit(0); // 0 for successful termination
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
