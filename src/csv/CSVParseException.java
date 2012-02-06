package csv;

import java.text.ParseException;

/**
 * @author nikita
 * CSV file parse exception. Raises when something going wrong on
 *         parse phase.
 */
public class CSVParseException extends ParseException {

    /**
     * Constructs a CSVException with the specified detail message and offset. A
     * detail message is a String that describes this particular exception.
     * @param s
     *        the detail message
     * @param errorOffset
     *        the position where the error is found while parsing.
     */
    public CSVParseException(final String s, final int errorOffset) {
        super(s, errorOffset);
    }
}
