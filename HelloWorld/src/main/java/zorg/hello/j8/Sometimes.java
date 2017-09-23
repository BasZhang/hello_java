package zorg.hello.j8;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Sometimes {

	public static void main(String[] args) {
		Clock clock = Clock.systemUTC();
		System.out.println(clock.getZone());
		LocalDateTime now = LocalDateTime.now(clock);
		System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(now.format(DateTimeFormatter.ISO_ORDINAL_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_TIME));
		System.out.println(now.format(DateTimeFormatter.ISO_WEEK_DATE));
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
		System.out.println(now.format(ofPattern));
		System.out.println(ofPattern.parse("2017-3-24 6:18:0"));
		java.util.Date date = Date.from(now.toInstant(ZoneOffset.ofHours(8)));
		System.out.println(date);
		Instant now2 = Instant.now();
		System.out.println(Date.from(now2));
		Instant now3 = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.ofHours(8));
		System.out.println(Date.from(now3));
		
	}
}
