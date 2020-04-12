package tech.donau;

import tech.donau.data.Weather;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weather")
public class WeatherResource {

    @Inject
    WeatherService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Weather getWeather(@QueryParam("city") String city) {
        return service.provideWeather(city);
    }

    @DELETE
    public Response deleteWeatherCache(@QueryParam("city") String city) {
        if(city == null) {
            service.invalidateAll();
        } else {
            service.invalidate(city);
        }
        return Response.ok().build();
    }
}