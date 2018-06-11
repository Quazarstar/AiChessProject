import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/
//Method for random move
  public Move randomSelectedMove(Stack potentialMoves){

    int moveID = rand.nextInt(potentialMoves.size());
    System.out.println("Agent randomly selected  the move : "+moveID);
    for(int i=1;i < (potentialMoves.size()-(moveID));i++){
      potentialMoves.pop();
    }
    Move selectedMove = (Move)potentialMoves.pop();
    return selectedMove;
  }
//Method for best move records the weight of the pieces and calculates when to move based on this if the situation comes up, otherwise places random.
  public Move nextBestMove(Stack whiteStack, Stack blackStack){
        Stack backup = (Stack) whiteStack.clone();
        Stack black = (Stack) blackStack.clone();
        Move whiteMove, currentMove, bestMove;
        Square blackPosition;
        int weight = 0;
        int highestWeight = 0;
        bestMove = null;

        while (!whiteStack.empty()) {
            whiteMove = (Move) whiteStack.pop();
            currentMove = whiteMove;

            //Check to see if the move is available
            if ((currentMove.getStart().getYC() < currentMove.getLanding().getYC())
                    && (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 3)
                    || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 3)
                    || (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 4)
                    || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 4)) {
                weight = 1;
                //Update the BestMove
                if (weight > highestWeight) {
                    highestWeight = weight;
                    bestMove = currentMove;
                }
            }

            //Compare both white and black landing positions. Counter by capturing opponent piece if available.
            while (!black.isEmpty()) {
                weight = 0;
                blackPosition = (Square) black.pop();
                if ((currentMove.getLanding().getXC() == blackPosition.getXC()) && (currentMove.getLanding().getYC() == blackPosition.getYC())) {
                    //Check Value of Piece
                    switch (blackPosition.getName()) {
                        case "BlackPawn":
                            weight = 1;
                            break;
                        case "BlackBishop":
                            weight = 2;
                            break;
                        case "BlackKnight":
                            weight = 3;
                            break;
                        case "BlackRook":
                            weight = 4;
                            break;
                        case "BlackQueen":
                            weight = 5;
                            break;
                        case "BlackKing":
                            weight = 6;
                            break;
                    }
                }
                //Update the BestMove to see what move to take
                if (weight > highestWeight) {
                    highestWeight = weight;
                    bestMove = currentMove;
                }
            }
            //Refresh Black spaces
            black = (Stack) blackStack.clone();
        }
        //Use Best Move if Available (Take pieces of equal value of higher), otherwise make a random move
        if (highestWeight > 0) {
            System.out.println("Agent selected the next best move.");
            return bestMove;
        }
        return randomSelectedMove(backup);
  }

  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
