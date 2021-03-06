package HomeWork_L1;

public final class Person {
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String country;
    private final String address;
    private final String phone;
    private final int age;
    private final String gender;

    public Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.country = builder.country;
        this.address = builder.address;
        this.phone = builder.phone;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private final String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public Builder(String firstName, String lastName, String middleName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
        }

        public Person build() {
            return new Person(this);
        }

        public Builder countryOfResidence(String country) {
            this.country = country;
            return this;
        }

        public Builder residentialAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder homePhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder agePerson(int age) {
            this.age = age;
            return this;
        }

        public Builder personGender(String gender) {
            this.gender = gender;
            return this;
        }
    }

    public static class Main {
        public static void main(String[] args) {
            Person person = new Builder("Ivanov", "Ivan", "Ivanovich")
                    .countryOfResidence("Russia")
                    .residentialAddress("c. Moscow, st. Pushkina, h. 1")
                    .homePhone("123-456-789")
                    .agePerson(18)
                    .personGender("Man").build();

            System.out.println("First name: " + person.firstName
                    + "\nLast name: " + person.lastName
                    + "\nMiddle name: " + person.middleName
                    + "\nCountry: " + person.country
                    + "\nAddress: " + person.address
                    + "\nPhone: " + person.phone
                    + "\nAge: " + person.age
                    + "\nGender: " + person.gender
                    + "\n-----------------------------------------------------");
        }
    }
}
