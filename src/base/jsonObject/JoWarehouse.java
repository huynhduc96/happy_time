
/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoWarehouse {

    @SerializedName("grass")
    @Expose
    private Integer grass;
    @SerializedName("egg")
    @Expose
    private Integer egg;
    @SerializedName("meat")
    @Expose
    private Integer meat;
    @SerializedName("milk")
    @Expose
    private Integer milk;
    @SerializedName("medicine_normal")
    @Expose
    private Integer medicineNormal;
    @SerializedName("medicine_special")
    @Expose
    private Integer medicineSpecial;
    @SerializedName("food_normal")
    @Expose
    private Integer foodNormal;
    @SerializedName("food_special")
    @Expose
    private Integer foodSpecial;
    @SerializedName("dog")
    @Expose
    private Integer dog;
    @SerializedName("cat")
    @Expose
    private Integer cat;
    @SerializedName("chicken")
    @Expose
    private Integer chicken;
    @SerializedName("cow")
    @Expose
    private Integer cow;
    @SerializedName("ostric")
    @Expose
    private Integer ostric;
    @SerializedName("pig")
    @Expose
    private Integer pig;

    public Integer getGrass() {
        return grass;
    }

    public void setGrass(Integer grass) {
        this.grass = grass;
    }

    public Integer getEgg() {
        return egg;
    }

    public void setEgg(Integer egg) {
        this.egg = egg;
    }

    public Integer getMeat() {
        return meat;
    }

    public void setMeat(Integer meat) {
        this.meat = meat;
    }

    public Integer getMilk() {
        return milk;
    }

    public void setMilk(Integer milk) {
        this.milk = milk;
    }

    public Integer getMedicineNormal() {
        return medicineNormal;
    }

    public void setMedicineNormal(Integer medicineNormal) {
        this.medicineNormal = medicineNormal;
    }

    public Integer getMedicineSpecial() {
        return medicineSpecial;
    }

    public void setMedicineSpecial(Integer medicineSpecial) {
        this.medicineSpecial = medicineSpecial;
    }

    public Integer getFoodNormal() {
        return foodNormal;
    }

    public void setFoodNormal(Integer foodNormal) {
        this.foodNormal = foodNormal;
    }

    public Integer getFoodSpecial() {
        return foodSpecial;
    }

    public void setFoodSpecial(Integer foodSpecial) {
        this.foodSpecial = foodSpecial;
    }

    public Integer getDog() {
        return dog;
    }

    public void setDog(Integer dog) {
        this.dog = dog;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public Integer getChicken() {
        return chicken;
    }

    public void setChicken(Integer chicken) {
        this.chicken = chicken;
    }

    public Integer getCow() {
        return cow;
    }

    public void setCow(Integer cow) {
        this.cow = cow;
    }

    public Integer getOstric() {
        return ostric;
    }

    public void setOstric(Integer ostric) {
        this.ostric = ostric;
    }

    public Integer getPig() {
        return pig;
    }

    public void setPig(Integer pig) {
        this.pig = pig;
    }

}