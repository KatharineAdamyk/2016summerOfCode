/**
 * Comments are nice to explain what the class is/what is it for. The class MUST MATCH the file name.
 */
public class HelloWorld{

	/**
	 * You'll learn as you go along, but the @param tags are a way of generating automatic java documentation (called javadocs). Super useful later on. 
	 * 
	 * @param args our arguments passed in from the command line. 
	 * The main method (first line below [12]) is always included.
	 */
	public static void main(String[] args){
		if (args.length > 0){
			System.out.println("Hello " + args[0] + "!");
		}else{
			System.out.println("Hello World!");
		}
	}
}