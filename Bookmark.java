
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bookmark {

    private static int idx = -1;
    private static JFrame fr = new JFrame();
    private static BookList Books = new BookList();

    /**
     * @return The line number of the code that ran this method
     * @author Brian_Entei
     */
    public static int getLineNumber() {
        return ___8drrd3148796d_Xaf();
    }

    /**
     * This methods name is ridiculous on purpose to prevent any other method
     * names in the stack trace from potentially matching this one.
     *
     * @return The line number of the code that called the method that called
     * this method(Should only be called by getLineNumber()).
     * @author Brian_Entei
     */
    private static int ___8drrd3148796d_Xaf() {
        boolean thisOne = false;
        int thisOneCountDown = 1;
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            String methodName = element.getMethodName();
            int lineNum = element.getLineNumber();
            if (thisOne && (thisOneCountDown == 0)) {
                return lineNum;
            } else if (thisOne) {
                thisOneCountDown--;
            }
            if (methodName.equals("___8drrd3148796d_Xaf")) {
                thisOne = true;
            }
        }
        return -1;
    }

    private static void onExit() throws IOException{
        BookList old = new BookList("bookmarks_backup");
        if(Books.equals(old))
            System.exit(1);
        else{
        if(Confirm("The bookmarks have been changed. Do you really want to exit without saving?","Exit",JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
            System.exit(1);
        else
            Books.write("bookmarks");
        System.exit(1);
        }
    }

    private static String[] bookList() {
        String path = "C:\\Users\\iaint\\Documents\\xiao\\books\\";
        File Dir;
        try{Dir = new File(path);}
        catch(IOException e){JOptionPane.showMessageDialog(null, "An exception occured while reading the Books" + getLineNumber() + i + ")", "IOException", JOptionPane.WARNING_MESSAGE),}
        String[] bookList;
        bookList = Dir.list();
        java.util.List<String> temp = new ArrayList<>();
        for (String str : bookList) {
            if (str != null && str.length() != 0 && str.contains(".pdf")) {
                str = str.replace(".pdf", "");
                temp.add(str);
            }
        }
        bookList = temp.toArray();
        return bookList;
    }

    public static int Confirm(String info, String title, int icon) {
        return JOptionPane.showConfirmDialog(null, info, title, JOptionPane.YES_NO_OPTION, icon);
    }

    public static String getInput(String info, String title, String Default) {
        return (String) JOptionPane.showInputDialog(null, info, title, JOptionPane.PLAIN_MESSAGE, null, null, Default);
    }

    public static Integer parseInteger(String text) {
        Integer result;
        if (text.equals("")) {
            result = 0;
        }
        try {
            result = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }

    private static JPanel panel1() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0, 3));
        class ActionListenerDisplayPanel implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton) e.getSource();
                if (temp.isSelected()) {
                    idx = Integer.parseInt(e.getActionCommand());
                }
            }
        }
        ButtonGroup gr = new ButtonGroup();
        for (int i = 0; i < Books.getSize(); i++) {
            JRadioButton jrb = new JRadioButton(Books.getName(i) + ": " + Books.getPage(i));
            jrb.setActionCommand("" + i);
            gr.add(jrb);
            jrb.addActionListener(new ActionListenerDisplayPanel());
            panel1.add(jrb);
        }
        return panel1;
    }

    private static JPanel panel2() {
        JButton jbt0 = new JButton("Open");
        JButton jbt1 = new JButton("Update Bookname");
        JButton jbt2 = new JButton("Update Bookmark");
        JButton jbt3 = new JButton("Save & Exit");
        JButton jbt4 = new JButton("Add a Book");
        JButton jbt5 = new JButton("Delete the Book");
        JButton jbt6 = new JButton("Update Path");
        JPanel panel2 = new JPanel(new GridLayout(0, 3));
        panel2.add(jbt0);
        panel2.add(jbt1);
        panel2.add(jbt2);
        panel2.add(jbt3);
        panel2.add(jbt4);
        panel2.add(jbt5);
        panel2.add(jbt6);
        //open
        jbt0.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (idx < 0) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a book!", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Books.get(idx).getPath().contains("http://")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            try {
                                Desktop.getDesktop().browse(new URI(Books.get(idx).getPath()));
                            } catch (URISyntaxException ex) {
                                JOptionPane.showMessageDialog(null, "The url path of ¡¶" + Books.get(idx).getName() + "¡· is not a valid url path.", "Incorrect URL", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "An unknown error occured in line"+getLineNumber()+".", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desktop is currently not supported on this platform. Check availibility of desktop.", "Cannot open desktop", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (Books.get(idx).getPath().contains("C:\\")) {
                    String path = "C:\\Users\\xiao\\SkyDrive\\Documents\\Books\\books\\" + Books.get(idx).getName() + ".pdf";
                    if (Desktop.isDesktopSupported()){
                    try{
                    Desktop.getDesktop().open(new File(path))};
                    catch (Exception e){
                    JOptionPane.showMessageDialog(null,"File path is not valid! Please update the path.","File not Found",JOptionPane.WARNING_MESSAGE);}}
                    else JOptionPane.showMessageDialog(null, "Desktop is currently not supported on this platform. Check availibility of desktop.", "Cannot open desktop", JOptionPane.WARNING_MESSAGE);
                    }
                } 
        });
        //update bookname
        //use JFileChooser to replace update bookname, update bookpath, add new book
        jbt1.addActionListener((ActionEvent e) -> {
            if (idx < 0) {
                JOptionPane.showMessageDialog(null, "You haven't selected a book!", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Books.get(idx).setName(getInput("Please enter the new name", "Update Name", ""));
            fr.dispose();
            fr = new JFrame();
            getFrame();
        });
        jbt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idx < 0) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a book!", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Books.get(idx).setPage(Integer.parseInt(getInput("Please enter the new page No.", "Update Bookmark", "")));
                fr.dispose();
                fr = new JFrame();
                getFrame();
            }
        });
        jbt3.addActionListener((ActionEvent e) -> {
            try {
                PrintWriter outFile = new PrintWriter(new File("bookmarks.txt"));
                for (int i = 0; i < Books.getSize(); i++) {
                    outFile.println(Books.get(i).getName() + Books.get(i).getPath());
                    outFile.println(Books.get(i).getPage());
                }
                outFile.close();
                System.exit(1);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occured during outputting file", "Error", JOptionPane.WARNING_MESSAGE);
                System.exit(1);
            }
        });
        jbt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Books.add(new Bookmarks(getInput("Please enter the name", "New Book", " "), Integer.parseInt(getInput("Please enter the bookmark", "New Book", "")), getInput("Please enter the path of this book", "New book", "")));
                fr.dispose();
                fr = new JFrame();
                getFrame();
            }
        });
        jbt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idx < 0) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a book!", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Books.deleteBook(idx);
                fr.dispose();
                fr = new JFrame();
                getFrame();
            }
        });
       jbt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idx < 0) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a book!", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Books.get(idx).setPath(getInput("Enter the new path:", "New Path", ""));
                fr.dispose();
                fr = new JFrame();
                getFrame();
            }
        });
        return panel2;
    }

    private static void getFrame() {
        fr.setLayout(new GridLayout(2, 1));
        fr.add(panel1());
        fr.add(panel2());
        fr.pack();
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fr.addWindowListener(new java.awt.event.WindowAdapter() {
            public void WindowClosing(java.awt.event.WindowEvent windowEvent) throws IOException {
onExit();
            }
        });
        fr.setVisible(true);
        fr.requestFocusInWindow();
    }

    public static void main(String[] argv) throws IOException {
        try {
          Books.add("bookmarks");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "An Error Occured while Scanning File!", "Error", JOptionPane.WARNING_MESSAGE);
            System.exit(1);
        }
        getFrame();
    }
}

