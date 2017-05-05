
/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoWarehouse {

    @SerializedName("grass")
    @Expose
    private String grass;
    @SerializedName("egg")
    @Expose
    private String egg;
    @SerializedName("meat")
    @Expose
    private String meat;
    @SerializedName("milk")
    @Expose
    private String milk;
    @SerializedName("medicine_normal")
    @Expose
    private String medicineNormal;
    @SerializedName("medicine_special")
    @Expose
    private String medicineSpecial;
    @SerializedName("food_normal")
    @Expose
    private String foodNormal;
    @SerializedName("food_special")
    @Expose
    private String food_sp;
    @SerializedName("cat")
    @Expose
    private String dog;
    @SerializedName("dog")
    @Expose
    private String chicken;
    @SerializedName("food_special")
    @Expose
    private String foodSpecial;

    public String getGrass() {
        return grass;
    }

    public void setGrass(String grass) {
        this.grass = grass;
    }

    public String getEgg() {
        return egg;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public String getMedicineNormal() {
        return medicineNormal;
    }

    public void setMedicineNormal(String medicineNormal) {
        this.medicineNormal = medicineNormal;
    }

    public String getMedicineSpecial() {
        return medicineSpecial;
    }

    public void setMedicineSpecial(String medicineSpecial) {
        this.medicineSpecial = medicineSpecial;
    }

    public String getFoodNormal() {
        return foodNormal;
    }

    public void setFoodNormal(String foodNormal) {
        this.foodNormal = foodNormal;
    }

    public String getFoodSpecial() {
        return foodSpecial;
    }

    public void setFoodSpecial(String foodSpecial) {
        this.foodSpecial = foodSpecial;
    }

}
