package com.zaripov.data;

import com.zaripov.entity.Book;
import java.util.List;

public interface BookComplexQueryRepository {
    List <Book> complexQueryMethod();
}