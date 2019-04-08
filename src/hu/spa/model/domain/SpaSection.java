package hu.spa.model.domain;

import java.util.Arrays;

public enum SpaSection {

	DRESSING_ROOM(0, ""),
	POOL(1, "Uszoda: "),
	SAUNA(2, "Szaunák: "),
	SPA(3, "Gyógyvizes medencék: "),
	STRAND(4, "Strand: ");

	private final int id;
	private final String description;
	
	private SpaSection(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static SpaSection createById(int id) {
		return Arrays.stream(SpaSection.values())
				.filter(i -> i.getId() == id)
				.findAny()
				.get();
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
