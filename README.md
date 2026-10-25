# ATM-JAVA

## Description
The project is a simulation of an ATM machine where the user enters 
their username and password, and is able to perform any of the following activities: 
1. View Account Balance
2. Deposit Money
3. Withdraw Money
4. Print a Receipt
5. Quit their sesstion

## Compile
```bash
make
```
## Run
```
java Main
```
## Java Concepts Practiced

- **Classes and objects:** Created a `Customer` class with a constructor, private fields, a getter, and a credential-checking method.
- **Collections:** Used `ArrayList<Customer>` to store customers and `ArrayList<String>` to track session activity.
- **Loops and conditionals:** Used enhanced `for` loops, a `do-while` menu loop, and `if`/`else` branches.
- **Console input and validation:** Used `Scanner` to read user input and handled invalid numeric input with `try`/`catch`.
- **File I/O:** Read account records from `Accounts.txt` and wrote a session receipt to `Receipt.txt`.
- **Strings and output formatting:** Compared strings with `.equals()` and formatted messages with `String.format()`.
- **Exceptions:** Handled input errors and declared file-related I/O exceptions.
