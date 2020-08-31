package tech.iwish.shyafinance.Lists;

public class OfferLists {
    String id,title,image,discription;
    public OfferLists(String id,String title,String image, String discription){
     this.id=id;
     this.title=title;
     this.image=image;
     this.discription=discription;
    }

    public void setId(String id){
        this.id=id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setImage(String image){
        this.image=image;
    }
    public void setDiscription(String discription){
        this.discription=discription;
    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getImage(){
        return image;
    }

    public String getDiscriptiont(){
        return discription;
    }
}
