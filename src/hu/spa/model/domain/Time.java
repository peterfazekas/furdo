package hu.spa.model.domain;

public class Time implements Comparable<Time> {

	private final int hour;
	private final int minute;
	private final int second;
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHour() {
		return hour;
	}

	public Integer getDuration(Time otherTime) {
		return Math.abs(this.toSeconds() - otherTime.toSeconds());
	}
	
	private Integer toSeconds() {
		return 3600 * hour + 60 * minute + second;
	}
	
	public boolean isBefore(Time otherTime) {
		return this.toSeconds() < otherTime.toSeconds();
	}

	public boolean isAfter(Time otherTime) {
		return this.toSeconds() > otherTime.toSeconds();
	}

	@Override
	public int compareTo(Time otherTime) {
		return this.toSeconds().compareTo(otherTime.toSeconds());
	}

	@Override
	public String toString() {
		return String.format("%d:%02d:%02d", hour, minute, second);
	}
	
	
	
	
}
