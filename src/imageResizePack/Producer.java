package imageResizePack;
import java.awt.image.BufferedImage;

public class Producer extends Thread { //clasa ce produce datele ce vor fi folosite de Consumer Thread
	private Buffer buffer;
	private BufferedImage picture;
	private int[][] picture_part; //o parte (1/4 din imaginea ce va fi folosita
	
	public Producer(Buffer b, BufferedImage _picture){//constructor Producer
		this.buffer = b;
		this.picture = _picture;
		this.picture_part = new int[picture.getHeight()/4 +1][picture.getWidth()];
		System.out.println("Producerul s-a creat");
	}
	
	public void run(){
		System.out.println("Producerul a inceput executia");
		int width = picture.getWidth();
		int height = picture.getHeight();
		for(int k = 0; k < 4; k++){ // secventa in care se pune in buffer cate 1/4 din imagine
			for (int i = (height/4)*k; i < (height/4)*(k+1); i++){
				for (int j = 0; j < width; j++){
					picture_part[i - (height/4)*k][j] = picture.getRGB(j,i);
				}
			}
			buffer.put(picture_part);
			System.out.println("Producer puts: " + (k+1) + " sfert");
			try {
				sleep(1000);
			}catch(InterruptedException e){}
			
		}
		System.out.println("Producer dead"); //se anunta incetarea din run a Producerului
		
	}
}
