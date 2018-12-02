// ** this is the mini project authored by ELEANOR WINRAM. student number 170324443. i have chosen to do a music quiz where the user progresses 
//*  through a series of stages or levels to try and get to the final level. the questions are from varying genres and ages. at the end the results 
//** will be sorted into a table so the user can see which round the did best in. 

import java.util.Scanner;
import java.util.Random;
import java.io.*;

class game 
{

///////////////////////////////////////////////////////////////////////////////////////////// MAIN METHOD START OF PROGRAM

	public static void main(String [] param)   // running each method 
	{
		
		hello();                 // HELLO printed in larger letters. adds visual feature to the program 
		setup();
		whileloop(setup());      // initialising the array of records inside this method so the records can be passed
		
		byemessage();           // BYE printed in large letters on screen 
		System.exit(0);
		
		Question_Bank a= new Question_Bank();     // record initialised here used later for the array of records
		
		
		
	
	}
	
  ////////////////////////////////////////////////////////////////////////////////////////// WELCOME MESSAGE, PLAYER METHOD 
  
  
	public static void welcomemessage()
	{                                                                      // simple welcome message explaining game. 
		
		print("WELCOME to the music quiz. There are 9 questions.\n"); 
	
		print("            Enjoy the game.\n");   

		showlastscores();
				
		return;
		
	}
	
	public static void showlastscores()
	{
		String printold;
		printold = input("\n would you like to view how well the previous saved player did?");
		if (printold.equals("yes"))
		{
			
			try{
				outputmethod();
			}catch(IOException e){
				print("This file cannot be loaded right now or does not exist. Good luck with the game.");
			}
				print("Can you beat that score? lets find out..");
		}// end if
		print("\n");
	}// end method
	
////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	public static String playermethod()
	{
		String playername;                                         // playername taken for use in the prog to personalise it more to the user.
		playername = input("Enter the player name");               // point of this is to  add a small feature to the game
		print("Welcome "+ playername+ "! Lets  begin.......");    // var playername is used later on to add to a record
		print("If you get the answer wrong you will get a 0");     // plans for project mean this var will be saved as a file so old player scores can be 
		print("If you get it right a dice throw decides how");     // found and printed to the user at the start as part of a file IO method
		print("         many points you get!");
		return playername;
		
	}
	
////////////////////////////////////////////////////////////////////////////////// GETTER AND SETTER METHODS	
	

	public static Question_Bank setquestions(Question_Bank a, String questions)
	{
		a.question = questions;                   
		return a;
	}
	
	public static String getquestions(Question_Bank a)
	{
		return a.question;
		
	}

	
	public static Question_Bank setanswers(Question_Bank a, String  answers)
	{
		a.answer = answers;
		return a;
	}
	
