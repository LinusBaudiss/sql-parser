package de.linus.sqlparser.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    private DatabaseConnection db;
    private Connection con;
    private static final Logger LOGGER = LoggerFactory.getLogger(Queries.class);
    private static final String SQLEXPMSG = "A wild SQLException occured, while performing the following statement: ";

    public Queries(String url, String user, String pw) {
        try {
            db = new DatabaseConnection(url, user, pw);
            con = db.getConnection();
        } catch (SQLException e) {
            LOGGER.error("A wild SQLException occured, while connecting to the database", e);
        }
    }

    public boolean fireExecute(String statement) {
        try (PreparedStatement pstmt = con.prepareStatement(statement)) {
            pstmt.execute();
        } catch (SQLException e) {
            LOGGER.error(SQLEXPMSG + statement, e);
            return false;
        }
        return true;
    }

    // TODO: Needs refactoring - Write output to output file
    public List<String> fireQuery(String statement) throws RuntimeException {
        List<String> list = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(statement)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();
                StringBuilder header = new StringBuilder();
                for (int j = 1; j < numColumns + 1; j++) {
                    header.append(rsmd.getColumnLabel(j));
                    if (j != numColumns) {
                        header.append(" | ");
                    }
                }
                list.add(header.toString());
                while (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < numColumns + 1; i++) {
                        switch (rsmd.getColumnType(i)) {
                            case java.sql.Types.BOOLEAN:
                                sb.append(rs.getBoolean(i));
                                break;
                            case java.sql.Types.CHAR:
                            case java.sql.Types.VARCHAR:
                                sb.append(rs.getString(i));
                                break;
                            case java.sql.Types.DATE:
                                sb.append(rs.getDate(i));
                                break;
                            case java.sql.Types.DECIMAL:
                            case java.sql.Types.DOUBLE:
                                sb.append(rs.getDouble(i));
                                break;
                            case java.sql.Types.INTEGER:
                                sb.append(rs.getInt(i));
                                break;
                            case java.sql.Types.TIMESTAMP:
                                sb.append(rs.getTimestamp(i));
                                break;
                            case java.sql.Types.TIME:
                                sb.append(rs.getTime(i));
                            default:
                                throw new RuntimeException(
                                        "A wild switch exception occured, while switching the SQL datatypes!");
                        }
                        if (i != numColumns) {
                            sb.append(" | ");
                        }
                    }
                    list.add(sb.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(SQLEXPMSG + statement, e);
        }
        return list;
    }

    public boolean fireUpdate(String statement) {
        try (PreparedStatement pstmt = con.prepareStatement(statement)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQLEXPMSG + statement, e);
            return false;
        }
        return true;
    }
}
