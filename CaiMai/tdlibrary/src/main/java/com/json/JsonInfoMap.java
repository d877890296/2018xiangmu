package com.json;

import java.util.HashMap;
import java.util.List;

 

public class JsonInfoMap {
	
	private String className;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	 
	
	private JsonInfoMap(){}
	public final HashMap<String, Property> propertyMap = new HashMap<String, Property>();
	
	private static final HashMap<String, JsonInfoMap> tableInfoMap = new HashMap<String, JsonInfoMap>();
	
	
	public static  JsonInfoMap get(Class<?> clazz){
		JsonInfoMap infoMap = tableInfoMap.get(clazz.getName());
		if( infoMap != null ){
			return infoMap;
		}
		infoMap = new JsonInfoMap();
		infoMap.setClassName(clazz.getName());
		
		List<Property> pList = ClassUtils.getPropertyList(clazz);
		if(pList!=null){
			for(Property p : pList){
				if(p!=null)
					infoMap.propertyMap.put(p.getJsonName(), p);
			}
		}
		tableInfoMap.put(clazz.getName(), infoMap);
		return infoMap;
	}
	
	public static JsonInfoMap get(String className){
		try {
			return get(Class.forName(className));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
