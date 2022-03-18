package exception;

/**
 * Class name: WrongCsvFormatException
 * Role: Exception
 * Functionality: Custom exception for enhancing user-friendly of the app
 */
public class WrongCsvFormatException extends Exception {
    public WrongCsvFormatException() {
        super("There is something wrong on the format of your CSV file!");
    }
}
