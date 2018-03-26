package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj�ca obszar Prawop�kulowy Introwertyk.
 */
public class RightBrainIntrovert extends AbstractType<Integer>
{
        /**
         * Konstruktor
         */    
	public RightBrainIntrovert()
	{
		types = new LinkedHashMap<>();
		types.put("\"katatoniczny\"", 0);
		types.put("nie mo�e my�le�", 0);
		types.put("nieprzewidywalny", 0);
		types.put("zastyga a potem wybucha", 0);
		types.put("mo�e kopa� ze strachu", 0);
		types.put("p�ochliwy/boja�liwy", 0);
		types.put("cichy/pos�uszny", 0);
		types.put("nieufny", 0);
		types.put("niepewny", 0);
		types.put("spi�ty", 0);
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
