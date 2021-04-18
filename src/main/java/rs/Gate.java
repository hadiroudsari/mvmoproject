package rs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/gate")
public class Gate {
    @GET
    @Produces("text/html")
    @Path("start/{name}/{id}")
    public String start(@PathParam("name") String name,@PathParam("id") String id) {
        System.out.println(id);
        return "Hello,"+ name+" with a serial "+id;
    }

    @GET
    @Produces("application/json")
    @Path("pooling/{name}/{id}")
    public Common pooling(@PathParam("name") String name,@PathParam("id") String id) {
        System.out.println(id);
        Common c=new Common("ok","false","man","233");
        return c;
    }

    @GET
    @Produces("application/json")
    @Path("attack/{name}/{id}/{time}/{battleId}")
    public Common attack(@PathParam("name") String name
                        ,@PathParam("id") String id
                        ,@PathParam("time") String time
                        ,@PathParam("battleId") String battleID) {
        System.out.println(id);
        Common c=new Common("ok","false","man","null");
        return c;
    }


}