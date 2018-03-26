package view;

import java.awt.Dimension;
import javax.swing.JPanel;
import main.MainFrame;

/**
 * Klasa abstrakcyjna b�d�ca og�ln� reprezentacj� widoku, na potrzeby implementacyjne.
 */
@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel
{
        /**
         * Nr. id widoku, na potrzeby dobrego porz�dku widok�w.
         */
	protected int id;
	
        /**
         * Ustawia wielko�� widoku na ca�e okno aplikacji.
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
         * Daje informacje o g��wnym oknie aplikacji na potrzeby zarz�dzania kolejno�ci� widok�w.
         * 
         * @param mf G��wne okno aplikacji.
         */
	public abstract void setMF(MainFrame mf);
	
        /**
         * Wywo�uje metody update dla adekwatnego modelu w celu jego aktualizacji do warto�ci podaych przez u�ytkownika.
         */
	public abstract void update();
	
        /**
         * Wywo�uje metody reset dla adekwatnego modelu w celu jego aktualizacji do warto�ci pocz�tkowych.
         */
	public abstract void reset();
	
	
}
