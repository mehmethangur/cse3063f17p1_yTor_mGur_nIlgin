import java.io.*;
import java.util.*;

public class Board {
    Square[] squares = new Square[40];
    Player[] players;

    public Board(){
        createSquares();
    }

    public Square getSquare(Square start, int distance)
    {
        int endIndex = (start.getIndex() + distance) % 40;
        return (Square) squares[endIndex];
    }

    public Square getStartSquare()
    {
        return (Square) squares[0];
    }

    public void createSquares(){
        //TODO: duzgun path ekle
        String[] result=null;
        int index;
        int utilityCount=0;

        String filename = "/Users/yasintoy/MonopolyGame/src/com/company/Monopoly-Lots.csv";
        result = this.readSquaresFromFile(filename);


        for (int i=0; i < 40 ; i++){
            Square square;
            if (i == 0)
                square = new GoSquare(i);
            else if (i == 11)
                square = new Jail(i);
            else if (i == 31)
                square = new GoJail(i);
            else if(i == 20)
                square = new FreeParkingSquare(i);
            else if (i == 5)
                square = new IncomeSquare(i);
            else if (i == 39)
                square = new LuxurySquare(i);
            else if(i == 13 || i == 29) {
                if (utilityCount == 0)
                    square = new UtilitySquare(i, "ElectricUtility", 150);
                else
                    square = new UtilitySquare(i, "WaterUtility", 150);
                utilityCount++;

            }
            else if(i == 6 || i == 16 || i == 26 || i == 36)
                square = new railsRoadSquare(i, 200);
            else if(this.isInFileInput(result.clone(), i)) {
                String[] output;
                int j=0;
                for(j=0; j < result.length-1; j++){
                    output = result[j].split(";");
                    if (Integer.parseInt(output[0]) == i)
                        break;
                }
                output = result[j].split(";");
                square = new LotsSquare(Integer.parseInt(output[0]), Integer.parseInt(output[1]), Integer.parseInt(output[2]));
            }
            else
                square = new OrdinarySquare(i);

            squares[i] = square;
        }
    }

    public boolean isInFileInput(String[] result, int i){
        String[] output;
        for(int j=0; j < result.length-1; j++){
            output = result[j].split(";");
            if (Integer.parseInt(output[0]) == i)
                return true;
        }
        return false;

    }

    public Player getPlayer(int id){
        return players[id];
    }

    public  String[] readSquaresFromFile(String fileName){
        String[] result=null;

        try {
            BufferedReader br = new BufferedReader( new FileReader(fileName));
            String strLine = null;
            String linecounter;
            StringTokenizer st = null;
            int lineNumber = 0, i = 0;

            while((linecounter = br.readLine()) != null) {
                lineNumber++;

            }
            result = new String[lineNumber];
            br.close();
            br = new BufferedReader(new FileReader(fileName));
            while((strLine = br.readLine())!=null) {
                if(i != 0)
                    result[i-1] = strLine;
                i++;
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}