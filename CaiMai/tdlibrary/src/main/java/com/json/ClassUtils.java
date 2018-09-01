package com.json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

 
 

public class ClassUtils {


public static List<Property> getPropertyList(Class<?> clazz) {
		List<Property> plist = new ArrayList<Property>();
		try {
			Field[] fs = clazz.getFields();
			for (Field f : fs) {
					if (!FieldUtils.isJsonField(f)) {
						continue;
					}
					Property property = new Property();
					property.setJsonName(FieldUtils.getColumnByField(f));
					property.setFieldName(f.getName());
					property.setDataType(f.getType());
					property.setDefaultValue(FieldUtils.getPropertyDefaultValue(f));
					property.setSet(FieldUtils.getFieldSetMethod(clazz, f));
					property.setGet(FieldUtils.getFieldGetMethod(clazz, f));
					property.setField(f);
					plist.add(property);
			}
			return plist;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	


		
}

