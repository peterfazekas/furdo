package hu.spa.model.domain;

import java.util.List;

public class Section {

	private final SpaSection spaSection;
	private final List<TimeStamp> timeStamps;
	
	public Section(SpaSection spaSection, List<TimeStamp> timeStamps) {
		this.spaSection = spaSection;
		this.timeStamps = timeStamps;
	}

	public int visitCount() {
		return timeStamps.size();
	}
	
	public Time getFirstEnterTime() {
		return timeStamps.get(0).getEnterTime();
	}
	
	public Integer getTotalDuration() {
		return timeStamps.stream().mapToInt(TimeStamp::getDuration).sum();
	}

	public SpaSection getSpaSection() {
		return spaSection;
	}


}
