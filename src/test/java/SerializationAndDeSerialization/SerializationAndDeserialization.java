package SerializationAndDeSerialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationAndDeserialization {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Creating an object
		Person person = new Person();

		// Serializing the object
		/*- The Process of writing State of an Object to a File is Called Serialization. 
		 * But Strictly Speaking it is the Process of Converting an Object from Java Supported 
		 * form to Either File Supported Form OR Network Supported Form. 
		- By using FileOutputStream and ObjectOutputStream Classes we can Achieve Serialization.
		*/
		FileOutputStream fileOut = new FileOutputStream("person.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(person);
		out.close();
		fileOut.close();
		System.out.println("Serialized data is saved in person.ser");

		/*
		 * Deserializing the object - The Process of Reading State of an Object from a
		 * File is Called DeSerialization. But Strictly Speaking it is the Process of
		 * Converting an Object from Either File OR Network Supported Form into Java
		 * Supported Form. - By using FileInputStream and ObjectInputStream Classes we
		 * can Achieve
		 */
		FileInputStream fileIn = new FileInputStream("person.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Person person1 = (Person) in.readObject();
		System.out.println("Deserialized data : " + person1.age);
		in.close();
		fileIn.close();

		/*
		 * Why do we need serialization and deserialization? 
		 * 
		 * Persisting Data:
		 * Serialization allows objects to be saved to a file or database, allowing them
		 * to be retrieved later, even after the application is closed and restarted.
		 * 
		 * Sending Objects Across a Network: When objects need to be sent over a network
		 * (e.g., in a distributed system), serialization converts the object into a
		 * byte stream that can be transmitted over the network.
		 * 
		 *  Caching and Persistence: Serialized objects can be stored in a cache or database, and
		 * later deserialized for use in the program.
		 * 
         LIMITATIONS
         Performance: Serialization and deserialization can have performance overhead due to 
         the process of converting objects to byte streams and vice versa.
         
         In summary>>>, serialization allows you to save and transmit objects, while 
         deserialization allows you to retrieve and restore those objects back into their 
         original form.
		 */
	}
}
