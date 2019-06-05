package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

//import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hakunamatata.myhome.database.Database;
//import org.hakunamatata.myhome.exception.DataNotFoundException;
import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.model.ErrorMessage;

public class CommentService {

    //test
	private Map<Long, Comment> comments = Database.getComments();

    public List<Comment> getAllComments() {
	return new ArrayList<Comment>(comments.values());
    }

    public List<Comment> getAllCommentsByYear(int year) {
	List<Comment> CommentsByYear = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	for (Comment c : comments.values()) {
	    cal.setTime(c.getCreatedDate());
	    if (cal.get(Calendar.YEAR) == year) {
		CommentsByYear.add(c);
	    }
	}
	return CommentsByYear;
    }

    public List<Comment> getAllCommentsPaginated(int start, int size) {
	ArrayList<Comment> list = new ArrayList<>(comments.values());
	if (start + size > comments.size()) {
	    new ArrayList<Comment>();
	}
	return list.subList(start, start + size);
    }

    public Comment getComment(long commentId) {
	ErrorMessage errorMessage = new ErrorMessage("Not Found",404);
	
	Response response = Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();

	Comment comment = comments.get(commentId);
	if (comment == null)
	    //throw new  DataNotFoundException("Comment with id : " + commentId + " not available");
	    //throw new NotFoundException(response);
	    throw new WebApplicationException(response);
	return comment;
    }

    public Comment addComment(Comment comment) {
	comment.setCommentId(comments.size() + 1);
	comments.put(comment.getCommentId(), comment);
	return comment;
    }

    public Comment updateComment(Comment comment) {
	if (comment.getCommentId() <= 0)
	    return null;
	comments.put(comment.getCommentId(), comment);
	return comment;
    }

    public Comment deleteComment(long commentId) {
	return comments.remove(commentId);
    }

}
