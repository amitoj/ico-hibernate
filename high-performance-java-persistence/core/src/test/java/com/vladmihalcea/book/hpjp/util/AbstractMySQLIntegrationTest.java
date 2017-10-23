package com.vladmihalcea.book.hpjp.util;

import com.vladmihalcea.book.hpjp.util.providers.DataSourceProvider;
import com.vladmihalcea.book.hpjp.util.providers.MySQLDataSourceProvider;

/**
 * AbstractMySQLIntegrationTest - Abstract MySQL IntegrationTest
 *
 * @author Vlad Mihalcea
 */
public abstract class AbstractMySQLIntegrationTest extends AbstractTest {

    @Override
    protected DataSourceProvider dataSourceProvider() {
        return new MySQLDataSourceProvider();
    }
}
