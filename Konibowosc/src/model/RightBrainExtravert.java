package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca obszar Prawop�kulowy Ekstrawertyk.
 */
public class RightBrainExtravert extends AbstractType<Integer>
{
        /**
         * Konstruktor
         */    
	public RightBrainExtravert()
	{
		types = new LinkedHashMap<>();
		types.put("impulsywny", 0);
		types.put("hiperczujny", 0);
		types.put("tratuj�cy ci�", 0);
		types.put("nosi wysoko g�ow�", 0);
		types.put("krn�brny/panikuj�cy", 0);
		types.put("nie mo�e usta� w miejscu", 0);
		types.put("mo�e si� wspina�/bryka�", 0);
		types.put("zapieraj�cy si�", 0);
		types.put("nadpobudliwy", 0);
		types.put("biego-holik", 0);
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
