public class Address {

    private String firstLine;
    private String postcode;

    public Address(String line1, String postcode) {
        this.firstLine = line1;
        this.postcode = postcode;
    }

    public String getFirstLine() {
        return this.firstLine;
    }

    public String getPostcode() {
        return this.postcode;
    }
}
