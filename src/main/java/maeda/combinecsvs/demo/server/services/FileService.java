package maeda.combinecsvs.demo.server.services;

import maeda.combinecsvs.demo.db.DBConnection;
import maeda.combinecsvs.demo.db.DB_CONSTANTS;
import maeda.combinecsvs.demo.exception.FileStorageException;
import maeda.combinecsvs.demo.model.dao.TableDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

@Service

public class FileService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public void uploadFile(MultipartFile file, String columnDelimiter, boolean isHeader) {

        try {
            String fileSuffix = System.currentTimeMillis() +"";
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()) + "_"+fileSuffix );
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            DBConnection db = new DBConnection(DB_CONSTANTS.DB_CONN_INFO);
            String tableName = "tbl_" + fileSuffix;
            new TableDao(db).create(tableName, copyLocation.toString(), columnDelimiter, isHeader);

        } catch (FileStorageException e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
