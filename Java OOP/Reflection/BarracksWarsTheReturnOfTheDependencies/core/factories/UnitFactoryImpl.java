package OOP.Reflection.pr0304Barracks.core.factories;

import OOP.Reflection.pr0304Barracks.contracts.Unit;
import OOP.Reflection.pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "OOP.Reflection.pr0304Barracks.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        Unit unit = null;
        try {
            Class<? extends Unit> unitClass = (Class<? extends Unit>)
                    Class.forName(UnitFactoryImpl.UNITS_PACKAGE_NAME + unitType);
            Constructor<? extends Unit> constructor = unitClass.getDeclaredConstructor();
            unit = constructor.newInstance();
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return unit;

    }
}
