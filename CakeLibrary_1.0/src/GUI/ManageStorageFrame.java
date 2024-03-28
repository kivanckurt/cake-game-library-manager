// revised
package GUI;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainAndSystem.LibrarySys;
import Others.Game;
import Others.OwnedGame;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ManageStorageFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox ownedGames;
	private JButton gameInstall;
	private JButton gameUninstall;
	private JTextField curMem;
	
	public void fillComboBox() {
		ownedGames.setModel(new DefaultComboBoxModel(LibrarySys.ownedGamesNames()));
	}

	
	Library lFrame = new Library(null);
	/**
	 * Create the frame.
	 */
	public ManageStorageFrame() {
		setBounds(100, 100, 807, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ownedGames = new JComboBox();
		ownedGames.setBounds(37, 54, 136, 22);
		contentPane.add(ownedGames);
		
		JButton gameInstall = new JButton("Install Game");
		
		gameInstall.setFont(new Font("Tahoma", Font.BOLD, 13));
		gameInstall.setForeground(new Color(255, 69, 0));
		gameInstall.setBounds(423, 46, 144, 38);
		contentPane.add(gameInstall);
		
		JButton gameUninstall = new JButton("Uninstall Game");
		gameUninstall.setFont(new Font("Tahoma", Font.BOLD, 13));
		gameUninstall.setForeground(new Color(255, 69, 0));
		
		gameUninstall.setBounds(639, 46, 129, 38);
		contentPane.add(gameUninstall);
		
		JTextArea dlcArea = new JTextArea();
		dlcArea.setEditable(false);
		dlcArea.setBounds(443, 264, 291, 148);
		contentPane.add(dlcArea);
		
		
		JTextArea generalDisplay = new JTextArea();
		generalDisplay.setEditable(false);
		generalDisplay.setBounds(37, 131, 330, 210);
		contentPane.add(generalDisplay);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlcArea.setText("");
				generalDisplay.setText("");
				dispose();
			}
		});
		btnBack.setBounds(24, 388, 136, 38);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("     Your Games: ");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 17, 136, 27);
		contentPane.add(lblNewLabel);
		
		JButton DLCcontentButton = new JButton("Display DLC Content");
		DLCcontentButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		DLCcontentButton.setBounds(463, 168, 278, 22);
		contentPane.add(DLCcontentButton);
		
		JLabel lblNewLabel_1 = new JLabel("   or");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(577, 55, 44, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("            See the extra features for this game:");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_2.setBounds(463, 200, 268, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Current Memory: ");
		lblNewLabel_3.setBounds(221, 59, 97, 13);
		contentPane.add(lblNewLabel_3);
		
		curMem = new JTextField();
		curMem.setText(LibrarySys.getAvailableSpace() + "");
		curMem.setEditable(false);
		
		curMem.setBounds(221, 82, 62, 19);
		contentPane.add(curMem);
		curMem.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("GB");
		lblNewLabel_4.setBounds(293, 85, 36, 13);
		contentPane.add(lblNewLabel_4);
		ownedGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnedGame game = new OwnedGame();
				String gameName= ownedGames.getSelectedItem().toString();
				game = (OwnedGame) LibrarySys.searchOwnedGames(gameName);
				if (!game.getIsInstalled()) {
					gameInstall.setEnabled(true);
					gameUninstall.setEnabled(false);
					DLCcontentButton.setEnabled(false);
					generalDisplay.setText(LibrarySys.gameInfoForStorage(game));
					
				} else if (game.getIsInstalled()) {
					gameInstall.setEnabled(false);
					gameUninstall.setEnabled(true);
					DLCcontentButton.setEnabled(true);
				} else
					System.out.println("Wait");
				dlcArea.setText("");
				curMem.setText(LibrarySys.getAvailableSpace() + "");
			}
			
		});
		
		gameInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnedGame game = new OwnedGame();
				String gameName= ownedGames.getSelectedItem().toString();
				game = (OwnedGame) LibrarySys.searchOwnedGames(gameName);
				if(LibrarySys.installGame(game))
				{
				generalDisplay.append("\n\n\tThe game was successfully installed!\n\tLet's play!\n"
						+ "Current Memory: "+ LibrarySys.getAvailableSpace()+" GB");
				gameInstall.setEnabled(false);
				gameUninstall.setEnabled(true);
				DLCcontentButton.setEnabled(true);
				curMem.setText(LibrarySys.getAvailableSpace()+ "");
				
				}
				else
					generalDisplay.setText("\n\n\tYou do not have enough storage to install this game!");
			lFrame.fillComboBox();
			}
		});
		
		
		gameUninstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnedGame game = new OwnedGame();
				String gameName= ownedGames.getSelectedItem().toString();
				game = (OwnedGame) LibrarySys.searchOwnedGames(gameName);
				LibrarySys.uninstallGame(game);
				generalDisplay.append("\n\n\tThe game was uninstalled!" + 
				"Current Memory: "+ LibrarySys.getAvailableSpace() +" GB");
				gameInstall.setEnabled(true);
				gameUninstall.setEnabled(false);
				DLCcontentButton.setEnabled(false);
				dlcArea.setText("");
				generalDisplay.setText(LibrarySys.gameInfoForStorage(game));
				curMem.setText(LibrarySys.getAvailableSpace() + "");
				lFrame.fillComboBox();
				/*for(int i= 0; i<lFrame.cbGame.getSize();i++) {
					
				}*/
			}
		});
		
		DLCcontentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnedGame game = new OwnedGame();
				String gameName= ownedGames.getSelectedItem().toString();
				game = (OwnedGame) LibrarySys.searchOwnedGames(gameName);
				dlcArea.setText(game.getExtraContent().toString());
				
			}
		});
		
		if (LibrarySys.ownedGamesNames() == null) {
			generalDisplay.setText("\n\tIt seems like you don't have any games in your library yet."
					+ "\n-------------------------------------------\n"
					
					+"Don't panic, go ahead and visit the CAKE store :)\n");
		}
		
	}
}
