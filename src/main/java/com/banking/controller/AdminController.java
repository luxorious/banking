package com.banking.controller;

import com.banking.entity.*;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PutMapping("/restore-product-by-id/{id}")
    public Product restoreProductById(@PathVariable UUID id){
        return productService.restoreById(id);
    }
    public List<Product> restoreAllProducts(){
        return productService.restoreAll();
    }

    public List<Product> showAllDeletedProducts(){
        return productService.showAllDeleted();
    }

    public List<Product> showAllProductsForAdmin(){
        return productService.showAllProductsForAdmin();
    }

    public List<Manager> findManagersByCreatedAt(Timestamp dateCreation){
        return managerService.findManagersByCreatedAt(dateCreation);
    }
    public Manager restoreManagerById(UUID id){
        return managerService.restoreById(id);
    }

    public List<Manager> restoreAllManagers(){
        return managerService.restoreAll();
    }

    List<Manager> showAllDeleted(){
        return managerService.showAllDeleted();
    }

    public List<Manager> showAllManagersForAdmin(){
        return managerService.showAllManagersForAdmin();
    }

    Document createDocument(Document document){
        return documentService.create(document);
    }
    Document findDocumentByClientId(UUID id){
        return documentService.findDocumentByClientId(id);
    }

    Document findDocumentById(Integer id){
        return documentService.findDocumentById(id);
    }

    Document editDocument(UUID clientId, Document documentFE){
        return documentService.edit(clientId, documentFE);
    }

    Document editImageById(Integer id, MultipartFile image, boolean isPassport) throws IOException{
        return null;
    }


    List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus){
        return clientService.findClientsByDeletedStatus(deletedStatus);
    }

    Client restoreClientById(UUID id){
        return clientService.restoreById(id);
    }
    List<Client> restoreAllClients(){
        return clientService.restoreAll();
    }

    List<Agreement> showDeletedAgreements(){
        return agreementService.showDeleted();
    }

    List<Account> findAllDeletedAccounts(){
        return accountService.findAllDeleted();
    }

    List<Account> findAllAccountsForAdmin(){
        return accountService.findAllAccountsForAdmin();
    }

}
