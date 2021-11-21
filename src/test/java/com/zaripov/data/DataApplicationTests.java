package com.zaripov.data;

import com.zaripov.DataApplication;
import com.zaripov.entity.Author;
import com.zaripov.entity.AuthorOfBook;
import com.zaripov.entity.Book;
import com.zaripov.service.BookService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplication.class)
class DataApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorOfBookRepository authorOfBookRepository;

	@Autowired
	private BookService bookService;

	@Before
	public void init() {
		Author author1 = new Author();
		author1.setFirstname("Mark");
		author1.setLastname("Tven");
		authorRepository.save(author1);

		Book book1 = new Book();
		book1.setTitle("Thom");
		book1.setYear(1876);
		bookRepository.save(book1);

		AuthorOfBook aob1 = new AuthorOfBook();
		aob1.setAuthor(author1);
		aob1.setBook(book1);
		authorOfBookRepository.save(aob1);

		Author author2 = new Author();
		author2.setFirstname("Jul");
		author2.setLastname("Vern");
		authorRepository.save(author2);

		Book book2 = new Book();
		book2.setTitle("Book");
		book2.setYear(1876);
		bookRepository.save(book2);

		AuthorOfBook aob2 = new AuthorOfBook();
		aob1.setAuthor(author2);
		aob1.setBook(book2);
		authorOfBookRepository.save(aob2);
	}

	@Test
	public void testCreation(){
		init();

		boolean founded = false;
		for(Book book : bookRepository.findAll())
			if (book.getTitle().contains("Thom"))
				founded = true;
		assertTrue(founded);
	}

	@Test
	public void testFindByYear(){
		assertEquals(2,bookRepository.findBookByYear(1876).size());
		assertEquals(0,bookRepository.findBookByYear(1878).size());
	}

	@Test
	public void testPaging(){
		boolean founded = bookService.findAtPage(1,1, Sort.Direction.ASC,"title")
				.get().anyMatch(book -> book.getTitle().equals("Thom"));
		assertTrue(founded);
	}

	@Test
	public void findSame(){
		Book book = new Book();
		book.setYear(1876);
		assertEquals(2,bookService.findSame(book).size());
	}

	@Test
	public void findInRange(){
		assertEquals(0, bookRepository.findAll(BooksSpecification.yearInRange(1874,1876)).size());

		assertEquals(2, bookRepository.findAll(BooksSpecification.yearInRange(1874,1877)).size());
	}

	@Test
	public void findByAuthorLastName(){
		assertEquals(1, bookRepository.findByAuthorLAstName("Tven").size());
	}

	@Test
	public void testComplexQuery() {
		System.out.println(bookRepository.complexQueryMethod());
	}
}