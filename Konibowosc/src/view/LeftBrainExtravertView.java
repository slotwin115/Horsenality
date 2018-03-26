package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import main.MainFrame;
import model.LeftBrainExtravert;

/**
 * Klasa reprezentuj¹ca widok modelu Lewopó³kulowego Ekstrawertyka.
 */
@SuppressWarnings("serial")
public class LeftBrainExtravertView extends AbstractPanel
{
	private JLabel				interval;
	private CharacterChoice[]	characters;
	private JButton				next;
	private JButton				back;
	private JButton				help;
	private MainFrame			mf;
	private LeftBrainExtravert	leftBrainExtravert;
	private String				helpText;
	private JLabel				helpLabel;
	
        /**
         * Konstruktor
         */
	public LeftBrainExtravertView(LeftBrainExtravert leftBrainExtravert)
	{
		this.helpText = "<html><p>Na tym ekranie nale¿y wybraæ stopieñ nasilenia ka¿dej z podanych cech. 0 oznacza, ¿e dana</p>"
				+ "<p>cecha nie ujawnia siê u opisywanego konia a 3, ¿e jest bardzo silna.</p><br />"
				+ "<p>Aby przejœæ na kolejny ekran nale¿y klikn¹æ przycisk <b>„dalej”</b> znajduj¹cy siê w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />" + "<p>Aby cofn¹æ siê do poprzedniego ekranu w celu zmiany wprowadzonych danych nale¿y</p>"
				+ "<p>klikn¹æ przycisk „wstecz” znajduj¹cy siê z lewej strony przycisku <b>„dalej”</b>.</p><br />"
				+ "<p><b>Wyjaœnienie trudniejszych pojêæ:</p></b><br />"
				+ "<p><b>„pyskaty”</b> - ³apie pyskiem wszystko dooko³a i prze¿uwa </p>"
				+ "<p><b>Zabawowy</b> - skory do zabaw</p>";
		
		id = 3;
		adjust(new Dimension(800, 600));
		
		interval = new JLabel("Lewo Pó³kulowy Ekstrawertyk");
		Dimension intervalSize = interval.getPreferredSize();
		interval.setBounds(800 / 2 - intervalSize.width / 2, 50, intervalSize.width, intervalSize.height);
		add(interval);
		
		this.leftBrainExtravert = leftBrainExtravert;
		
		int index = 0;
		int y = 70;
		characters = new CharacterChoice[this.leftBrainExtravert.getNumTypes()];
		for (Map.Entry<String, Integer> type : this.leftBrainExtravert.getEntrySet())
		{
			if (index < 5)
			{
				characters[index] = new CharacterChoice(type.getKey(), 100, y * ((index % 5) + 1), type.getValue());
			}
			else
			{
				characters[index] = new CharacterChoice(type.getKey(), 520, y * ((index % 5) + 1), type.getValue());
			}
			index++;
		}
		
		next = new JButton("dalej");
		next.setBounds(670, 520, 100, 30);
		next.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer[] leftBrainExtravertStates = new Integer[LeftBrainExtravertView.this.leftBrainExtravert.getNumTypes()];
				for (int i = 0; i < LeftBrainExtravertView.this.leftBrainExtravert.getNumTypes(); i++)
				{
					for (int j = 0; j < LeftBrainExtravertView.this.characters[i].gradNum; j++)
					{
						if (LeftBrainExtravertView.this.characters[i].gradation[j].isSelected())
						{
							leftBrainExtravertStates[i] = new Integer(j);
						}
					}
				}
				LeftBrainExtravertView.this.leftBrainExtravert.adjustState(leftBrainExtravertStates);
				LeftBrainExtravertView.this.mf.getIntervalsCount()[0] = new Integer(LeftBrainExtravertView.this.leftBrainExtravert.count());
				
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
				JOptionPane.showMessageDialog(LeftBrainExtravertView.this, helpLabel, "Pomoc", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(help);
		
		setLayout(null);
	}
	
	private class CharacterChoice
	{
		protected JLabel			characterLabel;
		protected ButtonGroup		group;
		protected JRadioButton[]	gradation;
		protected final int			gradNum	= 4;
		
		public CharacterChoice(String characterName, int x, int y, int selected)
		{
			characterLabel = new JLabel(characterName);
			Dimension characterSize = characterLabel.getPreferredSize();
			characterLabel.setBounds(x + 80 - characterSize.width / 2, y + 50, characterSize.width, characterSize.height);
			add(characterLabel);
			
			group = new ButtonGroup();
			gradation = new JRadioButton[gradNum];
			for (int i = 0; i < gradNum; i++)
			{
				gradation[i] = new JRadioButton(Integer.toString(i));
				gradation[i].setBounds(x + (i) * 40, y + 70, 40, 15);
				if (i == 0)
				{
					gradation[i].setSelected(true);
				}
				group.add(gradation[i]);
				add(gradation[i]);
			}
		}
	}
	
	@Override
	public void setMF(MainFrame mf)
	{
		this.mf = mf;
	}
	
	@Override
	public void reset()
	{
		this.leftBrainExtravert.reset();
	}
	
	@Override
	public void update()
	{
		for (int i = 0; i < this.leftBrainExtravert.getNumTypes(); i++)
		{
			this.characters[i].gradation[0].setSelected(true);
		}
	}
}
