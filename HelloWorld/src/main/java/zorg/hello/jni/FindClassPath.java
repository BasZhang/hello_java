package zorg.hello.jni;

public class FindClassPath {
	public static void main(String[] args) {
		// 类路径
		String str = System.getProperty("java.library.path");
		System.out.println(str);
	}
}
