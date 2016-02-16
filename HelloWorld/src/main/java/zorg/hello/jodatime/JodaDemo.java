package zorg.hello.jodatime;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;

public class JodaDemo {

	public boolean isAfterPayDay(DateTime datetime) {
		if (datetime.getMonthOfYear() == 2) { // February is month 2!!
			return datetime.getDayOfMonth() > 26;
		}
		return datetime.getDayOfMonth() > 28;
	}

	public Days daysToNewYear(LocalDate fromDate) {
		LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
		return Days.daysBetween(fromDate, newYear);
	}

	public boolean isRentalOverdue(DateTime datetimeRented) {
		Period rentalPeriod = new Period().withDays(2).withHours(12);
		return datetimeRented.plus(rentalPeriod).isBeforeNow();
	}

	public static void main(String[] args) {
		JodaDemo demo = new JodaDemo();
		System.out.println(demo.isAfterPayDay(DateTime.now()));
		System.out.println(demo.daysToNewYear(LocalDate.now()));
		System.out.println(demo.isRentalOverdue(DateTime.now()));
	}
}
