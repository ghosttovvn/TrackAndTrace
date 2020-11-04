import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

    private User user;
    private LocalDate date;
    private LocalDateTime time;
    private int size;
    private Establishment establishment;
    //private DateTimeFormatter formatDate;
    private DateTimeFormatter formatTime;
    private String eventID;
    //created eventID so the ID doesn't change each time single line is called

    public Event(User user, LocalDate date, int partyNumber, Establishment establishment) {
        this.user = user;
        this.size = partyNumber;
        this.establishment = establishment;
        //this.formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.formatTime = DateTimeFormatter.ofPattern("HH:mm");
        this.date = date;
        this.time = LocalDateTime.now();
        /*assuming we just use the current time, as that is what was used
        in the demo*/
        this.eventID = eventID();
    }

    public Event(User user, int partyNumber, Establishment establishment) {
        this.user = user;
        this.size = partyNumber;
        this.establishment = establishment;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public int getPartySize() {
        return this.size;
    }

    public Establishment getEstablishment() {
        return this.establishment;
    }

    public String singleLine() {
        return "Event ID: " + this.eventID + " | Recorded User";
    }

    public String eventID() {
        //event ID. Relies on the chance that no two are the same...
        double random = Math.random();
        double randomMultiplier = Math.random() * 1000; //x1000 so the ID is longer
        //as Math.random() creates a decimal
        int total = (int) (random * randomMultiplier);
        return "EV" + total;//event ID
    }

    @Override
    public String toString() {
        return String.format("%s\n\t" +
                        "%s\n\t" +
                        "Date: %s\n\t" +
                        "Time %s\n\t" +
                        "Party size: %s\n\t" +
                        "Establishment: \n\t\t%s",

                singleLine(), user.details(), this.date,
                this.time,
                this.size, establishment.toString());
    }
}
