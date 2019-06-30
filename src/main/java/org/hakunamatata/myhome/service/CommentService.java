package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hakunamatata.myhome.dao.CommentDao;
import org.hakunamatata.myhome.database.HibernateUtil;
import org.hakunamatata.myhome.model.Comment;

public class CommentService {

	private static CommentDao commentDao;

	public CommentService() {
		commentDao = new CommentDao();
	}

	public Comment addComment(Comment comment) {
		HibernateUtil.openCurrentSessionwithTransaction();
		comment = commentDao.save(comment);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return comment;
	}

	public Comment updateComment(Comment comment) {
		HibernateUtil.openCurrentSessionwithTransaction();
		commentDao.update(comment);
		HibernateUtil.closeCurrentSessionwithTransaction();
		return comment;
	}

	public Comment getComment(long parentId, long dataId) {
		HibernateUtil.openCurrentSession();
		Comment comment = commentDao.getById(parentId, dataId);
		HibernateUtil.closeCurrentSession();
		return comment;
	}

	public void deleteComment(long parentId, long dataId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		Comment comment = commentDao.getById(parentId, dataId);
		commentDao.delete(comment);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<Comment> getAllComments(long parentId) {
		HibernateUtil.openCurrentSession();
		List<Comment> comments = commentDao.getAll(parentId);
		HibernateUtil.closeCurrentSession();
		return comments;
	}

	public void deleteAllComments(long parentId) {
		HibernateUtil.openCurrentSessionwithTransaction();
		commentDao.deleteAll(parentId);
		HibernateUtil.closeCurrentSessionwithTransaction();
	}

	public List<Comment> getAllCommentsByYear(long parentId, int year) {
		List<Comment> CommentsByYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Comment c : getAllComments(parentId)) {
			cal.setTime(c.getCreatedDate());
			if (cal.get(Calendar.YEAR) == year) {
				CommentsByYear.add(c);
			}
		}
		return CommentsByYear;
	}

	public List<Comment> getAllCommentsPaginated(long parentId, int start, int size) {
		ArrayList<Comment> list = new ArrayList<>(getAllComments(parentId));
		if (start + size > getAllComments(parentId).size()) {
			new ArrayList<Comment>();
		}
		return list.subList(start, start + size);
	}

	public CommentDao commentDao() {
		return commentDao;
	}

}
