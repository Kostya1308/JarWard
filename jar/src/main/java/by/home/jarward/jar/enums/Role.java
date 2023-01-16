package by.home.jarward.jar.enums;

public enum Role {

    STUDENT("student"), TEACHER("teacher"), MANAGER("manager"), ADMIN("admin");

    Role(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
