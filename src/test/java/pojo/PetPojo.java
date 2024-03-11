package pojo;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Tag;

import java.util.List;

public class PetPojo {
    private Integer petId;
    private String petName;
    private List<String> petPhoto;
    private List<Tag> tags;
    private String status;
    private Category category;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public List<String> getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(List<String> petPhoto) {
        this.petPhoto = petPhoto;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
