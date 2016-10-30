package com.jstudy.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setAge(10);
		p1.setName("zhangsan");
		p1.setDepartMent("engineer,finance");

		String sql = generateSql(p1);
		System.out.println(sql);
	}

	public static String generateSql(Object obj) {
		boolean isExist = obj.getClass().isAnnotationPresent(Table.class);
		if (!isExist) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Table a = obj.getClass().getAnnotation(Table.class);
		String tableName = a.value();
		sb.append("select * from ").append(tableName).append(" where 1 = 1");
		Field[] fs = obj.getClass().getDeclaredFields();
		for (Field f : fs) {
			boolean fExist = f.isAnnotationPresent(Column.class);
			if (fExist) {
				Column c = f.getAnnotation(Column.class);
				String columnName = c.value();
				String fieldName = f.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase(Locale.getDefault())
						+ fieldName.substring(1);
				StringBuilder value = new StringBuilder();
				try {
					Method getMethod = obj.getClass().getMethod(getMethodName, null);
					Object mReturn = getMethod.invoke(obj);
					if (mReturn instanceof Integer) {
						sb.append(" and ").append(columnName);
						value.append("=").append(mReturn);
					} else if (mReturn instanceof String) {
						sb.append(" and ").append(columnName);
						if (((String) mReturn).contains(",")) {
							value.append(" in(");
							String[] mReturnArr = ((String) mReturn).split(",");
							for (String s : mReturnArr) {
								value.append("'").append(s).append("'").append(",");
							}
							value.deleteCharAt(value.length() - 1);
							value.append(")");
						} else {
							value.append("=").append("'").append(mReturn).append("'");
						}
					}
					sb.append(value);

				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
