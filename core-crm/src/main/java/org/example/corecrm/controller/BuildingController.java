package org.example.corecrm.controller;

import lombok.RequiredArgsConstructor;
import org.example.corecrm.dto.building.BuildingCreateDto;
import org.example.corecrm.dto.building.BuildingDto;
import org.example.corecrm.dto.building.BuildingUpdateDto;
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
    public BuildingDto getBuildingById(@PathVariable(name = "id") Long id) {
        return buildingService.getBuildingById(id);
    }

    @GetMapping
    public List<BuildingDto> getAllBuilding() {
        return buildingService.findAllBuilding();
    }

    @PostMapping
    public ResponseEntity<BuildingDto> createBuilding(@RequestBody BuildingCreateDto building) {
        return new ResponseEntity<>(buildingService.createBuilding(building), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public BuildingDto updateBuilding(@PathVariable(name = "id") Long id, @RequestBody BuildingUpdateDto building) {
        return buildingService.updateBuilding(id, building);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBuilding(@PathVariable(name = "id") Long id) {
        buildingService.deleteBuilding(id);
    }
}