package org.hakunamatata.myhome.resources;

import java.util.Collection;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hakunamatata.myhome.model.Reaction;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.MemberService;
import org.hakunamatata.myhome.service.NodeService;
import org.hakunamatata.myhome.service.ReactionService;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
public class ReactionResource {

	private ResponseMessage responseMessage;
	private ReactionService reactionService = new ReactionService();
	private MemberService memberService = new MemberService();
	private NodeService nodeService = new NodeService();
	
	@GET
	public Collection<Reaction> getAllReactions(@PathParam("nodeId") long nodeId) {
		return reactionService.getAllReactions(nodeService.getNode(nodeId));
	}
	
	@DELETE
	public Response deleteAllReactions(@PathParam("nodeId") long nodeId) {
		reactionService.deleteAllReactions(nodeService.getNode(nodeId));
		responseMessage = new ResponseMessage("Successfully deleted all the reactions for node with nodeId:" + nodeId, 200);
		return Response.ok(responseMessage).build();
	}

	@POST
	public Reaction postReaction(@PathParam("nodeId") long nodeId, @FormParam("member_id") long memberId, @FormParam("reaction_type") int reactionType) {
		Reaction reaction = new Reaction();
		reaction.setReactedBy(memberService.getMember(memberId));
		reaction.setReactedDate(new Date());
		reaction.setReactedNode(nodeService.getNode(nodeId));
		reaction.setReactionType(reactionType);
		Reaction newReaction = reactionService.postReaction(reaction);
		return newReaction;
	}
	
	@PUT
	public Reaction updateReaction(@PathParam("nodeId") long nodeId, @FormParam("member_id") long memberId, @FormParam("reaction_type") int reactionType) {
		Reaction reaction = new Reaction();
		reaction.setReactedBy(memberService.getMember(memberId));
		reaction.setReactedNode(nodeService.getNode(nodeId));
		reaction.setReactedDate(new Date());
		reaction.setReactionType(reactionType);
		Reaction newReaction = reactionService.updateReaction(reaction);
		return newReaction;
	}
}
