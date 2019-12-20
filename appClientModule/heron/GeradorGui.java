package heron;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeradorGui extends JFrame {

	private static final long serialVersionUID = 3545053785228009472L;

	// GUI Components
	private JPanel panel;

	private JPanel fillPanel;

	private JPanel origemPanel;
	private JPanel destinoPanel;

	private JLabel origemLb;
	private JLabel destinoLb;
	private JTextField origemTf;
	private JTextField destinoTf;

	private JPanel acaoPanel;

	private JButton startBt;

	public GeradorGui() {

		origemLb = new JLabel();
		origemLb.setFont(new Font("Consolas", Font.PLAIN, 14));
		origemLb.setHorizontalAlignment(JLabel.CENTER);
		origemLb.setText(" Origem: ");

		destinoLb = new JLabel();
		destinoLb.setFont(new Font("Consolas", Font.PLAIN, 14));
		destinoLb.setHorizontalAlignment(JLabel.CENTER);
		destinoLb.setText(" Destino: ");

		origemTf = new JTextField(Heron.getOrigem(), 62);
		destinoTf = new JTextField(Heron.getDestino(), 62);

		origemPanel = new JPanel();
		origemPanel.setLayout(new BorderLayout());
		origemPanel.add(origemLb, BorderLayout.WEST);
		origemPanel.add(origemTf, BorderLayout.EAST);

		destinoPanel = new JPanel();
		destinoPanel.setLayout(new BorderLayout());
		destinoPanel.add(destinoLb, BorderLayout.WEST);
		destinoPanel.add(destinoTf, BorderLayout.EAST);

		fillPanel = new JPanel();
		fillPanel.setLayout(new BorderLayout());
		fillPanel.add(origemPanel, BorderLayout.NORTH);
		fillPanel.add(destinoPanel, BorderLayout.SOUTH);

		acaoPanel = new JPanel();
		acaoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		startBt = new JButton("Executar");
		// startBt.setPreferredSize(new Dimension(wbtn, hbtn));
		startBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Heron.gerar(origemTf.getText(), destinoTf.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		acaoPanel.add(startBt);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(fillPanel, BorderLayout.NORTH);
		panel.add(acaoPanel, BorderLayout.SOUTH);

		add(panel);

		// setDefaultLookAndFeelDecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gera web.xml");
		// setUndecorated(!titlebar);
		setSize(600, 120);
		// setLocationRelativeTo(null);
		// setLocation(X,Y);
		// setAlwaysOnTop( true );
		// setLocationByPlatform( true );
		pack();
		setVisible(true);

	}
}
