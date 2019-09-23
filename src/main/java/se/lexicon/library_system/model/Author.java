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

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;
	private String authorName;
	
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY			
			)
	@JoinTable(
			joinColumns = @JoinColumn(name = "author_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"),
			name = "book_authors"
			)
	private List<Book> writtenBooks = new ArrayList<>();
	
	public Author(String authorName) {
		this.authorName = authorName;
	}
	
	public boolean addBook(Book book) {
		if(writtenBooks.contains(book)) {
			return false;
		}
		return writtenBooks.add(book);
	}
	
	public boolean remove(Book book) {
		if(!writtenBooks.contains(book)) {
			return false;
		}
		return writtenBooks.remove(book);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<Book> getWrittenBooks() {
		return writtenBooks;
	}

	public void setWrittenBooks(List<Book> writtenBooks) {
		this.writtenBooks = writtenBooks;
	}

	public int getAuthorId() {
		return authorId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Author [authorId=");
		builder.append(authorId);
		builder.append(", authorName=");
		builder.append(authorName);
		builder.append("]");
		return builder.toString();
	}
}
