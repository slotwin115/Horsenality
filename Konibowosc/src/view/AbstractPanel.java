package view;

import java.awt.Dimension;
import javax.swing.JPanel;
import main.MainFrame;

/**
 * Klasa abstrakcyjna bêd¹ca ogóln¹ reprezentacj¹ widoku, na potrzeby implementacyjne.
 */
@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel
{
        /**
         * Nr. id widoku, na potrzeby dobrego porz¹dku widoków.
         */
	protected int id;
	
        /**
         * Ustawia wielkoœæ widoku na ca³e okno aplikacji.
         * 
         * @param panelSize Wymiar widoku.
         */
	protected void adjust(Dimension panelSize)
	{
		setBounds(0, 0, panelSize.width, panelSize.height);
	}
	
        /**
         * @return Zwraca id widoku.
         */
	public int getID()
	{
		return id;
	}
	
        /**
         * Daje informacje o g³ównym oknie aplikacji na potrzeby zarz¹dzania kolejnoœci¹ widoków.
         * 
         * @param mf G³ówne okno aplikacji.
         */
	public abstract void setMF(MainFrame mf);
	
        /**
         * Wywo³uje metody update dla adekwatnego modelu w celu jego aktualizacji do wartoœci podaych przez u¿ytkownika.
         */
	public abstract void update();
	
        /**
         * Wywo³uje metody reset dla adekwatnego modelu w celu jego aktualizacji do wartoœci pocz¹tkowych.
         */
	public abstract void reset();
	
	
}
