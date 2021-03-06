package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentująca typ psychologiczny Lewopółkulowy.
 */
public class LeftBrain extends AbstractType<Boolean>
{
         /**
         * Konstruktor
         */
	public LeftBrain()
	{
		types = new LinkedHashMap<>();
		types.put("dominujący", false);
		types.put("pchający", false);
		types.put("tolerancyjny", false);
		types.put("niezangaażowany", false);
		types.put("pewny siebe", false);
		types.put("ciekawski", false);
	}

	@Override
	public void adjustState(Boolean[] states)
	{
		int i = 0;
		for(String key : types.keySet())
		{
			types.put(key, states[i++]);
		}
	}
	
	@Override
	public void reset()
	{
		for(String key : types.keySet())
		{
			types.put(key, false);
		}		
	}
	
	@Override
	public int count()
	{
		int result = 0;
		
		for(String key : types.keySet())
		{
			if(types.get(key))
			{
				result++;
			}
		}
		
		return result;
	}
}
