/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.CityDTO;
import dto.CountryDTO;
import entity.City;
import entity.Country;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author josephawwal
 */
public class Controller {
    
    private final EntityManagerFactory emf;
    
    public Controller(EntityManagerFactory emf){
        
        this.emf = emf;
    }
    
    
    public List<CountryDTO> getAllCountries(String population){
        
        EntityManager em = getEntityManager();
        List<Country> countries = new ArrayList();
        List<CountryDTO> countriesDTO = new ArrayList();
        
        try {
            
            TypedQuery<Country> query = null;
            if (population.isEmpty()){
                
                query = em.createNamedQuery("Country.findAll", Country.class);
                
            } else {
                
                
                int pop = 0;
                try {
                    
                    pop = Integer.parseInt(population);
                    
                    query = em.createNamedQuery("Country.findByPopulationGreaterThan", Country.class).setParameter("population", pop);
                    
                    
            } catch (NumberFormatException ex){
                    
                    
                    }
        }
            
            countries = query.getResultList();
            System.out.println("countries size: " + countries.size());
            
            if(countries.isEmpty()){
                
                
                
            } else {
                
                for (Country country : countries){
                    
                    String na = "Data not available";
                    if (country.getCode() == null || country.getCode().isEmpty()){
                        
                            country.setCode(na);
                    }
                    
                    if (country.getName() == null || country.getName().isEmpty()){
                        
                        country.setName(na);
                    }
                    
                  //  if (country.getContinent() == null || country.getContinent().isEmpty()){
                    
                //    country.setContinent(na);
               // }
                    
                    if (country.getCapital() == null){
                        
                        country.setCapital(0);
                    }
                    
                    CountryDTO dto = new CountryDTO(country.getCode(), country.getName(), country.getContinent(), country.getCapital());
                    
                }
            }
            
            
        
    }
        
        finally {
            
            em.close();
        }
        
        return countriesDTO;
    
}
    
    public List<CityDTO> getAllCitiesInCountry(String countryCode){
        
        EntityManager em = getEntityManager();
        List<City> cities = new ArrayList();
        List<CityDTO> citiesDTO = new ArrayList();
        Country country = new Country();
        country.setCode(countryCode);
        
        
        try {
            
            TypedQuery<City> query = em.createNamedQuery("City.findByCountryCode", City.class).setParameter("countryCode", country);
            cities = query.getResultList();
            if (cities.isEmpty()){
                
            }
            
            else {
                for (City city : cities){
                    CityDTO dto = new CityDTO(city.getName(), city.getDistrict(), city.getPopulation());
                    citiesDTO.add(dto);
                    
                }
            }
        } 
        finally {
                    
                    em.close();
                    }
        return citiesDTO;
        }
    
    public CityDTO createCity(CityDTO dto){
        
        EntityManager em = getEntityManager();
        List<City> cities = new ArrayList();
        
        try {
            
            TypedQuery<City> query = em.createNamedQuery("City.findByName", City.class).setParameter("name", dto.getName());
            cities = query.getResultList();
            if (cities.isEmpty()){
                
                Country country = em.find(Country.class, dto.getCountryCode());
                if(country == null){
                    
                }
                else {
                    
                    City city = new City();
                    city.setName(dto.getName());
                    city.setDistrict(dto.getDistrict());
                    city.setPopulation(dto.getPopulation());
                    city.setCountryCode(country);
                    em.getTransaction().begin();
                    em.persist(city);
                    em.getTransaction().commit();
                }
            }else {
                
                
            }
            
        }
        finally {
            em.close();
        }
        
        return dto;
    }
    
    private EntityManager getEntityManger(){
        
        return emf.createEntityManager();
        
    }

    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }