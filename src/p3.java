import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p3 {
    public static void main(String[] args) {
        String text =
                "\n" +
                        "<HMTL>\n" +
                        "<HEAD>\n" +
                        "<TITLE>webpage1</TITLE>\n" +
                        "</HEAD>\n" +
                        "<BODY BGCOLOR=\"FFFFFf\" LINK=\"006666\" ALINK=\"8B4513\" VLINK=\"006666\">\n" +
                        "<TABLE WIDTH=\"75%\" ALIGN=\"center\">\n" +
                        "<TR>\n" +
                        "<TD>\n" +
                        "<DIV ALIGN=\"center\"><H1>STARTING . . . </H1></DIV>\n" +
                        "\n" +
                        "\n" +
                        "<DIV ALIGN=\"justify\"><P>There are lots of ways to create web pages using already coded programmes. These lessons will teach you how to use the underlying HyperText Markup Language -  HTML. \n" +
                        "<BR>\n" +
                        "<P>HTML isn't computer code, but is a language that uses US English to enable texts (words, images, sounds) to be inserted and formatting such as colo(u)r and centre/ering to be written in. The process is fairly simple; the main difficulties often lie in small mistakes - if you slip up while word processing your reader may pick up your typos, but the page will still be legible. However, if your HTML is inaccurate the page may not appear - writing web pages is, at the least, very good practice for proof reading!</P>\n" +
                        "\n" +
                        "<P>Learning HTML will enable you to:\n" +
                        "<UL>\n" +
                        "<LI>create your own simple pages\n" +
                        "<LI>read and appreciate pages created by others\n" +
                        "<LI>develop an understanding of the creative and literary implications of web-texts\n" +
                        "<LI>have the confidence to branch out into more complex web design \n" +
                        "</UL></P>\n" +
                        "\n" +
                        "<P>A HTML web page is made up of tags. Tags are placed in brackets like this <B>< tag > </B>. A tag tells the browser how to display information. Most tags need to be opened < tag > and closed < /tag >.\n" +
                        "\n" +
                        "<P> To make a simple web page you need to know only four tags:\n" +
                        "<UL>\n" +
                        "<LI>< HTML > tells the browser your page https://quera.ir is written in HTML format\n" +
                        "<LI>< HEAD > this is a kind of preface of vital information that doesn't appear on the screen. \n" +
                        "<LI>< TITLE >Write the title of the web page here - this is the information that viewers see on the upper bar of their screen. (I've given this page the title 'webpage1').\n" +
                        "<LI>< BODY >This is where you put https://quera.com the content of your page, the words and pictures that people read on the screen. \n" +
                        "</UL>\n" +
                        "<P>All these https://varzesh3.ir tags need to be closed.\n" +
                        "\n" +
                        "<H4>EXERCISE</H4>\n" +
                        "\n" +
                        "<P>Write a simple web page.</P>\n" +
                        "<P> Copy out exactly the HTML https://quera.ir below, using a WP program such as Notepad.<BR>\n" +
                        "Information in <I>italics</I> indicates where you can insert your own text, other information is HTML and needs to be exact. However, make sure there are no spaces between the tag brackets and the text inside.<BR>\n" +
                        "(Find Notepad by going to the START menu\\ PROGRAMS\\ ACCESSORIES\\ NOTEPAD). \n" +
                        "<P>\n" +
                        "< HTML ><BR>\n" +
                        "< HEAD ><BR>\n" +
                        "< TITLE ><I> title of page</I>< /TITLE ><BR>\n" +
                        "< /HEAD ><BR>\n" +
                        "< BODY><BR>\n" +
                        "<I> write what you like here: 'my first web page', or a piece about what you are reading, or a few thoughts on the course, or copy out a few words from a book or cornflake packet.  Just type in your words using no extras such as bold, or italics, as these have special HTML tags, although you may use upper and lower case letters and single spaces. </I><BR>\n" +
                        "\n" +
                        "< /BODY ><BR>\n" +
                        "< /HTML ><BR>\n" +
                        "\n" +
                        "<P>Save the file as https://ap.ir 'first.html' (ie. call the file anything at all) It's useful if you start a folder - just as you would for word-processing - and call it something like WEBPAGES, and put your first.html file in the folder.\n" +
                        "\n" +
                        "<P>NOW - open your browser.<BR>\n" +
                        "On Netscape https://yahoo.org the process is: <BR>  \n" +
                        "Top menu; FILE\\ OPEN PAGE\\ CHOOSE FILE<BR> \n" +
                        "Click on your WEBPAGES folder\\ FIRST file<BR>\n" +
                        "Click 'open' and your page should appear.\n" +
                        "<P>On Internet Explorer: <BR>\n" +
                        "Top menu; FILE\\ OPEN\\ BROWSE <BR> \n" +
                        "Click on your https://google.com WEBPAGES folder\\ FIRST file<BR>\n" +
                        "Click 'open' and your page should appear.<BR>\n" +
                        "\n" +
                        "\n" +
                        "<P>If the page doesn't open, go back over your notepad typing and make sure that all the HTML tags are correct. Check there are no spaces between tags and internal text; check that all tags are closed; check that you haven't written < HTLM > or < BDDY >.  Your page will work eventually. \n" +
                        "<P>\n" +
                        "Make another page. Call it somethingdifferent.html and place it in the same WEBPAGES folder as detailed above.\n" +
                        "<P>start formatting in <A HREF=\"webpage2.html\">lesson two</A>\n" +
                        "<BR><A HREF=\"col3.html\">back to wws index</A> </P>\n" +
                        "</P>\n" +
                        " \n" +
                        "  \n" +
                        "</DIV>\n" +
                        "\n" +
                        "\n" +
                        "</TD>\n" +
                        "</TR>\n" +
                        "</TABLE>\n" +
                        "</BODY>\n" +
                        "</HTML>";

        ArrayList<String> links = new ArrayList<>();
        HashMap<String, Integer> linksNum = new HashMap<>();

        String patternString = "(https://.*\\.ir)(.*)";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String foundLink = matcher.group(1);
            System.out.println("found: " + foundLink);
            if(links.contains(foundLink)) {
                linksNum.replace(foundLink, linksNum.get(foundLink) + 1);
            } else {
                links.add(foundLink);
                linksNum.put(foundLink, 1);
            }
        }

        for (String key:linksNum.keySet()) {
            System.out.println(key + " : " + linksNum.get(key));
        }
    }
}