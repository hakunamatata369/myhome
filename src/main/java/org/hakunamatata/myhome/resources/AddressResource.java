package org.hakunamatata.myhome.resources;

import java.util.List;

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

import org.hakunamatata.myhome.model.Address;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.AddressService;

@Path("/addresses")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class AddressResource {

	@Context
	private UriInfo uriInfo;
	@PathParam("addressId")
	private long addressId;
	private ResponseMessage responseMessage;
	private AddressService addressService = new AddressService();

	@GET
	public List<Address> getAddresses() {
		return addressService.getAllAddresses();
	}

	@GET
	@Path("/{addressId}")
	public Address getAddress() {
		return addressService.getAddress(addressId);
	}

	@POST
	public Response addAddress(Address address) {
		Address newAddress = addressService.addAddress(address);
		responseMessage = new ResponseMessage("Successfully added the address", 201);
		return Response.created(uriInfo.getAbsolutePath()).entity("Successfully added the address").entity(newAddress)
				.build();
	}

	@PUT
	@Path("/{addressId}")
	public Address updateAddress(Address address) {
		address.setAddressId(addressId);

		Address updateAddress = addressService.updateAddress(address);

		return updateAddress;
	}

	@DELETE
	@Path("/{addressId}")
	public Response deleteAddress() {
		responseMessage = new ResponseMessage("Successfully deleted the address with addressId :" + addressId, 200);
		addressService.deleteAddress(addressId);
		return Response.ok(responseMessage).build();
	}

	@DELETE
	public Response deleteAddresses() {
		responseMessage = new ResponseMessage("Successfully deleted all the addresses", 200);
		addressService.deleteAllAddresses();
		return Response.ok(responseMessage).build();
	}
}
