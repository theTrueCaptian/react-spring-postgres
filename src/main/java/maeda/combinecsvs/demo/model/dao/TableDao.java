package maeda.combinecsvs.demo.model.dao;

import maeda.combinecsvs.demo.db.DBConnection;
import maeda.combinecsvs.demo.model.obj.Table;
import maeda.combinecsvs.demo.utils.StringUtils;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDao {
    private DBConnection db;
    public TableDao(DBConnection db) {
        this.db = db;
    }

    /**
     *
     * @param tableName
     * @param inputFileLoc
     * @param columnDelimiter
     * @param isHeader
     * @throws SQLException
     * @throws IOException
     */
    public void create(String tableName, String inputFileLoc, String columnDelimiter, boolean isHeader) throws SQLException, IOException {

        //use copy manager to read file
        CopyManager copyManager = new CopyManager((BaseConnection) this.db.getConnection());
        FileReader fileReader = new FileReader(inputFileLoc);


        String[] orderedColumns = readHeader(inputFileLoc, columnDelimiter, isHeader);
        String cols = "("+ StringUtils.joinWithSep(orderedColumns, ",") +")";


        String colsAndType = "";
        for(String outputCol: orderedColumns){
            colsAndType = colsAndType + (colsAndType.length()==0?"": ",\n")+ outputCol +" text";
        }

        String sql = "CREATE TABLE "+tableName+"\n" +
                "(\n" + colsAndType + ");";
        PreparedStatement pstmt = this.db.prepareStatement(sql);
        this.db.executeUpdate(pstmt);

        copyManager.copyIn("COPY " + tableName + " " + cols + " FROM STDIN WITH CSV DELIMITER '" + columnDelimiter + "'  " + (isHeader ? "HEADER" : ""), fileReader);

    }

    private String[] readHeader(String inputFileLoc, String columnDelimiter, boolean isHeader) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFileLoc)), "utf-8"));
        String line;
        String[] orderedColumns = null;

        //Insert the rows into init
        //read the header first
        if ((line = br.readLine()) != null) {
            String escapedDelimited = columnDelimiter.equals("|")? "\\|": columnDelimiter;
            orderedColumns = line.split(escapedDelimited);
        }
        br.close();

        if(!isHeader){
            for(int i=0; i<orderedColumns.length; i++){
                orderedColumns[i] = "col_" + (i+1);
            }
        }
        return orderedColumns;
    }

    public Table get(String tableName) throws SQLException {
        String sql = "select * from " + tableName;
        PreparedStatement pstmt = this.db.prepareStatement(sql);
        ResultSet res = this.db.executeQuery(pstmt);

        while(res.next()){

        }
        return null;
    }


}
