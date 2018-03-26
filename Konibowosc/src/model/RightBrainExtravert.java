package model;

import java.util.LinkedHashMap;

/**
 * Klasa reprezentuj¹ca obszar Prawopó³kulowy Ekstrawertyk.
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
		types.put("tratuj¹cy ciê", 0);
		types.put("nosi wysoko g³owê", 0);
		types.put("krn¹brny/panikuj¹cy", 0);
		types.put("nie mo¿e ustaæ w miejscu", 0);
		types.put("mo¿e siê wspinaæ/brykaæ", 0);
		types.put("zapieraj¹cy siê", 0);
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
