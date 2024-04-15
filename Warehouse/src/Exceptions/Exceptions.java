package Exceptions;

public class Exceptions {
//ексепшин для випадків коли видаляєм те, чого не існує
    public static class DataNotFoundException extends Exception {
        public DataNotFoundException(String message) {
            super(message);
        }
    }
//ексепшин для випадків, коли додаємо те, що вже існує
    public static class DataAlreadyExistsException extends Exception {
        public DataAlreadyExistsException(String message) {
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