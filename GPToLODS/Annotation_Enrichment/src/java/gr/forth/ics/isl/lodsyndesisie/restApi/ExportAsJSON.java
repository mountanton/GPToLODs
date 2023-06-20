
/*  This code belongs to the Semantic Access and Retrieval (SAR) group of the
 *  Information Systems Laboratory (ISL) of the
 *  Institute of Computer Science (ICS) of the
 *  Foundation for Research and Technology - Hellas (FORTH)
 *  Nobody is allowed to use, copy, distribute, or modify this work.
 *  It is published for reasons of research results reproducibility.
 *  (c) 2020 Semantic Access and Retrieval group, All rights reserved
 */
package gr.forth.ics.isl.lodsyndesisie.restApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
//import org.json.simple.JSONObject;

@Path("/exportAsJSON")
public class ExportAsJSON extends ArrayList<String> {

    Functionality func = new Functionality();

    @GET
    @Path("/")
    @Produces({"application/json"})
    public Response getE(@QueryParam("text") String text, @QueryParam("ERtools") String tool, @QueryParam("equivalentURIs") String equiv) {
        Response.Status status = Response.Status.OK;
        System.out.println(tool);
        boolean equivalent = false;

        if (equiv != null && equiv.equals("true")) {
            equivalent = true;
        }
        if (text == null) {
            status = Response.Status.NOT_FOUND;
            //   JSONObject job=new JSONObject();
            //  job.put("error","Unknown Query Parameter. Only the following query parameters are accepted: [uri,provenance (optional)]");
            return Response.status(status).type("application/json").entity("{'error':'text is null'").build();
        } else {

            String output = "";
            try {
                output = func.text2JSON(text, tool,equivalent);

            } catch (IOException ex) {
                Logger.getLogger(ExportAsJSON.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ExportAsJSON.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response.status(status).type("application/json").entity(output).build();

        }
//        else{
//            if (acceptHeader != null && (acceptHeader.equalsIgnoreCase("application/xml"))) {
//                List<Entity> triples = all.getURIsXML(uri);
//                GenericEntity<List<Entity>> list = new GenericEntity<List<Entity>>(triples) {
//                };
//                return Response.status(status).type(MediaType.APPLICATION_XML).entity(list).build();
//            } 
//            else if (acceptHeader != null && (acceptHeader.equalsIgnoreCase("application/json"))) {
//                String triples = all.getURIsJson(uri);
//                
//                return  Response.status(status).type(MediaType.APPLICATION_JSON).entity(triples).build();
//            } 
//            
//            else {
//                return Response.status(status).type("application/n-triples").entity(all.getURIsPlain(uri)).build();
//                //return Response.status(status).entity(all.getURIsPlain(uri)).build();
//            }
//        }
    }
}
