package userSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, User>>{
	
	public static class AdaptedMap {
		public List<Entry> entry = new ArrayList<Entry>();
	}
	
	public static class Entry {
		public String key;
		public User value;
 
	}

	@Override
	public AdaptedMap marshal(Map<String, User> map) throws Exception {
		AdaptedMap adaptedMap = new AdaptedMap();
		for (Map.Entry<String, User> mapEntry : map.entrySet()) {
			Entry entry = new Entry();
			entry.key = mapEntry.getKey();
			entry.value = mapEntry.getValue();
			adaptedMap.entry.add(entry);
		}
		return adaptedMap;
	}

	@Override
	public Map<String, User> unmarshal(AdaptedMap adaptedMap) throws Exception {
		Map<String, User> map = new HashMap<String, User>();
		for (Entry entry : adaptedMap.entry) {
			map.put(entry.key, entry.value);
		}
		return map;
	}
}
