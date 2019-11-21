package Lesson_9;

public class Student {

    private String firstName;
    private String secondName;
    private String lastName;
    private String birthDay;

    public Student(String firstName, String secondName, String lastName, String birthDay) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName + " " + lastName + " " + birthDay;
    }
}
