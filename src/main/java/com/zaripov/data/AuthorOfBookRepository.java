package com.zaripov.data;

import com.zaripov.entity.AuthorOfBook;
import org.springframework.data.repository.CrudRepository;

public interface AuthorOfBookRepository extends CrudRepository<AuthorOfBook,Long> {
}