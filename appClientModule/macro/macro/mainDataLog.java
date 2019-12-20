package macro.macro;
import java.util.Date;

class mainDataLog {
	public static void main(String... args) {
		TextTransfer textTransfer = new TextTransfer();

		// change the contents and then re-display
		textTransfer.setClipboardContents(Utility.getDateToStringLog(new Date()));
		Utility.log("Clipboard contains:" + textTransfer.getClipboardContents());
	}

}