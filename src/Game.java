/* -Tyler Knight
 * -EAS 108, 9/2015
 * -TA: Colin Pronovost
 * -Workshop leader: Mirijana
 * 
 * See README.TXT for additional information features,  
 * extra credit features, explanations and design decisions. 
 */

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.math.*;

public class Game {
	
	public static void main(String[] args) {
		/* Main method.
		 */
		Menu(); //Open menu upon startup
		//Control flow should never go here because game should always terminate in Quit()
		System.out.println("There has been an error. Please restart the game.");
		System.out.println("Quitting game...");
	}
	
	public static void Menu(){
		/* Method that prints the main menu and waits for user input.
		 */
		//TITLE
		System.out.println("+___________WWII__________+");
		System.out.println("|[******KURST  1943******]|");
		System.out.println("|^^^^^^^^^^^^^^^^^^^^^^^^^|");
		
		//MENU OPTIONS
		System.out.println("|>>>>>>>>>>>>>>>|");
		System.out.println("|Menu Options:  |");
		System.out.println("| 1=New Game    |");
		System.out.println("| 2=Info & Rules|");
		System.out.println("| 3=Quit        |");
		System.out.println("|>>>>>>>>>>>>>>>|");
		
		//MENU MECHANICS
		int selection = Choice(3, "Enter Choice");
		switch(selection){
			case 1:
				NewGame();
				break;
			case 2:
				Info();
				break;
			case 3:
				Quit();
				Menu();
				break;
		}
	}
	
	public static void Info(){
		/* Method that provides information on gameplay.
		 * Explains the rules, set up and displays credits.
		 */
		//INFORMATION
		System.out.println("|----------------INFORMATION---------------|");
		System.out.println("|Objective - Obliterate the enemy's tank   |");
		System.out.println("|   before they obliterate you.            |");
		System.out.println("|Rules - You take turns volleying shots    |");
		System.out.println("|   at the enemy, firing to the death.     |");
		System.out.println("|   You face off against the enemy until   |");
		System.out.println("|   you kill their tank...or get killed.   |");
		System.out.println("|   A direct hits means instant defeat     |");
		System.out.println("|   while nearby hits amass damage. You or |");
		System.out.println("|   the enemy are defeated at 100% damage, |");
		System.out.println("|   and whoever runs out of ammo first     |");
		System.out.println("|   automatically surenders to the enemy.  |");
		System.out.println("|MoralPoints - Keep track of MoralPoints.  |");
		System.out.println("|   Each shot taken costs 1 MoralPoint,    |");
		System.out.println("|   direct hits boosts MoralPoints by 5,   |");
		System.out.println("|   and each near miss gains 1 MoralPoint. |");
		System.out.println("|   If MoralPoints dip below -15, you lose.|");
		System.out.println("|Type \'q\' or \'quit\' at any time to quit    |");
		System.out.println("|------------------------------------------|");
		System.out.println("| Designed and coded by Tyler Knight, 2015 |");
		System.out.println("|------------------------------------------|");
		System.out.println("[Press Enter to return to Menu]");
		Pause();
		Menu();
	}
	
	public static void NewGame(){
		/* Method that asks user for settings about the game.
		 * Once settings are determined, NewGame() calls Play(), beginning game.
		 */
		
		//CHOOSE TANK
		//T-34 ASCII decorations and specs
		System.out.println("**Russian T-34**");
		System.out.println("      __()__       T-34 Specs");
		System.out.println("   __/_*||*_\\__    ----------");
		System.out.println("  /____/==\\____\\   *Weight:      34.1 tons");
		System.out.println(" *\\||/-^^^^-\\||/*  *Cannon:      76.2mm");
		System.out.println("|%%%%%%%%%%%%%%%%| *Gun:         DT 7.62mm (x2)");
		System.out.println("                   *Ammunition:  100 rounds");
		System.out.println("                   *Fuel:        210 gallons");
		System.out.println("                   *Range:       289 miles");
		System.out.println("                   *Speed:       33 mph");
		
		//Panther-V ASCII decorations and specs
		System.out.println("**German Panther-V**");
		System.out.println("      __{}__       Panther-V Specs");
		System.out.println("   __/]\"||\"[\\__    --------------");
		System.out.println("  /o\\__/==\\__/o\\   *Weight:      49.4 tons");
		System.out.println(" *\\||/-^^^^-\\||/*  *Cannon:      75mm");
		System.out.println("|%%%%%%%%%%%%%%%%| *Gun:         MG-34 7.92 (x2)");
		System.out.println("                   *Ammunition:  79 rounds");
		System.out.println("                   *Fuel:        190 gallons");
		System.out.println("                   *Range:       160 miles");
		System.out.println("                   *Speed:       29 mph");
		
		System.out.println("PICK A BELLIGERENT");
		//CHOOSE TEAM
		System.out.println("|>>>>>>>>>>>>>>>>|");
		System.out.println("|Menu options:   |");
		System.out.println("| 1=Soviet Russia|");
		System.out.println("| 2=Germany      |");
		System.out.println("|>>>>>>>>>>>>>>>>|");
		int team = Choice(2, "Choose a side");
		//CHOOSE DIFFICULTY
		System.out.println("|>>>>>>>>>>>>>>>>|");
		System.out.println("|Menu options:   |");
		System.out.println("| 1=Hard         |");
		System.out.println("| 2=Normal       |");
		System.out.println("| 3=Easy         |");
		System.out.println("|>>>>>>>>>>>>>>>>|");
		int difficulty = Choice(3, "Choose enemy difficulty");
				
		System.out.println();
		System.out.println("BATTLE IS READY. ARE YOU?");
		Pause();
		Play(team, difficulty); //Begin Game
	}
	
