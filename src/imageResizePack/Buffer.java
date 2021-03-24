package imageResizePack;

public class Buffer {
	private int[][] buffer_pic; //variabila in care se stocheaza datele ce trebuie scrise/citite
	private boolean available; // variabila ce ajuta la trecerea intre put si get
	
	public Buffer(int height, int width){ // constructor buffer
		this.buffer_pic = new int [height][width]; 
		available = false; 
	}
	public synchronized int[][] get(){ // metoda de get a bufferului
		while(!available){ 
			try{
				wait(); //asteapta o comanda noua
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		available = false; // dupa citire buffer-ul schimba starea
		notifyAll(); // se anunta participantii de terminarea get-ului
		return buffer_pic; // se pun in folosinta datele din buffer
	}
	
	public synchronized void put(int[][] buffer_img){
		while(available){
			try{
				wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.buffer_pic = buffer_img; // se citeste in buffer
		available = true;
		notifyAll();
		
	}

}
