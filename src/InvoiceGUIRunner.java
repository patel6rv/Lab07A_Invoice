import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InvoiceGUIRunner extends JFrame
{
    JPanel mainPnl;
    JPanel subMain;

    JPanel titlePnl;
    JLabel titleLbl;

    JPanel addressPnl;
    JLabel addressLbl;

    JPanel displayPnl;
    JPanel controlPnl;

    //displaying
    JTextArea displayTA;
    JScrollPane scroller;


    JButton invoiceBtn;
    JButton clearBtn;
    JButton quitBtn;

    public InvoiceGUIRunner()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4,1));

        createTitlePanel();
        mainPnl.add(titlePnl);

        createAddressPanel();
        subMain = new JPanel();
        subMain.setLayout(new GridLayout(1, 2));
        subMain.setBorder(new TitledBorder(new EtchedBorder(), "Address"));

        subMain.add(addressPnl, BorderLayout.WEST);

        mainPnl.add(subMain);

        createDisplayPanel();
        mainPnl.add(displayPnl);

        createControlPanel();
        mainPnl.add(controlPnl);

        add(mainPnl);
        setSize(600,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(1, 1));

        titleLbl = new JLabel("Invoice", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titleLbl.setFont(new Font("Georgia", Font.PLAIN, 36));
        titlePnl.add(titleLbl);
    }

    private void createAddressPanel()
    {
        addressPnl = new JPanel();
        addressPnl.setLayout(new GridLayout(1,1));
        addressPnl.setBorder(new TitledBorder(new EtchedBorder()));

        addressLbl = new JLabel("");

        addressPnl.add(addressLbl);
    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();
        displayPnl.setBorder(new TitledBorder(new EtchedBorder(), "Invoice"));
        displayTA = new JTextArea(6,40);
        displayTA.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));

        invoiceBtn = new JButton("Create Invoice");
        controlPnl.add(invoiceBtn);
        invoiceBtn.addActionListener((ActiveEvent_ae) ->
        {
            String customer = SafeInput.getNonZeroLenString("Please enter your company name \n(EX: Sam's Club)");
            String streetAd = SafeInput.getNonZeroLenString("Please enter your street address \n(EX: 123 Newberry St.)");
            String greaterAd = SafeInput.getNonZeroLenString("Please enter your city, state, and zip code \n(Ex: New York, NY 12345)");

            addressLbl.setText("<html>" + customer +"<br>" + streetAd + "<br>" + greaterAd+ "</html>");


            boolean confirm = true;
            String item = "";
            double quantity = 0;
            double price = 0;
            double total = 0;
            double amountDue = 0;
            String rec = "";

            rec += "Item" + " ".repeat(20) + "Qty" + " ".repeat(5) + "Price" + " ".repeat(5) + "Total\n";
            do
            {
                item = SafeInput.getNonZeroLenString("Please enter an item");
                quantity = SafeInput.getDouble("Please enter the quantity of the item");
                price = SafeInput.getDouble("Please enter the price of the item");
                total = quantity * price;
                amountDue += total;

                rec += item + " ".repeat(20) + quantity + " ".repeat(5) + "$"+price + " ".repeat(5) + "$"+total +"\n";

                confirm = SafeInput.getYNConfirm("Do you want to enter another item");
            }while(confirm);

            rec += "\n$"+amountDue;
            displayTA.append(rec);
        });


        clearBtn = new JButton("Clear Invoice");
        controlPnl.add(clearBtn);
        clearBtn.addActionListener((ActiveEvent_ae) ->
        {
            addressLbl.setText("");
            displayTA.selectAll();
            displayTA.replaceSelection("");
        });


        quitBtn = new JButton("Quit");
        controlPnl.add(quitBtn);
        quitBtn.addActionListener((ActiveEvent_ae) ->
        {
            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?","Select an Option", JOptionPane.YES_NO_CANCEL_OPTION);
            if(input == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });


    }
}
