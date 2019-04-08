package hu.spa.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.spa.model.domain.Record;
import hu.spa.model.domain.SpaSection;
import hu.spa.model.domain.Time;

public class DataReader {

	public List<Record> getRecords(String fileName) {
		return parse(read(fileName));
	}
	
	private List<Record> parse(List<String> lines) {
		return lines.stream().map(this::createRecord).collect(Collectors.toList());
	}
	
	private Record createRecord(String line) {
		String[] items = line.split(" ");
		int id = intValue(items[0]);
		SpaSection spaSection = SpaSection.createById(intValue(items[1]));
		boolean isEnter = intValue(items[2]) == 0;
		Time time = new Time(intValue(items[3]), intValue(items[4]), intValue(items[5]));
		return new Record(id, spaSection, isEnter, time);
	}
	
	private int intValue(String text) {
		return Integer.parseInt(text);
	}
	
	private List<String> read(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
