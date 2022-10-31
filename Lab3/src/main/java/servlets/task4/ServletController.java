package servlets.task4;

import classes.task4.GameHelper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "Servlet3", value = "/ServletController")
public class ServletController extends HttpServlet
{
    public void playServerTurn()
    {
        final int min = 0;
        final int max = GameHelper.getSizeArr();
        final String[][] arr_str = GameHelper.getArrStr();

        int rand_row = 0;
        int rand_column = 0;

        //finding a free cell to put a cross
        do
        {
            rand_row = ThreadLocalRandom.current().nextInt(min,max);
            rand_column = ThreadLocalRandom.current().nextInt(min,max);
        }
        while (arr_str[rand_row][rand_column].equals("0") || arr_str[rand_row][rand_column].equals("╳"));


        //putting ╳ as a server's turn
        arr_str[rand_row][rand_column] = "╳";
    }


    public void playUserTurn(String row, String column)
    {
        final int number_row = Integer.parseInt(row);
        final int number_column = Integer.parseInt(column);
        final String[][] arr_str = GameHelper.getArrStr();

        //if it is a free cell, put 0 in the cell
        //otherwise, the user will skip his turn
        if (arr_str[number_row][number_column].equals(" "))
        { arr_str[number_row][number_column] = "0"; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response); }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String input_row = request.getParameter("field_row");
        String input_column = request.getParameter("field_column");


        //playing
        playUserTurn(input_row,input_column);
        playServerTurn();


        //saving the data in the context
        final String[][] arr_str = GameHelper.getArrStr();
        getServletContext().setAttribute("array",arr_str);

        //redirecting the request to the jsp page
        request.getRequestDispatcher("jsp/task4_table.jsp").forward(request,response);
    }
}
