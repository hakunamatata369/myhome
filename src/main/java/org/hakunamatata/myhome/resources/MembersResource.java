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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.Member;
import org.hakunamatata.myhome.service.MemberService;

@Path("/members")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MembersResource {

    @Context 
    UriInfo uriInfo;
    MemberService memberService = new MemberService();

    @GET
    public List<Member> getMembers() {
	return memberService.getAllMembers();
    }

    @GET
    @Path("/{memberId}")
    public Member getMember(@PathParam("memberId") long id) {
	Member member = memberService.getMember(id);
	member.addLink(getSelfUri(id).toString(), "self");
	member.addLink(getFavouritesUri(id).toString(), "favourites");
	return member;
    }
    
    private URI getSelfUri(long id) {
	return uriInfo
		.getAbsolutePath();
    }

    private Object getFavouritesUri(long id) {
	return uriInfo
		.getBaseUriBuilder()
		.path(MembersResource.class)
		.path(MembersResource.class,"getFavouritesResource")
		.resolveTemplate("memberId", id)
		.build();
    }

    @POST
    public Member addMember(Member member) {
	return memberService.addMember(member);
    }

    @PUT
    @Path("/{memberId}")
    public Member updateMember(Member member, @PathParam("memberId") long id) {
	member.setMemberId(id);
	return memberService.updateMember(member);
    }

    @DELETE
    @Path("/{memberId}")
    public void deleteMember(@PathParam("memberId") long id) {
	memberService.deleteMember(id);
    }

    @Path("/{memberId}/favourites")
    public FavouritesResource getFavouritesResource() {
	return new FavouritesResource();
    }

}
