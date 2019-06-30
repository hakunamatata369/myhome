package org.hakunamatata.myhome.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.resources.beans.ResourceFilterBean;
import org.hakunamatata.myhome.service.CommentService;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class CommentsResource {

	@Context
	private UriInfo uriInfo;
	ResponseMessage responseMessage;

	@PathParam("nodeId")
	private long nodeId;

	@PathParam("commentId")
	private long commentId;

	CommentService commentService = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("nodeId") long nodeId, @BeanParam ResourceFilterBean resourceFilterBean) {

		int year = resourceFilterBean.getYear();
		int start = resourceFilterBean.getStart();
		int size = resourceFilterBean.getSize();

		if (year > 0) {
			return commentService.getAllCommentsByYear(nodeId, year);
		}
		if (start >= 0 && size >= 0) {
			return commentService.getAllCommentsPaginated(nodeId, start, size);
		}
		return commentService.getAllComments(nodeId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("nodeId") long nodeId, @PathParam("commentId") long commentId) {
		return commentService.getComment(nodeId, commentId);
	}

	@POST
	public Comment addComment(@PathParam("nodeId") long nodeId, @PathParam("commentId") long commentId, Comment comment) {
		comment.setParentId(nodeId);
		Comment newComment = commentService.addComment(comment);
		return newComment;
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("nodeId") long nodeId, @PathParam("commentId") long commentId, Comment comment) {
		comment.setDataId(commentId);
		comment.setParentId(nodeId);
		Comment updateComment = commentService.updateComment(comment);
		return updateComment;
	}

	@DELETE
	@Path("/{commentId}")
	public Response deleteComment(@PathParam("nodeId") long nodeId, @PathParam("commentId") long commentId) {
		commentService.deleteComment(nodeId, commentId);
		responseMessage = new ResponseMessage("Successfully deleted the comment with Id:" + commentId, 200);
		return Response.ok(responseMessage).build();
	}

	@DELETE
	public Response deleteComments(@PathParam("nodeId") long nodeId) {
		commentService.deleteAllComments(nodeId);
		responseMessage = new ResponseMessage("Successfully deleted all the comments", 200);
		return Response.ok(responseMessage).build();
	}
}
