package com.json;

 

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BtJsonParse {
	
	public static Object parseJsonString(String json,Class<?> clazz)
	{
		JSONObject jsonObj=null;
		try {
			jsonObj = new JSONObject( json);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return parseJson(jsonObj,clazz);
	}
	
	 
	private static Object parseJson(JSONObject json,Class<?> clazz)
	{
		if(json==null)
			return null;
		
		Object entity =null;
		try {
			  entity =   clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		JsonInfoMap infomap = JsonInfoMap.get(clazz);
		if(infomap==null)
			return null;
		
		if(entity instanceof BaseJsonObject )
		{
			//检查 status ,info
			String filedname;
			 
			filedname="info";
			Property info_property = infomap.propertyMap.get(filedname);
			if(info_property!=null){
				info_property.setJsonValue(entity, json);
			}
			
			filedname="status";
			Property status_property = infomap.propertyMap.get(filedname);
			if(status_property!=null){
				status_property.setJsonValue(entity, json);
			}
			
			Integer status=	status_property.getValue(entity);
			if(status==null)
			{
				return null;
			}
			if(status!=1)
			{
				return entity;
			}
		}
		pushValueFromJson(entity,json,clazz);

		return entity;
	}
	private static void pushArrayValueFromJson(Object entity,JSONObject json,Class<?> clazz,Field f,JsonInfoMap infomap)
	{
		Class<?> arrayListClasszz= f.getType();
		Class<?> arrayObjClazz=FieldUtils.getArrayClassType(f);
		String jsonname =FieldUtils.getColumnByField(f);
		Property pro = infomap.propertyMap.get(jsonname);
		CommonConvert convert=new CommonConvert(json);
		JSONArray jsonObjArray=null;
		try {
			jsonObjArray=convert.getObjectArray(jsonname);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(jsonObjArray==null)
			return;
		
		Object newList;
		Method addStrMethod;
		try {
			newList = arrayListClasszz.newInstance();
			addStrMethod= arrayListClasszz.getDeclaredMethod("add", new Class[]{Object.class});
			addStrMethod.setAccessible(true);
		} catch (Exception e1) {
			e1.printStackTrace();
			 return;
		}  
		 
		
		int count=jsonObjArray.length();
		for(int i=0;i<count;i++){
			JSONObject jsonObj=null;
			try {
				 jsonObj=jsonObjArray.getJSONObject(i);
			} catch (JSONException e) {
				e.printStackTrace();
				break;
			}
			
			Object newObj=null;
			try {
				newObj =  arrayObjClazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			pushValueFromJson(newObj,jsonObj,arrayObjClazz);
			
		 
		    //add object
			try {
				addStrMethod.invoke(newList, newObj);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		
		if(pro!=null){
			pro.setValue(entity, newList);
		}
	}
	private static void pushObjValueFromJson(Object entity,JSONObject json,Class<?> clazz,Field f,JsonInfoMap infomap)
	{
		Class<?> objClasszz= f.getType();
		
		String jsonname =FieldUtils.getColumnByField(f);
		Property pro = infomap.propertyMap.get(jsonname);
		
		CommonConvert convert=new CommonConvert(json);
		JSONObject jsonObj=null;
		try {
			jsonObj=convert.getObject(jsonname);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Object newObj=null;
		try {
			newObj =  objClasszz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		pushValueFromJson(newObj,jsonObj,objClasszz);
		
		if(pro!=null){
			pro.setValue(entity, newObj);
		}
	}
	//把json中的数据推到object中
	private static void pushValueFromJson(Object entity,JSONObject json,Class<?> clazz)
	{
		JsonInfoMap infomap = JsonInfoMap.get(clazz);
		if(infomap==null)
			return ;
		Field[] fs = clazz.getFields();
		for (Field f : fs) {
			//必须是基本数据类型和没有标瞬时态的字段
				if (!FieldUtils.isJsonField(f)) {
					continue;
				}
				//基础类型
				if(FieldUtils.isBaseDataType(f))
				{
					String jsonname =FieldUtils.getColumnByField(f);
					Property pro = infomap.propertyMap.get(jsonname);
					if(pro!=null){
						pro.setJsonValue(entity, json);
					}
				}else if(FieldUtils.isArrayListType(f))
				{
					pushArrayValueFromJson( entity, json, clazz, f, infomap);
					
				}else //Object 类似
				{
					pushObjValueFromJson( entity, json, clazz, f, infomap);
				}
				 
		}
	}
	 

	
}
