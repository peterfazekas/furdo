package hu.spa.model.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Guest {

	private final int id;
	private final List<Section> sections;
	
	public Guest(int id, List<Section> sections) {
		this.id = id;
		this.sections = sections;
	}

	public int getId() {
		return id;
	}

	public Time getDressingRoomExitTime() {
		return getSection(SpaSection.DRESSING_ROOM).get().getFirstEnterTime();
	}
	
	private Optional<Section> getSection(SpaSection spaSection) {
		return sections.stream().filter(i -> i.getSpaSection().equals(spaSection)).findAny();
	}
	
	public boolean isFastVisit() {
		return sections.stream().mapToInt(Section::visitCount).sum() == 2;
	}
	
	public Integer getVisitDuration() {
		return getSection(SpaSection.DRESSING_ROOM).get().getTotalDuration();
	}
	
	public boolean isInTimeInterval(Time startTime, Time endTime) {
		return getDressingRoomExitTime().isBefore(endTime) && getDressingRoomExitTime().isAfter(startTime);
	}
	
	public boolean isSaunaUsage() {
		return getSection(SpaSection.SAUNA).isPresent();
	}
	
	public String getSaunaTotalTime() {
		return getSection(SpaSection.SAUNA)
		.map(Section::getTotalDuration)
		.map(this::printSaunaVisitTime)
		.orElse(null);
	}
	
	private String printSaunaVisitTime(Integer duration) {
		return String.format("%d %s", id, LocalTime.ofSecondOfDay(duration));
	}
	
	public Set<SpaSection> getVisitedSections() {
		return sections.stream().map(Section::getSpaSection).collect(Collectors.toSet());
	}
}
