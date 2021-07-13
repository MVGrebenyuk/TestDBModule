package ru.neoflex.msa.testKafkaModule.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class DBSupport {

    public DBSupport() {
    }

    private DataSource dataSource;

    private String dataBaseName;

    @PostConstruct
    public void init() throws ClassNotFoundException {
    DriverManagerDataSource dmds = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=123456");
    dataSource = dmds;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
