package com.studentmanagement.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    Connection connect() throws SQLException;
}
