// revised
package MainAndSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

import Others.DLC;
import Others.Game;
import Others.OwnedGame;

import java.time.LocalDate;

public class LibrarySys {

	//finals for the discount --related to season
	private static  final double WINTER_DISCOUNT = 0.4;
	private static final double  SPRING_DISCOUNT = 0.3;
	private static final double  SUMMER_DISCOUNT = 0.25;
	private static final double  FALL_DISCOUNT = 0.17;
	private static double priceForCurrentGame = 0;
	
	//variables of the system class
	private static ArrayList<Game> listOfShopGames = new ArrayList<Game>(); // taken from file, not entered by user
	private static ArrayList<OwnedGame> listOfGamesOwned = new ArrayList<OwnedGame>();
	private static TreeSet<Integer> gameIdContainer = new TreeSet<Integer>();
	private static LocalDate currentDate = LocalDate.now(); // local date
	
	//constants at the beginning
	public static double balanceTotal = 100; // initial balance
	private static double availableSpace = 2000; // initial space
	
	public static ArrayList<Game> getListOfShopGames() {
		return listOfShopGames;
	}
	
	public static LocalDate getCurrentDate() {
		return currentDate;
	}
	public static ArrayList<OwnedGame> getListOfGamesOwned() {
		return listOfGamesOwned;
	}

	public static double getBalanceTotal() {
		return balanceTotal;
	}
	
	public static double getAvailableSpace() {
		return availableSpace;
	}
	

	// creating the whole system
	public static void addGamesInTheSystem(String name, String genre, double price, double size, String DLCName, double DLCSize) {
		DLC extraContent = new DLC(DLCName, DLCSize);
		
		// random unique id
		Integer randomId = (int) (Math.floor(Math.random() * 9999) + 1); // 1-10000
		
		while (gameIdContainer.contains(randomId)) {
			randomId = (int) (Math.floor(Math.random() * 9999) + 1);
		}
		
		Game game = new Game(randomId, name, genre, price, size, extraContent);
		listOfShopGames.add(game);
	}

