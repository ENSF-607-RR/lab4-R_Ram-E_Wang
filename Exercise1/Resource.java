
public class Resource {

	int counter;
	
	public synchronized int increment() {
		return counter++;
	}
	
}
