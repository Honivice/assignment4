import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class BankAccount {
    private final String accountNumber;
    private final String username;
    private double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class AccountRequest {
    private final String accountNumber;
    private final String username;
    private final double initialDeposit;

    public AccountRequest(String accountNumber, String username, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.initialDeposit = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }
}

class IntMinHeap {
    private final LinkedList<Integer> heap = new LinkedList<>();

    public boolean empty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public Integer getMin() {
        return empty() ? null : heap.get(0);
    }

    public void insert(int value) {
        heap.add(value);
        traverseUp(heap.size() - 1);
    }

    public Integer extractMin() {
        if (empty()) {
            return null;
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!empty()) {
            heap.set(0, last);
            heapify(0);
        }
        return min;
    }

    private void traverseUp(int index) {
        while (index > 0) {
            int parent = parentOf(index);
            if (heap.get(parent) <= heap.get(index)) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    private void heapify(int index) {
        while (true) {
            int left = leftChildOf(index);
            int right = rightChildOf(index);
            int smallest = index;

            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    private int leftChildOf(int index) {
        return (2 * index) + 1;
    }

    private int rightChildOf(int index) {
        return (2 * index) + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, temp);
    }
}

public class Main {
    private static final LinkedList<BankAccount> accounts = new LinkedList<>();
    private static final Stack<String> transactionHistory = new Stack<>();
    private static final Queue<String> billQueue = new LinkedList<>();
    private static final Queue<AccountRequest> accountRequests = new LinkedList<>();
    private static final IntMinHeap pendingTicketHeap = new IntMinHeap();
    private static final Scanner scanner = new Scanner(System.in);

    private static final BankAccount[] physicalAccounts = new BankAccount[3];
    private static int nextTicket = 1;

    public static void main(String[] args) {
        seedAccounts();
        seedPhysicalAccounts();
        printPhysicalAccounts();
        runMainMenu();
    }

    private static void seedAccounts() {
        accounts.add(new BankAccount("ACC1001", "Ali", 150000));
        accounts.add(new BankAccount("ACC1002", "Sara", 220000));
    }

    private static void seedPhysicalAccounts() {
        physicalAccounts[0] = new BankAccount("PHY2001", "Nurlan", 100000);
        physicalAccounts[1] = new BankAccount("PHY2002", "Dana", 175000);
        physicalAccounts[2] = new BankAccount("PHY2003", "Aruzhan", 90000);
    }

    private static void printPhysicalAccounts() {
        System.out.println("=== Task 6: Physical Data Structure (Array) ===");
        for (int i = 0; i < physicalAccounts.length; i++) {
            BankAccount account = physicalAccounts[i];
            System.out.printf("%d. %s (%s) - Balance: %.2f%n",
                    i + 1, account.getUsername(), account.getAccountNumber(), account.getBalance());
        }
        System.out.println();
    }

    private static void runMainMenu() {
        while (true) {
            System.out.println("=== Mini Banking Menu ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    runBankMenu();
                    break;
                case 2:
                    runAtmMenu();
                    break;
                case 3:
                    runAdminMenu();
                    break;
                case 4:
                    System.out.println("Thank you for using the banking system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
    }

    private static void runBankMenu() {
        while (true) {
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1 - Add new account directly");
            System.out.println("2 - Display all accounts");
            System.out.println("3 - Search account by username");
            System.out.println("4 - Deposit money");
            System.out.println("5 - Withdraw money");
            System.out.println("6 - Add bill payment request");
            System.out.println("7 - Submit account opening request");
            System.out.println("8 - Get service ticket (Heap demo)");
            System.out.println("9 - Back");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addNewAccount();
                    break;
                case 2:
                    displayAllAccounts();
                    break;
                case 3:
                    searchAccountByUsername();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withdrawMoney();
                    break;
                case 6:
                    addBillPaymentRequest();
                    break;
                case 7:
                    submitAccountOpeningRequest();
                    break;
                case 8:
                    takeServiceTicket();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void runAtmMenu() {
        while (true) {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    balanceEnquiry();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void runAdminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1 - View pending account requests");
            System.out.println("2 - Process next account request");
            System.out.println("3 - View bill payment queue");
            System.out.println("4 - Process next bill payment");
            System.out.println("5 - View last transaction (peek)");
            System.out.println("6 - Undo last transaction (pop)");
            System.out.println("7 - Display transaction history");
            System.out.println("8 - View next service ticket (heap getMin)");
            System.out.println("9 - Process next service ticket (heap extractMin)");
            System.out.println("10 - Back");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    displayPendingAccountRequests();
                    break;
                case 2:
                    processNextAccountRequest();
                    break;
                case 3:
                    displayBillQueue();
                    break;
                case 4:
                    processNextBillPayment();
                    break;
                case 5:
                    peekLastTransaction();
                    break;
                case 6:
                    undoLastTransaction();
                    break;
                case 7:
                    displayTransactionHistory();
                    break;
                case 8:
                    viewNextServiceTicket();
                    break;
                case 9:
                    processNextServiceTicket();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addNewAccount() {
        String accountNumber = readString("Enter account number: ");
        String username = readString("Enter username: ");
        double balance = readDouble("Enter initial balance: ");

        if (findAccountByUsername(username) != null) {
            System.out.println("Account with this username already exists.");
            return;
        }

        accounts.add(new BankAccount(accountNumber, username, balance));
        System.out.println("Account added successfully.");
    }

    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("Accounts List:");
        int index = 1;
        for (BankAccount account : accounts) {
            System.out.printf("%d. %s (%s) - Balance: %.2f%n",
                    index++, account.getUsername(), account.getAccountNumber(), account.getBalance());
        }
    }

    private static void searchAccountByUsername() {
        String username = readString("Enter username to search: ");
        BankAccount account = findAccountByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.printf("Found: %s (%s) - Balance: %.2f%n",
                account.getUsername(), account.getAccountNumber(), account.getBalance());
    }

    private static void depositMoney() {
        String username = readString("Enter username: ");
        BankAccount account = findAccountByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount = readDouble("Deposit amount: ");
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        account.setBalance(account.getBalance() + amount);
        String tx = String.format("Deposit %.2f to %s", amount, account.getUsername());
        transactionHistory.push(tx);
        System.out.printf("New balance: %.2f%n", account.getBalance());
    }

    private static void withdrawMoney() {
        String username = readString("Enter username: ");
        BankAccount account = findAccountByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount = readDouble("Withdraw amount: ");
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }
        if (account.getBalance() < amount) {
            System.out.println("Insufficient funds.");
            return;
        }

        account.setBalance(account.getBalance() - amount);
        String tx = String.format("Withdraw %.2f from %s", amount, account.getUsername());
        transactionHistory.push(tx);
        System.out.printf("New balance: %.2f%n", account.getBalance());
    }

    private static void balanceEnquiry() {
        String username = readString("Enter username: ");
        BankAccount account = findAccountByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.printf("Current balance for %s: %.2f%n", account.getUsername(), account.getBalance());
    }

    private static void addBillPaymentRequest() {
        String billName = readString("Enter bill name: ");
        billQueue.offer(billName);
        transactionHistory.push("Bill payment requested: " + billName);
        System.out.println("Added: " + billName);
    }

    private static void processNextBillPayment() {
        String bill = billQueue.poll();
        if (bill == null) {
            System.out.println("No bill payment requests in queue.");
            return;
        }
        transactionHistory.push("Bill processed: " + bill);
        System.out.println("Processing: " + bill);
    }

    private static void displayBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }

        System.out.println("Bill queue:");
        int index = 1;
        for (String bill : billQueue) {
            System.out.println(index++ + ". " + bill);
        }
    }

    private static void submitAccountOpeningRequest() {
        String accountNumber = readString("Enter requested account number: ");
        String username = readString("Enter requested username: ");
        double initialDeposit = readDouble("Enter initial deposit: ");

        accountRequests.offer(new AccountRequest(accountNumber, username, initialDeposit));
        transactionHistory.push("Account opening request submitted for " + username);
        System.out.println("Request submitted successfully.");
    }

    private static void processNextAccountRequest() {
        AccountRequest request = accountRequests.poll();
        if (request == null) {
            System.out.println("No pending account requests.");
            return;
        }

        if (findAccountByUsername(request.getUsername()) != null) {
            System.out.println("Request skipped: username already exists.");
            return;
        }

        accounts.add(new BankAccount(
                request.getAccountNumber(),
                request.getUsername(),
                request.getInitialDeposit()
        ));

        transactionHistory.push("Account created for " + request.getUsername() + " from request queue");
        System.out.println("Processed and added account for: " + request.getUsername());
    }

    private static void displayPendingAccountRequests() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }

        System.out.println("Pending account requests:");
        int index = 1;
        for (AccountRequest request : accountRequests) {
            System.out.printf("%d. %s (%s) - Initial deposit: %.2f%n",
                    index++, request.getUsername(), request.getAccountNumber(), request.getInitialDeposit());
        }
    }

    private static void peekLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    private static void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to undo.");
            return;
        }
        String removed = transactionHistory.pop();
        System.out.println("Undo -> removed: " + removed);
    }

    private static void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("Transaction history is empty.");
            return;
        }
        System.out.println("Transaction history (oldest -> newest):");
        for (String tx : transactionHistory) {
            System.out.println("- " + tx);
        }
    }

    private static void takeServiceTicket() {
        int ticket = nextTicket++;
        pendingTicketHeap.insert(ticket);
        transactionHistory.push("Service ticket issued: #" + ticket);
        System.out.println("Ticket issued: #" + ticket);
        System.out.println("Tickets waiting (heap size): " + pendingTicketHeap.size());
    }

    private static void viewNextServiceTicket() {
        Integer ticket = pendingTicketHeap.getMin();
        if (ticket == null) {
            System.out.println("No service tickets in heap.");
            return;
        }
        System.out.println("Next ticket (min): #" + ticket);
    }

    private static void processNextServiceTicket() {
        Integer ticket = pendingTicketHeap.extractMin();
        if (ticket == null) {
            System.out.println("No service tickets to process.");
            return;
        }
        transactionHistory.push("Service ticket processed: #" + ticket);
        System.out.println("Processed ticket: #" + ticket);
        System.out.println("Remaining tickets: " + pendingTicketHeap.size());
    }

    private static BankAccount findAccountByUsername(String username) {
        for (BankAccount account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            String raw = readString(prompt);
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {5
            String raw = readString(prompt);
            try {
                return Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
