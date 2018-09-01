package com.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//此类避免JSON值为null造成的解析失败
public class CommonConvert {
	JSONObject obj=new JSONObject();
	public CommonConvert(JSONObject obj){
		this.obj=obj;
	}
	public String getString(String key) throws JSONException{
		if(obj.isNull(key)) return "";
		else if(obj.getString(key)==null||obj.getString(key)=="null") return "";
		else
		{
			 String ret= obj.getString(key);
			 return unescape(ret);
		}
	}
	public int getInt(String key) throws JSONException{
		if(obj.isNull(key)) return 0;
		else return obj.getInt(key);
	}
	public long getLong(String key) throws JSONException{
		if(obj.isNull(key)) return 0;
		else return obj.getLong(key);
	}
	public boolean getBoolean(String key) throws JSONException{
		if(obj.isNull(key)) return false;
		else return obj.getBoolean(key);
	}
	public double getDouble(String key) throws JSONException{
		if(obj.isNull(key)) return 0.0;
		else return obj.getDouble(key);
	}
	public JSONObject getObject(String key) throws JSONException{
		if(obj.isNull(key)) return null;
		else return obj.getJSONObject(key);
	}
	public JSONArray getObjectArray(String key) throws JSONException{
		if(obj.isNull(key)) return null;
		else return obj.getJSONArray(key);
	}
	
	public static boolean IsEmpty(final String object) {
		if ((object == null) || (object.length() <= 0)) {
			return true;
		}
		return false;
	}
	public static String unescape (String s)
	{
		if(IsEmpty(s))
			return "";
	    while (true)
	    {
	        int n=s.indexOf("&#");
	        if (n<0) break;
	        int m=s.indexOf(";",n+2);
	        if (m<0) break;
	        try
	        {
	            s=s.substring(0,n)+(char)(Integer.parseInt(s.substring(n+2,m)))+
	                s.substring(m+1);
	        }
	        catch (Exception e)
	        {
	            return s;
	        }
	    }
	    s=s.replace("&quot;","\"");
	    s=s.replace("&lt;","<");
	    s=s.replace("&gt;",">");
	    s=s.replace("&amp;","&");
	    return s;
	}

}
