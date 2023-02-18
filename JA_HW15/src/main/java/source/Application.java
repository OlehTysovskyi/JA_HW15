package source;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Application {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		Session session = configuration.buildSessionFactory(serviceRegistry).openSession();

		Post post1 = new Post();
		post1.setTitle("Bitcoin is growing to 100k?");

		Comment comment1 = new Comment();
		comment1.setAuhtorName("Ilon Mask");
		comment1.setPost(post1);

		Comment comment2 = new Comment();
		comment2.setAuhtorName("Volodymyr Zelentskiy");
		comment2.setPost(post1);

		Comment comment3 = new Comment();
		comment3.setAuhtorName("Bohdan Danyliuk");
		comment3.setPost(post1);

		Set<Comment> comments = new HashSet<>();
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
		
		post1.setComments(comments);

		Transaction transaction = session.beginTransaction();
		session.save(post1);
		transaction.commit();

		Post postDB = (Post) session.get(Post.class, post1.getId());
		System.out.println(postDB + "-->" + postDB.getComments());

		Comment commentDb = (Comment) session.get(Comment.class, comment1.getId());
		System.out.println(commentDb + "-->" + commentDb.getPost());

	}

}
