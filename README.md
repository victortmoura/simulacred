# Loan Simulation API

A RESTful API built with Java 17 and Spring Boot for simulating loans. 
The API calculates monthly payments, total payments, and total interest based on the loan amount,
the customer's age (derived from their birthdate), and the payment term in months.

## Features
- Calculates loan payment simulations using custom interest rates based on age groups.
- Implements the Strategy design pattern for interest rate calculations.
- Provides concise and readable API documentation using Swagger.
---

## Setup Instructions

### Prerequisites
- Java 17
- Maven 3.8+

### Steps to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/victortmoura/simulacred.git
   cd simulacred
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the Swagger UI:
   Open your browser and navigate to: `http://localhost:8080/swagger-ui.html`

## API Documentation

### **Simulate Loan**

**Endpoint:**
```
POST /api/v1/simulate
```

**Request Payload:**
```json
{
    "loanAmount": 10000,
    "birthDate": "1994-08-12",
    "paymentTerm": 10
}
```

**Response Payload:**
```json
{
    "monthlyPayment": 1010.45,
    "totalPayment": 12125.40,
    "totalInterest": 2125.40
}
```

## Example Usage

### **cURL Example:**
```bash
curl -X POST http://localhost:8080/api/v1/simulate \
-H "Content-Type: application/json" \
-d '{
  "loanAmount": 20000,
  "birthDate": "1985-07-15",
  "paymentTerm": 24
}'
```
