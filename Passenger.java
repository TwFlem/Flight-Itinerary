import java.util.ArrayList;


public class Passenger{
	private String First;
	private String Last;
	private ArrayList<String> Alerts = new ArrayList<String>();
	private ArrayList<Flight> BookedFlight = new ArrayList<Flight>();;
	private ArrayList<Flight> StandbyFlight = new ArrayList<Flight>();
	
	public Passenger(String first, String last){
		this.First = first;
		this.Last = last;
		}
	public String getFirstName() {
		return First;
	}

	public String getLastName() {
		return Last;
	}

	public ArrayList<String> getAlerts() {
		return Alerts;
	}

	public ArrayList<Flight> getBookedFlights() {
		return BookedFlight;
	}

	public ArrayList<Flight> getStandbyFlights() {
		return StandbyFlight;
	}
	
	public boolean bookFlight(Flight f){
		if(f.addPassenger(this)){
			BookedFlight.add(f);
		return true;
		}
		else
		return false;	
	}

	public boolean addStandbyFlight(Flight f){
		if(f.addStandbyPassenger(this))
			StandbyFlight.add(f);
		return true;
	}
	public void clearAlerts(){
		if(Alerts.size()>0)
		Alerts.clear();
		else{throw new RuntimeException();}
	}
	public void cancelFlight(Flight f){
		if(BookedFlight.contains(f)){
		BookedFlight.remove(f);
		if(f.getStandbyPassengers().contains(this))
			//do these actually remove the passengers? Flight:97 & 98
		f.removePassenger(this);
		}
		if(StandbyFlight.contains(f)){
		StandbyFlight.remove(f);
		if(f.getStandbyPassengers().contains(this))
			// above comment. see Flight:97 & 98
		f.removeStandbyPassenger(this);
		}
	}
	public void cancelAll(){
		if(BookedFlight.size()>0){
		for(int i=0; i<BookedFlight.size(); i++)
			BookedFlight.get(i).removePassenger(this);
		BookedFlight.clear();
		}
		else{throw new RuntimeException();}
		if(StandbyFlight.size()>0){
		for(int i=0; i<BookedFlight.size(); i++)
			StandbyFlight.get(i).removePassenger(this);
		StandbyFlight.clear();
		}
		else{throw new RuntimeException();}
	}
	void addAlert(String s){
		if(s!=null)
		Alerts.add(s);
		else{throw new RuntimeException();}
		
	}
}