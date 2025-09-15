package edu.demo.cqrs.repo; import edu.demo.cqrs.model.Item; import org.springframework.data.jpa.repository.JpaRepository; public interface ItemRepo extends JpaRepository<Item,Long>{}
