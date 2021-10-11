# Workshop

A service establishment employs a total of 4 employees.
One person takes orders for the repair of X equipment, and three others perform the repairs.
The plant can accommodate a maximum of n pieces of X equipment.
The person accepting the orders notes the addresses of the owners of the equipment they return for repair and attaches them to the copy. Then he puts the equipment back on the shelf.
When any of the three "physical" repairers are free, they take the item received, repair it, pack it, stick the recipient's address, and send it to the ordering party.

The simulation works as follows:

-  At the entrance, the user specifies the number of orders to be picked up and the capacity of the shelf. If he does not give them or gives them incorrect, default settings downloaded from the data.properties file will be called.
-  The recipient receives orders from the pool at random time intervals, while the recipient receives the user, he can add or subtract the number of orders using buttons.
-  The recipient puts the order on the shelf after receiving it. If the shelf is full, it waits for some item to disappear from the shelf.
-  In the meantime, employees physically check whether there are orders on the shelf, if so, the fastest of them takes the order, repairs it and adds it to the repaired pool.
-  When all orders are completed, the threads of manual workers and recipients finish their work.

2. List of shared resources

- Shelf : Orders are placed on a shelf and picked up by manual workers. Access to the shelf is controlled in such a way that only one thread can perform a pick or put operation on it at a time.
- Order : It is an object placed on a shelf by the recipient and collected by a manual worker. The Orders objects are stored on the shelf.

3. List of highlighted synchronization points

- Placement of the order on the shelf by the recipient
- Picking the order from the shelf by a manual worker

4. List of synchronization objects

- the shelf

5. List of sequential processes

- Recipient
- Worker

Process data synchronization is based on the use of a monitor and locks mechanism.
Shelves Process Allows only one sequential process to use its resources at a time.
Thanks to this, when the recipient picks up the order, he closes the "lock" on the shelf and no one else can use it. At the moment, it checks if the shelf has space for the next order. If it does not provide access to the shelf to other processes, it waits until there is room on the shelf. As soon as a place is available, he adds the order to the shelf and opens the access. The same happens when the order is picked up by a manual worker.

6. Graphic interface

Made with javaFX library.
The default settings are taken from the data.properties file.
If the user fails to enter data in or enters incorrect, the simulation will be invoked with default settings from the data.properties file.

