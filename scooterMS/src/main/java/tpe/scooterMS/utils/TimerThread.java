package tpe.scooterMS.utils;

import java.util.Arrays;

import tpe.scooterMS.model.Trip;

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
		
		
		while(!Thread.currentThread().isInterrupted()) {
			seconds++;
			if (seconds == 60) {
				seconds = 0;
				minutes++;
			}
			
			String timer = minutes + ":" + seconds;
			trip.setPauseTime(timer);
			System.out.println(timer);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Trip getTrip() {
		return trip;
	}
}
