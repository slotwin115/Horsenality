package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca typ psychologiczny Prawop�kulowy.
 */
public class RightBrain extends AbstractType<Boolean>
{
        /**
         * Konstruktor
         */    
	public RightBrain()
	{
		types = new LinkedHashMap<>();
		types.put("satrchliwy/nerwowy", false);
		types.put("defensywny", false);
		types.put("czu�y", false);
		types.put("emocjonalny", false);
		types.put("niepewny siebie", false);
		types.put("p�ochliwy", false);
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
