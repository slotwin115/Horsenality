package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca typ psychologiczny Introwertyk.
 */
public class Introvert extends AbstractType<Boolean>
{        
        /**
         * Konstruktor
         */
	public Introvert()
	{
		types = new LinkedHashMap<>();
		types.put("ospa�y", false);
		types.put("\"do pchania\"", false);
		types.put("powolny", false);
		types.put("ch�tny do zatrzyma�", false);
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
