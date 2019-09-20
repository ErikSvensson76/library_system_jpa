package se.lexicon.library_system.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.library_system.model.LibraryUser;

import static org.junit.Assert.*;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LibraryUserDaoJpaTest {
	
	@Autowired LibraryUserDao testDao;
	
	@Test
	public void given_new_user_get_persisted() {
		LibraryUser testUser = new LibraryUser("Test", "Testsson", LocalDate.parse("1999-01-01"), "test@test.com");
		
		LibraryUser persistedUser = testDao.save(testUser);
		assertTrue(persistedUser.getId() > 0);
		
		assertTrue(testDao.findById(persistedUser.getId()).isPresent());
		
	}
	

}
