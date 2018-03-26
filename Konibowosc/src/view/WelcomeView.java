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
 * Klasa b�d�ca reprezentacj� widoki okana powitalnego.
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
		this.instruction = "<html><p>Program pomaga w okre�leniu osobowo�ci konia. Aby rozpocz�� kliknij przycisk <b>�Rozpocznij�</b></p><br />"
				+ "<p>Po rozpocz�ciu pojawi� si� kolejno sze�� ekran�w do wprowadzania danych o cechach</p>"
				+ "<p>twojego konia. Na pierwszych dw�ch ekranach nale�y zaznaczy� cechy, (klikaj�c na</p>"
				+ "<p>odpowiednie miejsce przy nazwie cechy) kt�re posiada tw�j ko�. Na kolejnych czterech</p>"
				+ "<p>nale�y wybra� stopie� nasilenia ka�dej z podanych cech. 0 oznacza, �e dana cecha nie</p>"
				+ "<p>ujawnia si� u opisywanego konia a 3, �e jest bardzo silna.</p><br />"
				+ "<p>Aby przej�� na kolejny ekran nale�y klikn�� przycisk <b>�dalej�</b> znajduj�cy si� w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />"
				+ "<p>Aby cofn�� si� do poprzedniego ekranu w celu zmiany wprowadzonych danych nale�y</p>"
				+ "<p>klikn�� przycisk �wstecz� znajduj�cy si� z lewej strony przycisku <b>�dalej�</b>.</p><br />"
				+ "<p>Aby uzyska� dodatkow� pomoc, nale�y nacisn�� przycisk �pomoc� umieszczony w lewym</p>"
				+ "<p>dolnym rogu ekranu.</p><br />"
				+ "<p>Na ostatnim ekranie, przycisk �dalej� zamieniony jest na <b>�wynik�</b> i po jego naci�ni�ciu</p>"
				+ "<p>program zako�czy wprowadzanie danych i poka�e wyliczony rezultat okre�laj�cy osobowo��</p>"
				+ "<p>twojego konia.</p><br />"
				+ "<p>Na koniec pojawi si� ekran z wynikiem oraz dwa przyciski na dole ekranu. <b>�start�</b> po</p>"
				+ "<p>naci�ni�ciu kt�rego wr�cimy do ekranu pocz�tkowego programu i b�dziemy mogli wykona�</p>"
				+ "<p>test jeszcze raz, oraz <b>�zako�cz�</b> kt�ry zamknie nasz program.</p>";
		
		
		id = 0;
		adjust(new Dimension(800, 600));
		
		welcomeLabel = new JLabel("<html><b>Witaj, ten program pomo�e ci okre�li� osobowo�� twojego konia.</html></b>");
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
