package net.javaguides.springboot.model;


import javax.persistence.*;

@Entity
@Table(name = "plotRegistration")
public class PlotRegistration {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String district ;

    private String sector;

    private String category;

    private Boolean carCharger;

    private Boolean heavyDuty;

    private Boolean industrialAppliance;

    private String plotNumber;

    private String PlotOwner;

    private Boolean isUploded=Boolean.FALSE;

    public PlotRegistration() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getCarCharger() {
        return carCharger;
    }

    public void setCarCharger(Boolean carCharger) {
        this.carCharger = carCharger;
    }

    public Boolean getHeavyDuty() {
        return heavyDuty;
    }

    public void setHeavyDuty(Boolean heavyDuty) {
        this.heavyDuty = heavyDuty;
    }

    public Boolean getIndustrialAppliance() {
        return industrialAppliance;
    }

    public void setIndustrialAppliance(Boolean industrialAppliance) {
        this.industrialAppliance = industrialAppliance;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getPlotOwner() {
        return PlotOwner;
    }

    public void setPlotOwner(String plotOwner) {
        PlotOwner = plotOwner;
    }

    public Boolean getUploded() {
        return isUploded;
    }

    public void setUploded(Boolean uploded) {
        isUploded = uploded;
    }

    public PlotRegistration(String userName, String district, String sector, String category, Boolean carCharger, Boolean heavyDuty, Boolean industrialAppliance, String plotNumber, String plotOwner, Boolean isUploded) {
        this.userName = userName;
        this.district = district;
        this.sector = sector;
        this.category = category;
        this.carCharger = carCharger;
        this.heavyDuty = heavyDuty;
        this.industrialAppliance = industrialAppliance;
        this.plotNumber = plotNumber;
        PlotOwner = plotOwner;
        this.isUploded = isUploded;
    }
}
