package org.hakunamatata.myhome.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.resources.beans.CommentFilterBean;
import org.hakunamatata.myhome.service.CommentService;

@Path("/comments")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class CommentsResource {

    @Context 
    UriInfo uriInfo;

    CommentService commentService = new CommentService();

    @GET
    public List<Comment> getComments(@BeanParam CommentFilterBean commentFilterBean) {

	/*
	  public List<Comment> getComments(@DefaultValue("-1") @QueryParam("year") int year,
	  				    @DefaultValue("-1") @QueryParam("start") int start,
	  				    @DefaultValue("-1") @QueryParam("size") int size) {
	*/ 

	int year = commentFilterBean.getYear();
	int start = commentFilterBean.getStart();
	int size = commentFilterBean.getSize();

	if (year > 0) {
	    return commentService.getAllCommentsByYear(year);
	}
	if (start >= 0 && size >= 0) {
	    return commentService.getAllCommentsPaginated(start, size);
	}
	return commentService.getAllComments();
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("commentId") long id) {
	return commentService.getComment(id);
    }

    @POST
    public Response addComment(Comment comment) {
	Comment newComment = commentService.addComment(comment);
	newComment.addLink(getSelfUri(newComment.getCommentId()).toString(), "self");
	return Response.created(getSelfUri(newComment.getCommentId()))
		.entity(newComment).build();
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(Comment comment, @PathParam("commentId") long id) {
	comment.setCommentId(id);

	Comment updateComment = commentService.updateComment(comment);
	updateComment.addLink(getSelfUri(id).toString(), "self");

	return updateComment;
    }

    private URI getSelfUri(long id) {
	return uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("commentId") long id) {
	commentService.deleteComment(id);
    }
}