class Bookmarks {

    String bookName;
    int bookPage;
    String bookPath;

    public Bookmarks() {
        this(null, null, 0);
    }

    public Bookmarks(String bookName, String bookPath, int bookPage) {
        this.bookName = bookName;
        this.bookPath = bookPath;
        this.bookPage = bookPage;
    }

    public Bookmarks(String bookName, int bookPage) {
        this(bookName, null, bookPage);
    }

    public Bookmarks(int bookPage, String bookName) {
        this(bookName, null, bookPage);
    }

    public Bookmarks(String bookName, int bookPage, String bookPath) {
        this(bookName, bookPath, bookPage);
    }

    public Bookmarks(String bookName) {
        this(bookName, 0);
    }

    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public String getName() {
        return bookName;
    }

    public void setPage(int bookPage) {
        this.bookPage = bookPage;
    }

    public int getPage() {
        return bookPage;
    }

    public void setPath(String path) {
        this.bookPath = path;
    }

    public String getPath() {
        return bookPath;
    }

    public boolean isName(String Name) {
        return bookName.equalsIgnoreCase(Name);
    }

    @Override
    public String toString() {
        String line1 = bookName + bookPath;
        String line2 = Integer.toString(bookPage);
        return line1 + "\n" + line2 + "\n";
    }
}

class BookList {

    ArrayList<Bookmarks> Books = new ArrayList<Bookmarks>();

    /**
     * @return The line number of the code that ran this method
     * @author Brian_Entei
     */
    public static int getLineNumber() {
        return ___8drrd3148796d_Xaf();
    }

