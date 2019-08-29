import java.util.List;

public class Smartphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            if(validateNumbers(number)) {
                result.append(String.format("Calling... %s%n", number ));
            } else {
                result.append(String.format("Invalid number!%n"));
            }
        }
        return result.toString();
    }

    @Override
    public String browse() {
        StringBuilder result = new StringBuilder();
        for (String url : urls) {
            if(validateURLS(url)) {
                result.append(String.format("Browsing: %s!%n", url));
            } else {
                result.append(String.format("Invalid URL!%n"));
            }
        }
        return result.toString();
    }

    private boolean validateURLS(String url) {
        String forbiddenChars = "0123456789";
        return url.matches("[^\\d]+");
    }

    private boolean validateNumbers(String number) {
        if(number.matches("^\\d+$")) {
            return true;
        }
        return false;
    }


}
