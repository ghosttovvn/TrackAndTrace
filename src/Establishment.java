public class Establishment {

    private String name;
    private String address;
    private String postcode;
    private int maxOccupancy;

    public Establishment(String name, Address address, int maxOccupancy) {
        this.name = name;
        this.address = address.getFirstLine();
        this.postcode = address.getPostcode();
        this.maxOccupancy = maxOccupancy;
    }

    public Establishment(String name, String firstLineAddress, String postcode, int maxOccupancy) {
        this.name = name;
        this.address = firstLineAddress;
        this.postcode = postcode;
        this.maxOccupancy = maxOccupancy;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public int maxOccupancy() {
        return this.maxOccupancy;
    }

    @Override
    public String toString() {
        return "\n\t\tName: " + this.getName() +
                "\n\t\tAddress: " + this.getAddress() + " " + this.getPostcode();
    }
}
