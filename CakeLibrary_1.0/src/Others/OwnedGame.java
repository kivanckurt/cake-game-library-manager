// revised
package Others;

public class OwnedGame extends Game implements SpaceManagement {
	
	private double hoursPlayed = 0;
	private boolean isInstalled = false;
	
	public OwnedGame() {}
	
	public OwnedGame(int id, String name, String genre, double price, double size, DLC extraContent) {
		super(id, name, genre, price, size, extraContent);
	}
	
	public OwnedGame(Game game) {
		this.id = game.id;
		this.name = game.name;
		this.genre = game.genre;
		this.price = game.price;
		this.size = game.size;
		this.extraContent = game.extraContent;		
	}
	
	public double getHoursPlayed() {
		return hoursPlayed;
	}

	public boolean getIsInstalled() {
		return isInstalled;
	}

	public void setInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	public void setHoursPlayed(double hoursPlayed) {
		this.hoursPlayed += hoursPlayed;
	}

	@Override
	public boolean installContent() {
		if (isInstalled) {
			System.out.println(this.name + " is already installed!");
			return false;
		}
		else {
			isInstalled = true;
			System.out.println(this.name + " was successfully installed!");
			return true;
		}
	}
	
	@Override
	public boolean uninstallContent() {
		if (isInstalled == false) {
			System.out.println(this.name + " is not installed!");
			return false;
		}
		else {
			isInstalled = false;
			System.out.println(this.name + " was successfully uninstalled!");
			return true;
		}
	}
	
	public String playGame(double hoursPlayed) {
		if (isInstalled == false)
			System.out.println("Please install the game first!");
		else if (hoursPlayed > 24) {
			return ("You can only play for 24 hours a day maximum! There are no more hours in a day :)");
		}
		else {
			this.hoursPlayed += hoursPlayed;
			return ("You have played " + this.name + " for " + hoursPlayed + " hours today!" + 
					"\nYour total play time for " + this.name + " is: " + this.hoursPlayed);		
		}
		
		return "";
	}
	
	@Override
	public String toString() {
		return super.toString() + "Hours Played: " + this.hoursPlayed + "\nInstall Status: " + this.isInstalled;
	}
	
}
