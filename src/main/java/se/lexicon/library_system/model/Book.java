package se.lexicon.library_system.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String title;	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY)
	private LibraryUser user;
	
	
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY
			)
	@JoinTable(
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"),
			name = "book_authors"
			)
	private List<Author> authors = new ArrayList<>();
	
	public Book(String title) {
		this.title = title;
	}
	
	public boolean addAuthor(Author author) {
		if(authors.contains(author)) {
			return false;
		}
		return authors.add(author);
	}
	
	public boolean removeAuthor(Author author) {
		if(!authors.contains(author)) {
			return false;
		}
		return authors.remove(author);
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LibraryUser getUser() {
		return user;
	}

	public void setUser(LibraryUser user) {
		this.user = user;
	}

	public int getBookId() {
		return bookId;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [bookId=");
		builder.append(bookId);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}
}
