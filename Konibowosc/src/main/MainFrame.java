package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import view.AbstractPanel;
import view.ConclusionView;

/**
 * Klasa reprezentuj�ca g��wne okno aplikacji.
 * 
 * <p>G�ownym zadaniem tej klasy jest zarz�dzanie widokami.
 * Ka�dy widok po wykonaniu pracy wysy�a adekwatn� informacj� do okna g��wnego, kt�re nast�pnie wykonuje odpowiednie operacje w zale�no�ci od otrzymanej informacji.
 * </p>
 */
public class MainFrame
{
        /**
        * g�owne okno aplikacji
        */ 
	private JFrame						currentFrame;
        
        /**
        * wymiar g��wnego okna
        */
	private Dimension					screenSize;
        
        /**
        * aktualny widok
        */
	private AbstractPanel				currentPanel;
        
        /**
        * lista widok�w
        */
	private ArrayList<AbstractPanel>	viewList;
        
        /**
        * zliczone typy psychologiczne
        */
	private Integer[]					typesCount;
        
        /**
        * zliczone obszary
        */
	private Integer[]					intervalsCount;
	
        /**
         * Konstruktor.
         * 
         * @param title Tytu� okna aplikacji.
         * @param frameSize Wymiar okna aplikacji.
         * @param viewList Tablica widok�w.
         */
	public MainFrame(String title, Dimension frameSize, ArrayList<AbstractPanel> viewList)
	{
		currentFrame = new JFrame(title);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		currentFrame.setBounds(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2, frameSize.width,
				frameSize.height);
		
		this.viewList = viewList;
		this.typesCount = new Integer[4];
		this.intervalsCount = new Integer[4];
		
		currentFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                currentFrame.setResizable(false);
	}
	
        /**
         * Ustawia aktualny panel.
         * 
         * @param index Indeks panelu do ustawienia jako aktualny.
         */
	public void setPanel(int index)
	{
		if (index < viewList.size())
		{
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().repaint();
			currentFrame.getContentPane().revalidate();
			currentPanel = viewList.get(index);
			currentFrame.add(currentPanel);
			currentFrame.getContentPane().repaint();
			currentFrame.getContentPane().revalidate();
		}
	}
	
	public Integer[] getTypesCount()
	{
		return typesCount;
	}
	
	public Integer[] getIntervalsCount()
	{
		return intervalsCount;
	}
	
	public int getCurrentID()
	{
		return currentPanel.getID();
	}
	
        /**
         * Ustawia okno jako widoczne.
         */
	public void makeVisible()
	{
		currentFrame.setVisible(true);
	}
	
        /**
         * @return Liczba widok�w w li�cie.
         */
	public int getNumPanels()
	{
		return viewList.size();
	}
	
        /**
         * Niszczy okno aplikacji.
         */
	public void dispose()
	{
		currentFrame.dispose();
	}
	
        /*
        * Zapisuje zmiany wprowadzone przez u�ytkownika w modelach.
        */
	public void updateData()
	{
		for (int i = 0; i < viewList.size(); i++)
		{
			viewList.get(i).update();
		}
	}
	
        /**
         * Przywraca modele do stanu pocz�tkowego.
         */
	public void resetData()
	{
		for (int i = 0; i < viewList.size(); i++)
		{
			viewList.get(i).reset();
		}
	}
	
