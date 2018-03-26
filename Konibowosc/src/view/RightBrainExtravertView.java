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
import model.RightBrainExtravert;

/**
 * Klasa reprezentuj¹ca widok modelu prawopó³kulowego Ekstrawertyka.
 */
@SuppressWarnings("serial")
public class RightBrainExtravertView extends AbstractPanel
{
	private JLabel				interval;
	private CharacterChoice[]	characters;
	private JButton				next;
	private JButton				back;
	private JButton				help;
	private MainFrame			mf;
	private RightBrainExtravert	rightBrainExtravert;
	private String				helpText;
	private JLabel				helpLabel;
	
        /**
         * Konstruktor
         */
	public RightBrainExtravertView(RightBrainExtravert rightBrainExtravert)
	{
		this.helpText = "<html><p>Na tym ekranie nale¿y wybraæ stopieñ nasilenia ka¿dej z podanych cech. 0 oznacza, ¿e dana</p>"
				+ "<p>cecha nie ujawnia siê u opisywanego konia a 3, ¿e jest bardzo silna.</p><br />"
				+ "<p>Aby przejœæ na kolejny ekran nale¿y klikn¹æ przycisk <b>„dalej”</b> znajduj¹cy siê w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />" + "<p>Aby cofn¹æ siê do poprzedniego ekranu w celu zmiany wprowadzonych danych nale¿y</p>"
				+ "<p>klikn¹æ przycisk „wstecz” znajduj¹cy siê z lewej strony przycisku <b>„dalej”</b>.</p><br />"
				+ "<p><b>Wyjaœnienie trudniejszych pojêæ:</p></b><br />"
				+ "<p><b>Mo¿e siê wspinaæ/brykaæ</b> - wspinaæ inaczej stawaæ \"dêba” </p>";
		
		id = 5;
		adjust(new Dimension(800, 600));
		
		this.rightBrainExtravert = rightBrainExtravert;
		
		interval = new JLabel("Prawo Pó³kulowy Ekstrawertyk");
		Dimension intervalSize = interval.getPreferredSize();
		interval.setBounds(800 / 2 - intervalSize.width / 2, 50, intervalSize.width, intervalSize.height);
		add(interval);
		
		int index = 0;
		int y = 70;
		characters = new CharacterChoice[rightBrainExtravert.getNumTypes()];
		for (Map.Entry<String, Integer> type : rightBrainExtravert.getEntrySet())
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
				Integer[] leftBrainExtravertStates = new Integer[RightBrainExtravertView.this.rightBrainExtravert.getNumTypes()];
				for (int i = 0; i < RightBrainExtravertView.this.rightBrainExtravert.getNumTypes(); i++)
				{
					for (int j = 0; j < RightBrainExtravertView.this.characters[i].gradNum; j++)
					{
						if (RightBrainExtravertView.this.characters[i].gradation[j].isSelected())
						{
							leftBrainExtravertStates[i] = new Integer(j);
						}
					}
				}
				RightBrainExtravertView.this.rightBrainExtravert.adjustState(leftBrainExtravertStates);
				RightBrainExtravertView.this.mf.getIntervalsCount()[2] = new Integer(RightBrainExtravertView.this.rightBrainExtravert.count());
				
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
				JOptionPane.showMessageDialog(RightBrainExtravertView.this, helpLabel, "Pomoc", JOptionPane.INFORMATION_MESSAGE);
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
		this.rightBrainExtravert.reset();
	}
	
	@Override
	public void update()
	{
		for (int i = 0; i < this.rightBrainExtravert.getNumTypes(); i++)
		{
			this.characters[i].gradation[0].setSelected(true);
		}
	}
}
