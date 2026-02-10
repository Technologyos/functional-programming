package com.technologyos.functional.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConsumerSupplierGuide {

   /**
    * A Consumer represents a function that takes a single input but returns no result.
    * The most common example is something like printing a value to the console.
    * In our example, we use it to store an account in a file and a database.
    */
   private static void persistAccount(Account account) {
      Consumer<Account> saveToFile = ConsumerSupplierGuide::saveToFile;

      Consumer<Account> saveToDatabase = request ->
         getDataBaseConnection().ifPresent(db -> db.insert(request));

      // `saveAccountTo` doesn't care what the Consumer actually does.
      // It just knows it has to pass the account to *some* saving logic.
      saveAccountTo(account, saveToFile);
      saveAccountTo(account, saveToDatabase);
   }

   private static void saveAccountTo(Account account, Consumer<Account> saveAction) {
      // Save the account using the given action
      saveAction.accept(account);
   }

   /**
    * A Supplier is a function that takes no arguments and returns a value.
    * This is useful when you need to generate or retrieve values dynamically—
    * for example, fetching a random number or getting a database connection.
    *
    * There's no strict rule: a Supplier may always return the same value,
    * a new instance each time, or even a random result depending on the logic.
    */
   private void supplierDemo() {
      // Supplies a random number
      Supplier<Double> numberSupplier = Math::random;

      System.out.println(numberSupplier.get());

      // Supplies a database connection
      Supplier<Optional<DataBaseExecutor>> dbSupplier = ConsumerSupplierGuide::getDataBaseConnection;

      // Uses the supplier to fetch a database connection, then retrieve a value by ID
      Function<String, Account> dbFunction = sId ->
         dbSupplier.get()
            .map(db -> db.select(sId, Account.class))
            .orElse(null);

      //The main reason for using Suppliers is to decouple data retrieval logic
      //from the business logic that uses the data.
      //In functional programming, you often focus more on *how* to do something
      //rather than *where* the data comes from.
      // And since a Supplier is just an object, it can be passed around and reused easily.
   }

   private static void saveToFile(Account account) {
      // Simulate file persistence logic
      System.out.printf("Saving account to file: ID=%s, Balance=%.2f%n", account.id(), account.balance());
   }

   private static Optional<DataBaseExecutor> getDataBaseConnection() {
      // In real scenarios, return a real implementation or use dependency injection
      return Optional.of(new InMemoryDatabaseExecutor());
   }

   private record Account(String id, double balance) {}

   private interface DataBaseExecutor {
      <T> void insert(T instance);
      <T> T select(String id, Class<T> type);
   }

   // Simple in-memory database implementation (for example/testing purposes)
   private static class InMemoryDatabaseExecutor implements DataBaseExecutor {
      private final Map<String, Object> store = new HashMap<>();

      @Override
      public <T> void insert(T instance) {
         if (instance instanceof Account account) {
            store.put(account.id(), account);
            System.out.println("Account stored in in-memory DB.");
         }
      }

      @Override
      public <T> T select(String id, Class<T> type) {
         return type.cast(store.get(id));
      }
   }
}
