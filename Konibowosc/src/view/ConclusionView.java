package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import main.MainFrame;

/**
 * Klasa reprezentuj¹ca ostatni widok, prezentuj¹cy uzyskan¹ osobowoœc.
 */
@SuppressWarnings("serial")
public class ConclusionView extends AbstractPanel
{
	private JLabel		characterType;
	private JLabel		result;
	private JButton		close;
	private JButton		tryAgain;
	private JEditorPane	conclusions;
	private JScrollPane scrollPane;
	private MainFrame	mf;
	
        /**
         * Konstruktor
         */
	public ConclusionView()
	{
		id = 7;
		adjust(new Dimension(800, 600));
		
		result = new JLabel("Wynik");
		Dimension resultSize = result.getPreferredSize();
		result.setBounds(800 / 2 - resultSize.width / 2, 50, resultSize.width, resultSize.height);
		add(result);
		
		characterType = new JLabel("Typ osobowoœci");
		Dimension characterTypeSize = characterType.getPreferredSize();
		characterType.setBounds(800 / 2 - characterTypeSize.width / 2, 100, characterTypeSize.width, characterTypeSize.height);
		add(characterType);
		
		conclusions = new JEditorPane("text/html", "");
		conclusions.setBounds(40, 120, 700, 380);
		conclusions.setEditable(false);
		conclusions.setBackground(Color.white);
		scrollPane = new JScrollPane(conclusions);
		scrollPane.setBounds(40, 120, 700, 380);
		add(scrollPane);
		
		tryAgain = new JButton("start");
		tryAgain.setBounds(290, 520, 100, 30);
		tryAgain.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mf.resetData();
				mf.updateData();
				mf.setPanel(0);
			}
		});
		add(tryAgain);
		
		close = new JButton("zakoñcz");
		close.setBounds(410, 520, 100, 30);
		close.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mf.dispose();
			}
		});
		add(close);
		
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
	
        /**
         * Ustawia opis uzyskanej osobowoœci do wyœwietlenia po uzykaniu danych od u¿ytkownika.
         * 
         * @param str Opis uzyskanej osobowoœci.
         */
	public void setConclusion(String str)
	{
		conclusions.setText(str);
	}
}
