public class PlayerToken {

    private String colour;//Red or Yellow
    public PlayerToken(String colour){
        //Assigning parameter to its instance variable
        this.colour =colour;
    }

    public String getColour(){
        return colour;
    }

    //Convert the strings to colours
    public String getColourToken(){
        if (colour.equals("RED")){
            return "\033[41m  \033[0m";//red token
        }
        else if(colour.equals("YELLOW")){
            return "\033[43m  \033[0m";//yellow token
        }
        else {
            return "\033[47m  \033[0m"; // white token for empty cells
        }
    }

    public static PlayerToken getEmptyToken() {
        return new PlayerToken("WHITE");
    }
    @Override
    public String toString() {
        return getColourToken();
    }

}
