package utils;

import java.util.Arrays;

import tpe.tripms.model.Trip;

public class TimerThread extends Thread {
	private Trip trip;
	
	public TimerThread(Trip trip) {
		this.trip = trip;
	}
	
	@Override
	public void run() {
		String[] stringNumbers = trip.getPauseTime().split(":");
		int[] intNumbers = Arrays.stream(stringNumbers).mapToInt(Integer::parseInt).toArray();
		int minutes = intNumbers[0];
		int seconds = intNumbers[1];
		
		
		while(true) {
			seconds++;
			if (seconds == 60) {
				seconds = 0;
				minutes++;
			}
			
			String timer = minutes + ":" + seconds;
			trip.setPauseTime(timer);
		}
	}
	
	public Trip getTrip() {
		return trip;
	}
}