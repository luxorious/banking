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

/**
 * Controller for handling administrative actions in the banking application.
 */
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

    /**
     * Restore a previously deleted product by its ID.
     *
     * @param id The ID of the product to restore.
     * @return The restored product, or null if no product was found with the given ID.
     */
    @PutMapping("/products/restore-by-id/{id}")
    public Product restoreProductById(@PathVariable UUID id){
        return productService.restoreById(id);
    }

    /**
     * Restore all previously deleted products.
     *
     * @return A list of restored products, or an empty list if no deleted products were found.
     */
    @GetMapping("/products/restore-all")
    public List<Product> restoreAllProducts(){
        return productService.restoreAll();
    }

    /**
     * Get a list of all deleted products.
     *
     * @return A list of deleted products, or an empty list if no products were deleted.
     */
    @GetMapping("/products/show-all-deleted")
    public List<Product> showAllDeletedProducts(){
        return productService.showAllDeleted();
    }

    /**
     * Get a list of all products for administrative purposes.
     *
     * @return A list of products for administrative use, or an empty list if no products were found.
     */
    @GetMapping("/products/show-all")
    public List<Product> showAllProductsForAdmin(){
        return productService.showAllProductsForAdmin();
    }

    /**
     * Get a list of managers created on a specific date.
     *
     * @param dateCreation The creation date to filter the managers.
     * @return A list of managers created on the given date, or an empty list if none were found.
     */
    @GetMapping("/managers/find0-by-date-creation")
    public List<Manager> findManagersByCreatedAt(Timestamp dateCreation){
        return managerService.findManagersByCreatedAt(dateCreation);
    }

    /**
     * Restore a previously deleted manager by their ID.
     *
     * @param id The ID of the manager to restore.
     * @return The restored manager, or null if no manager was found with the given ID.
     */
    @GetMapping("/managers/restore-by-id/{id}")
    public Manager restoreManagerById(@PathVariable UUID id){
        return managerService.restoreById(id);
    }

    /**
     * Restore all previously deleted managers.
     *
     * @return A list of restored managers, or an empty list if no deleted managers were found.
     */
    @PutMapping("/managers/restore-all")
    public List<Manager> restoreAllManagers(){
        return managerService.restoreAll();
    }

    /**
     * Get a list of all deleted managers.
     *
     * @return A list of deleted managers, or an empty list if no managers were deleted.
     */
    @GetMapping("/managers/show-all-deleted")
    List<Manager> showAllDeleted(){
        return managerService.showAllDeleted();
    }


    /**
     * Get a list of all managers for administrative purposes.
     *
     * @return A list of managers for administrative use, or an empty list if no managers were found.
     */
    @GetMapping("/managers/show-all")
    public List<Manager> showAllManagersForAdmin(){
        return managerService.showAllManagersForAdmin();
    }

    /**
     * Create a new document associated with the provided client ID.
     *
     * @param document The document entity to create.
     * @param clientId The ID of the client associated with the document.
     * @return The created document entity.
     */
    @PostMapping("/document/create/{clientId}")
    Document createDocument(@RequestBody Document document, @PathVariable UUID clientId){
        return documentService.create(document, clientId);
    }

    /**
     * Find a document by its ID associated with the provided client ID.
     *
     * @param id The ID of the document to find.
     * @return The found document entity, or null if no document was found with the given ID.
     */
    @GetMapping("/document/find-by-id/{id}")
    Document findDocumentByClientId(@PathVariable UUID id){
        return documentService.findDocumentByClientId(id);
    }

    /**
     * Edit an existing document associated with the provided client ID using the updated document details.
     *
     * @param clientId The ID of the client associated with the document to edit.
     * @param documentFE The updated document details received from the front-end.
     * @return The edited document entity, or null if no document was found with the given client ID.
     */
    @PutMapping("/document/edit/{clientId}")
    Document editDocument(@PathVariable UUID clientId, @RequestBody Document documentFE){
        return documentService.edit(clientId, documentFE);
    }

    /**
     * Find all clients with the provided deleted status.
     *
     * @param deletedStatus The deleted status to filter the clients.
     * @return A list of clients that match the provided deleted status.
     */
    @GetMapping("/clients/find-active-not-active")
    List<Client> findClientsByDeletedStatus(@RequestParam DeletedStatus deletedStatus){
        return clientService.findClientsByDeletedStatus(deletedStatus);
    }

    /**
     * Restore a previously deleted client by its ID.
     *
     * @param id The ID of the client to restore.
     * @return The restored client, or null if no client was found with the given ID.
     */
    @PutMapping("/clients/restore-by-id/{id}")
    Client restoreClientById(@PathVariable UUID id){
        return clientService.restoreById(id);
    }

    /**
     * Restore all previously deleted clients.
     *
     * @return A list of restored clients, or an empty list if no deleted clients were found.
     */
    @PutMapping("/clients/restore-all")
    List<Client> restoreAllClients(){
        return clientService.restoreAll();
    }

    /**
     * Get a list of all deleted agreements.
     *
     * @return A list of deleted agreements, or an empty list if no agreements were deleted.
     */
    @GetMapping("/agreements/show-deleted")
    List<Agreement> showDeletedAgreements(){
        return agreementService.showDeleted();
    }

    /**
     * Get a list of all deleted accounts.
     *
     * @return A list of deleted accounts, or an empty list if no accounts were deleted.
     */
    @GetMapping("/accounts/find-deleted")
    List<Account> findAllDeletedAccounts(){
        return accountService.findAllDeleted();
    }

    /**
     * Get a list of all accounts for administrative purposes.
     *
     * @return A list of accounts for administrative use, or an empty list if no accounts were found.
     */
    @GetMapping("/accounts/find-all")
    List<Account> findAllAccountsForAdmin(){
        return accountService.findAllAccountsForAdmin();
    }

}
