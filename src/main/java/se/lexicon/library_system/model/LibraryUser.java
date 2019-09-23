package se.lexicon.library_system.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class LibraryUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private UserDetail detail;
	
	@OneToMany(
			mappedBy = "user", 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.LAZY
			)
	private List<Book> lendedBooks = new ArrayList<>();
	
	public LibraryUser(int id, String firstName, String lastName, LocalDate birthDate, String email, UserDetail detail) {
		this(firstName, lastName, birthDate, email, detail);
		this.id = id;		
	}

	public LibraryUser(String firstName, String lastName, LocalDate birthDate, String email, UserDetail detail) {
		setFirstName(firstName);
		setLastName(lastName);
		setBirthDate(birthDate);
		setEmail(email);
		setDetail(detail);		
	}
	
	protected LibraryUser(){
		//Needed by JPA
	}
	
	public boolean addBook(Book book) {
		if(lendedBooks.contains(book)) {
			return false;
		}
		
		lendedBooks.add(book);
		book.setUser(this);
		return true;
	}
	
	public boolean returnBook(Book book) {
		if(!lendedBooks.contains(book)) {
			return false;
		}
		
		lendedBooks.remove(book);
		book.setUser(null);
		return true;		
	}
	
	public List<Book> getLendedBooks() {
		return lendedBooks;
	}

	public void setLendedBooks(List<Book> lendedBooks) {
		this.lendedBooks = lendedBooks;
	}

	public UserDetail getDetail() {
		return detail;
	}

	public void setDetail(UserDetail detail) {
		this.detail = detail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryUser other = (LibraryUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LibraryUser [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
}
