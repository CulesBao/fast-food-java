package Config;

public class Session {
    public static int accountId;
    public static String fullName;
    public static String role;

    public static void setSession(int accountId, String fullName, String role) {
        Session.accountId = accountId;
        Session.fullName = fullName;
        Session.role = role;
    }

    public static int getAccountId() {
        return accountId;
    }
    public static String getFullName() {
        return fullName;
    }
    public static String getRole() {
        return role;
    }
    public static void clearSession() {
        accountId = 0;
        fullName = null;
        role = null;
    }
    public static boolean isLoggedIn() {
        return accountId != 0;
    }
}
