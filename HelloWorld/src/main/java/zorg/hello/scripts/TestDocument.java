package zorg.hello.scripts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class TestDocument {
	
	 ScriptEngineManager manager = new ScriptEngineManager();
	 ScriptEngine engine = manager.getEngineByName("js");
	 
	 public static void main(String[] args) throws Exception {
		TestDocument sut = new TestDocument();
		String script = "console.count();";
		Object eval = sut.engine.eval(script);
		System.out.println(eval);
	}
}
