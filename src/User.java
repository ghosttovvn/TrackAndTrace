import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class User {
    private String name;
    private LocalDate dob;
    private String phoneNo;
    private String email;
    private int age;
    private DateTimeFormatter format;

    public User(String name, LocalDate dob, String email, String phoneNo) {
        this.name = name;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.email = email;
        this.age = getAge(this.dob, LocalDate.now());
    }

    //To print the single line displaying name and phone number
    public String singleLine() {
        return this.name + " | " + "Phone number: " + phoneNo;
    }

    //getters for Name, Dob, phoneNo, and Email
    public String getName() {
        return this.name;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getEmail() {
        return this.email;
    }

    //code for 'getAge' found from StackOverflow at:
    //https://stackoverflow.com/questions/1116123/how-do-i-calculate-someones-age-in-java
    public int getAge(LocalDate dob, LocalDate currentDate) {
        if ((dob != null) && (currentDate != null)) {
            return Period.between(dob, currentDate).getYears();
        } else {
            return 0;
        }
    }

    //return information about the user
    public String details() {
        return String.format("Name: %s\n\t" +
                        "Date of Birth: %s\n\t" +
                        "Email: %s\n\t" +
                        "Phone Number: %s\n\t" +
                        "Age: %d",

                name,
                dob,
                email,
                phoneNo,
                age);
    }
}