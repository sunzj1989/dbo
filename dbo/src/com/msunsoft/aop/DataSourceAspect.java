package com.msunsoft.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import com.msunsoft.annotation.DataSource;
import com.msunsoft.datasource.DataSourceHolder;

@Component
public class DataSourceAspect {

	public void changeDateSource(JoinPoint jp) {
		try {
			String methodName = jp.getSignature().getName();
			Class<?> targetClass = Class.forName(jp.getTarget().getClass().getName());
			for (Method method : targetClass.getMethods()) {
				if (methodName.equals(method.getName())) {
					Class<?>[] args = method.getParameterTypes();
					if (args.length == jp.getArgs().length) {
						DataSource ds = method.getAnnotation(DataSource.class);
						DataSourceHolder.setCustomeType(ds.name());
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}