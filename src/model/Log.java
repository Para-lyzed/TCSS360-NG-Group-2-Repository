package model;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * model.Log.java
 *
 * @author Cody Dukes
 *
 */
public class Log implements Serializable {
    private String name;
    private LocalDate date;
    private String description;
    private List<Image> images;

    /**
     * model.Log constructs a model.Log object that contains a log name and log date.
     * @param date the date the model.Log was made.
     * @param name the name of the model.Log.
     *
     * @author Cody Dukes
     */
    public Log(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    /**
     * getName returns the model.Log name.
     * @return name the model.Log name.
     *
     * @author Cody Dukes
     */
    public String getName(){
        return this.name;
    }

    /**
     * sets the name of a model.Log.
     * @param name name to set model.Log to.
     *
     * @author Cody Dukes
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getDate returns the model.Log date.
     * @return date the model.Log date.
     *
     * @author Cody Dukes
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * sets the date of a model.Log.
     * @param date date model.Log was made.
     *
     * @author Cody Dukes
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * getDescription returns the model.Log description.
     * @return description the model.Log description.
     *
     * @author Cody Dukes
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * sets the description of a model.Log.
     * @param description description of the model.Log.
     *
     * @author Cody Dukes
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getImages returns the model.Log images.
     * @return images the model.Log images.
     *
     * @author Cody Dukes
     */
    public List<Image> getImages() {
        return this.images;
    }

    /**
     * sets the images of a model.Log.
     * @param images the images associated with a model.Log.
     *
     * @author Cody Dukes
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Adds image to a model.Log.
     * @param image the images to add to the model.Log.
     *
     * @author Cody Dukes
     */
    public void addImage(Image image) {
        this.images.add(image);
    }
}
