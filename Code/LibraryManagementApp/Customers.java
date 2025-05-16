public class Customers {
    private String firstName,lastName;

    Customers(){
        firstName="";
        lastName="";
    };
    Customers(String firstName,String lastName){
        this.lastName=lastName;
        this.firstName=firstName;
    }

    public String getFirstName() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Customers{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
