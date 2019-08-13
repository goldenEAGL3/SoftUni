package OOP.Reflection.pr0304Barracks;

import OOP.Reflection.pr0304Barracks.contracts.Repository;
import OOP.Reflection.pr0304Barracks.contracts.UnitFactory;
import OOP.Reflection.pr0304Barracks.core.CommandInterpreterImpl;
import OOP.Reflection.pr0304Barracks.core.Engine;
import OOP.Reflection.pr0304Barracks.core.factories.UnitFactoryImpl;
import OOP.Reflection.pr0304Barracks.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Runnable engine = new Engine(new CommandInterpreterImpl(repository, unitFactory));
		engine.run();
	}
}