	public static void Play(int team, int difficulty){
		/* Method that contains all code pertaining to game play and mechanics.
		 */
		
		//BEGIN GAME CODE
		
		//Initial story/history
		System.out.println("====================================================");
		System.out.println("====================================================");
		System.out.println("====================================================");
		System.out.println("05:"+RandRange(29,39)+"AM, July 5th, 1943: Near the Russian village of Kurst.");
		System.out.println("------------------------");
		if (team == 1) { //Russian perspective
		System.out.println("Russian infantry and artillery forces tensely await among ranks of thousands ");
		System.out.println("for the massive German offensive en route to their position...");
		System.out.println("Do they think they can storm your weary men and take more of your war-torn homeland?");
		System.out.println("Not today, not ever...you and your comrades are will fight to the last man.");
		System.out.println("A low mechanical rumble echos in the woods beyond the grassy field before you...");
		System.out.println();
		System.out.println(RandPrompt(new String[]  //RandPrompt chooses string from String[] randomly
				{"Private:\"Commander! Enemy tank sighted! Do we fire?\"", 
				"Private:\"Commander! A German tank has emerged just beyond the treeline! Preparing to fire!\"",
				"Private:\"Commander! German tank spotted! Take the controls!\""}));
		}
		if (team == 2) { //German perspective
			System.out.println("Thousands of German infantry and artillery forces race towards the Russian");
			System.out.println("defensive line, aiming to obliterate it and push deeper into Russia.");
			System.out.println("A victory here is cruical for the German military. Failure is not an option.");
			System.out.println("You are prepared for a fight for your country, to the last tank.");
			System.out.println("Your unit roars through the woods, crushing trees and logs. Ahead is a grassy clearing...");
			System.out.println();
			System.out.println(RandPrompt(new String[] 
					{"Private:\"Commander! Russian tank ahead! Do we fire?\"", 
					"Private:\"Commander! The Russians are just across the field! We are ready to fire!\"",
					"Private:\"Commander! Russian tank spotted ahead! Take the controls!\""}));
		}
		System.out.println();
		System.out.println("You must lead your men and destroy the enemy tank that has appeared.");
		System.out.println("The enemy turret swivels in your direction. You grab the controls and engage.");
		if (team == 1){
			System.out.println("You have seconds left before the Germans fire! Quick!");
		}
		if (team == 2){
			System.out.println("You have seconds left before the Russians fire! Quick!");
		}
		System.out.println("[Press Enter to aim the cannon]");
		Pause();
		
		//DECLARATIONS
		//Shared variables
		int round = 0; //Rounds that have been played
		double inRadians = 0.0174532925; //Used to convert degrees into radians that java.math can use (sometimes .toRadians() is used)
		int shellType = -1; //Will store users/CPUs shell selection
		int distance = RandRange(25, 500); //Randomly chosen distance to enemy (in meters)
		int turn = team; //Alternates between 1&2, representing which side is currently shooting. Initialize the first shooter as player's team selection
		int maxVel = 75; //The fastest launch velocity user or computer is allowed to use
		String enemyName = ""; //Either "Russian" or "German" depending on user's team
		if (team == 1){enemyName = "German";} else {enemyName = "Russian";} //Determine team names for reference in text
		
		//Human variables
		int damage = 0; //Damage to armor in %
		int moralPoints = 0; //Equivalent to 'score' in project description (see information screen in Info())
		int[] shells = {20,10,3}; //shells[0]==# of std, shells[1]==# of explosive, shells[2]==# of high explosive
		int[] shellsBlastRadii = {1,5,10}; //Corresponding shell blast radii
		int shellArraySum = 33; //Total shells in stock
		double launchAngle = -1; //Launch variables to be used by player
		double launchVel = -1;
		double flightTime = -1; //All initialized to -1 for easy bug identification
		double timeAtApex = -1;		
		double shellRange = -1;
		double offBy = -1; 
		
		//CPU variables
		int enemyDamage = 0;
		int enemyMoralPoints = 0;
		int[] enemyShells = {20,10,3}; //Identical variables just for the enemy
		int[] enemyShellsBlastRadii = {1,5,10}; //all with prefix 'enemy'
		int enemyShellArraySum = 33;
		double enemyBlurredDistance = -1;
		double enemyLaunchAngle = -1; //Launch variables to be used by CPU
		double enemyLaunchVel = -1;
		double enemyFlightTime = -1;
		double enemyTimeAtApex = -1;
		double enemyShellRange = -1;
		double enemyOffBy = -1;
		
		//BEGIN GAME LOOP
		//Loop structure:
		//  While (game not over):
		//    if (human's turn):
		//      human aim, shoot and results
		//    else
		//      computer aim, shoot and results
		
		while (!CheckGameOver(shellArraySum, enemyShellArraySum, damage, enemyDamage, moralPoints, enemyMoralPoints, shells, enemyShells)) { //While nobody is dead, continue the game
			if (turn == team){ //It's human's turn to shoot
				round++; //Increment rounds played every time it comes back around to player's turn
				PrintStatus(team, turn, round, damage, enemyDamage, moralPoints, enemyMoralPoints, shells, enemyShells); //Pass variables about state of game to PrintStatus()
				
				//AIMING SEQUENCE
				
				System.out.println("---Munitions availible---");
				System.out.println("| 1=Standard: 1m blast radius");
				System.out.println("| 2=Explosive: 5m blast radius");
				System.out.println("| 3=High Explosive: 10m blast radius");
				
				//Choose shell
				shellType = Choice(3, "Choose your shell:");
				while (shells[shellType-1] <= 0){ //Ask until a shell in stock is picked
					System.out.println("You've run out of this shell! Pick another.");
					shellType = Choice(3, "Choose your shell:");
				}
				shells[shellType-1] -= 1; //Decrement count of chosen shell
				
				//Get the sum of all remaining ammunition for use later in CheckGameOver()
				//CheckGameOver() will end game if ammunition <= 0
				shellArraySum = 0;
				for (int i=0; i<shells.length; i++){
					shellArraySum += shells[i];
				}
				
				System.out.println(RandPrompt(new String[] 
						{"Your instruments say the "+enemyName+" tank is roughly "+distance+"m away.",
						"The "+enemyName+"s are "+distance+"m downrange.",
						"According to your sights, the "+enemyName+"s are about "+distance+"m away.",
						"The "+enemyName+" tank is approximately "+distance+"m from your position."}));
				
				launchAngle = ChoiceDouble(0,90,"Choose a launch angle in degrees:"); //Input ranges 0.0-90.0
				launchVel = ChoiceDouble(0,maxVel,"Choose a projectile velocity in m/s:"); //Input ranges from 0.0-maxVel
				
				System.out.println("MAIN GUN READY. PRESS ENTER TO FIRE.");
				Pause();
				moralPoints -= 1; //Cost of firing
				
				//TRAJECTORY AND FIRING SEQUENCE
				
				System.out.print("FIRING! *BOOM* [Shell in flight] ");
				flightTime = (2*launchVel*Math.sin(launchAngle*inRadians))/(9.80665); //Time of entire flight in seconds
				timeAtApex = flightTime/2; //Time at top of flight path
				shellRange = ((launchVel*launchVel)*Math.sin(2*launchAngle*inRadians))/(9.80665); //Calculates horizontal distance shells travels
				offBy = shellRange - distance; //Distance tank was missed by. Negative if shell fell short, positive if overshot.
				
				for (double i=0; i < flightTime; i+=(flightTime/16)){ //Outputs a flight progress bar
					if (i < timeAtApex) {
						System.out.print("+"); //Shell is rising
					} else {
						System.out.print("-"); //Shell is falling
					}
					Sleep(1000*((long)(flightTime))/16); //Sleep for 1/16 of flight path
				}
				System.out.print("IMPACT!");
				System.out.println();
				
				//IMPACT AND RESULTS SEQUENCE
				
				//Project assignment's required message: 
				if (Math.abs(offBy) <= 1){ //If within 1m, output direct hit 
					System.out.println("Direct hit!");
					enemyDamage = 100; //Set enemy damage to lethal 100%
					moralPoints += 5; //Award moralPoints
				} else if (Math.abs(offBy) <= 3){ //If within 3, output near miss
					System.out.println("Near miss!");
					moralPoints += 1; //Award moralPoints
				}
				
				//Hit info /w meters
				if (Math.abs(offBy) > 3){ //Supplements hit info with numbers. Useful if user is VERY off target and prevents frustration.
					if (offBy < 0){
						System.out.println("Your shot fell short by "+Math.abs((int)(offBy))+"m.");
					} else {
						System.out.println("Your shot went long by "+((int)(offBy))+"m.");
					}
				}
				
				//Assess & deal damage
				int shellPower = 20 * shellsBlastRadii[shellType-1]; //Shell's damaging power is 20 times its blast radius in meters
				double damageDealt = (shellPower)/(offBy*offBy); //Damage equation is shell power over the inverse square of distance from explosion epicenter 
				
				if (damageDealt >= 1 && enemyDamage < 100){ //If damage from shell is greater than 1% and they aren't dead yet, deal damage to enemy 
					enemyDamage += damageDealt;
					System.out.println("The blast from your shell caused "+((int)(damageDealt))+"% damage to the enemy!");
				}
				
				if (enemyDamage > 100){
					enemyDamage = 100; //Don't overkill them because that's just rude
									   //Since enemyDamage >= 100, this while-loop will exit soon after this statement
				}
				if (!CheckGameOver(shellArraySum, enemyShellArraySum, damage, enemyDamage, moralPoints, enemyMoralPoints, shells, enemyShells)){ //Only display if game isn't over
					System.out.println(RandPrompt(new String[] 
							{"The "+enemyName+"s are going to return fire! Press Enter to take cover!",
							 "The "+enemyName+"s are preparing to fire back! Press Enter and pray that they miss.",
							 "Watchout! The "+enemyName+"s have recovered and are returning fire! Press Enter to continue."}));
					Pause();
				}
									
				if (turn == 1) { //Turn switcher
					turn = 2;
				} else {
					turn = 1;
				}
				
			} else { //Enemy's turn to shoot
				
				PrintStatus(team, turn, round, damage, enemyDamage, moralPoints, enemyMoralPoints, shells, enemyShells); //args/arg order is identical to other PrintStatus call b/c PrintStatus() handles POV
			
				//AIMING SEQUENCE
				
				//CPU CHOOSE A RANDOM SHELL TO FIRE:
				//Sum enemyShell array to get # of total shells
				enemyShellArraySum = 0;
				for (int i=0; i<enemyShells.length; i++){
					enemyShellArraySum += enemyShells[i];
				}
				
				int randomType = RandRange(1, enemyShellArraySum); //Pick a random number between 1 and # of shells
				if (randomType <= enemyShells[0]) { //If rand# between 0 & # of standard shells...
					if (enemyShells[0] > 0) {
						enemyShells[0] -= 1; //...then decrement # of standard shells (if its > 0)... 
						shellType = 1; //...and select that as the shellType to be fired.
					}
				} else if (randomType > enemyShells[0] && randomType <= enemyShells[1]) { //Do same for other shell types
					if (enemyShells[1] > 0) {
						enemyShells[1] -= 1; 
						shellType = 2;
					}
				} else if (randomType > enemyShells[1]) {
					if (enemyShells[2] > 0) {
						enemyShells[2] -= 1;
						shellType = 3;
					}
				} //EX: (x10)std, (x20)explding, (x30)HE shells=60 total. If rand#(0-60) = 0-10: shellType=1, 11-30: shellType=2, 31-60: shellType=3...
				  // Will choose shells with a probability based on the number of each type remaining
				  //...repeat each turn until no shells are left
				
				//Re-sum shells for later use in CheckGameOver()
				enemyShellArraySum = 0;
				for (int i=0; i<enemyShells.length; i++){
					enemyShellArraySum += enemyShells[i];
				}
				
				//CPU CHOOSE A VELOCITY AND ANGLE TO FIRE AT
				//Using angle of reach formula and velocity of reach formula, could hit human every time.
				//To add challenge to game, CPU will have distance to human "blurred"-
				//-so it partially relies on luck. Blurriness depends on game difficulty selected by user. 
				//(Future idea: match difficulty based on how off player's last shot was, and how off their shots are on average.)
 
			    double minVel; //maxVel == literal value: 75
			    double maxVelFlightTime;
			    double randomBlur; 
			    if (round == 1){ //Prevents computer from killing player on the first round
				    do {
				    	randomBlur = RandRangeDouble(-20*difficulty, 20*difficulty); //Computer's first shot more likely to be very off, just like user's will be
				    } while(Math.abs(randomBlur) < 1);
			    } else {
			    	randomBlur = RandRangeDouble(-5*difficulty, 5*difficulty); //Formula to determine width of random range that blurs distance (at menu, difficulty: 1=hardest, 3=easiest)
			    }
			    
			    enemyBlurredDistance = distance + randomBlur; //Distance the CPU is aiming for, blurred.
			    minVel = VelocityNeeded(45, enemyBlurredDistance); //Minimum velocity: lowest velocity needed to hit target at 45 deg.
			    maxVelFlightTime = (2*maxVel*Math.sin(Math.toRadians(AngleOfReach(maxVel,enemyBlurredDistance))))/9.80665; //How many seconds to hit player's position firing with max velocity @ 45 degrees (will be the shortest of all solutions)
			    
			    //The impatient player must watch and wait for sequence of enemy firing, so CPU can't choose a launch velocity that will have an excess flight time
			    if (maxVelFlightTime >= 4){ //If the flight will take longer than 4 seconds using fastest velocity, set launch velocity to fastest velocity 
			        enemyLaunchVel = maxVel;
			    } else {
			        enemyLaunchVel = RandRangeDouble(minVel, maxVel); //Otherwise choose a random launch velocity in the valid range
			    }

			    enemyLaunchAngle = AngleOfReach(enemyLaunchVel,enemyBlurredDistance); //Calculate launch angle needed to hit target given the newly set launch velocity
				
			    if (minVel > maxVel){ //Catches any errors if tryna fire too fast
			        minVel = maxVel-1;
			        System.out.println("Player is too far away or an error has occurred."); //minVel SHOULD never be > maxVel b/c enemy always closer than 500m which is in range at 75m/s
			    }
				
				//TRAJECTORY AND FIRING SEQUENCE
				
				enemyFlightTime = (2*enemyLaunchVel*Math.sin(enemyLaunchAngle*inRadians))/(9.80665); //Time for entire flight
				enemyTimeAtApex = enemyFlightTime/2; //Time at top of flight path
				enemyShellRange = ((enemyLaunchVel*enemyLaunchVel)*Math.sin(2*enemyLaunchAngle*inRadians))/(9.80665); //Calculates horizontal distance shells travels
				enemyOffBy = enemyShellRange - distance; //Distance player tank was missed by. Negative if fell short, positive if overshot.
				
				Sleep(1000);
				System.out.println("The "+enemyName+"s have loaded their shell...");
				Sleep(1000);
				System.out.println("The "+enemyName+"s have taken aim at you...");
				Sleep(1000);
				System.out.println("The "+enemyName+"s have fired!");
				System.out.print("*BOOM*");
				
				enemyMoralPoints -= 1; //Cost of firing a shell
								
				for (double i=0; i < enemyFlightTime; i+=enemyFlightTime/16){ //Print shell trajectory progress bar in real time
					if (i < enemyTimeAtApex) {
						System.out.print("+"); //Shell rising
					} else {
						System.out.print("-"); //Shell falling
					}
					Sleep(1000*((long)(enemyFlightTime))/16); //Sleep for 1/16 of flight path
				}
				System.out.print("IMPACT!");
				System.out.println();
				
				//IMPACT AND RESULTS SEQUENCE
				
				//Direct and close hit warnings for player
				if (Math.abs(enemyOffBy) <= 1){ //If within 1m, output direct hit 
					System.out.println("The "+enemyName+"s made a direct hit!");
					damage = 100; //Set player damage to a lethal 100%
					enemyMoralPoints += 5;
				} else if (Math.abs(enemyOffBy) <= 3){ //If within 3m, output near miss
					System.out.println(RandPrompt(new String[] {
							"That was a close one!",
							"Near miss!",
							"Their shot just missed you!",
							"That one almost hit you!"}));
					enemyMoralPoints += 1;
				}
				
				//More specific hit warning for larger misses 
				if (Math.abs(enemyOffBy) > 3){
					if (enemyOffBy < 0){
						System.out.println("The "+enemyName+"s' shell fell short of you by "+Math.abs((int)enemyOffBy)+"m.");
					} else {
						System.out.println("The "+enemyName+"s overshot you by "+(int)(enemyOffBy)+"m.");
					}
				}
				
				//Damage dealing and warnings
				int enemyShellPower = 20 * enemyShellsBlastRadii[shellType-1]; //Shell's damaging power is 20 times its blast radius in meters
				double enemyDamageDealt = (enemyShellPower)/(enemyOffBy*enemyOffBy); //Damage equation is shell power over the inverse square of distance from explosion 
				
				if (enemyDamageDealt >= 1 && damage < 100){ //If damage from shell is over 1% and player isn't dead yet, deal damage to player 
					damage += enemyDamageDealt;
					System.out.println("The blast from their shell caused you "+((int)enemyDamageDealt)+"% damage!");
				}
				
				//Only offer to fire back/switch turns if the game is ongoing
				if (!CheckGameOver(shellArraySum, enemyShellArraySum, damage, enemyDamage, moralPoints, enemyMoralPoints, shells, enemyShells)){
					System.out.println("Press enter to fire back at them!");
					Pause();
					
					if (turn == 1) { //Switch turns
						turn = 2;
					} else {
						turn = 1;
					}	
					
				} //Else: OP is kill
				  //round will be over and while-loop will exit if player is dead
			} //End turn taker
			
		} //End while loop
		
		//If execution is here, then the while loop exited and the game is over
		//Figure out why:
		if (damage >= 100){
			System.out.println("---------------------------------------------");
			System.out.println("You're dead! Your tank is totalled, and your men are no more.");
			if (team == 1){
				System.out.println("You have let down your comrades and your country. The Germans now charge through the hole you left in the");
				System.out.println("defensive line and attack your unit from behind. Your fellow soldiers don't have time to react. By sundown ");
				System.out.println("most of your unit is in shambles, and the fight wears on...but barely.");
				System.out.println("Within a week, Russian resistance is crushed and Germany takes the countryside.");
				System.out.println("If only you had fought a little harder...");
				System.out.println();
			} else {
				System.out.println("Germany suffers because of your loss. Responsibility for the failing offensive rests on your shoulders");
				System.out.println("more than just any other soldier in the German military. Your tank, your men, were mislead by you into");
				System.out.println("a suicide mission that you had a chance to survive. The Russians continue to relentlessly shell and");
				System.out.println("massacre your ranks until your forces pull back in full retreat, wounded and broken, not even a week later.");
				System.out.println("There's only one way to find redemption...");
				System.out.println();
			}
		} else
			
		if (enemyDamage >= 100){
			System.out.println("---------------------------------------------");
			System.out.println("You have defeated the "+enemyName+" tank! That last blow did critical damage to the armor");
			System.out.println("and it's occupants are now limping into retreat.");
			System.out.println("Congratulations on a decisive victory against the enemy! A sharp shooter like you deserves");
			System.out.println("to be on the front lines. Test your luck and play again!");
			System.out.println();

		} else
		
		if (moralPoints <= -16){
			Pause();
			System.out.println("---------------------------------------------");
			System.out.println("You have surrendered your position to the enemy! Your MoralPoints are at "+moralPoints+"...");
			System.out.println("which is a wartime low. You and your men are tired of being rattled by incoming shells and");
			System.out.println("missing shot after shot. As a result, they have lost the will to fight, and would rather");
			System.out.println("chance their fate at the hands of the "+enemyName+"s than face certain death by artillery.");
			System.out.println("Play again for a chance to fix your mistakes and rally your troops!");
			System.out.println();
			
		} else
			
		if (enemyMoralPoints <= -16){
			Pause();
			System.out.println("---------------------------------------------");
			System.out.println("Congratulations! You have caused the enemy tank to surrender. Your non-stop barrage of fire has");
			System.out.println("worn down the "+enemyName+"s moral to the point where they no longer wish to fight.");
			System.out.println("They are raising the white flag and exiting their vehicle.");
			System.out.println("But the battle isn't over. Many more hostile "+enemyName+"s wait in the woods, itching to fight.");
			System.out.println("A good soldier will do his duty and play again...");
			System.out.println();
		} else
		
		if (shellArraySum <= 0){
			Pause();
			System.out.println("---------------------------------------------");
			System.out.println("You put up such a ferocious battle that you've run out of ammunition!");
			System.out.println("With nothing to fire, you reluctantly throw your tank in reverse");
			System.out.println("and fall back. But a soldier never deserts his men.");
			System.out.println("A true warriors gets back in line and plays again...");
			System.out.println();
		} else
		
		if (enemyShellArraySum <= 0){
			Pause();
			System.out.println("---------------------------------------------");
			System.out.println("The enemy is retreating! They have fired so much ammunition they are out of");
			System.out.println("shells! Consider yourself lucky to have survived this long. It looks like");
			System.out.println("the "+enemyName+" tank you were just fighting is down for the count,");
			System.out.println("but your job isn't finished. There are still dozens of opponents to engage with.");
			System.out.println("If you have even a speck of bravery, you will re-engage and play again...");
			System.out.println();
		} else {
			//If game is over and none of the above reasons to exit while loop are true, there is an error
			System.out.println("There has been an error. Please restart the game.");
			Pause();
			return; //Should return all the way up to menu, then terminate through main() method
		}
		
		System.out.println("Play again?");
		System.out.print("(Y/N):");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (input.equals("Y") || input.equals("Yes") || input.equals("y") || input.equals("yes")){
			NewGame();
		} else {
			Menu();
		}
	} //End play method 
	
