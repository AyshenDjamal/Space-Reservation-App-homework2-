import java.io.Serializable;

public class CoworkingSpaces implements Serializable {
    private static final long serialVersionUID = 1L;
    private int spaceID;
    private String spaceType;
    private double pricePerHour;
    private boolean isAvailable;

    public CoworkingSpaces(int spaceID, String spaceType, double pricePerHour, boolean isAvailable){
        this.spaceID = spaceID;
        this.spaceType = spaceType;
        this.pricePerHour = pricePerHour;
        this.isAvailable = isAvailable;
    }

    public int getSpaceID(){
        return spaceID;
    }

    public void setSpaceID(int spaceID) {
        this.spaceID = spaceID;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
