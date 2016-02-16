package zorg.hello.unknown;

import static java.lang.System.err;

public class SuppressedExceptionDemoWithTryCatch {
	public static void memberFunction() throws Exception {
		try (DirtyResource resource = new DirtyResource()) {
			resource.accessResource();
		}
	}

	/**
	 * Executable member function demonstrating suppressed exceptions using
	 * try-with-resources
	 */
	public static void main(String[] arguments) throws Exception {
		try {
			memberFunction();
		} catch (Exception ex) {
			err.println("Exception encountered: " + ex.toString());
			final Throwable[] suppressedExceptions = ex.getSuppressed();
			final int numSuppressed = suppressedExceptions.length;
			if (numSuppressed > 0) {
				err.println("tThere are " + numSuppressed + " suppressed exceptions:");
				for (final Throwable exception : suppressedExceptions) {
					err.println("tt" + exception.toString());
				}
			}
		}
	}
}