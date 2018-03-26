package model;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Klasa abstrakcyjna bêd¹ca ogóln¹ reprezentacj¹ typu, na potrzeby implementacyjne.
 * 
 * @param <T> Boolean (Typ psychologiczny), Integer (Obszar)
 */
public abstract class AbstractType<T>
{
        /**
         * Mapa cech, typu psychologicznego lub obszaru.<br /><br />
         * Klucz -> String<br />
         * (dla typu psychologicznego)<br />
         * Value -> Boolean<br />
         * (dla obszaru)<br />
         * Value -> Integer<br />
         */
	protected LinkedHashMap<String, T> types;
	
        /**
         * @return Liczba cech sk³adaj¹ca siê na typ lub obszar.
         */
	public int getNumTypes()
	{
		return types.size();
	}
	
        /**
         * @return EntrySet Mapy cech typu psychologicznego lub obszaru.
         */
	public Set<Entry<String, T>> getEntrySet()
	{
		return types.entrySet();
	}
	
        /**
         * @return Mapa cech.
         */
	public LinkedHashMap<String, T> getTypes()
	{
		return types;
	}
	
        /**
         * Zmienia wartoœci cech na te które podaj u¿ytkownik.
         * 
         * @param states Tablica wartoœci podaych przez u¿ytkownika.
         */
	public abstract void adjustState(T[] states);
        
        /**
         * Przywraca wartoœci cech do stanu pocz¹tkowego.<br />
         * np: rozpoczêcie programu od nowa w trakcie jednego wykonania.
         */
	public abstract void reset();
        
        /**
         * Zlicza cechy typu psychologicznego lub obszaru.
         * 
         * @return Zliczone cechy typu psychologicznego lub obszaru.
         */
	public abstract int count();
}
