package aula20191021.datas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class App {
	
	public static void main(String[] args) throws Exception {
		Date nascimento = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		nascimento = sdf.parse("25/12/1999");

		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1999);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 25);
		
		nascimento = c.getTime();
		
		System.out.println(sdf.format(nascimento));
		System.out.println(nascimento);
		System.out.println(nascimento.toLocaleString());
	}
	

}
