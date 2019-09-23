package se.lexicon.library_system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detailId;
	private String content;
	
	public UserDetail(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDetailId() {
		return detailId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetail [detailId=");
		builder.append(detailId);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
}
