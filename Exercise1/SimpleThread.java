
public class SimpleThread implements Runnable{

	Resource resource;
	
	public void run() {
		for(int i = 0; i<10; i++){
			try {
			System.out.println(resource.increment());
			
			Thread.sleep(1);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	SimpleThread(Resource resource){
		this.resource = resource;
	}

	public static void main(String args[]) {
		Resource resource = new Resource();
		SimpleThread runnable = new SimpleThread(resource);
		Thread t = new Thread(runnable);
		Thread s = new Thread(runnable);
		
		t.start();
		s.start();
	}

}