	public static Boolean CheckGameOver(int shellArraySum, int enemyShellArraySum, int damage, int enemyDamage, int moralPoints, int enemyMoralPoints, int[] shells, int[] enemyShells){
		/* Method to easily determine with a true/false if any conditions of the game
		 * imply that the game should stop running. 
		 */
		Boolean GameOver = false;
		if (shellArraySum <= 0 || enemyShellArraySum <= 0){
			GameOver = true;
			//System.out.println("Game Over: Out of ammo");
		}
		if (damage >= 100 || enemyDamage >= 100){
			GameOver = true;
			//System.out.println("Game Over: Damage too high");
		}
		if (moralPoints <= -16 || enemyMoralPoints <= -16){
			GameOver = true;
			//System.out.println("Game Over: Moral too low");
		}
		return GameOver;
	}
	
	public static double AngleOfReach(double vel, double distance){
		/* Method that helps the enemy AI find an angle to shoot at.
		 * Takes a velocity and a distance, then returns the angle needed
		 * to reach that distance.
		 */
		return 0.5*Math.toDegrees(Math.asin((distance*9.80665)/(vel*vel)));
	}
	
	public static double VelocityNeeded(double angle, double distance){
		/* Method that helps enemy AI find a velocity to shoot at. 
		 * Takes an angle and a distance, then returns the velocity needed
		 * to reach that distance.
		 */
	     return 2.2143*(Math.sqrt(distance*((1/Math.sin(angle)*(1/Math.cos(angle))))));
	}
	
