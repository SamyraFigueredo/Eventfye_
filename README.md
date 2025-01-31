# Eventfye 🎉  
**_Plataforma de Organização de Eventos_**

Bem-vindo ao **Eventfye**, a solução definitiva para organizar e gerenciar eventos de maneira prática e eficiente.

## 📝 Sobre o Projeto  

**Eventfye** é uma plataforma moderna e personalizável projetada para otimizar a gestão de eventos. Nosso objetivo é atender tanto organizadores quanto participantes, simplificando todo o processo.  

### Funcionalidades Principais 🚀  
- **Gerenciamento de Participantes e Palestrantes**  
- **Definição de Programação do Evento**  
- **Sistema de Inscrição Online**  
- **Notificações e Lembretes Personalizados**  
- **Emissão de Certificados com Autenticação Eletrônica**

## ⚙️ Tecnologias Utilizadas
### Backend
- **Java 22**
- **Spring Boot**
- **MySQL**
- **Keycloak (para autenticação e autorização)**

---

## 📊 Modelo ER (Entidade-Relacionamento)

Abaixo está o diagrama ER que utilizamos para modelar o banco de dados da plataforma **Eventfye**. Este modelo foi desenvolvido para atender às necessidades de gerenciamento de eventos, participantes e certificados, garantindo integridade e eficiência.

![0e558c4db566204250a2b2f0446021b2](https://github.com/user-attachments/assets/76a5633d-808d-4ad3-9265-145e3f369055)

### Descrição do Modelo
- **Usuários**: Representa os organizadores e participantes cadastrados na plataforma.
- **Eventos**: Contém informações sobre os eventos organizados, como título, descrição, e data.
- **Inscrições**: Relação entre participantes e eventos, permitindo o registro e controle de presença.
- **Palestrantes**: Usuários associados a eventos com o papel de palestrar.
- **Certificados**: Gerados para os participantes com autenticação eletrônica.
