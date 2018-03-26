package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.MainFrame;
import model.Extravert;
import model.Introvert;

/**
 * Klasa reprezentuj¹ca widok modelu Ekstrawertyka i Introwertyka.
 */
@SuppressWarnings("serial")
public class ExtravertIntrovertView extends AbstractPanel
{
	private JLabel		instructions;
	private JLabel		extravertLabel;
	private JLabel		introvertLabel;
	private JCheckBox[]	extravertBox;
	private JCheckBox[]	introvertBox;
	private JButton		next;
	private JButton		back;
	private JButton		help;
	private MainFrame	mf;
	private Extravert	extravert;
	private Introvert	introvert;
	private String		helpText;
	private JLabel		helpLabel;
	
        /**
         * Konstruktor
         */
	public ExtravertIntrovertView(Extravert extravert, Introvert introvert)
	{
		this.helpText = "<html><p>Na tym ekranie nale¿y zaznaczyæ cechy, (klikaj¹c na odpowiednie miejsce przy nazwie cechy)</p>"
				+ "<p>które posiada twój koñ.</p><br />"
				+ "<p>Aby przejœæ na kolejny ekran nale¿y klikn¹æ przycisk <b>„dalej”</b> znajduj¹cy siê w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />"
				+ "<p>Aby cofn¹æ siê do poprzedniego ekranu w celu zmiany wprowadzonych danych nale¿y</p>"
				+ "<p>klikn¹æ przycisk „wstecz” znajduj¹cy siê z lewej strony przycisku <b>„dalej”</b>.</p><br />"
				+ "<p><b>Wyjaœnienie trudniejszych pojêæ:</p></b><br />"
				+ "<p><b>„do pchania”</b> - niechêtnie wykonuje æwiczenia</p>"
				+ "<p><b>„do przodu”</b> - chce wykonaæ æwiczenie zanim zostanie zadane</p></<html>>";
		
		id = 1;
		adjust(new Dimension(800, 600));
		
		this.extravert = extravert;
		this.introvert = introvert;
		instructions = new JLabel("Zaznacz cechy");
		Dimension instructionsSize = instructions.getPreferredSize();
		instructions.setBounds(800 / 2 - instructionsSize.width / 2, 50, instructionsSize.width, instructionsSize.height);
		add(instructions);
		
		extravertLabel = new JLabel("Ekstrawertyk");
		Dimension extravertLabelSize = extravertLabel.getPreferredSize();
		extravertLabel.setBounds(100, 175, extravertLabelSize.width, extravertLabelSize.height);
		add(extravertLabel);
		
		introvertLabel = new JLabel("Introwertyk");
		Dimension introvertLabelSize = introvertLabel.getPreferredSize();
		introvertLabel.setBounds(500, 175, introvertLabelSize.width, introvertLabelSize.height);
		add(introvertLabel);
		
		int index = 0;
		int y = 200;
		extravertBox = new JCheckBox[this.extravert.getNumTypes()];
		for (Map.Entry<String, Boolean> type : this.extravert.getEntrySet())
		{
			extravertBox[index] = new JCheckBox(type.getKey());
			extravertBox[index].setSelected(type.getValue());
			extravertBox[index].setBounds(100, y, 200, 50);
			add(extravertBox[index]);
			index++;
			y += 50;
		}
		
		index = 0;
		y = 200;
		introvertBox = new JCheckBox[this.introvert.getNumTypes()];
		for (Map.Entry<String, Boolean> type : this.introvert.getEntrySet())
		{
			introvertBox[index] = new JCheckBox(type.getKey());
			introvertBox[index].setSelected(type.getValue());
			introvertBox[index].setBounds(500, y, 200, 50);
			add(introvertBox[index]);
			index++;
			y += 50;
		}
		
		next = new JButton("dalej");
		next.setBounds(670, 520, 100, 30);
		next.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Boolean[] extravertStates = new Boolean[ExtravertIntrovertView.this.extravert.getNumTypes()];
				for (int i = 0; i < ExtravertIntrovertView.this.extravert.getNumTypes(); i++)
				{
					extravertStates[i] = new Boolean(extravertBox[i].isSelected());
				}
				
				ExtravertIntrovertView.this.extravert.adjustState(extravertStates);
				ExtravertIntrovertView.this.mf.getTypesCount()[0] = new Integer(ExtravertIntrovertView.this.extravert.count());
				
				Boolean[] introvertStates = new Boolean[ExtravertIntrovertView.this.introvert.getNumTypes()];
				for (int i = 0; i < ExtravertIntrovertView.this.introvert.getNumTypes(); i++)
				{
					introvertStates[i] = new Boolean(introvertBox[i].isSelected());
				}
				
				ExtravertIntrovertView.this.introvert.adjustState(introvertStates);
				ExtravertIntrovertView.this.mf.getTypesCount()[1] = new Integer(ExtravertIntrovertView.this.introvert.count());
				
				mf.setPanel(id + 1);
			}
		});
		add(next);
		
		back = new JButton("wstecz");
		back.setBounds(555, 520, 100, 30);
		back.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mf.setPanel(id - 1);
			}
		});
		add(back);
		
		help = new JButton("pomoc");
		help.setBounds(15, 520, 100, 30);
		help.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				helpLabel = new JLabel(helpText);
				helpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
				JOptionPane.showMessageDialog(ExtravertIntrovertView.this, helpLabel, "Pomoc", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(help);
		
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
		this.extravert.reset();
		this.introvert.reset();
	}
	
	@Override
	public void update()
	{
		int index = 0;
		for (Map.Entry<String, Boolean> type : this.extravert.getEntrySet())
		{
			extravertBox[index].setSelected(type.getValue());
			index++;
		}
		
		index = 0;
		for (Map.Entry<String, Boolean> type : this.introvert.getEntrySet())
		{
			introvertBox[index].setSelected(type.getValue());
			index++;
		}
	}
}
