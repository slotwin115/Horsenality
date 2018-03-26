package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca obszar Lewop�kulowy Introwertyk.
 */
public class LeftBrainIntrovert extends AbstractType<Integer>
{
         /**
         * Konstruktor
         */
	public LeftBrainIntrovert()
	{
		types = new LinkedHashMap<>();
		types.put("sprytny", 0);
		types.put("niereaguj�cy", 0);
		types.put("mo�e wierzga�/atakowa�", 0);
		types.put("skory do dyskusji/prowokuj�cy", 0);
		types.put("niezmotywowany/ospa�y", 0);
		types.put("niezainteresowany", 0);
		types.put("szybko si� nudzi", 0);
		types.put("leniwy", 0);
		types.put("uparty", 0);
		types.put("�akomy", 0);
	}

	@Override
	public void adjustState(Integer[] states)
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
			types.put(key, 0);
		}		
	}
	
	@Override
	public int count()
	{
		int result = 0;
		
		for(String key : types.keySet())
		{
			result += types.get(key);
		}
		
		return result;
	}
}
