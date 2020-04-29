package com.basejava.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Executor<T> {
   public T execute(PreparedStatement ps) throws SQLException;
}
