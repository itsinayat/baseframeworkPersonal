package com.rest.baseframework.dao.hibernate;

/**
 * @author inayat
 * 
 * This class will generate HQL query based on given arguments
 */

import org.apache.commons.lang3.StringUtils;

public class HibernateQueryGenerator {

	/**
	 * @author Abhishek
	 * 
	 * @param aClass
	 * @param returnProperty
	 * @param propertyNames
	 * @param propertyValues
	 * @param orderProperty
	 * @param isDescending
	 * @return
	 */
	public static String generateQuery(@SuppressWarnings("rawtypes") Class aClass, String[] returnProperty,
			String[] propertyNames, Object[] propertyValues, String orderProperty, boolean isDescending) {

		if (propertyNames != null && propertyValues != null) {
			if (propertyNames.length != propertyValues.length)
				throw new RuntimeException("Null or Un-mached property name-value pair");
		}
		StringBuffer sb = null;

		sb = new StringBuffer("select ");
		for (int i = 0; i < returnProperty.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append("al.").append(returnProperty[i]);
		}
		sb.append(" from ");
		sb.append(aClass.getName()).append(" as al ");
		if (propertyNames != null) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (i == 0) {
					sb.append(" where ");
				} else {
					sb.append(" and ");
				}

				if (propertyValues != null && propertyValues[i] instanceof String && propertyValues[i].toString().contains("%")) {
					sb.append(" upper(").append(" al.");
					sb.append(propertyNames[i]).append(")");
					sb.append(" like ?" + i + " ");
				} else {
					sb.append(" al.").append(propertyNames[i]);
					if (propertyValues == null || propertyValues[i] == null) {
						sb.append(" is null ");
					} else {
						sb.append(" = ?" + i + " ");
					}
				}
			}
		}
		if (!StringUtils.isEmpty(orderProperty)) {
			sb.append(" order by ");
			String[] split = orderProperty.split(",");
			int size = 0;
			for (String aProp : split) {
				size++;
				sb.append("al.").append(aProp);
				if (size != split.length) {
					sb.append(", ");
				}
			}
			if (isDescending) {
				sb.append(" desc ");
			}
		}
		return sb.toString();
	}

	public static String generateBulkUpdateQuery(@SuppressWarnings("rawtypes") Class aClass, String[] propertyName,
			Object[] propertyValue, String[] conditionPropertyName, Object[] conditionPropertyValue) {

		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(aClass.getName()).append(" set ");
		int i = 0;
		for (; i < propertyName.length; i++) {
			if (propertyName[i].indexOf(".") > 0) {
				throw new RuntimeException("Use simple property values; Please omit '.' from property");
			}
			if (i != 0) {
				sb.append(" , ");
			}
			sb.append(propertyName[i]).append(" = ?" + i + " ");

		}
		if (conditionPropertyName != null && conditionPropertyValue != null) {
			for (int j = 0; j < conditionPropertyName.length; j++) {
				// if (conditionPropertyName[j].indexOf(".") > 0) {
				// throw new RuntimeException("Use simple property values;
				// Please omit '.' from property");
				// }
				if (j == 0) {
					sb.append(" where ");
				} else {
					sb.append(" and ");
				}
				sb.append(conditionPropertyName[j]);
				if (conditionPropertyValue[j] == null) {
					sb.append(" is null ");
				} else {
					sb.append(" = ?" + i + " ");
					i++;
				}
			}
		}
		return sb.toString();
	}
}
