package model;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Klasa abstrakcyjna b�d�ca og�ln� reprezentacj� typu, na potrzeby implementacyjne.
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
         * @return Liczba cech sk�adaj�ca si� na typ lub obszar.
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
         * Zmienia warto�ci cech na te kt�re podaj u�ytkownik.
         * 
         * @param states Tablica warto�ci podaych przez u�ytkownika.
         */
	public abstract void adjustState(T[] states);
        
        /**
         * Przywraca warto�ci cech do stanu pocz�tkowego.<br />
         * np: rozpocz�cie programu od nowa w trakcie jednego wykonania.
         */
	public abstract void reset();
        
        /**
         * Zlicza cechy typu psychologicznego lub obszaru.
         * 
         * @return Zliczone cechy typu psychologicznego lub obszaru.
         */
	public abstract int count();
}
