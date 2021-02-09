import java.util.ArrayList;



public class mainclass {

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
	Model model = new Model();
	View view = new View (model);
   Controller controller = new Controller(model, view);

	}

}
