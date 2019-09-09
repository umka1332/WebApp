package demo;

import java.io.Serializable;

public class Dota2 implements Serializable{
	public Dota2() {}
	String[] arr = new String[] {"a", "b", "c", "d", "e"};
	public String[] getArr() {
		return arr;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	public static String[] getArrhg() {
		return new Dota2().getArr();
	}
}
