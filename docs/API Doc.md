# Transaction Concert API

## Get Available Concerts

- Endpoint : GET /api/concerts

Request Header :
- page : 0
- size : 5
- concertName : cold

Response Body (Success)
```json
{
  "data": [
    {
      "id": 187,
      "concertName": "Coldplay 5",
      "concertPlace": "Jakarta",
      "description": "Coldplay Tour",
      "concertDetail": [
        {
          "id": 46,
          "concertDate": "08-09-2023",
          "concertBegTime": "19:35",
          "price": 1.0E7,
          "ticketsAmount": 1000
        }
      ]
    },
    {
      "id": 188,
      "concertName": "Coldplay 6",
      "concertPlace": "Jakarta",
      "description": "Coldplay Tour",
      "concertDetail": [
        {
          "id": 47,
          "concertDate": "08-09-2023",
          "concertBegTime": "19:35",
          "price": 1.0E7,
          "ticketsAmount": 1000
        }
      ]
    },
    {
      "id": 189,
      "concertName": "Coldplay 7",
      "concertPlace": "Jakarta",
      "description": "Coldplay Tour",
      "concertDetail": [
        {
          "id": 48,
          "concertDate": "08-09-2023",
          "concertBegTime": "19:35",
          "price": 1.0E7,
          "ticketsAmount": 1000
        }
      ]
    },
    {
      "id": 190,
      "concertName": "Coldplay 8",
      "concertPlace": "Jakarta",
      "description": "Coldplay Tour",
      "concertDetail": [
        {
          "id": 49,
          "concertDate": "08-09-2023",
          "concertBegTime": "19:35",
          "price": 1.0E7,
          "ticketsAmount": 1000
        }
      ]
    },
    {
      "id": 191,
      "concertName": "Coldplay 9",
      "concertPlace": "Jakarta",
      "description": "Coldplay Tour",
      "concertDetail": [
        {
          "id": 50,
          "concertDate": "08-09-2023",
          "concertBegTime": "19:35",
          "price": 1.0E7,
          "ticketsAmount": 1000
        }
      ]
    }
  ],
  "paging": {
    "currentPage": 1,
    "pageSize": 5,
    "totalPages": 4,
    "totalRecords": 20
  }
}
```

## Submit Transaction

- Endpoint : POST /api/transaction

Request Body :
```json
{
  "concertDetailId" : "concert_detail_id",
  "userId" : "user_id",
  "ticketAmount" : 2,
  "totalPayment" : 100000.00,
  "methodPayment" : "Transfer"
}
```

Response Body (Success)
```json
{
  "data" : {
    "id" : "id",
    "transactionDate" : "08-09-2023",
    "concertDetailId" : {
      "id": 41,
      "concertDate": "08-09-2023",
      "concertBegTime": "19:35",
      "price": 1.0E7,
      "ticketsAmount": 994
    },
    "userId" : {
      "id": 1,
      "username": "user001",
      "email": "user@gmail.com"
    },
    "ticketAmount" : 2,
    "totalPayment" : 100000.00,
    "statusPayment" : "Paid",
    "methodPayment" : "Transfer"    
  }
}
```

Response Body (Failed)
```json
{
  "errors" : "user not found, tickets sold, ...."
}
```

## Pay Transaction

- Endpoint : PUT /api/transaction/pay/{id}

Response Body (Success)
```json
{
  "data": {
    "id": 4,
    "transactionDate": "08-09-2023",
    "concertDetail": {
      "id": 41,
      "concertDate": "08-09-2023",
      "concertBegTime": "19:35",
      "price": 1.0E7,
      "ticketsAmount": 994
    },
    "user": {
      "id": 1,
      "username": "user001",
      "email": "user@gmail.com"
    },
    "ticketAmount": 1,
    "totalPayment": 1.0E7,
    "statusPayment": "Paid",
    "methodPayment": "Transfer"
  }
}
```

Response Body (Failed)
```json
{
  "error": "Transaction Not Found, Transaction Already Cancelled ,...."
}
```

## Cancel Transaction

- Endpoint : PUT /api/transaction/cancel/{id}

Response Body (Success)
```json
{
  "data": {
    "id": 6,
    "transactionDate": "08-09-2023",
    "concertDetail": {
      "id": 41,
      "concertDate": "08-09-2023",
      "concertBegTime": "19:35",
      "price": 1.0E7,
      "ticketsAmount": 996
    },
    "user": {
      "id": 1,
      "username": "user001",
      "email": "user@gmail.com"
    },
    "ticketAmount": 2,
    "totalPayment": 200000.0,
    "statusPayment": "Cancelled",
    "methodPayment": "Transfer"
  }
}
```

Response Body (Failed)
```json
{
  "error": "Transaction Not Found, Transaction Already Cancelled ,...."
}
```