	public static void PrintStatus(int humanTeam, int thisTeam, int roundsPlayed, int damage, int enemyDamage, int moralPoints, int enemyMoralPoints, int[] shells, int[] enemyShells) {
		/* Method that outputs the status of a fighter before the round commences. 
		 * Dynamic to print either from the POV of the enemy, or POV of human player.
		 */
		if (humanTeam == thisTeam) { //humanTeam == team == belligerent selected in NewGame(), thisTeam == turn, aka team currently aiming
			System.out.println("////////////////YOUR TURN////////////////"); //If true, its the human's turn this round
			System.out.println("|ROUND: "+roundsPlayed);
			System.out.println("|Damage: "+damage+"%");
			System.out.println("|Enemy Damage: "+enemyDamage+"%");
			System.out.println("|MoralPoints: "+moralPoints);
			System.out.println("|Enemy MoralPoints:"+enemyMoralPoints);
			System.out.println("|Shells: Standard(x"+shells[0]+")");
			System.out.println("|        Explosive(x"+shells[1]+")");
			System.out.println("|        High Explosive(x"+shells[2]+")");
			System.out.println("|////////////////////////////////////////");
		} else { //Otherwise it must be the computers turn
			System.out.println("//////////////ENEMY'S TURN///////////////");
			System.out.println("|ROUND: "+roundsPlayed);
			System.out.println("|Damage: "+damage+"%");    
			System.out.println("|Enemy Damage: "+enemyDamage+"%");
			System.out.println("|MoralPoints: "+moralPoints);
			System.out.println("|Enemy MoralPoints:"+enemyMoralPoints);
			System.out.println("|Enemy Shells: Standard(x"+enemyShells[0]+")"); //Note switched to enemyShells[] from shells[]
			System.out.println("|              Explosive(x"+enemyShells[1]+")"); //B/c we are outputting the CPU's game status
			System.out.println("|              High Explosive(x"+enemyShells[2]+")");
			System.out.println("|////////////////////////////////////////");
		}
	}
	