	public static String getanswers(Question_Bank a)
	{
		return a.answer;
		
	}
	
///////////////////////////////////////////////////////////////////////////////////ARRAY  OF RECORDS 


	
	public static Question_Bank[] setup()
	{
		final int MAX_Q = 9; // 9 because there are only 9 questions
		
		
		Question_Bank[] array = new Question_Bank[MAX_Q];
		
		Question_Bank a1 = new Question_Bank();
		setquestions(a1, "Which member of One Direction released 'Sign of the Times' in 2017?");
		setanswers(a1, "harry styles");
		array[0] = a1;
		
		
		Question_Bank a2 = new Question_Bank();
		setquestions(a2, "Who had a 2017 hit with 'Chained to the Rhythm'?");
		setanswers(a2, "katy perry");
		array[1] = a2;
		
		Question_Bank a3 = new Question_Bank();
		setquestions(a3, "Which Irish city did the 'girl' from Ed Sheeran's single hail from? \n a) Galway b) Dublin c) Belfast");
		setanswers(a3, "a");
		array[2] = a3;
		
		Question_Bank a4 = new Question_Bank();
		setquestions(a4, "4:44 is the title of the fourteenth studio album from which US rapper? \n a) Eminem b) Dr. Dre c) Jay-Z");
		setanswers(a4, "c");
		array[3] = a4;
		
		Question_Bank a5 = new Question_Bank();
		setquestions(a5, "Luis Fonzi and Daddy Yankee are responsible for which track that took the world by storm in 2017?");
		setanswers(a5, "despacito");
		array[4] = a5;
		
		Question_Bank a6 = new Question_Bank();
		setquestions(a6, "Gang Signs & Prayer is the title of the debut album from which English rapper?");
		setanswers(a6, "stormzy");
		array[5] = a6;
		
		Question_Bank a7 = new Question_Bank();
		setquestions(a7, "This group was responsible for the hits 'Welcome To The Jungle', 'Sweet Child O' Mine' and 'November Rain'. Who were they?");
		setanswers(a7, "guns and roses");
		array[6] = a7;
		
		Question_Bank a8 = new Question_Bank();
		setquestions(a8, "Which classic band recorded the following songs: 'I Want To Hold Your Hand', 'Yesterday', 'Hey Jude' and 'Let It Be'?");
		setanswers(a8, "the beatles");
		array[7] = a8;
		
		Question_Bank a9 = new Question_Bank();
		setquestions(a9, "Which great band were responsible for these hits: 'I Can't Get No Satisfaction', 'Paint It Black', 'Honky Tonk Woman' and 'It's Only Rock N' Roll'?");
		setanswers(a9, "rolling stones");
		array[8] = a9;
		
		return array;
	}

	
	


//**************************************************************************************************************************************//
	
		
	public static void whileloop(Question_Bank[] array)                 
	
