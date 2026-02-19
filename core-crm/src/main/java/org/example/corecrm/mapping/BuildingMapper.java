package org.example.corecrm.mapping;

import org.example.corecrm.dto.building.BuildingCreateDto;
import org.example.corecrm.dto.building.BuildingDto;
import org.example.corecrm.entity.Building;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingMapper {
    public BuildingDto toDto(Building building) {
        return BuildingDto.builder()
                .id(building.getId())
                .cadastr(building.getCadastr())
                .type(building.getType())
                .square(building.getSquare())
                .price(building.getPrice())
                .source(building.getSource())
                .assignedManager(building.getAssignedManager())
                .build();
    }

    public Building toEntity(BuildingCreateDto building) {
        return Building.builder()
                .cadastr(building.getCadastr())
                .type(building.getType())
                .square(building.getSquare())
                .price(building.getPrice())
                .source(building.getSource())
                .assignedManager(building.getAssignedManager())
                .build();
    }

    public List<BuildingDto> toListDto(List<Building> list) {
        List<BuildingDto> result = new ArrayList<>(list.size());
        for (Building building : list) {
            result.add(toDto(building));
        }
        return result;
    }

}
