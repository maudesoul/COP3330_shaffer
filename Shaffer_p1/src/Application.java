public class Application {
    public static void main(String[] args) {
        String eData = Encrypter.encrypt("1234");
        System.out.println(eData);

        String deData = Decrypter.decrypt("0189");
        System.out.println(deData);
    }
}
