public class HomeAdress implements Adress{

    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String number;

    public HomeAdress(String street, String city, String country, String zipCode, String number) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.number = number;
    }
    @Override
    public String getStreet() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public String getZipCode() {
        return null;
    }

    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public void setStreet(String street) {

    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setCountry(String country) {

    }

    @Override
    public void setZipCode(String zipCode) {

    }

    @Override
    public void setNumber(String number) {

    }
}
