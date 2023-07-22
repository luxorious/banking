package com.banking.controller;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.ManagerStatus;
import com.banking.entity.entityenumerations.Role;
import com.banking.service.interfaces.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing managers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    /**
     * Create a new manager.
     *
     * @param manager The manager object to be created.
     * @param role    The role of the manager.
     * @return The created manager.
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Manager createManager(@RequestBody Manager manager, Role role) {
        return managerService.createManager(manager, role);
    }

    /**
     * Find a manager by their ID.
     *
     * @param id The ID of the manager to find.
     * @return An optional containing the manager if found, or an empty optional otherwise.
     */
    @GetMapping("/find/{id}")
    public Optional<Manager> findById(@PathVariable UUID id) {
        return managerService.findById(id);
    }

    /**
     * Find a manager by their first name and last name.
     *
     * @param firstName The first name of the manager.
     * @param lastName  The last name of the manager.
     * @return An optional containing the manager if found, or an empty optional otherwise.
     */
    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Manager> findManagerByFirstNameAndLastName(
            @RequestParam String firstName, @RequestParam String lastName) {
        return managerService.findManagerByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Find managers with the provided status.
     *
     * @param status The status to filter the managers.
     * @return A list of managers that match the provided status.
     */
    @GetMapping("/find/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> findManagersByStatus(@PathVariable ManagerStatus status) {
        return managerService.findManagersByStatus(status);
    }

    /**
     * Find all managers.
     *
     * @return A list of all existing managers.
     */
    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> findAll() {
        return managerService.findAll();
    }

    /**
     * Update a manager with the provided ID.
     *
     * @param id            The ID of the manager to update.
     * @param managerFromFE The updated manager object.
     * @return true if the update was successful, false otherwise.
     */
    @PutMapping("/edit/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateManagerById(@PathVariable UUID id, @RequestBody Manager managerFromFE) {
        return managerService.updateManagerById(id, managerFromFE);
    }

    /**
     * Update the status of a manager with the provided ID.
     *
     * @param id     The ID of the manager to update.
     * @param status The new status for the manager.
     * @return The manager with the updated status.
     */
    @PutMapping("/edit/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager updateStatusById(@PathVariable UUID id, @RequestParam ManagerStatus status) {
        return managerService.updateStatusById(id, status);
    }

    /**
     * Delete a manager by their ID.
     *
     * @param id The ID of the manager to delete.
     * @return The deleted manager.
     */
    @DeleteMapping("/delete/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager deleteManagerById(@PathVariable UUID id) {
        return managerService.deleteManagerById(id);
    }

    /**
     * Delete managers with the provided status.
     *
     * @param status The status to filter the managers to be deleted.
     * @return A list of managers that were deleted.
     */
    @DeleteMapping("/delete/by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> deleteManagersByStatus(@PathVariable ManagerStatus status) {
        return managerService.deleteManagersByStatus(status);
    }
}
