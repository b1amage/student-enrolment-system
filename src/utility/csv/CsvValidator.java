package utility.csv;

/**
 * Class name: CsvValidator
 * Role: Utility class
 * Functionalities: validate line in csv file
 * */
public class CsvValidator {
    private static final int FIELD_PER_ROW = 7;
    private static final String SEPARATOR = ",";

    private CsvValidator() {
    }

    /**
     * Functionality: Check if a row is valid (use "," as separator, enough field, field is bnot null)
     * @param row a row of csv file
     * @return true if row is valid, false if row is not valid
     */
    public static boolean isAValidCsvRow(String row) {
        String[] fields = row.split(SEPARATOR);

        if (fields.length != FIELD_PER_ROW) return false;

        for (String field : fields) {
            if (field.isEmpty()) return false;
        }

        return true;
    }

}
