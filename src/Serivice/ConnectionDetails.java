package Serivice;

public final class ConnectionDetails {
    private final String url, user, pass;

    public ConnectionDetails(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return pass;
    }
}
