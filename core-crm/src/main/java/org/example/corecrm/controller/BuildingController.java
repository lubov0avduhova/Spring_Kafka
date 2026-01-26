package org.example.corecrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.entity.Building;
import org.example.corecrm.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("/{id}")
    //todo заменить на dto
    public Building getBuildingById(@PathVariable(name = "id") Long id) {
        return buildingService.getBuildingById(id);
    }

    @GetMapping
    //todo заменить на dto
    public List<Building> getAllBuilding() {
        return buildingService.findAllBuilding();
    }

    @PostMapping
    //todo заменить на dto
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        return new ResponseEntity<>(buildingService.createBuilding(building), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //todo заменить на dto
    public Building updateBuilding(@PathVariable(name = "id") Long id, @RequestBody Building building) {
        return buildingService.updateBuilding(id, building);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBuilding(@PathVariable(name = "id") Long id) {
        buildingService.deleteBuilding(id);
    }
}