	public static void Quit(){
		/* Destination of menu choice "Quit",
		 * prompts for a quit confirmation, then 
		 * displays goodbye message and exits application
		 * or returns void and resumes execution at the place that called it.
		 */
		System.out.println("Quit: Are you sure?");
		System.out.print("(Y/N):");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next(); //Scan for String input
		if (input.equals("Y") || input.equals("Yes") || input.equals("y") || input.equals("yes")){
			System.out.println("");
			System.out.println("Thank you for playing!");
			System.out.println("Exiting...");
			System.exit(0); //Termination command
		} else {
			return;
		}
	}
	
	public static void Sleep(long milliseconds){
		/* Method that pauses execution for a given number of milliseconds with Thread.sleep().
		 */
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {}
	}
	
	public static void Pause(){
		/* Prompts the user to enter anything to continue and waits for input. 
		 * Returns or quits when input is given.
		 * Does not care about type.
		 */
		Scanner scanner = new Scanner(System.in);
		try {
			String input = scanner.nextLine();
			if (input.equals("quit") || input.equals("q") || input.equals("Quit") || input.equals("Q")){ //If user entered quit, call Quit() sequence
				Quit();
				return; //If they changed their mind, return and continue with game execution
			} else if (!input.equals("") || input.equals("")){ //Else, if user entered anything else
				return;
			}
			
		} catch (InputMismatchException e) {
			return;  //Ignores exception because type doesn't matter
			//Input, valid or otherwise, implies user hit enter key, so continue regardless
		}
	}
	
