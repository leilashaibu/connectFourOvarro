public class Player {
    private  PlayerToken token;
    //Initialising the players token
    public Player (String colour){
        this.token = new PlayerToken(colour);
    }
    public PlayerToken getToken() {
        return token;
    }

}
