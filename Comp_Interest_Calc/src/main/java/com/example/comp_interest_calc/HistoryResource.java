package com.example.comp_interest_calc;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("HistoryResource")
public class HistoryResource {

    @GET
    @Path("JSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistoryItem> getHistoryJSON() {
        List<HistoryItem> items = new ArrayList<>();
        HashMap<Integer, HistoryItem> historyItems = HistoryItemRepo.getItems();
        for (HistoryItem item : historyItems.values()) {
            items.add(item);
        }

        return items;
    }

    @GET
    @Path("XML")
    @Produces(MediaType.APPLICATION_XML)
    public List<HistoryItem> getHistoryXML() {
        List<HistoryItem> items = new ArrayList<>();
        HashMap<Integer, HistoryItem> historyItems = HistoryItemRepo.getItems();
        for (HistoryItem item : historyItems.values()) {
            items.add(item);
        }

        return items;
    }
}