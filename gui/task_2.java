import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;

class Window extends JFrame {

  JTextField input = new JTextField();
  JTextArea results = new JTextArea("Results", 4, 1);
  JButton button = new JButton("Validate");

  public static void main(String[] args) {
    new Window();
  }

  public Window() {
    this.setTitle("Task 2");
    this.setSize(640, 480);
    this.setVisible(true);
    FlexColumn fc = new FlexColumn(this);
    fc.setPaddingX(100);

    LabelledInput field = new LabelledInput(new JLabel("Credit Card Number"), input, fc.getLocation(), fc.getWidth());
    results.setEditable(false);
    this.configureButton();

    fc.add(field, 0, 100);
    fc.add(button, 30, 75);
    fc.add(results, 30, 100);
    this.add(fc);
  }

  private void configureButton() {
    this.button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        results.setText(CreditCardValidator.validate(input.getText().trim()));
        try (FileWriter fw = new FileWriter(new File("attempts.txt"), true)) {
          fw.append("Attempting \"" + input.getText().trim() + "\". result:  \"" + results.getText() + "\"\n");
        } catch (Exception e) {
          results.append("Failed to write attempt to file: " + e.getMessage() + "\n");
        }
      }
    });
  }
}

class FlexColumn extends JComponent {

  private JFrame parent;
  private int paddingX = 25;
  private int paddingTop = 25;
  private int gap = 20;
  private int lastY = 0;

  public FlexColumn(JFrame parent) {
    this.parent = parent;
    this.setBounds(parent.getBounds());
  }

  public void setPaddingX(int paddingX) {
    this.paddingX = paddingX;
  }

  public void setPaddingTop(int paddingTop) {
    this.paddingTop = paddingTop;
  }

  public void setGap(int gap) {
    this.gap = gap;
  }

  public void add(JComponent component, int height, int widthPercentage) {
    Point location = new Point(this.centerX(calculateWidth(widthPercentage)), this.calculateY());
    if (component instanceof Refreshable) {
      ((Refreshable) component).refresh(location, this.calculateWidth(widthPercentage));
    } else {
      component.setLocation(location);
      component.setSize(this.calculateWidth(widthPercentage), height);
    }

    this.lastY = component.getY() + component.getHeight();
    super.add(component);
  }

  public int calculateY() {
    return this.lastY == 0 ? paddingTop : this.lastY + this.gap;
  }

  public int centerX(int width) {
    return paddingX + (((this.parent.getWidth() - (2 * paddingX)) - width) / 2);
  }

  public int calculateWidth(int percentage) {
    double width = (this.parent.getWidth() - (2 * paddingX)) * ((double) percentage / 100);
    return (int) width;
  }

}

class LabelledInput extends JComponent implements Refreshable {

  JLabel label;
  JTextField input;
  int gap = 10;

  public LabelledInput(JLabel label, JTextField input, Point location, int width) {
    this.label = label;
    this.input = input;
    this.add(label);
    this.add(input);
    this.refresh(location, width);
  }

  public void refresh(Point location, int width) {
    this.label.setLocation(0, 0);
    this.label.setSize(width, 20);
    this.input.setLocation(0, this.label.getHeight() + this.gap);
    this.input.setSize(width, 30);
    this.setLocation(location);
    this.setSize(width, this.label.getHeight() + this.gap + this.input.getHeight());
  }

}

interface Refreshable {
  public void refresh(Point location, int width);
}