
public class About {
    Profile owner;
    String version = "0.1";


    public About(Profile owner) {
        this.owner = owner;

    }

    public Profile getOwner() {
        return this.owner;
    }

    public String getVersion() {
        return this.version;
    }

}