    /**
     * This methods name is ridiculous on purpose to prevent any other method
     * names in the stack trace from potentially matching this one.
     *
     * @return The line number of the code that called the method that called
     * this method(Should only be called by getLineNumber()).
     * @author Brian_Entei
     */
    private static int ___8drrd3148796d_Xaf() {
        boolean thisOne = false;
        int thisOneCountDown = 1;
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            String methodName = element.getMethodName();
            int lineNum = element.getLineNumber();
            if (thisOne && (thisOneCountDown == 0)) {
                return lineNum;
            } else if (thisOne) {
                thisOneCountDown--;
            }
            if (methodName.equals("___8drrd3148796d_Xaf")) {
                thisOne = true;
            }
        }
        return -1;
    }

    private static String[] bookList() {
        String path = "C:\\Users\\xiao\\SkyDrive\\Documents\\Books\\books\\";
        File Dir;
        Dir = new File(path);
        String[] bookList;
        bookList = Dir.list();
        java.util.List<String> temp = new ArrayList<>();
        for (String str : bookList) {
            if (str != null && str.length() != 0 && str.contains(".pdf")) {
                str = str.replace(".pdf", "");
                temp.add(str);
            }
        }
        bookList = temp.toArray(new String[0]);
        return bookList;
    }

    public BookList() {
        this.Books = null;
    }

    public BookList(ArrayList<Bookmarks> Books) {
        this.Books = Books;
    }

    public BookList(String fileName) throws FileNotFoundException {
        this.read(fileName);
    }
    
    public BookList(BookList books) {
        for (int i = 0; i < books.getSize(); i++) {
            if(books.get(i)!=null)
            Books.add(books.get(i));
        }
    }
    
    public static BookList read(String fileName) throws FileNotFoundException{
        BookList tempBooks = new BookList();
        try{
        Scanner inFile = new Scanner(new FileReader(fileName+".txt"));
        while (inFile.hasNext()) {
            String tempName = inFile.nextLine();
            String tempPath = "";
            Integer tempPage = Bookmark.parseInteger(inFile.nextLine());
            if (tempPage == null) {
                JOptionPane.showMessageDialog(null, "Found a non-integer where bookmark is located at bookmarks.txt", "Input Error", JOptionPane.WARNING_MESSAGE);
                System.exit(1);
            }
            if (tempName.contains("C:\\")) {
                tempPath = tempName.substring(tempName.indexOf("C:\\"));
                tempName = tempName.substring(0, tempName.indexOf("C:\\"));
            } else if (tempName.contains("http://")) {
                tempPath = tempName.substring(tempName.indexOf("http://"));
                tempName = tempName.substring(0, tempName.indexOf("http://"));
            }
            tempBooks.add(new Bookmarks(tempName, tempPath, tempPage));
        }
        inFile.close();
        }        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File not found during reading!!","FileNotFoundException",JOptionPane.WARNING_MESSAGE);
            System.exit(1);
                }
        return tempBooks;
    }
    
    public void write(String fileName) throws FileNotFoundException{
        try{
        PrintWriter outFile = new PrintWriter(new File(fileName + ".txt"));
        outFile.print(this.Books.toString());
        outFile.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File not found during writing!!","FileNotFoundException",JOptionPane.WARNING_MESSAGE);
            System.exit(1);
                }
    }

    public int getSize() {
        return Books.size();
    }

    public Bookmarks get(int index) {
        return Books.get(index);
    }

    public String getName(int index) {
        return Books.get(index).getName();
    }

    public int getPage(int index) {
        return Books.get(index).getPage();
    }

    public String[] getNames() {
        String[] names = new String[Books.size()];
        for (int i = 0; i < Books.size(); i++) {
            try {
                names[i] = Books.get(i).getName();
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "An exception occured while getting book names in 1 array(" + getLineNumber() + i + ")", "ArrayIndexOutOfBoundsException", JOptionPane.WARNING_MESSAGE);
            }
        }
        return names;
    }

    public int getIndexOf(Bookmarks book) {
        int index = -1;
        for (int i = 0; i < Books.size(); i++) {
            if (book.getName().equals(this.getNames()[i])) {
                index = i;
            }
        }
        return index;
    }
    
    public void add(BookList temp){
    for(int i =0;i<temp.getSize();i++)
    this.add(temp.Books.get(i));
    }
    
    public void add(String fileName) throws FileNotFoundException{
    BookList temp = new BookList(fileName);
    this.add(temp);
    }

    public void add(Bookmarks book) {
        Books.add(book);
    }

    public void deleteBook(Bookmarks book) {
        int index;
        index = this.getIndexOf(book);
        if (index >= 0) {
            Books.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Error", "Cannot find this book!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteBook(int index) {
        this.deleteBook(Books.get(index));
    }

    public void setBookname(int index, String name) {
        if (name != null && index >= 0) {
            Books.get(index).setName(name);
        }
    }

    public void setBookpage(int index, int page) {
        if (page >= 0 && index >= 0) {
            Books.get(index).setPage(page);
        }
    }

    public void setBookpath(int index, String path) {
        if (!path.equals("") && index >= 0) {
            Books.get(index).setPath(path);
        }
    }

    public void set(int index, Bookmarks bookmark) {
        if (index >= 0 && bookmark != null) {
            Books.get(index).setName(bookmark.getName());
            Books.get(index).setPage(bookmark.getPage());
            Books.get(index).setPath(bookmark.getPath());
        }
    }
    
    public boolean equals(BookList books){
    if(this.Books.size()!=books.getSize())
        return false;
    for(int i =0; i<Books.size();i++){
    if(this.get(i).getName().equals(books.get(i).getName())&this.get(i).getPage()==books.get(i).getPage()&this.get(i).getPath().equals(books.get(i).getPath()))
;
    else
        return false;
        }
    return true;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < Books.size(); i++) {
            output.concat(Books.get(i).toString());
        }
        return output;
    }
}
