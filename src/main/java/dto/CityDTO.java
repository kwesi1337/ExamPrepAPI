/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author josephawwal
 */
public class CityDTO {
    
    private String name;
    private String countryCode;
    private String district;
    private int population;
    
    
    public CityDTO(String name, String district, int population){
        
        this.name = name;
        this.district = district;
        this.population = population;
    }
    
    public CityDTO(String name, String countryCode, String district, int population){
        
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
        
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
    
}
