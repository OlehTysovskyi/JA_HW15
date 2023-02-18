package source;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "author_name")
	private String auhtorName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public Comment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuhtorName() {
		return auhtorName;
	}

	public void setAuhtorName(String auhtorName) {
		this.auhtorName = auhtorName;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		return Objects.hash(auhtorName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(auhtorName, other.auhtorName) && id == other.id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", auhtorName=" + auhtorName + "]";
	}

}
