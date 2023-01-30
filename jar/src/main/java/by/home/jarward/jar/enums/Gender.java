package by.home.jarward.jar.enums;

public enum Gender {

    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
