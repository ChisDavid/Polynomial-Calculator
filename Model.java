import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// CalcModel.java
// Fred Swartz - December 2004
// Model
//     This model is completely independent of the user interface.
//     It could as easily be used by a command line or web interface.


public class Model {
    //... Constants
    static final String INITIAL_VALUE = "1";
    private Polinom polinom_curent;
    private Polinom rezultat;  
    private Polinom cat,rest;
  /*  public Polinom string_to_polinom(String a)
	{
		
		a=a.replace("-","+-");
		a=a.replace(" ","");
		//System.out.println(a);
		String [] arr = a.split("\\+");
		ArrayList <monom> rez = new ArrayList <monom>();
		for(String i:arr)
		{
			if(!i.isEmpty())
			{
				if(i.charAt(0)=='x')
				{
					i=1+i;
				}
				if(i.contains("x")==false)
				{
					i=i+"x^0";
				}
				if(i.contains("x") && i.contains("^")==false)
				{
					i=i+"^1";
				}
				if(i.charAt(0)=='-' && i.charAt(1)=='x')
				{
					i=i.replace("-", "-1");
				}
				String arr2[]= i.split("x\\^");
				rez.add(new monom(Double.parseDouble(arr2[0]),Integer.parseInt(arr2[1])));
			}
		//	System.out.println(i);
		
		
		}
	
			//System.out.println(i);}   
	//	System.out.println(rez.get(0).getCoef());
		Polinom rez1 = new Polinom(rez);
		rez1.reducere_termeni_asemenea();
		
	rez1.sortare_dupa_grad();
//	System.out.println(rez.get(0).getGrad());
	rez1.setGrad(rez.get(0).getGrad());
		return rez1; 
		
	}
 */
    
 public Polinom convertire(String s)
 {
	 //x^2+1x^1+1x^0
	// System.out.print(s+"\n");
	 Pattern p = Pattern.compile("([-]?(?:(?:\\d+(\\.\\d+)?+x\\^\\d+)|(x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
	 Matcher m = p.matcher(s);
	 int nr=0;
	 ArrayList<monom >rez = new ArrayList<monom>();
	 while(m.find())
	 {
		String i=m.group(1);
		if(i.contains("^")==false && i.contains("x")==true)
		{
			i=i+"^1";
		}
		if(i.charAt(0)=='x')
		{
			i=1+i;
		}
		if(i.contains("x")==false)
		{
			i=i+"x^0";
		}
		
		String arr[]=i.split("x\\^");
	
		rez.add(new monom(Double.parseDouble(arr[0]),Integer.parseInt(arr[1])));
		
	 }
	 Polinom pol = new Polinom(rez);
	 pol.reducere_termeni_asemenea();
	 if(!pol.getMonom().isEmpty())
	 { pol.setGrad(rez.get(0).getGrad());}
	 pol.sortare_dupa_grad();
	 return pol;


	 
 }
    
   
    public void inmultire(Polinom p) 
    {
        rezultat = polinom_curent.produs(p);
    }
    public void impartire(Polinom p) 
    {
    	try {
       cat = polinom_curent.impartire(p).get(0);
       rest = polinom_curent.impartire(p).get(1);}
    	catch(ExImposibil e)
    	{
    		System.out.println(e);
    	}
    }
    public void derivare() 
    {
        rezultat = polinom_curent.derivare();
    }
    public void adunare(Polinom p) {
    	
        rezultat = polinom_curent.suma(p);}
    	
    
    public void scadere(Polinom p){
        rezultat = polinom_curent.diferenta(p);
    }
    public void integrare ()
    {
        rezultat = polinom_curent.integrare();
    }
	public void setValue( Polinom p) {
		
		rezultat =p;
	}

	
}
