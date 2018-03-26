package main;

import java.awt.Dimension;
import java.util.ArrayList;
import model.Extravert;
import model.Introvert;
import model.LeftBrain;
import model.LeftBrainExtravert;
import model.LeftBrainIntrovert;
import model.RightBrain;
import model.RightBrainExtravert;
import model.RightBrainIntrovert;
import view.AbstractPanel;
import view.ConclusionView;
import view.ExtravertIntrovertView;
import view.LeftBrainExtravertView;
import view.LeftBrainIntrovertView;
import view.LeftRightBrainView;
import view.RightBrainExtravertView;
import view.RightBrainIntrovertView;
import view.WelcomeView;

/**
 * G³ówna klasa programu.
 */
public class Main
{
	
	public static void main(String[] args) throws InterruptedException
	{
                // inicjalizacja modeli
		Introvert introvert = new Introvert();
		LeftBrain leftBrain = new LeftBrain();
		Extravert extravert = new Extravert();
		RightBrain rightBrain = new RightBrain();
		LeftBrainExtravert leftBrainExtravert = new LeftBrainExtravert();
		LeftBrainIntrovert leftBrainIntrovert = new LeftBrainIntrovert();
		RightBrainExtravert rightBrainExtravert = new RightBrainExtravert();
		RightBrainIntrovert rightBrainIntrovert = new RightBrainIntrovert();
		
                //inicjalizacja widoków modeli
		ConclusionView conclusionView = new ConclusionView();
		RightBrainIntrovertView rightBrainIntrovertView = new RightBrainIntrovertView(rightBrainIntrovert);
		RightBrainExtravertView rightBrainExtravertView = new RightBrainExtravertView(rightBrainExtravert);
		LeftBrainIntrovertView leftBrainIntrovertView = new LeftBrainIntrovertView(leftBrainIntrovert);
		LeftBrainExtravertView leftBrainExtravertView = new LeftBrainExtravertView(leftBrainExtravert);
		LeftRightBrainView leftRightBrainView = new LeftRightBrainView(leftBrain, rightBrain);
		ExtravertIntrovertView extravertIntrovertView = new ExtravertIntrovertView(extravert, introvert);
		WelcomeView welcomeView = new WelcomeView();
		
                //inicjalizacja tablicy widoków
		ArrayList<AbstractPanel> viewList = new ArrayList<>();
		viewList.add(welcomeView);
		viewList.add(extravertIntrovertView);
		viewList.add(leftRightBrainView);
		viewList.add(leftBrainExtravertView);
		viewList.add(leftBrainIntrovertView);
		viewList.add(rightBrainExtravertView);
		viewList.add(rightBrainIntrovertView);
		viewList.add(conclusionView);
		
                //przes³anie informacji ka¿demu z widoków o oknie g³ównym(zarz¹dzaj¹cym)
		MainFrame mf = new MainFrame("Koniobowoœæ", new Dimension(800, 600), viewList);
		for(int i = 0; i < viewList.size(); i++)
		{
			viewList.get(i).setMF(mf);
		}
		mf.setPanel(0);
		mf.makeVisible();
	}
}
