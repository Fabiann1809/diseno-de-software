package person;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private String address;
    private LocalDate birthDate;
    private String email;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Person(String name, String address, LocalDate birthDate, String email) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate; }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate; }

    public String getEmail() {
        return email; }

    public void setEmail(String email) {
        this.email = email; }

    public int getAge() {
        if (this.birthDate != null) {
            return Period.between(this.birthDate, LocalDate.now()).getYears();
        }
        return 0;
    }

    public boolean isEmailValid() {
        return email != null && email.contains("@") && email.contains(".");
    }

    @Override
    public String toString() {
        return "Datos de la persona: se llama " + this.name +
                ", tiene " + this.getAge() + " años" +
                ", su correo es " + this.email + (isEmailValid() ? " (Válido)" : " (Inválido)") +
                " y vive en: " + this.address;
    }
}
