# Assignment 2 (ADS)

**Student:** Nur  
**Group:** SE-2511

## About

This is a simple banking console app in Java for Assignment 2.
I used these structures:

- `LinkedList` for accounts
- `Stack` for transaction history
- `Queue` for bill payments and account requests
- `BankAccount[3]` array for physical structure task
- small `MinHeap` demo (service tickets)

All code is in `src/Main.java`.

## What works

- add account, show all accounts, search by username
- deposit / withdraw
- transaction history (`push`, `peek`, `pop`)
- bill payment queue (`offer`, `poll`)
- account opening requests queue (admin processes requests)
- physical array of 3 predefined accounts
- menu system: Bank / ATM / Admin

## Screenshots

### Task 1 (LinkedList: display + search)

Task 1 - display accounts
Task 1 - search by username

### Task 2 (Deposit / Withdraw)

Task 2 - deposit example

### Task 3 (Stack: peek + pop)

Task 3 - peek last transaction
Task 3 - pop undo

### Task 4 (Bill Queue)

Task 4 - bill queue list

### Task 5 (Account Request Queue)

Task 5 - pending account requests

### Task 6 (Physical structure array) + Integrated menu

Task 6 + main menu

## Short note

I built it step by step: first accounts, then deposit/withdraw, then stack/queue parts, then combined everything in one menu.
There may be many ways to organize this code, but this version runs and matches assignment requirements.