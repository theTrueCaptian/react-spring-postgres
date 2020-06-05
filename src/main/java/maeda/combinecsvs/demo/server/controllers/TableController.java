package maeda.combinecsvs.demo.server.controllers;

import maeda.combinecsvs.demo.db.DBConnection;
import maeda.combinecsvs.demo.db.DB_CONSTANTS;
import maeda.combinecsvs.demo.model.obj.Table;
import maeda.combinecsvs.demo.server.services.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("table")
public class TableController {

    /**
     *
     * @param tableName e.g. SelectCustomerId
     * @return the table's contents
     *
     * @throws IOException
     *
     */
    @GetMapping("/{tableName}")
    public Table getTable(@PathVariable String tableName) throws IOException, SQLException {

        DBConnection db = new DBConnection(DB_CONSTANTS.DB_CONN_INFO);
        return new TableService(db).get(tableName);
    }



}