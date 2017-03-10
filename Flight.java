import java.util.ArrayList;


public class Flight {
	private String SourceAirport;
	private String DestinationAirport;
	private int takeoffTime;
	private int landingTime;
	private int capacity;
	private ArrayList<Passenger> BookedPassengers = new ArrayList<Passenger>();
	private ArrayList<Passenger> StandbyPassengers = new ArrayList<Passenger>();
	
	public Flight(String src, String dest, int takeoffTime, int landingTime, int capacity){
		this.SourceAirport = src;
		this.DestinationAirport = dest;
		if(takeoffTime<landingTime){
			this.takeoffTime = takeoffTime;
			this.landingTime = landingTime;
		}
		else{
			throw new RuntimeException();
		}
		this.capacity = capacity;
	}

	public String getSourceAirport() {
		return SourceAirport;
	}

	public String getDestinationAirport() {
		return DestinationAirport;
	}

	public int getTakeoffTime() {
		return takeoffTime;
	}

	public int getLandingTime() {
		return landingTime;
	}

	public int getCapacity() {
		return capacity;
	}

	public ArrayList<Passenger> getBookedPassengers() {
		return BookedPassengers;
	}

	public ArrayList<Passenger> getStandbyPassengers() {
		return StandbyPassengers;
	}
	public boolean addPassenger(Passenger p){
		if(nullPass(p)){
		if(BookedPassengers.contains(p))
			return false;
		else{BookedPassengers.add(p);}
		}
		if(BookedPassengers.size()<=capacity){
		return true;
		}
		else{
			BookedPassengers.remove(p);
			return false;
			}
		}
	
	public boolean addStandbyPassenger(Passenger p){
		if(nullPass(p)){
			if(StandbyPassengers.contains(p) || BookedPassengers.contains(p))
				return false;
			else{
		StandbyPassengers.add(p);
		return true;
			}
		}
		return false;
	}
	public void removePassenger(Passenger p){
		if(nullPass(p)){
			if(BookedPassengers.contains(p))
		BookedPassengers.remove(p);
			else
				throw new RuntimeException();
		}
			
	}
	public void removeStandbyPassenger(Passenger p){
		if(nullPass(p)){
			if(StandbyPassengers.contains(p))
		StandbyPassengers.remove(p);
			else
				throw new RuntimeException();
		}
	}
	public void cancel(){
		for(int i=0;i<StandbyPassengers.size();i++){
			StandbyPassengers.get(i).addAlert("Flght" +this+" Cancled!");
			StandbyPassengers.get(i).cancelFlight(this);
		}
		for(int i=0;i<BookedPassengers.size();i++){
			BookedPassengers.get(i).addAlert("Flght" +this+" Cancled!");
			BookedPassengers.get(i).cancelFlight(this);
		}
		//are these unnecessary? See Passenger:64 & 58
		BookedPassengers.clear();
		StandbyPassengers.clear();
		
	}
	public int promotePassengers(){
		int AvailableSpace = capacity - BookedPassengers.size();
		int PassengersPromoted = 0;
		if(AvailableSpace!=0){
			if(AvailableSpace>=StandbyPassengers.size()){
				PassengersPromoted = StandbyPassengers.size();
				for(int i= 0; i<StandbyPassengers.size();i++){
				StandbyPassengers.get(i).addAlert("Promoted to flight "+this+"!");
				StandbyPassengers.get(i).bookFlight(this);
				}
				BookedPassengers.addAll(StandbyPassengers);
				StandbyPassengers.clear();
			}
			else if(StandbyPassengers.size() > AvailableSpace){
				for(int i = 0; i<AvailableSpace;i++){
					StandbyPassengers.get(i).bookFlight(this);
					StandbyPassengers.get(i).addAlert("Promoted to flight "+this+"!");
					BookedPassengers.add(StandbyPassengers.get(i));
					StandbyPassengers.remove(i);
					PassengersPromoted++;
				
				}
			}
		}
		return PassengersPromoted;
	}
	boolean nullPass(Passenger p){
		if(p!=null)
			return true;
		else{throw new RuntimeException();
		}
	}
}