	{
		String [] userinput = new String [9];      // declaring user input array to take user inputs
		
		int [] totalscores = new int [9];     // declaring and initialising total scores record. setting them all to 0 to start with. 
		
		for(int i = 0; i<9; i++)
		{
		totalscores[i] = 0;
		}
		
		String playername = playermethod();
		
		String nextround = "yes";     
		String see;
		// while loop allowing user to exit at the end of each round or continue to the next. 
		while(nextround.equals("yes"))
		{
		
			rounds(array, userinput, totalscores);
			nextround = input("Do you want to see your scores? \n                yes/no");
			if(nextround.equals("yes")) break;
		
		}
		int endscore = 0;
		
		endscore = scoredsections(totalscores);
		
		Score user = createplayerinfo(playername, endscore);
		
		endprints(totalscores,user);
		
		
		return;
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////// ROUNDS /// FOR LOOP IN WHILE LOOP 
	
	
	public static void rounds(Question_Bank[] array, String [] userinput, int [] totalscores)
	{
		
		String useragain = "yes";
		
		while (useragain.equals("yes"))
		{                                           // while loop encloses the question loop which calls the marking method and score storing method.  
		for(int i = 0; i<9; i++)
		{
		String userresponse;                                              
		userresponse = input("Question " + (i+1) + "\n" + getquestions(array[i])); // users answer so it can be matched to actual answer
		userinput[i] = userresponse;                            // saving users answer 
		findscore(totalscores, i, array, userinput);        
		String result;                                       // reslut var to save response from foundanswer method
		result = findanswer(i, array, userinput, totalscores);  		// pass vars needed to find the answer.
		print(result);
		
		} // end for loop 
		useragain = input("Would you like to try the quiz again or keep your scores?");
		
		} // end while loop 
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void findscore(int [] totalscores, int i, Question_Bank[] array, String [] userinput) // marking method
	{
		int dicethrow;
		dicethrow = dice();
			
		
		if (userinput[i].equals(getanswers(array[i])))  //again equating the users answer to the actual answer
		{
			totalscores[i] = dicethrow;    // add the value from the dice roll to the array. 
		}
		else 
		{
			totalscores[i] = 0;   // 0 to fill the array index. 
		}
		return; 
	}
	
	public static int dice()   //dice rolling method
	{
		Random dice = new Random();              
		int dicethrow = dice.nextInt(6)+1;
		return dicethrow;
		
	}
	
	public static String findanswer( int i, Question_Bank[] array , String [] userinput, int [] totalscores) //told if they get the answer right or not.
	{
		String foundans;                              // var to be assignes and then returned
		
		if (getanswers(array[i]).equals(userinput[i]))         // if to decide if userinput it true or false
		{
			foundans = "Did you guess? \n   Congratulations! Time to roll the dice.\n       You get " + totalscores[i] + " point/s.\n";     //positive respone
		}
		else
		{
			foundans =  "Did you guess? \n   Ooops, that's not the correct answer." + getanswers(array[i]) + " is the correct answer, you get 0 points.\n"; //negative response
		}
		
		return foundans; 
	}
	
	
	
	
	

	////////////////////////////////////////////////////////////////////////////////////////////////////// SCORE PER SECTION
	
	public static int scoredsections(int [] totalscores)
	{
		
		int scoreA = 0;                                    // method sums up total for array totalscores using for loop 
		
		for(int input : totalscores){
			scoreA = scoreA + input;
		}
		
		return scoreA;                                   // value returned to be used in other methods
		
	}
	
	
	 public static Score createplayerinfo(String playername, int endscore)
   {
       Score s1 = new Score();
       
       s1 = setName(s1, playername);
       s1 = setscore1(s1, endscore);
	   print("\n Congratulations " + getName(s1) + "! You scored: " + getscore1(s1) +  " points.");
   
       
       return s1;                       // record returned. method called later. 
   }
   
    
   public static String getName (Score s)
   {
     return s.player;                                    // getter method for the users name 
   }

 
   public static int getscore1 (Score s)
   {
     return s.score1;                                     // getting the score record for the record print at the end 
   }

   

	public static Score setName (Score s, String playername)
   {
     s.player = playername;                              // set player name method 
     return s;
   }
   
   
   public static Score setscore1 (Score s, int endscore)
   {
     s.score1 = endscore;                                 // return score set in record 
     return s;
   }

 /////////////////////////////////////////////////////////////////////////////////// END PRINT METHOD  
   
	public static void endprints(int [] totalscores, Score s1)
	{
		
		
		print("the scores in ascending order are:");
		
      // this method is to break down the printing being done. the methods to pass the array values into the correct method. 	
			
	
		int [] qNumArray = sortarray();   // new array = the sort array method so values in that method returned to the qnumarray array 
		sort(totalscores, qNumArray);
		inputoutputmethod(s1);
		
		
	}
	
	public static void inputoutputmethod(Score s1)
	{
		String save;
		save = input("Would you like to save your playername and total score?");
		if(save.equals("yes"))
		{
			try{
				savemethod(s1);
			}catch(IOException e){
				print("error in saving process, please try again later");
			}
		}
		
	}
	
	
	   static void write_array(int[] totalscores, int [] qNumArray)
    {
		print("\n \t __________________");                             // method that creates the table of scores 
		print("\t| QUESTION | SCORE |");                            // for loop used to print each row containing array values 
		print("\t|__________|_______|");
	    for (int i=0; i < totalscores.length; i++)                  // array length used so only the correct number of rows are printed 
	    {
	            print("\t|     "+ qNumArray[i]+"    |   " + totalscores[i] + "   |");
								 
	    }
	    print("\t|__________|_______|");
    }
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////// BUBBLE SORT ALGORITHM
	
	
	public static int [] sortarray()
	{
		int [] sortQnumbers = new int [9];                       // intitialise array that will keep question numbers for sorted scores
		for (int i=0; i<9; i++)
		{
			sortQnumbers [i] = i+1;
		}
		
		return sortQnumbers;                    // returning array 
	}
	
	
	static void sort (int[] totalscores, int [] qNumArray)
    {
        boolean sorted=false;    // initial variable value 
	
        while (!sorted)         // while loop so that loop doesnt stop until the scores are sorted. there is no fixed length for this so a while loop is used
        {
	        
            sorted = true;     // variable changed 
            
	                           //traverse array switching ill-ordered pairs
	       for (int i=0; i < totalscores.length-1; i++)
	       {
                if (totalscores[i+1] > totalscores [i])
                {
		          
		          int tmp = totalscores[i];            // if the value in one index of the array is greater than the one after it then they should swap 
		          totalscores[i] = totalscores[i+1];     // new variable made to hold value being moved. value in indexs written over
		          totalscores[i+1] = tmp;                  // value stores in temporary variable assigned back to the lower index
				  
				  int tmpQ = qNumArray[i];             // same process occurring here. 
				  qNumArray[i] = qNumArray[i+1];         // integers in question number array swapped so we can keep track of scores correspoding q number 
				  qNumArray[i+1] = tmpQ;
				  
		          // array wasn't sorted
		          sorted = false;                      // if the if is satisfied that means that values need need to be sorted so sorted var changed to false
													   // this way the for while loop will run again so the numbers can be sorted.    
                  
		         } // end if statement 
				 
			
	       }// end for loop
		   
	    } // end while loop
		
		write_array(totalscores, qNumArray); // print out the scores and q numbers 
		
    } // end method 
	
	
	
	    
 
	
//////////////////////////////////////////////////////////////////////////////////// INPUT AND OUTPUT METHODS





	public static String input(String message)
	{
		Scanner scanner = new Scanner(System.in);           // scanner imported in line 1 of the program
		print(message);                                     
		String answer;
		answer = scanner.nextLine().toLowerCase();          // to lower case is a method that ignores the capital letters and reads them as lower case
		return answer;                                      // all answers are in lower case so regardless if the user enters the correct answer in capital 
	}                                                       // letters the answer will be correct as the case will be the same 

	public static void print(String message)
	{
		 System.out.println(message);
		 return;
	}
	
	// no integer input needed because no integers are inputted by the users. 
	

///////////////////////////////////////////////////////////////////////////////// WELCOME AND END MESSAGE 	




	public static void hello()    
	{
		print(" \n \n");
		print("  HH    HH  EEEEEEE LL        LL           OOO");          // adds feature to program 
		print("  HH    HH  EE      LL        LL         OO   OO");
		print("  HH    HH  EE      LL        LL        O       O");
		print("  HHHHHHHH  EEEEE   LL        LL        O       O");
		print("  HH    HH  EE      LL        LL        O       O");
		print("  HH    HH  EE      LL        LL         OO   OO");
		print("  HH    HH  EEEEEEE LLLLLLLLL LLLLLLLLL    OOO");
		print("\n \n");
		print(" \n");
		
		welcomemessage();
		
		return;
	}
	
	
	
	public static void byemessage()    // printed at the end 
	{
		print("\n");
		print("   BBBBBB    YY      YY  EEEEEEEE     ");
		print("   BB    BB   YY    YY   EE        ");
		print("   BB    BB    YY  YY    EE        ");
		print("   BBBBBB       YYYY     EEEEE      ");
		print("   BB    BB      YY      EE        ");
		print("   BB    BB      YY      EE       ");
		print("   BBBBBB        YY      EEEEEEEE   ");
		print("\n \n");
		print("         THANKS FOR PLAYING        ");
		print(" \n");
		
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////// INPUT OUTPUT 

	public static void savemethod(Score s1) throws IOException
	{
		PrintWriter outputStream = new PrintWriter(new FileWriter("mydata.txt"));

             
        outputStream.println("Name: " + getName(s1) + " Score: " + getscore1(s1));            // reads the line and saves it to file mydata.txt. records imported and used to print user name and score
        
        
        outputStream.close();
	} // end method
	
	
	public static void outputmethod () throws IOException
	{
		BufferedReader inStream = new BufferedReader(new FileReader("mydata.txt"));
 
        String nextword = inStream.readLine();                                                  // while loop reads each line in file and prints all until the line it reads has nothing in it. 

     
         while (nextword != null)
        {
            System.out.println(nextword);
            nextword = inStream.readLine();
        }

        inStream.close();
	} // output method
	
	
///////////////////////////////////////////////////////////////////////////////// END PROGRAM


} // end program



//////////////////////////////////////////////////////////////////////////////// SCORE CLASS
class Question_Bank
{
	String question;                 // record for array of records
	String answer;

}


class Score
{
	String player;               // record for storing 
	int score1;
}
