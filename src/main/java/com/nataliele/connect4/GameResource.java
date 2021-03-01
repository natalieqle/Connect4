package com.nataliele.connect4;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/connect-4")
public class GameResource {
    private Coordinator coordinator;
    private final int gridWidth;
    private final int gridHeight;
    private final int numPlayers;
    private final int winCondition;

    public GameResource(int gridWidth, int gridHeight, int numPlayers, int winCondition) {
        this.coordinator = new Coordinator(gridWidth, gridHeight, numPlayers, winCondition);
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.numPlayers = numPlayers;
        this.winCondition = winCondition;
    }

    @POST
    @Path("/play")
    @Produces(MediaType.APPLICATION_JSON)
    public void dropToken(TokenLocation tokenLocation) {
        coordinator.dropToken(tokenLocation.getColumn());
    }

    @GET
    @Path("/grid")
    @Produces(MediaType.TEXT_HTML)
    public GridView getGrid() {
        return coordinator.getGridView();
    }

    @PUT
    @Path("/restart")
    @Produces(MediaType.APPLICATION_JSON)
    public void restartGame() {
        this.coordinator = new Coordinator(gridWidth, gridHeight, numPlayers, winCondition);
    }
}
