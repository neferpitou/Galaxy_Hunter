package main;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

import java.awt.GridBagLayout;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import factory.PlayerShipType;
import kernel.Kernel;

@SuppressWarnings("serial")
public class GalaxyHunterMenu extends JPanel {
	private JFrame frame = new JFrame();
	private Kernel kernel = Kernel.getInstance();

	private Image menu_background = kernel.loadImage("NGC_1569.jpg");
	private PlayerShipType selected_ship = PlayerShipType.TALON;
	private final int WIDTH = 500, HEIGHT = 500;

	public GalaxyHunterMenu() {

		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);

		SwingUtilities
				.invokeLater(() -> {

					setBackground(Color.BLACK);
					setLayout(new BorderLayout(0, 0));

					JLabel title = new JLabel("GALAXY HUNTER");
					title.setForeground(Color.WHITE);
					title.setHorizontalAlignment(SwingConstants.CENTER);
					title.setFont( new Font("Serif", Font.BOLD, 20) );
					add(title, BorderLayout.NORTH);
					
					JButton btnStartGame = new JButton("Start Game");
					btnStartGame.addActionListener((e)->{
						frame.dispose();
						Kernel.startGame(selected_ship);
					});
					
					add(btnStartGame, BorderLayout.SOUTH);

					JPanel panel = new JPanel();
					panel.setOpaque(false);
					add(panel, BorderLayout.CENTER);
					GridBagLayout gbl_panel = new GridBagLayout();
					gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0,
							0, 0 };
					gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
					gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0,
							1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
					gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
							1.0, Double.MIN_VALUE };
					panel.setLayout(gbl_panel);

					JLabel lblTalonSelected = new JLabel("Talon Selected");
					lblTalonSelected.setVisible(true);
					lblTalonSelected.setForeground(Color.RED);
					GridBagConstraints gbc_lblTalonSelected = new GridBagConstraints();
					gbc_lblTalonSelected.insets = new Insets(0, 0, 5, 5);
					gbc_lblTalonSelected.gridx = 1;
					gbc_lblTalonSelected.gridy = 0;
					panel.add(lblTalonSelected, gbc_lblTalonSelected);

					JLabel lblVixenSelected = new JLabel("Vixen Selected");
					lblVixenSelected.setForeground(Color.GREEN);
					lblVixenSelected.setVisible(false);
					GridBagConstraints gbc_lblVixenSelected = new GridBagConstraints();
					gbc_lblVixenSelected.insets = new Insets(0, 0, 5, 5);
					gbc_lblVixenSelected.gridx = 3;
					gbc_lblVixenSelected.gridy = 0;
					panel.add(lblVixenSelected, gbc_lblVixenSelected);

					JLabel lblKnightSelected = new JLabel("Knight Selected");
					lblKnightSelected.setVisible(false);
					lblKnightSelected.setForeground(Color.CYAN);
					GridBagConstraints gbc_lblKnightSelected = new GridBagConstraints();
					gbc_lblKnightSelected.insets = new Insets(0, 0, 5, 5);
					gbc_lblKnightSelected.gridx = 5;
					gbc_lblKnightSelected.gridy = 0;
					panel.add(lblKnightSelected, gbc_lblKnightSelected);

					JRadioButton rdbtnNewRadioButton = new JRadioButton(
							loadShipImage("talon.png"), true);
					rdbtnNewRadioButton.setOpaque(false);
					rdbtnNewRadioButton.addActionListener((e) -> {
						lblTalonSelected.setVisible(true);
						lblVixenSelected.setVisible(false);
						lblKnightSelected.setVisible(false);
						selected_ship = PlayerShipType.TALON;
					});

					GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
					gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
					gbc_rdbtnNewRadioButton.gridx = 1;
					gbc_rdbtnNewRadioButton.gridy = 2;
					panel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

					JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
							loadShipImage("vixen.png"), false);
					rdbtnNewRadioButton_1.setOpaque(false);
					rdbtnNewRadioButton_1.addActionListener((e) -> {
						lblTalonSelected.setVisible(false);
						lblVixenSelected.setVisible(true);
						lblKnightSelected.setVisible(false);
						selected_ship = PlayerShipType.VIXEN;
					});

					GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
					gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
					gbc_rdbtnNewRadioButton_1.gridx = 3;
					gbc_rdbtnNewRadioButton_1.gridy = 2;
					panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

					JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
							loadShipImage("knight.png"), false);
					rdbtnNewRadioButton_2.setOpaque(false);
					rdbtnNewRadioButton_2.addActionListener((e) -> {
						lblTalonSelected.setVisible(false);
						lblVixenSelected.setVisible(false);
						lblKnightSelected.setVisible(true);
						selected_ship = PlayerShipType.KNIGHT;
					});
					
					GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
					gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
					gbc_rdbtnNewRadioButton_2.gridx = 5;
					gbc_rdbtnNewRadioButton_2.gridy = 2;
					panel.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

					JTextPane textPane = new JTextPane();
					textPane.setOpaque(false);
					String desc = "A standard, well-balanced space ship that has fared well in the war against Enemy X. Never a bad choice to bring into war with you\n\nWeapon: Missiles\nDurability: Medium\nSpeed: Medium";
					setTextPaneProperties(textPane, Color.RED, desc);

					GridBagConstraints gbc_textPane = new GridBagConstraints();
					gbc_textPane.insets = new Insets(0, 0, 0, 5);
					gbc_textPane.fill = GridBagConstraints.BOTH;
					gbc_textPane.gridx = 1;
					gbc_textPane.gridy = 4;
					panel.add(textPane, gbc_textPane);

					JTextPane textPane_1 = new JTextPane();
					textPane_1.setOpaque(false);
					desc = "A ship with more of a focus on defense than offense. Has since found popularity amongst pilots that prefer living.\n\nWeapon: Energy Blast\nDurability: High\nSpeed: Low";
					setTextPaneProperties(textPane_1, Color.GREEN, desc);
					
					GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
					gbc_textPane_1.insets = new Insets(0, 0, 0, 5);
					gbc_textPane_1.fill = GridBagConstraints.BOTH;
					gbc_textPane_1.gridx = 3;
					gbc_textPane_1.gridy = 4;
					panel.add(textPane_1, gbc_textPane_1);

					JTextPane textPane_2 = new JTextPane();
					textPane_2.setOpaque(false);
					desc = "A prototype built by humans as a way to keep up with the nimble fighters of Enemy X. Capable of dodging attacks other ships may not be, but is easily blasted apart. High risk, high reward.\n\nWeapon: Lasers\nDurability: Low\nSpeed: High";
					setTextPaneProperties(textPane_2, Color.CYAN, desc);
					
					GridBagConstraints gbc_textPane_2 = new GridBagConstraints();
					gbc_textPane_2.insets = new Insets(0, 0, 0, 5);
					gbc_textPane_2.fill = GridBagConstraints.BOTH;
					gbc_textPane_2.gridx = 5;
					gbc_textPane_2.gridy = 4;
					panel.add(textPane_2, gbc_textPane_2);

					frame.add(this);
					frame.pack();
					frame.setVisible(true);
				});

	}
	
	private void setTextPaneProperties(JTextPane textPane, Color color, String info){
		Style style = textPane.addStyle("", null);
		StyledDocument doc = textPane.getStyledDocument();
		StyleConstants.setForeground(style, color);
		StyleConstants.setBackground(style, new Color(0,0,0,128));
		StyleConstants.setBold(style, true);
		
		try {
			doc.insertString(doc.getLength(), info, style);
		} catch (BadLocationException e) {
			
		}
	}

	private ImageIcon loadShipImage(String imgpath) {
		return new ImageIcon(kernel.loadImage(imgpath));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(menu_background, 0, 0, getWidth(), getHeight(), null);
	}

}
