package se.lexicon.library_system.data;

import java.util.List;
import java.util.Optional;

import se.lexicon.library_system.model.LibraryUser;

public interface LibraryUserDao {
	
	Optional<LibraryUser> findById(int id);
	List<LibraryUser> findAll();
	LibraryUser save(LibraryUser user);
	void delete(int id);
	LibraryUser update(LibraryUser user);

}
