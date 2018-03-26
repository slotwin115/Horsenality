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
import model.LeftBrain;
import model.RightBrain;

/**
 * Klasa reprezentuj¹ca widok modelu Lewo i Prawo pó³kulowego.
 */
@SuppressWarnings("serial")
public class LeftRightBrainView extends AbstractPanel
{
	private JLabel		instructions;
	private JLabel		leftBrainLabel;
	private JLabel		rightBrainLabel;
	private JCheckBox[]	leftBrainBox;
	private JCheckBox[]	rightBrainBox;
	private JButton		next;
	private JButton		back;
	private JButton		help;
	private MainFrame	mf;
	private LeftBrain	leftBrain;
	private RightBrain	rightBrain;
	private String		helpText;
	private JLabel		helpLabel;
	
        /**
         * Konstruktor
         */
	public LeftRightBrainView(LeftBrain leftBrain, RightBrain rightBrain)
	{
		this.helpText = "<html><p>Na tym ekranie nale¿y zaznaczyæ cechy, (klikaj¹c na odpowiednie miejsce przy nazwie cechy)</p>"
				+ "<p>które posiada twój koñ.</p><br />"
				+ "<p>Aby przejœæ na kolejny ekran nale¿y klikn¹æ przycisk <b>„dalej”</b> znajduj¹cy siê w prawym dolnym</p>"
				+ "<p>roku ekranu.</p><br />"
				+ "<p>Aby cofn¹æ siê do poprzedniego ekranu w celu zmiany wprowadzonych danych nale¿y</p>"
				+ "<p>klikn¹æ przycisk „wstecz” znajduj¹cy siê z lewej strony przycisku <b>„dalej”</b>.</p><br />"
				+ "<p><b>Wyjaœnienie trudniejszych pojêæ:</p></b><br />"
				+ "<p><b>Pchaj¹cy</b> - nie szanuj¹cy przestrzeni osobistej cz³owieka</p>"
				+ "<p><b>Defensywny</b>  - nadmiernie broni siê przed atakiem</p>";
		
		id = 2;
		adjust(new Dimension(800, 600));
		
		this.leftBrain = leftBrain;
		this.rightBrain = rightBrain;
		
		instructions = new JLabel("Zaznacz cechy");
		Dimension instructionsSize = instructions.getPreferredSize();
		instructions.setBounds(800 / 2 - instructionsSize.width / 2, 50, instructionsSize.width, instructionsSize.height);
		add(instructions);
		
		leftBrainLabel = new JLabel("Lewo Pó³kulowy");
		Dimension leftBrainLabelSize = leftBrainLabel.getPreferredSize();
		leftBrainLabel.setBounds(100, 125, leftBrainLabelSize.width, leftBrainLabelSize.height);
		add(leftBrainLabel);
		
		rightBrainLabel = new JLabel("Prawo Pó³kulowy");
		Dimension rightBrainLabelSize = rightBrainLabel.getPreferredSize();
		rightBrainLabel.setBounds(500, 125, rightBrainLabelSize.width, rightBrainLabelSize.height);
		add(rightBrainLabel);
		
		int index = 0;
		int y = 150;
		leftBrainBox = new JCheckBox[leftBrain.getNumTypes()];
		for (Map.Entry<String, Boolean> type : leftBrain.getEntrySet())
		{
			leftBrainBox[index] = new JCheckBox(type.getKey());
			leftBrainBox[index].setSelected(type.getValue());
			leftBrainBox[index].setBounds(100, y, 200, 50);
			add(leftBrainBox[index]);
			index++;
			y += 50;
		}
		
		index = 0;
		y = 150;
		rightBrainBox = new JCheckBox[rightBrain.getNumTypes()];
		for (Map.Entry<String, Boolean> type : rightBrain.getEntrySet())
		{
			rightBrainBox[index] = new JCheckBox(type.getKey());
			rightBrainBox[index].setSelected(type.getValue());
			rightBrainBox[index].setBounds(500, y, 200, 50);
			add(rightBrainBox[index]);
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
				Boolean[] leftBrainStates = new Boolean[LeftRightBrainView.this.leftBrain.getNumTypes()];
				for(int i = 0; i < LeftRightBrainView.this.leftBrain.getNumTypes(); i++)
				{
					leftBrainStates[i] = new Boolean(leftBrainBox[i].isSelected());
				}
				
				LeftRightBrainView.this.leftBrain.adjustState(leftBrainStates);
				LeftRightBrainView.this.mf.getTypesCount()[2] = new Integer(LeftRightBrainView.this.leftBrain.count());
				
				Boolean[] rightBrainStates = new Boolean[LeftRightBrainView.this.rightBrain.getNumTypes()];
				for(int i = 0; i < LeftRightBrainView.this.rightBrain.getNumTypes(); i++)
				{
					rightBrainStates[i] = new Boolean(rightBrainBox[i].isSelected());
				}
				
				LeftRightBrainView.this.rightBrain.adjustState(rightBrainStates);
				LeftRightBrainView.this.mf.getTypesCount()[3] = new Integer(LeftRightBrainView.this.rightBrain.count());
				
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
				JOptionPane.showMessageDialog(LeftRightBrainView.this, helpLabel, "Pomoc", JOptionPane.INFORMATION_MESSAGE);
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
		this.leftBrain.reset();
		this.rightBrain.reset();
	}
	
	@Override
	public void update()
	{
		int index = 0;
		for (Map.Entry<String, Boolean> type : leftBrain.getEntrySet())
		{
			leftBrainBox[index].setSelected(type.getValue());
			index++;
		}
		
		index = 0;
		for (Map.Entry<String, Boolean> type : rightBrain.getEntrySet())
		{
			rightBrainBox[index].setSelected(type.getValue());
			index++;
		}
	}
}
