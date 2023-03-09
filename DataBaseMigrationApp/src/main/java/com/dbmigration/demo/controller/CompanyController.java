package com.dbmigration.demo.controller;

import com.dbmigration.demo.model.Company;
import com.dbmigration.demo.service.CompanyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    private static Logger logger= Logger.getLogger(CompanyController.class);

    @GetMapping("/fetchCompany/{companyId}")
    public ResponseEntity<Company> getCompanyByCompanyID(@PathVariable int companyId) {
        return new ResponseEntity<>(
          companyService.getByCompanyId(companyId),
          HttpStatus.OK
        );
    }

    @PostMapping("/saveCompany")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<>(
          companyService.saveCompany(company),
          HttpStatus.CREATED
        );
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable int companyId) {
        return new ResponseEntity<>(
          companyService.deleteCompany(companyId),
          HttpStatus.OK
        );
    }

    @GetMapping("/getCompany/{companyName}")
    public ResponseEntity<Company> getCompanyByCompanyName(@PathVariable String companyName){
        return new ResponseEntity<>(
          companyService.getCompanyByCompanyName(companyName),
          HttpStatus.OK
        );
    }
}
