package app;

public class items {
    String name = "";
    String ingredients = "";
    String picture = "";
    int price = 0;

    items(String name, String ingredients, String picture, int price){
        this.name = name;
        this.ingredients = ingredients;
        this.picture = picture;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public String getIngredients(){
        return this.ingredients;
    }

    public String getPicture(){
        return this.picture;
    }

    public int getPrice(){
        return this.price;
    }

    public String toString() {
        return this.name;
    }
}
