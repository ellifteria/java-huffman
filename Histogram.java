import java.util.*;
import java.io.*;

public class Histogram
{
	private int[] count;
	private double[] freq;

	// default constructor for histogram
	public Histogram()
	{
		count = new int[26];
		freq = new double[26];
	}


	// increments the count of character sent to it by one
	public void add(char c)
	{
		if(c >= 65 && c <= 90)
		{
			count[c-65]++;
		}
		if(c >= 97 && c <= 122)
		{
			count[c-97]++;
		}
	}

	public int[] getCountArray()
	{
		return count;
	}

	// returns the count of a specific character
	public int getCount(char c)
	{
		if(c >= 65 && c <= 90)
		{
			return count[c-65];
		}
		if(c >= 97 && c <= 122)
		{
			return count[c-97];
		}
		return 0;
	}

	// calculates the frequency of each character
	public void calculateFrequency()
	{
		int total = 0;
		for(int i : count)
		{
			total += i;
		}
		for(int i = 0; i< freq.length;i++)
		{
			freq[i] = (double)count[i]/total;
		}
	}

	// prints (SOPL) each character and their frequency;
	public void printFrequency()
	{
		char a = 'A';
		for(int i = 0; i< freq.length;i++)
		{
			System.out.println(a + ": " + freq[i]);
			a++;
		}
	}

	// prints out the histogram in frequency order.
	public void inOrder()
	{
		Map<Double,Character> m = new TreeMap<Double,Character>();
		for(int i=0;i<count.length;i++)
		{
			m.put(freq[i],(char)(i+65));
		}
		TreeSet<Double> keys = new TreeSet<Double>();
		for(double d:m.keySet())
		{
			keys.add(d);
		}
		while(keys.size()>0)
		{
			Double k = keys.last();
			keys.remove(k);
			System.out.printf("%.2f - %c\n",k,m.get(k));
		}


	}

	// returns a string that has each character and its count
	public String toString()
	{
		char a = 65;
		String[] b = new String[26];
		for(int i = 0; i< freq.length;i++)
		{
			b[i] = (a + ": " + count[i]);
			a++;
		}
		String c = "";
		for(String r: b)
		{
			c += r + "\n";

		}
		return c;
	}
}
