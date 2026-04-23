# Lista de Testes - Sistema Medicos API

Este arquivo junta os testes principais do projeto, tanto de CRUD quanto de regras de negocio.

## Base da API

- URL base: `http://localhost:8080`
- Hello: `GET /api/hello`

Resposta esperada:

```json
{
  "message": "Hello world",
  "project": "Sistema Medicos API"
}
```

---

## 1. Testes de Paciente

### 1.1 Criar paciente com dados validos

- Metodo: `POST`
- Endpoint: `/api/pacientes`

Body:

```json
{
  "name": "Joao da Silva",
  "email": "joao@email.com",
  "cpf": "123.456.789-00"
}
```

Esperado:

- status `201`
- retorna `id`, `name`, `email`, `cpf`
- salva no banco

### 1.2 Listar pacientes

- Metodo: `GET`
- Endpoint: `/api/pacientes`

Esperado:

- status `200`
- lista com os pacientes cadastrados

### 1.3 Buscar paciente por id

- Metodo: `GET`
- Endpoint: `/api/pacientes/{id}`

Esperado:

- status `200`
- retorna o paciente certo

### 1.4 Atualizar paciente

- Metodo: `PUT`
- Endpoint: `/api/pacientes/{id}`

Body:

```json
{
  "name": "Joao Atualizado",
  "email": "joao.atualizado@email.com",
  "cpf": "123.456.789-00"
}
```

Esperado:

- status `200`
- retorna dados atualizados
- altera no banco

### 1.5 Deletar paciente

- Metodo: `DELETE`
- Endpoint: `/api/pacientes/{id}`

Esperado:

- status `204`
- remove do banco

### 1.6 Tentar buscar paciente deletado

- Metodo: `GET`
- Endpoint: `/api/pacientes/{id}`

Esperado:

- erro
- mensagem de paciente nao encontrado

### 1.7 Criar paciente com campo faltando

- Metodo: `POST`
- Endpoint: `/api/pacientes`

Body:

```json
{
  "email": "teste@email.com",
  "cpf": "111.222.333-44"
}
```

Esperado:

- erro de validacao

### 1.8 Criar paciente com email invalido

- Metodo: `POST`
- Endpoint: `/api/pacientes`

Body:

```json
{
  "name": "Teste",
  "email": "email-invalido",
  "cpf": "111.222.333-44"
}
```

Esperado:

- erro de validacao

---

## 2. Testes de Medico

### 2.1 Criar medico com dados validos

- Metodo: `POST`
- Endpoint: `/api/medicos`

Body:

```json
{
  "name": "Dra Mariana Souza",
  "speciality": "Cardiologia",
  "crm": "CRM12345"
}
```

Esperado:

- status `201`
- retorna `id`, `name`, `speciality`, `crm`
- salva no banco

### 2.2 Listar medicos

- Metodo: `GET`
- Endpoint: `/api/medicos`

Esperado:

- status `200`
- lista de medicos

### 2.3 Buscar medico por id

- Metodo: `GET`
- Endpoint: `/api/medicos/{id}`

Esperado:

- status `200`
- retorna o medico certo

### 2.4 Atualizar medico

- Metodo: `PUT`
- Endpoint: `/api/medicos/{id}`

Body:

```json
{
  "name": "Dra Mariana Atualizada",
  "speciality": "Cardiologia",
  "crm": "CRM12345"
}
```

Esperado:

- status `200`
- altera no banco

### 2.5 Deletar medico

- Metodo: `DELETE`
- Endpoint: `/api/medicos/{id}`

Esperado:

- status `204`
- remove do banco

### 2.6 Criar medico com campo faltando

- Metodo: `POST`
- Endpoint: `/api/medicos`

Body:

```json
{
  "name": "Teste",
  "crm": "CRM99999"
}
```

Esperado:

- erro de validacao

---

## 3. Testes de Consulta - CRUD base

Observacao:

- para criar consulta, precisa existir paciente e medico validos no banco

### 3.1 Criar consulta valida

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T10:00:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Consulta valida"
}
```

Esperado:

- status `201`
- retorna `patient` e `doctor` dentro do DTO
- salva no banco

### 3.2 Listar consultas

- Metodo: `GET`
- Endpoint: `/api/consultas`

Esperado:

- status `200`
- lista com consultas cadastradas

### 3.3 Buscar consulta por id

- Metodo: `GET`
- Endpoint: `/api/consultas/{id}`

Esperado:

- status `200`
- retorna a consulta correta

### 3.4 Atualizar consulta

- Metodo: `PUT`
- Endpoint: `/api/consultas/{id}`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T11:00:00",
  "status": "REALIZADA",
  "price": 300.00,
  "notes": "Consulta atualizada"
}
```

