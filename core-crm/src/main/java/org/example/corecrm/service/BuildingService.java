package org.example.corecrm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.corecrm.dto.building.BuildingCreateDto;
import org.example.corecrm.dto.building.BuildingDto;
import org.example.corecrm.dto.building.BuildingUpdateDto;
import org.example.corecrm.entity.Building;
import org.example.corecrm.entity.Task;
import org.example.corecrm.entity.User;
import org.example.corecrm.exception.BuildingNotFoundException;
import org.example.corecrm.exception.TaskNotFoundException;
import org.example.corecrm.exception.UserNotFoundException;
import org.example.corecrm.mapping.BuildingMapper;
import org.example.corecrm.repository.BuildingRepository;
import org.example.corecrm.repository.TaskRepository;
import org.example.corecrm.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private final BuildingMapper mapper;

    public BuildingDto getBuildingById(Long id) {
        return mapper.toDto(buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(id)));
    }

    public List<BuildingDto> findAllBuilding() {
        return mapper.toListDto(buildingRepository.findAll());
    }

    public BuildingDto createBuilding(BuildingCreateDto building) {
        Task task = taskRepository.findById(building.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(building.getTaskId()));

        User user = userRepository.findById(building.getUserId())
                .orElseThrow(() -> new UserNotFoundException(building.getUserId()));

        Building entity = mapper.toEntity(building);
        entity.setUser(user);
        entity.setTasks(List.of(task));

        return mapper.toDto(buildingRepository.save(entity));
    }

    public BuildingDto updateBuilding(Long id, BuildingUpdateDto dto) {
        return mapper.toDto(buildingRepository.findById(id)
                .map(existingBuilding ->
                        buildingRepository.save(updateByDto(dto))
                )
                .orElseThrow(() -> new BuildingNotFoundException(id)));
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


    private Building updateByDto(BuildingUpdateDto dto) {
        Building building = new Building();
        building.setCadastr(dto.getCadastr());
        building.setType(dto.getType());
        building.setSquare(dto.getSquare());
        building.setPrice(dto.getPrice());
        building.setSource(dto.getSource());
        return building;
    }
}
