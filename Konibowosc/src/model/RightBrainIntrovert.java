package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj¹ca obszar Prawopó³kulowy Introwertyk.
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
		types.put("nie mo¿e myœleæ", 0);
		types.put("nieprzewidywalny", 0);
		types.put("zastyga a potem wybucha", 0);
		types.put("mo¿e kopaæ ze strachu", 0);
		types.put("p³ochliwy/bojaŸliwy", 0);
		types.put("cichy/pos³uszny", 0);
		types.put("nieufny", 0);
		types.put("niepewny", 0);
		types.put("spiêty", 0);
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
