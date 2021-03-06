package com.example.ds;

import com.example.ds.config.DataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiRouteDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {

        return DataSourceContext.getDataSource();
    }
}
