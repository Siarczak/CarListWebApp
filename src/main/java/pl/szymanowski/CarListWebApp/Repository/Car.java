package pl.szymanowski.CarListWebApp.Repository;

import javax.validation.constraints.NotNull;

public class Car  {



    private long id;

    @NotNull(message = "Cannot be null")
    private String brand;
    @NotNull(message = "Cannot be null")
    private String model;

    @NotNull(message = "Must be available color")
    private Color color;

    public Car(long id, String brand, String model, Color color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
