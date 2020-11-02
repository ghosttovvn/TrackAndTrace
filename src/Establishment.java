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

    @Override
    public String toString() {
        return "Establishment" +
                "\n\t\tName: " + this.name +
                "\n\t\tAddress: " + this.address + " "+ this.postcode;
    }
}