	public static boolean readFromFile() {
		File file = new File("games.txt");
		
		try {
			Scanner scanner = new Scanner(file);
			Game game;
			String gameInfo;
			String [] gameAll = new String[6];
			while(scanner.hasNext()) {
				gameInfo = scanner.nextLine();
				gameAll = gameInfo.split("%");
				addGamesInTheSystem(
						gameAll[0],
						gameAll[1],
						Double.parseDouble(gameAll[2]),
						Double.parseDouble(gameAll[3]),
						gameAll[4],
						Double.parseDouble(gameAll[5]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	// search method for shopping list
	public static Game searchAllGames(String name) {
		for (Game g : listOfShopGames)
			if(g.getName().equalsIgnoreCase(name))
					return g;
		
		return null;
	}
	
	// search method for in the owned games arraylist
	public static Game searchOwnedGames(String name) {
		for (Game g : listOfGamesOwned)
			if(g.getName().equalsIgnoreCase(name))
					return ((OwnedGame)g);
		
		return null;
	}
	
	
	
	// adding games into the system
	public static int purchaseGame(String name) {
		Scanner scanner = new Scanner(System.in); 
		Game game = new OwnedGame();
		game= searchAllGames(name);
			
		if(game != null) {
			if (searchOwnedGames(name) == null) {
				// check the price and make sure there is enough balance
				game.setFinalPrice(applyDiscountSeason(game.getPrice()));
				
				if (!(game.getFinalPrice() > balanceTotal)) {
					balanceTotal = balanceTotal - game.getFinalPrice();
					listOfGamesOwned.add(new OwnedGame(game));
					return 1; 
				}
				else {
					System.out.println("You do not have enough balance!");
					return -1;
				}
			}
			else {
				System.out.println("You have already purchased this game!");
				return 0;
			}
		}
		
		return 0;
	}
	
	
	public static boolean addBalance(double balance) {
		balanceTotal += balance;
		return true;
	}
	
	public static double applyDiscountSeason(double price) {
		int month = currentDate.getMonthValue();
		double discount;
		double fprice;
		
		if(month >= 3 && month <= 5) // spring 
			fprice = (double)Math.round((price * (1- SPRING_DISCOUNT)) * 100) / 100;
		else if (month >= 6 && month <= 8) // summer
			fprice = (double)Math.round((price * (1- SUMMER_DISCOUNT)) * 100) / 100;
		else if (month >= 9 && month <= 11) // fall
			fprice = Math.round((double)(price * (1- FALL_DISCOUNT)) * 100) / 100;
		else // if no other season, it's winter
			fprice = price * (1- WINTER_DISCOUNT);

		System.out.println("Before: " + price + "After: " + fprice);
		priceForCurrentGame = fprice;
		
		return fprice;
	}
	
	public static double getPriceForCurrentGame() {
		return priceForCurrentGame;
	}
	
	public static boolean installGame(OwnedGame game) {
		// ensure the availability of space
		if (availableSpace - game.getSize() >= 0) {	
			game.installContent();
			updateStorageInstall(game);
			
			return true;
		}
		else {
			System.out.println("You do not have enough storage!");
			return false;
		}
	}
	
	public static boolean uninstallGame(OwnedGame game) {
			game.uninstallContent();
			updateStorageUninstall(game);
			
			return true;
	}
	
	public static void updateStorageInstall(OwnedGame game) {
		// update the size
		availableSpace -= game.getSize();
	}
	
	public static void updateStorageUninstall(OwnedGame game) {
		// update the size
		availableSpace += game.getSize();
	}
		
	public static String[] ownedGamesNames() {
		String[] gameNames = null;
		gameNames = new String[LibrarySys.getListOfGamesOwned().size()];	
		int i=0;
		
		// in the case that there is no game owned yet
		String[] nullGames = new String[1];
		nullGames[0] = "No Games Found";
		
		if (LibrarySys.getListOfGamesOwned().size() == 0)
			return nullGames;
		
		for (OwnedGame g: LibrarySys.getListOfGamesOwned()) {
			gameNames[i] = g.getName();
			i++;
		}
		
		return gameNames;
	}
	
	public static String gameInfoForStorage(OwnedGame g) {
		
		String result = "";
		result += "Game information from CAKE Library: \n\n"+
				"The name: "+ g.getName() + "\n"+
				"The price with discount: " +g.getFinalPrice() + "\n" +
				"The current size: " +g.getSize()+" GB\n" +
				"The genre: " + g.getGenre()+ "\n\n";
		if (!g.getIsInstalled()) {
			result += "It seems like you haven't installed the game yet.\n\tTo be able to play please INSTALL it first!!";
		}
		else
			result += "It seems like you installed the game and you have played: " + g.getHoursPlayed() + "hours, "
					+ "to continue, go to the library!\n\tIf you want you can UNINSTALL it.";
				
		
		return result;
		
	}
	
	public static String[] installedGamesNames() {
		String[] gameNames = null;
		int size=0;
		
		for(OwnedGame g: getListOfGamesOwned()) {
			if(g.getIsInstalled())
				size++;
		}
		
		gameNames = new String[size];	
		
		// in the case that there is no game yet
		String[] nullGames = new String[1];
		nullGames[0] = "No Games Found";
		
		int i = 0;
		
		if (LibrarySys.getListOfGamesOwned().size() == 0)
			return nullGames;

		for (OwnedGame g: LibrarySys.getListOfGamesOwned()) {
			if (g.getIsInstalled()==true) {
				gameNames[i] = g.getName();		
				System.out.println(gameNames[i]);
				i++;
				
				
			}
		}
			return gameNames;
	}
	
	// string formatter for listOfShopGames
	public static String listOfShopGamesStr() {
		String str = "No games found!";
			
		if (listOfShopGames.size() != 0)
		{
			str = "";
				
			for (Game g : listOfShopGames) {
				str += g.toString() + "\n";
			}
		}
		
		return str;
	}
	
	public static double getDiscount() {
		int month = currentDate.getMonthValue();
		
		if(month >= 3 && month <= 5) // spring 
			return SPRING_DISCOUNT*100;
		else if (month >= 6 && month <= 8) // summer
			return SUMMER_DISCOUNT*100;
		else if (month >= 9 && month <= 11) // fall
			return FALL_DISCOUNT*100;
		else // if no other season, it's winter
			return WINTER_DISCOUNT*100;

		
	}
	
}

