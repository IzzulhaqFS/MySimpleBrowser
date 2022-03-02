package id.ac.its.izzulhaq.browser;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadFile extends JFrame {
    private JTextField addressBar;
    private JEditorPane display;

    public ReadFile() {
        super("MyBrowser");

        addressBar = new JTextField("Enter a URL");
        addressBar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadPage(e.getActionCommand());
                    }
                }
        );

        add(addressBar, BorderLayout.NORTH);

        display = new JEditorPane();
        display.setEditable(false);
        display.addHyperlinkListener(
                new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            loadPage(e.getURL().toString());
                        }
                    }
                }
        );

        add(new JScrollPane(display), BorderLayout.CENTER);
        setSize(500, 300);
        setVisible(true);
    }

    private void loadPage(String userText) {
        try {
            display.setPage(userText);
            addressBar.setText(userText);
        } catch (Exception e) {
            System.out.println("Oh, no!!!");
        }
    }
}
