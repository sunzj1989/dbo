package com.msunsoft.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		Object ds = DataSourceHolder.getCustomeType();
		DataSourceHolder.remove();
		return ds;
		//		return DataSourceHolder.getCustomeType();
	}

}
