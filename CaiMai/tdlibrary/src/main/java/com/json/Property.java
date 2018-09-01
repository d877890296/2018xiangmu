 
package com.json;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.json.JSONObject;

 
public class Property {
	
	private String fieldName;
	private String jsonname;
	private String defaultValue;
	private Class<?> dataType;
	private Field field;
	
	private Method get;
	private Method set;
	public void setJsonValue(Object receiver , JSONObject json)
	{
		if(json==null)
			return;
		
		Object value=null;
		try
		{
		CommonConvert convert=new CommonConvert(json);
		if (dataType == String.class) {
			value= convert.getString(jsonname);
		} else if (dataType == int.class || dataType == Integer.class) {
			value= convert.getInt(jsonname);
		}  else if (dataType == double.class || dataType == Double.class) {
			value= convert.getDouble(jsonname);
		} else if (dataType == long.class || dataType == Long.class) {
			value= convert.getLong(jsonname);
		} else if(dataType==Boolean.class||dataType==boolean.class)
		{
			value= convert.getBoolean(jsonname);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(set!=null){
			try {
				set.invoke(receiver,value);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}else{
			try {
				field.setAccessible(true);
				field.set(receiver, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void setValue(Object receiver , Object value){
		
		if(set!=null){
			try {
				set.invoke(receiver,value);
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}else{
			try {
				field.setAccessible(true);
				field.set(receiver, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 
	}
	
	/**
	 * 获取某个实体执行某个方法的结果
	 * @param obj
	 * @param method
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue(Object obj){
		if(obj != null && get != null) {
			try {
				return (T)get.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		try {
			field.setAccessible(true);
			return (T) field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return null;
	}
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getJsonName() {
		return jsonname;
	}
	public void setJsonName(String jsonname) {
		this.jsonname = jsonname;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Class<?> getDataType() {
		return dataType;
	}
	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}
	public Method getGet() {
		return get;
	}
	public void setGet(Method get) {
		this.get = get;
	}
	public Method getSet() {
		return set;
	}
	public void setSet(Method set) {
		this.set = set;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	
}
