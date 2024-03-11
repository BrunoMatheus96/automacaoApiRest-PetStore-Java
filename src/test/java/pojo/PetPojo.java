package pojo;

import java.util.List;

public class PetPojo {
        private Integer id;
        private CategoryPojo category;
        private String name;
        private List<String> photoUrls;
        private List<TagPojo> tags;
        private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryPojo getCategory() {
        return category;
    }

    public void setCategory(CategoryPojo category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagPojo> getTags() {
        return tags;
    }

    public void setTags(List<TagPojo> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPhotosUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
