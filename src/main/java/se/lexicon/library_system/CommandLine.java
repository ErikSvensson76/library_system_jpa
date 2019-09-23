package se.lexicon.library_system;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.library_system.model.Author;
import se.lexicon.library_system.model.Book;

@Component
@Transactional
public class CommandLine  implements CommandLineRunner{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book("JPA");
		Book book2 = new Book("JDBC");
		Author author = new Author("Erik");
		Author author2 = new Author("Amanda");
		Author author3 = new Author("Ulf");
		book.addAuthor(author3);
		book.addAuthor(author);
		em.persist(book);
		em.flush();
		book2.addAuthor(author2);
		book2.addAuthor(author);
		em.persist(book2);
		em.flush();		
	}

}
