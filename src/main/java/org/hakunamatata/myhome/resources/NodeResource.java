package org.hakunamatata.myhome.resources;

import java.util.List;

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

import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.NodeService;

@Path("/nodes")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class NodeResource {

	@Context
	UriInfo uriInfo;
	ResponseMessage responseMessage;
	@PathParam("nodeId")
	private long nodeId;
	private NodeService nodeService = new NodeService();

	@GET
	public List<Node> getNodes() {
		return nodeService.getAllNodes();
	}

	@GET
	@Path("/{nodeId}")
	public Response getNode() {
		Node node = nodeService.getNode(nodeId);
		return Response.ok(node).build();
	}

	@POST
	public Response addNode(Node node) {
		node = nodeService.addNode(node);
		return Response.created(uriInfo.getAbsolutePath()).entity(node).build();
	}

	@PUT
	@Path("/{nodeId}")
	public Response updateNode(Node node) {
		node.setDataId(nodeId);
		nodeService.updateNode(node);
		return Response.ok(node).build();
	}

	@DELETE
	@Path("/{nodeId}")
	public Response deleteNode() {
		nodeService.deleteNode(nodeId);
		responseMessage = new ResponseMessage("Successfully deleted the node with Id:" + nodeId, 200);
		return Response.ok(responseMessage).build();
	}

	@DELETE
	public Response deleteNodes() {
		nodeService.deleteAllNodes();
		responseMessage = new ResponseMessage("Successfully deleted all the nodes", 200);
		return Response.ok(responseMessage).build();
	}
	
	@Path("/{nodeId}/comments")
	public CommentsResource getCommentsResource() {
		return new CommentsResource();
	}

	@Path("/{nodeId}/reactions")
	public ReactionResource getReactionResource() {
		return new ReactionResource();
	}
}