package utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class name: DateConverter
 * Role: Utility (helper) class
 * Functionalities: convert Date object to String using SimpleDateFormat and vice ver sa
 * */
public class DateConverter {
    private static final String FORMAT = "MM/dd/yyyy";
    private static final SimpleDateFormat converter = new SimpleDateFormat(FORMAT);

    // Set constructor to private to avoid user initialize DateConverter instance
    private DateConverter() {
    }

    /**
     * Functionality: convert date under String format to a Date object
     * @param stringDate: Date under String format
     * @return Date object
     * @throws ParseException cannot parse string with wrong format
     */
    public static Date convertStringToDate(String stringDate) throws ParseException {
        converter.setLenient(false); // Avoid invalid date. Ex: 32/01/2022 will automatically parsed to 01/02/2022
        return converter.parse(stringDate);
    }

    /**
     * Functionality: convert a Date object to a String
     * @param date: Date object
     * @return a String with format "MM/dd/yyyy"
     */
    public static String convertDateToString(Date date) {
        if (date == null) { // Cannot be converted
            return "";
        }

        return converter.format(date);
    }
}
