@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:sqlite:taskdb.db")
                .driverClassName("org.sqlite.JDBC")
                .build();
    }
}