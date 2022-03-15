package utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    private static final String FORMAT = "MM/dd/yyyy";

    /** Set it to private to avoid user initialize DateConverter Object */
    private DateConverter() {
    }

    public static Date convertStringToDate(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(stringDate);
    }

    public static String convertDateToString(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(date);
    }
}
