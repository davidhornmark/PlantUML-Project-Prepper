/**
 * 
 */
package handlers;

import java.io.FileNotFoundException;

import workers.input.Reader;
import workers.translation.Translator;
/**
 * @author David Hörnmark
 *
 */
public class SourceHandler {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Reader read = new Reader("pupp.iuml");
		read.start();
		read.join();
		Translator trans = new Translator(read.getUMLFile());
		System.out.println(trans.toString());
	}
	
}
