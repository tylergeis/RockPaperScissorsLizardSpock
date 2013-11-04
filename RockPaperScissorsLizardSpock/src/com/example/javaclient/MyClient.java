package com.example.javaclient;

import com.example.rockpaperscissorslizardspock.Game;
import com.example.util.Message;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class MyClient extends Client {

		protected WebView webview1;
		
		public MyClient(int port, String ip, String playerName, WebView webview1){
			super(port, ip, playerName);
			this.webview1=webview1;
		}
		
		@Override
		protected void displayWinner(Integer winner) {
			// TODO: call JS function in WebView to update h1 tag with winner
			//System.out.println(playerNames[winner]+" won!");
			Game.setWinner(winner);
		}
		
		@Override
		protected void displayPlayerMoves(String[] moves) {
			// TODO: call JS function in WebView to display each player's moves
			for(int i=0; i<moves.length; i++){
				Game.setMoveByPlayerID(i,moves[i]);
			}
		}
		
		@Override
		protected void displayPlayerNames() {
			// TODO: call JS function in WebView to rewrite table with correct number of players and their names.
			// relevant variable: playerNames
			//System.out.println("Players now in game:");
			for(String s: playerNames){
				//System.out.println(s);
				Game.printPlayers(s);
			}
		}
		
		@Override
		protected void displayScores(){
			for(int i=0; i<scores.length; i++){
				// decouples WebView and command serialization
				Game.setScoreByPlayerID(i,scores[i]);
			}
		}

		/**
		 * Function that JS should call to notify the server that the player has set their move.
		 * @return
		 */
		@JavascriptInterface
		@Override
		public void sendMove(String move){
			super.sendMove(move);
		}
}
