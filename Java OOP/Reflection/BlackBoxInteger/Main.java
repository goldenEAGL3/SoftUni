package OOP.Reflection.pr02PrivateClassFiddling;

import OOP.Reflection.pr02PrivateClassFiddling.com.BlackBoxInt;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

		Scanner sc = new Scanner(System.in);

		Constructor<BlackBoxInt> constructor = BlackBoxInt.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		BlackBoxInt blackBoxInt = constructor.newInstance();
		Method[] methods = BlackBoxInt.class.getDeclaredMethods();
		Field innerValue = blackBoxInt.getClass().getDeclaredField("innerValue");
		innerValue.setAccessible(true);
		String input = sc.nextLine();

		while(!"END".equals(input)) {
			String[] data = input.split("_");
			String commandName = data[0];
			int value = Integer.parseInt(data[1]);

			Method method = Arrays.stream(methods)
					.filter(m -> m.getName().equalsIgnoreCase(commandName))
					.findFirst()
					.orElse(null);


			method.setAccessible(true);
			method.invoke(blackBoxInt, value);

			System.out.println(innerValue.get(blackBoxInt));

			input = sc.nextLine();


		}
	}
}