        /**
         * <p>Na podstawie wprowadzonych danych przez u�ytkownika, oblicza typ osobowo�ci.</p>
         * 
         * <p>
         * 0 -> left brain extravert<br />
	 * 1 -> left brain introvert<br />
	 * 2 -> right brain extravert<br />
	 * 3 -> right brain introvert
         * </p>
         * 
         * @return Rezultat w formie tablicy bool'owskiej.
         * np: [true, false, false, false]<br />
         * Wynikiem wtedy b�dzie Lewop�kulowy Ekstrawertyk.
         */
	public Boolean[] computeResult()
	{
		// most propable types
		///////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * result:
		 * [0] -> extravert
		 * [1] -> introvert
		 * [2] -> left brain
		 * [3] -> right brain
		 */
		Boolean[] result = new Boolean[4];
		for (int i = 0; i < typesCount.length; i++)
		{
			result[i] = false;
		}
		
		/*
		 * conclusion:
		 * [0] -> extravert vs introvert
		 * [1] -> left brain vs right brain
		 */
		int conclusion[] = new int[2];
		for (int i = 0; i < typesCount.length; i += 2)
		{
			if (typesCount[i] > typesCount[i + 1])
			{
				conclusion[i / 2] = 1;
			}
			else if (typesCount[i] < typesCount[i + 1])
			{
				conclusion[i / 2] = -1;
			}
			else
			{
				conclusion[i / 2] = 0;
			}
		}
		
		int mostPropableType[] = new int[0];
		
		/*
		 * mostPropableType:
		 * 0 -> left brain extravert
		 * 1 -> left brain introvert
		 * 2 -> right brain extravert
		 * 3 -> right brain introvert
		 */
		if (conclusion[0] != 0 && conclusion[1] != 0)
		{
			if (conclusion[0] == 1 && conclusion[1] == 1)
			{
				mostPropableType = new int[1];
				mostPropableType[0] = 0;
			}
			else if (conclusion[0] == -1 && conclusion[1] == 1)
			{
				mostPropableType = new int[1];
				mostPropableType[0] = 1;
			}
			else if (conclusion[0] == 1 && conclusion[1] == -1)
			{
				mostPropableType = new int[1];
				mostPropableType[0] = 2;
			}
			else if (conclusion[0] == -1 && conclusion[1] == -1)
			{
				mostPropableType = new int[1];
				mostPropableType[0] = 3;
			}
		}
		else if (conclusion[0] != 0 || conclusion[1] != 0)
		{
			if (conclusion[0] == 1)
			{
				mostPropableType = new int[2];
				mostPropableType[0] = 0;
				mostPropableType[1] = 2;
			}
			else if (conclusion[0] == -1)
			{
				mostPropableType = new int[2];
				mostPropableType[0] = 1;
				mostPropableType[1] = 3;
			}
			else if (conclusion[1] == 1)
			{
				mostPropableType = new int[2];
				mostPropableType[0] = 0;
				mostPropableType[1] = 1;
			}
			else
			{
				mostPropableType = new int[2];
				mostPropableType[0] = 2;
				mostPropableType[1] = 3;
			}
		}
		else
		{
			mostPropableType = new int[4];
			for (int i = 0; i < mostPropableType.length; i++)
			{
				mostPropableType[i] = i;
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		// max intervals
		///////////////////////////////////////////////////////////////////////////////////////////////////
		int maxInterval[] = new int[4];
		for (int i = 0; i < maxInterval.length; i++)
		{
			maxInterval[i] = -1;
		}
		
		int tmp = -1;
		for (int i = 0; i < intervalsCount.length; i++)
		{
			if (tmp < intervalsCount[i])
			{
				tmp = intervalsCount[i];
			}
		}
		
		for (int i = 0; i < intervalsCount.length; i++)
		{
			if (intervalsCount[i] == tmp)
			{
				maxInterval[i] = i;
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////
		int count = 0, index = -1;
		for (int i = 0; i < maxInterval.length; i++)
		{
			for (int j = 0; j < mostPropableType.length; j++)
			{
				if (maxInterval[i] == mostPropableType[j])
				{
					count++;
					index = i;
				}
			}
		}
		
		if (count == 1)
		{
			result[index] = true;
		}
		else
		{
			// �rednioa og�lna
			int generalAverage[] = new int[4];
			for (int i = 0; i < intervalsCount.length; i++)
			{
				if (i == 0)
				{
					generalAverage[i] = intervalsCount[i] + typesCount[0] * 3 + typesCount[2] * 3;
				}
				else if (i == 1)
				{
					generalAverage[i] = intervalsCount[i] + typesCount[1] * 3 + typesCount[2] * 3;
				}
				else if (i == 2)
				{
					generalAverage[i] = intervalsCount[i] + typesCount[0] * 3 + typesCount[3] * 3;
				}
				else
				{
					generalAverage[i] = intervalsCount[i] + typesCount[1] * 3 + typesCount[3] * 3;
				}
			}
			
			int maxGeneralAverage = -1;
			for (int i = 0; i < generalAverage.length; i++)
			{
				if (maxGeneralAverage < generalAverage[i])
				{
					maxGeneralAverage = generalAverage[i];
				}
			}
			
			count = 0;
			for (int i : generalAverage)
			{
				if (i == maxGeneralAverage)
				{
					count++;
				}
			}
			
			int maxGeneralAverges[] = new int[count];
			int j = 0;
			for (int i = 0; i < generalAverage.length; i++)
			{
				if (maxGeneralAverage == generalAverage[i])
				{
					maxGeneralAverges[j] = i;
					j++;
				}
			}
			
			if (count != 4)
			{
				for (int i : maxGeneralAverges)
				{
					result[i] = true;
				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		 * mostPropableType:
		 * 0 -> left brain extravert
		 * 1 -> left brain introvert
		 * 2 -> right brain extravert
		 * 3 -> right brain introvert
		 */
		
		return result;
	}
	
        /**
         * <p>
         * W zale�no�ci od wyniku, ustawia opis uzyskanej osobowo�ci.<br />
         * np: [true, true, false, false]<br />
         * Lewop�kulowy ekstrawertyk oraz Lewop�kulowy Introwertyk.
         * </p>
         * 
         * <p>
         * 0 -> left brain extravert<br />
	 * 1 -> left brain introvert<br />
	 * 2 -> right brain extravert<br />
	 * 3 -> right brain introvert
         * </p>
         * 
         * @param psychologicalTypes Tablica bool'owska b�d�ca wynikiem metody computeResult.
         */
	public void setConclusion(Boolean[] psychologicalTypes)
	{
		ConclusionView cv = (ConclusionView) viewList.get(7);
		String result = "";
		String noResult = "<center><h1>Brak sprecyzowanej osobowo�ci</h1></center>", str1 = "", str2 = "", str3 = "";
		String[] types = new String[4];
		
		str1 = "<center><h1>Lewop�kulowy Ekstrawertyk</h1></center><p><b>Lewop�kulowy</b> - ko� �oswojony�, czuj�cy si� do�� pewnie w towarzystwie ludzi</p><p><b>Ekstrawertyk</b> - ko� otwarty na �wiat z du�� dawk� energii</p><br />";
		str2 = "<p><b>Zaleca si�:</b></p><ul><li>Aktywn� i interesuj�c� (z perspektywy konia) prac�, mo�na w tym celu wykorzystywa� przeszkody, pi�ki, p�achty i inne zabawki</li><li>Cz�sto chwali� (drapa�, g�aska�, m�wi�), je�li wsp�praca sprawi tobie rado��, mo�esz mie� pewno�� �e ko� te� b�dzie zadowolony</li></ul><br />";
		str3 = "<p><b>Nie zaleca si�:</b></p><ul><li>Zbyt wielu powt�rze� tego samego �wiczenia (konie z tym typem osobowo�ci s� bystre i szybko si� ucz�)</li><li>Karania konia, ko� nie zdaje sobie sprawy �e jego ciekawo�� �wiata stwarza problemy, karania mo�e doprowadzi� do zamkni�cia si� w sobie i agresji z jego strony, lepiej stosowa� pozytywne wzmocnienie</li></ul><br />";
		types[0] = str1 + str2 + str3;
		
		str1 = "<center><h1>Lewop�kulowy Introwertyk</h1></center><p><b>Lewop�kulowy</b> - ko� �oswojony�, czuj�cy si� do�� pewnie w towarzystwie ludzi</p><p><b>Introwertyk</b> � ko� pozornie zamkni�ty w sobie, nie wykazuje zbyt du�ej aktywno�ci fizycznej, ale bardzo dobrze radzi sobie z my�leniem</p><br />";
		str2 = "<p><b>Zaleca si�:</b></p><ul><li>Proszenie o mniej ni� jest w stanie zrobi�, to go zmotywuje do robienia wi�cej</li><li>Uczenie sztuczek wymagaj�cych od konia troch� my�lenia</li></ul><br />";
		str3 = "<p><b>Nie zaleca si�:</b></p><ul><li>Zbyt wielu powt�rze� jednego �wiczenia, je�li ko� zrozumie �wiczenie, nale�y zako�czy� prac� lub zmieni� zadanie</li><li>Wymuszania pracy, lepiej zamieni� przykry obowi�zek w zabaw�, a od razu ko� ch�tnie wykona zadanie</li></ul><br />";
		types[1] = str1 + str2 + str3;
		
		str1 = "<center><h1>Prawop�kulowy Ekstrawertyk</h1></center><p><b>Prawop�kulowy</b> - ko� kieruj�cy si� g��wnie instynktem przetrwania, niepewny, �atwo wpada w paniczny strach</p><p><b>Ekstrawertyk</b> - ko� otwarty na �wiat z du�� dawk� energii</p><br />";
		str2 = "<p><b>Zaleca si�:</b></p><ul><li>Kr�tkie i proste treningi</li><li>Wymaganie od konia energii niewiele wi�kszej ni� on ma, co pozwoli prze�ama� jego l�k</li></ul><br />";
		str3 = "<p><b>Nie zaleca si�:</b></p><ul><li>Zbyt du�o �wicze� w linii prostej, linia prosta pozwala na zbyt du�e rozp�dzenie si� konia</li><li>Uczenia konia wi�cej ni� jednej rzeczy na raz</li></ul><br />";
		types[2] = str1 + str2 + str3;
		
		str1 = "<center><h1>Prawop�kulowy Introwertyk</h1></center><p><b>Prawop�kulowy</b> - ko� kieruj�cy si� g��wnie instynktem przetrwania, niepewny, �atwo wpada w paniczny strach</p><p><b>Introwertyk</b> -  ko� bardzo dobrze zapami�tuj�cy negatywne sytuacje kt�re potem szybko kojarzy z nowymi, bardzo wra�liwy</p><br />";
		str2 = "<p><b>Zaleca si�:</b></p><ul><li>Du�� cierpliwo�� w trakcie trening�w, dawa� koniowi czas na spokojne wykonanie i przemy�lenie zadania</li><li>Du�o powt�rze� jednego �wiczenia i przypominanie go na kolejnych treningach</li></ul><br />";
		str3 = "<p><b>Nie zaleca si�:</b></p><ul><li>Wymagania zbyt trudnych �wicze�, lepiej wprowadza� stopniowe, niewielkie modyfikacje prostego �wiczenia</li><li>Wymagania od konia wykonywania �wicze� podczas gdy jego wzrok nie jest skupiony na cz�owieku, brak kontaktu wzrokowego jest oznak� strachu</li></ul><br />";
		types[3] = str1 + str2 + str3;
		
		for (int i = 0; i < types.length; i++)
		{
			if (psychologicalTypes[i])
			{
				result += types[i];
			}
		}
		
		if (result.isEmpty())
		{
			result = noResult;
		}
		
		cv.setConclusion(result);
	}
	
}
