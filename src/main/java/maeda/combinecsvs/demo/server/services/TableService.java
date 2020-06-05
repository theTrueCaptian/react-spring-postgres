package maeda.combinecsvs.demo.server.services;

import maeda.combinecsvs.demo.model.dao.TableDao;
import maeda.combinecsvs.demo.db.DBConnection;
import maeda.combinecsvs.demo.model.obj.Table;

import java.sql.SQLException;

public class TableService {
    private DBConnection db;

    public TableService(DBConnection db) {
        this.db = db;
    }

    public Table get(String tableName) throws SQLException {
        return new TableDao(db).get(tableName);
    }

}
