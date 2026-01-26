package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.corecrm.entity.Building;
import org.example.corecrm.exception.BuildingNotFoundException;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.repository.BuildingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(id));
    }

    public List<Building> findAllBuilding() {
        return buildingRepository.findAll();
    }

    public Building createBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Building updateBuilding(Long id, Building building) {
        return buildingRepository.findById(id)
                .map(existingBuilding -> {
                    existingBuilding.setCadastr(building.getCadastr());
                    existingBuilding.setType(building.getType());
                    existingBuilding.setSquare(building.getSquare());
                    existingBuilding.setPrice(building.getPrice());
                    existingBuilding.setSource(building.getSource());

                    return buildingRepository.save(existingBuilding);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }


    //todo реализовать assignToManager(Long estateId, String managerName)

    @Scheduled(cron = "0 0 9 * * MON") // Каждый понедельник в 9 утра
    @SchedulerLock(name = "createAutoTasks", lockAtMostFor = "10m", lockAtLeastFor = "1m")
    public void createTasks() {
        log.info("Создаю автоматические задания для менеджеров...");
        // Логика поиска объектов без задач и создание уведомлений
    }
}
