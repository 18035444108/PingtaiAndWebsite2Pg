package com.golaxy.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EntityCaseUtils {
	public <T> List<T> caseEntity(List<Object[]> list, Class<T> clazz) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<T> rList = new ArrayList<T>(list.size());
		Object[] o = list.get(0);
		@SuppressWarnings("rawtypes")
		Class[] c = new Class[o.length];
		for (int i = 0; i < o.length; i++) {
			c[i] = o[i].getClass();
		}
		for (Object[] os : list) {
			Constructor<T> ct = clazz.getConstructor(c);
			rList.add(ct.newInstance(os));
		}
		return rList;

	}
}
