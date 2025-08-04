package com.teamaround.resources.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testcontainers.containers.PostgreSQLContainer;

import com.teamaround.resources.sql.SqlScripts;
import com.teamaround.resources.sql.Table;
import com.teamaround.resources.sql.real_sql_classes.User;


@TestInstance(Lifecycle.PER_CLASS)
public class SqlWrapperTest {

    private PostgreSQLContainer<?> postgresContainer;
    private Connection connection;
    private final String POSTGRESQL_IMAGE_NAME = "postgres:14.18";

    @BeforeAll
    public void setup() throws Exception{
        
        this.postgresContainer = new PostgreSQLContainer<>(this.POSTGRESQL_IMAGE_NAME);
        this.postgresContainer.start();
        this.connection = DriverManager.getConnection(this.postgresContainer.getJdbcUrl(), this.postgresContainer.getUsername(), this.postgresContainer.getPassword());
    }
    @AfterAll
    public void clean()
    {
        postgresContainer.close();
    }

    @Test
    void connectToDatabaseReturnsNotNull() throws SQLException
    {
        String url = this.postgresContainer.getJdbcUrl();
        String username = this.postgresContainer.getUsername();
        String password = this.postgresContainer.getPassword();

        this.connection = DriverManager.getConnection(url, username, password);

        assertNotEquals(null, this.connection);
    }

    @Test
    void createDBReturnsTrue() throws Exception
    {
        SqlScripts.initialize();

        boolean result = SqlWrapper.createDB(this.connection);

        assertTrue(result);
    }

    @Test
    void insertDataMockUserReturnsTrue() throws Exception
    {
        SqlScripts.initialize();
        this.connection.prepareStatement(SqlScripts.createUsersTable).execute();
        User mockUser = User.builder().nickname("Example_Username").firstName("Example_First_name").lastName("Example_Last_Name").email("Example_email").build();

        boolean result = SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, mockUser);
        
        this.connection.prepareStatement("DROP TABLE users CASCADE").execute();
        assertTrue(result);
    }

    @Test
    void getData() throws Exception
    {
        SqlScripts.initialize();
        connection.prepareStatement(SqlScripts.createUsersTable).execute();
        User mockUser = User.builder().nickname("Example_Username").userId(1).firstName("Example_First_name").lastName("Example_Last_Name").email("Example_email").build();
        SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, mockUser);
        Table<User> mockTable = new Table<>();
        mockTable.addRow(mockUser);

        Table<User> resultTable = SqlWrapper.getData(
            this.connection,
            "SELECT * FROM users WHERE nickname = 'Example_Username';",
            CreateParametersArray.createParametersArray(new String[0]),
            User.class
        );

        this.connection.prepareStatement("DROP TABLE users CASCADE").execute();
        assertEquals(mockTable, resultTable);
    }
}
