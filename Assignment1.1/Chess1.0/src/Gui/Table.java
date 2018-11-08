package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import gamelogic.*;

public class Table {
	private final JFrame gameFrame;
	private final BoardPanel boardPanel;
	 
	private Game game;
	private final Color lightColor = Color.decode("#C0C0C0");
	private final Color darkColor = Color.decode("#0080FF");
	
	public Table() {
		this.gameFrame = new JFrame("MyChess");
		this.gameFrame.setSize(new Dimension(800, 800));
		this.gameFrame.setVisible(true);
		this.gameFrame.setLayout(new BorderLayout());
		
		this.game = new Game();
		
		// MENU button
		//final JMenuBar tableMenuBar = createTableMenuBar();
		
		this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
	}
	
	private class BoardPanel extends JPanel{
		//final List<TilePanel> boardTiles;
		
		BoardPanel(){
			super(new GridLayout(8, 8));
			for(int i = 0; i <= 7; i++) {
				for(int j = 0; j <= 7; j++) {
					final TilePanel tilePanel = new TilePanel(this, i, j);
					//this.boardTiles.add(tilePanel);
					add(tilePanel);
				}
			} 
			
	        setPreferredSize(new Dimension(500,500));
		}
	}
	
	private class TilePanel extends JPanel{
		private final int i;
		private final int j;
		
		TilePanel(final BoardPanel boardPanel, final int i, final int j){
			super(new GridBagLayout()); 
			this.i = i;
			this.j = j;
			
			setPreferredSize(new Dimension(2, 2));
			
			assignTileColor();
			assignTilePiece(game);
			
			validate();		
		}
		
		private void assignTilePiece(final Game game) {
			//this.removeAll();
			
			if(game.getPiece(i, j) != null && game.getPiece(i, j).getPlayer().getPlayerType() == true) {
				String color = "white";
				String classname =  game.getPiece(i, j).getClass().getSimpleName();
				String filename = color+classname;
				try {
					final BufferedImage image = ImageIO.read(getClass().getResource("../PiecesGUI/" + filename + ".png"));
					add(new JLabel(new ImageIcon(image)));
				} catch(IOException e) {
					return;
				}
			}
			
			if(game.getPiece(i, j) != null && game.getPiece(i, j).getPlayer().getPlayerType() == false) {
				String color = "black";
				String classname =  game.getPiece(i, j).getClass().getSimpleName();
				String filename = color+classname;
				try {
					final BufferedImage image = ImageIO.read(getClass().getResource("../PiecesGUI/" + filename + ".png"));
					add(new JLabel(new ImageIcon(image)));
				} catch(IOException e) {
					return;
				}
			}
		}
		
		private void assignTileColor() {
			boolean isDark = ((i + j) % 2 != 0);
			setBackground(isDark ? darkColor : lightColor);
		}
	}
	
	public static void main(String[] args) {
		Table table = new Table();
	}
}