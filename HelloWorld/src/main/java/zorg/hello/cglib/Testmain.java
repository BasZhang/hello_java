package zorg.hello.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Testmain {
	public static void main(String[] args) {
		Enhancer en = new Enhancer();
		en.setSuperclass(MyClass.class);
		en.setCallback(new MethodInterceptorImpl());
		MyClass mc = (MyClass) en.create();
		mc.method();
	}
}

class MyClass {
	public void method() {
		System.out.println(this.getClass() + "is invoked..");
	}
}

class MethodInterceptorImpl implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method m, Object[] os, MethodProxy mp) throws Throwable {
		System.out.println(this.getClass() + "is invoked..");
		System.out.println(m);
		return mp.invokeSuper(o, os);
	}
}
