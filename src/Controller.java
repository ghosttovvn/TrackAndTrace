import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public final class Controller {
    private ArrayList<Event> Events;
    private ArrayList<Establishment> Establishments;

    public Controller() {
        this.Events = new ArrayList<>();
        this.Establishments = new ArrayList<>();
    }

    //constructor automatically reads and parses the CSV if called with a file
    public Controller(File establishmentCSVFileURI) throws FileNotFoundException {
        this.Events = new ArrayList<>();
        this.Establishments = new ArrayList<>();
        parseEstablishments(readCSV(establishmentCSVFileURI));
    }

    //greatly helped with this code by Jordan Barnes' demo and sample code
    //general structure from the University of Helsinki's Java MOOC
    public ArrayList<String> readCSV(File establishmentCSVFileURI) throws FileNotFoundException {
        Scanner scanner = new Scanner(establishmentCSVFileURI);

        ArrayList<String> records = new ArrayList<>();

        while (scanner.hasNextLine()) {
            records.add(scanner.nextLine());
        }
        if (records.size() > 0) {
            records.remove(0);
        }
        return records;
    }

    /* Main to test with
    public static void main(String[] args) throws FileNotFoundException {

        Controller c = new Controller();

        File EstablishmentCSV = new File(Controller.class.getResource(
                "establishments.csv").getFile());

        c.parseEstablishments(c.readCSV(EstablishmentCSV));

    } */

    public void parseEstablishments(ArrayList<String> records) {
        for (String line : records) {
            String elements[] = line.split(",");
            Address address = new Address(elements[1], elements[2]);
            Establishment establishment = new Establishment(elements[0],
                    address, Integer.parseInt(elements[3]));
            addEstablishment(establishment);
        }
    }

    public void addEvent(Event event) {
        if (!this.Events.contains(event.eventID())) {
            this.Events.add(event);
            /* I chose eventID because the user could attend multiple events,
            the establishment will obviously be frequented many times, and
            eventID made more sense for checking if the event itself has been registered.
            Although, this is a randomly assigned ID so couldn't be entered manually
            for the duplication to be checked
             */
        }
    }

    public void addEstablishment(Establishment establishment) {
        for (Establishment est : Establishments) {
            //tests to see if establishment already exists. plain .equals() doesn't work here
            if (est.getPostcode().equals(establishment.getPostcode()) &&
                    est.getAddress().equals(establishment.getAddress()) &&
                    est.getName().equals(establishment.getName())) {
                //learned how to make this monstrosity from Java MOOC
                return;
            }
        }
        this.Establishments.add(establishment);
    }

    public ArrayList<Establishment> getEstablishments() {
        return this.Establishments;
    }

    public ArrayList<Event> getEvents() {
        return this.Events;
    }

    public ArrayList<Event> getByEstablishments(String nameOfEstablishment) {
        ArrayList<Event> hasEstablishment = new ArrayList<>();
        for (Event event : this.Events) {
            if (event.getEstablishment().getName().equals(nameOfEstablishment)) {
                hasEstablishment.add(event);
            }
        }
        return hasEstablishment;
    }

    public ArrayList<Event> getByDate(LocalDate date) {
        ArrayList<Event> hasDate = new ArrayList<>();
        for (Event event : this.Events) {
            if (event.getDate().equals(date)) {
                hasDate.add(event);
            }
        }
        return hasDate;
    }

    public ArrayList<Event> getByName(String name, String email) {
        ArrayList<Event> hasName = new ArrayList<>();
        for (Event event : this.Events) {
            if (event.getUser().getName().equals(name) && event.getUser().getEmail().equals(email)) {
                hasName.add(event);
            }
        }
        return hasName;
    }

}
