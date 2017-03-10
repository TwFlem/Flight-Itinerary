
import java.util.Scanner;
public class Project1 {	
	//I wanted to divide up my program, so if i needed to make adjustments or call on this program for future assignments, i could.
			public static void main(String[] args) {
				String[] itinerary = new String[0];
				String[] flights = new String[0];
				//Just in case i need an itinerary for a future project module
				itinerary = Menu1(itinerary, flights);
				
			}
			public static String[] Menu1(String[] itinerary, String[] flights){
				Scanner input = new Scanner(System.in);
				//Initialed all of my variables and arrays
				String[] newItinerary = new String[itinerary.length];
				String[] newflights = new String[flights.length];
				String[] randoItinerary = new String[0];
				
				//Menu 1 begins
				System.out.println("============ Menu 1 ============");
				System.out.print("Enter Option (1,2,3): ");
				//getInput is my input validation for menu 1
				int option = getInput();
				int count = 0;
				int count1 = 0;
				String findPassenger = "money";
				String Passenger = null;
				String canceledflight = null;
				
				//This is the loop for the whole program- cancels when 3 is inputed
				while (option !=3){
				//this is option 1	
				if(option == 1){
					System.out.print("Enter first name: ");
				String first = input.next();
				System.out.print("Enter last name: ");
				String last = input.next();
				Passenger = first + " " + last; 
				System.out.println("newPassenger: " + Passenger);
				// just in case i need an itinerary of all inputs- i'm enlarging my Itinerary with every input added
					newItinerary = enlargeItinerary(newItinerary);
					newItinerary[count] = Passenger;
					count++;
				}
				//menu 1 option 2
				 if(option == 2){
					 System.out.print("Enter first name: ");
						String findfirst = input.next();
						System.out.print("Enter last name: ");
						String findlast = input.next();
						findPassenger = findfirst + " " + findlast;
						System.out.println("findPassenger: " + findPassenger);
				 }
				 
				 //Menu 2 begins
				 System.out.println("============ Menu 2 ============");
				 System.out.print("Enter Option (1,2,3,4): ");
				 //Input validation for menu 2
					int option2 = getInput2();
				//Loop for menu 2 until option 4 is chosen	
					while(option2 != 4){
				//Menu 2 option 1		
					if(option2 == 1){
						System.out.print("Enter start: ");
						String ori = input.next();
						System.out.print("Enter finish: ");
						String dest = input.next();
						String Flight = ori + " " + dest; 
						System.out.println("findAvailableFlightPlans: " + Flight);
						newflights = enlargeflights(newflights);
						newflights[count1] = Flight;
						count1++;
					}
					//Menu 2 option 2
					if(option2==2){
						randoItinerary = RandomItinerary.get();
					//Provides user a menu to choose a flight to cancel. Then lets user know which is canceled.	
					canceledflight = submenu(randoItinerary, Passenger, findPassenger);
					if(canceledflight.equals("cancel")){}
					else{
					System.out.println("cancelFlight: " + canceledflight);
					canceledflight = null;
					}
					}
					//Menu 3 option 3
					if(option2==3){
						randoItinerary = RandomItinerary.get();
					//Prints off all Itinerary and lets user know the flights have been canceled.	
					for(int i=0;i<randoItinerary.length;i++){	
							 if(findPassenger.equals("money")){System.out.println("Cancel Flight: " + randoItinerary[i] + " " + Passenger);}
						else{System.out.println("Cancel Flight: " + randoItinerary[i] + " " + findPassenger);}
						}
					}
					 System.out.println("============ Menu 2 ============");
					 System.out.print("Enter Option (1,2,3,4): ");
					 option2 = getInput2();
					}
					
					System.out.println("============ Menu 1 ============");
					System.out.print("Enter Option (1,2,3): ");
					option = getInput();
				}

				return newItinerary;
		}
			
	
			//Input validation for first menu
			private static int getInput() {
				Scanner input = new Scanner(System.in);
				int option =0;
				boolean err = true;
				do{
					
					if(input.hasNextInt()){
						option = input.nextInt();
						if(option>=1 && option<=3)
							err = false;
						
						else if(option < 1 || option >3){
							System.out.println("Invalid input");
						}
					}
						else{
							System.out.println("Invalid input");
							input.next();
						}
					
				}while(err);
						
				return option;
			}
			//Input validation for second menu
			private static int getInput2() {
				Scanner input = new Scanner(System.in);
				int option =0;
				boolean err = true;
				do{
					
					if(input.hasNextInt()){
						option = input.nextInt();
						if(option>=1 && option<=4)
							err = false;
						
						else if(option < 1 || option >4){
							System.out.println("Invalid input");
						}
					}
						else{
							System.out.println("Invalid input");
							input.next();
						}
					
				}while(err);
						
				return option;
			}
			//Sub-menu for menu 2 option 2
			private static String submenu(String[] randoItinerary, String Passenger, String findPassenger) {
				Scanner input = new Scanner(System.in);
				String canceledflight = null;
				int cancel = randoItinerary.length;
				for(int i=0;i<randoItinerary.length;i++)
					System.out.println((i+1)+" " + randoItinerary[i]);
						System.out.println((cancel+1)+".cancel");
						System.out.print("Enter flight number: ");
						int option = input.nextInt();
						if(option == cancel+1){canceledflight = "cancel";}
						
						else{
							if(findPassenger.equals("money")){
								canceledflight = randoItinerary[option-1] + " " + Passenger;
							}
							
							
						else{
						canceledflight = randoItinerary[option-1] + " " + findPassenger;
						}
						}
						return canceledflight;
			}
			
				
				
			//enlarging my flights to which pair along with future Itinerary... for future use
			private static String[] enlargeflights(String[] flights) {
				Scanner input = new Scanner(System.in);
				String[] tempflights = new String[flights.length+1];
				for(int i=0; i<tempflights.length-1;i++)
					tempflights[i] = flights[i];
				return tempflights;
			}
			//enlarging my Itinerary for future use
			public static String[] enlargeItinerary(String[] itinerary) {
				Scanner input = new Scanner(System.in);
				String[] newItinerary = new String[itinerary.length+1];
				for(int i=0; i<newItinerary.length-1;i++)
					newItinerary[i] = itinerary[i];
				return newItinerary;	
			}
			// finds index for the "findPassnger from menu 1 option 2... for future use
			public static int searchIndex(String[] newItinerary, String findPassenger){
					int index = 0;
				for(int i=0;i<newItinerary.length;i++){
					if(newItinerary[i].equals(findPassenger))
				    index = i;	
				}
				return index;
			}
}