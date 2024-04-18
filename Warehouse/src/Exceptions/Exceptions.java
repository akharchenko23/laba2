package Exceptions;

public class Exceptions {
    //ексепшини для випадків коли видаляєм те, чого не існує
    public static class ProductNotFoundException extends Exception {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class GroupNotFoundException extends Exception {
        public GroupNotFoundException(String message) {
            super(message);
        }
    }

    //ексепшини для випадків, коли додаємо те, що вже існує
    public static class ProductAlreadyExistsException extends Exception {
        public ProductAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class GroupAlreadyExistsException extends Exception {
        public GroupAlreadyExistsException(String message) {
            super(message);
        }
    }

    //ексепшин спеціально, якщо при пошуку нічого не знаходить
    public static class NothingFoundException extends Exception {
        public NothingFoundException(String message) {
            super(message);
        }
    }


}