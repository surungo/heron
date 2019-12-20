package AWT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import teste.Move;

public class Chronometer2 extends JFrame {

    private static final long serialVersionUID = 3545053785228009472L;

    // GUI Components
    private JPanel panel;
    private JLabel timeLabel;
    private JTextField tfMin;
    private JTextField tfSec;
    private JTextField tfMil;

    private JPanel buttonPanel;
    private JButton startButton;
    private JButton resetButton;
    private JButton closeButton;
    
    private int wbtn = 60; 
    private int hbtn = 20; 
    
    private int wtfd = 16; 
    private int htfd = 16; 

    private int wtimeLabel = 120; 
    private int htimeLabel = 30; 

    private int X = 0;
    private int Y = 0;
    
    // Properties of Program.
    private byte centiseconds = 0;
    private byte seconds = 0;
    private short minutes = 3;

    private DecimalFormat timeFormatter;

    private Timer timer;
    
    private boolean rodando = false;
    
    private Dimension dtfd;
    private Dimension dtimeLabel;
    
    private boolean titlebar = true;

    public Chronometer2() {
    	
    	dtimeLabel = new Dimension(wtimeLabel, htimeLabel);
    	dtfd = new Dimension(wtfd, htfd);
    	
    	timeFormatter = new DecimalFormat("00");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        timeLabel = new JLabel();
        timeLabel.setPreferredSize(dtimeLabel);
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(timeLabel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(wbtn, hbtn));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if(rodando) {
                    timer.stop();
                    rodando=false;
                    startButton.setText("Start");
            	}else {
	                timer.start();
	                rodando=true;
	                startButton.setText("Stop");
            	}

            }
        });
        buttonPanel.add(startButton);

        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(wbtn, hbtn));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                timer.stop();
                rodando=false;
                startButton.setText("Start");
                try {
                	minutes = Byte.parseByte(tfMin.getText());
                	seconds = Byte.parseByte(tfSec.getText());
                	centiseconds = Byte.parseByte(tfMil.getText());
	                String strTime = timeFormatter.format(minutes) + ":"
	                        + timeFormatter.format(seconds) + "."
	                        + timeFormatter.format(centiseconds);
	
	                timeLabel.setText(strTime);
                }catch (Exception e2) {
					// TODO: handle exception
				}
            }
        });

        buttonPanel.add(resetButton);

        tfMin = new JTextField (timeFormatter.format(minutes), 2); 
        tfSec = new JTextField (timeFormatter.format(seconds), 2); 
        tfMil = new JTextField (timeFormatter.format(centiseconds), 2); 
        tfMin.setPreferredSize(dtfd);
        tfSec.setPreferredSize(dtfd);
        tfMil.setPreferredSize(dtfd);
        buttonPanel.add(tfMin);         
        buttonPanel.add(tfSec);         
        buttonPanel.add(tfMil);         
        
        if(!titlebar) {
	        closeButton = new JButton("Close");
	        closeButton.setPreferredSize(new Dimension(wbtn, hbtn));
	        closeButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.exit(0);
	                
	            }
	        });
	        buttonPanel.add(closeButton); 
        }
        panel.add(buttonPanel, BorderLayout.EAST);        

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centiseconds > 0) {
                    centiseconds--;
                } else {
                    if (seconds == 0 && minutes == 0) {
                        timer.stop();
                    } else if (seconds > 0) {
                        seconds--;
                        centiseconds = 99;
                    } else if (minutes > 0) {
                        minutes--;
                        seconds = 59;
                        centiseconds = 99;
                    }
                }
                timeLabel.setText(timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds) + "."
                        + timeFormatter.format(centiseconds));
            }
            
        });

        timeLabel.setText(timeFormatter.format(minutes) + ":"
                + timeFormatter.format(seconds) + "."
                + timeFormatter.format(centiseconds));

        add(panel);
        
        setDefaultLookAndFeelDecorated(true);
        
        //getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,8));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Chronometer");
        setUndecorated(!titlebar);
       // setSize(300,100);
        setLocationRelativeTo(null);
        setLocation(X,Y);
        setAlwaysOnTop( true );
        setLocationByPlatform( true );
        pack();
        setVisible(true);
        
     /*   
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(300, 280));

        Move ch = new Move();
        frame.getContentPane().add(ch);
        frame.setUndecorated(true);
        
        MoveMouseListener mml = new MoveMouseListener(ch);
        ch.addMouseListener(mml);
        ch.addMouseMotionListener(mml);

        frame.pack();
        frame.setVisible(true);*/
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                new Chronometer2();
            }
        });
    }
   
}