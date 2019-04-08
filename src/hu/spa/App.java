package hu.spa;

import java.util.List;

import hu.spa.controller.Spa;
import hu.spa.model.domain.Guest;
import hu.spa.model.domain.Record;
import hu.spa.model.service.DataReader;
import hu.spa.model.service.GuestTransformer;
import hu.spa.model.service.ResultWriter;

public class App {

	private final Spa spa;
	private final ResultWriter writer;
	
	public App() {
		DataReader data = new DataReader();
		List<Record> records = data.getRecords("furdoadat.txt");
		GuestTransformer guestTransformer = new GuestTransformer();
		List<Guest> guests = guestTransformer.transform(records);
		spa = new Spa(guests);
		writer = new ResultWriter("szauna.txt");
	}
	
	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		System.out.println("2. feladat: " + spa.getFirstAndLastGuestExitTime());
		System.out.println("3. feladat: " + spa.getFastVisitCount());
		System.out.println("4. feladat: " + spa.getLongestStayDetail());
		System.out.println("5. feladat: " + spa.getIntervalVisitCount());
		writer.printAll(spa.getSaunaVisitDetail());
		System.out.println("7. feladat:\r\n" + spa.getVisitedSectionsCount());
	}

}
