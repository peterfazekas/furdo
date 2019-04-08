package hu.spa.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hu.spa.model.domain.Guest;
import hu.spa.model.domain.Record;
import hu.spa.model.domain.Section;
import hu.spa.model.domain.SpaSection;
import hu.spa.model.domain.TimeStamp;

public class GuestTransformer {

	public List<Guest> transform(List<Record> records) {
		List<Guest> guests = new ArrayList<>();
		getGuestIds(records).forEach(id -> guests.add(createGuest(id, records)));
		return guests;
	}

	private Set<Integer> getGuestIds(List<Record> records) {
		return records.stream().map(Record::getId).collect(Collectors.toSet());
	}

	private Guest createGuest(int guestId, List<Record> records) {
		int id = guestId;
		List<Section> sections = new ArrayList<>();
		for (SpaSection spaSection : SpaSection.values()) {
			List<Record> filterRecordsByIdAndSpaSections = filterRecordsByIdAndSpaSections(guestId, spaSection,
					records);
			if (filterRecordsByIdAndSpaSections.size() > 0) {
				sections.add(new Section(spaSection, createTimeStamp(filterRecordsByIdAndSpaSections)));
			}
		}
		return new Guest(id, sections);
	}

	private List<TimeStamp> createTimeStamp(List<Record> records) {
		List<TimeStamp> timeStamps = new ArrayList<>();
		for (int i = 0; i < records.size(); i += 2) {
			timeStamps.add(new TimeStamp(records.get(i).getTime(), records.get(i + 1).getTime()));
		}
		return timeStamps;
	}

	private List<Record> filterRecordsByIdAndSpaSections(int guestId, SpaSection spaSection, List<Record> records) {
		return records.stream().filter(record -> record.getId() == guestId)
				.filter(record -> record.getSpaSection().equals(spaSection)).collect(Collectors.toList());
	}
}
