package uni.fmi.masters.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.beans.CommentBean;

@Repository
public interface CommentRepo extends JpaRepository<CommentBean, Integer>{

}
