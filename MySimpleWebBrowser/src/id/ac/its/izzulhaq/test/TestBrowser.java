package id.ac.its.izzulhaq.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestBrowser {

    private boolean browsing;
    private WebVisit currentVisit;

    public static void main(String[] args) {
        TestBrowser b = new TestBrowser();
        b.run();
    }

    public TestBrowser() {
        browsing = true;
        currentVisit = null;
        System.out.println("Welcome to FCS Browser v0.01");
    }

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (browsing) {
            try {
                promptUser();
                String cmd = br.readLine();
                interpretAndExecute(cmd);
            } catch (IOException e) {
                System.out.println("Oops, there was an error reading your command.");
            }
        }
    }

    private void promptUser() {
        System.out.println("\nBrowser ready.\n(Commands: visit *web url*; view history; back; quit; )");
    }

    private void interpretAndExecute(String cmd) {
        String[] commands = cmd.split(" ");
        if (cmd.equals("back")) {
            goBack();
        } else if (cmd.equals("view history")) {
            viewHistory();
        } else if (commands[0].equals("visit")) {
            visitPage(commands[1]);
        } else if (cmd.equals("quit")) {
            quit();
        } else if (cmd.equals("forward")) {
            goForward();
        } else {
            System.out.println("Command not recognized");
        }
    }

    private void visitPage(String url) {
        System.out.println("Now going to visit " + url);
        WebVisit wv = new WebVisit(url, currentVisit);
        if (currentVisit != null) {
            currentVisit.setNextNode(wv);
        }
        currentVisit = wv;
        System.out.println(currentVisit);
    }

    private void goBack() {
        if (currentVisit != null) {
            System.out.println("Going back...");
            currentVisit = currentVisit.getPreviousNode();
            if (currentVisit != null) {
                System.out.println(currentVisit);
            }
        } else {
            System.out.println("No web visits in browser history");
        }
    }

    private void goForward() {
        if (currentVisit != null && currentVisit.getNextNode() != null) {
            System.out.println("Moving forward");
            currentVisit = currentVisit.getNextNode();
            if (currentVisit != null) {
                System.out.println(currentVisit);
            }
        } else {
            System.out.println("I can't do that silly!!!");
        }
    }

    private void viewHistory() {
        System.out.println("Showing browser history...");
        WebVisit tmp = currentVisit;
        int counter = 0;
        while (tmp != null) {
            System.out.println("\t~" + tmp);
            tmp = tmp.getPreviousNode();
            counter++;
        }
        System.out.println("\t\t" + counter + " total");
    }

    private void quit() {
        System.out.println("Quitting now...");
        browsing = false;
    }
}
