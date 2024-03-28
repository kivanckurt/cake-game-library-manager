// revised
package MainAndSystem;

import GUI.MainFrame;

public class Main {

	public static void main(String[] args) {
		LibrarySys.readFromFile();
		
		MainFrame main = new MainFrame();
		main.setVisible(true);

	}

}