Esperado:

- status `200`
- altera no banco

### 3.5 Deletar consulta

- Metodo: `DELETE`
- Endpoint: `/api/consultas/{id}`

Esperado:

- status `204`
- remove do banco

---

## 4. Testes de Regras de Negocio da Consulta

### 4.1 Paciente inexistente

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 9999,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T10:00:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Paciente invalido"
}
```

Esperado:

- erro
- mensagem `Paciente nao encontrado`
- nao salva no banco

### 4.2 Medico inexistente

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 9999,
  "appointmentDate": "2026-12-20T10:00:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Medico invalido"
}
```

Esperado:

- erro
- mensagem `Medico nao encontrado`
- nao salva no banco

### 4.3 Status obrigatorio

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T10:00:00",
  "price": 250.00,
  "notes": "Sem status"
}
```

Esperado:

- status `400`
- mensagem `Status da consulta e obrigatorio`

### 4.4 Price igual a zero

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T10:00:00",
  "status": "AGENDADA",
  "price": 0,
  "notes": "Preco zerado"
}
```

Esperado:

- erro
- mensagem `O valor da consulta deve ser maior que zero`
- nao salva no banco

### 4.5 Price negativo

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-20T10:00:00",
  "status": "AGENDADA",
  "price": -10,
  "notes": "Preco negativo"
}
```

Esperado:

- erro
- mensagem `O valor da consulta deve ser maior que zero`
- nao salva no banco

### 4.6 Data no passado

- Metodo: `POST`
- Endpoint: `/api/consultas`

Body:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2020-01-10T14:30:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Data no passado"
}
```

Esperado:

- erro
- mensagem `Nao e permitido agendar consulta no passado`
- nao salva no banco

### 4.7 Mesmo medico no msm horario

Teste 1:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-21T09:00:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Primeira consulta"
}
```

Teste 2:

```json
{
  "patientId": 3,
  "doctorId": 2,
  "appointmentDate": "2026-12-21T09:00:00",
  "status": "AGENDADA",
  "price": 300.00,
  "notes": "Conflito de horario do medico"
}
```

Esperado no teste 2:

- erro
- mensagem `O medico ja possui consulta nesse horario`

### 4.8 Mesmo paciente no msm horario

Teste 1:

```json
{
  "patientId": 2,
  "doctorId": 2,
  "appointmentDate": "2026-12-21T10:00:00",
  "status": "AGENDADA",
  "price": 250.00,
  "notes": "Primeira consulta"
}
```

Teste 2:

```json
{
  "patientId": 2,
  "doctorId": 3,
  "appointmentDate": "2026-12-21T10:00:00",
  "status": "AGENDADA",
  "price": 300.00,
  "notes": "Conflito de horario do paciente"
}
```

Esperado no teste 2:

- erro
- mensagem `O paciente ja possui consulta nesse horario`

### 4.9 Mesmo paciente com horario diferente

- deve passar

### 4.10 Mesmo medico com horario diferente

- deve passar

---

## 5. Testes no Banco

Sempre que quiser validar persistencia, use:

```sql
USE sistema_medicos;
SELECT * FROM pacientes;
SELECT * FROM medicos;
SELECT * FROM consultas;
```

Coisas pra observar:

- se o `POST` salvou
- se o `PUT` alterou
- se o `DELETE` removeu
- se operacao invalida nao deixou lixo no banco

---

## 6. Lista curta pra prova

Se estiver sem tempo e precisar provar que o sistema esta funcionando, os testes minimos mais importantes sao:

1. `POST /api/pacientes`
2. `GET /api/pacientes`
3. `POST /api/medicos`
4. `GET /api/medicos`
5. `POST /api/consultas`
6. `GET /api/consultas`
7. consulta com `patientId` invalido
8. consulta com `doctorId` invalido
9. consulta sem `status`
10. consulta com `price = 0`
11. consulta com data no passado
12. consulta repetida pro mesmo medico no msm horario
13. consulta repetida pro mesmo paciente no msm horario
