import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public final class Controller {
    private ArrayList<Event> Events;
    private ArrayList<Establishment> Establishments;
    private Scanner scanner;

    public Controller() {
        this.Events = new ArrayList<>();
        this.Establishments = new ArrayList<>();
    }

    public Controller(String establishmentCSVFileURI) {
        scanner = new Scanner(establishmentCSVFileURI);

    }

    //greatly helped with this code by Jordan Barnes' demo and sample code
    //general ideas and structure from the University of Helsinki's Java MOOC
    public ArrayList<String> readCSV(File establishmentCSVFileURI) throws FileNotFoundException {
        ArrayList<String> records = new ArrayList<>();

        while (scanner.hasNextLine()) {
            records.add(scanner.nextLine());
        }
        return records;
    }

    public void addEvent(Event event) {
        this.Events.add(event);
    }

    public void addEstablishment(Establishment establishment) {
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
            if (event.getEstablishment().equals(nameOfEstablishment)) {
                hasEstablishment.add(event);
            }
        }
        return hasEstablishment;
    }

    public ArrayList<Event> getByDate(LocalDate date) {
        ArrayList<Event> hasDate = new ArrayList<>();
        for (Event event: this.Events) {
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
