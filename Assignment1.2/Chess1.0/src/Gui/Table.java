package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import gamelogic.*;

public class Table {
	
	private final JFrame gameFrame, forName;
	private final Boardbuttons boardbuttons;
	public String p1Name,p2Name;
	private Game game;
	private final Color lightColor = Color.decode("#C0C0C0");
	private final Color darkColor = Color.decode("#0080FF");
	public boolean goesFirst = true;
	private Icon oldIcon;
	private Icon newIcon;
	private JButton selected = null;
	private JButton from = null;
	private JButton to = null;
	private Piece p2 = null;
	private int player1Score  = 0, player2Score = 0;
	private int custom;
	
	public Table(int custom, int p1s, int p2s, String player1, String player2) {
		player1Score = p1s;
		player2Score = p2s;
		 		
		this.custom = custom;
		// JFrames
		this.gameFrame = new JFrame();
		this.gameFrame.setTitle("MyChess");
		this.gameFrame.setSize(new Dimension(800, 800));
		this.gameFrame.setVisible(true);
		this.gameFrame.setResizable(false);
		this.gameFrame.setLocationRelativeTo(null);
		this.gameFrame.setLayout(new GridLayout(8,8));
		this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// game
		this.game = new Game(custom);
		 
		// MENU button
		final JMenuBar tableMenuBar = createTableMenuBar();
		this.gameFrame.setJMenuBar(tableMenuBar);
		
		// boardPanel, tile Panel
		this.boardbuttons = new Boardbuttons();
		
		forName = new JFrame();
		if(player1 == "" || player2 == "")
			forName.setVisible(true);
		else
			forName.setVisible(false);
		p1Name = player1;
		p2Name = player2;

		forName.setTitle("Name");
		forName.setLocationRelativeTo(null);
		forName.setSize(new Dimension(400,400));
		JPanel name = new JPanel();
		forName.add(name);
		
		JLabel label = new JLabel("Please enter each players name: (Player 1 first then Player2) ");
		name.add(label);
		
		JTextField fieldName = new JTextField();
		JTextField fieldName2 = new JTextField();
		fieldName.setPreferredSize(new Dimension(200, 30));
		name.add(fieldName);
		fieldName2.setPreferredSize(new Dimension(200, 30));
		name.add(fieldName2);
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				  p1Name = fieldName.getText();
				  p2Name = fieldName2.getText();
				  JLabel label2 = new JLabel("Player 1: " + player1);
				  name.add(label2);
				  JLabel label3 = new JLabel("Player 2: " + player2);
				  name.add(label3);
				  
			}
			
		});
		submit.setPreferredSize(new Dimension(200, 30));
		name.add(submit);
	}
	
	private JMenuBar createTableMenuBar() {
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.add(createFileMenu());
	 
		return tableMenuBar;
	}
	
	private JMenu createFileMenu() {
		final JMenu menu = new JMenu("Menu");
		
		final JMenuItem newGame = new JMenuItem("New game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				forName.setVisible(false);
				gameFrame.setVisible(false);
				
				Table t = new Table(custom, player1Score, player2Score, p1Name, p2Name);
				 
			}
		});
		
		final JMenuItem forfeitGame = new JMenuItem("Forfeit game");
		forfeitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Forfeit game");
				if(goesFirst == true) {
					player2Score++;
					JOptionPane.showConfirmDialog(null, p1Name + "'s score: " + player1Score +" "+ p2Name + "'s score: " + player2Score , "Result",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					player1Score++;
					JOptionPane.showConfirmDialog(null, p1Name + "'s score: " + player1Score +" "+ p2Name + "'s score: " + player2Score , "Result",JOptionPane.PLAIN_MESSAGE);
				}
				forName.setVisible(false);
				gameFrame.setVisible(false);
				Table t = new Table(custom, player1Score, player2Score, p1Name, p2Name);
			}
		});
		
		final JMenuItem quitGame = new JMenuItem("Quit");
		quitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				forName.setVisible(false);
				gameFrame.setVisible(false);
			}
		});
		
		final JMenuItem undoGame = new JMenuItem("Undo");
		undoGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				if(from == null && to == null) {return;}
				// handles icon
				oldIcon = to.getIcon();
				from.setIcon(oldIcon);
				to.setIcon(newIcon);
				
				// undo call
				Piece p = (Piece) to.getClientProperty("piece");
				Location loc = (Location) from.getClientProperty("loc");
				Location toloc = (Location) to.getClientProperty("loc");
				game.start.board.undo(p, loc, toloc);
				
				// retrieve the object
				from.putClientProperty("piece", p);
				to.putClientProperty("piece", p2);
				
				// clear data
				from = null;
				to = null;
				
				// handles turn
				if(goesFirst == true) {
           		 goesFirst = false;
           	 	}
				else {
           		 goesFirst = true;
				}
			}
		});
		
		final JMenuItem scoreBoard = new JMenuItem("Scores");
		scoreBoard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showConfirmDialog(null, p1Name + "'s score: " + player1Score +" "+ p2Name + "'s score: " + player2Score , "Score",JOptionPane.PLAIN_MESSAGE);
            	 return;
			}
		});
		
		
		menu.add(newGame);
		menu.add(forfeitGame);
		menu.add(undoGame);
		menu.add(scoreBoard);
		menu.add(quitGame);
		return menu;
	}
	 
	private class Boardbuttons{
		
		Boardbuttons(){
			// local variables JPanel and JButton
			JPanel tiles[][] = new JPanel[8][8];
	        JButton button[][] = new JButton[8][8];
	        
	        for (int i = 0; i <= 7; i++){
	            for (int j = 0; j <= 7; j++){

	                button[i][j] = new JButton();
	                tiles[i][j] = new JPanel();
	                
	                boolean isDark = ((i + j) % 2 != 0);
	    			button[i][j].setBackground(isDark ? darkColor : lightColor);
	    			
	    			button[i][j].setPreferredSize(new Dimension(100, 100));
	    			button[i][j].addActionListener(new buttonListener());
	                button[i][j].putClientProperty("loc", new Location(i, j));
	               
	                tiles[i][j].add(button[i][j]);
	                gameFrame.add(tiles[i][j]);
	               
	                if(game.getPiece(i, j) != null && game.getPiece(i, j).getPlayer().getPlayerType() == true) {
	    				String color = "white";
	    				String classname =  game.getPiece(i, j).getClass().getSimpleName();
	    				String filename = color+classname;
	    				try {
	    					final BufferedImage image = ImageIO.read(getClass().getResource("../PiecesGUI/" + filename + ".png"));
	    					button[i][j].setIcon(new ImageIcon(image));
	    					button[i][j].putClientProperty("piece", game.getPiece(i, j));
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
	    					button[i][j].setIcon(new ImageIcon(image));
	    					button[i][j].putClientProperty("piece", game.getPiece(i, j));
	    				} catch(IOException e) {
	    					return;
	    				}
	                }
	            }
	        }
		}
	}
	
	private class buttonListener implements ActionListener {
		@Override
		 public void actionPerformed(ActionEvent e) {
			
			 JButton curButton = (JButton) e.getSource();
			 String color = "";
			  
			 if(selected == null && curButton.getClientProperty("piece") == null) {
            	 return;
             }
			 
			 if (selected == null && curButton.getClientProperty("piece") != null){
				 Piece p = (Piece) curButton.getClientProperty("piece");
				 
				 int i = p.getLocation().getRow();
	             int j = p.getLocation().getCol(); 
	             Enum classname =  p.getType(); 
	              
	             if(p.getPlayer().getPlayerType() != goesFirst) {
	            	 JOptionPane.showConfirmDialog(null,"It's not your turn!","Warning!", JOptionPane.PLAIN_MESSAGE);
	            	 return;
	             }
	             
	             if(p.getPlayer().getPlayerType() == true) color = "white";
	             else color = "black";
	             selected = curButton;
	             System.out.println("You selected " + color + classname + " at (" + i +"," + j +")");
	         } 
			 else {
				 Location loc = (Location) curButton.getClientProperty("loc");
				 int cur_i = loc.getRow();
				 int cur_j = loc.getCol();
	            // System.out.println("(" + cur_i +"," + cur_j +")");
	             Piece p = (Piece) selected.getClientProperty("piece");
	             
	              
	             if(game.start.board.movePiece(p, loc, game.start, p.getPlayer())) {
	            	 // Icons
	            	 oldIcon = selected.getIcon();
	            	 newIcon = curButton.getIcon();            	 
	            	 
	            	 // get old property
	            	 p2 = (Piece) curButton.getClientProperty("piece");
	            	 curButton.setIcon(oldIcon);
	            	 selected.setIcon(null);
	            	 // put old property to new location
	            	 curButton.putClientProperty("piece", p);
	            	 //System.out.println(p.getType() + "KING!!!!!!!!!!!!");
	            	 // set old property equal to null
	            	 selected.putClientProperty("piece", null);
	            	
	            	       	 
	            	 if(goesFirst == true) {
	            		 goesFirst = false;
	            	 }
	            	 else {
	            		 goesFirst = true;
	            	 }
	            	 
	            	 from = selected;
	            	 to = curButton;
	            	 Enum classname =  p.getType(); 
	            	 if(p.getPlayer().getPlayerType() == true) color = "white";
		             else color = "black";
	            	 System.out.println("You moved "+ color + classname + " to (" + cur_i +"," + cur_j +")");
	            	 
	            	 Piece king = game.start.board.findKing(game.start, p.getPlayer(), 1);
		             if(king == null) {
		            	 if(p.getPlayer().getPlayerType() == true) {
		            			 color = "white";
		    	            	 JOptionPane.showConfirmDialog(null, color +" WINS !!", "Game over", JOptionPane.PLAIN_MESSAGE);
		            			 player1Score++;
		            			 gameFrame.setFocusable(false);
		            			 //forName.setVisible(false);
		            			  
		            	 }
			             else { 
			            	color = "black";
		            	 JOptionPane.showConfirmDialog(null, color +" WINS !!", "Game over", JOptionPane.PLAIN_MESSAGE);
		            	 player2Score++;
		            	 gameFrame.setFocusable(false);
            			 forName.setVisible(false);
		            	  
			             }
		            	  
		             }
		             
		             if(game.start.board.ischecked(p, game.start, p.getPlayer(), 1)){
		            	 if(p.getPlayer().getPlayerType() == true) color = "black";
			             else color = "white";
		            	 JOptionPane.showConfirmDialog(null,"Your "+ color + " king is in check!","Checked!", JOptionPane.PLAIN_MESSAGE);
		             }
		             else {
		            	 System.out.println("not checked!");
		             }
		              
		             if(game.start.board.isStaleMate(p, game.start, p.getPlayer())) {
		            	 JOptionPane.showConfirmDialog(null,"Game Tied! Stalemate!","Game over", JOptionPane.PLAIN_MESSAGE);
		             }
	             }
	             else {
	            	 System.out.println("Invalid move!");
	             }
	            
	             selected = null;
	             
	         }
			 
		 }
	}
		
		 
	public static void main(String[] args) {
		int yesNo = JOptionPane.showConfirmDialog(null, "Play Custom Chess Pieces?", "Welcome!", JOptionPane.YES_NO_OPTION);
		System.out.println(yesNo);
		int p1s = 0, p2s = 0;
		String player1 = "";
		String player2 = "";
		
		Table table = new Table(yesNo, p1s, p2s, player1, player2);
	}
}