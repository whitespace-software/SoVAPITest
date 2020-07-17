package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampGenerator {
    public static String make( String pattern ) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
