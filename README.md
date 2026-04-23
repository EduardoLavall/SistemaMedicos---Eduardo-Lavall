# Sistema Medicos API

API em `Java + Spring Boot + Spring Data JPA + MySQL` para cadastro de:

- pacientes
- medicos
- consultas

## Requisitos

Antes de rodar o projeto, confirme se voce tem:

- `Java` instalado
- `MySQL` rodando
- `IntelliJ IDEA` ou `Maven`

## Banco usado no projeto

Configuracao atual do projeto:

- banco: `sistema_medicos`
- usuario: `root`
- senha: `laboratorio`
- porta: `3306`

## 1. Criar o banco

No MySQL, rode:

```sql
CREATE DATABASE sistema_medicos;
```

Se quiser conferir:

```sql
SHOW DATABASES;
```

## 2. Conferir o application.properties

Arquivo:

[`src/main/resources/application.properties`](</c:/Users/laboratorio/Desktop/SistemaMedicos - Eduardo Lavall/src/main/resources/application.properties>)

Configuracao esperada:

```properties
spring.application.name=sistema-medicos
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/sistema_medicos
spring.datasource.username=root
spring.datasource.password=laboratorio
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## 3. Rodar pelo IntelliJ

1. Abra a pasta do projeto no IntelliJ.
2. Espere o IntelliJ reconhecer o `pom.xml`.
3. Abra a classe:

[`SistemaMedicosApplication.java`](</c:/Users/laboratorio/Desktop/SistemaMedicos - Eduardo Lavall/src/main/java/br/com/sistemamedicos/SistemaMedicosApplication.java>)

4. Clique no botao verde de `Run` ao lado do metodo `main`.

Se o IntelliJ pedir para importar dependencias Maven, aceite.

## 4. Rodar pelo terminal com Maven

Se o Maven estiver instalado no Windows:

```powershell
cd "C:\Users\laboratorio\Desktop\SistemaMedicos - Eduardo Lavall"
mvn spring-boot:run
```

## 5. Testar se a API subiu

Teste no navegador ou no Postman:

```text
GET http://localhost:8080/api/hello
```

Resposta esperada:

```json
{
  "message": "Hello world",
  "project": "Sistema Medicos API"
}
```

## 6. Conferir se as tabelas foram criadas

No MySQL:

```sql
USE sistema_medicos;
SHOW TABLES;
```

As tabelas esperadas sao:

- `pacientes`
- `medicos`
- `consultas`

## 7. Endpoints principais

### Pacientes

- `POST /api/pacientes`
- `GET /api/pacientes`
- `GET /api/pacientes/{id}`
- `PUT /api/pacientes/{id}`
- `DELETE /api/pacientes/{id}`

### Medicos

- `POST /api/medicos`
- `GET /api/medicos`
- `GET /api/medicos/{id}`
- `PUT /api/medicos/{id}`
- `DELETE /api/medicos/{id}`

### Consultas

- `POST /api/consultas`
- `GET /api/consultas`
- `GET /api/consultas/{id}`
- `PUT /api/consultas/{id}`
- `DELETE /api/consultas/{id}`

## 8. Exemplo de JSON

### Paciente

```json
{
  "name": "Joao da Silva",
  "email": "joao@email.com",
  "cpf": "123.456.789-00"
}
```

### Medico

```json
{
  "name": "Dra Mariana Souza",
  "speciality": "Cardiologia",
  "crm": "CRM12345"
}
```

### Consulta

```json
{
  "patientId": 1,
  "doctorId": 1,
  "appointmentDate": "2026-12-10T14:30:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Paciente retornara com exames"
}
```

## 9. Observacao

Se a aplicacao subir mas algum `POST` falhar no Postman com erro `415 Unsupported Media Type`, confira se voce esta enviando:

- `Body -> raw`
- tipo `JSON`
- header `Content-Type: application/json`
