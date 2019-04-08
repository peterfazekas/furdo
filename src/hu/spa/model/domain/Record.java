package hu.spa.model.domain;

public class Record {

	private final int id;
	private final SpaSection spaSection;
	private final boolean isEnter;
	private final Time time;

	public Record(int id, SpaSection spaSection, boolean isEnter, Time time) {
		this.id = id;
		this.spaSection = spaSection;
		this.isEnter = isEnter;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public SpaSection getSpaSection() {
		return spaSection;
	}

	public boolean isEnter() {
		return isEnter;
	}

	public Time getTime() {
		return time;
	}

}
