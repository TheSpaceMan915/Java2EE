package classes.task4;
import java.io.Serializable;

public class GameHelper implements Serializable
{
    private static final int m_arr_size = 3;
    private static final String[][] m_arr_str = new String[m_arr_size][m_arr_size];

    //user_flag signals that the user has won
    private static boolean m_user_flag = false;

    //server_flag signals that the server has won
    private static boolean m_server_flag = false;

    private static final Thread m_thread_check = new Thread(new CheckerJob());

    static
    {
        //writing " " in all the strings in m_arr_str
        for (int i = 0; i < m_arr_size; i++)
        {
            for (int j = 0; j < m_arr_size; j++)
            {
                m_arr_str[i][j] = " ";
            }
        }
        m_thread_check.start();
    }

    public static int getSizeArr() { return m_arr_size; }

    public static String[][] getArrStr() { return m_arr_str; }

    public static void setServerFlag(boolean m_server_flag) { GameHelper.m_server_flag = m_server_flag; }

    public static void setUserFlag(boolean m_user_flag) { GameHelper.m_user_flag = m_user_flag; }

    public static boolean getServerFlag() { return m_server_flag; }

    public static boolean getUserFlag() { return m_user_flag; }
}
