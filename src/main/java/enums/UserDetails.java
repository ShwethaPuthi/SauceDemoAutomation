package enums;

public enum UserDetails {
    DETAILS("Shwetha","V Puthi","560054");

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    UserDetails(String firstName, String lastName, String postalCode){
        this.firstName=firstName;
        this.lastName=lastName;
        this.postalCode=postalCode;
    }
    public String getFirstname(){ return firstName;}
    public String getLastname(){ return lastName;}

    public String getPinCode(){ return postalCode;}

}
