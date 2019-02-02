package com.rest.baseframework.dao.hibernate;

import java.util.List;

public interface IBaseDao {

	<T> T retrieveLatestObject(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> List<T> retrieveLatestObjects(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> T retrieveObject(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> List<T> retrieveObjects(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> T retrieveLatestReadOnlyObject(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> List<T> retrieveLatestReadOnlyObjects(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> T retrieveReadOnlyObject(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> List<T> retrieveReadOnlyObjects(Class<T> clazz, String[] conditionPropertyNames, Object[] conditionPropertyValues);

	<T> T saveObject(T obj);

	<T> T updateObject(T obj);

	<T> List<T> customUpdateObjects(Class<T> clazz, List<T> newObjects, String[] conditionPropertyNames,
			Object[] conditionPropertyValues);
	
	<T> T customUpdateObject(Class<T> clazz, T newObject, String[] conditionPropertyNames,
			Object[] conditionPropertyValues);
}
