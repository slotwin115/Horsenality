package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import view.AbstractPanel;
import view.ConclusionView;

/**
 * Klasa reprezentuj¹ca g³ówne okno aplikacji.
 * 
 * <p>G³ownym zadaniem tej klasy jest zarz¹dzanie widokami.
 * Ka¿dy widok po wykonaniu pracy wysy³a adekwatn¹ informacjê do okna g³ównego, które nastêpnie wykonuje odpowiednie operacje w zale¿noœci od otrzymanej informacji.
 * </p>
 */
public class MainFrame
{
        /**
        * g³owne okno aplikacji
        */ 
	private JFrame						currentFrame;
        
        /**
        * wymiar g³ównego okna
        */
	private Dimension					screenSize;
        
        /**
        * aktualny widok
        */
	private AbstractPanel				currentPanel;
        
        /**
        * lista widoków
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
         * @param title Tytu³ okna aplikacji.
         * @param frameSize Wymiar okna aplikacji.
         * @param viewList Tablica widoków.
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
         * @return Liczba widoków w liœcie.
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
        * Zapisuje zmiany wprowadzone przez u¿ytkownika w modelach.
        */
	public void updateData()
	{
		for (int i = 0; i < viewList.size(); i++)
		{
			viewList.get(i).update();
		}
	}
	
        /**
         * Przywraca modele do stanu pocz¹tkowego.
         */
	public void resetData()
	{
		for (int i = 0; i < viewList.size(); i++)
		{
			viewList.get(i).reset();
		}
	}
	
