package macro.others;
import java.util.Date;

import macro.macro.TextTransfer;
import macro.macro.Utility;

class mainDataCompleta {
	public static void main(String... args) {
		TextTransfer textTransfer = new TextTransfer();

		// change the contents and then re-display
		textTransfer.setClipboardContents(Utility.getCompleteDateToString(new Date()));
		Utility.log("Clipboard contains:" + textTransfer.getClipboardContents());
	}

}