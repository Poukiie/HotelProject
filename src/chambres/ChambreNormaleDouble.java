package chambres;

public class ChambreNormaleDouble extends Chambre {
    private final boolean balcon;
    private final boolean wifiGratuit;

    public ChambreNormaleDouble() {
        super("double", 105.30, 1, false);
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