        /**
         * <p>Na podstawie wprowadzonych danych przez u¿ytkownika, oblicza typ osobowoœci.</p>
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
         * Wynikiem wtedy bêdzie Lewopó³kulowy Ekstrawertyk.
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
			// œrednioa ogólna
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
         * W zale¿noœci od wyniku, ustawia opis uzyskanej osobowoœci.<br />
         * np: [true, true, false, false]<br />
         * Lewopó³kulowy ekstrawertyk oraz Lewopó³kulowy Introwertyk.
         * </p>
         * 
         * <p>
         * 0 -> left brain extravert<br />
	 * 1 -> left brain introvert<br />
	 * 2 -> right brain extravert<br />
	 * 3 -> right brain introvert
         * </p>
         * 
         * @param psychologicalTypes Tablica bool'owska bêd¹ca wynikiem metody computeResult.
         */
	public void setConclusion(Boolean[] psychologicalTypes)
	{
		ConclusionView cv = (ConclusionView) viewList.get(7);
		String result = "";
		String noResult = "<center><h1>Brak sprecyzowanej osobowoœci</h1></center>", str1 = "", str2 = "", str3 = "";
		String[] types = new String[4];
		
		str1 = "<center><h1>Lewopó³kulowy Ekstrawertyk</h1></center><p><b>Lewopó³kulowy</b> - koñ „oswojony”, czuj¹cy siê doœæ pewnie w towarzystwie ludzi</p><p><b>Ekstrawertyk</b> - koñ otwarty na œwiat z du¿¹ dawk¹ energii</p><br />";
		str2 = "<p><b>Zaleca siê:</b></p><ul><li>Aktywn¹ i interesuj¹c¹ (z perspektywy konia) pracê, mo¿na w tym celu wykorzystywaæ przeszkody, pi³ki, p³achty i inne zabawki</li><li>Czêsto chwaliæ (drapaæ, g³askaæ, mówiæ), jeœli wspó³praca sprawi tobie radoœæ, mo¿esz mieæ pewnoœæ ¿e koñ te¿ bêdzie zadowolony</li></ul><br />";
		str3 = "<p><b>Nie zaleca siê:</b></p><ul><li>Zbyt wielu powtórzeñ tego samego æwiczenia (konie z tym typem osobowoœci s¹ bystre i szybko siê ucz¹)</li><li>Karania konia, koñ nie zdaje sobie sprawy ¿e jego ciekawoœæ œwiata stwarza problemy, karania mo¿e doprowadziæ do zamkniêcia siê w sobie i agresji z jego strony, lepiej stosowaæ pozytywne wzmocnienie</li></ul><br />";
		types[0] = str1 + str2 + str3;
		
		str1 = "<center><h1>Lewopó³kulowy Introwertyk</h1></center><p><b>Lewopó³kulowy</b> - koñ „oswojony”, czuj¹cy siê doœæ pewnie w towarzystwie ludzi</p><p><b>Introwertyk</b> – koñ pozornie zamkniêty w sobie, nie wykazuje zbyt du¿ej aktywnoœci fizycznej, ale bardzo dobrze radzi sobie z myœleniem</p><br />";
		str2 = "<p><b>Zaleca siê:</b></p><ul><li>Proszenie o mniej ni¿ jest w stanie zrobiæ, to go zmotywuje do robienia wiêcej</li><li>Uczenie sztuczek wymagaj¹cych od konia trochê myœlenia</li></ul><br />";
		str3 = "<p><b>Nie zaleca siê:</b></p><ul><li>Zbyt wielu powtórzeñ jednego æwiczenia, jeœli koñ zrozumie æwiczenie, nale¿y zakoñczyæ pracê lub zmieniæ zadanie</li><li>Wymuszania pracy, lepiej zamieniæ przykry obowi¹zek w zabawê, a od razu koñ chêtnie wykona zadanie</li></ul><br />";
		types[1] = str1 + str2 + str3;
		
		str1 = "<center><h1>Prawopó³kulowy Ekstrawertyk</h1></center><p><b>Prawopó³kulowy</b> - koñ kieruj¹cy siê g³ównie instynktem przetrwania, niepewny, ³atwo wpada w paniczny strach</p><p><b>Ekstrawertyk</b> - koñ otwarty na œwiat z du¿¹ dawk¹ energii</p><br />";
		str2 = "<p><b>Zaleca siê:</b></p><ul><li>Krótkie i proste treningi</li><li>Wymaganie od konia energii niewiele wiêkszej ni¿ on ma, co pozwoli prze³amaæ jego lêk</li></ul><br />";
		str3 = "<p><b>Nie zaleca siê:</b></p><ul><li>Zbyt du¿o æwiczeñ w linii prostej, linia prosta pozwala na zbyt du¿e rozpêdzenie siê konia</li><li>Uczenia konia wiêcej ni¿ jednej rzeczy na raz</li></ul><br />";
		types[2] = str1 + str2 + str3;
		
		str1 = "<center><h1>Prawopó³kulowy Introwertyk</h1></center><p><b>Prawopó³kulowy</b> - koñ kieruj¹cy siê g³ównie instynktem przetrwania, niepewny, ³atwo wpada w paniczny strach</p><p><b>Introwertyk</b> -  koñ bardzo dobrze zapamiêtuj¹cy negatywne sytuacje które potem szybko kojarzy z nowymi, bardzo wra¿liwy</p><br />";
		str2 = "<p><b>Zaleca siê:</b></p><ul><li>Du¿¹ cierpliwoœæ w trakcie treningów, dawaæ koniowi czas na spokojne wykonanie i przemyœlenie zadania</li><li>Du¿o powtórzeñ jednego æwiczenia i przypominanie go na kolejnych treningach</li></ul><br />";
		str3 = "<p><b>Nie zaleca siê:</b></p><ul><li>Wymagania zbyt trudnych æwiczeñ, lepiej wprowadzaæ stopniowe, niewielkie modyfikacje prostego æwiczenia</li><li>Wymagania od konia wykonywania æwiczeñ podczas gdy jego wzrok nie jest skupiony na cz³owieku, brak kontaktu wzrokowego jest oznak¹ strachu</li></ul><br />";
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
