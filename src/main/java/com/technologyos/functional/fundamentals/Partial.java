package com.technologyos.functional.fundamentals;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Partial {
   static void partialApplication() {
      /*
       * Partial application is the technique of fixing a few arguments of a function,
       * producing another function of smaller arity.
       *
       * Here, we use currying to apply this concept in Java with BiFunction.
       */

      BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;

      System.out.println(multiply.apply(5, 4)); // 20

      // Step 1: Create a curried version of BiFunction:
      Function<BiFunction<Integer, Integer, Integer>, Function<Integer, Function<Integer, Integer>>>
         curryBiFunction = biFun -> x -> y -> biFun.apply(x, y);

      // Step 2: Apply partial application using the curried function:
      Function<Integer, Function<Integer, Integer>> multiplyBy =
         x -> curryBiFunction.apply(multiply).apply(x);

      // Now we can generate custom functions with fixed arguments:
      Function<Integer, Integer> multiplyBy5 = multiplyBy.apply(5);
      System.out.println(multiplyBy5.apply(20)); // 100

      Function<Integer, Integer> multiplyBy2 = multiplyBy.apply(2);
      System.out.println(multiplyBy2.apply(10)); // 20
   }

   static void ejemploDB(DBConfiguration connectionConf) {
      /*
       * Simulating a database operation that takes configuration and a query.
       */
      BiFunction<DBConfiguration, Query, QueryResult> biDB = (conf, query) ->
         new DataBaseConnection(conf).executeQuery(query);

      // Traditional usage (verbose, repetitive config):
      QueryResult result1 = biDB.apply(connectionConf, new Query("SELECT ..."));
      result1 = biDB.apply(connectionConf, new Query("INSERT ..."));

      // Currying the BiFunction to reduce boilerplate:
      Function<DBConfiguration, Function<Query, QueryResult>> dbFunCreator = curryBiFunction(biDB);

      // Create specialized functions by fixing configuration (partial application):
      Function<Query, QueryResult> postgresExecutor =
         dbFunCreator.apply(new DBConfiguration("localhost", "admin", "1234", 5432));

      Function<Query, QueryResult> mariaDBExecutor =
         dbFunCreator.apply(connectionConf);

      // Now you can execute queries more elegantly:
      postgresExecutor.apply(new Query("SELECT ..."));
      postgresExecutor.apply(new Query("INSERT ..."));
      mariaDBExecutor.apply(new Query("UPDATE ..."));
   }

   // Generic curry function for BiFunction
   static <F, S, R> Function<F, Function<S, R>> curryBiFunction(BiFunction<F, S, R> biFunction) {
      return f -> s -> biFunction.apply(f, s);
   }

   // Mocked classes for demonstration:
   static class QueryResult {}

   static class Query {
      public Query() {}
      public Query(String query) {}
   }

   static class DBConfiguration {
      private String host;
      private String user;
      private String password;
      private int port;

      public DBConfiguration() {}

      public DBConfiguration(String host, String user, String password, int port) {
         this.host = host;
         this.user = user;
         this.password = password;
         this.port = port;
      }
   }

   static class DataBaseConnection {
      public DataBaseConnection(DBConfiguration dbConfiguration) {}

      public QueryResult executeQuery(Query query) {
         return new QueryResult();
      }
   }
}
