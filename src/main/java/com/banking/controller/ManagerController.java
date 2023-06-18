package com.banking.controller;

import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.ManagerStatus;
import com.banking.service.interfaces.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Manager createManager(@RequestBody Manager manager){
        return managerService.createManager(manager);
    }

    @GetMapping("/find/{id}")
    public Optional<Manager> findById(@PathVariable UUID id){
        return managerService.findById(id);
    }

    @GetMapping("/find/full-name/")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Manager> findManagerByFirstNameAndLastName(
            @RequestParam String firstName, @RequestParam String lastName){
        return managerService.findManagerByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/find/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> findManagersByStatus(@PathVariable ManagerStatus status){
        return managerService.findManagersByStatus(status);
    }

    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> findAll(){
        return managerService.findAll();
    }

    @PutMapping("/edit/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateManagerById(@PathVariable UUID id, @RequestBody Manager managerFromFE){
        return managerService.updateManagerById(id, managerFromFE);
    }
    @PutMapping("/edit/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager updateStatusById(@PathVariable UUID id, @RequestParam ManagerStatus status){
        return managerService.updateStatusById(id, status);
    }

    @DeleteMapping("/delete/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager deleteManagerById(@PathVariable UUID id){
        return managerService.deleteManagerById(id);
    }

    @DeleteMapping("/delete/by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Manager> deleteManagersByStatus(@PathVariable ManagerStatus status){
        return managerService.deleteManagersByStatus(status);
    }
}
