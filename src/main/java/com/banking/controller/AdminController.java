package com.banking.controller;

import com.banking.entity.*;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final AccountService accountService;
    private final AgreementService agreementService;
    private final ClientService clientService;
    private final ManagerService managerService;
    private final DocumentService documentService;

    @PutMapping("/products/restore-by-id/{id}")
    public Product restoreProductById(@PathVariable UUID id){
        return productService.restoreById(id);
    }

    @GetMapping("/products/restore-all")
    public List<Product> restoreAllProducts(){
        return productService.restoreAll();
    }

    @GetMapping("/products/show-all-deleted")
    public List<Product> showAllDeletedProducts(){
        return productService.showAllDeleted();
    }

    @GetMapping("/products/show-all")
    public List<Product> showAllProductsForAdmin(){
        return productService.showAllProductsForAdmin();
    }

    @GetMapping("/managers/find0-by-date-creation")
    public List<Manager> findManagersByCreatedAt(Timestamp dateCreation){
        return managerService.findManagersByCreatedAt(dateCreation);
    }

    @GetMapping("/managers/restore-by-id/{id}")
    public Manager restoreManagerById(@PathVariable UUID id){
        return managerService.restoreById(id);
    }

    @PutMapping("/managers/restore-all")
    public List<Manager> restoreAllManagers(){
        return managerService.restoreAll();
    }

    @GetMapping("/managers/show-all-deleted")
    List<Manager> showAllDeleted(){
        return managerService.showAllDeleted();
    }

    @GetMapping("/managers/show-all")
    public List<Manager> showAllManagersForAdmin(){
        return managerService.showAllManagersForAdmin();
    }

    @PostMapping("/document/create/{clientId}")
    Document createDocument(@RequestBody Document document, @PathVariable UUID clientId){
        return documentService.create(document, clientId);
    }

    @GetMapping("/document/find-by-id/{id}")
    Document findDocumentByClientId(@PathVariable UUID id){
        return documentService.findDocumentByClientId(id);
    }

    @PutMapping("/document/edit/{clientId}")
    Document editDocument(@PathVariable UUID clientId, @RequestBody Document documentFE){
        return documentService.edit(clientId, documentFE);
    }


    @GetMapping("/clients/find-active-not-active")
    List<Client> findClientsByDeletedStatus(@RequestParam DeletedStatus deletedStatus){
        return clientService.findClientsByDeletedStatus(deletedStatus);
    }

    @PutMapping("/clients/restore-by-id/{id}")
    Client restoreClientById(@PathVariable UUID id){
        return clientService.restoreById(id);
    }

    @PutMapping("/clients/restore-all")
    List<Client> restoreAllClients(){
        return clientService.restoreAll();
    }

    @GetMapping("/agreements/show-deleted")
    List<Agreement> showDeletedAgreements(){
        return agreementService.showDeleted();
    }

    @GetMapping("/accounts/find-deleted")
    List<Account> findAllDeletedAccounts(){
        return accountService.findAllDeleted();
    }

    @GetMapping("/accounts/find-all")
    List<Account> findAllAccountsForAdmin(){
        return accountService.findAllAccountsForAdmin();
    }

}
