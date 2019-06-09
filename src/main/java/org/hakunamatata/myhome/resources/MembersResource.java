package org.hakunamatata.myhome.resources;

import java.net.URI;
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
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.Member;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.MemberService;

@Path("members")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MembersResource {

	@Context
	UriInfo uriInfo;
	ResponseMessage responseMessage;
	MemberService memberService = new MemberService();

	@GET
	public List<Member> getMembers() {
		return memberService.getAllMembers();
	}

	@GET
	@Path("/{memberId}")
	public Response getMember(@PathParam("memberId") long id) {

		Member member = memberService.getMember(id);
		member.addLink(getSelfUri(id).toString(), "self");
		member.addLink(getFavouritesUri(id).toString(), "favourites");

		return Response.ok(member)
				.links(Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build())
				.links(Link.fromUri((URI)getFavouritesUri(id)).rel("favourites").type("GET").build())
				.build();
	}

	@POST
	public Response addMember(Member member) {
		member = memberService.addMember(member);
		return Response.created(uriInfo.getAbsolutePath()).entity(member).build();
	}

	@PUT
	@Path("/{memberId}")
	public Response updateMember(Member member, @PathParam("memberId") long id) {
		member.setMemberId(id);
		memberService.updateMember(member);
		return Response.ok(member).build();
	}

	@DELETE
	@Path("/{memberId}")
	public Response deleteMember(@PathParam("memberId") long id) {
		memberService.deleteMember(id);
		responseMessage = new ResponseMessage("Successfully deleted the memeber with Id:"+id, 200 );
		return Response.ok(responseMessage).build();
	}

	@Path("/{memberId}/favourites")
	public FavouritesResource getFavouritesResource() {
		return new FavouritesResource();
	}

	private URI getSelfUri(long id) {
		return uriInfo.getAbsolutePath();
	}

	private Object getFavouritesUri(long id) {
		return uriInfo.getBaseUriBuilder().path(MembersResource.class)
				.path(MembersResource.class, "getFavouritesResource").resolveTemplate("memberId", id).build();
	}

}
