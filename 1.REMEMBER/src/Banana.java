
public class Banana {
	
public String str1;
private Boolean b1;
private long l1;

	public String getStr1() {
	return str1;
}


public void setStr1(String str1) {
	this.str1 = str1;
}


public Boolean getB1() {
	return b1;
}


public void setB1(Boolean b1) {
	this.b1 = b1;
}


public long getL1() {
	return l1;
}


public void setL1(long l1) {
	this.l1 = l1;
}


	@Override
public String toString() {
	return "Banana [str1=" + str1 + ", b1=" + b1 + ", l1=" + l1 + "]";
}


	public static void main(String[] args) {
		Banana b1 = new Banana();
		b1.setB1(false);
		b1.setL1(10000000);
		b1.setStr1("hooooolaaaa");
		System.out.println(b1);
	}

}
