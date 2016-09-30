/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityDTO;
import dto.CountryDTO;
import facade.Controller;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author josephawwal
 */

@Path("country")
public class CountryRest {
    
    private final Controller ctrl;
    private final EntityManagerFactory emf;
    private Gson gson;
    
    public CountryRest(){
        
        emf = Persistence.createEntityManagerFactory("PU");
        ctrl = new Controller(emf);
        gson = new GsonBuilder().setPrettyPrinting().create();
        
    }
    
    
    @GET
    @Path("all")
    public Response getCountries(){
        
        List<CountryDTO> allCountries = ctrl.getAllCountries("");
        return Response.ok(gson.toJson(allCountries)).type(MediaType.APPLICATION_JSON).build();
        
    }
    
    
    
    @GET
    @Path("{population}")
    public Response getCountriesPopulation(@PathParam("population") String population){
        
       List<CountryDTO> allCountries = ctrl.getAllCountries("population");
       return Response.ok(gson.toJson(allCountries)).type(MediaType.APPLICATION_JSON).build();
       
    }
    
    @GET
    @Path("city/{countryCode}")
    public Response getCities(@PathParam("countryCode") String countryCode){
        
        List<CityDTO> cities = ctrl.getAllCitiesInCountry(countryCode);
        return Response.ok(gson.toJson(cities)).type(MediaType.APPLICATION_JSON).build();
        
    }
    
    @POST
    @Path("city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCity(String content){
        
        CityDTO dto = ctrl.createCity(gson.fromJson(content, CityDTO.class));
        return Response.ok(gson.toJson(dto)).type(MediaType.APPLICATION_JSON).build();
        
    }
}
