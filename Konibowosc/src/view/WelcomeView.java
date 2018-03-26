package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.MainFrame;

/**
 * Klasa bêd¹ca reprezentacj¹ widoki okana powitalnego.
 */
@SuppressWarnings("serial")
public class WelcomeView extends AbstractPanel
{
	private JButton		beginButton;
	private JButton		instructions;
	private JLabel		welcomeLabel;
	private MainFrame	mf;
	private String		instruction;
	private JLabel		instructionLabel;
	
        /**
         * Konstruktor
         */
	public WelcomeView()
	{
		this.instruction = "<html><p>Program pomaga w okreœleniu osobowoœci konia. Aby rozpocz¹æ kliknij przycisk <b>„Rozpocznij”</b></p><br />"
				+ "<p>Po rozpoczêciu pojawi¹ siê kolejno szeœæ ekranów do wprowadzania danych o cechach</p>"
				+ "<p>twojego konia. Na pierwszych dwóch ekranach nale¿y zaznaczyæ cechy, (klikaj¹c na</p>"
				+ "<p>odpowiednie miejsce przy nazwie cechy) które posiada twój koñ. Na kolejnych czterech</p>"
				+ "<p>nale¿y wybraæ stopieñ nasilenia ka¿dej z podanych cech. 0 oznacza, ¿e dana cecha nie</p>"
				+ "<p>ujawnia siê u opisywanego konia a 3, ¿e jest bardzo silna.</p><br />"
				+ "<p>Aby przejœæ na kolejny ekran nale¿y klikn¹æ przycisk <b>„dalej”</b> znajduj¹cy siê w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />"
				+ "<p>Aby cofn¹æ siê do poprzedniego ekranu w celu zmiany wprowadzonych danych nale¿y</p>"
				+ "<p>klikn¹æ przycisk „wstecz” znajduj¹cy siê z lewej strony przycisku <b>„dalej”</b>.</p><br />"
				+ "<p>Aby uzyskaæ dodatkow¹ pomoc, nale¿y nacisn¹æ przycisk „pomoc” umieszczony w lewym</p>"
				+ "<p>dolnym rogu ekranu.</p><br />"
				+ "<p>Na ostatnim ekranie, przycisk „dalej” zamieniony jest na <b>„wynik”</b> i po jego naciœniêciu</p>"
				+ "<p>program zakoñczy wprowadzanie danych i poka¿e wyliczony rezultat okreœlaj¹cy osobowoœæ</p>"
				+ "<p>twojego konia.</p><br />"
				+ "<p>Na koniec pojawi siê ekran z wynikiem oraz dwa przyciski na dole ekranu. <b>„start”</b> po</p>"
				+ "<p>naciœniêciu którego wrócimy do ekranu pocz¹tkowego programu i bêdziemy mogli wykonaæ</p>"
				+ "<p>test jeszcze raz, oraz <b>„zakoñcz”</b> który zamknie nasz program.</p>";
		
		
		id = 0;
		adjust(new Dimension(800, 600));
		
		welcomeLabel = new JLabel("<html><b>Witaj, ten program pomo¿e ci okreœliæ osobowoœæ twojego konia.</html></b>");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		Dimension welcomeLabelSize = welcomeLabel.getPreferredSize();
		welcomeLabel.setBounds(800 / 2 - welcomeLabelSize.width / 2, 100, welcomeLabelSize.width, welcomeLabelSize.height);
		add(welcomeLabel);
		
		beginButton = new JButton("Rozpocznij");
		beginButton.setBounds(800 / 2 - 100, 200, 200, 60);
		beginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mf.setPanel(id + 1);
			}
		});
		add(beginButton);
		
		instructions = new JButton("Instrukcja");
		instructions.setBounds(800 / 2 - 100, 300, 200, 60);
		instructions.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				instructionLabel = new JLabel(instruction);
				instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
				JOptionPane.showMessageDialog(WelcomeView.this, WelcomeView.this.instructionLabel, "Instrukcja", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(instructions);
				
		setLayout(null);
	}
	
	@Override
	public void setMF(MainFrame mf)
	{
		this.mf = mf;
	}
	
	@Override
	public void reset()
	{
	}
	
	@Override
	public void update()
	{
	}
}
