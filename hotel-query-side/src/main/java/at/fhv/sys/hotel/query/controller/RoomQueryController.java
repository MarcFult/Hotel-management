package at.fhv.sys.hotel.query.controller;

import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import at.fhv.sys.hotel.models.RoomsQueryModel;
import at.fhv.sys.hotel.projection.RoomProjection;
import at.fhv.sys.hotel.service.RoomService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomQueryController {

    @Inject
    RoomService roomService;

    @Inject
    RoomProjection roomProjection;

    @GET
    @Path("/freeRooms")
    public List<RoomsQueryModel> getFreeRooms(@QueryParam("startDate") String startDateStr,
                                              @QueryParam("endDate") String endDateStr,
                                              @QueryParam("minCapacity") int minCapacity) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return roomService.getFreeRooms(startDate, endDate, minCapacity);
    }

    @GET
    @Path("/rooms")
    public List<RoomsQueryModel> getAllRooms() {
        return roomService.getAllRooms();
    }


    @POST
    @Path("/roomCreated")
    public Response handleRoomCreated(RoomCreated event) {
        roomProjection.processRoomsCreatedEvent(event);
        return Response.ok().build();
    }



}
