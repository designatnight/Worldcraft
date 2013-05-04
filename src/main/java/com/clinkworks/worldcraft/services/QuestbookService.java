package com.clinkworks.worldcraft.services;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.clinkworks.worldcraft.api.Questbook;
import com.clinkworks.worldcraft.domain.Quest;
import com.clinkworks.worldcraft.domain.QuestCategory;
import com.clinkworks.worldcraft.domain.QuestID;
import com.clinkworks.worldcraft.util.NonPersistantSequenceGenerator;
import com.google.inject.Inject;



@Singleton
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Path("worldcraft/quests")
public class QuestbookService {

	private Questbook questbook;
	
	private Logger logger = Logger.getLogger(QuestbookService.class);
	
	
	@Inject
	private NonPersistantSequenceGenerator sequenceGenerator;
	
	@Inject
	private QuestCategoryService questCategoryService;
	
    @Inject
    public QuestbookService(Questbook questBook) {
        this.questbook = questBook;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuests() {
        return build200ServiceResponse(questbook.getQuests());
    }
    
    private Response build200ServiceResponse(Object payload){
    	return Response.status(200).entity(payload).build();
    }
    
    /**
     * 
     * @param quest to delete
     * @return the deleted quest
     */
    @DELETE
    @Path("/manage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteQuest(Quest quest){
    	questbook.removeQuest(quest);
    	System.out.println("         A delete response was received by the server      ");
    	return Response.status(200).entity(quest).build();
    }
    
    @POST
	@Path("/manage")
	public Response addQuest(@QueryParam("name") String name, @QueryParam("text") String text, @QueryParam("categoryID") String categoryID) {
    	QuestID questID = sequenceGenerator.generateSequence(QuestID.class);
    	QuestCategory category = questCategoryService.getQuestCategoryByID(categoryID);
    	Quest newQuest = new Quest(questID, category);
    	
    	newQuest.setQuestName(name);
    	newQuest.setQuestDescription(text);
		
    	questbook.addQuest(newQuest);
    	Response response = Response.status(201).entity(newQuest).build();
    	
    	logger.debug("addQuest response payload: " + response.getEntity());
    	
		return response;
	}
    
    @PUT
    @Path("/manage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateQuest(Quest quest){
    	
    	if(quest.getQuestID() == null){
    		System.out.println("       An attempt to modify a non existant quest failed         ");
    		return Response.status(404).build();
    	}
    	
    	Quest serverSideQuest = questbook.getQuestByQuestID(quest.getQuestID());
    	
    	serverSideQuest.shallowCopyQuest(quest);

    	return Response.status(200).entity(serverSideQuest).build();
    }
}
