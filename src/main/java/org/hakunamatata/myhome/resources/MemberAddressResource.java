package org.hakunamatata.myhome.resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hakunamatata.myhome.model.Address;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.AddressService;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MemberAddressResource {

	ResponseMessage responseMessage;
	AddressService addressService = new AddressService();

	@GET
	public Collection<Address> getAllAddresses(@PathParam("memberId") Long memberId) {
		return addressService.getAllMemberAddresses(memberId);
	}

	@PUT
	@Path("/{dataId}")
	public Response addOrRemoveAddress(@PathParam("memberId") Long memberId, @PathParam("dataId") Long dataId,
			@QueryParam("action") String action) {
		if (action.equals("add")) {
			addressService.addMemberAddress(memberId, dataId);
			responseMessage = new ResponseMessage("Successfully added the address", 200);
		} else if (action.equals("remove")) {
			addressService.removeMemberAddress(memberId, dataId);
			responseMessage = new ResponseMessage("Successfully removed the address", 200);
		} else {
			responseMessage = new ResponseMessage("Unknown action specified", 500);
		}

		return Response.ok(responseMessage).build();
	}
}
