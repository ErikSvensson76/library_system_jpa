package se.lexicon.library_system;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import se.lexicon.library_system.data.LibraryUserDao;
import se.lexicon.library_system.model.LibraryUser;

@Component
public class CommandLine  implements CommandLineRunner{
	
	private LibraryUserDao userDao;
	
	@Autowired
	public CommandLine(LibraryUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void run(String... args) throws Exception {
		LibraryUser u1 = new LibraryUser("Erik", "Svensson", LocalDate.parse("1976-09-11"), "erik.svensson@lexicon.se");
		u1 = userDao.save(u1);
		userDao.delete(1);
	}

}