	public static int RandRange(int min, int max){
		/* Method returns a random INTEGER within the specified range, inclusive.
		 */
		Random random = new Random();
		return random.nextInt(max - min + 1) + min; //Random range formula for integers
	}
	
	public static double RandRangeDouble(double min, double max){
		/* Method returns a random DOUBLE within the specified range, inclusive.
		 * Almost same method as above, but formatted for doubles.
		 */
		Random random = new Random();
		return random.nextDouble()*(max - min) + min; //Random range formula for doubles
	}
	
	public static int Choice(int numChoices, String prompt){
		/* Method that streamlines process of getting user input for menus
		 * Asks the user FOR AN INT input from 1 to numChoices.
		 * Will handle exceptions and invalid input.
		 * Takes int & str, returns int.
		 */
		try {
			System.out.print(prompt + " (1-" + numChoices + "):");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next(); //Scan for string for continuous quit
			if (input.equals("quit") || input.equals("q") || input.equals("Quit") || input.equals("Q")){ //If user entered quit
				Quit(); //Call for quit confirmation and execution sequence
				return Choice(numChoices, prompt); //If Quit() returns, users changed mind, re-call Choice()
			}
			int choice = Integer.parseInt(input); //If not "quit", attempt to parse String into int

			while (choice > numChoices || choice < 1){ //Keep asking until menu choice is valid option
				System.out.println("[Invalid option]");
				System.out.print(prompt + " (1-" + numChoices + "):");
				input = scanner.next();
				if (input.equals("quit") || input.equals("q") || input.equals("Quit") || input.equals("Q")){ //Same dealio as above so continuous quit will still work if typed after invalid choice
					Quit();
					Choice(numChoices, prompt);
				}
				choice = Integer.parseInt(input); //Attempt String parse to int
			}
			
			return choice; //Hand user's selection back to caller
			
		} catch (InputMismatchException|NumberFormatException e) { //If invalid character entered or parse failed
			System.out.println("[Invalid input]");
			return Choice(numChoices, prompt); //Recursively call Choice() so user can try to enter their choice again
		}
	}
	
