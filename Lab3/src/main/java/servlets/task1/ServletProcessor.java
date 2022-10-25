package servlets.task1;

import classes.task1.MusicCollection;
import classes.task1.MySong;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//processes data and sends it to Servlet2
@WebServlet(name = "Servlet1", value = "/Servlet1")
public class ServletProcessor extends HttpServlet
{
    private static final File m_file_xml = new File("/Users/jackyokov/IdeaProjects/Java2EE/Lab3/src/main/webapp/xml/MyMusic.xml");

    public MusicCollection readXml()
    {
        MusicCollection temp = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(MusicCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (MusicCollection) unmarshaller.unmarshal(m_file_xml);
        }
        catch (JAXBException exep)
        { exep.printStackTrace(); }

        assert temp != null;
        return temp;
    }


    public void writeXml(MusicCollection music)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(MusicCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

            marshaller.marshal(music,m_file_xml);
        }
        catch (JAXBException exep)
        { exep.printStackTrace(); }
    }

    //doGet deletes songs
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MusicCollection music_collection = readXml();
        List<MySong> list = music_collection.getListSongs();
        String input_title = request.getParameter("field_title");
        String input_artist = request.getParameter("field_artist");


        //deleting some songs from the list
        //we need to use Iterator because the size of the list may change
        Iterator<MySong> i = list.listIterator();
        MySong temp = null;
        while (i.hasNext())
        {
            temp = i.next();
            if (input_title.equals(temp.getTitle()))
            {
                if (input_artist.equals(temp.getArtist()))
                { i.remove(); }
            }
        }

        //saving an updated music collection on the server
        writeXml(music_collection);


        //redirecting the request to Viewer
        request.setAttribute("list_songs",list);
        request.getRequestDispatcher("ServletViewer").forward(request,response);
    }


    //doPost adds songs
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //getting the parameters for a new song object
        String input_title = request.getParameter("field_title");
        String input_artist = request.getParameter("field_artist");
        int rand_id = ThreadLocalRandom.current().nextInt(0,1001);


        //adding a song
        MySong song = new MySong(Integer.toString(rand_id),input_title,input_artist);
        MusicCollection music_collection = readXml();
        List<MySong> list = music_collection.getListSongs();
        list.add(song);

        //updating the xml file to save the data on the server
        writeXml(music_collection);


        //redirecting the request to Viewer
        request.setAttribute("list_songs",list);
        request.getRequestDispatcher("ServletViewer").forward(request,response);
    }
}
