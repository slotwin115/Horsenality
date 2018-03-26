package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj¹ca typ psychologiczny Lewopó³kulowy.
 */
public class LeftBrain extends AbstractType<Boolean>
{
         /**
         * Konstruktor
         */
	public LeftBrain()
	{
		types = new LinkedHashMap<>();
		types.put("dominuj¹cy", false);
		types.put("pchaj¹cy", false);
		types.put("tolerancyjny", false);
		types.put("niezangaa¿owany", false);
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
