package ru.emesless.searchplayers.util;

/**
 * todo Document type Views
 */
public class Views {
    public interface UserId {
    }

    public interface UserInfo extends UserId {
    }

    public interface Request extends UserInfo {
    }

    public interface UserRole extends UserInfo {
    }
}
