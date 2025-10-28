package enums;

public enum Users {
    STANDARD_USER("standard_user", "secret_sauce");

    private final String username;
    private final String password;

    Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
