package com.devcula.resources;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue.ValueType;

@SuppressWarnings("all")
public class JSONParser {

	private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(
			Boolean.class, Character.class, Byte.class, Short.class,
			Integer.class, Long.class, Float.class, Double.class, Void.class,
			String.class));

	private static boolean isWrapperType(Class clazz) throws Exception {
		return WRAPPER_TYPES.contains(clazz);
	}

	private static String toJsonFromList(List list) throws Exception {
		String JSONString = "[";
		for (Object item : list) {
			if (isWrapperType(item.getClass()))
				if (item.getClass().getName().equals("java.lang.String")
						|| item.getClass().getName()
								.equals("java.lang.Character"))
					JSONString += "\"" + item.toString() + "\"";
				else
					JSONString += item.toString();
			else if (item.getClass().getName()
					.equals("java.util.LinkedHashMap")
					|| item.getClass().getName().equals("java.util.HashMap")
					|| item.getClass().getName().equals("java.util.Map")) {
				JSONString += toJsonFromMap((Map) item);
			} else if (item.getClass().getName().equals("java.util.ArrayList")
					|| item.getClass().getName().equals("java.util.List")) {
				JSONString += toJsonFromList((List) item);
			} else if (item.getClass().getName()
					.equals("java.util.GregorianCalendar")
					|| item.getClass().getName().equals("java.util.Calendar")) {
				JSONString += toJson(item);
			} else {
				JSONString += toJson(item);
			}
			JSONString += ",";
		}
		JSONString = JSONString.substring(0, JSONString.length() - 1);
		JSONString += "]";
		return JSONString;
	}

	private static String toJsonFromMap(Map map) throws Exception {
		String JSONString = "{";
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			JSONString += "\"" + key.toString() + "\"";
			JSONString += ":";
			JSONString += "\"";

			try {
				if (value.getClass().getName()
						.equals("class java.util.ArrayList")
						|| value.getClass().getName()
								.equals("class java.util.List"))
					JSONString += toJsonFromList((List) value);
				else if (value.getClass().getName()
						.equals("class java.util.HashMap")
						|| value.getClass().getName()
								.equals("class java.util.LinkedHashMap")
						|| value.getClass().getName().equals("class Map"))
					JSONString += toJsonFromMap((Map) value);
				else if (isWrapperType(value.getClass())) {
					if (value.getClass().getName()
							.equals("class java.lang.String")
							|| value.getClass().getName()
									.equals("class java.lang.Character"))
						JSONString += "\"" + value.toString() + "\"";
					else
						JSONString += value.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Error in parsing map");
			}

			JSONString += "\"";
			JSONString += ",";
		}
		JSONString = JSONString.substring(0, JSONString.length() - 1);
		JSONString += "}";
		return JSONString;
	}

	public static String toJson(Object obj) throws Exception {
		String JSONString = "{";
		if (obj.getClass().getName().equals("java.util.ArrayList")
				|| obj.getClass().getName().equals("java.util.List"))
			return toJsonFromList((List) obj);
		else if (obj.getClass().getName().equals("java.util.HashMap")
				|| obj.getClass().getName().equals("java.util.LinkedHashMap")
				|| obj.getClass().getName().equals("Map"))
			return toJsonFromMap((Map) obj);
		else if (isWrapperType(obj.getClass())) {
			if (obj.getClass().getName().equals("java.lang.String")
					|| obj.getClass().getName().equals("java.lang.Character"))
				JSONString += "\"" + obj.toString() + "\"";
			else
				JSONString += obj.toString();
		} else {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor propertyDesc : beanInfo
					.getPropertyDescriptors()) {
				if (propertyDesc.getReadMethod().invoke(obj) != null
						&& !(propertyDesc.getName().equals("class"))) {
					JSONString += "\"" + propertyDesc.getName() + "\"";
					JSONString += ":";
					if (propertyDesc.getPropertyType().toString()
							.equals("class java.lang.String")
							|| propertyDesc.getPropertyType().toString()
									.equals("class java.lang.Character")) {
						JSONString += "\""
								+ propertyDesc.getReadMethod().invoke(obj)
								+ "\"";
						JSONString += ",";
					} else {
						if (propertyDesc.getPropertyType().toString()
								.equals("class java.util.Calendar")
								|| propertyDesc
										.getPropertyType()
										.toString()
										.equals("class java.util.GregorianCalendar")) {
							Calendar calendar = (GregorianCalendar) propertyDesc
									.getReadMethod().invoke(obj);
							SimpleDateFormat sdf = new SimpleDateFormat(
									"MM/dd/yyyy, hh:mm:ss aaa");
							String calendarJsonString = "\""
									+ sdf.format(calendar.getTime()) + "\"";
							JSONString += calendarJsonString;
							JSONString += ",";

						} else {
							JSONString += propertyDesc.getReadMethod().invoke(
									obj);
							JSONString += ",";
						}
					}
				}
			}
			JSONString = JSONString.substring(0, JSONString.length() - 1);
		}
		JSONString += "}";
		return JSONString;
	}

	public static <T> T fromJson(String jsonInput, Class<T> classOfInputData) throws Exception{
		JsonObject jsonObj = Json.createReader(new StringReader(jsonInput))
				.readObject();

		T object = null;
		try {

			object = classOfInputData.newInstance();
			Field fieldArray[] = classOfInputData.getDeclaredFields();

			for (Field field : fieldArray) {
				// To know the Json type of the field
				field.setAccessible(true);
				if (jsonObj.containsKey(field.getName())) {
					// Sets the value for field type
					// Number,String,boolean,Object[Calendar,AnyUserdefined
					// Object]
					setFieldData(jsonObj, field, object);

					if (jsonObj.get(field.getName()).getValueType()
							.equals(ValueType.ARRAY)) {

						if (field.getType().getCanonicalName()
								.contains("java.util.Map")) {
							// JsonArray
							System.out.println("->in1");
						}
						// If object type is list
						else if (field.getType().getCanonicalName()
								.contains("java.util.List")) {

							// Get the ParameterizedType of List
							ParameterizedType listType = (ParameterizedType) field
									.getGenericType();
							Class<?> listClass = (Class<?>) listType
									.getActualTypeArguments()[0];

							JsonArray jsonArray = jsonObj.getJsonArray(field
									.getName());
							List list = new ArrayList();
							// List of String
							if (listClass.getCanonicalName().contains("String")) {
								for (int i = 0; i < jsonArray.size(); i++) {
									list.add(jsonArray.getString(i));
								}
							}
							// List of Integer
							else if (listClass.getCanonicalName().contains(
									"Integer")) {
								for (int i = 0; i < jsonArray.size(); i++) {
									list.add(jsonArray.getInt(i));
								}
							}
							// list of user defined object
							else {
								for (int i = 0; i < jsonArray.size(); i++) {
									JsonObject jsonObject = jsonArray
											.getJsonObject(i);
									Object obj = getUserDefinedObject(listClass, jsonObject, field);
									list.add(obj);
								}
							}
							field.set(object, list);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Problem in input data", e);
		}
		return object;
	}
	
	private static Object getUserDefinedObject(Class listClass,
			JsonObject jsonObject, Field field) throws Exception {

		Object obj = Class.forName(listClass.getName()).newInstance();
		Field fieldBean[] = listClass.getDeclaredFields();

		for (Field field2 : fieldBean) {
			field2.setAccessible(true);
			setFieldData(jsonObject, field2, obj);
		}
		return obj;
	}
	
	// Getting Calender object from JsonObject
	private static Calendar getCalendarFromJSON(JsonObject jsonObj, Field field) {
		String dateStr =  jsonObj.getString(field.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss aaa");
		Calendar actualCalObject =Calendar.getInstance();
		try {
			Date birthDate = sdf.parse(dateStr);
			actualCalObject.setTime(birthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return actualCalObject;
	}
	
	private static LocalDate getLocalDateFromJSON(JsonObject jsonObj, Field field) {
		/*JsonObject calJsonObj = jsonObj.getJsonObject(field.getName());*/
		String dateStr =  jsonObj.getString(field.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
		LocalDate date=null;
		try {
			date=LocalDate.parse(dateStr,formatter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	private static Boolean isDatePattern(String inputString){
		Boolean flag=false;
		
	    String regularExpressionDatePattern="((?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";	// MMDDYYYY 1
	    String regularExpressionSingleCharacter="(,)";	// Any Single Character 1
	    String regularExpressionWhiteSpace="(\\s+)";	// White Space 1
	    String regularExpressionTime="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec 1

	    Pattern pattern = Pattern.compile("\""+regularExpressionDatePattern+regularExpressionSingleCharacter+regularExpressionWhiteSpace+regularExpressionTime+"\"",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    if(inputString.matches(pattern.toString())){
	    	flag=true;
	    };
		return flag;
	}
	
	private static Boolean isLocalDatePattern(String inputString){
		Boolean flag=false;
		
	    String regularExpression="((?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";	// 
	    Pattern pattern = Pattern.compile("\""+regularExpression+"\"",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    if(inputString.matches(pattern.toString())){
	    	flag=true;
	    };
		return flag;
	}
	
	private static void setFieldData(JsonObject jsonObj, Field field,
			Object object) throws Exception {
		
		if(isDatePattern(jsonObj.get(field.getName()).toString())){
			Calendar actualCalObject = getCalendarFromJSON(jsonObj, field);
			field.set(object, actualCalObject);
		}
		else if(isLocalDatePattern(jsonObj.get(field.getName()).toString())){
			LocalDate actualCalObject=getLocalDateFromJSON(jsonObj, field);
			field.set(object, actualCalObject);
			
		}
		else if (jsonObj.get(field.getName()).getValueType().equals(ValueType.STRING)) {
			
			field.set(object, jsonObj.getString(field.getName()));
		} else if (jsonObj.get(field.getName()).getValueType().equals(ValueType.NUMBER)) {
			if (field.getType().getCanonicalName().contains("Integer"))
				field.set(object, jsonObj.getInt(field.getName()));
			else if (field.getType().getCanonicalName().contains("Long")) {
				Long value = Long.parseLong(jsonObj.get(field.getName())
						.toString());
				field.set(object, value);
			} else if (field.getType().getCanonicalName().contains("Double")) {
				Double value = Double.parseDouble(jsonObj.get(field.getName())
						.toString());
				field.set(object, value);
			} else if (field.getType().getCanonicalName().contains("Float")) {
				Float value = Float.parseFloat(jsonObj.get(field.getName())
						.toString());
				field.set(object, value);
			} else if (field.getType().getCanonicalName().contains("Short")) {
				Short value = Short.parseShort(jsonObj.get(field.getName())
						.toString());
				field.set(object, value);
			} else if (field.getType().getCanonicalName().contains("Byte")) {
				Byte value = Byte.parseByte(jsonObj.get(field.getName())
						.toString());
				field.set(object, value);
			}
		} else if (jsonObj.get(field.getName()).getValueType()
				.equals(ValueType.TRUE)) {
			field.set(object, jsonObj.getBoolean(field.getName()));
		} else if (jsonObj.get(field.getName()).getValueType()
				.equals(ValueType.FALSE)) {
			field.set(object, jsonObj.getBoolean(field.getName()));
		} else if (jsonObj.get(field.getName()).getValueType()
				.equals(ValueType.OBJECT)) {
				JsonObject jsonObject2 = jsonObj.getJsonObject(field.getName());
				Object resultObj = getUserDefinedObject(field.getType(),
						jsonObject2, field);
				field.set(object, resultObj);
			
		}
	}

}
