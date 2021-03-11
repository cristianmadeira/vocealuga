package br.cefetrj.mg.bsi.vocealuga.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utils {

	public static Timestamp converterLocalDateTimeToTimestamp(LocalDateTime dateTime){
		return Timestamp.valueOf(dateTime);
	}
	public static LocalDateTime converterTimestampToLocalDateTime(Timestamp timestamp){
		return timestamp.toLocalDateTime();
	}
}
