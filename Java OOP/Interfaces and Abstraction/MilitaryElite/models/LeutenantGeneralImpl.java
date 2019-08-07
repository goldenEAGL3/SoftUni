package InterfacesAndAbstractions.MilitaryElite.models;
import InterfacesAndAbstractions.MilitaryElite.interfaces.LeutenantGeneral;
import InterfacesAndAbstractions.MilitaryElite.interfaces.Private;

import java.util.TreeSet;

public class LeutenantGeneralImpl extends PrivateImpl implements LeutenantGeneral {
    private TreeSet<Private> privates;

    public LeutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new TreeSet<>((first, second) -> second.getId() - first.getId());
    }

    @Override
    public void addPrivate(Private priv) {
        this.privates.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append("Privates:");
        for (Private aPrivate : privates) {
            sb.append(System.lineSeparator()).append("  ").append(aPrivate.toString());
        }
        return sb.toString();
    }
}
