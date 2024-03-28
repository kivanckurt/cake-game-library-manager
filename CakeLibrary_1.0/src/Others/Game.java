package Others;

public class Game extends Application{
	
	protected int id;
	protected String name, genre;
	protected double price, size, finalPrice;
	protected DLC extraContent;


	protected static double totalLibraryCost = 0.0;
	
	public Game() {}

	public Game(int id, String name, String genre, double price, double size, DLC extraContent) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.price = price;
		this.size = size;
		this.extraContent = extraContent;
	}
	

	// getters & setters
	
	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public double getPrice() {
		return price;
	}

	public double getSize() {
		return size;
	}

	public DLC getExtraContent() {
		return extraContent;
	}

	public static double getTotalLibraryCost() {
		return totalLibraryCost;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setSize(double size) {
		this.size = size;
	}


	public void setExtraContent(DLC extraContent) {
		this.extraContent = extraContent;
	}


	public static void setTotalLibraryCost(double totalLibraryCost) {
		Game.totalLibraryCost = totalLibraryCost;
	}


	public String toString() {
		return "Game ID: " + id + "\nName: " + name + "\nGenre: " + genre + "\nPrice: " + price + "\nSize: " + 
				size + "\nExtra Content: " + extraContent + "\n";
	}

	@Override
	public String returnLibraryManager() {
		// TODO Auto-generated method stub
		return "You are in CAKE Game Library";
	}
	

	
}
