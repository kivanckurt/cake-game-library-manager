// revised
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainAndSystem.LibrarySys;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class AddBalanceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField currentBalance;
	private JTextField enteredValue;
	private JTextField result;
	private JButton btnNewButton;
	private JLabel ErrorLabel;

	/**
	 * Create the frame.
	 */
	public AddBalanceFrame(MainFrame m) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("We are still using 'TL' as currency");
		lblNewLabel.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNewLabel.setBounds(200, 10, 220, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel1 = new JLabel("Press Enter to add balance!");
		lblNewLabel1.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNewLabel1.setBounds(200, 22, 220, 20);
		contentPane.add(lblNewLabel1);
		
		currentBalance = new JTextField();
		currentBalance.setEditable(false);
		currentBalance.setBounds(151, 46, 86, 19);
		contentPane.add(currentBalance);
		currentBalance.setColumns(10);
		double moneyHad = LibrarySys.getBalanceTotal();
		currentBalance.setText(String.format("%.2f", moneyHad) + "  TL");
		
		JLabel lblNewLabel_1 = new JLabel("Your current Balance: ");
		lblNewLabel_1.setBounds(10, 49, 131, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter amount to load: ");
		lblNewLabel_2.setBounds(10, 107, 188, 36);
		contentPane.add(lblNewLabel_2);
		
		enteredValue = new JTextField();
		enteredValue.setBounds(232, 108, 143, 36);
		contentPane.add(enteredValue);
		enteredValue.setColumns(10);
		enteredValue.addKeyListener((KeyListener) new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if((enteredValue.getText().equals("")) || enteredValue.getText().contains("-")) {
					ErrorLabel.setVisible(true);
				}	
                else {
                if (key == KeyEvent.VK_ENTER) {
                    LibrarySys.addBalance(Double.parseDouble(enteredValue.getText()));
                    double balanceUpdated = LibrarySys.getBalanceTotal();
                    currentBalance.setText(balanceUpdated+ "   TL");
                    result.setText("You loaded "+ Double.parseDouble(enteredValue.getText()) +" TL "
                            + "and your current balance is: " + balanceUpdated + " TL");
                    enteredValue.setText("");
                    ErrorLabel.setVisible(false);
                    m.refresh();
                }
            }
        }});
		
		ErrorLabel = new JLabel("Please enter a valid value");
		ErrorLabel.setBounds(69, 182, 168, 14);
		ErrorLabel.setVisible(false);
		contentPane.add(ErrorLabel);
		
		JButton addButton = new JButton("ACCEPT");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((enteredValue.getText().equals("")) || enteredValue.getText().contains("-")) {
					ErrorLabel.setVisible(true);
				}	
				else {
				LibrarySys.addBalance(Double.parseDouble(enteredValue.getText()));
				double balanceUpdated = LibrarySys.getBalanceTotal();
				currentBalance.setText((String.format("%.2f", balanceUpdated) + "   TL"));
				result.setText("You load "+ Double.parseDouble(enteredValue.getText()) +" TL "
						+ "and your current balance: " + balanceUpdated + " TL");
				enteredValue.setText("");
				ErrorLabel.setVisible(false);
				m.refresh();
				}
			}
		});
		addButton.setBounds(290, 154, 85, 21);
		contentPane.add(addButton);
		
		result = new JTextField();
		result.setFont(new Font("Tahoma", Font.ITALIC, 10));
		result.setText("Always 3D Security in the CAKE Library... We protect you, and we are proud of that :)");
		result.setBackground(Color.ORANGE);
		result.setBounds(10, 207, 416, 46);
		contentPane.add(result);
		result.setColumns(10);
		
		btnNewButton = new JButton("Go Main");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Papyrus", Font.ITALIC, 12));
		btnNewButton.setBounds(319, 49, 107, 26);
		contentPane.add(btnNewButton);
		
		
		
		
		
	}
}
