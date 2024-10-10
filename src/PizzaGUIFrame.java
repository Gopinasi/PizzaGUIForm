import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PizzaGUIFrame extends JFrame {
    JPanel topPanel = new JPanel();

    JPanel mainPanel;
    JPanel crustPanel;
    JPanel sizePanel;
    JPanel toppingsPanel;
    JPanel displayPanel;
    JPanel controlPanel;

    JTextArea display;
    JScrollPane scroll;


    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepdish;

    JComboBox size;
    HashMap sizeCost;
    double cost;
    double toppingsCost = 1;
    double toppingsCount = 0;

    JCheckBox eyeballs;
    JCheckBox worms;
    JCheckBox dragon;
    JCheckBox blood;
    JCheckBox monster;
    JCheckBox scales;
    JCheckBox breath;
    JCheckBox goblin;

    JButton order;
    JButton clear;
    JButton quit;

    String receipt;

    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,2));

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = (int) (screenSize.width/4.5);
        int height = (int) (screenSize.height*0.17);

        setLocation(width, height);

        createCrustPanel();
        createSizePanel();
        createToppingsPanel();

        mainPanel.add(topPanel);

        createDisplayPanel();
        mainPanel.add(displayPanel);

        createControlPanel();
        mainPanel.add(controlPanel);

        add(mainPanel);

        setSize(800, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createCrustPanel() {
        crustPanel = new JPanel();
        crustPanel.setLayout(new GridLayout(1,3));
        crustPanel.setBorder(new TitledBorder(new EtchedBorder(),"Crust"));

        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepdish = new JRadioButton("Deep-Dish");

        thin.setFocusPainted(false);
        regular.setFocusPainted(false);
        deepdish.setFocusPainted(false);

        regular.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thin);
        group.add(regular);
        group.add(deepdish);

        crustPanel.add(thin);
        crustPanel.add(regular);
        crustPanel.add(deepdish);

        topPanel.add(crustPanel);
    }

    private void createSizePanel() {
        sizePanel = new JPanel();
        sizePanel.setBorder(new TitledBorder(new EtchedBorder(),"Size"));


        size = new JComboBox();
        size.addItem("Small");
        size.addItem("Medium");
        size.addItem("Large");
        size.addItem("Super");

        sizeCost = new HashMap<>();
        sizeCost.put("Small", 8);
        sizeCost.put("Medium", 12);
        sizeCost.put("Large", 16);
        sizeCost.put("Super", 20);

        size.setSelectedItem(null);

        String home = (String) size.getSelectedItem();

        sizePanel.add(size);

        topPanel.add(sizePanel);
    }

    private void createToppingsPanel() {
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(4,2));
        toppingsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Crazy Toppings"));

        eyeballs = new JCheckBox("Eyeball Eggs");
        worms = new JCheckBox ("Wormy Walnuts");
        dragon = new JCheckBox ("Dragon Duck");
        blood = new JCheckBox ("Bloody Bacon");
        monster = new JCheckBox ("Monster Mustard");
        scales = new JCheckBox("Scaly Salami");
        breath = new JCheckBox("Bad Breath Bananas");
        goblin = new JCheckBox("Goblin Garlic");

        eyeballs.setFocusPainted(false);
        worms.setFocusPainted(false);
        dragon.setFocusPainted(false);
        blood.setFocusPainted(false);
        monster.setFocusPainted(false);
        scales.setFocusPainted(false);
        breath.setFocusPainted(false);
        goblin.setFocusPainted(false);

        toppingsPanel.add(eyeballs);
        toppingsPanel.add(worms);
        toppingsPanel.add(dragon);
        toppingsPanel.add(blood);
        toppingsPanel.add(monster);
        toppingsPanel.add(scales);
        toppingsPanel.add(breath);
        toppingsPanel.add(goblin);

        topPanel.add(toppingsPanel);
    }

    private void createDisplayPanel() {
        displayPanel = new JPanel(new BorderLayout());
        display = new JTextArea(10, 5);
        display.setEditable(false);
        display.setFont(new Font ("Monospaced",Font.PLAIN,14));

        scroll = new JScrollPane(display);

        displayPanel.add(scroll, BorderLayout.CENTER);
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setBorder(new TitledBorder(new EtchedBorder(),"Control"));

        order = new JButton("Order");
        clear = new JButton("Clear");
        quit = new JButton("Quit");

        order.addActionListener((actionEvent) ->
        {
            String crust = "";
            if(thin.isSelected())
                crust = "Thin";
            else if(regular.isSelected())
                crust = "Regular";
            else if(deepdish.isSelected())
                crust = "Deep-Dish";


            String selectedSize = (String) size.getSelectedItem();
            cost = (int) sizeCost.get(selectedSize);

            receipt = String.format("%-20s %-10s\n\n", crust + " " + selectedSize, "$" + cost);

            if (eyeballs.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Eyeball Eggs", "$" + toppingsCost);
            }


            if (worms.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Wormy Walnuts", "$" + toppingsCost);
            }


            if (dragon.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Dragon Duck", "$" + toppingsCost);
            }


            if (blood.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Bloody Bacon", "$" + toppingsCost);
            }

            if (monster.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Monster Mustard", "$" + toppingsCost);
            }


            if (scales.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Scaly Salami", "$" + toppingsCost);
            }

            if (breath.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Bad Breath Bananas", "$" + toppingsCost);
            }

            if(goblin.isSelected()) {
                toppingsCount++;
                receipt += String.format("%-20s %-10s\n", "Goblin Garlic", "$" + toppingsCost);
            }

            double subtotal = (int) sizeCost.get(selectedSize) + toppingsCount; // Base pizza cost + $1 for each topping
            double tax = subtotal * 0.07; // Example tax rate of 8%
            double total = subtotal + tax;

            receipt += String.format("\n\n%-20s %-10s\n", "Sub-total:", "$" + subtotal);
            receipt += String.format("%-20s %-10s\n", "Tax:", "$" + tax);
            receipt += "------------------------------------------\n";
            receipt += String.format("%-20s %-10s\n", "Total:", "$" + total);


            display.setText(receipt);
        });

        clear.addActionListener((actionEvent) -> {
            thin.setSelected(false);
            regular.setSelected(true);
            deepdish.setSelected(false);

            size.setSelectedItem(null);

            eyeballs.setSelected(false);
            worms.setSelected(false);
            dragon.setSelected(false);
            blood.setSelected(false);
            monster.setSelected(false);
            scales.setSelected(false);
            breath.setSelected(false);
            goblin.setSelected(false);

            display.setText("");
        });

        quit.addActionListener((actionEvent) -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        controlPanel.add(order);
        controlPanel.add(clear);
        controlPanel.add(quit);



    }


}
