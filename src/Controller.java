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
        //records.remove(0);
        return records;
    }

    //this is how i am used to doing it through Java MOOC
    private void parseEstablishments(ArrayList<String> records) {
        for (String line : records) {
            String[] parts = line.split(",");
            String name = parts[0];
            String firstLine = parts[1];
            String postcode = parts[2];
            int maxOccupancy = Integer.valueOf(parts[3]);
            Establishment establishment = new Establishment(
                    name, firstLine, postcode, maxOccupancy);
            addEstablishment(establishment);
        }
    }

    public void addEvent(Event event) {
        if (!this.Events.contains(event.eventID())) {
            /* I chose eventID because for the user, they could attend multiple events.
            The establishment will obviously be frequented many times.
            eventID made more sense for checking if the event itself has been registered
            though this is a randomly assigned ID, so couldn't be entered manually
            for the duplication to be checked
             */
            this.Events.add(event);
        }
    }

    public void addEstablishment(Establishment establishment) {
        if (!this.Establishments.contains(establishment)) {
            this.Establishments.add(establishment);
        }
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
