package zorg.hello.scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptTest {


	private static Logger logger = Logger.getGlobal();

	public static void main(String[] args) {
		// extracted();
	}

	private static void extracted() {
		Bean bean = new Bean();
		 bean.setF1(12);
		 bean.setF2(4.3);
		
		
		 ScriptEngineManager manager = new ScriptEngineManager();
		 ScriptEngine engine = manager.getEngineByName("js");
		
		
		 // Bindings bindings = engine.createBindings();
		 // bindings.put("bean", bean);
		 // engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		
		
		 String jsPath = "D:\\JavaNashornTest.js";
		 try (Reader reader = new FileReader(jsPath)) {
		 engine.eval(reader);
		 long start = System.currentTimeMillis();
		 Invocable in = (Invocable) engine;
		 for (int i = 0; i < 10000; i++) {
		 Object object = in.invokeFunction("f", bean, i);
		 System.out.print(object);
		 }
		 System.out.println("time:" + (System.currentTimeMillis() - start));
		
		 } catch (IOException | ScriptException | NoSuchMethodException e) {
		 e.printStackTrace();
		 }
		 long start = System.currentTimeMillis();
		 for (int i = 0; i < 10000; i++) {
		 double d = bean.getF1() * bean.getF2() + i;
		 System.out.print(d);
		 }
		 System.out.println("time:" + (System.currentTimeMillis() - start));
		Path path = Paths.get("D:\\JavaNashornTest.js");
		try (BufferedReader br = Files.newBufferedReader(path,
				StandardCharsets.UTF_8);
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						"D://TestResult.txt", "rw");) {
			MappedByteBuffer out = randomAccessFile.getChannel().map(
					FileChannel.MapMode.READ_WRITE, 0, 0xffffff);
			String join = String.join("", "a", "b", "c");
			System.out.println(join);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 Class<?> c = ScriptTest.class;
		 Method method;
		 try {
		 method = c.getMethod("func1");
		 Annotation[] annotations = method.getAnnotations();
		 System.out.println(Arrays.toString(annotations));
		 } catch (final ReflectiveOperationException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

}

interface I {
	int get();
}
