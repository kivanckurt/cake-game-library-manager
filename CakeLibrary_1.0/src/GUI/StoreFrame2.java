// revised
package GUI;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainAndSystem.LibrarySys;
import Others.Game;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class StoreFrame2 extends JFrame {

	private JPanel contentPane;
	private JLabel lblBalance;
	String[] gameNames;
	JComboBox cbShopGames;
	private JTextField displayArea;
	private JTextField discount;
	private JTextField beforeD;
	private static LocalDate currentDate = LocalDate.now(); // local date
	
	public void refresh() {
		int i=0;
		gameNames = new String[LibrarySys.getListOfShopGames().size()];	
		
		for (Game g: LibrarySys.getListOfShopGames()) {
			gameNames[i++] = g.getName();
		}
		
		cbShopGames.setModel(new DefaultComboBoxModel(gameNames));
		String rounded = String.format("%.2f", LibrarySys.getBalanceTotal());
		lblBalance.setText(rounded+" TL" );
	}
	

	/**
	 * Create the frame.
	 */
	
	public StoreFrame2() {
		HashMap<String, Game> hmGames = new HashMap<String, Game>();
		gameNames = new String[LibrarySys.getListOfShopGames().size()];	
		int i=0;

		for (Game g : LibrarySys.getListOfShopGames()) {
			hmGames.put(g.getName(), g);
			gameNames[i++] = g.getName();
		}
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbShopGames = new JComboBox();
		
		
		cbShopGames.setModel(new DefaultComboBoxModel(gameNames));
		cbShopGames.setSelectedIndex(0);
		cbShopGames.setBounds(33, 11, 131, 22);
		contentPane.add(cbShopGames);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringGame = cbShopGames.getSelectedItem().toString();
				int purchaseStatus = LibrarySys.purchaseGame(stringGame);
				String result = "";
				double price;
				price = LibrarySys.getPriceForCurrentGame();
				cbShopGames.setSelectedIndex(cbShopGames.getSelectedIndex());
				
				if(purchaseStatus == 0) {
					discount.setText("");
					result += stringGame + " is already in your CAKE Library! Please check installiaton.";
					displayArea.setText(result);
				}
				else if(purchaseStatus == 1) {
					discount.setText(String.format("%.2f", price) + " TL");
					result += stringGame + " ---> wow, what good taste!!";
					displayArea.setText(result);
				}
				else {
					discount.setText(String.format("%.2f", price) + " TL");
					result += "Your balance is not enough; add more balance.";
					displayArea.setText(result);
				}
				refresh();
			}
		});
		btnPurchase.setBounds(255, 104, 114, 23);
		contentPane.add(btnPurchase);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				discount.setText("");
				displayArea.setText("");
				dispose();
			}
		});
		btnBack.setBounds(255, 137, 114, 23);
		contentPane.add(btnBack);
		
		lblBalance = new JLabel("New label");
		lblBalance.setBounds(282, 15, 114, 14);
		contentPane.add(lblBalance);
		String rounded = String.format("%.2f", LibrarySys.getBalanceTotal());
		lblBalance.setText( rounded +" TL");
		
		displayArea = new JTextField();
		displayArea.setEditable(false);
		displayArea.setBounds(10, 175, 416, 78);
		contentPane.add(displayArea);
		displayArea.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Price with discount: ");
		lblNewLabel_1.setBounds(17, 142, 114, 13);
		contentPane.add(lblNewLabel_1);
		
		discount = new JTextField();
		discount.setEditable(false);
		discount.setBounds(141, 137, 74, 23);
		contentPane.add(discount);
		discount.setColumns(10);
		
		beforeD = new JTextField();
		beforeD.setEditable(false);
		beforeD.setBounds(141, 52, 74, 22);
		contentPane.add(beforeD);
		beforeD.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Price before discount: ");
		lblNewLabel.setBounds(10, 55, 121, 13);
		contentPane.add(lblNewLabel);
		
		JTextArea labelDiscoun = new JTextArea();
		labelDiscoun.setEditable(false);
		labelDiscoun.setBounds(10, 84, 205, 41);
		contentPane.add(labelDiscoun);
		
		cbShopGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = new Game();
				game = LibrarySys.searchAllGames(cbShopGames.getSelectedItem().toString());
				int month = currentDate.getMonthValue();
				labelDiscoun.setText("The current month: "+ currentDate.getMonthValue()+"\nYou get " +LibrarySys.getDiscount() + "% discount :)");
				beforeD.setText(game.getPrice() +"");
				discount.setText("");
				displayArea.setText("");
			}
		});
		
	}
}
