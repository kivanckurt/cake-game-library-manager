// revised
package Others;

public class DLC implements SpaceManagement {
	private String name;
	private double size;
	private boolean isInstalled = false;
	
	public DLC(String name, double size) {
		this.name = name;
		this.size = size;
	}
	
	public String toString() {
		return "\n\nDLC of This Game: \n\tName: " + this.name +
				"\n\tSize: " +this.size;
	}

	@Override
	public boolean installContent() {
		if (isInstalled) {
			System.out.println("DLC " + this.name + " is already installed!");
			return false;
		}
		else {
			isInstalled = true;
			System.out.println("DLC " + this.name + " was successfully installed!");
			return true;
		}
	}

	@Override
	public boolean uninstallContent() {
		if (isInstalled == false) {
			System.out.println("DLC " + this.name + " is not installed!");
			return false;
		}
		else {
			isInstalled = false;
			System.out.println("DLC " + this.name + " was successfully uninstalled!");
			return true;
		}
	}
}
