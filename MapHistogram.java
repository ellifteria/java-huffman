import java.util.Map;
import java.util.TreeMap;

public class MapHistogram
{
	private Map<Character, Integer> histogram;

	// default constructor for histogram
	public MapHistogram()
	{
		histogram = new TreeMap<>();
	}


	// increments the count of character sent to it by one
	public void add(char c)
	{
		int count = 0;
		if(histogram.containsKey(c))
		{
			count = histogram.get(c);
		}
		count += 1;
		histogram.put(c, count);

	}

	public Map<Character, Integer> getMap()
	{
		return histogram;
	}

	// returns the count of a specific character
	public int getCount(char c)
	{
		if(histogram.containsKey(c))
		{
			return histogram.get(c);
		}
		return 0;
	}
}
