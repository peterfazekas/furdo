package hu.spa.controller;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import hu.spa.model.domain.Guest;
import hu.spa.model.domain.SpaSection;
import hu.spa.model.domain.Time;

public class Spa {

	private static final Time SIX = new Time(6, 0, 0);
	private static final Time NINE = new Time(9, 0, 0);
	private static final Time SIXTEEN = new Time(16, 0, 0);
	private static final Time TWENTY = new Time(20, 0, 0);

	private final List<Guest> guests;

	public Spa(List<Guest> guests) {
		this.guests = guests;
	}

	public String getFirstAndLastGuestExitTime() {
		return String.format("%s%s", getGuestExitTime("első", compareByDressingRoomExitTimeAsc()),
				getGuestExitTime("utolsó", compareByDressingRoomExitTimeDesc()));
	}

	private String getGuestExitTime(String text, Comparator<Guest> comparator) {
		return String.format("%nAz %s vendég %s-kor lépett ki az öltözőből.", text,
				getGuestByComparator(comparator).getDressingRoomExitTime());
	}

	private Comparator<Guest> compareByDressingRoomExitTimeAsc() {
		return Comparator.comparing(Guest::getDressingRoomExitTime);
	}

	private Comparator<Guest> compareByDressingRoomExitTimeDesc() {
		return compareByDressingRoomExitTimeAsc().reversed();
	}

	public String getFastVisitCount() {
		return String.format("A fürdőben %d vendég járt csak egy részlegen.", countFastVisit());
	}

	private Long countFastVisit() {
		return guests.stream().filter(Guest::isFastVisit).count();
	}

	public String getLongestStayDetail() {
		Guest guest = getGuestByComparator(compareByVisitDuration());
		return String.format("A legtöbb időt eltöltő vendég: %d. vendég %s", guest.getId(),
				LocalTime.ofSecondOfDay(guest.getVisitDuration()));
	}

	private Comparator<Guest> compareByVisitDuration() {
		return Comparator.comparing(Guest::getVisitDuration).reversed();
	}

	public String getIntervalVisitCount() {
		return String.format("%s%s%s", printIntervalVisitCount(SIX, NINE), printIntervalVisitCount(NINE, SIXTEEN),
				printIntervalVisitCount(SIXTEEN, TWENTY));
	}

	private String printIntervalVisitCount(Time begin, Time end) {
		return String.format("%n%d-%d óra között %d vendég", begin.getHour(), end.getHour(), countVisitors(begin, end));
	}

	private Long countVisitors(Time begin, Time end) {
		return guests.stream().filter(i -> i.isInTimeInterval(begin, end)).count();
	}

	public List<String> getSaunaVisitDetail() {
		return guests.stream().filter(Guest::isSaunaUsage).map(Guest::getSaunaTotalTime).collect(Collectors.toList());
	}

	private Guest getGuestByComparator(Comparator<Guest> comparator) {
		return guests.stream().sorted(comparator).findFirst().get();
	}

	public String getVisitedSectionsCount() {
		return countSectionVisit().entrySet().stream()
				.filter(i -> !i.getKey().equals(SpaSection.DRESSING_ROOM))
				.map(i -> i.getKey().getDescription() + i.getValue())
				.collect(Collectors.joining("\r\n"));
	}
	
	private Map<SpaSection, Long> countSectionVisit() {
		return guests.stream()
				.map(Guest::getVisitedSections)
				.flatMap(Set::stream)
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
	}
}
