package AWT;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class  Double  extends Applet
 implements MouseListener {
 
  public void init() {
    this.addMouseListener(this);
    }
 
  public void paint(Graphics g) {
     g.drawString("Click here", 10,10);
    }

  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {
    System.out.println
      ( "Click at (" + e.getX() + ":" + e.getY() + ")" );
    if (e.getClickCount() == 2)  
      System.out.println( "  and it's a double click!");
    else
      System.out.println( "  and it's a simple click!");
    }
}