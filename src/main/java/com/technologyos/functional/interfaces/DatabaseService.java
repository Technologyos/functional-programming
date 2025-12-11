package com.technologyos.functional.interfaces;

import java.util.List;

public interface DatabaseService<T> {
   T getById(Long id);

   List<T> getAllRecords();
}
