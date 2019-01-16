import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayCountMain {

	public static void main(String[] args) throws Exception {

		String fromDate = "2018-10-20";
		String toDate = "2018-12-12";

		// String fromDate ="2017-10-20";
		// String toDate = "2018-12-12";

		long totaldays = getdayCount(fromDate, toDate);
		long daysUsingLocaldate = totalDaysUsingDate(fromDate, toDate);
		System.out.println("totaldays using my logic -->" + totaldays);
		System.out.println("totaldays using my LocalDate -->"
				+ daysUsingLocaldate);

	}

	/*
	 * This method calculated the total days between the 2 given dates
	 */
	private static long getdayCount(String fromDate, String toDate)
			throws Exception {
		// TODO Auto-generated method stub
		String[] fromArray = getFromDateArray(fromDate);
		int fromYear = Integer.parseInt(fromArray[0]);
		int fromMonth = Integer.parseInt(fromArray[1]);
		int fromDay = Integer.parseInt(fromArray[2]);

		String[] toArray = getFromDateArray(toDate);
		int toYear = Integer.parseInt(toArray[0]);
		int toMonth = Integer.parseInt(toArray[1]);
		int toDay = Integer.parseInt(toArray[2]);

		boolean validDates = true;
		if (toYear < fromYear
				| (toYear == fromYear && toMonth < fromMonth)
				| (toYear == fromYear && toMonth == fromMonth && toDay < fromDay)) {
			validDates = false;
		}
		if (!validDates) {
			throw new Exception("Please enter valid from and to dates");
		}

		boolean fromYearisLeap = false;
		boolean toYearisLeap = false;
		boolean yearIsLeap = false;

		if (toYear > fromYear) {
			toYearisLeap = isLeapYear(toYear);
			fromYearisLeap = isLeapYear(fromYear);

			long fd1 = getDaysInFromYear(fromMonth + 1, fromYearisLeap);
			long fd2 = getDaysInMonth(fromMonth, yearIsLeap) - fromDay;
			long totalDaysInFromYear = fd1 + fd2;
			long td1 = getDaysInToYear(toMonth - 1, toYearisLeap);
			long totalDaysInToYear = td1 + toDay;

			return totalDaysInFromYear + totalDaysInToYear;

		} else if (toYear == fromYear)
			yearIsLeap = false;
		// days from months in same year
		long fd1 = getDaysInFromYear(fromMonth + 1, yearIsLeap);
		long fd2 = (getDaysInMonth(fromMonth, yearIsLeap) - fromDay);
		long startTillFromMonthDays = fd1 + fd2;
		long td = getDaysInFromYear(toMonth - 1, yearIsLeap);
		long startTillToMonthDays = td + toDay;

		return startTillToMonthDays - startTillFromMonthDays;

	}

	/*
	 * This method gives the number of days for a given month
	 */
	private static int getDaysInMonth(int month, boolean yearIsLeap) {
		int days = 0;
		switch (month) {
		case 2:
			days = yearIsLeap ? 29 : 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
		}
		return days;
	}

	/*
	 * This method gives the total number of days from the given month till the
	 * end of the year
	 */

	private static int getDaysInFromYear(int fromMonth, boolean fromYearisLeap) {

		int totalDaysInFromYear = 0;
		int febDays = fromYearisLeap ? 29 : 28;
		switch (fromMonth) {
		case 1:
			totalDaysInFromYear = 7 * 31 + febDays + 4 * 30;
			break;
		case 2:
			totalDaysInFromYear = 6 * 31 + febDays + 4 * 30;
			break;
		case 3:
			totalDaysInFromYear = 6 * 31 + 4 * 30;
			break;
		case 4:
			totalDaysInFromYear = 5 * 31 + 4 * 30;
			break;
		case 5:
			totalDaysInFromYear = 5 * 31 + 3 * 30;
			break;
		case 6:
			totalDaysInFromYear = 4 * 31 + 3 * 30;
			break;
		case 7:
			totalDaysInFromYear = 4 * 31 + 2 * 30;
			break;
		case 8:
			totalDaysInFromYear = 3 * 31 + 2 * 30;
			break;
		case 9:
			totalDaysInFromYear = 2 * 31 + 2 * 30;
			break;
		case 10:
			totalDaysInFromYear = 2 * 31 + 30;
			break;
		case 11:
			totalDaysInFromYear = 31 + 30;
			break;
		case 12:
			totalDaysInFromYear = 30;
			break;

		}
		return totalDaysInFromYear;
	}

	/*
	 * This method gives the total number of days from the start of the year
	 * till the end of given month
	 */
	private static int getDaysInToYear(int toMonth, boolean toYearisLeap) {
		// TODO Auto-generated method stub
		int totalDaysInFromYear = 0;
		int febDays = toYearisLeap ? 29 : 28;
		switch (toMonth) {
		case 1:
			totalDaysInFromYear = 31;
			break;
		case 2:
			totalDaysInFromYear = 31 + febDays;
			break;
		case 3:
			totalDaysInFromYear = 2 * 31 + febDays;
			break;
		case 4:
			totalDaysInFromYear = 2 * 31 + febDays + 30;
			break;
		case 5:
			totalDaysInFromYear = 3 * 31 + febDays + 30;
			break;
		case 6:
			totalDaysInFromYear = 3 * 31 + febDays + 2 * 30;
			break;
		case 7:
			totalDaysInFromYear = 4 * 31 + febDays + 2 * 30;
			break;
		case 8:
			totalDaysInFromYear = 5 * 31 + febDays + 2 * 30;
			break;
		case 9:
			totalDaysInFromYear = 5 * 31 + febDays + 3 * 30;
			break;
		case 10:
			totalDaysInFromYear = 6 * 31 + febDays + 3 * 30;
			break;
		case 11:
			totalDaysInFromYear = 6 * 31 + febDays + 4 * 30;
			break;
		case 12:
			totalDaysInFromYear = 7 * 31 + febDays + 4 * 30;
			break;

		}
		return totalDaysInFromYear;
	}

	private static boolean isLeapYear(int year) {

		return year % 4 == 0 ? true : false;
	}

	private static String[] getFromDateArray(String date) {
		// TODO Auto-generated method stub
		return date.split("-");
	}

	private static long totalDaysUsingDate(String fromDate, String toDate) {

		LocalDate dateBefore = LocalDate.parse(fromDate);
		LocalDate dateAfter = LocalDate.parse(toDate);
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		System.out.println(noOfDaysBetween);

		return noOfDaysBetween;
	}

}
