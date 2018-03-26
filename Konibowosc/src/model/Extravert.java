package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj¹ca typ psychologiczny Ekstrawertyk.
 */
public class Extravert extends AbstractType<Boolean>
{
        /**
         * Konstruktor
         */
	public Extravert()
	{
		types = new LinkedHashMap<>();
		types.put("nabuzowany", false);
		types.put("\"do przodu\"", false);
		types.put("szybki", false);
		types.put("skory do biegania", false);
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
