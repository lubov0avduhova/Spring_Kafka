## Real Estate Microservices System (Pet Project)
Система автоматизации риелторского агентства, построенная на микросервисной архитектуре с использованием Event-Driven Design. 
Проект демонстрирует навыки работы с высоконагруженными системами, очередями сообщений и распределенными транзакциями.

### Архитектура и модули
Система состоит из 5 независимых сервисов:

- content-loader-adapter (Port: 8082): Входная точка. Парсит XLSX файлы (Apache POI) и транслирует данные в Kafka.

- content-processor (Port: 8083): "Рабочая лошадка". Читает сырые данные из Kafka батчами, сопоставляет их и обновляет текущее состояние объектов недвижимости.

- price-history (Port: 8084): Аналитический модуль. Хранит историю изменения цен, используя композитные индексы для быстрой выборки.

- core-crm (Port: 8081): Мозг системы. Управление задачами, пользователями и объектами. Реализован Optimistic Lock для предотвращения конфликтов.

- sender-notification (Port: 8085): Сервис уведомлений. Демонстрирует надежность через Non-blocking Retry и DLT.

### Технологический стек
- Backend: Java 17, Spring Boot 3, Spring Data JPA

- Messaging: Apache Kafka (KRaft mode)

- Database: PostgreSQL

- Reliability: ShedLock (Cluster Scheduling), Spring Retry

- Documentation: Swagger UI (OpenAPI 3)

- Infrastructure: Docker, Kubernetes (Liveness/Readiness probes)
 
### Как запустить
1. #### Подготовка инфраструктуры
   Убедитесь, что у вас установлен Docker и запущен Docker Desktop. 
Из корневой директории проекта выполните:

```Bash
docker-compose up -d
```
Это поднимет PostgreSQL и Kafka Broker.

2. #### Запуск сервисов
   Вы можете запустить каждый модуль через IDEA или собрать их с помощью Maven:

```Bash
./mvnw clean package
```
Затем запустить jar файлы или воспользоваться профилями запуска в IDEA


### Ссылки для проверки (Swagger)
После запуска сервисов API документация доступна по адресам:

Core CRM: http://localhost:8081/swagger-ui/index.html

Content Loader: http://localhost:8082/swagger-ui/index.html