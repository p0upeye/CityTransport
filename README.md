## 🏗️ Структура проєкту
```
src/
   Main.java                     // Точка входу, демонстрація системи
   interfaces/
      PassengerCarrier.java      // Інтерфейс для перевезення пасажирів
      CargoCarrier.java          // Інтерфейс для перевезення вантажів
   model/
      cargo/
         Cargo.java              // Абстрактний клас для вантажу
         BoxCargo.java           // Реалізація вантажу (коробка)
      person/
         Person.java             // Абстрактний клас для людей
         Passenger.java          // Пасажир
         Driver.java             // Водій
      transport/
         Transport.java          // Абстрактний клас транспорту
         cargo/
            CargoTransport.java  // Абстрактний транспорт для вантажу
            Truck.java           // Вантажівка (з причепом або без)
         passenger/
            PassengerTransport.java // Абстрактний транспорт для пасажирів
            Bus.java             // Автобус (звичайний / двоповерховий)
            Tram.java            // Трамвай (має довжину маршруту)
            Taxi.java            // Таксі (доступність)
   service/
      CityTransportSystem.java   // Основний сервіс для роботи системи

```

# Хід виконання проєкту
### 👤 Створення абстрактного класу Person
- Поля: `name`, `id`.
- Абстрактний метод `displayInfo()`.
- Класи-нащадки: Passenger, Driver.

### 👤🛻 Клас Driver
- Поле `currentTransport` - транспорт, який веде водій.
- Методи:
- `isDriving()` - чи веде зараз транспорт;
- `stopDriving()` - завершити поїздку;
- `drive(Transport, CityTransportSystem)` - почати рух транспортом.
- Забезпечує, що водій може одночасно керувати лише одним транспортом.

### 🚶 Клас Passenger
- Поля: `ticketNumber`, `currentTransport`.
- Методи:
- `isOnTransport()` - чи знаходиться пасажир у транспорті;
- `leaveTransport()` - вийти з транспорту.
- Перевірка квитків (наприклад у Tram).

### 📦 Абстрактний клас Cargo
- Поля: `id`, `weightKg`, `description`.
- Метод `displayInfo()`.
- Нащадок: BoxCargo.

### 🚍 Абстрактний клас Transport
- Поля: `id`, `routeNumber`, `driver`.
- Методи для роботи з водієм: `setDriver()`, `removeDriver()`, `hasDriver()`.
- Перевизначається у пасажирських та вантажних видах транспорту.

### 👥 Класи пасажирського транспорту (PassengerTransport + нащадки)
- Базовий клас `PassengerTransport` реалізує `PassengerCarrier`.
- Дочірні класи:
  - Bus - з полем `isDoubleDecker`.
  - Taxi - має поле `isAvailable`, блокує посадку, якщо повний.
  - Tram - має обмеження: пасажир повинен мати квиток.
 
### 📦 Класи вантажного транспорту (CargoTransport + нащадки)
- Базовий клас `CargoTransport` реалізує `CargoCarrier`.
- Контролює вагу вантажу та не дозволяє перевантаження.
- Дочірній клас Truck:
  - Може мати причіп (`hasTrailer`), що додає до вантажопідйомності.
  - Виводить повідомлення про повне завантаження чи розвантаження.
 
### 🛠️ Клас CityTransportSystem
- Зберігає колекції: `transports`, `drivers`, `passengers`, `cargos`.
- Методи:
  - `addTransport()`, `addDriver()`, `addPassenger()`, `addCargo()`
  - `assignDriverToTransport()`, `removeDriverFromTransport()`
  - `boardPassenger()`, `disembarkPassenger()`
  - `loadCargo()`, `unloadCargo()`
  - `displayAllTransports()`, `displayAllPassengers()`, `displayAllDrivers()`, `displayAllCargos()`
- Обробляє повідомлення від транспорту (pendingMessages).
