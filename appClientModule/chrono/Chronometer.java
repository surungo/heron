package chrono;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import AWT.MoveMouseListener;


public class Chronometer extends JFrame {

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
    private JButton layoutButton;
    private JButton closeButton;
    
    private int wbtn = 54; 
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
    
    private boolean titlebar = false;
    
    private String play = "S";
    private String pause = "P";
    private String reset = "R";
    private String layout = "<>";
    private String close = "X";
    
    private int positionlayout = 0;    
    private boolean showDecimos = true;    

    public Chronometer() {
    	
    	dtimeLabel = new Dimension(wtimeLabel, htimeLabel);
    	dtfd = new Dimension(wtfd, htfd);
    	
    	timeFormatter = new DecimalFormat("00");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        timeLabel = new JLabel();
        timeLabel.setPreferredSize(dtimeLabel);
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        colorInicial();
     
        panel.add(timeLabel, BorderLayout.WEST);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton(play);
        startButton.setPreferredSize(new Dimension(wbtn, hbtn));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	rodar();

            }

        });
        buttonPanel.add(startButton);

        resetButton = new JButton(reset);
        resetButton.setPreferredSize(new Dimension(wbtn, hbtn));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	reset();
                
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
        
        layoutButton = new JButton(layout);
        layoutButton.setPreferredSize(new Dimension(wbtn, hbtn));
        layoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//
            	//panel.remove(buttonPanel);
            	switch (positionlayout) {
            	case 0:
            		panel.add(timeLabel, BorderLayout.EAST);
            		panel.add(buttonPanel, BorderLayout.WEST);
            		showDecimos=true;
            		positionlayout++;
					break;
            	case 1:
					panel.add(timeLabel, BorderLayout.WEST);
					panel.add(buttonPanel, BorderLayout.EAST);
					showDecimos=true;
					positionlayout++;
					break;

            	case 2:
            		panel.add(timeLabel, BorderLayout.EAST);
            		panel.add(buttonPanel, BorderLayout.WEST);
            		showDecimos=false;
					positionlayout++;
					break;
            	case 3:
					panel.add(timeLabel, BorderLayout.WEST);
					panel.add(buttonPanel, BorderLayout.EAST);
					showDecimos=false;
					positionlayout=0;
					break;

				default:
						panel.add(timeLabel, BorderLayout.EAST);
						panel.add(buttonPanel, BorderLayout.WEST);
						showDecimos=true;
						break;
				}
            	if(!rodando)reset();
            	panel.validate();
            }
        });
        buttonPanel.add(layoutButton);
        
        if(!titlebar) {
	        closeButton = new JButton(close);
	        closeButton.setPreferredSize(new Dimension(wbtn, hbtn));
	        closeButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.exit(0);
	                
	            }
	        });
	        buttonPanel.add(closeButton); 
        }
        panel.add(buttonPanel);        

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
                if(minutes==0  && seconds<10 )colorAlerta();
                timeLabel.setText(timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds) + 
                        (showDecimos? ("." + timeFormatter.format(centiseconds) ):"") );
            }
            
        });

        timeLabel.setText(timeFormatter.format(minutes) + ":"
                + timeFormatter.format(seconds) + 
                (showDecimos? ("." + timeFormatter.format(centiseconds) ):"") );
        
        add(panel);

        MoveMouseListener mml = new MoveMouseListener(this);
        panel.addMouseListener(mml);
        panel.addMouseMotionListener(mml);
        
        setDefaultLookAndFeelDecorated(true);
                
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chronometer");
        setUndecorated(!titlebar);
        //setSize(300,100);
        setLocationRelativeTo(null);
        setLocation(X,Y);
        setAlwaysOnTop( true );
        //setLocationByPlatform( true );
        pack();
        setVisible(true);
        
    }
    
    public void colorInicial() {
		timeLabel.setForeground(Color.black);
		//panel.setBackground(Color.gray);
		panel.setBackground(new Color(0xeeeeee));
		
		
	}
	
	public void colorAlerta() {
		timeLabel.setForeground(Color.white);
		panel.setBackground(Color.red);
		
	}

	public void rodar() {
		if(rodando) {
            timer.stop();
            rodando=false;
            startButton.setText(play);
    	}else {
    		
            timer.start();
            rodando=true;
            startButton.setText(pause);
    	}
		colorInicial();
		
		
	}   
	
	public void vai() {
	    timer.start();
        rodando=true;
        startButton.setText(pause);
        colorInicial();
		
	}   

	public void reset() {
		timer.stop();
        rodando=false;
        startButton.setText(play);
        try {
        	minutes = Byte.parseByte(tfMin.getText());
        	seconds = Byte.parseByte(tfSec.getText());
        	centiseconds = Byte.parseByte(tfMil.getText());
            String strTime = timeFormatter.format(minutes) + ":"
                    + timeFormatter.format(seconds) +(showDecimos? ("."
                    + timeFormatter.format(centiseconds) ):"");

            timeLabel.setText(strTime);
        }catch (Exception e2) {
			// TODO: handle exception
		}
        colorInicial();
		
	}
	
	public JComponent getPanel() {
		// TODO Auto-generated method stub
		return panel;
	}

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                new Chronometer();
            }
        });
    }
   
}