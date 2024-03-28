// revised
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import MainAndSystem.LibrarySys;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static JTextPane textPane;
	private static JLabel lblBalance;
	
	Library lFrame = new Library(this);
	
	public static void display(String s) {
		textPane.setText(s);
	}
	
	public static void refresh() {
		Double balance = LibrarySys.getBalanceTotal();
		lblBalance.setText("Balance: " + balance + " TL");
	}

	
	AddBalanceFrame addBalance = new AddBalanceFrame(this);
	StoreFrame2 storeFrame2 = new StoreFrame2();
	ManageStorageFrame manageStorageFrame =  new ManageStorageFrame();
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("CAKE Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddBalance = new JButton("Add Balance");
		btnAddBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBalance.setVisible(true);
			}
		});
		btnAddBalance.setBounds(14, 93, 154, 23);
		contentPane.add(btnAddBalance);
		
		lblBalance = new JLabel("Balance: 100 TL");
		lblBalance.setBounds(22, 25, 120, 20);
		contentPane.add(lblBalance);
		lblBalance.setText(Double.toString(LibrarySys.getBalanceTotal()) + " TL");
		
		JLabel lblNewLabel = new JLabel("Username: USER");
		lblNewLabel.setBounds(22, 10, 120, 20);
		contentPane.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setBounds(178, 49, 357, 294);
		contentPane.add(textPane);
		textPane.setEditable(false);
		textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                display(LibrarySys.listOfShopGamesStr());
            }
        });
		display("Click to show all shop games!");
		
		JButton Store = new JButton("Store");
		Store.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeFrame2.setVisible(rootPaneCheckingEnabled);
			}
		});
		Store.setBounds(14, 127, 154, 23);
		contentPane.add(Store);
		
		JButton btnLibrary = new JButton("Library");
		btnLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				System.out.println(LibrarySys.getListOfGamesOwned().toString());
				for(int i =0 ; i< LibrarySys.installedGamesNames().length;i++)
				{	System.out.println(LibrarySys.installedGamesNames()[i]);
				
				}*/
				lFrame.fillComboBox();
				lFrame.setVisible(true);
			}
		});
		btnLibrary.setBounds(14, 161, 154, 23);
		contentPane.add(btnLibrary);
		
		JButton btnStorage = new JButton("Manage Storage");
		btnStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(LibrarySys.getListOfGamesOwned()); 
				for(int i = 0; i < LibrarySys.ownedGamesNames().length; i++) {
					System.out.println(LibrarySys.ownedGamesNames()[i]);
				}
				manageStorageFrame.fillComboBox();
				manageStorageFrame.setVisible(true);
				
			}
		});
		btnStorage.setBounds(14, 195, 154, 23);
		contentPane.add(btnStorage);
		
		
		
		
	}
}
