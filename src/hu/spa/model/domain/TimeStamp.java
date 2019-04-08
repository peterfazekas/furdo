package hu.spa.model.domain;

public class TimeStamp {

	private final Time enterTime;
	private final Time exitTime;

	public TimeStamp(Time enterTime, Time exitTime) {
		this.enterTime = enterTime;
		this.exitTime = exitTime;
	}

	public Time getEnterTime() {
		return enterTime;
	}

	public Time getExitTime() {
		return exitTime;
	}

	public Integer getDuration() {
		return exitTime.getDuration(enterTime);
	}
	
}
