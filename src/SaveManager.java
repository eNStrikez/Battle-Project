import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class SaveManager {
	public SaveManager(){
		
	}
	
	public Obstruction[][] loadBlockages(String mapName){
		Obstruction[][] loadedObstructions = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(mapName)));
			try {
				loadedObstructions = (Obstruction[][]) inputStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loadedObstructions;
	}
	
	public void saveBlockages(String mapName, Obstruction[][] toSave) throws ClassNotFoundException{
		
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("U:/Years/Year 10/Battle Prototype 2 Dykstras/" + mapName));
			System.out.println("Start save");
			outputStream.writeObject(toSave);
			System.out.println("End Save");
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
