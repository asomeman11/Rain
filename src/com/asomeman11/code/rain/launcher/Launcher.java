package com.asomeman11.code.rain.launcher;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import com.burksnet.code.games.rain.Game;
import com.burksnet.code.games.rain.MyProperties;
import com.burksnet.code.games.rain.console.ConsoleToLauncher;;




public class Launcher extends Game{
	private static final long serialVersionUID = 1L;
	private Dimension size = new Dimension(1000, 860);
	private JFrame frame;
	public static JTextArea ConsoleOutFrame;
	public static boolean launcherstarter = false;
	public static boolean closelauncher = MyProperties.close_launcher_on_game_exit;
	public static boolean secondrun = false;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// JFrame Init
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(size);
		frame.setMinimumSize(size);
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setResizable(false);
		frame.setTitle("Rain Launcher");
		frame.setSize(size);
		frame.setLocationRelativeTo((Component)null);
		
		// ConsoleOut Init
		ConsoleOutFrame = new JTextArea();
		ConsoleOutFrame.setForeground(SystemColor.desktop);
		ConsoleOutFrame.setText("");
		ConsoleOutFrame.setBackground(SystemColor.WHITE);
		ConsoleOutFrame.setCaretPosition(ConsoleOutFrame.getDocument().getLength());
		//					  X	  Y  Length With
		ConsoleOutFrame.setBounds(150, 20, 700, 300);
		ConsoleOutFrame.setEditable(false);
		frame.getContentPane().setLayout((LayoutManager)null);
		frame.getContentPane().add(ConsoleOutFrame, BorderLayout.SOUTH);

		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var3) {
            var3.printStackTrace();
        }
		
		//Play
		Button button = new Button("Play");
        button.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == 10){
					if(!running){
					System.out.println("Play");
					try{
					ConsoleOutFrame.setText("Running");
					
				} catch (NullPointerException var3) {
					;				
				}
					if (!secondrun){
					Game.main(null);
					}
					else{
					Game.game.start();
					}
					System.out.println("Play");
					running = true;
        	}
					else System.err.println("An instance of the game is already open!");
				}
				}			
			});
        button.setBounds(25, 60, 100, 30);
        button.addActionListener(new ActionListener() {
            public synchronized void actionPerformed(ActionEvent arg0) {
            	if(!running){
					try{
					ConsoleOutFrame.setText("Running");
					
					} catch (NullPointerException var3) {
						;				
					}
						if (!secondrun){
						Game.main(null);
						}
						else{
						Game.game.start();
						}
						System.out.println("Play");
						running = true;
            	}
            	else System.err.println("An instance of the game is already open!");
            }
        });
        frame.getContentPane().add(button);
        ConsoleOut();
	}
	

	
    public static void ConsoleOut(){
    	if (!running){
    		ConsoleOutFrame.setText("Hello");
    	}

    }

}
