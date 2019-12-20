package timezomeTeste;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Prin {
	public static void main(String[] args) {

		//  Debug configuration JVM
		// -Duser.timezone=UTC-3
		
		System.out.println(System.getProperty("user.timezone"));
        System.out.println(new Date());
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(new GregorianCalendar(TimeZone.getTimeZone("GMT-3") , new Locale("pt_BR")).getTime());

        
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Prin() {
		super();
	}

}