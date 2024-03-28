// revised
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainAndSystem.LibrarySys;
import Others.OwnedGame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Library extends JFrame {

	private JPanel contentPane;
	public JComboBox<String> cbGame;
	private JTextField txt;
	private JTextField hours;
	
	public void fillComboBox() {
		cbGame.removeAllItems();
		cbGame.setModel(new DefaultComboBoxModel(LibrarySys.installedGamesNames()));
	}

	/**
	 * Create the frame.
	 */
	public Library(MainFrame m) {
		setBounds(100, 100, 749, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt = new JTextField();
		txt.setEditable(false);
		txt.setForeground(new Color(0, 255, 255));
		txt.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 12));
		txt.setBackground(new Color(0, 0, 255));
		txt.setText("Your place your CAKE, welcome to the library...");
		txt.setBounds(10, 21, 300, 56);
		contentPane.add(txt);
		txt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Display Installed Games:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setBounds(32, 109, 282, 27);
		contentPane.add(lblNewLabel);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(367, 37, 318, 272);
		contentPane.add(textArea);
		
		cbGame = new JComboBox();
		cbGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		cbGame.setBounds(84, 146, 166, 21);
		contentPane.add(cbGame);
		
		
		
		JButton playButton = new JButton("Play Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnedGame game = new OwnedGame();
				String gameName= cbGame.getSelectedItem().toString();
				game = (OwnedGame) LibrarySys.searchOwnedGames(gameName);
				
				
				if(!(hours.getText().equals("")))
				{
					double hour = Integer.parseInt(hours.getText());
				if(hour>0)
					{
					double before = game.getHoursPlayed();
					textArea.setText(game.playGame(hour));
					}
				}else
					textArea.setText("\t\n\n\nPlease fill the hour area!!!");
			}
		});
		playButton.setBounds(116, 238, 104, 23);
		contentPane.add(playButton);
		
		hours = new JTextField();
		hours.setBounds(236, 194, 111, 20);
		contentPane.add(hours);
		hours.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("How many hours you want to play: ");
		lblNewLabel_1.setBounds(10, 196, 215, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton goMainButton = new JButton("Go Main...");
		goMainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		goMainButton.setBounds(10, 316, 128, 27);
		contentPane.add(goMainButton);
	}
}
