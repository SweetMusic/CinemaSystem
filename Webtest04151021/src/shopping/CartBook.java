package shopping;

import java.util.HashMap;
import java.util.Map;

public class CartBook {
	private Map<String, Integer> map;

	public CartBook()
	{
		map = new HashMap<String, Integer>();
	}
	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	
}
