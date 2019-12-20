package macro.others;
import java.util.Date;

import macro.macro.TextTransfer;
import macro.macro.Utility;

class mainTextTransfer {
	/** Simple test harness. */
	public static void main(String... args) {
		TextTransfer textTransfer = new TextTransfer();

		// display what is currently on the clipboard
		Utility.log("Clipboard contains:" + textTransfer.getClipboardContents());

		// change the contents and then re-display
		textTransfer.setClipboardContents(Utility.getCompleteDateToString(new Date()) + " blah, blah, blah ");
		Utility.log("Clipboard contains:" + textTransfer.getClipboardContents());
	}

}