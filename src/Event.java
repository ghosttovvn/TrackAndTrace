import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {

    private User user;
    private LocalDate date;
    //include time
    private int size;
    private Establishment establishment;
    private DateTimeFormatter format;

    public Event(User user, String date, int partyNumber, Establishment establishment) {
        this.user = user;
        this.size = partyNumber;
        this.establishment = establishment;
        this.format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(date, format);
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

    public int getPartySize() {
        return this.size;
    }

    public Establishment getEstablishment() {
        return this.establishment;
    }

    public String singleLine() {
        return "Event ID: " + eventID() + " | Recorded User";
    }

    public String eventID() {
        return "";//event ID
    }

    public String eventPrint() {
        return String.format("%s\n\t" +
                        "%s\n\t" +
                         "Date: %s\n\t" +
                        //"Time %s\n\t" +
                        "Party size: %s\n\t" +
                        "%s\n\t",

                singleLine(), user.details(), date,
                //time.format("HH:MM"),
                this.size, establishment.toString());
    }
}
