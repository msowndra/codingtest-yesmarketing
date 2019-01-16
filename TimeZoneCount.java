import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class TimeZoneCount {

	public static void main(String tz[]) throws Exception {

		if (tz.length != 2) {
			throw new Exception("Please enter from and to Timezones");
		}
		String fromTimeZone = tz[0];
		String toTimeZone = tz[1];

		getTotalZones(fromTimeZone, toTimeZone);

	}

	private static int getTotalZones(String fromTimeZone, String toTimeZone)
			throws Exception {
		int numberOfZones = 0;

		Set<ZoneOffset> zoneOffset = getOffsetSet();
		// System.out.println("Zone: " + zone + ", Offset: "+
		// offset.toString());

		// for()
		int zone1 = getZoneOffsetIndex(fromTimeZone, zoneOffset);
		int zone2 = getZoneOffsetIndex(toTimeZone, zoneOffset);
		if (zone1 != 0 | zone2 != 0) {
			numberOfZones = zone1 > zone2 ? (zone1 - zone2) : (zone2 - zone1);
			System.out
					.println("the total number of zones between the given 2 zones --> "
							+ numberOfZones);
		} else {
			throw new Exception("Please enter valid Timezones");
		}
		return numberOfZones;
	}

	/*
	 * This method gets the offset for each zone and adds them to a Treeset
	 */
	private static Set<ZoneOffset> getOffsetSet() {
		Set<ZoneOffset> zoneOffset = new TreeSet<ZoneOffset>();

		for (String zone : ZoneId.getAvailableZoneIds()) {

			ZoneId zoneId = ZoneId.of(zone);
			ZoneOffset offset = zoneId.getRules()
					.getOffset(LocalDateTime.now());
			zoneOffset.add(offset);

		}

		System.out.println("Offset list ----------" + zoneOffset);
		System.out.println("Offset size-------" + zoneOffset.size());
		return zoneOffset;
	}

	private static int getZoneOffsetIndex(String zone1,
			Set<ZoneOffset> zoneOffset) {
		int index;
		ZoneId zoneId = null;
		ZoneOffset zo1 = null;
		try {
			zoneId = ZoneId.of(ZoneId.SHORT_IDS.get(zone1));
			zo1 = zoneId.getRules().getOffset(LocalDateTime.now());
			System.out.println("Zone id" + zoneId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Please enter valid Zone");
		}

		index = new ArrayList<ZoneOffset>(zoneOffset).indexOf(zo1);

		return index;

	}
}
