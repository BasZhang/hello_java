package zorg.hello.jni;

public class JNI {

	public native void print();

	static {
		// System.load("D:\\where is c\\jniWin32\\x64\\Debug\\jniWin32.dll");
		System.loadLibrary("jniWin32");
	}

	public static void main(String[] args) {
		new JNI().print();
	}
}
