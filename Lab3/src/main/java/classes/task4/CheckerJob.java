package classes.task4;

public class CheckerJob implements Runnable
{
    private boolean winnerFlag;
    
    public void checkWinner(String [][] array_str)
    {
        int counter_noughts = 0;
        int counter_crosses = 0;

        //checking the rows
        for (int i = 0; i < GameHelper.getSizeArr(); i++)
        {
            for (int j = 0; j < GameHelper.getSizeArr(); j++)
            {
                if (array_str[i][j].equals("0"))
                { counter_noughts++; }
                else if (array_str[i][j].equals("╳"))
                { counter_crosses++; }
            }

            //checking if the user has 3 noughts or the server has 3 crosses
            if (counter_noughts == 3)
            {
                GameHelper.setUserFlag(true);
                return;
            }
            else if (counter_crosses == 3)
            {
                GameHelper.setServerFlag(true);
                return;
            }
            else
            {
                counter_noughts = 0;
                counter_crosses = 0;
            }
        }

        //checking the columns
        for (int i = 0; i < GameHelper.getSizeArr(); i++)
        {
            for (int j = 0; j < GameHelper.getSizeArr(); j++)
            {
                if (array_str[j][i].equals("0"))
                { counter_noughts++; }
                else if (array_str[j][i].equals("╳"))
                { counter_crosses++; }
            }

            //checking if the user has 3 noughts or the server has 3 crosses
            if (counter_noughts == 3)
            {
                GameHelper.setUserFlag(true);
                return;
            }
            else if (counter_crosses == 3)
            {
                GameHelper.setServerFlag(true);
                return;
            }
            else
            {
                counter_noughts = 0;
                counter_crosses = 0;
            }
        }

        //checking the [0][0] - [2][2] diagonal
        int k = 0, l = 0;
        while (k  < GameHelper.getSizeArr())
        {
            if (array_str[k][l].equals("0"))
            { counter_noughts++; }
            else if (array_str[l][l].equals("╳"))
            { counter_crosses++; }

            k++;
            l++;
        }

        if (counter_noughts == 3)
        {
            GameHelper.setUserFlag(true);
            return;
        }
        else if (counter_crosses == 3)
        {
            GameHelper.setServerFlag(true);
            return;
        }
        else
        {
            counter_noughts = 0;
            counter_crosses = 0;
        }

        //checking the [0][2] - [2][0] diagonal
        int k2 = 0,l2 = 2;
        while (k2  < GameHelper.getSizeArr())
        {
            if (array_str[k2][l2].equals("0"))
            { counter_noughts++; }
            else if (array_str[k2][l2].equals("╳"))
            { counter_crosses++; }

            k2++;
            l2--;
        }

        if (counter_noughts == 3)
        { GameHelper.setUserFlag(true); }
        else if (counter_crosses == 3)
        { GameHelper.setServerFlag(true); }
    }


    @Override
    public void run()
    {
        String[][] arr = null;
        while (!GameHelper.getServerFlag() && !GameHelper.getUserFlag())
        {
            arr = GameHelper.getArrStr();
            checkWinner(arr);
        }
    }
}
