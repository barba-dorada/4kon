package app.jersey.resources;

import org.json.JSONObject;
import ru.cwl.kon.model.Fact;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by vad on 30.01.2016 8:14
 * 4kon
 */

@Path("/hello")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "Hello Jersey";
    }

    @Path("/hh")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello2() {
        Fact f = new Fact();
        f.setId(1);
        f.setDate(LocalDate.now());
        f.setAmount(new BigDecimal(22.33));
        f.setAccount("acc1");
        f.setDescription("descr1");
        f.setUser(null);//new User(10, "vad", "aaa", "ddd"));

        JSONObject o=new JSONObject(f);
        //o.put("id", f.getId()).put("amount", f.getAmount()).put("account", f.getAccount());
        return o.toString();//Response.ok(o.build()).build();

    }


}

