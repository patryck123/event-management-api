# 🎪 Event Management API

Gerenciamento de eventos com inscrições, controle de vagas e lista de espera.

## 📋 Sobre o Projeto

API para criar e gerenciar eventos (shows, workshops, conferências). Controla o número máximo de vagas, registra inscrições e cancela automaticamente vagas não confirmadas. Ideal para sistemas de ingressos e eventos corporativos.

## ✨ Funcionalidades

- ✅ Criar eventos com data, local e capacidade máxima
- ✅ Classificar eventos: CONFERENCE, WORKSHOP, SHOW, SPORTS
- ✅ Inscrever participante com validação de vagas
- ✅ Cancelar inscrição
- ✅ Controle automático de vagas disponíveis
- ✅ Listar participantes inscritos
- ✅ Listar eventos futuros
- ✅ Status do evento: UPCOMING, ONGOING, FINISHED, CANCELLED

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET/POST | `/api/events` | Listar / Criar evento |
| GET | `/api/events/upcoming` | Eventos futuros |
| GET | `/api/events/{id}/participants` | Listar inscritos |
| POST | `/api/events/{id}/register/{userId}` | Inscrever participante |
| DELETE | `/api/events/{id}/register/{userId}` | Cancelar inscrição |

## 🛠️ Tecnologias

- Java 17 · Spring Boot 3.2 · PostgreSQL · Maven · Lombok
