package chambres;

public class ChambreNormaleSimple extends Chambre {
    private final boolean balcon;
    private final boolean wifiGratuit;

    public ChambreNormaleSimple() {
        super("simple", 73.5, 1, false);
        this.balcon = false;
        this.wifiGratuit = true;
    }

    public boolean getBalcon() {
        return balcon;
    }

    public boolean getWifiGratuit() {
        return wifiGratuit;
    }
}
