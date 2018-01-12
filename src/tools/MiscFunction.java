package tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public final class MiscFunction {
	
	private static final SimpleDateFormat theSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static String getIDByCurrentTime()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return theSimpleDateFormat.format(timestamp);
	}
}

