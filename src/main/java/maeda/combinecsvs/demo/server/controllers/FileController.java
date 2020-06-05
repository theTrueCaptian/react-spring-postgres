package maeda.combinecsvs.demo.server.controllers;

import maeda.combinecsvs.demo.db.DBConnection;
import maeda.combinecsvs.demo.db.DB_CONSTANTS;
import maeda.combinecsvs.demo.model.obj.Table;
import maeda.combinecsvs.demo.server.services.FileService;
import maeda.combinecsvs.demo.server.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("columnDelimiter") String columnDelimiter,
            @RequestParam("isHeader") int isHeader,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        //DBConnection db = new DBConnection(DB_CONSTANTS.DB_CONN_INFO);

        fileService.uploadFile(file, columnDelimiter, isHeader==1);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

}
