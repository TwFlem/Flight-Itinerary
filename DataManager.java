import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class DataManager {
	
	FileOutputStream out;
	public static void exportData(String filename,
			ArrayList<Passenger> passengers, ArrayList<Flight> flights){
		File file = new File(filename);
		PrintWriter output = null;
		try {
			 output= new PrintWriter(file);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.println("#flightCount "+ flights.size());
	//	System.out.println("#flightCount "+ flights.size());
		for(int i=0; i<flights.size(); i++){
			output.println("#newFlight");
	//		System.out.println("#newFlight");
			output.println(flights.get(i).getSourceAirport()+ " , " + flights.get(i).getDestinationAirport() + " , " + flights.get(i).getTakeoffTime() + " , " + flights.get(i).getLandingTime());
	//		System.out.println(flights.get(i).getSourceAirport()+ " , " + flights.get(i).getDestinationAirport() + " , " + flights.get(i).getTakeoffTime() + " , " + flights.get(i).getLandingTime());
			output.println(flights.get(i).getCapacity());
	//		System.out.println(flights.get(i).getCapacity());
		}
		output.println("#passCount "+ passengers.size());
	//	System.out.println("#passCount "+ passengers.size());
		for(int i=0; i<passengers.size(); i++){
			output.println("#newPass");
		//	System.out.println("#newPass ");
			output.println(passengers.get(i).getFirstName() + " , " + passengers.get(i).getLastName());
		//	System.out.println(passengers.get(i).getFirstName() + " , " + passengers.get(i).getLastName());
			output.println(passengers.get(i).getAlerts().size());
		//	System.out.println(passengers.get(i).getAlerts().size());
			for(int l = 0; l<passengers.get(i).getAlerts().size();l++){
				output.println(passengers.get(i).getAlerts().get(l).toString());
		//		System.out.println(passengers.get(i).getAlerts().get(l).toString());
				
			}
			output.println(passengers.get(i).getBookedFlights().size());
		//	System.out.println(passengers.get(i).getBookedFlights().size());
			for(int j=0; j<passengers.get(i).getBookedFlights().size();j++){
			output.println(passengers.get(i).getBookedFlights().get(j).getSourceAirport() + " , " + passengers.get(i).getBookedFlights().get(j).getDestinationAirport() + " , " + passengers.get(i).getBookedFlights().get(j).getTakeoffTime() + " , " + passengers.get(i).getBookedFlights().get(j).getLandingTime());
		//	System.out.println(passengers.get(i).getBookedFlights().get(j).getSourceAirport() + " , " + passengers.get(i).getBookedFlights().get(j).getDestinationAirport() + " , " + passengers.get(i).getBookedFlights().get(j).getTakeoffTime() + " , " + passengers.get(i).getBookedFlights().get(j).getLandingTime());
			
			}
			output.println(passengers.get(i).getStandbyFlights().size());
		//	System.out.println(passengers.get(i).getStandbyFlights().size());
			for(int k=0; k<passengers.get(i).getStandbyFlights().size();k++){
				output.println(passengers.get(i).getStandbyFlights().get(k).getSourceAirport() + " , " + passengers.get(i).getStandbyFlights().get(k).getDestinationAirport() + " , " + passengers.get(i).getStandbyFlights().get(k).getTakeoffTime() + " , " + passengers.get(i).getStandbyFlights().get(k).getLandingTime());
		//		System.out.println(passengers.get(i).getStandbyFlights().get(k).getSourceAirport() + " , " + passengers.get(i).getStandbyFlights().get(k).getDestinationAirport() + " , " + passengers.get(i).getStandbyFlights().get(k).getTakeoffTime() + " , " + passengers.get(i).getStandbyFlights().get(k).getLandingTime());
				
			}	
			
		}
		output.close();
	}

	public static ImportData importData(String filename){
		Scanner input = null;
		ArrayList<Flight> flights = new ArrayList<Flight>();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		ArrayList<String[]> flightcodes = new ArrayList<String[]>();
		ArrayList<String[]> bookcodes = new ArrayList<String[]>();
		ArrayList<String[]> standcodes = new ArrayList<String[]>();
		try {
			 input= new Scanner(new File(filename));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] splitflight = new String[4];
		
		int pnum =0;
		while(input.hasNext()){
		//String[] fline = input.nextLine().split(" ");
		//System.out.println(fline[1]);
			input.next();
		int fnum = input.nextInt();
	//	System.out.println(fnum);
			input.nextLine().trim();
			for(int i=0;i<fnum;i++){
				input.nextLine();// #newFlight
				splitflight = input.nextLine().split(" , "); // flight path and times
			//	for(int q=0;q<splitflight.length;q++)
			//	System.out.println(splitflight[q]);
				flightcodes.add(splitflight); //storing this data for passenger use
				//input.nextLine();
				int capacity = Integer.parseInt(input.nextLine());
				//System.out.println(capacity);
				Flight flight = new Flight(splitflight[0],splitflight[1], Integer.parseInt(splitflight[2]), Integer.parseInt(splitflight[3]),
						capacity);
				flights.add(flight);
			}
				input.next();
				pnum = input.nextInt();
			//	System.out.println(pnum);
				input.nextLine().trim();
				for(int j=0;j<pnum;j++){
				input.nextLine(); // #newPass	
				String[] splitname = input.nextLine().split(" , ");
			//	for(int i =0; i<splitname.length; i++)
			//		System.out.println(splitname[i]);
				Passenger passenger = new Passenger(splitname[0], splitname[1]);
				passengers.add(passenger);
				int anum = Integer.parseInt(input.nextLine());
			//	System.out.println(anum);
				for(int i=0; i<anum; i++){
					String alert = input.nextLine();
				//	System.out.println(alert);
					passenger.addAlert(alert);
				}
				int bnum = Integer.parseInt(input.nextLine());
				for(int i=0; i<bnum; i++){
					String[] splitbooked = input.nextLine().split(" , ");
					bookcodes.add(splitbooked);
				}
					for(int y=0;y<bnum;y++){
						for(int k=0; k<flightcodes.size(); k++){
							if(flightcodes.get(k)[0].equals(bookcodes.get(y)[0]) && flightcodes.get(k)[1].equals(bookcodes.get(y)[1])
									&& flightcodes.get(k)[2].equals(bookcodes.get(y)[2]) && flightcodes.get(k)[3].equals(bookcodes.get(y)[3])){ 
								passenger.bookFlight(flights.get(k));
							}
						}	
				}
					
					int snum = Integer.parseInt(input.nextLine());
					for(int i=0; i<snum; i++){
						String[] splitstand = input.nextLine().split(" , ");
						//for(int g=0;g<splitstand.length;g++)
						//	System.out.println(splitstand[g]);
						standcodes.add(splitstand);
					}
						for(int y=0;y<snum;y++){
							for(int k=0; k<flightcodes.size(); k++){
							//	System.out.println(flightcodes.get(k)[0]);
								if(flightcodes.get(k)[0].equals(standcodes.get(y)[0]) && flightcodes.get(k)[1].equals(standcodes.get(y)[1]) && flightcodes.get(k)[2].equals(standcodes.get(y)[2])
										&& flightcodes.get(k)[3].equals(standcodes.get(y)[3])){ 
									passenger.addStandbyFlight(flights.get(k));
								}
							}	
					}
			}
		}
		input.close();
		//System.out.println(passengers.get(1).getStandbyFlights().get(1).getLandingTime());
		//System.out.println(passengers.get(1).getStandbyFlights().size());
		//System.out.println(passengers.get(0).getBookedFlights().size());
		ImportData data = new ImportData(passengers, flights);
		
		
		
		
		return data;	
}
}
	

