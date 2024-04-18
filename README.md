# Desafio de Projeto
Java RESTful API criada para o Java AI Powered

Projeto: Sistema de Gestão de Saúde

```mermaid
classDiagram
    class HealthManagementApp {
        - String appName
        - String description
        - List<String> targetAudience
        - HealthResources resources
        - boolean dataSecurity
        - List<String> integrations
        - String status
        - Date releaseDate
        - List<String> partnerships
        - String marketingStrategy
    }

    class HealthResources {
        - boolean activityTracking
        - boolean dietRecording
        - boolean medicationRecording
        - boolean reminders
        - boolean appointmentScheduling
        - boolean healthInformationAccess
    }

    HealthManagementApp --> HealthResources
```
