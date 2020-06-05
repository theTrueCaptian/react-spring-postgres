package maeda.combinecsvs.demo.server.controllers;

import maeda.combinecsvs.demo.model.obj.Table;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class MainController {

    @RequestMapping("")
    public List<Table> getAllTables(){
        List<Table> tables = new ArrayList<>();

        tables.add(new Table("Person"));
        tables.add(new Table("Company"));

        return tables;
    }

}
