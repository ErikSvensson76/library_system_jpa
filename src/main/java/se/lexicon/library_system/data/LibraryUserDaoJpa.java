package se.lexicon.library_system.data;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.library_system.model.LibraryUser;

@Repository
@Transactional(rollbackFor = Exception.class)
public class LibraryUserDaoJpa implements LibraryUserDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override	
	public Optional<LibraryUser> findById(int id) {
		LibraryUser result = em.find(LibraryUser.class, id);
		
		if(result == null) {
			return Optional.empty();
		}else {
			return Optional.of(result);
		}
	}

	@Override
	public List<LibraryUser> findAll() {
		Query query = em.createQuery("SELECT u FROM LibraryUser u");
		return query.getResultList();		
	}

	@Override
	public LibraryUser save(LibraryUser user) {
		if(user == null) {
			throw new IllegalArgumentException("User was " + user);
		}
		
		if(user.getId() != 0) {
			return update(user);
		}
		
		em.persist(user);		
		return user;
	}

	@Override
	public void delete(int id) {
		Optional<LibraryUser> result = findById(id);
		em.remove(result.orElseThrow(IllegalArgumentException::new));		
	}

	@Override
	public LibraryUser update(LibraryUser user) {		
		if(user == null) {
			throw new IllegalArgumentException("User was " + user);
		}
		if(user.getId() == 0) {
			return save(user);
		}
		return em.merge(user);
	}

}