	public static double ChoiceDouble(double min, double max, String prompt){
		/* Method that streamlines process of getting user input for number values.
		 * Asks the user FOR A DOUBLE input from min to max inclusive.
		 * Will handle exceptions and invalid input.
		 * Takes doubles & str, returns double.
		 * Almost same method as above, but formatted for doubles.
		 */ 
		try {
			System.out.print(prompt + " ("+min+"-"+max+"):");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next(); //Scan for String for continuous quit
			if (input.equals("quit") || input.equals("q") || input.equals("Quit") || input.equals("Q")){ //If user entered quit
				Quit(); //Call for quit confirmation and execution sequence
				return ChoiceDouble(min, max, prompt); //If Quit() returns users changed mind, re-call ChoiceDouble() 
			}
			double choice = Double.parseDouble(input);
			
			while (choice < min || choice > max){ //Keep asking until menu choice is valid option
				System.out.println("[Invalid option]");
				System.out.print(prompt + " ("+min+"-"+max+"):");
				input = scanner.next();
				if(input.equals("quit") || input.equals("q") || input.equals("Quit") || input.equals("Q")){ //Same process as above for sensing quit
					Quit();
					return ChoiceDouble(min, max, prompt);
				}
				choice = Double.parseDouble(input);
			}
			
			return choice;
			
		} catch (InputMismatchException|NumberFormatException e) { //When invalid character entered or parse fails
			System.out.println("[Invalid input]");
			return ChoiceDouble(min, max, prompt); //Recursively call ChoiceDouble() if type error thrown.
		}
		
	}
	
	public static String RandPrompt(String[] prompts){
		/* Method that takes an array of strings and returns a random one.
		 * Used to spice up some of the game prompts and story.
		 */
		Random random = new Random();
		int randomChoice = random.nextInt(prompts.length); //Random int from 1-#prompts
		String selection = prompts[randomChoice];
		return selection;
	}
}
//800 LOC?!?! WTF?? (Sorry for using up all your time...trust me, I didn't want it to be this long either...)