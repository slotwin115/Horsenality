package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca obszar Lewop�kulowy Ekstrawertyk.
 */
public class LeftBrainExtravert extends AbstractType<Integer>
{
         /**
         * Konstruktor
         */
	public LeftBrainExtravert()
	{
		types = new LinkedHashMap<>();
		types.put("bystry", 0);
		types.put("psotny", 0);
		types.put("\"pyskaty\"", 0);
		types.put("zabawowy", 0);
		types.put("charyzmatyczny", 0);
		types.put("ma w�asne pomys�y", 0);
		types.put("mo�e gry��/podszczypywa�", 0);
		types.put("niepos�uszny", 0);
		types.put("�ywio�owy", 0);
		types.put("przyjazny", 0);
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
